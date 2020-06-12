package com.start.pro.ctrl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.servlet.http.HttpSession;

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

import com.start.pro.dto.DTO_Pay;
import com.start.pro.models.pay.IService_Pay;

@Controller
public class Controller_Pay {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IService_Pay service;
	
	@RequestMapping(value = "/pay.do", method = RequestMethod.GET)
	public String payPage() {
		logger.info("pay.do {}", new Date());
		System.out.println("▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒");
		
		return "pay/pay";
	}
	
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
			
//			intent://pay?payToken={payToken}#Intent;scheme=supertoss;package=viva.republica.toss;end
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
		
		DTO_Pay dto = new DTO_Pay(null, token, orderNo, selCash, null, 1);
		service.createPay(dto);

		model.addAttribute("payed", checkoutPage);
		session.setAttribute("token", token);
		
//		return null;
		return "index";
	}
	
}
