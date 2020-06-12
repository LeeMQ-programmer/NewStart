package com.start.pro.comm;

import java.util.List;

import com.start.pro.dto.DTO_Review;
import com.start.pro.dto.DTO_User;

public class inputList {

	private List<DTO_Review> lists;
	private DTO_User mem;

	public void setLists(List<DTO_Review> lists) {
		this.lists = lists;
	}

	public void setMem(DTO_User mem) {
		this.mem = mem;
	}

	//날짜
	private String dateFormat(String date) {
		return date.substring(0,date.indexOf(" "));
	}
	
//	이미지
	private String titleFormat(int teacher,String reply) {
		StringBuffer sb = new StringBuffer();
		
		if (teacher==0 && reply.equalsIgnoreCase("Y")) {
			sb.append("&nbsp;&nbsp;&nbsp;");
			sb.append("<img alt='리플' src='./img/reply.png'/>");
		}
		return sb.toString();
	}
//	출력 리스트 폼
	private String listForm(DTO_Review dto) {
		StringBuffer sb = new StringBuffer();
//		colspan의 기본 설정, user은 6, admin은 7 이다.
		int n =6;
	sb.append("	 <tr>   "   );
    sb.append("   <td><input type='checkbox' name='chkVal' value='"+dto.getRe_seq()+"'></td> " );
    sb.append("  <td>"+dto.getRe_seq()+"</td> " );
    sb.append("   <td>     " );
    sb.append("  <div class='panel-heading'> " );
    sb.append("   <a data-toggle='collapse' data-parent='#accordion' href='#collapse"+dto.getRe_seq()+
    		"' onclick='collapse(\""+dto.getRe_seq()+"\")'>"+titleFormat(dto.getRe_teacher(),dto.getRe_reply())+dto.getRe_title()+"</a>");
    sb.append("  </div>  " );
    sb.append(" </td>   " );
    sb.append(" <td>"+dto.getUser_seq()+"</td>  " );
    if (mem.getUser_grade().equalsIgnoreCase("A")) {
    	n = 7;
    	sb.append("<td>"+dto.getRe_delete()+"</td>" );
	}
    sb.append("     <td>"+dateFormat(dto.getRe_regdate())+"</td>  " );
    sb.append("  </tr>   " );
    sb.append("  <tr>     " );
    sb.append("     <td colspan='"+n+"'>        " );
    sb.append("        <div id='collapse"+dto.getRe_seq()+"' class='panel-collapse collapse'>   " );
    sb.append("           <div class='form-group'>      " );
    sb.append("  <label>내용</label><br>          " );
    sb.append("  <textarea rows='"+n+"' class='form-control' readonly='readonly'>"+dto.getRe_content()+"</textarea>      " );
    sb.append("  </div>   " );

    sb.append("	<div>");
    sb.append("		<div class='form-group'>");
    if (mem.getUser_seq().equalsIgnoreCase(String.valueOf(dto.getUser_seq()))) {
    	sb.append("<input class='btn btn-primary btn-sm btn-center' type='button' value='글 수정' onclick='modify(\""+dto.getRe_seq()+"\")'>");
	}
    if (mem.getUser_seq().equalsIgnoreCase(String.valueOf(dto.getUser_seq()))||mem.getUser_grade().equalsIgnoreCase("A")) {
    	sb.append("<input class='btn btn-primary btn-sm btn-center' type='button' value='글 삭제' onclick='del(\""+dto.getRe_seq()+"\")'>");
	}
    //현재 사용자가 강사이며 해당 글의 대상 강사이면 답글 작성 가능
    if (mem.getUser_grade().equalsIgnoreCase("T")&&mem.getUser_seq().equalsIgnoreCase(String.valueOf(dto.getRe_teacher()))) {
    	sb.append("<input class='btn btn-primary btn-sm btn-center' type='button' value='답글' onclick='reply(\""+dto.getRe_seq()+"\")'>");
	}
    sb.append("			 	  </div>");
    sb.append("			  </div>");
    
    sb.append("        </div>                                                                                                        " );
    sb.append("     </td>                                                                                                            " );
    sb.append("  </tr>                                                                                                               " );
		
		return sb.toString();
	}
	
	public String getListForm() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i <lists.size(); i++) {
			sb.append(listForm(lists.get(i)));
		}
		return sb.toString();
	}
	
	
}







