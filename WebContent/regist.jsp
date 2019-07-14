<%@ page language="java" contentType="text/html; charset=utf-8" import="xin.trooper.storage.domain.RealFile,xin.trooper.storage.domain.Users"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>顺顺网盘</title>
    <link rel="stylesheet" href="css/common.css"/>
    <link rel="stylesheet" href="css/register.css"/>
</head>
<body>
<div class="box">
    <div class="nav cl">
        <div class="nav_z z">
            <img class="drivelogo" src="images/weblogo.png" style="width: 225px;margin-top: -14px;margin-left: -20px;"/>
        </div>
        <!--<div class="nav_y y">-->
            <!--<a href="">关于我们</a>-->
            <!--<span>丨</span>-->
            <!--<a href="">联系我们</a>-->
        <!--</div>-->
    </div>
    <div class="main cl">
        <div class="main_z z">
            <img src="images/register02.png"/>
        </div>
        <div class="main_y y">
            <div class="main_y_title">
                欢迎使用
            </div>
            <div class="meta"></div>
            <div class="main_y_content">
				<form action="${pageContext.request.contextPath }/user_regist.action" method="post">
                <div class="main_input">
                    <input type="text" placeholder="您的姓名" class="phone" name="user_name" maxlength="11" id="username"/>
                    <img src="images/register03.png"/>
                </div>
                <div class="main_input">
                    <input type="text" placeholder="您的账号" class="account" name="user_account" maxlength="11" id="account"/>
                    <img src="images/register03.png"/>
                </div>
                <div class="main_input">
                    <input type="password" placeholder="您的密码" class="password" name="user_password" maxlength="18" id="pwd"/>
                    <img src="images/register03.png"/>
                </div>
                </form>
<!--                 <div class="remember_password">
                    <input type="checkbox" checked="checked" name="checked" class="examine"/>
                    <span>记住密码</span>
                </div> -->
                <a>
                    <div class="register" id="register">
                        注册
                    </div>
                </a>
                <!--<div class="main_y_content_buttom">-->
                    <!--<a href="">忘记密码?</a>-->
                <!--</div>-->
            </div>
        </div>
    </div>
    <div class="footer">
        2016 youlianyun inc. All Rights Reserved
    </div>
</div>
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/register.js"></script>
<script>

/*    $(".phone").blur(function () {
//                    电话号码验证
        var sw = /^(1)[3-9]{1}[0-9]{9}$/;//验证手机号
//                    var str=17757425590;
//                    alert(sw.test(str));
        var valu = sw.test(parseInt($(this).val()));
        if ($(this).val().length == 0 || valu == false) {
            $('.meta').text('用户名或者密码错误，重新输入后再次尝试');
        }
        else {
            $('.meta').text('');
        }
    });
*/    
    $(function () {
        //点击注册事件（点击注册时调用的方法）
        $("#register").click(function (e) {
            e.preventDefault();  //阻止a标签的默认事件
            //获取username用户名数据
            var username = $("#username").val();
            //获取密码数据
            var pwd = $("#pwd").val();

            //获取用户账号
            var account = $("#account").val();
			//你来
            //这三个是要传给后台的值
            console.log(username)
            console.log(pwd)
            console.log(account)
			$("form").submit();
            //注册成功跳转index.html页面
            //这里假数据不做任何判断直接跳转
           	//window.location.href="/webstorage/user_regist.action?user_name="+encodeURI(encodeURI(username))+"&user_password="+pwd+"&user_account="+account;
             
			//实际调用ajax和后台交互
			
/*             $.ajax({
                url:"user_regist.action",
                type:"post",
               // dataType:"json",  //不是json的话，删除这里
                data:  {
                   user_account:account,
                   user_name:username,
             	   user_password:pwd
                },  //这里是传参数的地方
                async: false,
                success: function(data){
                	console.log(data)
                	//window.location.href="file.html";
                    // 这里接收后台数据，成功跳转主页
                },
                error:function(){
                    alert("注册失败")
                } //error结束

            }); */

        })


    })


</script>

</body>
</html>
