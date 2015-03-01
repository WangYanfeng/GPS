function refreshStation(){
	$.ajax({
		url:"./IndexServlet",
		type:"post",
		dataType:"json",
		success:function(data){
			//显示数据
			$.each(data,function(i,item){
				if(item.stationNo==1){
					$("#bassStation_longitude").val(item.longitude);
					$("#bassStation_latitude").val(item.latitude);
					$("#bassStation_altitude").val(item.altitude);
					baseStation=item;
				}else if(item.stationNo==2){
					$("#subStation1_longitude").val(item.longitude);
					$("#subStation1_latitude").val(item.latitude);
					$("#subStation1_altitude").val(item.altitude);
				}else if(item.stationNo==3){
					$("#subStation2_longitude").val(item.longitude);
					$("#subStation2_latitude").val(item.latitude);
					$("#subStation2_altitude").val(item.altitude);
				}
			});
			//计算
			calculatePoint(data);
		},
		error:function(data){
			//alert("数据加载失败！");
		}
	});
	setTimeout("refreshStation()",1000);
}
//计算
function calculatePoint(data){
	var subStation1V=null;
	var subStation2V=null;
	var subStationDistance1=null;
	var subStationDistance2=null;
	$.each(data,function(i,item){
		if(item.stationNo==2){
			if(subStation1!=null){
				//当上次经纬度数据不为null时，计算速度值
				subStation1V=calculateV(subStation1, item);
			}
			//计算距离
			subStationDistance1=calculateDistance(baseStation, item);
			subStation1=item;
		}else if(item.stationNo==3){
			if(subStation2!=null){
				//当上次经纬度数据不为null时，计算速度值
				subStation2V=calculateV(subStation2, item);
			}
			//计算距离
			subStationDistance2=calculateDistance(baseStation, item);
			subStation2=item;
		}
	});
	//计算点的坐标
	if(subStation1!=null){
		var s1=subStation1.longitude;
		s1=s1.substring(0,s1.length-1);
		var base=baseStation.longitude;
		base=base.substring(0,base.length-1);
		var y=o.y+(parseFloat(s1)-parseFloat(base))*3700;
		
		s1=subStation1.latitude;
		s1=s1.substring(0,s1.length-1);
		base=baseStation.latitude;
		base=base.substring(0,base.length-1);
		var x=o.x+(parseFloat(s1)-parseFloat(base))*3700;
		subStation1XY.x=x;
		subStation1XY.y=y;
		
		//显示悬浮框数据
		showData(1,subStationDistance1, subStation1V, subStation1XY);
	}
	if(subStation2!=null){
		var s2=subStation2.longitude;
		s2=s2.substring(0,s2.length-1);
		var base2=baseStation.longitude;
		base2=base2.substring(0,base2.length-1);
		var y2=o.y+(parseFloat(s2)-parseFloat(base2))*3700;
		
		s2=subStation2.latitude;
		s2=s2.substring(0,s2.length-1);
		base2=baseStation.latitude;
		base2=base2.substring(0,base2.length-1);
		var x2=o.x+(parseFloat(s2)-parseFloat(base2))*3700;
		subStation2XY.x=x2;
		subStation2XY.y=y2;
		
		//显示悬浮框数据
		showData(2,subStationDistance2, subStation2V, subStation2XY);
	}
	
}
function drawPic(o){
	drawCircle(o,100);
	drawCircle(o,200);
	drawCircle(o,300);
	drawCircle(o,400);
	drawDashLine(100, 400, 900, 400);
	drawDashLine(500, 0, 500, 800);
	drawPoint(o.x,o.y);
	
	drawPoint(subStation1XY.x,subStation1XY.y);
	drawPoint(subStation2XY.x,subStation2XY.y);
}
function drawCircle(o,r){
	var circle={
			x:o.x,//圆心的x轴坐标值
			y:o.y,//圆心的y轴坐标值
			r:r,//圆的半径
	};
	//简单地检测当前浏览器是否支持Canvas对象，以免在一些不支持html5的浏览器中提示语法错误
	if(canvas.getContext){  
	    //开始一个新的绘制路径
	    ctx.beginPath();
	    //设置弧线的颜色为蓝色
	    ctx.strokeStyle = "#00ff00";
	    ctx.lineWidth=2;
	    //以canvas中的坐标点(100,100)为圆心，绘制一个半径为50px的圆形
	    ctx.arc(circle.x, circle.y, circle.r, 0, Math.PI * 2, false);    
	    //按照指定的路径绘制弧线
	    ctx.stroke();
	    ctx.closePath();
	}
}
//画直线
function drawLine(x1, y1, x2, y2){
	ctx.beginPath();
	ctx.strokeStyle = "#00ff00";
    ctx.lineWidth=3;
    ctx.moveTo(x1, y1);   
    ctx.lineTo(x2, y2);  
    ctx.stroke();
    ctx.closePath();
}
//画布上画虚线
function drawDashLine(x1, y1, x2, y2){
	ctx.lineWidth = 1;
	ctx.strokeStyle = "#00ff00";
	var dashLength=4;
	var dashLen = dashLength === undefined ? 5 : dashLength,
	  xpos = x2 - x1, //得到横向的宽度;
	  ypos = y2 - y1, //得到纵向的高度;
	  numDashes = Math.floor(Math.sqrt(xpos * xpos + ypos * ypos) / dashLen); 
	
	//利用正切获取斜边的长度除以虚线长度，得到要分为多少段;
	  for(var i=0; i<numDashes; i++){
	     if(i % 2 === 0){
	         ctx.moveTo(x1 + (xpos/numDashes) * i, y1 + (ypos/numDashes) * i); 
	         //有了横向宽度和多少段，得出每一段是多长，起点 + 每段长度 * i = 要绘制的起点；
	      }else{
	          ctx.lineTo(x1 + (xpos/numDashes) * i, y1 + (ypos/numDashes) * i);
	      }
	   }
	  ctx.stroke();
}

//画点
function drawPoint(x,y){
	ctx.fillStyle="#00ff00";
	ctx.beginPath();
	ctx.arc(x,y,5,0,Math.PI*2,false);
	ctx.closePath();
	ctx.fill();
}
//扫描旋转
function myRotate()
{
	clearScreen();
	/*圆心坐标
	o={
		x:500,
		y:300
	};*/
	var angle_pre=((angle-1)*1)%360;
	var angle_now=(angle*(-1))%360;
	if(angle*(-1)%360==0){
		angle=0;
	}
	ctx.translate(500,400);
	ctx.rotate( (Math.PI / 180) * angle_pre);
	ctx.translate(-500,-400);
	drawPic(o);
	
	//drawLine(500, 0, 500, 300);
	ctx.translate(500,400); 
	ctx.rotate( (Math.PI / 180) * angle_now);
	ctx.translate(-500,-400);
	drawLine(500, 0, 500, 400);
	
	angle=angle+1;
	setTimeout("myRotate()",10);
}
//清除屏幕
function clearScreen(){
	ctx.fillStyle="black";
	ctx.fillRect(0,0,1000,600);
}
//浏览器全屏显示
function fullScreen(docElm){
	//W3C 
	if (docElm.requestFullscreen) {
        docElm.requestFullscreen();
    }
    else if (docElm.msRequestFullscreen) {
        docElm.msRequestFullscreen();
    }
    else if (docElm.mozRequestFullScreen) {
        docElm.mozRequestFullScreen();
    }
    else if (docElm.webkitRequestFullScreen) {
        docElm.webkitRequestFullScreen();
    }
}
//计算距离
function calculateDistance(baseStation,subStation){
	var str=baseStation.longitude;
	var lon1=parseFloat(str.substring(0,str.length-1));
	str=baseStation.latitude;
	var lat1=parseFloat(str.substring(0,str.length-1));
	str=subStation.longitude;
	var lon2=parseFloat(str.substring(0,str.length-1));
	str=subStation.latitude;
	var lat2=parseFloat(str.substring(0,str.length-1));
	var theta = lon1 - lon2;
	var dist =  Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
			    + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
			    * Math.cos(deg2rad(theta));
	dist = Math.acos(dist);
    dist = rad2deg(dist);
    miles = dist * 60 * 1.1515;
    return (miles*1.609344).toFixed(3);
}
//计算速度
function calculateV(subStationLast,subStation){
	var dis=calculateDistance(subStationLast,subStation);
	var t=subStation.time.time-subStationLast.time.time;
	//return (dis/t).toFixed(3);
	return dis;
}
function deg2rad(degree) {
    return degree / 180 * Math.PI;
}
//将弧度转换为角度
function rad2deg(radian) {
    return radian * 180 / Math.PI;
}


//悬浮框显示数据
function showData(x,distance,v,stationXY){
	if(x==1){
		$("#subStation1Data").css('display',"block");
		$("#subStation1DistanceDiv").val(distance);
		$("#subStation1VDiv").val(v);
		$("#subStation1Data").css("top",stationXY.y+ "px");
		$("#subStation1Data").css("left",stationXY.x+ "px");
	}else if(x==2){
		$("#subStation2Data").css('display',"block");
		$("#subStation2DistanceDiv").val(distance);
		$("#subStation2VDiv").val(v);
		$("#subStation2Data").css("top",stationXY.y+ "px");
		$("#subStation2Data").css("left",stationXY.x+ "px");
	}
}