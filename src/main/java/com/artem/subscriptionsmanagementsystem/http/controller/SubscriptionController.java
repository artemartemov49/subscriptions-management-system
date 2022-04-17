
package com.artem.subscriptionsmanagementsystem.http.controller;

import com.artem.subscriptionsmanagementsystem.dto.order.SubscriptionWithOrderCreateDto;
import com.artem.subscriptionsmanagementsystem.dto.subscription.SubscriptionEditDto;
import com.artem.subscriptionsmanagementsystem.service.OrderService;
import com.artem.subscriptionsmanagementsystem.service.SubscriptionService;
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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("subscriptions", subscriptionService.findAll());

        return "subscription/subscriptions";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        return subscriptionService.findById(id)
            .map(subscription -> {
                model.addAttribute("subscription", subscription);
                return "subscription/subscription";
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String createWithOrder(Model model,
                                  SubscriptionWithOrderCreateDto subscription) {
        model.addAttribute("subscription", subscription);
        return "subscription/subscriptionCreate";
    }

    @PostMapping("/create")
    public String createWithOrder(@Validated SubscriptionWithOrderCreateDto subscription,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("subscription", subscription);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/subscriptions/create";
        }

        return "redirect:/subscriptions/" + subscriptionService.createWithOrder(subscription).getId();
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable Integer id, Model model) {
        return subscriptionService.findById(id)
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
