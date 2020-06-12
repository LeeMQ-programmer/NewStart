package com.start.pro.models.gonggo;

import java.util.List;

import com.start.pro.dto.DTO_Gonggo;
import com.start.pro.dto.DTO_User;

public class inputList {
	private List<DTO_Gonggo> lists;
	private DTO_User users;

	public void setLists(List<DTO_Gonggo> lists) {
		this.lists = lists;
	}

	public void setUsers(DTO_User users) {
		this.users = users;
	}

	// 날짜
	private String dateFormat(String date) {
		return date.substring(0, date.indexOf(" "));
	}

	

	// TODO
	// 관리자의 이미지 미리보기 구현 파트로 사용자들이 등록한 이미지를 디비로부터 찾아와서 연결해주면 되겠다.
	private String titleFormat() {
		StringBuffer buf = new StringBuffer();
		buf.append("<img src='./img/reply.jpg'/>");
		return buf.toString();
	}

	// 출력 리스트 폼
	private String listForm(DTO_Gonggo dto) {
		System.out.println("inputList시작합니다. : \t{}");
		System.out.println("DTO_Gonggo : \t{}" + dto);
		System.out.println("DTO_User : \t{}" + users);
		StringBuffer buf = new StringBuffer();
		
		// colspan의 기본 user 종류별로 구분

			System.out.println("유저쪽으로 유입 : \t{}" + dto);
			
			int n = 7;
			buf.append("<tr>");
			buf.append("<td><input type='checkbox' name='chkVal' value='" + dto.getGonggo_seq() + "'></td>");
			buf.append("<td>" + dto.getGonggo_seq() + "</td>");
			buf.append("<td>");
			buf.append("<div class='panel-heading'>");
			buf.append("<a data-toggle='collapse' data-parent='#accordion' href='#collapse" + dto.getGonggo_seq()
					+ "' onclick='collapse(\"" + dto.getGonggo_seq() + "\")'>" + titleFormat() + dto.getGonggo_title()
					+ "</a>	");
			buf.append("</div>");
			buf.append("</td>");
			buf.append("<td>" + dto.getUser_seq() + "</td>");
			buf.append("<td>" + dateFormat(dto.getGonggo_time()) + "</td>");
			n = 7;
			buf.append("<td>" + dto.getFileox() + "</td>");
			buf.append("</tr>");
			buf.append("<tr>");
			buf.append("<td colspan='" + n + "'>");
			buf.append("<div id='collapse'" + dto.getGonggo_seq() + " class='panel-collapse'>");
			buf.append("<div class='form-group'>");
			buf.append("<label>내용</label>");
			buf.append("<textarea rows='7' class='form-control' readonly='readonly'>" + dto.getGonggo_content() + "</textarea>");
			buf.append("</div>");
			buf.append("<div>");
			buf.append("<div class ='form-group'>");
			buf.append("<input class='btn btn-primary btn-center' type='button' value='글 삭제' onclick='del(\""
						+ dto.getGonggo_seq() + "\")'>");
			buf.append("</div>");
			buf.append("</div>");
			buf.append("</div>");
			buf.append("</td>");
			buf.append("</tr>");
	
			return buf.toString();
			
	}

	// 리스트 가져가기
	public String getListForm() {
		System.out.println("getListForm으로 들어오긴 합니다.");
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < lists.size(); i++) {
			System.out.println("나가는 lists의 값은 뭔가요?" + lists);
			buf.append(listForm(lists.get(i)));
		}
		return buf.toString();
	}

}
