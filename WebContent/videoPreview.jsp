<%@ page language="java" contentType="text/html; charset=utf-8" import="xin.trooper.storage.domain.Users"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
   

   

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
    <link href="js/video-js/video-js.css" rel="stylesheet">
    <style>
        body{
        background-color: #eee;
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
            <button id="logoffbtn" type="button" class="btn btn-primary" id="logoff">注销</button>
        </div>
        	
        <div class="userNumber y">
        <img alt="" src="images/user.png" style="margin-top: 29px; margin-right: 12px;">
            你好,<span id="userName"><s:property value="#session.username"/></span>
        </div>
    </div>
<input id="virfile_id" hidden="true" type="text" value=<s:property value="virfile_id"/>><s:property value="virfile_id"/></input>
</div>
<!--中间部分开始-->
    <div style="width:100%;height:935px;padding-top: 80px;">
        <div style="width:60.5%;height:643px;border:1px solid #ddd;background-color: #fff;margin-left:20%;margin-top:1%;">
            <div style="margin-top:20px;">
                <!--上面那个视频的名称-->
                <p style=" margin-left: 5%;font-size: 16px;"><p style="margin-left: 20px;"><s:property value="video_name"/></p></p >
            </div>

            <div style="height:500px;background-color: #ddd;margin: 30px 20px 0 20px;">
				<video id="example_video_1" class="video-js vjs-default-skin" preload="none" autoplay="autoplay" style="height:500px; width: auto">
    				<source id="videosource" src="" type="video/mp4">
 				</video>
            </div>

            <div style="margin:20px 20px;">
                <button id="sharebtn" type="button" class="btn btn-primary"><i class="glyphicon glyphicon-share"></i> 分享</button>

                <!-- Indicates a successful or positive action -->
                <button id="downloadbtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-download-alt"></i> 下载</button>

                <!-- Contextual button for informational alert messages -->
                <button type="button" class="btn btn-info"><i class="glyphicon glyphicon-volume-up"></i> 申诉</button>
            </div>

        </div>
    </div>

<!--中间部分结束-->
<body>
<!--<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>-->
<script type="text/javascript" src="js/jquery.min.js"></script>
<%-- <script type="text/javascript" src="page/pageGroup.js"></script>  --%>
<script src="js/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/video-js/video.js"></script>
 <script type="text/javascript">
 
 
    $("#videosource").attr("src","<s:property value="videoURL"/>"); 
 
	//解决自动播放偶尔失效问题
	$(document).ready(function(){
		
		$('#example_video_1').trigger('play');
	});
	
	
	   var player = videojs('example_video_1',{
		    muted: false,
			controls : true,      
			//height : "500px",
			//width :  "auto",
			autoplay : true,
			loop : true,
		});
	 
	 //logo点击回到我的文件
	 $(".drivelogo").click(function () {
	   	console.log(".drivelogo");
	   	window.location.href="storage_userFileView.action";
	 })
	    
	 $("#downloadbtn").click(function () {
		 var virfile_id = $("input").attr("value");
	     console.log(virfile_id);
	     window.location.href="storage_download.action?virfile_id=" + virfile_id;
	 });
	 
	 $("#sharebtn").click(function () {
		 var virfile_id = $("input").attr("value");
	     console.log(virfile_id);
	     //window.location.href="storage_download.action?virfile_id=" + virfile_id;
	 });
	 
	 $(document).ready(function(){
	  if($("#userName").attr("value") == "请登录"){
		  $("#logoffbtn").attr("hidden","true");
	  }else{
		 
	  }
	});
	 
	 $("#logoffbtn").click(function(){
		 window.location.href="user_logoff.action";
	    });
</script>
</body>
</html>
