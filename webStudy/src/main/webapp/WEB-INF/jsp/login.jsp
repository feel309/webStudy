<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
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
        .login-container {
            background-color: white;
            padding: 40px;
            width: 350px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .login-container h2 {
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
        .login-btn {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }
        .register-btn {
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
        .error-message {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>로그인</h2>
        <form action="/loginProc" method="post">
            <input type="text" name="username" class="input-field" placeholder="아이디" required><br>
            <input type="password" name="password" class="input-field" placeholder="비밀번호" required><br>
            <button type="submit" class="login-btn">로그인</button>
            <button type="button" class="register-btn" onclick="location.href='/register'">회원가입</button>
        </form>
        <p class="error-message">${error}</p>
    </div>
</body>
</html>
