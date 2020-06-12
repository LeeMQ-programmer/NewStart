package com.start.pro.dto;

import java.util.List;

public class DTO_Filter {

	private String filter;
	private String firstDate;
	private String lastDate;
	private List<String> successchk;
	
	public DTO_Filter() {}

	public DTO_Filter(String filter, String firstDate, String lastDate, List<String> successchk) {
		super();
		this.filter = filter;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
		this.successchk = successchk;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public List<String> getSuccesschk() {
		return successchk;
	}

	public void setSuccesschk(List<String> successchk) {
		this.successchk = successchk;
	}

	@Override
	public String toString() {
		return "DTO_Filter [filter=" + filter + ", firstDate=" + firstDate + ", lastDate=" + lastDate + ", successchk="
				+ successchk + "]";
	}
	
	
	
}
