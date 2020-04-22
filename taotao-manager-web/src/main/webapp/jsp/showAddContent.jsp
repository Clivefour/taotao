<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加内容</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/layui/css/layui.css">
<script src="${pageContext.request.contextPath }/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.0.min.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8"
	src="${pageContext.request.contextPath}/js/showAddContent.js"></script>
</head>
<body>
		<div style="padding: 15px;background-color: #FFFFFF">
			<form class="layui-form">
				
				<div class="layui-form-item">
					<label class="layui-form-label">内容标题</label>
					<div class="layui-input-inline">
						<input type="text" name="title" class="layui-input">
						<input type="hidden" id="contentCategoryId" name="categoryId"/>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">内容子标题</label>
					<div class="layui-input-inline">
						<input type="text" name="subTitle" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">内容链接</label>
					<div class="layui-input-inline">
						<input type="text" name="url" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">内容图片1</label>
					<div class="layui-input-block">
						<div class="layui-upload">
							<button type="button" class="layui-btn" id="pic">上传图片</button>
							<blockquote class="layui-elem-quote layui-quote-nm"
								style="margin-top: 10px;">
								预览图：
								<div class="layui-upload-list" id="demo2"></div>
							</blockquote>
						</div>
						<input type="hidden" id="image1" name="pic">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">内容图片2</label>
					<div class="layui-input-block">
						<div class="layui-upload">
							<button type="button" class="layui-btn" id="pic2">上传图片</button>
							<blockquote class="layui-elem-quote layui-quote-nm"
								style="margin-top: 10px;">
								预览图：
								<div class="layui-upload-list" id="demo3"></div>
							</blockquote>
							<input type="hidden" id="image2" name="pic2">
							
						</div>
					</div>
				</div>
				
				
				<div class="layui-form-item">
					<label class="layui-form-label">内容描述</label>
					<div class="layui-input-inline">
						<textarea id="addContentEditor" name="titleDesc"
							style="width: 700px; height: 300px;">
						</textarea>
					</div>
				</div>
					
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button type="submit" class="layui-btn" lay-submit=""
							lay-filter="addContent">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>
</body>
</html>