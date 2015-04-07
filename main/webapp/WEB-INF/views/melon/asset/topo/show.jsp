<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<script type="text/javascript" src="<mh:theme code='jquery.jsPlumb.js'/>"></script>
<style type="text/css">
	#flowChart .panel-body{
		position: relative;
		height : 500px;
	}
		
	.melon-device {
		position : absolute;
		height : 100px;
		width : 100px;
	}
</style>
<div class="panel panel-default"  id="flowChart">
	<div class="panel-heading">
		流程图
	</div>
	<div class="panel-body" >
		<div class="melon-device alert alert-info" id="start" style="top : 400px;left : 400px;">
			START
		</div>
		<div class="melon-device alert alert-info" id="end">
			END
		</div>	
	</div>
	<div class="panel-footer">
		<button class="btn btn-default" id="hide">
			隐藏
		</button>
		<button class="btn btn-default" id="show">
			显示
		</button>
	</div>
</div>
<script type="text/javascript">
	//必须等到图形DOM加载完成后
	jsPlumb.bind("ready", function() {
		var instance = jsPlumb.getInstance({
			container : $("#flowChart"),
			//此处样式的优先级最高
			PaintStyle:{
				lineWidth:3,
				strokeStyle:"blue",
				outlineColor:"black",
				outlineWidth:1
			},
			Connector:[ "Bezier", { curviness: 90 } ],
			Endpoint:[ "Dot", { radius:5 } ],
			EndpointStyle : { fillStyle: "#567567"  },
			Anchor : [ 0.5, 0.5, 1, 1 ]
		});
		//载入并覆盖默认的配置,不能覆盖getInstance的样式
		instance.importDefaults({
			PaintStyle : {
				lineWidth : 13,
				strokeStyle : 'rgba(200,0,0,0.5)'
			},
			DragOptions : {
				cursor : "crosshair"
			},
			Endpoints : [["Dot", {
				radius : 7
			}], ["Dot", {
				radius : 11
			}]],
			EndpointStyles : [{
				fillStyle : "#225588"
			}, {
				fillStyle : "#558822"
			}]
		});
		//
		instance.draggable($(".melon-device"));
		instance.connect({
			target : $("#start"),
			source : $("#end")
		});
		instance.setZoom(0.75);
		$("#show").on("click", function(event) {
			
		});
		//instance.repaint();
		//$(".end-point").draggable();
		//当改变了内嵌对象的位置,需要重新计算容器的位置
		//$("#container .header").hide();    // hide the header bar. this will alter the offset of the other child elements...
		//jsPlumb.recalculateOffsets("container"); 
	});
</script>
