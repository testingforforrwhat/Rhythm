package com.test.interceptor;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
	/**
	 * 目标方法之前之前执行
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println(request.getRequestURI());

		// 在这里编写登录的拦截的逻辑
		// 获取到请求头中的token
		String token = request.getHeader("authorization");
		// 准备好登录的提示信息
		HashMap<Object, Object> map = new HashMap<>();
		map.put("httpstatus","error");
		map.put("message","用户未登录");
		map.put("httpcode","401");

		// 判断这个token是否为null
		if(token == null){
			log.warn("用户未登录");
			// 直接返回数据 提示 未登录
			response.setContentType("application/json;charset=utf8");
			PrintWriter writer = response.getWriter();
			writer.println(map);
			writer.close();
			return false;
		}else {
			log.info("获取到了用户token");
			log.info("用户token:{}",token);
			// 解析token 获取到 userId
			Object userId = JSON.parseObject(JWT.decode( token).getAudience().get(0).toString()).get("userId");
			// 不可以直接强转 需要使用hutool工具的类型转换工具
			Integer convert = Convert.convert(Integer.class, userId);
			// 如果一切正常 说明 登录了 直接返回true
			return true;
		}
	}

	/**
	 * 目标方法执行之后才会执行
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 页面渲染完毕之后执行
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
