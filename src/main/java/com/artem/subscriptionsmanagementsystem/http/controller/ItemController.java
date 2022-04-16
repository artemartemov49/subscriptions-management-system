package com.artem.subscriptionsmanagementsystem.http.controller;

import com.artem.subscriptionsmanagementsystem.dto.item.ItemCreateEditDto;
import com.artem.subscriptionsmanagementsystem.dto.item.ItemReadDto;
import com.artem.subscriptionsmanagementsystem.service.ItemService;
import java.util.Optional;
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
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("items", itemService.findAll());

        return "item/items";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        return itemService.findById(id)
            .map(item -> {
                model.addAttribute("item", item);
                return "item/item";
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model,
                         ItemCreateEditDto item) {
        model.addAttribute("item", item);
        return "item/itemCreate";
    }

    @PostMapping("/create")
    public String create(@Validated ItemCreateEditDto item,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("item", item);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/items/create";
        }

        return "redirect:/items/" + itemService.create(item).getId();
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable Integer id, Model model) {
        return itemService.findById(id)
            .map(item -> {
                model.addAttribute("item", item);
                return "item/itemEdit";
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/update")
    public String update(@PathVariable Integer id, @Validated ItemCreateEditDto item) {
        return itemService.update(id, item)
            .map(it -> "redirect:/items/{id}/update")
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable Integer id) {
        if (!itemService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/items";
    }
}
