<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>초기화면</title>
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
    
    <script>
	    var userName = $('input[name=userName]').val();
		//로그인한 아이디가 아닐 때
	    if(userName == 'anonymousUser') {
	    	$('#loginBtn').show(); //로그인버튼 보이기
	    	$('#regBtn').show(); //회원가입버튼 보이기
	    	$('#logoutBtn').hide(); //로그아웃버튼 숨기기
	    }
	    else{
	    	$('#loginBtn').hide(); //로그인버튼 숨기기
	    	$('#regBtn').hide(); //회원가입버튼 숨기기
	    	$('#logoutBtn').show(); //로그아웃버튼 보이기
	    }
	</script>
	
</body>
</html>