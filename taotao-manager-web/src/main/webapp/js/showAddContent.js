var table;
var categoryIdVal;
$(function(){
	//初始化富文本编辑器
	var editor = window.editor = KindEditor.create("#addContentEditor");
	layui.use([ 'form', 'layedit', 'laydate', 'upload' ],function(){
		var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate, upload = layui.upload;
		form.on('submit(addContent)',function(data){
			editorVal = editor.html();
			//绑定描述信息
			$.ajax({
		        type: "POST",
		        url: "/content/addContent",
		        data:"title="+data.field.title+"&titleDesc="+editorVal+"&categoryId="+data.field.categoryId+"&subTitle="+data.field.subTitle+"&url="+data.field.url+"&pic="+$("#image1").val()+"&pic2="+$("#image2").val(),
		        dataType: "json",
		        success:function (message) {
		        	layer.alert(message.msg);
		        	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		        	parent.layer.close(index);
		        	//这条代码已经ok了 
		        	table.reload('reloadContentTable',{
		        		where:{
							categoryId:categoryIdVal,
						},
						page:{
							page:1
						}
		        	});
		        },
		        error:function (message) {
		        	layer.alert(message.msg);
		        }
		    });
		});
		//上传图片的代码
		upload
		.render({
			elem : '#pic',
			url : '/item/fileUpload' //改成您自己的上传接口
				,
				multiple : true,
				before : function(obj) {
					//预读本地文件示例，不支持ie8
					obj.preview(function(index,file, result) {
						$('#demo2').append('<img style="height: 100px;width: 100px" src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')});
				},
				done : function(res) {
					/*
					两种可能行 
					1.返回的结果集 必须按照某一种规格的json格式返回
					 */
					$("#image1").val(res.data.src);
				}
		});
		//上传图片的代码
		upload
		.render({
			elem : '#pic2',
			url : '/item/fileUpload' //改成您自己的上传接口
				,
				multiple : true,
				before : function(obj) {
					//预读本地文件示例，不支持ie8
					obj
					.preview(function(index,
							file, result) {
						$('#demo3')
						.append(
								'<img style="height: 100px;width: 100px" src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
					});
				},
				done : function(res) {
					/*
			两种可能行 
			1.返回的结果集 必须按照某一种规格的json格式返回
					 */
					$("#image2").val(res.data.src);
				}
		});

	});
})
//用于绑定categoryId的方法
function child(obj,t,i){
	table = t;
	index = i;
	categoryIdVal = obj;
	/**
	 * 这里的代码 和之前的代码不一样
	 * parent.$("#selectCid").val(treeNode.name); 之前的代码为 这个
	 * 因为原来的页面 是  父子关系 ，现在的页面没有父子关系 所以不用这样搞
	 */
	$("#contentCategoryId").val(categoryIdVal);
}