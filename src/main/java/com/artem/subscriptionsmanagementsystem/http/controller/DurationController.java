
package com.artem.subscriptionsmanagementsystem.http.controller;

import com.artem.subscriptionsmanagementsystem.dto.duration.DurationCreateEditDto;
import com.artem.subscriptionsmanagementsystem.service.DurationService;
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
@RequestMapping("/durations")
@RequiredArgsConstructor
public class DurationController {

    private final DurationService durationService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("durations", durationService.findAll());

        return "duration/durations";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        return durationService.findById(id)
            .map(duration -> {
                model.addAttribute("duration", duration);
                return "duration/duration";
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/create")
    public String create(Model model,
                         @ModelAttribute("duration") DurationCreateEditDto duration) {
        model.addAttribute("duration", duration);
        return "duration/durationCreate";
    }

    @PostMapping("/createDuration")
    public String createDuration(@Validated DurationCreateEditDto duration,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("duration", duration);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/durations/create";
        }

        return "redirect:/durations/" + durationService.create(duration).getId();
    }

    @GetMapping("{id}/update")
    public String update(@PathVariable Integer id, Model model) {
        return durationService.findById(id)
            .map(duration -> {
                model.addAttribute("duration", duration);
                return "duration/durationEdit";
            }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/updateDuration")
    public String updateDuration(@PathVariable Integer id,
                                 @Validated DurationCreateEditDto duration,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("duration", duration);
            redirectAttributes.addFlashAttribute("bindingResult", bindingResult);
            return "redirect:/durations/{id}/update";
        }

        return durationService.update(id, duration)
            .map(it -> "redirect:/durations/{id}/update")
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable Integer id) {
        if (!durationService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return "redirect:/durations";
    }
}
