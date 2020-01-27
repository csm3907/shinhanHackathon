<%@page import="com.fund.util.UrlUtil"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%
    UrlUtil urlUtil = new UrlUtil();
    String clientId = "9f987ff8ba1e7bf970a623af5512d97f";
    String redirectUri = "http://www.shinhan-myfund.site/v1.0/kakao/token";
    String apiUrl ="https://kauth.kakao.com/oauth/authorize?response_type=code";
    apiUrl += "&client_id=" + clientId;
    apiUrl += "&redirect_uri=" + redirectUri;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>카카오 로그인</title>
<!-- Bootstrap 4.0-->
<link rel="stylesheet" href="/assets/vendor_components/bootstrap/dist/css/bootstrap.css">
<!-- Bootstrap extend-->
<link rel="stylesheet" href="/assets/css/bootstrap-extend.css">
<!-- theme style -->
<link rel="stylesheet" href="/assets/css/master_style.css">
<!-- AIUI Admin skins -->
<link rel="stylesheet" href="/assets/css/skins/_all-skins.css">
<!-- Morris charts -->
<!--  정적 자원은 여기다. (Root 경로는 resource, static 그다음은 따라오는거 (나머지만 적으면 된다.) -->
<link rel="stylesheet" href="/assets/vendor_components/morris.js/morris.css">
</head>
<body>
	<a href="<%= apiUrl %>" class="nav-link">카카오 로그인</a>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
</body>
</html>