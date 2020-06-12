package com.start.pro.security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.start.pro.captcha.ICaptchaKey;
import com.start.pro.models.login.IService_Login;
import com.start.pro.util.Util_Message;

public class Sc_LoginFailureHandler implements AuthenticationFailureHandler{

	@Autowired
	private IService_Login service;
	
	@Autowired
	private Util_Message message;
	
	//키를 받아오는 클래스
	@Resource(name = "getKey")
	private ICaptchaKey getKey;
	
	
	private String loginidname;
	private String loginpwdname;
	private String errormsgname;
	private String defaultFailureUrl;
	
	
	public String getLoginidname() {
		return loginidname;
	}

	public void setLoginidname(String loginidname) {
		this.loginidname = loginidname;
	}
	
	public String getLoginpwdname() {
		return loginpwdname;
	}

	public void setLoginpwdname(String loginpwdname) {
		this.loginpwdname = loginpwdname;
	}

	public String getErrormsgname() {
		return errormsgname;
	}

	public void setErrormsgname(String errormsgname) {
		this.errormsgname = errormsgname;
	}

	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println("로그인실패핸들러");
		String id = req.getParameter("username");
		String password = req.getParameter("password");
		String errormsg = null;
		
		int cnt = 0;
		System.out.println(exception);
		if(exception instanceof LockedException){
        	System.out.println("_____________________________________________________________________왜안들어와?");
        	cnt = loginFailureCount(id);
        	errormsg = message.getMessage("error.LockedException");
        
		}else if(exception instanceof BadCredentialsException) {
			cnt = loginFailureCount(id);
			errormsg = message.getMessage("error.BadCredentials");
		}else if(exception instanceof InternalAuthenticationServiceException) {
            errormsg = message.getMessage("error.BadCredentials");
        }
		
		
		System.out.println(errormsg+"오류!");
		
		req.setAttribute("id", id);
		req.setAttribute("password", password);
		req.setAttribute("error", errormsg);
		
		
		if(cnt >= 5) {
			String key = getccKey();
			req.setAttribute("key", key);
		}
			req.getRequestDispatcher(defaultFailureUrl).forward(req, resp);
	}
	
	
	private int loginFailureCount(String id) {
		System.out.println(id);
		int cnt = Integer.parseInt(service.PWFail(id));
		System.out.println("이 사용자는 비밀번호를 "+cnt+"만큼 틀렸습니당");
		return cnt;
	
	}
	
	
	private String getccKey() {
		
		String key = getKey.get("0");
		System.out.println(key+"키 받아왔나요?");
		
		// json으로 key 값 뽑아오기
        JSONParser parser  = new JSONParser();
        Object obj = null;
		try {
			obj = parser.parse(key);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        JSONObject jsonobj = (JSONObject) obj;
        
        key = (String) jsonobj.get("key");
        System.out.println(key);
        return key;
	}
	
	
}
