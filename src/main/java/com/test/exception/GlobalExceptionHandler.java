package com.test.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAccessDeniedException(AccessDeniedException ex, Model model) {
        model.addAttribute("error", "You do not have permission to access this page.");
        return "error/403";
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public String handleInsufficientAuthenticationException(InsufficientAuthenticationException ex, Model model) {
        return "redirect:/login";
    }

    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(AuthenticationException ex, Model model) {
        return "redirect:/login";
    }

    /**
     *
     * 如果存在全局异常处理器，它可能会拦截并处理异常，从而导致自定义的 AccessDeniedHandler 未被调用。
     *
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("error", "An unexpected error occurred.");
        return "error/500";
    }
}