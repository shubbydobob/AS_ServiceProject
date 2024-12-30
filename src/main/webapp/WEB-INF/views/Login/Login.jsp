<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>로그인 화면</title>
    <link rel="stylesheet" href="/resources/css/Login/Login.css">
</head>
<body>
   <div class="login-container">
        <h2>로그인</h2>
        <form method="post" action="/home" id="loginForm">
            <div class="form-group">
                <label for="id">아이디</label>
                <input type="text" name="userId" id="userId" placeholder="아이디를 입력해주세요." required>
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="password" name="password" id="password" placeholder="비밀번호를 입력해주세요." required>
            </div>
          <div class="options">
                <label>
                    <input type="checkbox" name="rememberId" id="rememberId">
                    아이디 저장
                </label>
                <div class="find-links">
                    <button type="button" class="find-button" id="findIdButton">아이디 찾기</button>
                    <button type="button" class="find-button" id="findPasswordButton">비밀번호 찾기</button>
                </div>
            </div>
            <button type="submit" class="login-button">로그인</button>
        </form>
        
        
        <div class="naver-login">
            <div id="naver_id_login"></div>
        </div>
    </div>
    <script type="text/javascript">
        var naver_id_login = new naver_id_login("WvFIrG7DSpEo66yj42r8", "http://localhost:8080/home");
        var state = naver_id_login.getUniqState();
        naver_id_login.setButton("white", 2, 40); // 버튼 색상, 크기, 높이 설정
        naver_id_login.setDomain("http://localhost:8080"); // 서비스 도메인 설정
        naver_id_login.setState(state); // 상태 토큰 설정
        naver_id_login.init_naver_id_login();
    </script>
</body>
</html>
