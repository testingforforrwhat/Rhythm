//package com.test.exception;
//
//import com.test.exception.ResultData;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// *
// * 显式处理 /error
// * 不使用默认的错误处理机制, 即: "This application has no explicit mapping for /error, so you are seeing this as a fallback."
// *
// *
// * 全局异常处理。在处理异常时，开发者可以根据实际情况返回不同的页面，但是这种异常处理方式一般用来处理应用级别的异常，有一些容器级别的错误就处理不了，例如Filter中抛出异常，使用@ControllerAdvice定义的全局异常处理机制就无法处理。因此，Spring Boot中对于异常的处理还有另外的方式
// * (caution: dev时, 请取消, 使得可以查看error具体
// *
// */
//@RestController
//public class GlobalErrorController implements ErrorController {
//
//    /**
//     *
//     * 返回自定义的错误消息或页面
//     *
//     * @return
//     */
//    @RequestMapping("/error")
//    public ResultData<Object> handleError() {
//        return ResultData.fail(500,"An error occurred. Please try again later..");
//    }
//
//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
//}