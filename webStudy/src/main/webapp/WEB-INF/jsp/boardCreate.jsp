<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>게시글 등록 화면</title>
</head>
<body>
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