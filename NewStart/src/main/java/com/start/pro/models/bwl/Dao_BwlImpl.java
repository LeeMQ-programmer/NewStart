package com.start.pro.models.bwl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.start.pro.dto.DTO_BWL;

@Repository
public class Dao_BwlImpl implements IDao_Bwl {


	@Autowired
	SqlSessionTemplate session;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.start.pro.bidding_w_list.";
	
	
	@Override
	public List<DTO_BWL> bwl_show() {
		log.info("dao impl bwl_show : \t{}");
		List<DTO_BWL> lists = session.selectList(NS+"bwl_show");
		return lists;
	}

	@Override
	public DTO_BWL bwl_detail(String seq) {
		log.info("dao impl bwl_detail : \t{}", seq);
		return session.selectOne(NS+"bwl_detail", seq);
	}

	@Override
	public boolean bwl_winner(DTO_BWL dto) {
		log.info("dao impl bwl_winner : \t{}", dto);
		int n = session.insert(NS+"bwl_winner", dto);
		return n > 0 ? true : false;
	}

}
