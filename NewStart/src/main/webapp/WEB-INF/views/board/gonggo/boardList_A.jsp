
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첫번째 메인 화면</title>
<link type="text/css" rel="stylesheet" href="./css/boardList.css">
</head>
<script type="text/javascript" src="./js/boardListGonggo.js"></script>
<body>
	<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>


	<div id="container">
		<div id="select">
			<span> <select class="btn btn-primary" id="list" name="list"
				onchange="pageList()">
					<option value="5">5</option>
					<option value="10">10</option>
					<option value="15">15</option>
					<option value="20">20</option>
			</select>
			</span>
		</div>
		<br>
		<form action="#" method="post" id="frm" name="frm"
			onsubmit="return chkbox();">
			<div class="panel-group" id="accordion">
				<table class="table table-bordered">
					<tr>
						<th><input type="checkbox" onclick="checkAll(this.checked)">
						</th>
						<th>글번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>파일유무</th>
						<c:if test="${fn:contains(lists, user=A)}">
							<th>삭제 여부</th>
						</c:if>
					</tr>
					<jsp:useBean id="format" class="com.start.pro.models.gonggo.inputList"
						scope="page" />
					<jsp:setProperty property="lists" name="format" value="${lists}" />
					<jsp:setProperty property="users" name="format" value="${users}" />
					<jsp:getProperty property="listForm" name="format" />


				</table>
			</div>
		</form>
	</div>
			

</body>
</html>