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
<script type="text/javascript">
//다중 삭제
function checkAll(bool){
	
	var chks = document.getElementsByName('seq');

	for (var i = 0; i < chks.length; i++) {
		chks[i].checked = bool;
	}
}


function multiDelChk(){
	var chks = document.getElementsByName('seq');
	var cntChecked = 0;
	for (var i = 0; i < chks.length; i++) {
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

function chk(){
	var chkbool = document.getElementsByName('seq');
	var allcheck = document.getElementsByName('allcheck')[0];
	var cnt = 0;
	for (var i = 0; i < chkbool.length; i++) {
		if(chkbool[i].checked){
			cnt++;
		}
	}
	
	if(cnt == chkbool.length){
		allcheck.checked = true;
	}else{
		allcheck.checked = false;
	}
	
}
</script>
<body>


<div class="container">
<form action="./UserMBoardDel.do" method="post" id="del">
  <h2>Basic Table</h2>
  <p>The .table class adds basic styling (light padding and only horizontal dividers) to a table:</p>            
  <table class="table">
    <thead>
      <tr>
      	<th><input type="checkbox" name='allcheck' onclick="checkAll(this.checked)"><th>
        <th>카테고리</th>
        <th>제목</th>
        <th>등록일</th>
        <th>답변 처리 여부</th>
      </tr>
    </thead>
    <tbody>
		<c:forEach items="${dtos}" var="dto" varStatus="idx">
		      <tr>
		      	 <td><input type="checkbox" name="seq" value="${dto.mounui_seq}" onclick="chk()"></td>
       			 <td>${fn:length(dtos)-idx.index}</td>
       			 <td>${dto.category_title}</td>
        		 <td><a href="./UserMBoardDetail.do?seq=${dto.mounui_seq}">${dto.title}</a></td>
        		 <td>${dto.regdate}</td>
        		 <td>
        		 	<c:choose>
						<c:when test="${dto.replychk eq 'N'}">답변 처리 중</c:when>
						<c:when test="${dto.replychk eq 'Y'}">답변 완료</c:when>
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