<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>로또 번호 자동 생성</title>
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
        .lotto-container {
            background-color: white;
            padding: 40px;
            width: 350px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .lotto-container h2 {
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }
        .generate-btn {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }
        .generate-btn:hover {
            background-color: #0056b3;
        }
        .lotto-numbers {
            margin-top: 20px;
            font-size: 18px;
            color: #333;
            font-weight: bold;
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
    <div class="lotto-container">
        <h2>로또 번호 자동 생성</h2>
        <button class="generate-btn" onclick="location.href='/board/lotto/generate'">로또 번호 받기</button>
        <p class="lotto-numbers">생성된 번호: ${lottoNumbers}</p>
        <button type="button" class="cancel-btn" onclick="location.href='/board'">게시판으로 돌아가기</button>
    </div>
</body>
</html>
