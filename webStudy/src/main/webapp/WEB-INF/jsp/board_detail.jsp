<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>게시판상세 화면</title>
</head>
<body>

<h1>게시글 상세</h1>

<table border="1">
    <tr>
        <th>구입일자</th>
        <td>${board.purchase_date}</td>
    </tr>
    <tr>
        <th>복권명</th>
        <td>${board.lottery_name}</td>
    </tr>
    <tr>
        <th>회차</th>
        <td>${board.draw_number}</td>
    </tr>
    <tr>
        <th>수량</th>
        <td>${board.quantity}</td>
    </tr>
    <tr>
        <th>주문번호</th>
        <td>${board.order_number}</td>
    </tr>
</table>

<h2>댓글</h2>
<form method="post" action="/board/comment">
    <input type="hidden" name="boardId" value="${id}">
    <textarea name="content" placeholder="댓글을 입력하세요"></textarea>
    <button type="submit">댓글 작성</button>
</form>

<ul>
    <c:forEach var="comment" items="${comments}">
        <li>
            ${comment.content} (작성일: ${comment.created_at})
            <form method="post" action="/board/comment">
                <input type="hidden" name="boardId" value="${id}">
                <input type="hidden" name="parentId" value="${comment.id}">
                <textarea name="content" placeholder="대댓글 입력"></textarea>
                <button type="submit">답글</button>
            </form>
        </li>
    </c:forEach>
</ul>

<a href="/board">목록으로</a>
</body>
</html>