<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>게시판 상세</title>
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f6f7;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .board-container {
            background-color: white;
            padding: 40px;
            width: 500px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .board-container h1 {
            font-size: 24px;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f8f9fa;
        }
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }
        .comment-btn {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
        }
        .comment-list {
            text-align: left;
            margin-top: 20px;
        }
        .comment-item {
            background-color: #f8f9fa;
            padding: 10px;
            margin-top: 10px;
            border-radius: 5px;
        }
        .back-btn {
            display: block;
            margin-top: 20px;
            padding: 10px;
            background-color: #6c757d;
            color: white;
            text-align: center;
            border-radius: 5px;
            text-decoration: none;
        }
        .back-btn:hover {
            background-color: #5a6268;
        }
    </style>
</head>
<body>
    <div class="board-container">
        <h1>게시글 상세</h1>
        <table>
            <tr><th>구입일자</th><td>${board.purchase_date}</td></tr>
            <tr><th>복권명</th><td>${board.lottery_name}</td></tr>
            <tr><th>회차</th><td>${board.draw_number}</td></tr>
            <tr><th>수량</th><td>${board.quantity}</td></tr>
            <tr><th>로또번호</th><td>${board.order_number}</td></tr>
			<tr><th>첨부파일</th>
			    <td>
			        <c:if test="${not empty board.file_path}">
			            <img src="/uploads/image?filename=${board.file_path.substring(board.file_path.lastIndexOf('/')+1)}" width="200">
			        </c:if>
			    </td>
			</tr>
        </table>
        <h2>댓글</h2>
        <form method="post" action="/board/comment">
            <input type="hidden" name="boardId" value="${id}">
            <textarea name="content" placeholder="댓글을 입력하세요"></textarea>
            <button type="submit" class="comment-btn">댓글 작성</button>
        </form>
        <div class="comment-list">
            <c:forEach var="comment" items="${comments}">
                <div class="comment-item">
                    ${comment.content} (작성일: ${comment.created_at})
                    <form method="post" action="/board/comment">
                        <input type="hidden" name="boardId" value="${id}">
                        <input type="hidden" name="parentId" value="${comment.id}">
                        <textarea name="content" placeholder="대댓글 입력"></textarea>
                        <button type="submit" class="comment-btn">답글</button>
                    </form>
                </div>
            </c:forEach>
        </div>
        <a href="/board" class="back-btn">목록으로</a>
    </div>
</body>
</html>