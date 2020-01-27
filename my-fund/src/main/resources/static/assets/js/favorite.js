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
	
});


/**
 * 상세 기사 출력 이벤트
 *
 * @param Null
 * @returns Null
 */
$(function() {
	$('#ModalContents').empty();
	
	
	$(".favoriteList").click(function(){
		var seq = $(this).children("input[name=itemCode]").val();
		var name = $(this).children("input[name=itemName]").val();
		
		/*
		 *  현재 실 Data에서는 내려오는 값이 없어 임의의 "삼성" 이라는 값을 만들었습니다. 
		 */
		var url = "/v1.0/stocks/favorite/" + "삼성";
		console.log(url);
		$("#myLargeModalLabel").text(name + "을 포함하고 있는 가입가능한 펀드리스트입니다.");
		
		$.ajax(
		   {
		        url: url,
		        type: "GET",
		        success: function(msg) {
		        	console.log(JSON.parse(msg));
		        	var jsonData = JSON.parse(msg);
		        	var HTML = "";
		        	for(var i = 0; i < jsonData.length; i++) {
			            HTML += "<td align=center>" + jsonData[i].name + "</td>";
			            document.getElementById('ModalContents').innerHTML = HTML;
		        	}
		        	
		        	$("#LargeModalView").modal();
		        },error: function(e) {
		        	alert("서버와의 연결 도중 오류가 발생하였습니다. 다시 시도해 주십시오.");
		        }
		    }
		);
    });
});



