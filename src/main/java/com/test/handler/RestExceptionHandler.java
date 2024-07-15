package com.test.handler;

import com.test.exception.ResultData;
import com.test.exception.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;


/**
 *
 * @ControllerAdvice和@RestControllerAdvice可以干净地将异常处理逻辑与控制器逻辑分离，为不同类型的异常提供一致的处理方式。
 *
 * @ControllerAdvice 返回视图（通常是 JSP、Thymeleaf 等） (视图名称(返回String)或模型(返回ModelAndView对象))
 *
 * @RestControllerAdvice  = @ControllerAdvice + @ResponseBody  返回对象序列化为的JSON或XML
 *
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     *
     * 注意避免全局性捕获所有异常（如 Exception.class），这可能会覆盖特定的异常处理（如 AccessDeniedException）
     *
     * 默认全局异常处理。
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return ResultData.fail(ReturnCode.RC500.getCode(),e.getMessage());
    }

    /**
     * 默认sql异常处理。
     * @param e_sql the e_sql
     * @return ResultData
     *
     * @ExceptionHandler,统一处理某一类异常
     *
     * 在Spring Boot应用中，可以使用 @ExceptionHandler 注解来统一处理某一类异常。
     * @ExceptionHandler 可以直接应用于例如controller内部的方法，该方式仅对该controller内的方法有效。
     * 或者通过 @ControllerAdvice 注解来将其提升到全局范围，以便对全局范围内的异常进行处理。
     *
     *
     */
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(SQLException e_sql) {
        log.error("sql异常信息 ex={}", e_sql.getMessage(), e_sql);
        return ResultData.fail(ReturnCode.RC500.getCode(),e_sql.getMessage());
    }

}
