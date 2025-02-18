<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
</head>
<body>
    <h2>로그인</h2>
    <form action="/loginProc" method="post">
        <input type="text" name="username" placeholder="아이디" required><br>
        <input type="password" name="password" placeholder="비밀번호" required><br>
        <button type="submit">로그인</button>
        <button type="button" onclick="location.href='/register'">회원가입</button>
    </form>
    <p>${error}</p>
</body>
</html>