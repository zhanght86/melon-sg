<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="portal-main">
	<div class="page-header">
		<h3><fmt:message key="asset.assetGroup.root"/></h3>
	 	<p>
			<fmt:message key="group.listOrgans.description"/>
		</p>
	</div>
	<div class="row">
		<div class="col-xs-4">
			<div class="melon-protal-dataArea">
				<fmt:message key="asset.group.listOrgan.groupCount">
					<fmt:param><span class="userRate">${fn:length(homeViewDb)}</span></fmt:param>
				</fmt:message>
			</div>	
				<a href="listByPol" class="left-charts-a">
					<fmt:message key="asset.group.organView"/>
				</a>
				
				<a href="listByType" class="left-charts-a">
					<fmt:message key="asset.group.typeView"/>
				</a>
				
				<a href="<c:url value='tree'/>" class="btn btn-primary portal-chart-a">
					<fmt:message key='asset.group.all'/>
				</a>
			
		</div>
		<div class="col-xs-8">
			<div id="chart" style="height: 290px;"></div>
		</div>
	</div>
</div>
</div>
<div class="row" style="margin:20px -20px 0px 0px;">
	<c:forEach items="${homeViewDb}" var="info">
		<div class="col-sm-4 organ">
   			<div class="thumbnail">
   				<div class="caption">
					<a href="<c:url value="/asset/group/byGroupId/${info[0]}"/>">
						<h4>${info[1]}</h4>
					</a>
					<p>
						<fmt:message key="asset.device.notvirtual" />：${info[2]}
					</p>
					<p>
						<fmt:message key="info.system.infoCount" />：${info[3]}
					</p>
				</div>
   			</div>
   		</div>
	</c:forEach>
</div>

<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
	var datas = <mh:toJson value="${homeViewDb}"/>,
		rotate = 0,
		count = 0,
		end = 100;
	$(document).ready(function() {
		if(datas.length > 5) {
			rotate = 30;
			end = Math.round((5/datas.length) * 100);
		}
		//获取总个数
		$.each(datas, function(index, item) {
			count += item[1];
		});
		$("#infoSums").text(count);
		require(["chart/mcharts"], function(mcharts) {
			//准备图形
			var configs = {
				el : "#chart",
				theme : "blue",
				title : '<fmt:message key="info.system.listOrgan.chart"/>',
				rotate : false,
				data : datas,
				dataZoom : true,
				ready : function() {
					//屏蔽Legend
					this.defaults.xAxis[0].axisLabel.rotate = rotate;//旋转标签
					this.defaults.dataZoom.end = end;
					this.defaults.dataZoom.zoomLock = false;//限定只显示5个
					this.defaults.yAxis[0].name= "<fmt:message key='infoSystem.listOrgans.chart.yAxisName'/>";
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
								color:"#808080"
							}
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
					name : '<fmt:message key="info.system.infoCount"/>',
					type : 'bar',
					group : "deviceNum",
					category : "1",
					value : "2"
				},{
					name : '<fmt:message key="asset.device.notvirtual"/>',
					type : 'line',
					group : "infoNum",
					category : "1",
					value : "3"
				}]
			};
			//生成线形趋势图
			new mcharts.Column(configs);
		});
	});
</script>