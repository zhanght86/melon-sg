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
		<h3><fmt:message key="asset.device.listOrgan.title"/></h3>
	 	<p>
			<fmt:message key="device.listOrgans.description"/>
		</p>
	</div>
	<div class="row">
		<div class="col-xs-4">
			<div class="melon-protal-dataArea">
				<fmt:message key="asset.device.listOrgan.assetCount">
					<fmt:param> <span id="deviceSums" class="userRate"></span> </fmt:param>
					<fmt:param><span class="userRate"> ${fn:length(devices)} </span></fmt:param>
				</fmt:message>
			</div>
			
			<a href="list" class="left-charts-a">
				<fmt:message key="asset.device.all"/>
			</a>
			<a href="listByVirtual"  class="left-charts-a">
				<fmt:message key="asset.device.virtual"/>
			</a>
			<a href="listByHasIp"  class="left-charts-a">
				<fmt:message key="asset.device.netType"/>
			</a>
			
			<br/>
			<a href="listByEnvi"  class="left-charts-a">
				<fmt:message key="asset.device.enviView"/>
			</a>
			<a href="listByDomain"  class="left-charts-a">
				<fmt:message key="asset.device.domainView"/>
			</a>
			<a href="listByDeviceInfo"  class="left-charts-a">
				<fmt:message key="asset.device.infoView"/>
			</a>
			<br/>
			<a href="listByOrgan"  class="left-charts-a">
				<fmt:message key="asset.device.organView"/>
			</a>
			<a href="listByType"  class="left-charts-a">
				<fmt:message key="asset.device.typeView"/>
			</a>
			<a href="byDomainTopo"  class="left-charts-a">
				安全域拓扑图
			</a>
			
			<c:url value='/asset/lasDevice/list' var="listlas" />
			<a href="${listlas }"  class="left-charts-a">
				同步设备
			</a>
			<a href="<c:url value='create'/>" class="btn btn-primary portal-chart-a">
				<fmt:message key='button.addDevice'/>
			</a>
		</div>
		<div class="col-xs-8">
			<div id="chart" style="height: 290px;"></div>
		</div>
	</div>
</div>

<div class="row" style="margin-right: -20px; margin-left: 0px;">
	<c:forEach items="${devices}" var="device">
		<div class="col-sm-4 organ">
   			<div class="thumbnail">
   				<div class="caption">
					<a href='<c:url value="/asset/device/listByOrgan/${device[4]}"/>'>
						<h4>${device[0]}
							<fmt:message key="asset.device.count">
								<fmt:param>${device[1]}</fmt:param>
							</fmt:message>
						</h4>
					</a>
					<p>
						<fmt:message key="asset.deviceshow.virtuals" />${device[2]}
					</p>
					<p>
						<fmt:message key="asset.deviceshow.useRate" />${device[3]}
					</p>
				</div>
   			</div>
   		</div>
	</c:forEach>
</div>

<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
	var datas = <mh:toJson value="${devices}"/>,
		rotate = 0,
		count = 0,
		end = 100;
	$(document).ready(function() {
		//console.log(datas);
		if(datas.length > 5) {
			rotate = 30;
			end = Math.ceil((5/datas.length) * 100);
		}
		//获取总人数
		$.each(datas, function(index, item) {
			count += item[1];
		});
		$("#deviceSums").text(count);
		require(["chart/mcharts"], function(mcharts) {
			
			//准备图形
			var configs = {
				el : "#chart",
				theme : "blue",
				title : '<fmt:message key="asset.device.listOrgan.chart"/>',
				rotate : false,
				data : datas,
				dataZoom : true,
				ready : function() {
					//屏蔽Legend
					this.defaults.xAxis[0].axisLabel.rotate = rotate;//旋转标签
					this.defaults.dataZoom.end = end;
					this.defaults.dataZoom.zoomLock = false;//限定只显示5个
					this.defaults.yAxis[0].name= "<fmt:message key='device.listOrgans.chart.yAxisName'/>";
					$.extend(this.defaults.title,{	//标题样式
						x:"center",
						y:"bottom",
						textStyle:{
							color: '#333',
							fontSize:14
						}
					});
					$.extend(this.defaults.yAxis[0],{	//纵坐标
						nameTextStyle:{
							color:"#808080"
						},
						axisLabel:{
							textStyle:{
								color:"#808080"
							}
						}
					});
					$.extend(this.defaults.xAxis[0],{	//横坐标
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
								type : "max", 
								name : '<fmt:message key="hint.max"/>'
							},{
								type : "min", 
								name : '<fmt:message key="hint.min"/>'
							}],
							itemStyle:{
								 normal:{
			                        color:'#ffaa80'
			                    }
							}
						},
						markLine : {
							data : [{
								type : "average", 
								name : '<fmt:message key="hint.averge"/>'
							}]
						}
					});
				},
				seriesConfig : [{
					name : '<fmt:message key="asset.device.notvirtual"/>',
					type : 'bar',
					group : "device",
					category : "0",
					value : "1"
				},{
					name : '<fmt:message key="asset.device.virtual"/>',
					type : 'line',
					group : "device",
					category : "0",
					value : "2"
				}]
			};
			//生成线形趋势图
			new mcharts.Column(configs);
		});
	});
</script>