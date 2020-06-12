package com.start.pro.dto;

import java.util.Date;

public class DTO_Refund_Pay {

	private String refund_seq  ;
	private Date refund_date ;
	private String pay_seq     ;
	
	public DTO_Refund_Pay() {
		// TODO Auto-generated constructor stub
	}
	public String getRefund_seq() {
		return refund_seq;
	}
	public void setRefund_seq(String refund_seq) {
		this.refund_seq = refund_seq;
	}
	public Date getRefund_date() {
		return refund_date;
	}
	public void setRefund_date(Date refund_date) {
		this.refund_date = refund_date;
	}
	public String getPay_seq() {
		return pay_seq;
	}
	public void setPay_seq(String pay_seq) {
		this.pay_seq = pay_seq;
	}
	
	@Override
	public String toString() {
		return "DTO_Refund_Pay [refund_seq=" + refund_seq + ", refund_date=" + refund_date + ", pay_seq=" + pay_seq
				+ "]";
	}
	public DTO_Refund_Pay(String refund_seq, Date refund_date, String pay_seq) {
		super();
		this.refund_seq = refund_seq;
		this.refund_date = refund_date;
		this.pay_seq = pay_seq;
	}
	
}
