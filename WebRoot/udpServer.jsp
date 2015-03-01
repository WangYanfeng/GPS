<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>UDP Server</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#log{
			margin-top:10px;
			padding:10px;
			float:left;
			margin-left:20px;
			width:900px;
			height:400px;
			color:white;
			background-color:black;
			overflow-y:auto;
		}
	</style>
  </head>
  
  <body>
  	<div style="margin-left:350px;">
  		<%
  			if(request.getAttribute("isServerOn")==null||((Boolean)request.getAttribute("isServerOn")==false)){
		%>
			<form action="./ServerController" method="post">
				<input style="display:none;" type="text" name="run" value=1>
				<input style="width:150px;height:30px;" type="submit" value="启动服务器">
			</form>
		<%
  			}else{
  		%>
  			<form action="./ServerController" method="post">
				<input style="display:none;" type="text" name="run" value=0>
				<input style="width:150px;height:30px;background-color:red;" type="submit" value="关闭服务器">
			</form>
  		<%
  			}
  		%>
  	</div>
    <div >
    	<div>Server Log:</div>
    	<div id="log">
    		Server is running...
    	</div>
    </div>
  </body>
</html>
