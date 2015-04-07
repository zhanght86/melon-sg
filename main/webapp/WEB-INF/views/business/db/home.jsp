<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>

<style type="text/css">
	.tab-content{
		padding:20px 0px;
	}
	#levelSource{
		float:right;
		margin-top:4px;
	}
	.page-header{
		border-bottom:0px;
	}
	.block-design{
		margin:0px;
	}
	.chart{
		height:300px;
		margin-bottom:20px;
	}
	.numBg{
		background-color: #f55549;
	}
</style>

<div class="portal-main">
	<div class="page-header">
		<h3><fmt:message key="db.home.title"/></h3>
		<p>
			<fmt:message key="db.home.desc"/>
		</p>
	</div>
	<div class="row">
		<div class="col-xs-4">
			<div class="melon-protal-dataArea">
				<fmt:message key="db.home.countAssert" >
					<fmt:param> <span id="Assert" class="userRate">7</span></fmt:param>
					<fmt:param><span id="AllAssert" class="userRate">10</span></fmt:param>
				</fmt:message>
			</div>
			<a href="<c:url value='#'/>" class="btn btn-primary portal-chart-a">
				<fmt:message key='db.home.selectAll'/>
			</a>
		</div>
		<!-- 今年已评估信息系统 -->
		<div class="col-xs-4">
			<div id="chart3" style="height:300px;"></div>
		</div>
		<!-- 总评估信息系统 -->
		<div class="col-xs-4">
			<div id="chart4" style="height:300px;"></div>
		</div>
	</div>
	<div class="row">
		<ul class="nav nav-tabs" role="tablist">
			<li class="active"><a href="#profile" role="tab" data-toggle="tab">按年度查看</a></li>
			<li><a href="#messages" role="tab" data-toggle="tab">按系统级别查看</a></li>
			<li><a href="#settings" role="tab" data-toggle="tab">按单位查看</a></li>
		</ul>
		<div class="tab-content col-xs-12">
			<div class="tab-pane active" id="profile">
				<div class="row block-design">
					<div id="timeBucketChart" class="chart"></div>
				</div>
				<div class="row block-design">
					<div id="timeChart" class="chart"></div>
				</div>
			</div>
			<div class="tab-pane" id="messages">
				
			</div>
			<div class="tab-pane" id="settings">
				<div class="row" style="margin-right: -20px; margin-left: 0px;">
					<%-- <c:forEach items="${devices}" var="device"> --%>
						<div class="col-sm-4 organ">
				   			<div class="thumbnail">
				   				<div class="caption">
									<a href="#">
										<h4>国税总局
											[10]套
										</h4>
									</a>
									<p>
										一级系统：1套
									</p>
									<p>
										二级系统：6套
									</p>
								</div>
				   			</div>
				   		</div>
				   		<div class="col-sm-4 organ">
				   			<div class="thumbnail">
				   				<div class="caption">
									<a href="#">
										<h4>山东单位
											[10]套
										</h4>
									</a>
									<p>
										一级系统：1套
									</p>
									<p>
										二级系统：6套
									</p>
								</div>
				   			</div>
				   		</div>
					<%-- </c:forEach> --%>
				</div>
			</div>
		</div>
		
	</div>
</div>

<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		require(["chart/mcharts"], function(mcharts) {
			/* 今年已评估信息系统 */
			var configs = {
					el : "#chart3",
					theme : "blue",
					title : "<fmt:message key='db.home.readyAssert'/>",
					rotate : false,
					data : "<c:url value='/statics/json/db_assert.json'/>",
					dataZoom : false,
					ready : function() {
						//标题的样式 
						$.extend(this.defaults.title, {
							x : 'center',
							y : 'bottom',
							textStyle : {
								fontSize : 12,
								color : "#808080"
							}
						});
					},
					seriesConfig : [{
						name : '系统定级',
						label : "0",
						type : 'pie',
						center : ['50%', 150],
					    radius : 50,
						stack : "group",
						category : "0",
						value : 1,
						itemStyle:{
					        normal:{
								label:{
								    show: true,
								    formatter: '{b} : {c}'
								},
								labelLine :{show:true}
				            }
				        }
					}]
				};
				var pie = new mcharts.Pie(configs);

				/* 总评估信息系统 */
				var configs = {
						el : "#chart4",
						theme : "blue",
						title : "<fmt:message key='db.home.allAssert'/>",
						rotate : false,
						data : "<c:url value='/statics/json/db_assert.json'/>",
						dataZoom : false,
						ready : function() {
							//标题的样式 
							$.extend(this.defaults.title, {
								x : 'center',
								y : 'bottom',
								textStyle : {
									fontSize : 12,
									color : "#808080"
								}
							});
						},
						seriesConfig : [{
							name : '系统定级',
							label : "0",
							type : 'pie',
							center : ['50%', 150],
						    radius : 50,
							stack : "group",
							category : "0",
							value : 1,
							itemStyle:{
						        normal:{
									label:{
									    show: true,
									    formatter: '{b} : {c}'
									},
									labelLine :{show:true}
					            }
					        }
						}]
					};
					var pie = new mcharts.Pie(configs);

					/* 按年度查看已评估和未评估信息系统 */
					var configs = {
							el : "#timeBucketChart",
							theme : "blue",
							title : "近七年已评估和未评估信息系统",
							rotate : false,
							data : [[2014,10,7],[2013,20,10],
							        [2012,15,5],[2011,12,3],
							        [2010,30,8],[2009,20,4]
							        ],
							dataZoom : false,
							ready : function() {
								this.defaults.yAxis[0].name="数量";
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
								//标题的样式 
								$.extend(this.defaults.title, {
									x : 'center',
									y : 'bottom',
									textStyle : {
										fontSize : 12,
										color : "#808080"
									}
								});
							},
							seriesConfig : [{
								name : '已评估数量',
								type : 'bar',    //以什么类型展示    
								stack : "group",
								category : "0",//横坐标
								value : "1"  //纵坐标
							},{
								name : '未评估数量',
								type : 'line',
								stack : "group",
								category : "0",
								value : "2"
							}]
						};
						new mcharts.Column(configs);
						
						/* 按年度查看已上报和未上报任务 */
						var configs = {
								el : "#timeChart",
								theme : "blue",
								title : "近七年已上报和未上报任务",
								rotate : false,
								data : [[2014,20,7],[2013,40,10],
								        [2012,30,5],[2011,60,3],
								        [2010,30,8],[2009,50,4]
								        ],
								dataZoom : false,
								ready : function() {
									this.defaults.yAxis[0].name="数量";
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
									//标题的样式 
									$.extend(this.defaults.title, {
										x : 'center',
										y : 'bottom',
										textStyle : {
											fontSize : 12,
											color : "#808080"
										}
									});
								},
								seriesConfig : [{
									name : '已上报数量',
									type : 'bar',    //以什么类型展示    
									stack : "group",
									category : "0",//横坐标
									value : "1"  //纵坐标
								},{
									name : '未上报数量',
									type : 'line',
									stack : "group",
									category : "0",
									value : "2"
								}]
							};
							new mcharts.Column(configs);
						
		});
	});
</script>
