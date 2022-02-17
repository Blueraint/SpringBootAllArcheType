package com.spring.SpringbootAllArchetype.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BaseController {

    @GetMapping("/")
    public String getIndex(ModelMap modelMap) {

        modelMap.addAttribute("data", "This is get data");
        return "index";
    }

    @PostMapping("/")
    public String postIndex(ModelMap modelMap) {

        modelMap.addAttribute("data", "This is post data");
        return "index";
    }
}
