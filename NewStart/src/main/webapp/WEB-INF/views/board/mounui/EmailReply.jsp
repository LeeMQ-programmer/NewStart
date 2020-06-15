<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
<script type="text/javascript" src="./js/mounui.js"></script>
<body>

<div class="container">
	<form action="./MReplySend.do" method="post" id="form">
    <input type="hidden" class="form-control" name="mounui_seq" id="modi" value="${seq}">
  <div class="form-group">
      <label for="usr">수신자 :</label>
      <input type="text" class="form-control" name="user_email" id="usr" value="${email}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="usr">제목 :</label>
      <input type="text" class="form-control" name="title" id="usr">
    </div>
    <div class="form-group">
      <label for="comment">내용:</label>
      <textarea  class="form-control" rows="5" id="comment" name="content" style="resize: none"></textarea>
    </div>

      <input type="hidden" class="form-control" name="filechk" value="N">
      </form>
	      <button type="button" class='btn' id="reply" onclick="sendreply()">답변보내기</button>
	      <button type="button" class='btn' id="cancel" onclick="cancel()">취소</button>
</div>


</body>
</html>