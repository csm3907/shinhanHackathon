<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmtDT" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/images/favicon.ico">

    <title>신한해커톤 - 변액지킴이</title>

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
	
	<!-- jQuery 3 -->
	<script src="/assets/vendor_components/jquery-3.3.1/jquery-3.3.1.min.js"></script>
	
	<!-- jQuery UI 1.11.4 -->
	<script src="/assets/vendor_components/jquery-ui/jquery-ui.js"></script>


  </head>
  <body class="hold-transition skin-black dark-sidebar sidebar-mini">
	<div class="wrapper">
		<jsp:include page="header.jsp"></jsp:include>
		<jsp:include page="aside.jsp"></jsp:include>
		<c:if test="${content ne null}">
			<jsp:include page="${content}.jsp"></jsp:include>
		</c:if>
		
		<!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
	  	<div class="control-sidebar-bg"></div>
	  	<jsp:include page="footer.jsp"></jsp:include>
  	</div>
  <!-- ./wrapper -->
  

	<!-- popper -->
	<script src="/assets/vendor_components/popper/dist/popper.min.js"></script>

	<!-- Bootstrap 4.0-->
	<script src="/assets/vendor_components/bootstrap/dist/js/bootstrap.js"></script>

	<!-- Slimscroll -->
	<script src="/assets/vendor_components/jquery-slimscroll/jquery.slimscroll.js"></script>

	<!-- FastClick -->
	<script src="/assets/vendor_components/fastclick/lib/fastclick.js"></script>

	<!-- Sparkline -->
	<script src="/assets/vendor_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>

	<!-- apexcharts -->
	<script src="/assets/vendor_components/apexcharts-bundle/irregular-data-series.js"></script>
	<script src="/assets/vendor_components/apexcharts-bundle/dist/apexcharts.js"></script>

	<!-- AIUI Admin App -->
	<script src="/assets/js/template.js"></script>

	<script src="/assets/js/comm.js"></script>
  </body> 
</html>