<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 등록</title>
</head>
<body>
	<h2>게시물 작성</h2>
	<form action="board-insert-process.do" name="board" method="post">
		<label for="title">제목</label>
		<input type="text" name="title" required><br>
		<label for="content">내용</label>
		<textarea name="content" required></textarea><br>
		<!-- <label for="writer_idx">작성자idx</label> 로그인하여 사용자세션을 불러오면 필요없는 부분 
		<input type="number" name="writer_idx" required><br> -->
		<input type="submit" value="게시물 등록">
	</form>
	<br>
	<form action="board-list.do" method="get">
		<button type="submit">목록으로</button>
	</form>
</body>
</html>