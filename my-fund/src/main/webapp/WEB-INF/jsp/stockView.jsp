<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmtDT" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="toDay" class="java.util.Date" />

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
	<!-- Content Header (Page header) -->
		<div class="content-header d-none d-md-block">
			<div class="d-flex align-items-center">
				<div class="mr-auto">
					<h3 class="page-title br-0" id="fundName">
						<%-- <c:out value="${fund.name8_1_0_}"></c:out>  --%>
					</h3>
				</div>
				<div class="right-title">
					<div class="d-flex mt-10 justify-content-end">
						<div class="d-md-flex mr-20 ml-10 d-none">
							<div class="chart-text mr-10">
								<h6 class="mb-0"><small>THIS MONTH</small></h6>
								<h4 class="mt-0 text-primary">₩ 12,125</h4>
							</div>
							<div class="spark-chart">
								<div id="thismonth"><canvas width="60" height="35" style="display: inline-block; width: 60px; height: 35px; vertical-align: top;"></canvas></div>
							</div>
						</div>
						<div class="d-md-flex ml-10 d-none">
							<div class="chart-text mr-10">
								<h6 class="mb-0"><small>LAST YEAR</small></h6>
								<h4 class="mt-0 text-danger">￦22,754</h4>
							</div>
							<div class="spark-chart">
								<div id="lastyear"><canvas width="60" height="35" style="display: inline-block; width: 60px; height: 35px; vertical-align: top;"></canvas></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

    <!-- Main content -->
    <section class="content">
		<div class="container-full">
			<div class="row">
				<div class="col-12">
					<div class="box pull-up">
						<div class="box-header with-border">
							<h4 class="box-title">주가차트</h4>
							<div class="box-controls pull-right">
								<select class="custom-select">
									<option value="0" selected="">Monthly</option>
									<option value="1">Daily</option>
									<option value="2">Weekly</option>
									<option value="3">Yearly</option>
								</select>
							</div>
						</div>

						<div class="box-body">
							<div class="row">
								<div class="col-lg-9">
									<div class="chart">
										<!-- <div id="earnings"></div> -->
										<!-- HTML -->
										<div id="chartdiv"></div>
									</div>
								</div>
								<div class="col-lg-3">
									<h1 class="mb-0 mt-4">$7,7458.68</h1>
									<h6 class="font-light text-muted">This Month Earnings</h6>
									<h3 class="mt-4 mb-0">4,421</h3>
									<h6 class="font-light text-muted">This Month Sales</h6>
									<a class="btn btn-info mt-3 p-15 pl-4 pr-4 mb-3" href="javascript:void(0)">Previous Month Summary</a>
								</div>
							</div>
						</div>
						<div class="box-footer">
							<div class="row mb-0">
								<div class="col-lg-3 col-md-6">
									<div class="d-flex align-items-center">
										<div class="mr-2"><span class="text-orange font-size-60"><i class="mdi mdi-wallet"></i></span></div>
										<div><span>Account Balance</span>
											<h3 class="my-0">$12,8547.53</h3>
										</div>
									</div>
								</div>
								<div class="col-lg-3 col-md-6">
									<div class="d-flex align-items-center">
										<div class="mr-2"><span class="text-cyan font-size-60"><i class="mdi mdi-star-circle"></i></span></div>
										<div><span>Referral Program</span>
											<h3 class="my-0">$7458.08</h3>
										</div>
									</div>
								</div>
								<div class="col-lg-3 col-md-6">
									<div class="d-flex align-items-center">
										<div class="mr-2"><span class="text-info font-size-60"><i class="mdi mdi-shopping"></i></span></div>
										<div><span>Total Sales</span>
											<h3 class="my-0">7854</h3></div>
									</div>
								</div>
								<div class="col-lg-3 col-md-6">
									<div class="d-flex align-items-center">
										<div class="mr-2"><span class="text-primary font-size-60"><i class="mdi mdi-currency-usd"></i></span></div>
										<div><span>Sales Earnings</span>
											<h3 class="my-0">$12,985.90</h3>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-12 col-12">
					<div class="box">
					  <div class="box-header">
						<h4 class="box-title">관련 뉴스</h4>
						<br> 뉴스 제목 클릭 시 상세 내용을 확인할 수 있습니다.
					  </div>
					  <div class="box-body">
						<div class="table-responsive">
						  <table class="table table-hover mb-5">
							<thead>
							  <tr>
								<th>제목</th>
								<th>출처</th>
								<th></th>
								<th>Date</th>
							  </tr>
							</thead>
							<tbody>
							  <c:forEach items="${newsList}" var="item" varStatus="status" begin="0" end="10" step="1">
								<tr class="articleRow">
									<input type="hidden" name="itemDate" value="${item.news_date}"/>
									<input type="hidden" name="itemSeq" value="${item.news_no1}"/>
									<input type="hidden" name="itemName" value="${item.news_titl}"/>

									<td><c:out value="${item.news_titl}"/></td>
									<td><c:out value="${item.press}" /></td>
									<td>
								 		<span class="badge badge-success">Click</span>
									</td>
									<td>
										<c:out value="${item.news_date}"/>
									</td>
							  	</tr>
							  </c:forEach>
							</tbody>
						  </table>
						</div>
					  </div>
					</div>
				  </div>


				<!-- <div class="col-xl-4 col-12">
					<div class="box">
					  <div class="box-header">
						<h4 class="box-title">Recent Buyers</h4>
					  </div>
					  <div class="box-body px-1">
						<div id="recent-buyers" class="media-list">
						  <a href="#" class="media xs-media p-5">
							<div class="media-left pr-1">
							  <span class="avatar avatar-lg">
								<img class="media-object" src="/images/avatar/1.jpg" alt="Generic placeholder image">
								<i></i>
							  </span>
							</div>
							<div class="media-body w-100">
							  <h6 class="list-group-item-heading">Kristopher Candy
								<span class="float-right pt-1">$1,021</span>
							  </h6>
							  <p class="list-group-item-text mt-5 mb-0">
								<span class="badge badge-primary">Electronics</span>
								<span class="badge badge-warning ml-1">Decor</span>
							  </p>
							</div>
						  </a>
						  <a href="#" class="media xs-media p-5">
							<div class="media-left pr-1">
							  <span class="avatar avatar-lg">
								<img class="media-object" src="/images/avatar/2.jpg" alt="Generic placeholder image">
								<i></i>
							  </span>
							</div>
							<div class="media-body w-100">
							  <h6 class="list-group-item-heading">Lawrence Fowler
								<span class="float-right pt-1">$2,021</span>
							  </h6>
							  <p class="list-group-item-text mt-5 mb-0">
								<span class="badge badge-danger">Appliances</span>
							  </p>
							</div>
						  </a>
						  <a href="#" class="media xs-media p-5">
							<div class="media-left pr-1">
							  <span class="avatar avatar-lg">
								<img class="media-object" src="/images/avatar/3.jpg" alt="Generic placeholder image">
								<i></i>
							  </span>
							</div>
							<div class="media-body w-100">
							  <h6 class="list-group-item-heading">Linda Olson
								<span class="float-right pt-1">$1,112</span>
							  </h6>
							  <p class="list-group-item-text mt-5 mb-0">
								<span class="badge badge-primary">Electronics</span>
								<span class="badge badge-success ml-1">Office</span>
							  </p>
							</div>
						  </a>
						  <a href="#" class="media xs-media p-5">
							<div class="media-left pr-1">
							  <span class="avatar avatar-lg">
								<img class="media-object" src="/images/avatar/4.jpg" alt="Generic placeholder image">
								<i></i>
							  </span>
							</div>
							<div class="media-body w-100">
							  <h6 class="list-group-item-heading">Roy Clark
								<span class="float-right pt-1">$2,815</span>
							  </h6>
							  <p class="list-group-item-text mt-5 mb-0">
								<span class="badge badge-warning">Decor</span>
								<span class="badge badge-danger ml-1">Appliances</span>
							  </p>
							</div>
						  </a>
						  <a href="#" class="media xs-media p-5">
							<div class="media-left pr-1">
							  <span class="avatar avatar-lg">
								<img class="media-object" src="/images/avatar/5.jpg" alt="Generic placeholder image">
								<i></i>
							  </span>
							</div>
							<div class="media-body w-100">
							  <h6 class="list-group-item-heading">Kristopher Candy
								<span class="float-right pt-1">$2,021</span>
							  </h6>
							  <p class="list-group-item-text mt-5 mb-0">
								<span class="badge badge-primary">Electronics</span>
								<span class="badge badge-warning ml-1">Decor</span>
							  </p>
							</div>
						  </a>
						  <a href="#" class="media xs-media p-5">
							<div class="media-left pr-1">
							  <span class="avatar avatar-lg">
								<img class="media-object" src="/images/avatar/6.jpg" alt="Generic placeholder image">
								<i></i>
							  </span>
							</div>
							<div class="media-body w-100">
							  <h6 class="list-group-item-heading">Lawrence Fowler
								<span class="float-right pt-1">$1,321</span>
							  </h6>
							  <p class="list-group-item-text mt-5 mb-0">
								<span class="badge badge-danger">Appliances</span>
							  </p>
							</div>
						  </a>
						  </div>
					  </div>
					</div>
				</div>
				 -->
				<!--
				<div class="col-xl-3 col-12">
					<div class="box">
						<div class="box-body">
							<div class="d-flex justify-content-between">
								<div class="bg-primary ml-2 h-50 w-50 rounded-circle d-icon">
									<i class="fa fa-line-chart"></i>
								</div>
								<div class="text-right">
									<p class="font-size-18 mb-0 text-fade">Sales</p>
									<p class="font-size-20">12,354</p>
								</div>
							</div>
							<p class="mt-20">95% Groth Sales</p>
						</div>
					</div>
				</div>
				 -->

				<div class="modal fade bs-example-modal-lg" id="LargeModalView" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="myLargeModalLabel">Large modal</h4>
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							</div>
							<div class="modal-body" id="ModalContents">

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger waves-effect text-left" data-dismiss="modal">Close</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->


			</div>
		</div>
	</section>
    <!-- /.content -->
  </div>

	<!-- Choi 구현하는 javaScript 파일 import -->
	<script type="text/javascript">
		var stockProfitList = ${stockProfitList}; // 종목 현재가 추이
	</script>

	<!-- Morris.js charts -->
	<script src="/assets/vendor_components/raphael/raphael.min.js"></script>
	<script src="/assets/vendor_components/morris.js/morris.min.js"></script>

	<script src="/assets/js/stockView.js"></script>

	<!-- AIUI Admin dashboard -->
	<script src="/assets/js/pages/dashboard3.js"></script>

	<!-- Resources -->
	<script src="https://www.amcharts.com/lib/4/core.js"></script>
	<script src="https://www.amcharts.com/lib/4/charts.js"></script>
	<script src="https://www.amcharts.com/lib/4/themes/animated.js"></script>

	<style>
	#chartdiv {
 		width: 100%;
  		height: 500px;
	}
	</style>

	<script>
	am4core.ready(function() {

		// Themes begin
		am4core.useTheme(am4themes_animated);
		// Themes end

		var chart = am4core.create("chartdiv", am4charts.XYChart);

		var data = [];
		var value = 50;

		var AmtSet = [];
		var dateSet = [];

		for(var index=0; index < stockProfitList.length; index++){
		 	dateSet.push(stockProfitList[index].date);
			AmtSet.push(Number(stockProfitList[index].price));

			var y = stockProfitList[index].date.substr(0,4), //year
	        m = stockProfitList[index].date.substr(4,2), //month
	        d = stockProfitList[index].date.substr(6,2); //day

			let date = new Date(y,m,d); //Date 형식으로 변경
			date.setHours(0,0,0,0);

			data.push({date:date, value: AmtSet[index]});
		}

		chart.data = data;

		// Create axes
		var dateAxis = chart.xAxes.push(new am4charts.DateAxis());
		dateAxis.renderer.minGridDistance = 60;

		var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());

		// Create series
		var series = chart.series.push(new am4charts.LineSeries());
		series.dataFields.valueY = "value";
		series.dataFields.dateX = "date";
		series.tooltipText = "{value}"

		series.tooltip.pointerOrientation = "vertical";

		chart.cursor = new am4charts.XYCursor();
		chart.cursor.snapToSeries = series;
		chart.cursor.xAxis = dateAxis;

	}); // end am4core.ready()

	</script>