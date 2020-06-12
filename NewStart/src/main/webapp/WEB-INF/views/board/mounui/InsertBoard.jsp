<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <form>
    <div class="form-group">
     <label for="sel1">Select list (select one):</label>
      <select class="form-control" id="sel1">
        <option value="0">1</option>
        <option value="0">2</option>
        <option value="0">3</option>
        <option value="0">4</option>
      </select>
      <label for="usr">Name:</label>
      <input type="text" class="form-control" id="usr">
    </div>
    <div class="form-group">
      <label for="pwd">Password:</label>
      <input type="password" class="form-control" id="pwd">
    </div>
    <div class="form-group">
      <label for="comment">Comment:</label>
      <textarea class="form-control" rows="5" id="comment"></textarea>
    </div>
  </form>
</div>

</body>
</html>