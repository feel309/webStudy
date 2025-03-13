<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>메인</title>
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f6f7;
            text-align: center;
        }
        .container {
            width: 400px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #2DB400;
        }
        .btn {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        .btn-primary { background-color: #007bff; color: white; }
        .btn-secondary { background-color: #fff; color: #333; border: 1px solid #ddd; }
        .btn-google { background-color: #DB4437; color: white; }
        .btn-naver { background-color: #2DB400; color: white; }
        .btn:hover { opacity: 0.9; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome, ${id}!</h1>
        <input type="hidden" name="userName" value="${id}">
        
        <button id="loginBtn" class="btn btn-primary" type="button" onclick="location.href='/login'">로그인</button>
        <button id="regBtn" class="btn btn-secondary" type="button" onclick="location.href='/register'">회원가입</button>
        <button id="logoutBtn" class="btn btn-primary" type="button" onclick="location.href='/logout'">로그아웃</button>
        
        <button id="googleLoginBtn" class="btn btn-google" type="button" onclick="location.href='/oauth2/authorization/google'">
            Google 로그인
        </button>
        <button id="naverLoginBtn" class="btn btn-naver" type="button" onclick="location.href='/oauth2/authorization/naver'">
            Naver 로그인
        </button>
    </div>
    
    <script>
        var userName = $('input[name=userName]').val();
        if (userName == 'anonymousUser') {
            $('#loginBtn').show();
            $('#regBtn').show();
            $('#logoutBtn').hide();
            $('#googleLoginBtn').show();
            $('#naverLoginBtn').show();
        } else {
            $('#loginBtn').hide();
            $('#regBtn').hide();
            $('#logoutBtn').show();
            $('#googleLoginBtn').hide();
            $('#naverLoginBtn').hide();
        }
    </script>
</body>
</html>
