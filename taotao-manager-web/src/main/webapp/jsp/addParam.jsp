<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加规格参数</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/addParam.js"></script>
</head>
<body>
<div style="padding: 15px; background-color: #FFFFFF;height: 100%">
	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">商品类目</label>
			<div class="layui-input-block">
				<button id="findGroup" type="button"
					class="layui-btn layui-btn-radius">选择类目</button>
				<span style="display: none;" id="addParamSpan"></span> <input
					id="cIdParam" type="hidden" name="cId" />
			</div>
		</div>
		
		<div id="paramTemplate" style="display: none;"  class="layui-form-item">
			<label class="layui-form-label">规格模板</label>
			<div class="layui-input-block">
				<button id="addParamGroup" type="button"
					class="layui-btn layui-btn-radius">添加规格参数组</button>
			</div>
		</div>
		<div id="groupAndKey">
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
					<button type="submit" class="layui-btn" lay-submit=""
						lay-filter="saveParam">保存规格参数</button>
					<button type="reset" class="layui-btn layui-btn-primary">取消</button>
			</div>
		</div>
	</form>


</div>
</body>
</html>