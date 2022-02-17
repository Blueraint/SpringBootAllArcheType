package com.spring.SpringbootAllArchetype.core.exception;

import com.spring.SpringbootAllArchetype.core.dto.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice(value = "com.spring.SpringbootAllArchetype.controller.api")
public class RestControllerExceptionAdvice {

    // Exception 시에 dispatcherservlet 가 돌려줄 페이지 set
    @ExceptionHandler(Exception.class)
    public MessageDto Exception(HttpServletResponse response, Exception e) {
        return new MessageDto(false, e.getMessage());
    }
}