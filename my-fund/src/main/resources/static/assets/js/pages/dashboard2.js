//[Dashboard Javascript]

//Project:	AIUI Admin - Responsive Admin Template
//Primary use:   Used only for the main dashboard (index.html)


$(function () {
  console.log("dashboard2 in!!");
  var colorArray = ["#4d79f6", '#4ac7ec', '#f3c74d', '#106ebe', '#ff0000'];
  var colorForCircleGraph = [];
  var listDate = [];
  var listProfit = [];
	
  for(var index=0; index < setupProfitList.length; index++){
		listDate.push(setupProfitList[index].date);
		listProfit.push(Number(setupProfitList[index].profit));
  }
  
  console.log(listDate);
  console.log(Math.max.apply(null,listProfit));
  console.log(Math.min.apply(null,listProfit));
  
  var myPortFolioassetType = [];
  var myPortFolioratio = [];
  
  for(var index=0; index < assetPortfolo.length; index++){
	  myPortFolioassetType.push(assetPortfolo[index].assetType);
	  myPortFolioratio.push(Number(assetPortfolo[index].ratio));
	  colorForCircleGraph.push(colorArray[index % 3]);
  }
  
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
	  colors: ["#4d79f6"], //여기에 color 를 rgB로 추가해주면 된다.
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
	chartLine.render();
	
	// 여기서부터 원차트 시작이다.
	var optionsCircle4 = {
	  chart: {
		type: 'radialBar',
		width: 320,
		height: 430,
	  },
	  plotOptions: {
		radialBar: {
		  size: undefined,
		  inverseOrder: true,
		  hollow: {
			margin: 5,
			size: '48%',
			background: 'transparent',

		  },
		  track: {
			show: false,
		  },
		  startAngle: -180,
		  endAngle: 180

		},
	  },
	  stroke: {
		lineCap: 'round'
	  },
	  colors: colorArray, //원그래프의 색 값이 들어간다.
	  series: myPortFolioratio, // Data 값들이 들어간다.
	  labels: myPortFolioassetType, //Data Label 값이 들어간다.
	  legend: {
		show: true,
		floating: true,
		position: 'left',
		offsetX: 10,
		offsetY: 330
	  },
	}

	var chartCircle4 = new ApexCharts(document.querySelector('#radialBarBottom'), optionsCircle4);
	chartCircle4.render();
	
}); // End of use strict




