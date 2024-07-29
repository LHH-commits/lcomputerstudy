<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 실패</title>
</head>
<body>
<h1>로그인 실패</h1>
<a href="user-login.do">다시 로그인 하기</a>
<script>
setTimeout(function (){
	window.location.href = "user-login.do";
},2000);
</script>
</body>
</html>