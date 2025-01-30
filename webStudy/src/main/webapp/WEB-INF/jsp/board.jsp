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
    <button id="create" type="button" onclick="location.href='/board/create'">게시글 등록</button>
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
            <td><a href="/board/detail/${board.id}">${board.lottery_name}</a></td>
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

</body>
</html>