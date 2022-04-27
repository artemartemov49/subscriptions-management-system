package com.artem.subscriptionsmanagementsystem.http.controller;

import com.artem.subscriptionsmanagementsystem.dto.price.PriceCreateEditDto;
import com.artem.subscriptionsmanagementsystem.service.DurationService;
import com.artem.subscriptionsmanagementsystem.service.ItemService;
import com.artem.subscriptionsmanagementsystem.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;
    private final ItemService itemService;
    private final DurationService durationService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("prices", priceService.findAll());

        return "price/prices";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        return priceService.findById(id)
            .map(price -> {
                model.addAttribute("price", price);
                return "price/price";
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model,
                         @ModelAttribute("price") PriceCreateEditDto price) {
        model.addAttribute("price", price);
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("durations", durationService.findAll());

        return "price/priceCreate";
    }

    @PostMapping("/createPrice")
    public String createPrice(@Validated PriceCreateEditDto price,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("price", price);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/prices/create";
        }

        return "redirect:/prices/" + priceService.create(price).getId();
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable Integer id, Model model) {
        return priceService.findById(id)
            .map(price -> {
                model.addAttribute("price", price);
                model.addAttribute("items", itemService.findAll());
                model.addAttribute("durations", durationService.findAll());
                return "price/priceEdit";
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/updatePrice")
    public String updatePrice(@PathVariable Integer id,
                              @Validated PriceCreateEditDto price,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("price", price);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/prices/{id}/updatePrice";
        }

        return priceService.update(id, price)
            .map(it -> "redirect:/prices/{id}/update")
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable Integer id) {
        if (!priceService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/prices";
    }
}
