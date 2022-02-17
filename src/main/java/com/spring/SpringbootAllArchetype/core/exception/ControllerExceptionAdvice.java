package com.spring.SpringbootAllArchetype.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(value = "com.spring.SpringbootAllArchetype.controller.web")
public class ControllerExceptionAdvice {

    // Exception 시에 dispatcherservlet 가 돌려줄 페이지 set
    @ExceptionHandler(Exception.class)
    public String Exception(ModelMap modelMap, Exception e) {
        modelMap.addAttribute("message",e.getMessage());

        return "error";
    }
}
