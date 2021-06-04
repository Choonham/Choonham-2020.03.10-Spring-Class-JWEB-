/**
 *  좋아요를 누른 사람의 목록 추출
 */

function closeLayer() {
	$(".likeList").hide();

}

$(function(){
	$('.like_input').mouseover(function(e)
		{
			var param = $('.param').val();
			var index = $(".like_input").index(this); //몇번쨰 인덱스인지 확인
			
			var i =parseInt(index) + parseInt(param);
			
			var sWidth = window.innerWidth;
			var sHeight = window.innerHeight;
	
			var oWidth = $('#like_list'+index).width();
			var oHeight = $('#like_list'+index).height();
	
			var divLeft = e.pageX + 10;
			var divTop = e.pageY + 5;
	
			if( divLeft < 0 ) divLeft = 0;
			if( divTop < 0 ) divTop = 0;
	
			
			$('#like_list'+i).css({
				"top": divTop,
				"left": divLeft,
				"position": "absolute"
			}).show();
		});
});