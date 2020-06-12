package com.start.pro.ctrl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.start.pro.dto.DTO_Credit;
import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_Gonggo;
import com.start.pro.dto.DTO_PageMaker;
import com.start.pro.dto.DTO_Pay;
import com.start.pro.dto.DTO_Refund_Pay;
import com.start.pro.dto.DTO_User;
import com.start.pro.models.gonggo.IService_Gonggo;
import com.start.pro.models.pay.IService_Pay;
import com.start.pro.models.user.IService_User;

@Controller
public class Controller_Pay {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IService_Pay service;
	
	@Autowired
	private IService_User user;
	
	@Autowired
	private IService_Gonggo gonggo;
	
	@RequestMapping(value = "/pay.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String payPage(HttpSession session) {
		logger.info("pay.do {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		return "pay/pay";
	}
	
	@ResponseBody
	@RequestMapping(value = "/payment.do", method = {RequestMethod.POST, RequestMethod.GET})
	public String payPage(Model model, HttpSession session, String selCash) {
		logger.info("payment.do {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		System.out.println(selCash);
		
		URL url = null;
		URLConnection connection = null;
		StringBuilder responseBody = new StringBuilder();
		
		int a = service.selectMax();
		int orderNo = a + 1;
		
		try {
			
			url = new URL("https://pay.toss.im/api/v2/payments");
			connection = url.openConnection();
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			
			JSONObject jsonBody = new JSONObject();
			jsonBody.put("orderNo", orderNo);
			jsonBody.put("amount", selCash);
			jsonBody.put("amountTaxFree", 0);
			jsonBody.put("productDesc", "테스트 결제");
			jsonBody.put("autoExecute", true);
			jsonBody.put("apiKey", "sk_test_w5lNQylNqa5lNQe013Nq");
			jsonBody.put("resultCallback", "http://localhost:8095/NewStart/callback.do");
		    jsonBody.put("retUrl", "http://localhost:8095/NewStart/main.do?orderno=" + orderNo);
		    jsonBody.put("retCancelUrl", "http://localhost:8095/NewStart/cancel.do");

			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
			
		    bos.write(jsonBody.toJSONString().getBytes(StandardCharsets.UTF_8));
			bos.flush();
			bos.close();

			
		    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			String line = null;
			while ((line = br.readLine()) != null) {
				responseBody.append(line);
			}
			br.close();
		} catch (Exception e) {
			responseBody.append(e);
		}
		System.out.println(responseBody.toString()+"응답받은거");
		String s = responseBody.toString();
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject jsonresult = (JSONObject) obj;
		String token = (String) jsonresult.get("payToken");
		String checkoutPage = (String) jsonresult.get("checkoutPage");
		
		System.out.println("잘담기나요??"+token);
		System.out.println("잘담기나요??"+checkoutPage);
		
		DTO_User user = (DTO_User) session.getAttribute("newstart");
		DTO_Pay dto = new DTO_Pay(0, token, orderNo, selCash, null, user.getUser_seq(), "Y");
		
		service.createPay(dto);
		
		model.addAttribute("payed", checkoutPage);
		session.setAttribute("token", token);
		session.setAttribute("selCash", selCash);
		model.addAttribute("user", user);
		
		String u = user.getUser_seq();
		int cnt = Integer.parseInt(u);
		
		int sel = Integer.parseInt(selCash);
		if (sel == 10000) {
			int credit = 10;
			DTO_Credit dtoo = new DTO_Credit(credit, cnt);
			service.createCredit(dtoo);
		} else if (sel == 50000) {
			int credit = 50;
			DTO_Credit dtoo = new DTO_Credit(credit, cnt);
			service.createCredit(dtoo);
		} else if (sel == 100000) {
			int credit = 100;
			DTO_Credit dtoo = new DTO_Credit(credit, cnt);
			service.createCredit(dtoo);
		} else if (sel == 200000) {
			int credit = 200;
			DTO_Credit dtoo = new DTO_Credit(credit, cnt);
			service.createCredit(dtoo);
		} else {
			int credit = 500;
			DTO_Credit dtoo = new DTO_Credit(credit, cnt);
			service.createCredit(dtoo);
		}
		
		return checkoutPage;
	}
	
	@RequestMapping(value = "/cancel.do", method = RequestMethod.POST)
	public String cancel(HttpSession session, HttpServletRequest req) {
		logger.info("cancel.do {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		URL url = null;
		URLConnection connection = null;
		StringBuilder responseBody = new StringBuilder();

		String token = req.getParameter("token");
		System.out.println(token);
		String selCash = (String)session.getAttribute("selCash");
		System.out.println(token + "나 환불 토큰이야 이것드라");
		try {
			url = new URL("https://pay.toss.im/api/v2/refunds");
			connection = url.openConnection();
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			connection.setDoInput(true);

			org.json.simple.JSONObject jsonBody = new JSONObject();
			jsonBody.put("payToken", token);
			jsonBody.put("amount", selCash);
			jsonBody.put("apiKey", "sk_test_w5lNQylNqa5lNQe013Nq");

			BufferedOutputStream bos = new BufferedOutputStream(connection.getOutputStream());
			
		    bos.write(jsonBody.toJSONString().getBytes(StandardCharsets.UTF_8));
			bos.flush();
			bos.close();

			
		    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
			String line = null;
			while ((line = br.readLine()) != null) {
				responseBody.append(line);
			}
			br.close();
		} catch (Exception e) {
			responseBody.append(e);
		}
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&"+responseBody.toString());
		String s = responseBody.toString();
		
		service.refundPay(1);
		String ss = req.getParameter("seq");
		System.out.println(ss.getClass().getName());
		
		int seq = Integer.parseInt(ss);
		
		System.out.println(seq);
		service.updateRef(seq);
		
		return "redirect:/payList.do";
	}
	
	@RequestMapping(value = "/payList.do", method = RequestMethod.GET)
	public String payList(Model model, DTO_Criteria cri, HttpSession session) {
		logger.info("payList.do {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		DTO_User user = (DTO_User) session.getAttribute("newstart");
//		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒"+user);
		model.addAttribute("user", user);
		String u = user.getUser_seq();
		int cnt = Integer.parseInt(u);
		
		int first = cri.getRowStart();
		int end = cri.getRowEnd();
//		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒"+cri);
		List<DTO_Pay> lists = service.selectPay(first, end, u);
		
//		DTO_Refund_Pay dto = new DTO_Refund_Pay(u, 0);
		List<DTO_Refund_Pay> listss = service.selectRef(first, end, u);
		
		DTO_PageMaker pageMaker = new DTO_PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCount(cnt));
		
		DTO_PageMaker pageMaker1 = new DTO_PageMaker();
		pageMaker1.setCri(cri);
		pageMaker1.setTotalCount(service.refListCount(cnt));
		
//		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒"+lists);
		model.addAttribute("lists", lists);
		model.addAttribute("listss", listss);
		
//		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒TotalCount"+pageMaker.getTotalCount());
//		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒StartPage"+pageMaker.getStartPage());
//		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒EndPage"+pageMaker.getEndPage());
//		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒RowEnd"+cri.getRowEnd());
//		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒RowStart"+cri.getRowStart());
//		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒Page"+cri.getPage());
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("pageMaker1", pageMaker1);
		model.addAttribute("user", user);
		
		return "pay/payList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/listChange.do", method = RequestMethod.POST)
	public String pList(String sel) {
		logger.info("payList.do {}", new Date());
		String isc = "false";
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		if(sel.trim().equals("cash")) {
			isc = "true";
		}
		return isc;
	}
	
	@RequestMapping(value = "/credit.do", method = RequestMethod.GET)
	public String creditList(Model model, HttpSession session, DTO_Criteria cri) {
		logger.info("credit.do {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		DTO_User user = (DTO_User) session.getAttribute("newstart");
		String u = user.getUser_seq();
		int cnt = Integer.parseInt(u);
		
		int first = cri.getRowStart();
		int end = cri.getRowEnd();
		
		List<DTO_Credit> lists = service.selectCredit(first, end, u);
		
		DTO_PageMaker pageMaker = new DTO_PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.creListCount(cnt));
		
		
		
		
		model.addAttribute("lists", lists);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("user", user);
		
		return "pay/credit";
	}
	
}
