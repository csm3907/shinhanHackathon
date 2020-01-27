//[Preview Menu Javascript]

//Project:	AIUI Admin - Responsive Admin Template
//Primary use:   This file is for demo purposes only.

var gGraphStatus = true;

$(document).ready(function(){
	console.log("document ready in!!!!");
});

/**
 * , 를 찍는 함수를 실행한다.
 *
 * @param 10000 같은 String Value
 * @returns ,를 찍은 값을 return 한다.  (금액 Formatting 함수)
 */
function setMoneyAmt(str) {
	  str = String(str);

	  return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}


/**
 * onCreate 시작.
 *
 * @param Null
 * @returns Null
 */
$(function () {
	//alert("setupProfitList : " + setupProfitList);
	if(fundInfo.company == null || fundInfo.company == undefined){
		$("#fundName").text(fundInfo.name);
	}else{
		$("#fundName").text(fundInfo.company + " " + fundInfo.name);
	}

	$("#myProfitMain").text(setMoneyAmt(fundInfo.standardPrice) + " ￦");
	if(fundInfo.turnoverRatio == null || fundInfo.turnoverRatio == undefined){
		$("#turnOverRatioBox").hide();
	}
	if(fundInfo.totalExpenseRatio == null || fundInfo.totalExpenseRatio == undefined){
		$("#tatalExpenseRatioBox").hide();
	}
	if(fundInfo.tradingFeeRatio == null || fundInfo.tradingFeeRatio == undefined){
		$("#tradingFeeBox").hide();
	}
	if(fundInfo.manager == null || fundInfo.manager == undefined){
		$("#fundManagerBox").hide();
	}

	$("#profitGragh").on("click", onClickProfit);


	// 총비용보수 비율
	$("#totalExpenseRatioName").text(fundInfo.totalExpenseRatio + "%");
	$("#totalExpenseRatioCount").text(fundInfo.totalFundCount);
	$("#totalExpenseRatioRank").text(fundInfo.totalExpenseRatioRank);
	// 매매중개수수료비율
	$("#tradingFeeRatioInfo").text(fundInfo.tradingFeeRatio + "%");
	$("#tradingFeeFundCount").text(fundInfo.totalFundCount);
	$("#tradingFeeFundRank").text(fundInfo.tradingFeeRatioRank);
	// 매매회전율값을 나타내는 박스 값 Setting
	$("#turnOverRatio").text(fundInfo.turnoverRatio + " %");
	//펀드매니저 값 세팅
	$("#fundManager").text(fundInfo.manager);
	$("#fundManagerDate").text(fundInfo.managerDate);

	/*
	 * STOCK // 주식형
	MIXED_STOCK // 주식혼합형
	BOND // 채권형
	MIXED_BOND // 채권혼합형
	REINDIRECTNESS // 재간접형
	REAL_ESTATE // 부동산형
	ETC // 기타형
	 */
	if(fundInfo.fundType == "STOCK") {
		$("#totalExpenseRatioFundType").text("주식형");
		$("#tradingFeeFundType").text("주식형");
	} else if(fundInfo.fundType == "MIXED_STOCK") {
		$("#totalExpenseRatioFundType").text("주식혼합형");
		$("#tradingFeeFundType").text("주식혼합형");
	} else if(fundInfo.fundType == "BOND") {
		$("#totalExpenseRatioFundType").text("채권형");
		$("#tradingFeeFundType").text("채권형");
	} else if(fundInfo.fundType == "MIXED_BOND") {
		$("#totalExpenseRatioFundType").text("채권혼합형");
		$("#tradingFeeFundType").text("채권혼합형");
	} else if(fundInfo.fundType == "REINDIRECTNESS") {
		$("#totalExpenseRatioFundType").text("재간접형");
		$("#tradingFeeFundType").text("재간접형");
	} else if(fundInfo.fundType == "REAL_ESTATE") {
		$("#totalExpenseRatioFundType").text("부동산형");
		$("#tradingFeeFundType").text("부동산형");
	} else { //ETC
		$("#totalExpenseRatioFundType").text("기타형");
		$("#tradingFeeFundType").text("기타형");
	}
});


/**
 * Main Graph Button Click Event
 *
 * @param Null
 * @returns Null
 */
function onClickProfit() {
	$('#line-adwords').empty();

	var listDate = [];
	var listProfit = [];
	var graphColor = " ";

	if(gGraphStatus == true){ // setupProfitList (설정일 기준 수익률 그래프 보여주기.)
		for(var index=0; index < standardProfit.length; index++){
			listDate.push(standardProfit[index].date);
			listProfit.push(Number(standardProfit[index].profit));
		}
		graphColor = "#f3c74d";
		$("#profitGragh").text("기준일 기준 수익률 그래프");
	} else { // standardProfitList (기준일 기준 수익률 그래프 보여주기.)
		for(var index=0; index < setupProfitList.length; index++){
			listDate.push(setupProfitList[index].date);
			listProfit.push(Number(setupProfitList[index].profit));
		}
		graphColor = "#4d79f6";
		$("#profitGragh").text("설정일 기준 수익률 그래프");
	}
	gGraphStatus = !gGraphStatus;
	console.log("inClick : " + listProfit);
	console.log(Math.max.apply(null,listProfit));
	console.log(Math.min.apply(null,listProfit));


	var optionsLine = {
			chart: {
				height: 360,
				type: 'line',
				zoom: {
					enabled: false
				},
			},
			stroke: {
				curve: 'smooth',
				width: 2
			},
			colors: [graphColor], //여기에 color 를 rgB로 추가해주면 된다.
			series: [{ //여기에 값들을 추가하면 그래프가 여러개 그려진다.
				name: fundInfo.name,
				data: listProfit
			}
			],
			markers: {
				size: listDate.length,
				strokeWidth: 0,
				hover: {
					size: 9
				}
			},
			grid: {
				show: true
			},
			labels: listDate,
			xaxis: {
				tooltip: {
					enabled: false
				}
			},
			yaxis: {
				title: {
					text: '수익률'
				},
				min: Math.min.apply(null,listProfit) - 10,
		        max: Math.max.apply(null,listProfit) + 10
			},
			legend: {
				position: 'top',
				horizontalAlign: 'right',
				offsetY: -20
			}
	}

	var chartLine = new ApexCharts(document.querySelector('#line-adwords'), optionsLine);
	chartLine.render(); // 다시 그리기.

	console.log("end of Chart redraw");
	console.log(listDate);

}

/**
 * tableForStock Click Event
 *
 * @param Null
 * @returns Null
 */
$(function() {
	$("#tableForStock tr").click(function() {
		/*
		 *  현재 실 Data에서는 Code 값이 000660 만 내려와 현재 Code 값을 고정으로 진행하였습니다.
		 *  (실 데이터에서는 Code 값이 다양하게 내려올 예정입니다.)
		 */
		var code = "000660";
		location.href = "/v1.0/stocks/" + code;
	});
});

/**
 * turnOverRatioBox Click Event
 *
 * @param Null
 * @returns Null
 */
$(function() {
	// 매매회전율 modal
	$("#turnOverRatioBox").click(function() {
		$("#turnOverRatioModal").modal();
	});

	// 펀드매니저 modal
	$("#fundManagerBox").click(function() {
		$("#fundManagerModal").modal();
	});

	// 총보수비용비율 modal
	$("#tatalExpenseRatioBox").click(function() {
		$("#tatalExpenseRatioModal").modal();
	});

	// 매매중개수수료비율 modal
	$("#tradingFeeBox").click(function() {
		$("#tradingFeeModal").modal();
	});

});
