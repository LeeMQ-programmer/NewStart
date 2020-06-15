<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="./js/email.js"></script>
<body>
<%@include file="/WEB-INF/views/boardTopMenu.jsp"%>
<div class="container">
  <h2>Form control: input</h2>
  <p>The form below contains two input elements; one of type text and one of type password:</p>
  <form action="./AutomailUp.do" method="post">
	<input type="hidden" name="category_code" value="${dto.category_code}">
    <div class="form-group">
      <label for="usr">제목:</label>
      <input type="text" class="form-control" id="title" name="email_title" value="${dto.email_title}" readonly="readonly">
    </div>
    <div class="form-group">
      <label for="pwd">내용:</label>
      <textarea class="form-control" id="content" name="email_content" rows="30" cols="50" readonly="readonly">${dto.email_content}</textarea>
    </div>
      <input type="hidden"  name="filechk" value="${dto.filechk}">
      사용 여부 : <input type="radio" id="chk" name="use_chk" value="Y"> 사용
      		<input type="radio" name="use_chk" value="N"> 비사용
  </form>
  	<div>
	<button class='btn' type="button" name='board' onclick='board()' >목록</button>
	<button class='btn' type="button" name='modify' onclick="modify()">수정</button>
	<button class='btn' type="button" name='gosubmit' onclick="gosubmit()" style='display: none;'>저장</button>
	</div>
</div>

<script type="text/javascript">
	//alert('${dto.filechk}');
	var radio = document.getElementsByName("use_chk");
	//alert(radio.length);
	for (var i = 0; i < radio.length; i++) {
		if(radio[i].value == '${dto.use_chk}'){
			radio[i].setAttribute('checked', 'checked');
		}
	}
	
</script>
</body>
</html>