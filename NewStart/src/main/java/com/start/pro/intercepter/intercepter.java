package com.start.pro.intercepter;


import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class intercepter extends HandlerInterceptorAdapter {

	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("인터셉터 가동");
		System.out.println("getContentType"+request.getContentType());
		System.out.println("getContextPath"+request.getContextPath());
		System.out.println("getQueryString"+request.getQueryString());
		System.out.println("getgetRequestURI()"+request.getRequestURI());
		System.out.println("getgetAttributeNames()"+request.getAttributeNames());
		
		
		
		return super.preHandle(request, response, handler);
	}
	
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

			if(modelAndView.getModel().get("filename") != null) {
				System.out.println("_______________ㅎㅎ__________________________");
			}
	}
	
}
