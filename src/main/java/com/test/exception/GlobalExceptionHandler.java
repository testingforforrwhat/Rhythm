package com.test.exception;

import com.test.security.PermissionErrorHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 在使用 Spring 的全局异常处理器（例如 @ControllerAdvice）时，我们需要注意避免全局性捕获所有异常（如 Exception.class），
 * 这可能会覆盖特定的异常处理（如 AccessDeniedException）。
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private PermissionErrorHandle permissionErrorHandle;

    /**
     *
     * 如果存在一个全局的异常处理器（例如使用 @ControllerAdvice 和 @ExceptionHandler），它可能会拦截并处理异常，从而导致自定义的 AccessDeniedHandler 未被调用。
     *
     * 我们可以采取以下策略：
     *
     * 确保 Spring Security 异常处理优先：确保 Spring Security 的异常处理优先于全局异常处理器。可以在全局异常处理器中排除 Spring Security 相关的异常。
     *
     * 手动调用 AccessDeniedHandler：在全局异常处理器中，如果捕获到 AccessDeniedException，可以手动调用自定义的 AccessDeniedHandler。
     *
     *
     * @param request
     * @param response
     * @param ex
     * @throws ServletException
     * @throws IOException
     */
    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws ServletException, IOException {
        // 手动调用自定义的 AccessDeniedHandler
        permissionErrorHandle.handle(request, response, ex);
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
     * 在使用 Spring 的全局异常处理器（例如 @ControllerAdvice）时，我们需要注意避免全局性捕获所有异常（如 Exception.class），这可能会覆盖特定的异常处理（如 AccessDeniedException）。
     *
     * @param ex
     * @param model
     * @return
     */
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handleException(Exception ex, Model model) {
//        model.addAttribute("error", "An unexpected error occurred.");
//        return "error/500";
//    }
}