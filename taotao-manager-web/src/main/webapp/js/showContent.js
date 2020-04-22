var table;
var categoryIdVal;
$(function(){
	layui.use('table',function() {
		table = layui.table;
		table.render({
			elem : '#showContentTable',//绑定哪个table表 可以以id选择器绑定 可以以class选择器 还可以以 name选择器
			url : '/content/showContentTable',//请求服务器的url路径
			toolbar : '#toolbarContent',//开启头部工具栏，并为其绑定左侧模板
			id : "reloadContentTable",
			defaultToolbar : [ 'filter', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
				layEvent : 'LAYTABLE_TIPS',
				icon : 'layui-icon-tips'
			} ],
			title : '内容表',
			cols : [
			        [
			         {
					  type : 'checkbox',
					  fixed : 'left'
					 },
					 {
					  field : 'id',
					  title : 'ID',
					  width : 80,
					  fixed : 'left'
					 },
					 {
					  field : 'title',
					  title : '内容标题',
					  width : 150
					 },
					 {
					  field : 'subTitle',
					  title : '内容子标题',
					  width : 150
					 },
					 {
					  field : 'titleDesc',
					  title : '内容描述',
					  width : 100
					 },
					 {
					  field : 'url',
					  title : '内容链接',
					  width : 100
					 },
					 {
					  field : 'pic',
					  title : '图片',
					  width : 100
					 },
					 {
					  field : 'pic2',
					  title : '图片2',
					  width : 100
					 },
					 {
					  field : 'created',
					  title : '创建时间',
					  templet : '<div>{{ layui.util.toDateString(d.created, "yyyy-MM-dd HH:mm:ss") }}</div>',
					  width : 200
					 },
					 {
					  field : 'updated',
					  title : '更新时间',
					  templet : '<div>{{ layui.util.toDateString(d.updated, "yyyy-MM-dd HH:mm:ss") }}</div>',
					  width : 200
					 }
					 ] 
			        ],
					page : true
				});
		table.on('toolbar(contentToolBar)',function(obj) {
			var checkStatus = table.checkStatus(obj.config.id);
				switch (obj.event) {
					/*
					 * 因为ajax这边 有contentType 值为application/json;charset=utf-8
					 * 所以 ajax页面这边传递给java的是json 
					 * 而java要接受json数据 必须使用@RequestBody来接受
					 * */
					case 'deleteContent':
						var data = checkStatus.data;
						console.log("点击了批量删除按钮");
							$.ajax({
								type : "POST",
								url : "/content/deleteContentByCategoryId",
								contentType : "application/json;charset=utf-8",
								data : JSON.stringify(data),
								dataType : "json",
								success : function(message) {
									layer.alert('删除商品成功');
									table.reload('reloadContentTable',{
										where:{
											categoryId:categoryIdVal,
										},
										page:{
											page:1
										}
									});
							}
						});
						break;
					case 'addContent':
						 layer.open({
							type : 2,
							title : '添加内容',
							shadeClose : true,
							shade : 0.8,
							area : [ '70%', '90%' ],
							content : '/jsp/showAddContent.jsp',
							success : function(layero, index) {
								var iframe = window['layui-layer-iframe' + index];
								iframe.child(categoryIdVal,table,layer);
							}
						});
						break;
					};
				});
		});	
	
	
	var setting = {
			async: {
				enable: true,
				url:"/content/showContentZtree",
				autoParam: ["id"]	
			},
			callback: {
				onClick: zTreeOnClick
			}
		};
		$.fn.zTree.init($("#showContentTree"), setting);
})
function zTreeOnClick(event, treeId, treeNode) {
	if(treeNode.isParent==false){
		/*
		 *  如果页面写了这句话 contentType : "application/json;charset=utf-8",
		 *  java这边 必须使用@RequestBody来接受数据
		 *  如果没有写 则可以直接接受
		 *  页面的传递 就是 name=categoryId value=真正的id来传值
		 *  所以java可以直接接受值
		 * */
		categoryIdVal = treeNode.id;
		$.ajax({
				type : "POST",
				url : "/content/showContentTable",
				data : "categoryId="+categoryIdVal+"&page=1&limit=1",
				dataType : "json",
				success : function(message) {
				table.reload('reloadContentTable',{
					where:{
						categoryId:categoryIdVal,
					},
					page:{
						page:1
					}
				});
			}
		});
	}
};