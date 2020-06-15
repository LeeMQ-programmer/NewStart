<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="./js/mounui.js"></script>
<script type="text/javascript">

function udel(){
	if(confirm('정말로 삭제하시겠습니까?')){
	location.href='./UserMBoardDel.do?seq=${dto.mounui_seq}';
	}
}
</script>
<body>

<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>
<div class="container">
  <div class="form-group">
      <label for="usr">카테고리 :</label>
      <input type="text" class="form-control" name="title" id="usr" value="${dto.category_title}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="usr">제목 :</label>
      <input type="text" class="form-control" name="title" id="usr" value="${dto.title}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="comment">내용:</label>
      <textarea class="form-control" rows="5" id="comment" name="content" readonly="readonly" style="resize: none">${dto.content}</textarea>
    </div>
    <div class="form-group">
      <label for="usr">등록일 :</label>
      <input type="text" class="form-control" name="regdate" id="usr" value="${dto.regdate}" readonly="readonly">
    </div>
      <input type="hidden" class="form-control" name="filechk" value="N">
      <button type="button" class='btn'  onclick="location.href='./UserMBoard.do'">목록</button>
      <button type="button" class='btn' id="del" onclick="udel()">삭제</button>
</div>

</body>
</html>