<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물등록 확인</title>
</head>
<body>
	<h1>게시물 등록 완료</h1>
	<a href="/lcomputerstudy/board-list.do">게시판으로 가기</a>
	<script>
	setTimeout(function(){
		window.location.href = "board-list.do";
	},2000);
	</script>
</body>
</html>