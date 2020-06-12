package com.start.pro.models.bidding;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_Bidding;

@Repository
public class Dao_Bidding implements IDao_Bidding {


	@Autowired
	SqlSessionTemplate session;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.start.pro.bidding.";
	
	
	@Override
	public boolean bidding_insert(DTO_Bidding dto) {
		log.info("dao impl bidding_insert : \t{}", dto);
		int n = session.insert(NS+"bidding_list", dto);
		return n >0 ? true:false;
	}

	@Override
	public boolean bidding_update(DTO_Bidding dto) {
		log.info("dao impl bidding_update : \t{}", dto);
		int n = session.insert(NS+"bidding_update", dto);
		return n >0 ? true:false;
	}

	@Override
	public List<DTO_Bidding> bidding_list(String seq) {
		log.info("dao impl bidding_list : \t{}", seq);
		List<DTO_Bidding> lists = session.selectList(NS+"bidding_list", seq);
		return lists;
	}

}
