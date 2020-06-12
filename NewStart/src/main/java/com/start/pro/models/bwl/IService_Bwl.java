package com.start.pro.models.bwl;

import java.util.List;

import com.start.pro.dto.DTO_BWL;

public interface IService_Bwl {

	/**
	 * 필요기능 1. 성공된 매칭의 매칭 입찰자 전체 조회하기
	 * @return
	 */
	public List<DTO_BWL> bwl_show ();
	
	/**
	 * 필요기능 2. 성공된 매칭의 매칭 입찰자 상세 조회하기
	 * @param seq
	 * @return
	 */
	public DTO_BWL bwl_detail (String seq);
	
	/**
	 * 필요기능 3. 성공된 매칭의 매칭 입찰자 등록하기
	 * @param dto
	 * @return
	 */
	public boolean bwl_winner (DTO_BWL dto);
}
