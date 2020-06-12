package com.start.pro.models.pay;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.start.pro.dto.DTO_Credit;
import com.start.pro.dto.DTO_Pay;
import com.start.pro.dto.DTO_Refund_Credit;
import com.start.pro.dto.DTO_Refund_Pay;

@Service
public class Service_Pay implements IService_Pay {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IDao_Pay dao;
	
	@Override
	public boolean createPay(DTO_Pay dto) {
		logger.info("createPay, {}", dto);
		return dao.createPay(dto);
	}

	@Override
	public List<DTO_Pay> selectPay(String seq) {
		logger.info("selectPay, {}", seq);
		return dao.selectPay(seq);
	}

	@Override
	public boolean refundPay(String seq) {
		logger.info("refundPay, {}", seq);
		return dao.refundPay(seq);
	}

	@Override
	public List<DTO_Refund_Pay> selectRef(String seq) {
		logger.info("selectRef, {}", seq);
		return dao.selectRef(seq);
	}

	@Override
	public boolean createCredit(DTO_Credit dto) {
		logger.info("createCredit, {}", dto);
		return dao.createCredit(dto);
	}

	@Override
	public List<DTO_Credit> selectCredit(String seq) {
		logger.info("selectCredit, {}", seq);
		return dao.selectCredit(seq);
	}

	@Override
	public boolean refundCredit(String seq) {
		logger.info("refundCredit, {}", seq);
		return dao.refundCredit(seq);
	}

	@Override
	public List<DTO_Refund_Credit> selectCreRef(String seq) {
		logger.info("selectCreRef, {}", seq);
		return dao.selectCreRef(seq);
	}

	@Override
	public int selectMax() {
		logger.info("selectMax, {}", new Date());
		return dao.selectMax();
	}

}
