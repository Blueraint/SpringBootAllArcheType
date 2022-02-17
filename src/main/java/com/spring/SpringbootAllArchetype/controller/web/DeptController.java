package com.spring.SpringbootAllArchetype.controller.web;

import com.spring.SpringbootAllArchetype.service.dept.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DeptController {

    final DeptService deptService;

    @GetMapping("/deptList")
    public String getDeptList(ModelMap modelMap) {

        modelMap.addAttribute("data", "This is deptList data");
        return "deptList";
    }

    @GetMapping("/dept/{deptno}")
    public String deptDetail(@PathVariable("deptno") int deptno, ModelMap modelMap) {

        modelMap.addAttribute("data", deptService.deptDetail(deptno));
        return "deptDetail";

    }
}
