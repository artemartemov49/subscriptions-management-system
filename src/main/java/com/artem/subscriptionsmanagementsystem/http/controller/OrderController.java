
package com.artem.subscriptionsmanagementsystem.http.controller;

import com.artem.subscriptionsmanagementsystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String findAll(@RequestParam(required = false) Integer userId,
                          Model model) {

        var orders = userId == null
            ? orderService.findAll()
            : orderService.findAllByUserId(userId);

        model.addAttribute("orders", orders);

        return "order/orders";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        return orderService.findById(id)
            .map(order -> {
                model.addAttribute("order", order);
                return "order/order";
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable Integer id) {
        if (!orderService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/orders";
    }
}
