<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<link rel="stylesheet" href="<mh:theme code='jquery.jqGrid.css'/>" media="all" />

<div class="portal-main">
	<div class="page-header">
	  <p>
	  	<fmt:message key="melon.staff.title"/>
	  </p>
	</div>
	<div class="row">
		<div class="col-xs-4">
			<div class="melon-protal-dataArea">
				<fmt:message key="melon.staff.inter.hint">
					<fmt:param><span class="userRate">${interCount}</span></fmt:param>
				</fmt:message>
			</div>
		</div>
		<div class="col-xs-8">
			<div id="chartColumn" style="height: 290px;"></div>
		</div>
	</div>
</div>
<div class="panel">
	<div class="page-header">
		<p id="interTable" style="color: #808080;font-size: 12px;line-height: 20px;margin-bottom: 0;"><fmt:message key="melon.staff.interTable"/></p>
	</div>
	<div class="panel-body" id="listBody" style="padding: 0">
	</div>
</div>

<script type="text/javascript" src="<mh:theme code='jquery.jqGrid.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxgrid.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#listBody").load("<c:url value='/staff/inter/load' />");
	
	require(["chart/mcharts"], function(mcharts) {
		
		var datas = <mh:toJson value="${datas}" />;
		//准备图形
		var configs = {
			el : "#chartColumn",
			theme : "blue",
			title : '<fmt:message key="melon.staff.inter.chart"/>',
			rotate : false,
			data : datas,
			dataZoom : false,
			ready : function() {
				
				$.extend(this.defaults.dataZoom, {
					orient  : 'vertical'
				});
				
				$.extend(this.defaults.title,{	//标题样式
					x:"center",
					y:"bottom",
					textStyle:{
						color: '#333',
						fontSize:14
					}
				});
				//纵坐标
				$.extend(this.defaults.yAxis[0],{	
					name : "<fmt:message key='melon.staff.inter.ylabel'/>",
					nameTextStyle:{
						color:"#808080"
					},
					axisLabel:{
						textStyle:{
							color:"#808080"
						}
					}
				});
				//横坐标
				$.extend(this.defaults.xAxis[0],{	
					axisLabel:{
						textStyle:{
							color:"#808080",
							fontSize:10
						},
						rotate : 15
					}
				});
				//添加辅助线
				$.extend(this.defaults.series[0], {
					markPoint : {
						data : [{
							type : "max", name : '<fmt:message key="hint.max"/>'
						},{
							type : "min", name : '<fmt:message key="hint.min"/>'
						}],
						itemStyle:{
							 normal:{
		                        color:'#ffaa80'
		                    }
						}
					},
					markLine : {
						data : [{
							type : "average", name : '<fmt:message key="hint.averge"/>'
						}]
					}
				});
			},
			seriesConfig : [{
				name : '<fmt:message key="melon.staff.inter.ylabel"/>',
				type : 'bar',
				category : "0",
				value : "1"
			}]
		};
		//生成线形趋势图
		new mcharts.Column(configs);
	});
});	

</script>





