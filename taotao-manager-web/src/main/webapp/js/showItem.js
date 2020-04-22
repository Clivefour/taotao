$(function(){
	layui.use('table',function() {
		table = layui.table;
		table.render({
			elem : '#showItemPage',//绑定哪个table表 可以以id选择器绑定 可以以class选择器 还可以以 name选择器
			url : '/item/showItemPage',//请求服务器的url路径
			toolbar : '#toolbarDemo',//开启头部工具栏，并为其绑定左侧模板
			id : "reloadTable",
			defaultToolbar : [ 'filter', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
				layEvent : 'LAYTABLE_TIPS',
				icon : 'layui-icon-tips'
			} ],
			title : '商品表',
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
					  title : '商品名称',
					  width : 150
					 },
					 {
					  field : 'sellPoint',
					  title : '商品卖点',
					  width : 150
					 },
					 {
					  field : 'price',
					  title : '商品价格',
					  width : 100
					 },
					 {
					  field : 'num',
					  title : '商品数量',
					  width : 100
					 },
					 {
					  field : 'cId',
					  title : '商品类目',
					  width : 100
					 },
					 {
					  field : 'status',
					  title : '商品状态',
					  width : 100,
					  templet : '<div>{{# if(d.status ==0){ }} 下架  {{#  }  else if(d.status==1){ }} 上架 {{#  }  else if(d.status==2){ }} 删除 {{#  } }}</div>',
					  sort : true
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
					 }, 
					 {
					  fixed : 'right',
					  title : '操作',
					  toolbar : '#barDemo',
					  width : 150
					 } 
					 ] 
			        ],
					page : true
				});
				//头工具栏事件
table.on('toolbar(itemToolBar)',function(obj) {
	var checkStatus = table.checkStatus(obj.config.id);
		switch (obj.event) {
			case 'itemDelete':
				var data = checkStatus.data;
					$.ajax({
						type : "POST",
						url : "/item/itemDelete",
						contentType : "application/json;charset=utf-8",
						data : JSON.stringify(data),
						dataType : "json",
						success : function(message) {
						if (message.status == 200) {
							layer.alert('删除商品成功');
							table.reload('reloadTable',{});
						} else {
							layer.alert(message.msg);
						}
					}
				});
			break;
			case 'commodityUpperShelves':
				var data = checkStatus.data;
				$.ajax({
					type : "POST",
					url : "/item/commodityUpperShelves",
					contentType : "application/json;charset=utf-8",
					data : JSON.stringify(data),
					dataType : "json",
					success : function(message) {
					if (message.status == 200) {
						layer.alert(message.msg);
						table.reload('reloadTable',{});
					} else {
						layer.alert(message.msg);
					}
				}
			});
			break;
			case 'commodityLowerShelves':
				var data = checkStatus.data;
				$.ajax({
					type : "POST",
					url : "/item/commodityLowerShelves",
					contentType : "application/json;charset=utf-8",
					data : JSON.stringify(data),
					dataType : "json",
					success : function(message) {
					if (message.status == 200) {
						layer.alert(message.msg);
						table.reload('reloadTable',{});
					} else {
						layer.alert(message.msg);
					}
				}
			});
			break;
			};
		});

	});
})

$("#search").click(function() {
	var titleVal = $("#searchTitile").val();
	var priceMinVal = $("#priceMin").val();
	var priceMaxVal = $("#priceMax").val();
	var cidContentVal = $("#cidContent").val();
	table.reload('reloadTable', {
		url : '/item/searchItem',
		method : "post",
		where : {
			title : titleVal,
			priceMin : priceMinVal,
			priceMax : priceMaxVal,
			cId : cidContentVal
		},
		page : {
			curr : 1
		}
	});
	$("#searchTitile").val("");
	$("#priceMin").val("");
	$("#priceMax").val("");
	$("#cidContent").val("");
	$("#selectCid").val("");
})

$("#selectCid").click(function() {
	layer.open({
		type: 2,
		title: '商品分类选择',
		shadeClose: true,
		shade: 0.8,
		area: ['380px', '90%'],
	    content: '/jsp/showZtree.jsp', 
		btn: ['确定','取消'], //按钮
		success:function (layero,index) {
		     var iframe = window['layui-layer-iframe' + index];
		     iframe.child('showItem.jsp');
		}
	}); 
})
