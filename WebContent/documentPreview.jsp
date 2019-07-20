<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>顺顺网盘</title>
</head>
<body>
	<p id="dcpath" hidden="true"><s:property value="documentURL"/></p>
	<p id="dcext" hidden="true"><s:property value="virfile_ext"/></p>










	
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/pdfjs/web/viewer.js"></script>
<script type="text/javascript" src="js/pdfjs/build/pdf.js"></script>
<script type="text/javascript" src="js/pdfjs/build/pdf.worker.js"></script>

<script type="text/javascript">
	//判断用户是否登录
	$(document).ready(function(){
  	if($("#dcext").text() == ".doc" || $("#dcext").text() == ".xls" || 
  				$("#dcext").text() == ".vsd" || $("#dcext").text() == ".ppt" ||
  				$("#dcext").text() == ".docx" || $("#dcext").text() == ".xlsx" || 
  				$("#dcext").text() == ".vsdx" || $("#dcext").text() == ".pptx"){
  			//office全家桶预览未实现.....
  			
  		}else if($("#dcext").text() == ".pdf"){
			console.log("this is pdf file");
			console.log($("#dcext").text());
			window.location.href="js/pdfjs/web/viewer.html?file=" + $("#dcpath").text();
  		}else if($("#dcext").text() == ".js" || $("#dcext").text() == ".txt"){
			console.log("this is text file");
			window.location.href= $("#dcpath").text();
  		}
  	
  	
  	
	});

</script>
</body>
</html>


