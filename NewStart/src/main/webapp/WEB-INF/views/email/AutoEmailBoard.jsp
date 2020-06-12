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
  <h2>Table</h2>
  <p>The .table-responsive class creates a responsive table which will scroll horizontally on small devices (under 768px). When viewing on anything larger than 768px wide, there is no difference:</p>                                                                                      
  <div class="table-responsive">          
  <table class="table">
    <thead>
      <tr>
        <th>#</th>
        <th>제목</th>
        <th>사용 여부</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="dto" items="${dtos}">
      <tr>
        <td>${dto.category_code}</td>
        <td><a href="./AutomailBD.do?seq=${dto.category_code}">${dto.email_title}</a></td>
        <td>${dto.use_chk}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  </div>
</div>
</body>
</html>