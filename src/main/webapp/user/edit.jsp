<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>
</head>
<body>
	<h1>회원 정보 수정</h1>
	<form action="/lcomputerstudy/user-update.do" name="user" method="post">
			<input type="hidden" name="u_idx" value="${edit.u_idx }">
			<p> 아이디 : <input type="text" name="edit_id" value="${edit.u_id }"></p>
			<p> 비밀번호 : <input type="password" name="edit_password" value="${edit.u_pw }"></p>
			<p> 이름 : <input type="text" name="edit_name" value="${edit.u_name }">
			<p> 연락처 : <input type="text" maxlength="4" size="4" name="edit_tel1" value="${fn:split(edit.u_tel, '-')[0]}" >
					<input type="text" maxlength="4" size="4" name="edit_tel2" value="${fn:split(edit.u_tel, '-')[1]}">
					<input type="text" maxlength="4" size="4" name="edit_tel3" value="${fn:split(edit.u_tel, '-')[2]}">
			</p>
			<p> 나이 : <input type="text" name="edit_age" value="${edit.u_age }"></p>
			<p> <input type="submit" value="수정완료"></p>
			
	</form>
</body>
</html>