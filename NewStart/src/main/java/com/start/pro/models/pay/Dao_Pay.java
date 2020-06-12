package com.start.pro.models.pay;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_Credit;
import com.start.pro.dto.DTO_Pay;
import com.start.pro.dto.DTO_Refund_Credit;
import com.start.pro.dto.DTO_Refund_Pay;

@Repository
public class Dao_Pay implements IDao_Pay {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.start.pro.pay.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	@Override
	public boolean createPay(DTO_Pay dto) {
		logger.info("createPay");
		int a = sqlSession.insert(NS+"createPay", dto);
		return a > 0 ? true : false;
	}

	@Override
	public List<DTO_Pay> selectPay(String seq) {
		logger.info("selectPay");
		return sqlSession.selectList(NS+"selectPay", seq);
	}

	@Override
	public boolean refundPay(String seq) {
		logger.info("refundPay");
		int a = sqlSession.insert(NS+"refundPay", seq);
		return a > 0 ? true : false;
	}

	@Override
	public List<DTO_Refund_Pay> selectRef(String seq) {
		logger.info("selectRef");
		return sqlSession.selectList(NS+"selectRef", seq);
	}

	@Override
	public boolean createCredit(DTO_Credit dto) {
		logger.info("createCredit");
		int a = sqlSession.insert(NS+"createCredit", dto);
		return a > 0 ? true : false;
	}

	@Override
	public List<DTO_Credit> selectCredit(String seq) {
		logger.info("selectCredit");
		return sqlSession.selectList(NS+"selectCredit", seq);
	}

	@Override
	public boolean refundCredit(String seq) {
		logger.info("refundCredit");
		int a = sqlSession.insert(NS+"refundCredit", seq);
		return a > 0 ? true : false;
	}

	@Override
	public List<DTO_Refund_Credit> selectCreRef(String seq) {
		logger.info("selectCreRef");
		return sqlSession.selectList(NS+"selectCreRef", seq);
	}

	@Override
	public int selectMax() {
		logger.info("selectMax");
		return sqlSession.selectOne(NS+"selectMax");
	}

}
