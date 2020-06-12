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

function checkAll(bool,name){
	//alert(name);
	var filter = document.getElementsByName('filter')[0];
	var f = '';
	var chkVals = document.getElementsByName(name);
	for (var i = 0; i < chkVals.length; i++) {
		chkVals[i].checked = bool;
		}
	
	 if(bool){
		if(name=='user_grade'){
			filter.value = 'MT/'+filter.value.split('/')[1];
		}else{
			filter.value = filter.value.split('/')[0]+'/NHL';
		}
	}else{
		if(name=='user_grade'){
			filter.value = 'X/'+filter.value.split('/')[1];
		}else{
			filter.value = filter.value.split('/')[0]+'/X';
		}
	} 
}

function chk(name){
	var chkbool = document.getElementsByName(name);
	var filter = document.getElementsByName('filter')[0];
	var f = "";
	var cnt = 0;
	for (var i = 1; i < chkbool.length; i++) {
		if(chkbool[i].checked){
			if(name == 'user_grade'){
				f = f+chkbool[i].value;
				cnt++;
			}else{
				f = f+chkbool[i].value;
			}
		}else{
			chkbool[0].checked = false;
		}
	}
		if(f == ""){
			f = 'X';
		}
		if(name == 'user_grade'){
			filter.value = f+'/'+filter.value.split('/')[1];
		}else{
			filter.value = filter.value.split('/')[0]+'/'+f;
		}
		
		if(cnt == chkbool.length-1){
			chkbool[0].checked = true;
		}
	
}

function sub(form){
	
	var grade = document.getElementsByName("user_grade");
	var type = document.getElementsByName("user_type");
	
	var user_grade = [];
	var user_type = [];
	
	for (var i = 1; i < grade.length; i++) {
		if(grade[i].checked){
			user_grade.push(grade[i].value);
		}
	}
	for (var i = 1; i < type.length; i++) {
		if(type[i].checked){
			user_type.push(type[i].value);
		}
	}
	
	alert(user_grade);
	alert(user_type);
	

 	$.ajax({
		url : "./getuserEmails.do",
		type : "post",
		traditional : true,
		data : {"user_grade":user_grade,"user_type":user_type},
		dataType : "text",
		success: function(data){
			alert(data);
			document.getElementsByName("user_email")[0].value = data;
			form.submit();
		},
		error: function(){
			alert('오류');
		}
	}); 
	

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
      회원 등급 :  <input type="checkbox" name="user_grade" value="A" onclick="checkAll(this.checked, this.name)"> 전체
			<input type="checkbox"  name="user_grade" value="M" onclick="chk(this.name)"> 멘티
 			<input type="checkbox"  name="user_grade" value="T" onclick="chk(this.name)"> 강사 <br>
     회원 상태 :	<input type="checkbox" name="user_type" value="A" onclick="checkAll(this.checked, this.name)"> 전체
			<input type="checkbox"  name="user_type" value="N" onclick="chk(this.name)"> 일반
 			<input type="checkbox"  name="user_type" value="H" onclick="chk(this.name)"> 휴면	
 			<input type="checkbox"  name="user_type" value="L" onclick="chk(this.name)"> 잠김	 <br>
  			<input type="hidden" name ="user_email">
  			<input type="hidden" name ="filechk" value="N">
  			<input type="hidden" name ="filter" value="X/X">
  <div class="sendCnt">발송 수 : ??명</div>
      <input type="button" value="발송" onclick="sub(this.parentElement)">
  </form>
</div>

</body>
</html>