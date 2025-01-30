<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>게시판상세 화면</title>
</head>
<body>
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

</body>
</html>