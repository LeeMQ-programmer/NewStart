package com.start.pro.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Controller_Main {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String mainPage() {
		logger.info("main.do");
		
		return "index";
	}

	@RequestMapping(value = "/access_denied_page.do", method = RequestMethod.GET)
	public String denied() {
		logger.info("denied.do");
		System.out.println("안들어와??");
		return "access_denied";
	}
	
}
