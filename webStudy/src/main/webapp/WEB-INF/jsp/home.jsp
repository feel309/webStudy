<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>초기화면</title>
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
    <input type="hidden" name="userName" value="${id}">
    <h2>초기화면</h2>
    <button id="loginBtn" type="button" onclick="location.href='/login'">로그인</button>
    <button id="regBtn" type="button" onclick="location.href='/register'">회원가입</button>
    <br/>
    <h1>Welcome, ${id}!</h1>
    <button id="logoutBtn" type="button" onclick="location.href='/logout'">로그아웃</button>

    <!-- Google 로그인 버튼 -->
    <button id="googleLoginBtn" type="button" onclick="location.href='/oauth2/authorization/google'">
        Google 로그인
    </button>
    
	<!-- 네이버 로그인 버튼 -->
	<button id="naverLoginBtn" type="button" onclick="location.href='/oauth2/authorization/naver'">
	    Naver 로그인
	</button>

    <script>
        var userName = $('input[name=userName]').val();
      	//로그인한 아이디가 아닐 때
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