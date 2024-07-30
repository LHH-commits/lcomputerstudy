<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 확인</title>
</head>
<body>
	<h1>게시물 상세보기</h1>
    <c:choose>
        <c:when test="${not empty detail}">
            <p><strong>제목:</strong> ${detail.b_title}</p>
            <p><strong>내용:</strong> ${detail.b_content}</p>
            <p><strong>작성 일시:</strong> ${detail.b_date}</p>
            <a href="board-list.do">목록으로</a>
        </c:when>
        <c:otherwise>
            <p>게시물이 존재하지 않습니다.</p>
            <a href="board-list.do">목록으로</a>
        </c:otherwise>
    </c:choose>
</body>
</html>