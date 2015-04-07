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
		<h3><fmt:message key="security.account.listOrgan.title"/></h3>
	 	<p>
			<fmt:message key="organ.listOrgans.description"/>
		</p>
	</div>
	<div class="row">
		<div class="col-xs-4">
			<div class="melon-protal-dataArea">
				<fmt:message key="security.account.listOrgan.organCount">
					<fmt:param><strong class="userRate"> ${fn:length(organs)} </strong></fmt:param>
				</fmt:message><br/>
				<fmt:message key="security.account.listOrgan.accountSums">
					<fmt:param><strong id="accountSums" class="userRate"></strong></fmt:param>
				</fmt:message>
			</div>
			
			<a href="<c:url value='/security/account/list'/>" class="left-charts-a">
				<fmt:message key='organ.listOrgans.userList'/>
			</a>
			<a href="<c:url value='/security/account/online'/>" class="left-charts-a">
				<fmt:message key='organ.listOrgans.onlineUser'/>
			</a>
			<a href="<c:url value='/system/logger/activeUser'/>" class="left-charts-a" >
				<fmt:message key="organ.listOrgans.activeUser"/>
			</a>
			
			<a href="<c:url value='/security/account/create' />" class="btn btn-primary portal-chart-a"><fmt:message key='organ.listOrgans.createUser'/></a>
		</div>
		<div class="col-xs-8">
			<div id="chart" style="height: 290px;"></div>
		</div>
	</div>
</div>
<div class="row" style="margin-right: -20px; margin-left: 0px;">
	<c:forEach items="${organs}" var="organ">
		<div class="col-sm-4 organ">
   			<div class="thumbnail">
   				<div class="caption">
					<a href="<c:url value="/security/account/listUsers/${organ[0]}"/>">
						<h4>${organ[1]}
							<fmt:message key="organ.organization.account">
								<fmt:param>${organ[4]}</fmt:param>
							</fmt:message>
						</h4>
					</a>
					<p>
						<fmt:message key="organ.organization.organPrin" />${organ[2]}
					</p>
					<p>
						<fmt:message key="organ.organization.organPrinTel" />${organ[3]}
					</p>
				</div>
   			</div>
   		</div>
	</c:forEach>
</div>

<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
	var datas = <mh:toJson value="${organs}"/>,
		rotate = 0,
		count = 0,
		end = 100;
	
	$(document).ready(function() {
		if(datas.length > 5) {
			rotate = 30;
			end = Math.round((5/datas.length) * 100);
		}
		//获取总人数
		$.each(datas, function(index, item) {
			count += item[4];
		});
		$("#accountSums").text(count);
		require(["chart/mcharts"], function(mcharts) {
			//准备图形
			var configs = {
				el : "#chart",
				theme : "blue",
				title : '<fmt:message key="security.account.listOrgan.chart"/>',
				rotate : false,
				data : datas,
				dataZoom : true,
				ready : function() {
					//屏蔽Legend
					this.defaults.legend = false;
					this.defaults.xAxis[0].axisLabel.rotate = rotate;//旋转标签
					this.defaults.dataZoom.end = end;
					this.defaults.dataZoom.zoomLock = false;//限定只显示5个
					this.defaults.yAxis[0].name= "<fmt:message key='organ.listOrgans.chart.yAxisName'/>";
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
								type : "max", name : '<fmt:message key="hint.max"/>'
							},{
								type : "min", name : '<fmt:message key="hint.min"/>'
							}],
							itemStyle:{
								emphasis: {
									color:"#2e9bd1",
								 },
								 normal:{
			                        color:'#ffaa80'
			                    }
							},
						},
						markLine : {
							data : [{
								type : "average", name : '<fmt:message key="hint.averge"/>'
							}],
							itemStyle:{
								normal: {
									lineStyle :{
										color:"#2e9bd1",
										type:"dashed",
										width:2
									}
								 }
							}
						}
					});
				},
				seriesConfig : [{
					name : '<fmt:message key="security.account.listOrgan.label"/>',
					type : 'bar',
					category : "1",
					value : "4"
				}]
			};
			//生成线形趋势图
			new mcharts.Column(configs);
		});
	});
</script>