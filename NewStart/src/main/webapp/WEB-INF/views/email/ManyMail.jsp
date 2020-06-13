<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

function checkAll(bool){
	//alert(name);
	var chkVals = document.getElementsByName('filter');
	//Allfilter
	document.getElementsByName('Allfilter').checked = bool;
	for (var i = 0; i < chkVals.length; i++) {
		chkVals[i].checked = bool;
		}

	if(bool){
	var user_grade = ["'M'","'T'"];
	alert(user_grade);
	chkCnt(user_grade);
	}else{
		document.getElementById('sendCnt').innerHTML = '총 발송 건 수 : 0통';
	}
	
	
}

function chk(){
	var chkbool = document.getElementsByName('filter');


	var user_grade = [];
	var cnt = 0;
	
	for (var i = 0; i < chkbool.length; i++) {
		if(chkbool[i].checked){
			cnt++;
			user_grade.push(chkbool[i].value);
		}else{
			document.getElementsByName('Allfilter')[0].checked = false;
		}
	}
		if(cnt == chkbool.length){
			document.getElementsByName('Allfilter')[0].checked = true;
			//alert(user_grade);
			chkCnt(user_grade);
		}else if(cnt > 0){
			chkCnt(user_grade);
		}else{
			document.getElementById('sendCnt').innerHTML = '총 발송 건 수 : 0통';
		}
	
		
		
		
}

function chkCnt(user_grade){
	var emailList = document.getElementsByName('user_email')[0];
	var sendCnt = document.getElementById('sendCnt');

	$.ajax({
		url : "./getuserEmails.do",
		type : "post",
		traditional : true,
		data : {"user_grade":user_grade},
		dataType : "text",
		success: function(data){
			//alert(data);
			//alert(data.split(',').length);
			emailList.value = data;
			sendCnt.innerHTML = '총 발송 건 수 : '+data.split(',').length+'통';
		},
		error: function(){
			alert('오류');
		}
	}); 
}

function gosubmit(){
	var chkbool = document.getElementsByName('filter');
	var email_title = document.getElementsByName('email_title')[0];
	var email_content = document.getElementsByName('email_content')[0];
	
	var cnt = 0;

	if(email_title.value.trim() == '' || email_content.value.trim() == ''){
		alert('제목 및 내용을 작성해주세요');
		return;
	}
	
	for (var i = 0; i < chkbool.length; i++) {
		if(chkbool[i].checked){
			cnt ++;
		}
	}
	
	if(cnt == 0){
		alert('수신자 필터를 설정해 주세요');
		return;
	}else{
		alert('전송');
		document.forms[0].submit();
	}
	
}


</script>
<body>

<div class="container">
  <form action="./ManyMailSend.do" method="post">
    <div class="form-group">
      <label for="usr">제목:</label>
      <input type="text" class="form-control" id="usr" name="email_title" >
    </div>
    <div class="form-group">
      <label for="pwd">내용:</label>
      <textarea class="form-control" id="pwd" name="email_content" rows="30" cols="50"></textarea>
    </div>
      수신자 필터 :<br>
      회원 등급 :  <input type="checkbox" name="Allfilter" value="'A'" onclick="checkAll(this.checked)"> 전체
			<input type="checkbox"  name="filter" value="'M'" onclick="chk()"> 멘티
 			<input type="checkbox"  name="filter" value="'T'" onclick="chk()"> 강사 <br>

  			<input type="hidden" name ="user_email">
  			<input type="hidden" name ="filechk" value="N">
  <div id="sendCnt">총 발송 건 수 : 0통</div>
      <input type="button" value="발송" onclick="gosubmit()">
  </form>
</div>

</body>
</html>