var TT = TAOTAO = {
    checkLogin : function(){
        //使用jQuery的方法  从 cookie 的key为TT_TOKEN里面取值
        var _ticket = $.cookie("TT_TOKEN");
        if(!_ticket){
            return ;
        }
        $.ajax({
            url : "http://localhost:8088/user/token/" + _ticket,
            dataType : "jsonp",
            type : "GET",
            success : function(data){
                if(data.status == 200){
                    var userName = data.data.userName;
                    var html = userName + "，欢迎来到淘淘！<a href='http://localhost:8088/user/logout/"+_ticket+"' class='link-logout'>[退出]</a>";
                    $("#loginbar").html(html);
                }
            }
        });
    }
}

$(function(){
    // 查看是否已经登录，如果已经登录查询登录信息
    TT.checkLogin();
});