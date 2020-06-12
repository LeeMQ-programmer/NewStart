package com.start.pro.models.pay;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.start.pro.dto.DTO_Credit;
import com.start.pro.dto.DTO_Criteria;
import com.start.pro.dto.DTO_Pay;
import com.start.pro.dto.DTO_Refund_Credit;
import com.start.pro.dto.DTO_Refund_Pay;

public interface IDao_Pay {

	/**
	 * 결제 생성
	 * @param dto
	 * @return true
	 */
	public boolean createPay(DTO_Pay dto);
	
	/**
	 * 결제 내역 조회
	 * @param seq
	 * @return List<DTO_Pay>
	 */
//	public List<DTO_Pay> selectPay(DTO_Criteria cri);
	public List<DTO_Pay> selectPay(@Param("rowStart") int rowStart, @Param("rowEnd") int rowEnd, @Param("user_seq") String user_seq);
	
	
	/**
	 * 상품 번호 조회
	 * @param num
	 * @return int
	 */
	public int selectMax();
	
	/**
	 * 결제 환불
	 * @param seq
	 * @return true
	 */
	public boolean refundPay(int seq);
	
	/**
	 * 환불 여부 N으로 바꾸기
	 * @param seq
	 * @return	true
	 */
	public boolean updateRef(int seq);
	
	/**
	 * 환불내역 조회
	 * @param seq
	 * @return List<DTO_Refund_Pay>
	 */
//	public List<DTO_Refund_Pay> selectRef(DTO_Criteria cri);
	public List<DTO_Refund_Pay> selectRef(@Param("rowStart") int rowStart, @Param("rowEnd") int rowEnd, @Param("user_seq") String user_seq);
	
	/**
	 * 크레딧 생성
	 * @param dto
	 * @return true
	 */
	public boolean createCredit(DTO_Credit dto);
	
	/**
	 * 크레딧 내역 조회
	 * @param seq
	 * @return List<DTO_Credit>
	 */
	public List<DTO_Credit> selectCredit(@Param("rowStart") int rowStart, @Param("rowEnd") int rowEnd, @Param("user_seq") String user_seq);
	
	/**
	 * 크레딧 환불
	 * @param seq
	 * @return true
	 */
	public boolean refundCredit(String seq);
	
	/**
	 * 크레딧 환불 내역 조회
	 * @param seq
	 * @return List<DTO_Refund_Credit>
	 */
	public List<DTO_Refund_Credit> selectCreRef(String seq);
	
	/**
	 * 결제 게시물 총 갯수
	 * @return
	 */
	public int listCount(int seq);
	/**
	 * 환불 게시물 총 갯수
	 * @return
	 */
	public int refListCount(int seq);
	/**
	 * 크레딧 게시물 총 갯수
	 * @return
	 */
	public int creListCount(int seq);
}
