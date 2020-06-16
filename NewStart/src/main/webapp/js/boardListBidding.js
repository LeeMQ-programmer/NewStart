
function reloadx(){
	window.location.reload();
}

// 이거하나로 수정페이지로 이동시키기
function bidding(val){
	location.href = "./biddingPage.do?gonggo_seq="+val;
}


function biddingList(val){
	
	$.ajax({
		url:"./getBiddingList.do",
		type:"post",
		dataType:"json",
		data:{"gonggo_seq":val},
		success:function(map_L){
			var list = map_L.lists;
			html = "<div>"
			for (var i = 0; i < list.length; i++) {
				html += "<input type='text' name='title' id='title' value='"+ list[i]['biddinger'] + '&nbsp:&nbsp' + list[i]['biddinger_w'] +"' readonly='readonly' style='width:100%'>";		
				html += "<button onclick='BW_Select("+val+")'>입찰자 선택하기 </button>";
			}
			html += "</div>"
			alert("val : " + val);
			$("#biddingList"+val+"").html(html);
			//location.href = "./gonggo.do";
			$('body').click(function(){
				setTimeout(function(){
					// 1초 후 작동해야할 코드
					window.location.reload();
				}, 2000);
			});
		},
		error: function(){
			alert("잘못된 요청입니다.");
		}
	});
}



function BW_Select(val){
	location.href = "./bwl_insert.do?gonggo_seq="+val;
}


window.onload = function(){
	
	var val = $('input[name="Gonggo_seq"]');
	var biddingOX = [];
	var bidding_btn = [];
	var indexNum = [];
	var n = 0;
	for (var i = 0; i < val.length; i++) {
		var gonggo_seq = $('input[name="Gonggo_seq"]').eq(i).val(); // 전체 
		var biddingList = $('div[name="biddingList"]').eq(i).children('input').attr("name", 'biddingOX'+ gonggo_seq +'"').val();
		if(biddingList != null){
			biddingOX[n] = 'biddingOX'+gonggo_seq;
			bidding_btn[n] = 'bidding_btn'+gonggo_seq;
			indexNum[n] = i;
			++n;
		}
	}
	n = 0;
		$.ajax({
			url:"./getBWLList.do",
			type:"post",
			dataType:"json",
			data:{"gonggo_seq":gonggo_seq},
			success:function(map_OX){
				if(map_OX.result){
					for(var j = 0; j < biddingOX.length; j++){
						var div_input = $('div[name="biddingList"]').eq(indexNum[j])
						var div_input1 = div_input.children('input')
						var biddingOX_input = div_input1.attr("name", biddingOX[j]);
						var bidding_btn_input = div_input1.attr("name", bidding_btn[j]);
						//biddingOX_input.css('display', 'none');
						//bidding_btn_input.css('display', 'none');
					}
				}
			},
			error: function(){
				alert("잘못된 요청입니다.");
			}
		});
	
}
