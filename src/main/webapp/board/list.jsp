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
	/*h1 {
		text-align:center;
	}*/
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
	ul {
		width:600px;
		height:50px;
		margin:10px auto;
	}
	li {
		list-style:none;
		width:50px;
		line-height:50px;
		border:1px solid #ededed;
		float:left;
		text-align:center;
		margin:0 5px;
		border-radius:5px;
	}
</style>
<body>
<h1><a href="board-list.do">게시물 목록</a></h1>
	<form action="board-list.do" method="get">
		<select name="searchOption">
			<option value="b_title" ${searchOption == 'b_title' ? 'selected' : '' }>제목</option>
			<option value="b_title_content" ${searchOption == 'b_title_content' ? 'selected' : '' }>제목+내용</option>
			<option value="b_writer" ${searchOption == 'b_writer' ? 'selected' : '' }>작성자</option>
		</select>
		<input type="text" name="searchKeyword" placeholder="검색어를 입력하세요" value="${searchKeyword }">
		<input type="submit" value="검색">
	</form>
	<table>
		<tr>
			<td colspan="5">전체 게시글 수 : ${b_pagination.count }</td>
		</tr>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성일</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${b_list}" var="board" varStatus="status">
			<tr>
				<td>${board.b_idx }</td>
				<td><a href="/lcomputerstudy/board-detail.do?b_idx=${board.b_idx }">${board.b_title }</a></td>
				<td>${board.b_date }</td>
				<td>${board.b_writer}</td>
				<td>${board.b_views }</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<ul>
			<c:choose>
				<c:when test="${b_pagination.prevPage ge 1 }">
					<li>
						<a href="board-list.do?page=${b_pagination.prevPage }">
							◀
						</a>
					</li>
				</c:when>
			</c:choose>
			<c:forEach var="i" begin="${b_pagination.startPage }" end="${b_pagination.endPage }" step="1">
				<c:choose>
					<c:when test="${b_pagination.page eq i }">
						<li style="background-color:#ededed;">
							<span>${i}</span>
						</li>
					</c:when>
					<c:when test="${b_pagination.page ne i }">
						<li>
							<a href="board-list.do?page=${i }">${i }</a>
						</li>
					</c:when>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${b_pagination.nextPage le b_pagination.lastPage }">
					<li style="">
						<a href="board-list.do?page=${b_pagination.nextPage }">▶</a>
					</li>
				</c:when>
			</c:choose>
		</ul>
	</div>
	<a href="board-insert.do">새 글 작성</a>
</body>
</html>