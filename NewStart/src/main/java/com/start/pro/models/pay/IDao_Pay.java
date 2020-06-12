package com.start.pro.models.pay;

import java.util.List;

import com.start.pro.dto.DTO_Credit;
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
	public List<DTO_Pay> selectPay(String seq);
	
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
	public boolean refundPay(String seq);
	
	/**
	 * 환불내역 조회
	 * @param seq
	 * @return List<DTO_Refund_Pay>
	 */
	public List<DTO_Refund_Pay> selectRef(String seq);
	
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
	public List<DTO_Credit> selectCredit(String seq);
	
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
}
