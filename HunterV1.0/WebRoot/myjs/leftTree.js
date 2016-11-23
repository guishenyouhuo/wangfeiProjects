$(function(){
		$("div.pnav-box div.box-title a.btn-fold").click(function(){
			var parentNode=$(this).parent();
			$(this).addClass("hidden");
			$("#"+parentNode.attr("id")+" .btn-unfold").removeClass("hidden");	
			$("#"+parentNode.parent().attr("id")+" ul").removeClass("hidden");
		});
		$("div.pnav-box div.box-title a.btn-unfold").click(function(){
			var parentNode=$(this).parent();
			$(this).addClass("hidden");
			$("#"+parentNode.attr("id")+" .btn-fold").removeClass("hidden");	
			$("#"+parentNode.parent().attr("id")+" ul").addClass("hidden");
		});
		$("div.pnav-box div.box-title .pnav-letter").click(function(){
		var parentNode=$(this).parent();
		var parentId=parentNode.attr("id");
		var unfold=$("#"+parentId+" #unfold");
		var fold=$("#"+parentId+" #fold");
		var unfoldClass=unfold.attr("class");
		if(unfoldClass.split(" ").length==1){
			unfold.addClass("hidden");
			fold.removeClass("hidden");
			$("#"+parentNode.parent().attr("id")+" ul").addClass("hidden");
		}
		else {
			fold.addClass("hidden");
			unfold.removeClass("hidden");
			$("#"+parentNode.parent().attr("id")+" ul").removeClass("hidden");
			}
		});
	});