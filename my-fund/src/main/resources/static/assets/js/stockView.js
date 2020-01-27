//[Preview Menu Javascript]

//Project:	AIUI Admin - Responsive Admin Template
//Primary use:   This file is for demo purposes only.

var gGraphStatus = true;

$(document).ready(function(){
	console.log("document ready in!!!!");
});

/**
 * onCreate 시작.
 *
 * @param Null
 * @returns Null
 */
$(function () {
	//alert("setupProfitList : " + setupProfitList);
	console.log(stockProfitList);
});


/**
 * 상세 기사 출력 이벤트
 *
 * @param Null
 * @returns Null
 */
$(function() {
	$('#ModalContents').empty();
	
	
	$(".articleRow").click(function(){
		console.log($(this).children("input[name=itemDate]").val()); // 기사 Date 
		console.log($(this).children("input[name=itemSeq]").val()); // 기사 seq 
		console.log($(this).children("input[name=itemName]").val()); // 기사 seq 
		
		var seq = $(this).children("input[name=itemSeq]").val();
		var date = $(this).children("input[name=itemDate]").val();
		var name = $(this).children("input[name=itemName]").val();
		
		var url = "/v1.0/news/" + seq + "/" + date;
		console.log(url);
		$("#myLargeModalLabel").text(name);
		
		
		$.ajax(
		   {
		        url: url,
		        type: "GET",
		        success: function(msg) {
		        	//console.log(msg.result.news_data);
		        	var div = document.createElement('div');
		            div.className = 'modal-body';
		            div.innerHTML = msg.result.news_data;
		            document.getElementById('ModalContents').appendChild(div);
		        	
		        	$("#LargeModalView").modal();
		        },error: function(e) {
		        	alert("서버와의 연결 도중 오류가 발생하였습니다. 다시 시도해 주십시오.");
		        }
		    }
		);
    });
});



