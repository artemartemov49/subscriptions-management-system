
package com.artem.subscriptionsmanagementsystem.http.controller;

import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionCreateDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionEditDto;
import com.artem.subscriptionsmanagementsystem.service.PriceService;
import com.artem.subscriptionsmanagementsystem.service.SubscriptionService;
import com.artem.subscriptionsmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final PriceService priceService;
    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("subscriptions", subscriptionService.findAllWithUser());

        return "subscription/subscriptions";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        return subscriptionService.findByIdWithUser(id)
            .map(subscription -> {
                model.addAttribute("subscription", subscription);
                return "subscription/subscription";
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/add/{userId}")
    public String addSubscription(@PathVariable Integer userId,
                                  SubscriptionCreateDto subscription,
                                  @RequestParam(required = false) Integer itemId,
                                  Model model) {
        var prices = itemId == null
            ? priceService.findAll()
            : priceService.findAllByItemId(itemId);

        return userService.findById(userId)
            .map(userDto -> {
                    model.addAttribute("user", userDto);
                    model.addAttribute("users", userService.findAll());
                    model.addAttribute("prices", prices);
                    model.addAttribute("subscription", subscription);
                    return "subscription/subscriptionCreate";
                }
            ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/add")
    public String addSubscription(@Validated SubscriptionCreateDto subscription,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("subscription", subscription);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/subscriptions/create";
        }

        return "redirect:/subscriptions/" + subscriptionService.addSubscription(subscription).getId();
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable Integer id, Model model) {
        return subscriptionService.findByIdWithUser(id)
            .map(subscription -> {
                model.addAttribute("subscription", subscription);
                return "subscription/subscriptionEdit";
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/update")
    public String update(@PathVariable Integer id, @Validated SubscriptionEditDto subscription) {
        return subscriptionService.update(id, subscription)
            .map(it -> "redirect:/subscriptions/{id}/update")
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable Integer id) {
        if (!subscriptionService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/subscriptions";
    }
}
