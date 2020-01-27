<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmtDT" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar-->
    <section class="sidebar">
	  <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="image">
          <img src="/images/avatar/3.jpg" class="rounded-circle" alt="User Image">
        </div>
        <div class="info">
          <p><c:out value="${custNm}"/></p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control border-0 bg-transparent" placeholder="Search...">
          <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat bg-transparent"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->

      <!-- sidebar menu-->
   	  <ul class="sidebar-menu" data-widget="tree">
		<c:forEach items="${goodNmList}" var="goodNm" varStatus="status">
			<li class="treeview"><a href="/v1.0/"> <i
					class="mdi mdi-view-dashboard"></i><span><c:out value="${fn:substring(goodNm,0,9)}"/></span> <span
					class="pull-right-container"> <i
						class="ti-plus pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<c:forEach items="${fundList}" var="fund" varStatus="status">
					<li><a href="/v1.0/${fund.scCode}"><i
							class="mdi mdi-resize-bottom-right"></i><c:out value="${fund.name}"/></a></li>
					</c:forEach>
				</ul>
			</li>
		</c:forEach>
		<li class="treeview"><a href="/v1.0/"> <i
					class="mdi mdi-view-dashboard"></i><span>타사 펀드비교</span> <span class="pull-right-container"> <i
						class="ti-plus pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">
					<li><a href="/v1.0/klia/1"><i class="mdi mdi-resize-bottom-right"></i>주식형</a></li>
					<li><a href="/v1.0/klia/76"><i class="mdi mdi-resize-bottom-right"></i>채권형</a></li>
				</ul>
		</li>
		<li class="nav-devider"><a href="/v1.0/stocks/favorite"><i class="mdi mdi-file-document"></i>나의 관심종목</a></li>
		<li class="mt-35"><a href="/v1.0/tax"><i class="mdi mdi-file-document"></i>비과세 계산기</a></li>
      </ul>
    </section>
  </aside>