<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
</head>
<body>
	<h2>게시물 작성</h2>
		<form action="board-update.do" name="board" method="post">
			<label for="title">제목</label>
			<input type="text" name="title" required><br>
			<label for="content">내용</label>
			<textarea name="content" required></textarea><br>
			<input type="submit" value="수정완료">
		</form>
</body>
</html>