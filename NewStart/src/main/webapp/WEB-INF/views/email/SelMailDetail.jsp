<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>

<div class="container">
  <h2>Form control: input</h2>
  <p>The form below contains two input elements; one of type text and one of type password:</p>
    <div class="form-group">
      <label for="usr">수신자 :</label>
      <input type="text" class="form-control" id="usr" name="email_title" readonly="readonly" 
      	value="${dto.user_email}">
    </div>
    <div class="form-group">
      <label for="usr">제목:</label>
      <input type="text" class="form-control" id="usr" name="email_title" readonly="readonly" value="${dto.email_title}">
    </div>
    <div class="form-group">
      <label for="pwd">내용:</label>
      <textarea class="form-control" id="pwd" name="email_content" readonly="readonly" rows="30" cols="50">${dto.email_content}</textarea>

<c:if test="${dto.successchk eq 'F'}">
	<button onclick="location.href='./resend.do?seq=${dto.email_seq}'">재전송</button>
</c:if>
    </div>
</div>

</body>
</html>