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
			
				<div class="box">
            <div class="box-header with-border">
              <h4 class="box-title">관심종목(신한금융투자) 목록</h4><br><small>클릭 시 해당 종목을 포함하고 있는 펀드리스트가 나옵니다.</small>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
				<div class="table-responsive">
					<table id="example" class="table table-bordered table-hover display nowrap margin-top-10 w-p100">
					<thead>
						<tr>
							<th>종목</th>
							<th>국내외 구분</th>
							<th></th>
							<th>변동가격</th>
							<th>현재가</th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach items="${itemList}" var="item" varStatus="status">
							<tr class="favoriteList">
								<input type="hidden" name="itemCode" value="${item.stbd_code}"/>
								<input type="hidden" name="itemName" value="${item.stbd_nm}"/>
								<td><c:out value="${item.stbd_nm}"/></td>
								<td>
									<c:choose>
									<c:when test="${item.dom_for_tp_code eq '1'}">
										<c:out value="국내" />
									</c:when>
									<c:otherwise> <c:out value="해외"/> </c:otherwise>
									</c:choose>
								</td>
								<td align="center">
									<c:choose>
									<c:when test="${item.increase eq true}">
										<span class="badge badge-success">Up</span>
									</c:when>
									<c:otherwise> <span class="badge badge-danger">Down</span></c:otherwise>
									</c:choose>
								</td>
								<td><c:out value="${item.fluctuationPrice}"/></td>
								<td>
									<fmt:formatNumber type="number" maxFractionDigits="3" value="${item.price}" />
								</td>
							</tr>
						</c:forEach>
						
					</tbody>
				  </table>
				</div>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->  
				
				<div class="modal fade bs-example-modal-lg" id="LargeModalView" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" id="myLargeModalLabel">Large modal</h4>
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							</div>
							<div class="modal-body">
								<table class="table table-hover mb-5">
									<thead>
							  			<tr>
											<th >펀드명</th>
							  			</tr>
									</thead>
									<tbody>
										<tr id="ModalContents">
							  			</tr>
									</tbody>
						  		</table>
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
	</div>

	<!-- This is data table -->
    <script src="/assets/vendor_components/datatable/datatables.min.js"></script>
	
	<!-- AIUI Admin for Data Table -->
	<script src="/assets/js/pages/data-table.js"></script>
	
	<script src="/assets/js/favorite.js"></script>
	
</body>
</html>
    