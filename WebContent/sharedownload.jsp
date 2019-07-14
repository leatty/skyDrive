<%@ page language="java" contentType="text/html; charset=utf-8" import="xin.trooper.storage.domain.Users"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%-- <%

Users existUser = (Users)request.getSession().getAttribute("loginedUser");
%>  --%>   

   

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>顺顺网盘</title>
    <link rel="stylesheet" href="css/common.css" type="text/css"/>
    <link rel="stylesheet" href="css/index.css" type="text/css"/>
    <link rel="stylesheet" href="js/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/my.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="page/base.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="page/pageGroup.css" type="text/css" media="screen"/>

    
    <style>
        body{
        background-color: #eee;
        }
        btnhidden{
        display: none;
        }
    </style>
</head>
<!--顶部-->
<div class="nav" style="z-index: 10000">
    <div class="nav cl" style="z-index: 10000000">
        <div class="nav_z z">
            <a><img class="drivelogo" src="images/weblogo.png" style="width: 225px;margin-top: -14px;margin-left: -20px;"/></a>
        </div>

        <div class="nav_y y">
            <!--<a href="">关于我们</a>-->
            <!--<span>丨</span>-->
            <!--<a href="">联系我们</a>-->
            <button type="button" class="btn btn-primary" id="logoffbtn">注销</button>
        </div>
        <div class="userNumber y">
            你好,<span id="userName"><s:property value="#session.username"/></span>
        </div>
    </div>

</div>
<!--顶部结束-->
    <!--中间部分开始-->
    <div style="width:100%;height:935px;padding-top: 80px;">
    <div style="width:60%;height:500px;border:1px solid #ddd;background-color: #fff;margin-left:20%;margin-top:5%;">
    <div class="row">
    <div class="col-md-9">
    <div class="row" style="margin-top:40px;margin-left:10px;">
    <div class="col-md-4">
    <img src="./images/ys.png" alt="" style="width:25px;"> <span><s:property value="virfile_name"/></span>
    </div>
    <div class="col-md-8" style="text-align: right">
    <button type="button" class="btn btn-default" style="margin-right:20px;"><i class="glyphicon glyphicon-ban-circle"></i> 取消分享</button>

    <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
    <button type="button" id="downloadbtn" class="btn btn-primary" style="margin-right:20px;"><i class="glyphicon glyphicon-save"></i> 下载</button>

    <!-- Indicates a successful or positive action -->
    <button type="button" class="btn btn-success"><i class="glyphicon glyphicon-qrcode"></i> 保存到手机</button>
    </div>

    </div>
    <div>
    <p style="color: #9a9898;  margin-top: 30px;  margin-left: 20px"><i></i><span>2019-06-14 15:20</span> <span>失效时间：7天后</span></p>
    </div>

    <div style="width:98%;height:340px;background-color: #F6F9FD;margin-left:20px;margin-top:20px;text-align: center">
    <img src="./images/ys.png" alt="" style="margin-top:50px;">
    <p id="filesize" style="text-align: center;margin:20px 0;">文件大小（<s:property value="virfile_size"/>）</p>
    <button type="button" class="btn btn-primary">打开文件</button>

    </div>
    </div>
    <div class="col-md-3">
    <div style="width:100%;height: 500px;background-color: #F3F8FF">
    <div style="margin-left:20px;padding-top:20px;">
    <img src="./images/head.jpg" alt="" style="width:50px;height:50px;border-radius: 50%;">
    <span style="display: inline-block;height:50px;line-height: 50px;margin-left:10px;">xxxxxx</span>
    <p style="margin-top: 10px;color: #897c7c">说明：你还没有个人简介呢！</p>
    </div>
    </div>
    </div>
    </div>



    </div>
    </div>
	<input type="hidden" id="virfile_id" value=<s:property value="virfile_id"/>>
    <!--中间部分结束-->
<body>
<!--<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>-->
<script type="text/javascript" src="js/jquery.min.js"></script>
<%-- <script type="text/javascript" src="page/pageGroup.js"></script>  --%>
<script src="js/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>


 <script type="text/javascript">
	 $(document).ready(function(){
		 if($("#userName").text() == "请登录"){
			//$("#logoffbtn").addClass("btnhidden");
			$("#logoffbtn").hide();
			$("#userName").text("");
			$("#userName").append("<a href='login.jsp'>请登录</a>");
		}else{
			$(".drivelogo").parent().attr("href","/webstorage/storage_shareView.action"); 
		}
		 var sizeOfMB = '<s:property value="virfile_size"/>';
		  sizeOfMB = sizeOfMB/1024/1024;
		  sizeOfMB = sizeOfMB.toFixed(2);
		  sizeOfMB = sizeOfMB + " MB";
		 $("#filesize").text(sizeOfMB);
	 });
	 $("#downloadbtn").click(function () {
		 var virfile_id = $("#virfile_id").attr("value");
	     console.log(virfile_id);
	     window.location.href="storage_download.action?virfile_id=" + virfile_id;
	 })
	 $("#logoffbtn").click(function(){
		 window.location.href="user_logoff.action";
	 });
	 
</script>
</body>
</html>
