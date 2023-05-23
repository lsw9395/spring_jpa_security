package com.nhnacademy.spring.jpa_security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class WebControllerAdvice {

    @ExceptionHandler(Exception.class)
    public String handlerException(Exception e, Model model) {
        model.addAttribute("exception", e.getMessage());

        e.printStackTrace();

        return "error";
    }

}
