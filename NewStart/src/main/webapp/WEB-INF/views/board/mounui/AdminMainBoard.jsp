<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
//다중 삭제
function checkAll(bool,name){
	var chkVals = document.getElementsByName(name);
	for (var i = 0; i < chkVals.length; i++) {
		chkVals[i].checked = bool;
		}
	
}


function multiDelChk(){
	var chks = document.getElementsByName('seq');
	var cntChecked = 0;
	for (var i = 1; i < chks.length; i++) {
		if(chks[i].checked){
			cntChecked ++;
		}
	}
	if(cntChecked>0){
		 document.getElementById('del').submit();
	}else{
		alert("선택된 글이 없습니다.");
		return;
	}
}

function chk(name){
	var chkbool = document.getElementsByName(name);
	//var allcheck = document.getElementsByName('allcheck')[0];
	var cnt = 0;
	for (var i = 1; i < chkbool.length; i++) {
		if(chkbool[i].checked){
			cnt++;
		}else{
			chkbool[0].checked = false;
			return;
		}
	}
	
	if(cnt == chkbool.length-1){
		chkbool[0].checked = true;
	}
}

function searchFilter(){

	var firstDate = document.getElementsByName('firstDate')[0].value;
	var lastDate = document.getElementsByName('lastDate')[0].value;

	//모두 체크면 null , 하나만 선택하면 그 value리턴
	var user_grade = domchk("user_grade");
	var replychk = domchk("replychk");
	var delchk = domchk("delchk");
	
	
 	$.ajax({
		url : "./AdminMBoard.do",
		type : "post",
		traditional : true,
		data : {"user_grade":user_grade, "replychk" : replychk,"delchk":delchk,"firstDate":firstDate,"lastDate":lastDate},
		dataType : "json",
		success: function(data){
			//alert(data);
			//alert(data[0].mounui_seq);
			var html = "";
			var tbody = document.getElementById('boardlist');
			
			if(data.length == 0){
				  html += "<tr>";
				  html += "<td>글이 존재하지 않습니다...</td>";
		     	  html += "</tr>";
			}else{
			
			for (var i = 0; i < data.length; i++) {
			
		    html += "<tr>";
		    html += "<td><input type='checkbox' name='seq' value=\'"+data[i].mounui_seq+"\' onclick='chk(this.name)'></td>";
     		html += "<td>"+(data.length-i)+"</td>";
     		html += "<td>"+data[i].category_title+"</td>";
      		html += "<td><a href='./AdminMBoardDetail.do?seq="+data[i].mounui_seq+"\'>"+data[i].title+"</a></td>";
      		html += "<td>"+data[i].category_seq+"&lt;"+data[i].board_code+"&gt;</td>";
      		html += "<td>"+data[i].regdate+"</td>";
      		html += "<td>";
      		if(data[i].replychk == 'N'){
			html += "답변 처리 중";
      		}else{
			html += "답변 완료";
      		}
      		html += " </td>";
      		html += " <td>";
      		if(data[i].delchk == 'N'){
			html += "존재";
      		}else{
			html += "삭제";
      		}
      		html += "</td>";
    		html += "</tr>";
			}
		}
			
		tbody.innerHTML = html;			
			
		},
		error: function(){
			alert('오류');
		}
	});  
}


function domchk(name){
	var domname = document.getElementsByName(name);
	var idx = null;
	
	if(!domname[0].checked){
		for (var i = 1; i < domname.length; i++) {
			if(domname[i].checked){
				idx = domname[i].value;
			}
		}
	}
	
	return idx;
}

</script>
<body>
	검색 필터 :
	<div id="searchfilter">
		회원 등급 : <input type="checkbox" name="user_grade" value="A" onclick="checkAll(this.checked, this.name)"> 전체
				 <input type="checkbox" name="user_grade" value="M" onclick="chk(this.name)"> 멘티
				 <input type="checkbox" name="user_grade" value="T"	onclick="chk(this.name)"> 강사 <br>
		처리 여부 : <input type="checkbox" name="replychk" value="A" onclick="checkAll(this.checked, this.name)"> 전체
		 <input type="checkbox" name="replychk" value="Y" onclick="chk(this.name)"> 답변 완료
		  <input type="checkbox" name="replychk" value="N" onclick="chk(this.name)"> 답변 대기 <br>
		삭제 여부 : <input type="checkbox" name="delchk" value="A" onclick="checkAll(this.checked, this.name)"> 전체
		 <input type="checkbox" name="delchk" value="N" onclick="chk(this.name)"> 존재
		  <input type="checkbox" name="delchk" value="Y" onclick="chk(this.name)"> 삭제 <br>
		   날짜 검색 : <input type="date" name="firstDate"> ~ <input type="date" name="lastDate"> 
		<button onclick="searchFilter()">검색</button>
	</div>

<div class="container">
<form action="./AdminMBoardDel.do" method="get" id="del">
  <h2>Basic Table</h2>
  <p>The .table class adds basic styling (light padding and only horizontal dividers) to a table:</p>            
  <table class="table">
    <thead>
      <tr>
      	<th><input type="checkbox" name='seq' onclick="checkAll(this.checked,this.name)"><th>
        <th>카테고리</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록일</th>
        <th>답변 처리 여부</th>
        <th>글 삭제 여부</th>
      </tr>
    </thead>
    <tbody id="boardlist">
		<c:forEach items="${dtos}" var="dto" varStatus="idx">
		      <tr>
		      	 <td><input type="checkbox" name="seq" value="${dto.mounui_seq}" onclick="chk(this.name)"></td>
       			 <td>${fn:length(dtos)-idx.index}</td>
       			 <td>${dto.category_title}</td>
        		 <td><a href="./AdminMBoardDetail.do?seq=${dto.mounui_seq}">${dto.title}</a></td>
        		 <td>${dto.category_seq}&lt;${dto.board_code}&gt;</td>
        		 <td>${dto.regdate}</td>
        		 <td>
        		 	<c:choose>
						<c:when test="${dto.replychk eq 'N'}">답변 처리 중</c:when>
						<c:when test="${dto.replychk eq 'Y'}">답변 완료</c:when>
					</c:choose>
        		 </td>
        		 <td>
        		 	<c:choose>
						<c:when test="${dto.delchk eq 'N'}">존재</c:when>
						<c:when test="${dto.delchk eq 'Y'}">삭제</c:when>
					</c:choose>
        		 </td>
      		</tr>
		</c:forEach>
    </tbody>
  </table>
  <button type="button" onclick="multiDelChk()">삭제</button>
  </form>
</div>


</body>
</html>