<%@ page language="java" contentType="text/html; charset=utf-8" import="xin.trooper.storage.domain.Users"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

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
   			<img alt="" src="images/recyclebin.png" style="opacity:0.75; margin-left: 43px;">
   		
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
             <div style="margin-left:45px;color: #6b6f73;font-size: 16px;">
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
        <div id="page_content" style="overflow:auto ;height:500px;"class="headBorder">
            <div class="zuijinTop cl">
                <!--<img src="images/xiazai.png" class="xiazai z" alt="">-->
                <!-- <button type="button" class="btn btn-success xiazai z" id="download">
                    <i class="glyphicon glyphicon-download"></i> 下载文件
                </button> -->

                <button type="button" class="btn btn-info yidong z" id="recover">
                    <i class="glyphicon glyphicon-move"></i> 恢复
                </button>

                <!-- <button type="button" class="btn btn-warning chong z">
                    <i class="glyphicon glyphicon-pencil"></i> 重命名
                </button> -->

                <button type="button" class="btn  btn-danger shanchu z" id="realdelete">
                    <i class="glyphicon glyphicon-trash"></i> 彻底删除
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
            
            
            
            
            
           <s:iterator value="recycleBinList"> 
                <div class="template cl rowHover" data-click="no" id="<s:property value="garbage_id"/>">
                    <img src="images/select.png" class="z templateState shareImg1" alt="">
                    <img src="images/recyclebinIcon.png" class="z iconImg shareImg" alt="">
                    <p hidden="true" id="garbage_id" value=<s:property value="garbage_id"/>></p>
                    <p hidden="true" id="delete_date" value=<s:property value="delete_date"/>></p>
                    <p hidden="true" id="virfile_name" value=<s:property value="virfile_name"/>></p>
                    <p hidden="true" id="virfile_ext" value=<s:property value="virfile_ext"/>></p>
                    <p hidden="true" id="virfile_path" value=<s:property value="virfile_path"/>></p>
                    <p hidden="true" id="virfile_size" value=<s:property value="virfile_size"/>></p>
                    <p hidden="true" id="upload_date" value=<s:property value="upload_date"/>></p>
                    <p hidden="true" id="file_id" value=<s:property value="file_id"/>></p>
					<p hidden="true" id="user_id" value=<s:property value="user_id"/>></p>
                    <p class="z name"><s:property value="virfile_name"/></p>
                    <p class="y time shareTime"><s:property value="delete_date"/><%-- <span>12:11:01</span> --%></p>
                    <!-- <p class="y size">20.1K</p> -->
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
            <%-- <div class="moveBox none">
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
            </div> --%>
        </div>
    </div>
</div>





<script type="text/javascript">

	function userfileview(){
		window.location = "${pageContext.request.contextPath }/storage_userFileView.action";
	}
	
</script>


<body>
<script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script>

	function torecyclebin(){
		window.location = "${pageContext.request.contextPath }/storage_recycleBinView.action";
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
	
	
	//判断用户是否登录
	$(document).ready(function(){
	  if($(".userStatus").attr("id").length > 0){
		  console.log("user has logined");
	  }else{
		  window.location.href="login.jsp"; 
	  }
	});
	
	
	var garbage_id = null;

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
                    garbage_id = null;
                }else {
                    $(this).attr("data-click","yes");
                    $(this).find(".templateState").attr("src","images/selectC.png");
                    garbage_id = $(this).attr("id");
                }
            });
        });
    }
    /* $(".huanyuan").click(function(){
        $(".huanyuanBox").show();
    }); */
    $(".quxiao,.ok,.close").click(function(){
        $(".huanyuanBox").hide();
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
    
   	//恢复文件
    $("#recover").click(function () {
    	console.log(garbage_id);
    	if(garbage_id == null){
    		alert("请选择文件！");
    	}else{
    		window.location.href="/webstorage/storage_recover.action?garbage_id="+garbage_id;
    	}
        
    })
    
    //彻底删除文件
    $("#realdelete").click(function () {
    	console.log(garbage_id);
    	if(garbage_id == null){
    		alert("请选择文件！");
    	}else{
    		window.location.href="/webstorage/storage_delete.action?garbage_id="+garbage_id;
    	}
        
    })
    
    //去分享页
    $("#share").click(function () {
        console.log("share");
        window.location.href="/webstorage/storage_shareView.action";
    })
</script>	
<%-- 	<h1>RecycleBin</h1><br>
	<input type="button" onclick="userfileview()" value="我的文件"/>
	
	
	<table>
			<tr>
			<tD><label>garbage_id</label></tD>
				<tD><label>delete_date</label></tD>
				<tD><label>virfile_name</label></tD>
				<tD><label>virfile_path</label></tD>
				<tD><label>upload_date</label></tD>
				<tD><label>file_id</label></tD>
				<tD><label>user_id</label></tD>
				<tD><label>删除</label></tD>
				<tD><label>恢复</label></tD>

		<s:iterator value="recycleBinList">
			<tr>
				<td> <s:property value="garbage_id"/></td>
				<td> <s:property value="delete_date"/></td>
				<td> <s:property value="virfile_name"/></td>
				<td> <s:property value="virfile_path"/></td>
				<TD> <s:property value="upload_date"/></TD>
				<td> <s:property value="file_id"/></td>
				<td> <s:property value="user_id"/></td>
				<TD><a href="${pageContext.request.contextPath }/storage_delete.action?garbage_id=<s:property value="garbage_id"/>">删除</a></TD>
				<TD><a href="${pageContext.request.contextPath }/storage_recover.action?garbage_id=<s:property value="garbage_id"/>">恢复</a></TD>
			</tr>
		</s:iterator>
	</table> --%>


</body>
</html>