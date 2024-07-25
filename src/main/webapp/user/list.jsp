<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록2</title>
</head>
<style>
	h1 {
		text-align:center;
	}
	table {
		border-collapse:collapse;
		margin:40px auto;
	}
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
<h1>회원 목록</h1>
	<table>
		<tr>
			<td colspan="3">전체 회원 수 : ${usercount }</td>
		</tr>
		<tr>
			<th>No</th>
			<th>ID</th>
			<th>이름</th>
		</tr>
		<c:forEach items="${list}" var="item">
			<tr>
				<td><a href="/lcomputerstudy/user-detail.do?u_idx=${item.u_idx}">${item.u_idx}</a></td>
				<td>${item.u_id}</td>
				<td>${item.u_name}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>