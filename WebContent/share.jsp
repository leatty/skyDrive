<%@ page language="java" contentType="text/html; charset=utf-8" import="xin.trooper.storage.domain.Users"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%
Users existUser = (Users)request.getSession().getAttribute("loginedUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>顺顺网盘</title>
    <link rel="stylesheet" href="css/common.css" type="text/css"/>
    <link rel="stylesheet" href="css/index.css" type="text/css"/>
    <link rel="stylesheet" href="js/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/my.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="page/base.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="page/pageGroup.css" type="text/css" media="screen"/>
    
    <style>
    #search{
        float: right;
        margin-right: 22%;
        width: 25%;
        margin-top: 20px;
    }
    
    #left-menu-two .leftStyle{
    	margin-bottom:7px;
    }
    
     .shareImg{
    	margin-right: 8px;
	    width: 25px;
	    height: 25px;
	    margin-top: 16px;
    }
    .shareImg1{
    	margin: 15px;
    }
    .shareTime{
    	margin-right: 38%;
    	margin-top: 15px;
    }
    .rowHover:hover{
    	 cursor: pointer;
		 background-color: #e9f3fd;
    }
    
    .headBorder{
    border-top: 1px;
    border-color: rgb(204,204,204);
    border-top-style: solid;
    }
	</style>
</head>

<!--顶部-->
<div class="nav">
    <div class="nav cl">
        <div class="nav_z z">
            <a><img class="drivelogo" src="images/weblogo.png" style="width: 225px;margin-top: -14px;margin-left: -50px;"/></a>
        </div>

        <div class="nav_y y">
            <!--<a href="">关于我们</a>-->
            <!--<span>丨</span>-->
            <!--<a href="">联系我们</a>-->
            <button type="button" class="btn btn-primary" id="logoff">注销</button>
        </div>
        <div class="userNumber y">
        <img alt="" src="images/user.png" style="margin-top: 29px; margin-right: 12px;">
            你好,<span><s:property value="#session.username"/></span>
            <p hidden="true" class="userStatus" id=<s:property value="#session.userStatus"/>></p>
        </div>
    </div>

</div>
<!--顶部结束-->
<!--菜单-->
<div class="left-menu">
   <div class="left-menu-top">
   		<div>
   			<img alt="" src="images/share.png" style="opacity:0.7; margin-left: 46px;">
   		
   		</div>
        <!-- <a href="javascript:void(0)">
            <div class="left-menu-top-small">
                <img src="images/index_01.png"/>
            </div>
        </a>

        上传
        <a href="javascript:void(0)">
            <div class="left-menu-top-small">
                <img src="images/index_02.png"/>
            </div>
        </a>

        <div style="margin-left:20%;">
            <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                <i class="glyphicon glyphicon-upload"></i>上传文档
            </button>
        </div> -->

    </div> 
    
    <div class="left-menu-one" style="height:70px;">
    	<a><!-- 我的文件 -->
    	<div style="margin-left:58px;color: #6b6f73;font-size: 16px;margin-bottom: 5px;" onclick="userfileview()">
    	<span class="glyphicon glyphicon-folder-open" style="display: inline-block;padding-right: 5px;font-size: 20px"></span> <span style="color:#2f3235">我的文件</span>
		</div>
		</a>
		<a><!-- 我的分享 -->
		<div style="margin-left:58px;color: #6b6f73;font-size: 16px;" id="share">
    	<span class="glyphicon glyphicon-share" style="display: inline-block;padding-right: 5px;font-size: 20px"></span> <span style="color:#2f3235">我的分享</span>
		</div>
		</a>
        <%-- <a href="${pageContext.request.contextPath }/storage_userFileView.action">
            <div class="left-menu-one-small">
                <img src="images/index_03_1.png"/>
            </div>
        </a>
        <a href="zuijin.html">
            <div class="left-menu-one-small">
                <img src="images/index_05.png"/>
            </div>
        </a> --%>
    </div>
    <div class="left-menu-two" >
    	<a>
	    <div style="margin-left:58px;color: #6b6f73;font-size: 16px;margin-bottom: 5px;" onclick="classify('图片')">
	    <span class="glyphicon glyphicon-picture" style="display: inline-block;padding-right: 5px;font-size: 20px"></span> <span style="color:#2f3235">图片</span>
		</div>
		</a>
		<a>
		<div style="margin-left:58px;color: #6b6f73;font-size: 16px;margin-bottom: 5px;" onclick="classify('文档')">
   		<span class="glyphicon glyphicon-list-alt" style="display: inline-block;padding-right: 5px;font-size: 20px"></span> <span style="color:#2f3235">文档</span>
		</div>
		</a>
		<a>
		<div style="margin-left:58px;color: #6b6f73;font-size: 16px; margin-bottom: 5px;" onclick="classify('视频')">
    	<span class="glyphicon glyphicon-film" style="display: inline-block;padding-right: 5px;font-size: 20px"></span> <span style="color:#2f3235">视频</span>
		</div>
		</a>
		<a>
		<div style="margin-left:58px;color: #6b6f73;font-size: 16px; margin-bottom: 5px;" onclick="classify('音乐')">
    	<span class="glyphicon glyphicon-music" style="display: inline-block;padding-right: 5px;font-size: 20px"></span> <span style="color:#2f3235">音乐</span>
		</div>
		</a>
        <!-- <a href="javascript:void(0)" onclick="classify('图片')">
            <div class="left-menu-two-small">
                <img src="images/excel01.png"/>
            </div>
        </a>
        <a href="javascript:void(0)" onclick="classify('文档')">
            <div class="left-menu-two-small">
                <img src="images/ppt01.png"/>
            </div>
        </a>
        <a href="javascript:void(0)" onclick="classify('视频')">
            <div class="left-menu-two-small">
                <img src="images/word01.png"/>
            </div>
        </a>
        <a href="javascript:void(0)" onclick="classify('音乐')" >
            <div class="left-menu-two-small">
                <img src="images/word01.png"/>
            </div>
        </a> -->
    </div>
    <div class="left-menu-three" style="height:36px;">
        <!-- <a href="caogao.html">
            <div class="left-menu-three-small">
                <img src="images/caogaoxiang01.png"/>
            </div>
        </a> -->
        <a href="javascript:void(0)" onclick="torecyclebin()">
            <div class="left-menu-three-small">
             <!--    <img src="images/lajixiang01.png"/>  -->
             <div style="margin-left:45px;color: #6b6f73;font-size: 16px;" onclick="torecyclebin()">
			    <span class="glyphicon glyphicon-trash" style="display: inline-block;padding-right: 5px;font-size: 20px"></span> <span style="color:#2f3235">垃圾箱</span>
			</div>
            </div>
        </a>
    </div>
    <div class="left-menu-four">
        <div class="left-menu-four-small">
            <div class="left-menu-four-small-new"></div>
        </div>
        <div class="contain_four">
            <div class="contain_four_main">
                <span>容量：</span>
                <span class="cont">5</span>GB；可用
                <span class="use">3</span>GB
            </div>
            <div class="max_add">
                <button class="max_container" >升级容量</button>
            </div>
        </div>
    </div>

    <div class="foot">
        <a href="">返回意见</a>丨
        <a href="">帮助中心</a>
    </div>
</div>
<!--菜单end-->




<!--菜单右边的iframe页面-->
<div id="right-content" class="right-content">
    <div class="content">
        <div id="page_content" style="overflow:auto ;height:500px;" class="headBorder">
            <div class="zuijinTop cl">
                <!--<img src="images/xiazai.png" class="xiazai z" alt="">-->
                <!-- <button type="button" class="btn btn-success xiazai z" id="download">
                    <i class="glyphicon glyphicon-download"></i> 下载文件
                </button> -->

                <button type="button" class="btn btn-info yidong z" id="copyLink">
                    <i class="glyphicon glyphicon-move"></i> 复制分享链接
                </button>

                <!-- <button type="button" class="btn btn-warning chong z">
                    <i class="glyphicon glyphicon-pencil"></i> 重命名
                </button> -->

                <button type="button" class="btn  btn-danger shanchu z" id="shareDelete">
                    <i class="glyphicon glyphicon-trash"></i> 取消分享
                </button>

                <!-- <button type="button" class="btn  btn-success shanchu z">
                    <i class="glyphicon glyphicon-plus"></i> 新建文件夹
                </button> -->
                <!--<img src="images/yidong.png" class="yidong z" alt="">-->
                <!--<img src="images/chong.png" class="chong z" alt="">-->
                <!--<img src="images/shanchu.png" class="shanchu z" alt="">-->
                <!--<img src="images/xinjian.png" class="xin z" alt="">-->
<!--                 <div class="sousuo y">
                    <div class="ss1 z"></div>
                    <input type="text" placeholder="请输入关键字搜索文件" class="z sousouInput" />
                </div>  -->
                
                <%-- <form id="search" action="${pageContext.request.contextPath }/storage_search.action" method="post" >
				    <div class="form-group" style="position: relative">
				        <i class="glyphicon glyphicon-search" id="searchbtn" style="display: inline-block;position: absolute;top:9px;left:10px"></i>
				        <input type="text" class="form-control" id="searchItem" name="search_name" placeholder="请输入关键字搜索文件" style="padding-left:30px;">
				    </div>
				</form> --%>
            </div>
            <%-- <div class="zuijinTitle cl">
                <img src="images/select.png" data-all="no" class="z allIcon allSelect" alt="">
                <!-- <div class="z the">我的文档</div>
                <div class="z the">人事</div> -->
                <a href="${pageContext.request.contextPath }/storage_sort.action?sortType=upload_date">按照上传日期排序</a>
                <span>丨</span>
                <a href="${pageContext.request.contextPath }/storage_sort.action?sortType=virfile_name">按照名字排序</a>
                <span>丨</span>
                <a href="javascript:void(0)">按照大小排序</a>
            </div>
            <div class="content cl" style="padding-left: 2%;height:500px;overflow-y:auto;pisition:relative;"> --%>
            
            
            
          
           <div style="overflow:auto"></div>
           <s:iterator value="shareList"> 
                <div class="template cl rowHover" data-click="no"  id="<s:property value="share_id" />" >
                    <img src="images/select.png" class="z templateState shareImg1" alt="" >
                    <img src="images/shareIcon.png" class="z iconImg shareImg" alt="">
                    
                    <p hidden="true" id="share_id" value=<s:property value="share_id"/>></p>
                    <p hidden="true" id="user_id" value=<s:property value="user_id"/>></p>
                    <input hidden="true" id="virfile_id" value=<s:property value="virfile_id"/>></input>
                    <p hidden="true" id="share_name" value=<s:property value="share_name" />></p>
                    <p hidden="true" id="share_date" value=<s:property value="share_date" />></p>
                    <p hidden="true" id="share_password" value=<s:property value="share_password"/>></p>
                    
                    <p class="z name"><s:property value="share_name"/></p>
                    
                    <p class="y time shareTime"><s:property value="share_date" /></p>
                    <p class="y time " style="margin-top: 15px; margin-right: 100px;"><s:property value="share_password"/></p>
                    <!-- <p class="y size">20.1K</p> -->
                </div>
             </s:iterator>
                
                
                
                
                
                
            </div>
        </div>
    </div>
</div>








<body>
<script type="text/javascript">



	function classify(type){
		var type = type;
		if(type == "图片"){
			type = 1;
		}else if(type == "文档"){
			type = 2;
		}else if(type == "视频"){
			type = 3;
		}else if(type == "音乐"){
			type = 4;
		}
		window.location = "${pageContext.request.contextPath }/storage_classify.action?type="+type;
	}
	function userfileview(){
		window.location = "${pageContext.request.contextPath }/storage_userFileView.action";
	}
	function torecyclebin(){
		window.location = "${pageContext.request.contextPath }/storage_recycleBinView.action";
	}
	function toshare(){
		window.location = "${pageContext.request.contextPath }/storage_shareView.action";
	}
</script>
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/jquery.copy.js"></script>
<script type="text/javascript">

	//判断用户是否登录
	$(document).ready(function(){
	  if($(".userStatus").attr("id").length > 0){
		  console.log("user has logined");
	  }else{
		  window.location.href="login.jsp"; 
	  }
	});
	

	var share_id = null;
	var virfile_id = null;
	
	function userfileview(){
		window.location = "${pageContext.request.contextPath }/storage_userFileView.action";
	}
	
	
	 
	 
	 
	 /******点击全选*****/

	    $(".allSelect").click(function(){
	       if($(".allIcon").attr("data-all")=="no"){
	           $(".template").attr("data-click","yes");
	           $(".templateState").attr("src","images/selectC.png");
	           $(".allIcon").attr("data-all","yes");
	           $(".allIcon").attr("src","images/selectC.png");
	       }else {
	           $(".template").attr("data-click","no");
	           $(".templateState").attr("src","images/select.png");
	           $(".allIcon").attr("data-all","no");
	           $(".allIcon").attr("src","images/select.png");
	       }
	    });

	    bindClick();
	    /*********某一个点击*****/
	    function  bindClick(){
	        $(".content .template").each(function(e){
	            $(this).click(function(){
	                if($(this).attr("data-click")=="yes"){
	                    $(".allIcon").attr("data-all","no");
	                    $(".allIcon").attr("src","images/select.png");
	                    $(this).attr("data-click","no");
	                    $(this).find(".templateState").attr("src","images/select.png");
	                    share_id = null;
	                    virfile_id = null;
	                }else {
	                    $(this).attr("data-click","yes");
	                    $(this).find(".templateState").attr("src","images/selectC.png");
	                    share_id = $(this).attr("id");
	                    virfile_id = $(this).children("input").attr("value");
	                    console.log("bindClick() share_id = "+share_id);
	                    console.log("bindClick() virfile_id = "+virfile_id);
	                }
	            });
	        });
	    }
	    
	    
	  	//logo点击回到我的文件
	    $(".drivelogo").click(function () {
	    	console.log(".drivelogo");
	    	window.location.href="storage_userFileView.action";
	    })
		// 去分享页面
	    $("#share").click(function () {
	        console.log("share");
	        window.location.href="/webstorage/storage_shareView.action";
	    })
	    
	    
	    // 复制分享链接
	    $("#copyLink").click(function () {
	        console.log("share_id="+share_id);
	        console.log("virfile_id="+virfile_id);
	        var link = "http://localhost:8080/webstorage/storage_shareDownload.action?share_id=" + share_id;
	    	console.log(link);
	    	if(share_id == null){
	    		alert("请选择文件！");
	    	}else{
	    		$.copy(link);
		    	alert("已经复制到剪贴板");
	    	}
	    	
	    })
	    //注销用户
	    $("#logoff").click(function () {
	        console.log("注销")
	        //调用注销的api
	        //实际调用ajax和后台交互
			window.location.href="user_logoff.action";
	/*             $.ajax({
	                url:"/user_logoff.action",
	                type:"get",
	                async: false,
	                success: function(data){
	                    // 这里接收后台数据
	                },
	                error:function(){
	                    alert("注销失败")
	                } //error结束
	
	            }); */
	    })
	    
	    // 取消分享
	    $("#shareDelete").click(function () {
	        console.log(share_id);
	        if(share_id == null){
	        	alert("请选择文件！");
	        }else{
	        	window.location.href="/webstorage/storage_shareDelete.action?share_id=" + share_id;
	        }
	        
	    })
</script>

<style type="text/css">
   .wrapper {position: relative;}
   #input {position: absolute;top: 0;left: 0;opacity: 0;z-index: -10;}
</style>

	
</body>
</html>