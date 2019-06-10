$(function(){
	 //需要从OC那里拿值，之后会触发OC的sendValueToHtml方法
	function getValueFromOC(){
	    native.sendValueToHtml();
	}
//	mounted(){
//		window.getUserNameAndAge = this.getUserNameAndAge
//	}
	//接收从OC传过来的值，需要OC调用该方法，并传入值
	function getUserNameAndAge(androidrid,androiduid,androidpid){
	    alert('rId:'+rId+' '+'uId:'+uId+''+'pId:'+pId);
		rId = androidrid;
		uId = androiduid;
		pId = androidpid;
	}
	rId = androidrid;
	uId = androiduid;
	pId = androidpid;
	
	$("#rId").val(rId);
	$("#uId").val(uId);
	$("#pId").val(pId);
})
$(".img_k").on("change","li input",function(){//添加图片事件
	index = $(this).parent("li").index();
	var file = this.files[0];
	// 确认选择的文件是图片     
	if(file.type.indexOf("image") == 0) {
			var reader = new FileReader();
			reader.readAsDataURL(file);
			reader.onload = function(e) {
					// 图片base64化
					var newUrl = this.result;
					$(".img_k li").eq(index).css("background-image",'url(' + newUrl + ')');//LI添加图片路径
					$(".img_k li").eq(index).children("p").html('');//当前的文字清空
					$(".img_k li").eq(index).children("img").attr("src","img/close.png");//当前的添加清除按钮
					var nextNumber = index+2;
					var number = $(".img_k li").length;
					//console.log("我是第一个数字"+nextNumber+"我是第二个数字"+number)
					if(number<4 && nextNumber>number){
						var next = '<li class="">'
										+'<input type="file" name="files" accept="" />'
										+'<p>'+nextNumber+'/4</p>'
										+'<img class="close" src="" alt="">'
									+'</li>'
						$('.img_k').append(next);
					}
			};
	}
});
$(".img_k").on("click","li img",function(){//关闭按钮事件
	var index = $(this).parent("li").index();
	$(".img_k li").eq(index).remove();
	var next = index+1;
	if($(".img_k li").last().css("background-image").indexOf("imgBg.png")==-1 || number==0){
		var next = '<li class="">'
						+'<input type="file" name="files" accept="" />'
						+'<p>4/4</p>'
						+'<img class="close" src="" alt="">'
					+'</li>'
		$('.img_k:last').append(next);
	}
	var number = $(".img_k li").length;
	if(number!=4 || $(".img_k li").last().css("background-image").indexOf("imgBg.png")!=-1 && number==4){
		$(".img_k li").last().children("p").html(number+'/4');
	}
	
	
});
$(".submit").click(function(){
	var textareaText = $("textarea").text();
	console.log(textareaText);
	$.ajax({
		type:"post",
		url:"http://abc.smnn.top:8082/post/addReport",
		data:$('#form1').serialize(),
		success:function(e){
			console.log(e);//打印服务端返回的数据(调试用)
			if (e.message == "举报成功") {
				alert("举报成功");
			};
		},
		error:function(e){
			console.log(e);
		}
	});
	
});