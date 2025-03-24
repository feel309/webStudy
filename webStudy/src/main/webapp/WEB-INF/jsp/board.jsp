<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>로또 게시판</title>
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
            width: 80%;
            max-width: 800px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        h1 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }
        .search-form input {
            width: calc(33% - 10px);
            padding: 12px;
            margin: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }
        .search-form button, .action-btn {
            padding: 12px;
            background-color: #007bff;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
            margin: 5px;
        }
        .action-btn {
            background-color: #A9A9A9;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f8f9fa;
            font-weight: bold;
        }
        .pagination a {
            margin: 5px;
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }
        .pagination a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="board-container">
        <h1>로또 게시판</h1>
        <form method="get" action="/board" class="search-form">
            <input type="text" name="lotteryName" placeholder="복권명" value="${param.lotteryName}">
            <input type="date" name="startDate" value="${param.startDate}">
            <input type="date" name="endDate" value="${param.endDate}">
            <button type="submit">검색</button>
            <button type="button" class="action-btn" onclick="location.href='/board/create'">게시글 등록</button>
        </form>

        <table>
            <tr>
                <th>구입일자</th>
                <th>복권명</th>
                <th>회차</th>
                <th>수량</th>
                <th>로또번호</th>
                <th>첨부파일유무</th>
            </tr>
            <c:forEach var="board" items="${boardList}">
                <tr>
                    <td>${board.purchase_date}</td>
                    <td><a href="/board/detail/${board.id}">${board.lottery_name}</a></td>
                    <td>${board.draw_number}</td>
                    <td>${board.quantity}</td>
                    <td>${board.order_number}</td>
                    <c:if test="${not empty board.file_path}">
                    	<td>O</td>
                    </c:if>
                    <c:if test="${empty board.file_path}">
                    	<td>X</td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>

        <!-- 페이징 -->
        <div class="pagination">
            <c:forEach begin="1" end="${totalPages}" var="i">
                <a href="?page=${i}&lotteryName=${param.lotteryName}&startDate=${param.startDate}&endDate=${param.endDate}">${i}</a>
            </c:forEach>
        </div>

        <!-- 로또번호 자동생성 화면이동 -->
        <button type="button" class="action-btn" onclick="location.href='/board/lotto/generate/page'">로또번호 자동생성 화면</button>
    </div>
</body>
</html>