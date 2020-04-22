<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>淘淘商城后台</title>
<style type="text/css">
body, html, #allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
<script type="text/javascript"
	src="//api.map.baidu.com/api?v=2.0&ak=6x4Mzp8ArLpNLD1UgtPT3694bUScigwc"></script>
<script src="${pageContext.request.contextPath }/layui/layui.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-2.1.0.min.js"></script>
<script
	src="${pageContext.request.contextPath }/js/echarts-en.common.min.js"></script>
<script src="${pageContext.request.contextPath }/js/index.js"></script>

</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">淘淘商城后台</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->

			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="http://t.cn/RCzsdCq" class="layui-nav-img"> admin
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">个人中心</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="">注销</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item"><a class="" href="javascript:;">商品管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a id="addItemCat" href="javascript:;">商品分类添加</a>
							</dd>
							<dd>
								<a href="javascript:;">商品分类查询</a>
							</dd>
							<dd>
								<a id="addItem" href="javascript:;">商品添加</a>
							</dd>
							<dd>
								<a id="showItem" href="javascript:;">商品查询</a>
							</dd>
							<dd>
								<a id="addParam" href="javascript:;">商品规格参数模板添加</a>
							</dd>
							<dd>
								<a href="javascript:;">商品规格参数模板查询</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">cms内容管理系统</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">内容分类管理</a>
							</dd>
							<dd>
								<a id="showContent" href="javascript:;">内容管理</a>
							</dd>
							
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">订单管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">查询订单</a>
							</dd>
							<dd>
								<a href="javascript:;">订单统计</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">用户管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">查询用户</a>
							</dd>
							<dd>
								<a href="javascript:;">用户统计</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">权限管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">查看权限</a>
							</dd>
							<dd>
								<a href="javascript:;">分配权限</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;">活动管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">新增活动</a>
							</dd>
							<dd>
								<a href="javascript:;">查看活动</a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<div id="content" style="background-color: #F2F2F2; height: 100%">
				<div style="padding: 20px; background-color: #F2F2F2;">
					<div class="layui-row layui-col-space15">
						<div class="layui-col-md3">
							<div class="layui-card">
								<div class="layui-card-header">
									<div>
										<span>访问量</span> <span style="float: right; margin-top: 13px"
											class="layui-badge layui-bg-blue">周</span>
									</div>
								</div>
								<div class="layui-card-body">
									<span style="font-size: 36px; color: #676767">99999</span></br> </br> <span
										style="color: #676767">总访问量</span> <i style="float: right;"
										class="layui-icon layui-icon-flag"></i>
								</div>
							</div>
						</div>
						<div class="layui-col-md3">
							<div class="layui-card">
								<div class="layui-card-header">
									<span>每天收入 </span> <span style="float: right; margin-top: 13px"
										class="layui-badge layui-bg-orange">天</span>
								</div>

								<div class="layui-card-body">
									<span style="font-size: 36px; color: #676767">5000</span></br> </br> <span
										style="color: #676767">总收入</span> <i style="float: right;"
										class="layui-icon layui-icon-rmb"></i>
								</div>
							</div>
						</div>
						<div class="layui-col-md3">
							<div class="layui-card">
								<div class="layui-card-header">
									<div>
										<span>用户总数</span> <span style="float: right; margin-top: 13px"
											class="layui-badge layui-bg-green">月</span>
									</div>
								</div>
								<div class="layui-card-body">
									<span style="font-size: 36px; color: #676767">8000</span></br> </br> <span
										style="color: #676767">总用户数</span> <i style="float: right;"
										class="layui-icon layui-icon-user"></i>
								</div>
							</div>
						</div>
						<div class="layui-col-md3">
							<div class="layui-card">
								<div class="layui-card-header">
									<div>
										<span>新增用户</span> <span style="float: right; margin-top: 13px"
											class="layui-badge">天</span>
									</div>
								</div>
								<div class="layui-card-body">
									<span style="font-size: 36px; color: #676767">16</span></br> </br> <span
										style="color: #676767">新增人数</span> <i style="float: right;"
										class="layui-icon layui-icon-user"></i>
								</div>
							</div>
						</div>
						<div class="layui-col-md12">
							<div class="layui-col-md6">
								<div class="layui-card">
									<div class="layui-card-header">商品分类统计</div>
									<div class="layui-card-body">
										<div id="echartsMain1" style="width: 400px; height: 400px;"></div>
									</div>
								</div>
							</div>
							<div class="layui-col-md6">
								<div class="layui-card">
									<div class="layui-card-header">当前地址</div>
									<div class="layui-card-body">
										<div style="width: 400px; height: 400px" id="allmap"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="layui-footer" style="background-color: #2F4056">
			<span style="color: #01AAED">©www.clive.com - 淘淘商城</span>
		</div>
	</div>
	<script type="text/javascript">
		var myChart1 = echarts.init(document.getElementById('echartsMain1'));
		var weatherIcons = {};
		$.get('/itemCat/statisticsItem').done(function(resule) {
			myChart1.setOption({
				title : {
					text : '商品分类统计',
					left : 'center'
				},
				tooltip : {
					trigger : 'item',
					formatter : '{a} <br/>{b} : {c} ({d}%)'
				},
				series : [ {
					type : 'pie',
					radius : '65%',
					center : [ '50%', '50%' ],
					selectedMode : 'single',
					data : resule
				} ]
			});
		})

		//	百度地图API功能
		var map = new BMap.Map("allmap"); // 创建Map实例
		map.centerAndZoom(new BMap.Point(104.06, 30.67), 11); // 初始化地图,设置中心点坐标和地图级别
		//	添加地图类型控件
		map.addControl(new BMap.MapTypeControl({
			mapTypes : [ BMAP_NORMAL_MAP, BMAP_HYBRID_MAP ]
		}));
		map.setCurrentCity("成都"); // 设置地图显示的城市 此项是必须设置的
		map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
	</script>

</body>
</html>

