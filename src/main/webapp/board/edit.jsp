<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
</head>
<body>
	<h2>게시물 수정</h2>
		<form action="board-update.do" name="board" method="post">
		<input type="hidden" name="b_idx" value="${b_edit.b_idx }">
		<input type="hidden" name="u_idx" value="${b_edit.u_idx }">
			<label for="title">제목</label>
			<input type="text" name="edit_title" value="${b_edit.b_title }" required><br>
			<label for="content">내용</label>
			<textarea name="edit_content" required>${b_edit.b_content }</textarea><br>
			<input type="submit" value="수정완료">
		</form>
</body>
</html>