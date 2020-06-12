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

function cancel(){
	if(confirm('작성 중이던 문서가 사라집니다. 그래도 취소하시겠습니까?')){
		window.close();
	}
}

function reply(){
	document.domain = 'localhost';
	opener.name='boarddetail';
	var f = document.getElementById('form');
	var mounui_seq = document.getElementsByName('mounui_seq')[0].value;
	var user_email = document.getElementsByName('user_email')[0].value;
	var title = document.getElementsByName('title')[0].value;
	var content = document.getElementsByName('content')[0].value;
	var filechk = document.getElementsByName('filechk')[0].value;
	//opener.document.location.href='';
	//f.target = opener.name;
	//f.submit();
	$.ajax({
		url : "./MReplySend.do",
		type : "post",
		traditional : true,
		data : {"mounui_seq":mounui_seq, "user_email" : user_email,"title":title,"content":content,"filechk":filechk},
		dataType : "text",
		success: function(data){
			if(data == 'true'){
				alert('메일이 발송되었습니다!');
				opener.document.location.href='./AdminMBoardDetail.do?seq='+mounui_seq;
				self.close();
			}else{
				alert('메일 발송이 실패되었습니다.');
			}
		},
		error : function(){
			alert('오류');
			}
	});
		
	
}

</script>
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
      <textarea class="form-control" rows="5" id="comment" name="content"></textarea>
    </div>

      <input type="hidden" class="form-control" name="filechk" value="N">
      </form>
	      <button type="button" class='btn' id="reply" onclick="reply()">답변보내기</button>
	      <button type="button" class='btn' id="cancel" onclick="cancel()">취소</button>

</div>

</body>
</html>