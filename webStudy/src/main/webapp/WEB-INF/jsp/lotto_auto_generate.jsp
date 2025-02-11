<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>로또 번호 자동 생성 화면</title>
</head>
<body>

<h1>로또 번호 자동 생성</h1>
<button onclick="location.href='/board/lotto/generate'">로또 번호 받기</button>
<p>생성된 번호: ${lottoNumbers}</p>
    
</body>
</html>