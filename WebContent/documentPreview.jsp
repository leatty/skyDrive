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
	<p id="dcpath" hidden="true"><s:property value="document_path"/></p>
	<p id="dcext" hidden="true"><s:property value="virfile_ext"/></p>










	
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/pdfjs/web/viewer.js"></script>
<script type="text/javascript" src="js/pdfjs/build/pdf.js"></script>
<script type="text/javascript" src="js/pdfjs/build/pdf.worker.js"></script>

<script type="text/javascript">
	//判断用户是否登录
	$(document).ready(function(){
  	if($("#dcext").text() == ".txt"){
		  console.log("this is txt file");
		  window.location.href="http://localhost:8080/file" + $("#dcpath").text();
		  //window.location.href="js/pdfjs/web/viewer.html?"+"http://localhost:8080/file" + $("#dcpath").text();
  		}else if($("#dcext").text() == ".doc" || $("#dcext").text() == ".xls" || 
  				$("#dcext").text() == ".vsd" || $("#dcext").text() == ".ppt" ||
  				$("#dcext").text() == ".docx" || $("#dcext").text() == ".xlsx" || 
  				$("#dcext").text() == ".vsdx" || $("#dcext").text() == ".pptx"){
  			
  		}else if($("#dcext").text() == ".pdf"){
			console.log("this is pdf file");
			console.log($("#dcext").text());
			window.location.href="js/pdfjs/web/viewer.html?file="+"http://localhost:8080/file" + $("#dcpath").text();
  		}else if($("#dcext").text() == ".js"){
			console.log("this is javascript file");
			window.location.href="http://localhost:8080/file" + $("#dcpath").text();
  		}
  	
  	
  	
	});

</script>
</body>
</html>


