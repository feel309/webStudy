<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>로또 게시판 화면</title>
</head>
<body>
<h1>로또 게시판</h1>

<form method="get" action="/board">
    <input type="text" name="lotteryName" placeholder="복권명" value="${param.lotteryName}">
    <input type="date" name="startDate" value="${param.startDate}">
    <input type="date" name="endDate" value="${param.endDate}">
    <button type="submit">검색</button>
</form>


<table border="1">
    <tr>
        <th>구입일자</th>
        <th>복권명</th>
        <th>회차</th>
        <th>수량</th>
        <th>주문번호</th>
    </tr>
    <c:forEach var="board" items="${boardList}">
        <tr>
            <td>${board.purchase_date}</td>
            <td>${board.lottery_name}</td>
            <td>${board.draw_number}</td>
            <td>${board.quantity}</td>
            <td>${board.order_number}</td>
        </tr>
    </c:forEach>
</table>

<!-- 페이징 -->
<div>
	<c:forEach begin="1" end="${totalPages}" var="i">
	    <a href="?page=${i}&lotteryName=${param.lotteryName}&startDate=${param.startDate}&endDate=${param.endDate}">${i}</a>
	</c:forEach>
</div>


<h2>게시글 등록</h2>
<form action="/board/add" method="post">
    <label>구입일자: <input type="date" name="purchaseDate" required></label><br>
    <label>복권명: <input type="text" name="lotteryName" required></label><br>
    <label>회차: <input type="number" name="drawNumber" required></label><br>
    <label>수량: <input type="number" name="quantity" required></label><br>
    <label>주문번호: <input type="text" name="orderNumber" required></label><br>
    <button type="submit">등록</button>
</form>
</body>
</html>