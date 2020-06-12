<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="//cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
<script type="text/javascript" src="./js/boardListGonggo.js"></script>

<body>
	<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>
<div id="container">
	<form action="./boardInsert.do" method="post">
		<b>ID</b><input class="ipt" type="text" name="user_seq" id="user_seq" required="required">
		<b>Title</b><input class="ipt" type="text" name="gonggo_title" id="gonggo_title" required="required">
		<b>COST</b><input class="ipt" type="text" name="gonggo_cost" id="gonggo_cost" required="required">
		<br>
		<br>
		<br>
		<textarea id="bo_content" name="gonggo_content" rows="30" cols="30"></textarea><br>
		<input type="button" value="뒤로가기" onclick="javascript:history.back(-1)">
		<input type="submit">
	</form>
</div>
	<script type="text/javascript">
		$(function(){
			CKEDITOR.replace('bo_content',{
				filebrowserUploadUrl: './adm/fileupload.do'
			});
		});
	</script>
</body>
</html>