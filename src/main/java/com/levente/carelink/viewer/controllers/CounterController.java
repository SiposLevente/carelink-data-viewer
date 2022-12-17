package com.levente.carelink.viewer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.levente.carelink.viewer.objects.CareLinkDataManager;

@Controller
public class CounterController {
    @GetMapping({ "/bg", "/" })
    public String counter(Model model) {
        SetAttributes(model);
        return "glucoseMonitor";
    }

    @GetMapping("/currentData")
    public String current_counter(Model model) {
        return "currentData";
    }

    private void SetAttributes(Model model) {
        model.addAttribute("bg", CareLinkDataManager.getCurrentSGString(2));
        model.addAttribute("bg_delta", CareLinkDataManager.getSGDeltaString(2));
        model.addAttribute("unit", CareLinkDataManager.getUnit());
    }

}
