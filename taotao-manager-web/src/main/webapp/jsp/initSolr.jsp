<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品分类</title>
</head>
<body>
<div style="padding: 15px; background-color: #FFFFFF">
    <button id="initSolrButton" type="button" class="layui-btn layui-btn-normal">一键导入solr索引库</button>
</div>
<script type="text/javascript">
    $(function () {
        var layer;
        layui.use('layer', function(){
            layer = layui.layer;


        });
        $("#initSolrButton").click(function () {
            $.ajax({
                type: "get",
                url: "/search/importSolr",
                success: function(msg){
                    layer.alert(msg.msg);
                }
            });

        });
    })


</script>
</body>
</html>