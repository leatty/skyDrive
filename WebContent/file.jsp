<%@ page language="java" contentType="text/html; charset=utf-8" import="xin.trooper.storage.domain.Users"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<%


%>    

   

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
    <link rel="stylesheet" href="js/galpop/css/jquery.galpop.css" media="screen"  type="text/css">
    
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
    .xiaoshi{
    	width:1px;
    	heigth:1px;
    }
    .imgautosize{
    width:100%;height:auto
    /* 无效，写在了galpop.css里面 */
    
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
    <div class="left-menu-top" >
        <img alt="" src="images/upload.png" style="margin-left: 75px;">
        <div style="margin-left:20%;">
            <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
                <i class="glyphicon glyphicon-upload"></i>上传文档
            </button>
        </div>

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
        <div id="page_content">
            <div class="zuijinTop cl">
                <!--<img src="images/xiazai.png" class="xiazai z" alt="">-->
                <button type="button" class="btn btn-success xiazai z" id="download">
                    <i class="glyphicon glyphicon-download"></i> 下载文件
                </button>

                <button type="button" class="btn btn-info yidong z">
                    <i class="glyphicon glyphicon-move"></i> 移动到
                </button>

                <button type="button" class="btn btn-warning chong z" id="renamebtn" data-target="#youModal"><!-- data-toggle="modal"  -->
                    <i class="glyphicon glyphicon-pencil"></i> 重命名
                </button>

                <button type="button" class="btn  btn-danger shanchu z" id="virdelete">
                    <i class="glyphicon glyphicon-trash"></i> 删除
                </button>

                <button type="button" class="btn  btn-success shanchu z">
                    <i class="glyphicon glyphicon-plus"></i> 新建文件夹
                </button>
                
                <div class="btn-group shanchu z" >
				  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    分享 <span class="caret"></span>
				  </button>
				  <ul class="dropdown-menu" style="min-width: auto;">
				    <li>
				    <a id="commonShare">公开分享</a>
				    </li>
				    <li>
				    <a id="privateShare">私密分享</a>
				    </li>
				   
				  </ul>
				</div>
                <!--<img src="images/yidong.png" class="yidong z" alt="">-->
                <!--<img src="images/chong.png" class="chong z" alt="">-->
                <!--<img src="images/shanchu.png" class="shanchu z" alt="">-->
                <!--<img src="images/xinjian.png" class="xin z" alt="">-->
<!--                 <div class="sousuo y">
                    <div class="ss1 z"></div>
                    <input type="text" placeholder="请输入关键字搜索文件" class="z sousouInput" />
                </div>  -->
                
                <form id="search" action="${pageContext.request.contextPath }/storage_search.action" method="post" >
				    <div class="form-group" style="position: relative">
				        <i class="glyphicon glyphicon-search" id="searchbtn" style="display: inline-block;position: absolute;top:9px;left:10px"></i>
				        <input type="text" class="form-control" id="searchItem" name="search_name" placeholder="请输入关键字搜索文件" style="padding-left:30px;">
				    </div>
				</form>
            </div>
            <div class="zuijinTitle cl">
                <img src="images/select.png" data-all="no" class="z allIcon allSelect" alt="">
                <!-- <div class="z the">我的文档</div>
                <div class="z the">人事</div> -->
                <a href="${pageContext.request.contextPath }/storage_sort.action?sortType=upload_date">按照上传日期排序</a>
                <span>丨</span>
                <a href="${pageContext.request.contextPath }/storage_sort.action?sortType=virfile_name">按照名字排序</a>
                <span>丨</span>
                <a href="${pageContext.request.contextPath }/storage_sort.action?sortType=virfile_size">按照大小排序</a>
            </div>
            <div class="content cl" style="padding-left: 2%;height:500px;overflow-y:auto;pisition:relative;">
            
            
            
            
            
            <s:iterator value="userFileList">    
               <div class="box2 template foo" data-show="no" id="<s:property value="virfile_id"/>">
                    <img src="images/icon3.png" class="icon" alt="">
                    <div>
                    	<p hidden="true"  value=<s:property value="virfile_id"/>><s:property value="virfile_id"/></p>
                    	<input id="ext<s:property value="virfile_id"/>" hidden="true" class="fileType"  value=<s:property value="virfile_ext"/>></input>
                    	<p hidden="true"  value=<s:property value="virfile_path"/>><s:property value="virfile_path"/></p>
                    	<p hidden="true"  value=<s:property value="upload_date"/>><s:property value="upload_date"/></p>
                    	<p hidden="true"  value=<s:property value="file_id"/>><s:property value="file_id"/></p>
                    	<p hidden="true"  value=<s:property value="user_id"/>><s:property value="user_id"/></p>
                    	<p hidden="true"  value=<s:property value="virfile_name"/>><s:property value="virfile_name"/></p>
                        <p ><s:property value="virfile_name"/></p>
                    </div>
                    <img  src="images/select.png" class="select" alt="">
                    <p hidden="true"  value=<s:property value="virfile_size"/>><s:property value="virfile_size"/></p>
                </div>
            </s:iterator>
                
                
                
                
                
                
            </div>
            <!--<div style='width:90%;position:fixed;bottom:10px;margin:auto'>-->
                    <!--<div id="pageGro" class="cb">-->
                        <!--<div class="pageUp">上一页</div>-->
                        <!--<div class="pageList">-->
                            <!--<ul>-->
                                <!--<li>1</li>-->
                                <!--<li>2</li>-->
                                <!--<li>3</li>-->
                                <!--<li>4</li>-->
                                <!--<li>5</li>-->
                            <!--</ul>-->
                        <!--</div>-->
                        <!--<div class="pageDown">下一页</div>-->
                    <!--</div>-->
                <!--</div>-->
            <!--移动弹窗-->
            <div class="moveBox none">
                <div class="moveTitle">
                        <span>移动到</span>
                </div>
                <div class="closeMove">+</div>
                <div class="wenjian cl">
                    <img src="images/icon3.png" class="z"  alt="">
                    <p class="z name">劳动合同XX</p>
                    <p class="z size">29.8K</p>
                </div>
                <div class="urlBox">
                        <div class="urlBoxTitle">
                            选择目标文件夹
                        </div>
                    <div class="contentMain cl">
                        <div class="templateUrl z">
                            <div class="arrow z none"></div>
                            <img src="images/wenjian.png" class="z" alt="">
                            <p class="z">我的文档</p>
                        </div>
                        <div class="templateUrl z">
                            <div class="arrow z"></div>
                            <img src="images/wenjian.png" class="z" alt="">
                            <p class="z">我的文档</p>
                        </div>
                        <div class="templateUrl z">
                            <div class="arrow z"></div>
                            <img src="images/wenjian.png" class="z" alt="">
                            <p class="z">我的文档</p>
                        </div>
                    </div>
                </div>
                <input type="button" class="y quxiao" value="取消">
                <input type="button" class="y ok" value="确定">
            </div>
        </div>
    </div>
</div>


<!--上传的模态框-->
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">上传文档</h4>
            </div>
            <div class="modal-body">
                <form method="post" enctype="multipart/form-data" id="uploadform" action="storage_upload.action">
                    <input type="file" name="upload" id="fileData"><br>
                    
                </form>
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-success" id="upload">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!-- 重命名模态框 -->
<div class="modal fade" id="youModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 400px;">
            
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">重命名</h4>
            </div>
            
            <form id="renameform" action="storage_rename.action" method="post"> 
            <div class="modal-body">
            	<input id="rename_id" name="virfile_id" type="hidden"></input>
            	<input id="rename_name" name="virfile_name" placeholder="请输入文件名"></input>
            </div>
            </form>
            <div class="modal-footer">
            	<button type="button" class="btn btn-primary" id="rename">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 音乐模态框 -->
<div class="modal fade" style="pointer-events: none;display: table;width: 1px; height: 1px;" id="musicModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="false">
    <div class="modal-dialog" style="width: 330px;height:141.8px;">
        <div class="modal-content" style="pointer-events: visible;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="musicModalLabel"></h4>
            </div>
            <div class="modal-body">
            	<audio id="musicplayer" class="green-audio-player" src="" controls autoplay loop></audio>
            	<div ></div>	
            </div>
            <!-- <div class="modal-footer">
                <button type="button" class="closemusic btn btn-default" data-dismiss="modal">关闭</button>
            </div> -->
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 属性模态框 -->
<div class="modal fade" id="attrModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="">
            
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">属性</h4>
            </div>
            <div class="modal-body">
            	<div><span >文件名称：</span><input  readonly id="attr_name" style="width: -webkit-fill-available;"></input></div>
            	<div><span>存储路径：</span><input readonly id="attr_path"></input></div>
            	<div><span>文件大小：</span><input readonly id="attr_size"></input></div>
            	<div><span>上传日期：</span><input readonly id="attr_date"></input></div>
            	
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<a id="previewImage" hidden="true"  href="images/image-1-large.jpg" class="galpop" title="first image">
    <img hidden="true" src="" alt="first image thumbnail" />
</a>





<body>
<!--<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>-->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<%-- <script type="text/javascript" src="page/pageGroup.js"></script>  --%>
<script src="js/bootstrap/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/jquery.contextify.min.js"></script>
<script type="text/javascript" src="js/galpop/js/jquery.galpop.js"></script>
<script src="js/jquery.marquee.min.js"></script>



 <script type="text/javascript">
	
 	
 
	function logoff(){
		window.location = "${pageContext.request.contextPath }/user_logoff.action";
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
	
	function search(){
		var search = document.getElementById("search");
		var search_name= search.value;
		search_name = encodeURI(encodeURI(search_name)); 
		window.location = "${pageContext.request.contextPath }/storage_search.action?search_name=" + search_name;
	}
	
	
	
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
</script>
<script>
	
	
	//定义需要用到的变量和数组
	var server_protocol = 'http://';
	var server_name = 'localhost';
	var server_port = ':8080';
	var server_resource = 'file';
	var virfile_id = null;
	var virfile_ext = null;
	var virfile_name = null;
	var virfile_path = null;
	var virfile_size = null;
	var virfile_upload = null;
	var image=new Array();
	image.push(".jpg");image.push(".jpeg");image.push(".png");image.push(".gif");image.push(".bmp");
	image.push(".tif");image.push(".raw");
	var video=new Array();
	video.push(".mpeg");video.push(".avi");video.push(".mov");video.push(".asf");video.push(".wmv");
	video.push(".mkv");video.push(".flv");video.push(".rmvb");video.push(".mp4");
	var music=new Array();
	music.push(".mp3");music.push(".wav");music.push(".ape");music.push(".flac");music.push(".aac");
	var documents=new Array();
	documents.push(".xls");documents.push(".vsd");documents.push(".ppt");documents.push(".doc");documents.push(".xlsx");
	documents.push(".vsdx");documents.push(".pptx");documents.push(".docx");documents.push(".txt");documents.push(".pdf");
	documents.push(".js");

	
	
	
	
	
	
	$(document).ready(function(){
		//判断用户是否登录
	  if($(".userStatus").attr("id").length > 0){
		  console.log("user has logined");
		  //window.location = "${pageContext.request.contextPath }/storage_userFileView.action";
	  }else{
		  window.location.href="login.jsp"; 
	  }
	  
	  //$('#musicModal').modal('show');
	  $('#musicModal').modal('hide');
		
	    //以及替换文件图标
	    var allFileExt = $(".fileType").map(function(){return $(this).attr("id");}).get();
	    	 //console.log(allFileExt);
	    	 for(var i=0;i<allFileExt.length;i++){
	    		 var ext = "#"+allFileExt[i];
	    		 var extvalue = $(ext).attr("value");
	    		 for(var j=0;j<image.length;j++){
	    			 var imageType = image[j];
    				 //console.log(imageType);
	    			 if(extvalue == imageType){
	    				 $(ext).parent().parent().children(":first").attr("src","images/imageIcon.png");
	    			 }
	    		 }
	    		 for(var j=0;j<video.length;j++){
	    			 var videoType = video[j];
    				 //console.log(videoType);
	    			 if(extvalue == videoType){
	    				 $(ext).parent().parent().children(":first").attr("src","images/videoIcon.png");
	    			 }
	    		 }
	    		 for(var j=0;j<music.length;j++){
	    			 var musicType = music[j];
    				 //console.log(musicType);
	    			 if(extvalue == musicType){
	    				 $(ext).parent().parent().children(":first").attr("src","images/musicIcon.png");
	    			 }
	    		 }
	    		 for(var j=0;j<documents.length;j++){
	    			 var documentsType = documents[j];
    				 //console.log(documentsType);
	    			 if(extvalue == documentsType){
	    				 $(ext).parent().parent().children(":first").attr("src","images/documentIcon.png");
	    			 }
	    		 }
	    	 }
	});
	
	/* for(var i=0;i<image.length;i++){
		if($(this).attr("value") == image[i])
		$(this).parent().children("img").attr("src","images/imageIcon.png");
	} */
	
    $(".sousouInput").blur(function(){
        $(".sousuo").css({
            border:"1px solid #ccc"
        });
        $(".ss1").removeClass("focusState");
    });
    $(".sousouInput").focus(function(){
        $(".sousuo").css({
            border:"1px solid #9cf"
        });
        $(".ss1").addClass("focusState");
    });
    $(".zuijinTitle").css({
        width:$(window).width()-237
    });
    $(".content").css({
       width:$(window).width()-237
    });

    $("#page_content").css({
        width:$(window).width()-237
    });

    $(".allIcon").click(function(){
        if($(this).attr("data-all")=="yes"){
            $(this).attr("src","images/select.png");
            $(".select").attr("src","images/select.png");
            $(this).attr("data-all","no");
            $(".template").attr("data-show","no");
            $(".template").removeClass("box2H");
        }else{
            $(this).attr("src","images/selectC.png");
            $(".template").attr("data-show","yes");
            $(this).attr("data-all","yes");
            $(".template").addClass("box2H");
            $(".select").attr("src","images/selectC.png");
        }
    });

    bindMyClick();

    function  bindMyClick(){
    	
        $(".content .template").each(function(){
           $(this).click(function(){
               if($(this).attr("data-show")=="yes"){
                   $(this).removeClass(".box2C");
                   $(this).removeClass(".box2H");
                   $(this).find(".select").attr("src","images/select.png");
                   $(this).attr("data-show","no");
                   virfile_id = null;
                   virfile_ext = null;
                   virfile_name = null;
                   console.log(virfile_id);
                   virfile_path = null;
               	   virfile_size = null;
               	   virfile_upload = null;
               }else {
                   $(this).addClass(".box2C");
                   $(this).addClass(".box2H");
                   $(this).find(".select").attr("src","images/selectC.png");
                   $(this).attr("data-show","yes");
                   $(this).find(".select").show();
                   virfile_id = $(this).attr("id");
                   virfile_ext = $(this).children("div").children("input").val();
                   virfile_name = $(this).children("div").find("p").eq(6).text();
                   console.log("bindMyClick() virfile_id = "+virfile_id);
                   virfile_path = $(this).find("div").children("p").eq(1).text();
                   virfile_size = $(this).children("p").text();
                   virfile_upload = $(this).find("div").children("p").eq(2).text();
                   console.log(virfile_path + "|" + virfile_size + "|" + virfile_upload)
               }
           });
        });
        
    }


    $(".contentMain .templateUrl").each(function(e){
        $(".contentMain .templateUrl").eq(e).click(function(){
            $(".contentMain p").removeClass("templateUrlClickP");
            $(".contentMain p").eq(e).addClass("templateUrlClickP");
       });
    });

    $(".yidong").click(function(){
        $(".moveBox").show();
    });
        $(".closeMove,.ok,.quxiao").click(function(){
           $(".moveBox").hide();
        });
        
        
        
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
    
    // 上传文件
    $("#upload").click(function () {
        //var formData = new FormData();
        //formData.append("upload", document.getElementById("fileData").files[0]);
		
        // formData 是传给后台的值
        //console.log(formData)
        $("#uploadform").submit();
/*          $.ajax({
            url: "storage_upload.action",  //地址
            type: "POST",
            data: formData,
            
            //必须false才会自动加上正确的Content-Type
             
            contentType: false,
            
             //必须false才会避开jQuery对 formdata 的默认处理
             //XMLHttpRequest会对 formdata 进行正确的处理
             
            processData: false,
            success: function (data) {
                //这里是上传成功之后的处理函数
               console.log("成功")
            },
            error: function () {
                alert("上传失败！");

            }
        }); */
        
    })
    
    // 音乐模态框关闭时触发事件-关闭音乐
    $('#musicModal').on('hide.bs.modal', function () {
    	console.log("closemusic");
        $("#musicplayer").trigger('pause');
	})
 	
    // 去分享页面
    $("#share").click(function () {
        console.log("share");
        window.location.href="/webstorage/storage_shareView.action";
    })
    
    // 下载文件
    $("#download").click(function () {
        console.log(virfile_id);
        if(virfile_id == null){
        	alert("请选择要下载的文件！");
        }else{
        	window.location.href="storage_download.action?virfile_id="+virfile_id;
        }
        
    })
    
    // 删除文件
    $("#virdelete").click(function () {
        console.log(virfile_id);
        if(virfile_id == null){
        	alert("请选择要删除的文件！");
        }else{
        	window.location.href="storage_virdelete.action?virfile_id="+virfile_id;
        }
        
        
    })
    
    
    // 重命名-打开模态框
    $("#renamebtn").click(function () {
    	if(virfile_id == null){
    		$("#youModal").modal('hide');
    		alert("请选择要重命名的文件！");
    	}else{
    		$('#youModal').modal('show');
    		$("#rename_id").attr("value",virfile_id);
    		//$("#renametext").attr("placeholder",virfile_name);
    	}
    	
    	
    })
    //重命名-提交
    $("#rename").click(function () {
    	$("#renameform").submit();
    })
    
    
    //logo点击回到我的文件
    $(".drivelogo").click(function () {
    	console.log(".drivelogo");
    	window.location.href="storage_userFileView.action";
    })
    
/*     //打开时获取
    $('#youModal').on('show.bs.modal', function () {
    	if(virfile_id == null){
    		alert(1);
    		
    	}else{
    		alert(2);
    	}
	}) */
    
    // 搜索文件
    $("#searchbtn").click(function () {
    	console.log("searchbtn");
        $("#search").submit();
    })
    
    // 公开分享
    $("#commonShare").click(function () {
    	console.log("commonShare");
    	console.log(virfile_id);
    	//window.location.href="storage_commonShare.action?virfile_id="+virfile_id;
    	if(virfile_id != null){
    		$.ajax({
                url:"storage_commonShare.action?virfile_id="+virfile_id,
                type:"get",
                async: true,
                success: function(data){
                    // 这里接收后台数据
                },
                error:function(){
                } //error结束
    			
            });
        	alert("分享成功！");
    	}else{
    		alert("请选择要分享的文件！");
    	}
    	
    })
    
    
    // 私密分享
    $("#privateShare").click(function () {
    	console.log("privateShare");
    	console.log(virfile_id);
    	if(virfile_id != null){
    		$.ajax({
                url:"storage_privateShare.action?virfile_id="+virfile_id,
                type:"get",
                async: false,
                success: function(data){
                    // 这里接收后台数据
                },
                error:function(){
                } //error结束
            });
        	alert("分享成功！");
    	}else{
    		alert("分享失败！");
    	}
    })
    
     $(function(){
        $(".modal-dialog").draggable();
    })
    
    //启用图片预览插件
    $('.galpop').galpop();
    //$('#viewer').viewer();
    
    
    //右键菜单
    var options = {items:[
	  {header: 'Options'},
	  {divider: true},
	  {text: '属性', onclick: function(e) {
		  if(virfile_id != null){
			  $("#attr_name").attr("value",virfile_name)
			  //$("#attr_path").attr("value",virfile_path)
			  $("#attr_path").attr("value","myfile/browser/cache")
			  var sizeOfMB = virfile_size;
			  sizeOfMB = sizeOfMB/1024/1024;
			  sizeOfMB = sizeOfMB.toFixed(2);
			  sizeOfMB = sizeOfMB + " MB";
			  console.log(sizeOfMB);
			  $("#attr_size").attr("value",sizeOfMB)
			  $("#attr_date").attr("value",virfile_upload)
			  $('#attrModal').modal('show');
		  }
		   
	  }},
	  {text: '预览', onclick: function(e) {
		  if(virfile_ext != null){
			//图片预览操作在这里  
       	   for(var i=0;i<image.length;i++){  
       		    if(virfile_ext == image[i]){
       		    	console.log("this is "+image[i]+" file");
       		    	$.ajax({ 
       		         type : "get", //提交方式 
       		         url : "storage_imagePreview.action?virfile_id="+virfile_id,//路径 
       		         data : { 
       		        //数据，这里使用的是Json格式进行传输 
       		         },
       		         async:true,
       		         success : function(result) {//返回数据根据结果进行相应的处理 
       		          if ( result.success ) {
       		        	  console.log("result = " + result);
       		        	  var image_path = server_protocol + server_name + server_port + "/" + server_resource + result;
       		          } else {
       		        	  console.log(result);
       		        	  console.log("error");
       		        	  var image_path = server_protocol + server_name + server_port + "/" + server_resource + result;
       		        	  $("#previewImage").attr("href",image_path);
         		    	  $("#previewImage").click();
         		    	  $("#lighter-content").children("img").addClass("imgautosize");
       		          } 
       		         } 
       		        });
       		    }  
       		}
			//视频预览操作在这里
       	   for(var i=0;i<video.length;i++){  
      		    	if(virfile_ext == video[i]){
      		    		console.log("this is "+video[i]+" file");
      		    		window.location.href="storage_videoPreview.action?virfile_id="+virfile_id;
      		    	}  
      			}
       		//文档预览操作在这里
       	   for(var i=0;i<documents.length;i++){  
      		    	if(virfile_ext == documents[i]){
      		    		console.log("this is "+documents[i]+" file");
      		    		window.open("storage_documentPreview.action?virfile_id="+virfile_id);
      		    	}  
      			}
			//音频预览操作在这里
       	   for(var i=0;i<music.length;i++){  
      		    	if(virfile_ext == music[i]){
      		    		console.log("this is "+music[i]+" file");
      		    		$.ajax({ 
      	       		         type : "get", //提交方式 
      	       		         url : "storage_musicPreview.action?virfile_id="+virfile_id,//路径 
      	       		         data : { 
      	       		        //数据，这里使用的是Json格式进行传输 
      	       		         },
      	       		         async:true,
      	       		         success : function(result) {//返回数据根据结果进行相应的处理 
      	       		          if ( result.success ) {
      	       		        	  console.log("result = " + result);
      	       		        	  var music_path = server_protocol + server_name + server_port + "/" + server_resource + result;
      	       		          } else {
      	       		        	  console.log(result);
      	       		        	  console.log("error");
      	       		        	  var music_path = server_protocol + server_name + server_port + "/" + server_resource + result;
      	       		        	  $("#musicplayer").attr("src",music_path);
      	       		        	  var xx = '<s:property value="music_path"/>';
      	       		        	  console.log(virfile_name);
      	       		        	  $("#musicModalLabel").text(virfile_name);
      	         		    	  //$("#lighter-content").children("img").addClass("imgautosize");
      	       		          } 
      	       		       } 
      	       		   });
      		    	//$('#musicModal').modal({backdrop:"static"});
      		    	//$('#musicModal').draggable();
      		    	$(".modal-backdrop").remove();
      		    	$('#musicModal').modal('show');
      		    	}  
      	   		}
      		}
	    //alert('Hello ' + e.data.name);
	  }},
	  {divider: true},
	  {text: '移动', onclick: function(e) {
		  
	  }},
	  {text: '复制', onclick: function(e) {
		  
	  }},
	  {text: '重命名', onclick: function(e) {
		  $("#renamebtn").click();
	  }},
	  {text: '删除', onclick: function(e) {
		  $("#virdelete").click();
	  }},
	  {divider: true},
	  {text: '下载', onclick: function(e) {
		  $("#download").click();
	  }},
	  {divider: true},
	  {text: '公开分享', onclick: function(e) {
		  $("#commonShare").click();
	  }},
	  {text: '私密分享', onclick: function(e) {
		  $("#privateShare").click();
	  }},
	  //{text: '', href: '#'}
	]}
	$('.foo').contextify(options); 
</script>

</body>
</html>
