<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 상세페이지 VMC test</title>
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
		border:none;
		cursor:pointer;
		padding:10px;
		display:inline-block;
	}
</style>
<body>
	<h1>회원 상세페이지</h1>
	<table>
		<tr>
			<td>회원 번호</td>
			<td><%=u_idx %></td>
		</tr>
		<tr>
			<td>회원 ID</td>
			<td><%=u_id %></td>
		</tr>
		<tr>
			<td>회원 이름</td>
			<td><%=u_name %></td>
		</tr>
		<tr>
			<td>회원 전화번호</td>
			<td><%=u_tel %></td>
		</tr>
		<tr>
			<td>회원 나이</td>
			<td><%=u_age %></td>
		</tr>
	</table>
</body>
</html>