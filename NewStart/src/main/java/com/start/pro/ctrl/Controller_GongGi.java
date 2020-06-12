package com.start.pro.ctrl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.start.pro.dto.DTO_Gonggi;
import com.start.pro.dto.DTO_PageMaker;
import com.start.pro.dto.DTO_Criteria;
import com.start.pro.models.gonggi.IService_Gonggi;

@Controller
public class Controller_GongGi {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IService_Gonggi service;
	
	/**
	 * 공지사항 전체 조회
	 * @param model, dto
	 * @return lists
	 */
	@RequestMapping(value = "/gongGiList.do", method = RequestMethod.GET)
	public String gongGiList(Model model, DTO_Criteria cri) {
		logger.info("gongGiList.do : \t {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		List<DTO_Gonggi> lists = service.GI_AllSelect(cri);
		model.addAttribute("lists", lists);

		DTO_PageMaker pageMaker = new DTO_PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCount());
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "board/gonggi/gonggi";
	}
	
	@RequestMapping(value = "/gonggiOneSel.do", method = RequestMethod.GET)
	public String gongGiOneSel(Model model, String seq, String category) {
		logger.info("gongGiOneSel.do : \t {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gi_seq", seq);
		map.put("gi_category", category);
		
		DTO_Gonggi dto = service.GI_OneSelect(map);
		
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒"+dto+"▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		model.addAttribute("dto", dto);
		
		return "board/gonggi/gonggiOneSel";
	}
	
	@RequestMapping(value = "/modify.do", method = RequestMethod.GET)
	public String modify(Model model, String seq, String title, String category, 
						String content, String regdate) {
		logger.info("modify.do : \t {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		System.out.println("▒▒▒▒▒▒▒"+title+"▒▒▒▒▒");
		DTO_Gonggi dto = new DTO_Gonggi(seq, 1000, category, title, content, regdate);
		
		model.addAttribute("dto", dto);
		
		return "board/gonggi/modify";
	}
	
	@RequestMapping(value = "/modifyForm.do", method = RequestMethod.POST)
	public String modifyForm(Model model, String seq, String title, String content, String category) {
		logger.info("modifyForm.do : \t {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		DTO_Gonggi dto = new DTO_Gonggi(seq, title, content, category);
		System.out.println(category);
		System.out.println(seq);
		System.out.println(title);
		System.out.println(content);
		boolean n;
		n = service.GI_Update(dto);
		
		return "redirect:/gongGiList.do";
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public String deleteGi(String seq) {
		logger.info("delete.do : \t {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println(seq);
		
		boolean n;
		n = service.GI_Delete(seq);
		
		return "redirect:/gongGiList.do";
	}
	
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String write() {
		logger.info("write.do : \t {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		return "board/gonggi/gonggiWrite";
	}
	
	@RequestMapping(value = "/writeForm.do", method = RequestMethod.POST)
	public String writeForm(String title, String content, String category) {
		logger.info("writeFrom.do : \t {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println(category);
		
		DTO_Gonggi dto = new DTO_Gonggi(title, content);
		
		boolean n;
		
		if (category.equalsIgnoreCase("Y")) {
			n = service.GI_Imp_Insert(dto);
		} else {
			n = service.GI_UImp_Insert(dto);
		}
		
		return "redirect:/gongGiList.do";
	}
	
	
}
