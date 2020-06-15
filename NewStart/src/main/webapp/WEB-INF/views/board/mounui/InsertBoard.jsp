<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function chkchk(){
	var title = document.getElementById('title').value;
	var comment = document.getElementById('comment').value;
	if(title.trim() == '' || comment.trim() == ''){
		alert('제목과 내용을 모두 입력해주세요');
	}else{
		alert('문의글이 등록되었습니다.');
		document.forms[0].submit();
	}
	
}

</script>
<body>
<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>

<div class="container">
  <form action="./UinsertBoard.do" method="post">
    <div class="form-group">
     <label for="sel1">Select list (select one):</label>
      <select class="form-control" name="category_seq" id="sel1">
        <c:forEach items="${Fdto}" var="fdto">
	        <option  value="${fdto.category_seq}">${fdto.category_title}</option>
        </c:forEach>
      </select>
      <label for="usr">제목 :</label>
      <input type="text" class="form-control" name="title" id="title">
    </div>
    <div class="form-group">
      <label for="comment">내용:</label>
      <textarea class="form-control" rows="5" id="comment" name="content" style="resize: none"></textarea>
    </div>
      <input type="hidden" class="form-control" name="filechk" value="N">
      <button type="button" class="form-control" onclick="chkchk()">등록</button>
  </form>
</div>

</body>
</html>