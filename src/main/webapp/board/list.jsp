<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>
<style>
	table {
		border-collapse:collapse;
	}
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
	}
</style>
<body>
<h1>게시물 목록</h1>
	<table>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
		<c:forEach items="${b_list}" var="board">
			<tr>
				<td>${board.b_idx }</td>
				<td>${board.b_title }</td>
				<td>${board.b_date }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>