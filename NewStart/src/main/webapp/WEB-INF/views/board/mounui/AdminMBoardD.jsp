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
function reply(){
	document.domain = 'localhost';
	window.open("./MBoardReply.do?seq=${dto.mounui_seq}&email=${dto.board_code}","답장보내기","width=1000px, height=1000px, left=300");
}

function replyview(){
	var div = document.getElementById('replydiv');
	var btn = document.getElementById('replyview');
	if(div.style.display == 'none'){
		div.style.display = 'block';
		btn.innerHTML = '접기';
	}else{
		div.style.display = 'none';
		btn.innerHTML = '답변 보기';
	}
}

</script>
<body>
<div class="container">
  <div class="form-group">
      <label for="usr">카테고리 :</label>
      <input type="text" class="form-control" name="title" id="usr" value="${dto.category_title}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="usr">작성자 :</label>
      <input type="text" class="form-control" name="title" id="usr" value="${dto.category_seq}&lt;${dto.board_code}&gt;" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="usr">제목 :</label>
      <input type="text" class="form-control" name="title" id="usr" value="${dto.title}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="comment">내용:</label>
      <textarea class="form-control" rows="5" id="comment" name="content" readonly="readonly">${dto.content}</textarea>
    </div>
    <div class="form-group">
      <label for="usr">등록일 :</label>
      <input type="text" class="form-control" name="regdate" id="usr" value="${dto.regdate}" readonly="readonly">
    </div>
      <button type="button" class='btn'  onclick="location.href='./AdminMBoard.do'">목록</button>
      <button type="button" class='btn'  onclick="location.href='./AdminMBoardDel.do?seq=${dto.mounui_seq}'">삭제</button>
      <c:if test="${dto.delchk ne 'Y'}">
	      <c:if test="${dto.replychk eq 'N'}">
	    	  <button type="button" class='btn' id="reply" onclick="reply()">답변보내기</button>
    	  </c:if>
      </c:if>
		<c:if test="${dto.replychk eq 'Y'}">
			<button type="button" class='btn' id='replyview'
				onclick='replyview()'>답변 보기</button>
			
		<div class="container" id="replydiv" style="display: none;">
			<div class="form-group">
				<label for="usr">제목 :</label> <input type="text"
					class="form-control" name="title" id="usr" value="${edto.email_title}"
					readonly="readonly">
			</div>
			<div class="form-group">
				<label for="comment">내용:</label>
				<textarea class="form-control" rows="5" id="comment" name="content"
					readonly="readonly">${edto.email_content}</textarea>
			</div>
			<div class="form-group">
				<label for="usr">발송일 :</label> <input type="text"
					class="form-control" name="regdate" id="usr" value="${edto.regdate}"
					readonly="readonly">
			</div>
		</div>
		</c:if>

	</div>

</body>
</html>