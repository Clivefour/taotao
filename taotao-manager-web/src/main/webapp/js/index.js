$(function() {
	//JavaScript代码区域
	layui.use('element', function(){
		var element = layui.element;

	});
	$("#showItem").click(function(){
		$("#content").load("/jsp/showItem.jsp");
	})
	$("#addItemCat").click(function(){
		$("#content").load("/jsp/addItemCat.jsp");
	})
	$("#addItem").click(function(){
		$("#content").load("/jsp/addItem.jsp");
	})
	$("#addParam").click(function() {
		$("#content").load("/jsp/addParam.jsp");
	})
	$("#showContent").click(function(){
		$("#content").load("/jsp/showContent.jsp");
	})

})
	
