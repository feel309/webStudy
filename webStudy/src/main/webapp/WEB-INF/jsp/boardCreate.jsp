<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>게시글 등록</title>
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
            width: 350px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .board-container h2 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }
        .input-field {
            width: 100%;
            padding: 12px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }
        .submit-btn {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }
        .cancel-btn {
            width: 100%;
            padding: 12px;
            background-color: #fff;
            border: 1px solid #ddd;
            color: #333;
            font-size: 16px;
            border-radius: 5px;
            margin-top: 10px;
            cursor: pointer;
        }
        .cancel-btn:hover {
            background-color: #f8f9fa;
        }
    </style>
</head>
<body>
    <div class="board-container">
        <h2>게시글 등록</h2>
        <form action="/board/add" method="post">
            <input type="date" name="purchaseDate" class="input-field" placeholder="구입일자" required><br>
            <input type="text" name="lotteryName" class="input-field" placeholder="복권명" required><br>
            <input type="number" name="drawNumber" class="input-field" placeholder="회차" required><br>
            <input type="number" name="quantity" class="input-field" placeholder="수량" required><br>
            <input type="text" name="orderNumber" class="input-field" placeholder="주문번호" required><br>
            <button type="submit" class="submit-btn">등록</button>
            <button type="button" class="cancel-btn" onclick="location.href='/board'">취소</button>
        </form>
    </div>
</body>
</html>