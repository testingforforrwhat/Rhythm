package com.test.controller;

import com.test.exception.ResultData;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResultData<Object> handleError() {
        return ResultData.fail(500,"An error occurred. Please try again later.");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}