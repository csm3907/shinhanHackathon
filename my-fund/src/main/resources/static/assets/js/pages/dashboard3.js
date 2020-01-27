//[Dashboard Javascript]

//Project:	AIUI Admin - Responsive Admin Template
//Primary use:   Used only for the main dashboard (index.html)


$(function () {

  'use strict';
  
  var AmtSet = [];
  var dateSet = [];
  
  for(var index=0; index < 10; index++){
	  dateSet.push(stockProfitList[index].date);
	  AmtSet.push(Number(stockProfitList[index].price));
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
			  name: '주가 변동',
			  data: AmtSet
			}
		  ],
		  markers: {
			size: AmtSet.length,
			strokeWidth: 0,
			hover: {
			  size: 9
			}
		  },
		  grid: {
			show: true
		  },
		  labels: dateSet,
		  xaxis: {
			tooltip: {
			  enabled: false
			}
		  },
		  yaxis: {
	          title: {
	              text: '수익률'
	          },
	          min: Math.min.apply(null,AmtSet) - 10,
	          max: Math.max.apply(null,AmtSet) + 10
	      	},
		  legend: {
			position: 'top',
			horizontalAlign: 'right',
			offsetY: -20
		  }
		}

		//var chartLine = new ApexCharts(document.querySelector('#earnings'), optionsLine);
		//chartLine.render();
	
}); // End of use strict

