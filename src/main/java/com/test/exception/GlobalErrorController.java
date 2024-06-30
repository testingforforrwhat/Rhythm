package com.test.exception;

import com.test.exception.ResultData;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 显式处理 /error
 * 不使用默认的错误处理机制, 即: "This application has no explicit mapping for /error, so you are seeing this as a fallback."
 *
 */
@RestController
public class GlobalErrorController implements ErrorController {

    /**
     *
     * 返回自定义的错误消息或页面
     *
     * @return
     */
    @RequestMapping("/error")
    public ResultData<Object> handleError() {
        return ResultData.fail(500,"An error occurred. Please try again later..");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}