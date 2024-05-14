package com.test.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "An error occurred. Please try again later.";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}