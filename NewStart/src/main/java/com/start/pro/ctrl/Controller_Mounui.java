package com.start.pro.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Controller_Mounui {


	@RequestMapping(value = "/insertBoard.do")
	public String insertBoard(){

		return "board/mounui/InsertBoard";
	}
}
