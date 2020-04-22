<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品展示页面</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/showItem.js"></script>
</head>
<body>
	<div style="padding: 15px;background-color: #FFFFFF">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">商品名称</label>
				<div class="layui-input-inline">
					<input id="searchTitile" type="text" name="title"
						placeholder="请输入商品名称" class="layui-input">
				</div>
			</div>

			<div class="layui-inline">
				<label class="layui-form-label">范围</label>
				<div class="layui-input-inline" style="width: 100px;">
					<input type="text" id="priceMin" placeholder="￥:0"
						autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline" style="width: 100px;">
					<input type="text" id="priceMax" placeholder="￥:10000000"
						autocomplete="off" class="layui-input">
				</div>
			</div>

			<div class="layui-inline">
				<label class="layui-form-label">商品分类</label>
				<div class="layui-input-inline">
					<input id="selectCid" type="text" placeholder="请选择商品名称"
						class="layui-input">
				</div>
				<input type="hidden" id="cidContent" name="cId" />
			</div>
			<button id="search" type="button" class="layui-btn layui-btn-radius">点击搜索</button>
		</div>
		<table class="layui-hide" id="showItemPage" lay-filter="itemToolBar"></table>

		<div id="toolbarDemo" style="display: none;"
			class="layui-btn-container">
			<button class="layui-btn layui-btn-sm" lay-event="itemDelete">选中删除</button>
			<button class="layui-btn layui-btn-sm" lay-event="addItem">新增商品</button>
			<button class="layui-btn layui-btn-sm" lay-event="updateItem">修改商品</button>
			<button class="layui-btn layui-btn-sm"
				lay-event="commodityUpperShelves">商品上架</button>
			<button class="layui-btn layui-btn-sm"
				lay-event="commodityLowerShelves">商品下架</button>
		</div>
		<div style="display: none;" id="barDemo">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a> <a
				class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</div>
	</div>
</body>
</html>