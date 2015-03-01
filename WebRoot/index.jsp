<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>探测</title>
	<script src="./js/jquery.js"></script>
	<script src="./js/main.js"></script>
	<style type="text/css">
		html:-webkit-full-screen {
		    background: black;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			$("#view-fullscreen").click(function(){
				var docElm = document.documentElement;
				fullScreen(docElm);
			});
			//获取Canvas对象(画布)
			canvas = document.getElementById("myCanvas");
			//获取对应的CanvasRenderingContext2D对象(画笔)
		    ctx = canvas.getContext("2d");
		  	//圆心坐标
			o={
				x:500,
				y:400
			};
		  	//从节点的经纬度坐标
		  	baseStation=null;
			subStation1=null;
			subStation2=null;
		  	//从节点像素坐标
		  	subStation1XY={x:500,y:400};
		  	subStation2XY={x:500,y:400};
			drawPic(o);
			drawLine(500, 0, 500, 400);
			//旋转角度
			angle=1;
			myRotate();
			//从节点1的地理位置
			
			//从节点2的地理位置
			refreshStation();
		})
	</script>
  </head>
  
  <body style="margin:0px;background-color:#000;">
  	<div style="float:left;width:1000px;">
  		<div id="subStation1Data" style="display:none;width:150px;height:70px;background:rgb(129, 189, 140);opacity:0.5;z-index:3;position: absolute;font-size:10px;">
  			<div style="padding-top:2px;padding-left:5px;">从节点1</div>
  			<div style="padding-left:10px;">
  				<table>
  					<thead><tr><td  style="width:90px;"></td><td style="width:50px;"></td></tr></thead>
  					<tr>
  						<td>距主站距离:</td>
  						<td><input style="width:50px;" id="subStation1DistanceDiv" type="text" readonly="readonly"></td>
  					</tr>
  					<tr>
  						<td>速度:</td>
  						<td><input style="width:50px;" id="subStation1VDiv" type="text" readonly="readonly"></td>
  					</tr>
  				</table>
  			</div>
  		</div>
  		<div id="subStation2Data" style="display:none;width:150px;height:70px;background:#228B22;opacity:0.5;z-index:3;position: absolute;font-size:10px;">
  			<div style="padding-top:2px;padding-left:5px;">从节点2</div>
  			<div style="padding-left:10px;">
  				<table>
  					<thead><tr><td  style="width:90px;"></td><td style="width:50px;"></td></tr></thead>
  					<tr>
  						<td>距主站距离:</td>
  						<td><input style="width:50px;" id="subStation2DistanceDiv" type="text" readonly="readonly"></td>
  					</tr>
  					<tr>
  						<td>速度:</td>
  						<td><input style="width:50px;" id="subStation2VDiv" type="text" readonly="readonly"></td>
  					</tr>
  				</table>
  			</div>
  		</div>
  		<canvas id="myCanvas" width="1000px" height="800px" style="background:black;"></canvas>
  	</div>
    <div style="height:100%;float:left;padding-top:30px;padding-left:20px;padding-right:20px;background:url(./img/Background.jpg);background-size:100% 100%;">
    	<button style="margin-left:80px;cursor:pointer;width:60px;height:30px;border-radius:3px;" id="view-fullscreen">全屏</button>
    	<table style="color: #00ff00;font-weight: bolder;">
    		<tr>
    			<td colspan="2">主节点 :</td>
    		</tr>
    		<tr>
    			<td>经度 :</td>
    			<td><input type="text" id="bassStation_longitude" readonly/></td>
    		</tr>
    		<tr>
    			<td>维度 :</td>
    			<td><input type="text" id="bassStation_latitude" readonly/></td>
    		</tr>
    		<tr>
    			<td>海拔 :</td>
    			<td><input type="text" id="bassStation_altitude" readonly/></td>
    		</tr>
    	</table>
    	<hr>
    	<table style="color: #00ff00;font-weight: bolder;">
    		<tr>
    			<td colspan="2">从节点1:</td>
    		</tr>
    		<tr>
    			<td>经度 :</td>
    			<td><input type="text" id="subStation1_longitude" readonly/></td>
    		</tr>
    		<tr>
    			<td>维度 :</td>
    			<td><input type="text" id="subStation1_latitude" readonly/></td>
    		</tr>
    		<tr>
    			<td>海拔 :</td>
    			<td><input type="text" id="subStation1_altitude" readonly/></td>
    		</tr>
    	</table>
    	<hr>
    	<table style="color: #00ff00;font-weight: bolder;">
    		<tr>
    			<td colspan="2">从节点2:</td>
    		</tr>
    		<tr>
    			<td>经度 :</td>
    			<td><input type="text" id="subStation2_longitude" readonly/></td>
    		</tr>
    		<tr>
    			<td>维度 :</td>
    			<td><input type="text" id="subStation2_latitude" readonly/></td>
    		</tr>
    		<tr>
    			<td>海拔 :</td>
    			<td><input type="text" id="subStation2_altitude" readonly/></td>
    		</tr>
    	</table>
    	<hr>
    </div>
  </body>
</html>
