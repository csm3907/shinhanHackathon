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

				<div class="col-lg-3 col-md-6 col-12" id="tradingFeeBox">
					<div class="box box-danger pull-up">
						<div class="box-body" >
							<div class="d-flex align-items-center font-size-18">
							  <div class="icon">
								<i class="mdi mdi-package-down text-white mr-10"></i>
							  </div>
							  <small class="font-weight-medium mb-0 text-white" id="tradingFeeName">매매수수료비율 &nbsp;</small>
							  <div style="text-align: right;">
							  	<h3 class="font-weight-normal text-white" style="text-align: right;" id="tradingFeeRatioInfo"></h3>
							  </div>
							</div>
							<!-- 매매수수료비율 전체 순위 -->
							<div class="d-flex align-items-center font-weight-medium text-white">
							  <small>전체</small>&nbsp;
							  <small class="font-weight-normal text-white" id=tradingFeeFundType></small>&nbsp;
							  <h3 class="font-weight-normal text-white"  id="tradingFeeFundCount"></h3>
							  <small class="font-weight-medium mb-0 text-white">개 중 </small>&nbsp;
							  <h3 class="font-weight-normal text-white" id=tradingFeeFundRank></h3>&nbsp;
							  <small class="font-weight-medium mb-0 text-white">등</small>
							</div>
						</div>
					</div>
				</div>

				<!-- 총보수비용비율(Total_expense_ratio), 총보수비용비율 순위(TotalExpenseRatioRank) -->
				<div class="col-lg-3 col-md-6 col-12" id="tatalExpenseRatioBox">
					<div class="box box-info pull-up">
						<div class="box-body" >
							<!-- 총보수비용비율 -->
							<div class="d-flex align-items-center font-size-18">
							  <div class="icon">
								<i class="mdi mdi-truck text-white mr-10"></i>
							  </div>
							  <small class="font-weight-medium mb-0 text-white">
							  	총보수비용비율 &nbsp;
							  </small>
							  <div style="text-align: right;">
							  	<h3 class="font-weight-normal text-white" style="text-align: right;" id="totalExpenseRatioName"></h3>
							  </div>
							</div>
							<!-- 총보수비용비율 전체 순위 -->
							<div class="d-flex align-items-center font-weight-medium text-white">
							  <small>전체</small>&nbsp;
							  <small class="font-weight-normal text-white" id=totalExpenseRatioFundType></small>&nbsp;
							  <h3 class="font-weight-normal text-white"  id="totalExpenseRatioCount"></h3>
							  <small class="font-weight-medium mb-0 text-white">개 중</small>&nbsp;
							  <h3 class="font-weight-normal text-white" id=totalExpenseRatioRank></h3>&nbsp;
							  <small class="font-weight-medium mb-0 text-white">등</small>
							</div>
						</div>
					</div>
				</div>


				<!-- 매매회전율(turnoverRatio), 매매회전율 순위(turnoverRatioRank)  -->
				<div class="col-lg-3 col-md-6 col-12" id="turnOverRatioBox">
					<div class="box box-primary pull-up">
						<div class="box-body" >
							<div class="d-flex align-items-center font-size-18">
							  <div class="icon">
								<i class="mdi mdi-star-circle text-white mr-10"></i>
							  </div>
							  <small class="font-weight-medium mb-0 text-white">매매 회전율 &nbsp;</small>
							  <div style="text-align: right;">
							  	<h3 class="font-weight-normal text-white" style="text-align: right;" id="turnOverRatio"> <!-- 회전율의 경우 서버 값을 세팅. -->
							  	</h3>
							  </div>
							</div>
							<div class="d-flex align-items-center font-weight-medium text-white">
							  <small>전체</small>&nbsp;
							  <small class="font-weight-normal text-white" id=tradingFeeFundType></small>&nbsp;
							  <h3 class="font-weight-normal text-white">123</h3>&nbsp;
							  <small class="font-weight-medium mb-0 text-white">개 중 </small>&nbsp;
							  <h3 class="font-weight-normal text-white">123</h3>&nbsp;
							  <small class="font-weight-medium mb-0 text-white">등</small>
							</div>

						</div>
					</div>
				</div>

				<!-- 펀드매니저 , 날짜, 이름 -->
				<div class="col-lg-3 col-md-6 col-12" id="fundManagerBox">
					<div class="box box-warning pull-up">
						<div class="box-body" >
							<!-- 펀드매니저 -->
							<div class="d-flex align-items-center font-size-18">
							  <div class="icon">
								<i class="mdi mdi-basket text-white mr-10"></i>
							  </div>
							  <small class="font-weight-medium mb-0 text-white" >펀드매니저 </small>
							  <div style="text-align: right;" class="col-lg-6">
							  	<h3 class="font-weight-normal text-white" style="text-align: right;" id="fundManager"></h3>
							  </div>
							</div>
							<!-- 운용개시일 -->
							<div class="d-flex align-items-center font-size-18">
							  <small class="font-weight-medium mb-0 text-white" id="fundManager">운용개시일 </small>&nbsp;
							  <div style="text-align: right; padding:0;">
							  	<h3 class="font-weight-normal text-white" style="text-align: right;" id="fundManagerDate"></h3>
							  </div>
							</div>

							<small class="font-weight-medium d-block text-white-50"></small>
						</div>
					</div>
				</div>

				<div class="col-xl-4 col-12">
					<div class="box">
						<div class="box-header with-border">
							<h4 class="box-title">
								자산구성
							</h4>
						</div>
						<div class="box-body">
							<div class="chart">
								<div id="radialBarBottom"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xl-8 col-12">
					<div class="box">
						<div class="box-header">
							<h4 class="box-title">
								<button type="button" class="btn btn-outline btn-flat btn-primary w-p100 text-left no-radius" id="profitGragh">설정일 기준 그래프</button>
							</h4>
						</div>
						<div class="box-body">
							<div class="chart">
								<div id="line-adwords"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-xl-8 col-12">
					<div class="box">
					  <div class="box-header">
						<h2 class="box-title">상위 10개 종목</h2>
						<br> 종목 클릭 시 해당 종목의 차트, 뉴스 등을 확인할 수 있습니다. 
						<div align="right">
							<h4 class="box-title" style="text-align: right;" >
								<c:if test="${standardDate ne null}">
									기준일 : <c:out value="${fn:substring(standardDate,0,10)}"> </c:out>
								</c:if>
							</h4> <!-- 기준일자 -->
						</div>
					  </div>
					  <div class="box-body">
						<div class="table-responsive">
						  <table class="table table-hover mb-5" id="tableForStock">
							<thead>
							  <tr>
								<th>Name</th>
								<th>Ratio</th>
								<th>Status</th>
								<th>Date</th>
							  </tr>
							</thead>
							<tbody>

							  <c:forEach items="${topItems}" var="item" varStatus="status">
								<tr>
									<td><c:out value="${item.name}"/></td>
									<td><c:out value="${item.ratio}" /></td>
									<td>
								 		<span class="badge badge-success">Up</span>
									</td>
									<td>
										<fmtDT:formatDate value="${toDay}" pattern="yyyy-MM-dd"/>
									</td>
							  	</tr>
							  </c:forEach>

							</tbody>
						  </table>
						</div>
					  </div>
					</div>
				  </div>

				<div class="col-xl-4 col-12">
					<div class="box">
						<div class="box-header with-border">
							<h4 class="box-title">펀드 전략 </h4>
						</div>
						<div class="box-body">
						  	<div class="media-body">
								<p>
									<a class="hover-primary" href="#">
									<strong>
										운용경과
									</strong>
									</a>
									<span class="float-right">
										2분기 중 펀드 듀레이션은 벤치마크와 유사한 수준으로 가져간 가운데,
										5월말 금통위에서 기준금리 인하를 주장하는 소수의견이 등장하고 대내외 경기 둔화 우려 지속됨에 따라 단기 구간 비중을 확대하고 장기 구간 비중을
										축소하여 스티프닝 포지션을 구축하였습니다. 이러한 커브 전략이 6월 중에 펀드성과에 우호적으로 작용한 가운데, 크레딧 채권을 통한 YTM 제고 전략을 통해
										벤치마크 대비 이자 수익과 롤링에서 아웃퍼폼을 기록하였습니다. 다만 종목 선택효과에서 다소 부진한 성과가 이를 상쇄하면서 해당기간 벤치마크와 유사한 성과를 기록하였습니다.
									</span>
								</p>

							</div>
						</div>
						<div class="box-body">
						  	<div class="media-body">
						  		<p>
									<a class="hover-primary" href="#">
									<strong>
										운용계획
									</strong>
									</a>
									<span class="float-right">
										미중 무역협상 난항과 함께 일본의 한국 수출 규제에 따른 국내 펀더멘털 둔화 입력 확대로 3분기 중 한국은행은 기준금리 인하를 단행할 것으로 예상합니다.
										다만 시장이 금리 인하 가능성을 선제적으로 반영한 상황이기 때문에 레벨 부담에 따른 단기 조정 가능성을 염두하고,반등 시 듀레이션을 확대할 계획입니다.
										통화정책 전환기 커브 스팁 압력이 우세해질 수 있으나, 국내 통화정책 대응이 실물 경기 악화 속도 대비 소극적이라는 점에서 분기 중 플래트닝 흐름 여전히
										유효할 것으로 보고 10년 구간 중심 OW 포트폴리오 구성할 계획입니다. 다만 경기 악화에 따른 잠재적 크레딧 리스크 가능성을 우려하여 추가적인 회사채 매수
										없이 현 수준을 유지할 계획입니다.
									</span>
								</p>
						  	</div>
						</div>
					</div>
				</div>

				<div class="modal fade bs-example-modal-lg" id="LargeModalView" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="myLargeModalLabel">Large modal</h4>
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							</div>
							<div class="modal-body">
								<h4>Overflowing text to show scroll behavior</h4>
								<p>Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor.</p>
								<p>Aenean lacinia bibendum nulla sed consectetur. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Donec sed odio dui. Donec ullamcorper nulla non metus auctor fringilla.</p>
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

				<!-- turnOverRatio modal -->
				<div class="modal fade bs-example-modal-sm" id="turnOverRatioModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" style="display: none;">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="mySmallModalLabel">매매회전율</h4>
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							</div>
							<div class="modal-body"> 매매회전율이란 초기 투자금액 대비 거래금액으로 평균 200%내외 입니다. 매매회전율이 평균보다 크게 상회하는 경우 펀드비용 증가로 수익률을 악화시킬 수 있습니다. </div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger waves-effect text-left" data-dismiss="modal">Close</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.turnOverRatio modal -->

				<!-- turnOverRatio modal -->
				<div class="modal fade bs-example-modal-sm" id="fundManagerModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" style="display: none;">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="mySmallModalLabel">펀드매니저</h4>
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							</div>
							<div class="modal-body"> 펀드매니저 교체가 빈번하다는 것은 운용상에 문제가 있다는 사실을 드러내기도 합니다.</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger waves-effect text-left" data-dismiss="modal">Close</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.turnOverRatio modal -->


				<!-- turnOverRatio modal -->
				<div class="modal fade bs-example-modal-sm" id="tatalExpenseRatioModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" style="display: none;">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="mySmallModalLabel">총보수비용비율</h4>
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							</div>
							<div class="modal-body"> 총보수비용비율이란 펀드의 순자산 중 반복적으로 지급되는 제반비용의 비율입니다. 예로 총보수비율이 3%라면 펀드 순자산의 3%가 매년 비용으로 지급됩니다. </div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger waves-effect text-left" data-dismiss="modal">Close</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.turnOverRatio modal -->


				<!-- turnOverRatio modal -->
				<div class="modal fade bs-example-modal-sm" id="tradingFeeModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" style="display: none;">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="mySmallModalLabel">매매중개수수료비율</h4>
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							</div>
							<div class="modal-body"> 매매중개수수료비율이란 주식이나 채권 등을 사고팔 때 발생하는 비용을 연환산한 비율입니다. 예로 매매중개수수료비율이 3%라면 펀드 순자산의 3%가 매년 비용으로 지급됩니다. </div>
							<div class="modal-footer">
								<button type="button" class="btn btn-danger waves-effect text-left" data-dismiss="modal">Close</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.turnOverRatio modal -->

			</div>
		 </div>
		</section>
		<!-- /.content -->
	</div>
    <!-- /.content-wrapper -->


  	<!-- Choi 구현하는 javaScript 파일 import -->
	<script type="text/javascript">
	var kliaCode = "${kliaCode}"; //펀드 코드.
	var setupDate = "${setupDate}"; // 설정
	var fundInfo = ${fund}; // 내가 가입한 펀드 정보.
	var setupProfitList = ${setupProfitList}; // 설정일 기준 수익률
	var standardProfit = ${standardProfit}; // 기준일 기준 수익률
	var assetPortfolo = ${assetPortfolo}; // 내 정보.(원그래프)

	</script>
	
	<script src="/assets/js/index.js"></script>
	
	<!-- AIUI Admin dashboard demo (This is only for demo purposes) -->
	<script src="/assets/js/pages/dashboard2.js"></script>
