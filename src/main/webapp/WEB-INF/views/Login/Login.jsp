<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<title>로그인 화면</title>
</head>
<body>
 <h2>로그인</h2>
    <form action="${pageContext.request.contextPath}/Login" method="post">
        <div id="naver_id_login"></div>
    </form>
   <script type="text/javascript">
    var naver_id_login = new naver_id_login("WvFIrG7DSpEo66yj42r8", "http://localhost:8080/home");
    var state = naver_id_login.getUniqState();
    naver_id_login.setButton("white", 2, 40); // 버튼 색상, 크기, 높이 설정
    naver_id_login.setDomain("http://localhost:8080"); // 서비스 도메인 설정
    naver_id_login.setState(state); // 상태 토큰 설정
    naver_id_login.setPopup(); // 팝업 형태의 인증 진행
    naver_id_login.init_naver_id_login();
</script>
</body>
</html>