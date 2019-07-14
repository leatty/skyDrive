<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>顺顺网盘</title>
    <link rel="stylesheet" href="js/bootstrap/dist/css/bootstrap.min.css">
    <style>
        body{
            margin:0 auto;
            padding: 0;
        }
    </style>
</head>
<body>
    <div style="width:100%;background-color:#e9f3fd;height:935px; text-align: center">
        <div style="padding-top:10%;">
            <h1>顺顺网盘</h1>
            <div style="width:600px;height:300px;margin-left:35%;">
                <div style="width:100%;height:100px;background-color: #2aabd2;border-top-right-radius: 10px;border-top-left-radius: 10px;">
                    <div style="text-align: left;color:#fff;padding-top: 30px;padding-left: 20px;">
                        <img src="./images/head.jpg" alt="" style="width:50px;height:50px;border-radius: 50%;">
                        <p style="font-size: 20px;display: inline-block">xxx给你分享了文件</p>
                    </div>

                </div>
                <div style="width:100%;height:250px; border: 1px solid #ddd;background-color: #fff;">
                    <p style="text-align: left;font-size: 18px;margin-top:70px;margin-left:40px;">请输入提取码:</p>
                    <form class="form-inline" style="text-align: left;margin-left:40px;" action="${pageContext.request.contextPath }/storage_privateShareDownload.action">
                        <div class="form-group">
                            <input type="hidden" name="share_id" value="<s:property value="share_id"/>">
                            <input type="text" class="form-control" name="share_password" id="pickUpInput" placeholder="请输入提取码" style="width:390px;margin-top:20px;height: 45px;">
                        </div>
                        <button type="submit" class="btn btn-info" style="margin-top:20px;">提取文件</button>
                    </form>
                </div>
            </div>
        </div>
    </div>





<script type="text/javascript" src="js/jquery.min.js"></script>
<!--<script type="text/javascript" src="page/pageGroup.js"></script>-->
<script src="js/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
	var tips = "<s:property value='tips'/>";
	$(document).ready(function(){
	  console.log(tips);
	  if(tips != null){
		  //$("#pickUpInput").attr("value","密码错误");
	  }
	});

</script>
<%-- 	<label><s:property value="tips"/></label>
	
	<form action="${pageContext.request.contextPath }/storage_privateShareDownload.action">
		<input type="hidden" name="share_id" value="<s:property value="share_id"/>">
		<input type="text" name="share_password" value="分享密码">
		<input type="submit" value="查看文件">
	</form>	 --%>	
</body>
</html>