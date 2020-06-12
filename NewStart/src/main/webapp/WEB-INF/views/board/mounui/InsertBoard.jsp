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
  <form action="./insertBoard.do" method="post">
    <div class="form-group">
     <label for="sel1">Select list (select one):</label>
      <select class="form-control" name="category_seq" id="sel1">
        <c:forEach items="${Fdto}" var="fdto">
	        <option  value="${fdto.category_seq}">${fdto.category_title}</option>
        </c:forEach>
      </select>
      <label for="usr">제목 :</label>
      <input type="text" class="form-control" name="title" id="usr">
    </div>
    <div class="form-group">
      <label for="comment">내용:</label>
      <textarea class="form-control" rows="5" id="comment" name="content"></textarea>
    </div>
      <input type="hidden" class="form-control" name="filechk" value="N">
      <input type="submit" class="form-control">
  </form>
</div>

</body>
</html>