<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<style type="text/css">
	.tab-content{
		padding:20px 0px;
	}
	#levelSource{
		text-align: center;
		display:inline-block;
		margin-left:180px;
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

<sf:form modelAttribute="alarm" role="form" cssClass="form-horizontal chart-form-show">

	<sf:hidden path="id"/>
	<sf:hidden path="deviceId"/>
	<sf:hidden path="organId"/>
	<sf:hidden path="processInstanceId"/>
	
	<div class="page-header">
	  <h4><mh:value path="title"/></h4>
	</div>
	
	<div class="row">
	
		<div class="col-xs-6">
			<!-- 以仪表显示 -->
			<div id="alarm-state" style="height:220px;margin-top:20px;">
				
			</div>
			<a id="levelSource" href="<c:url value='/alarm/base/helpPage/' />${alarm.id}" >告警等级来源</a>
		</div>
		
		<div class="col-xs-6" style="padding-right:30px;">
			<div class="form-group">
				<div class="form-button-panel">
					<div class="col-xs-12 form-buttons">
						<mh:button class="btn btn-primary btn-sm">
							<fmt:message key="button.sure"/>
						</mh:button>
						
						<mh:button class="btn btn-default btn-sm">
							<fmt:message key="button.clean"/>
						</mh:button>
						
						<mh:button class="btn btn-default btn-sm">
							<fmt:message key="button.startProcess"/>
						</mh:button>
						
						<mh:button class="btn btn-default btn-sm">
							<fmt:message key="button.querySame"/>
						</mh:button>
						
						<mh:button class="btn btn-default btn-sm">
							对比
						</mh:button>
						<mh:button class="btn btn-default btn-sm">
							批注
						</mh:button>
					</div>
				</div>
			</div>
		
			<%-- 设备IP  --%>
			<div class="form-group">
				<sf:label path="deviceIp" cssClass="col-xs-3 control-label">
					<fmt:message key="alarm.deviceIp"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<div class="col-xs-6" style="padding-left : 0px;">
						<mh:value path="deviceIp"/>
					</div>
					<div class="col-xs-6" style="padding-right:0px;height:36px;">
						<p class="form-control-static" style="float:right;">
							<a href="#" class="search">
								<fmt:message key="button.query"/>
							</a>
						</p>
					</div>
				</div>
			</div>
			
			<c:if test="${!empty alarm.deviceId}">
				<div class="form-group">
					<sf:label path="deviceName" cssClass="col-xs-3 control-label">
						<fmt:message key="alarm.deviceName"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<a href='<c:url value="/asset/device/show/${alarm.deviceId}"/>'>
							<mh:value path="deviceName"/>
						</a>
					</div>
				</div>
			</c:if>
			
			<div class="form-group">
				<sf:label path="occurTime" cssClass="col-xs-3 control-label">
					<fmt:message key="alarm.occurTime"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<div class="col-xs-6" style="padding-left : 0px;">
						<mh:value path="occurTime"/>
					</div>
					<div class="col-xs-6" style="padding-right:0px;height:36px;">
						<p class="form-control-static" style="float:right;">
							<span style="font-size:12px;font-style: italic;float:right;">12天12小时</span>
						</p>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="type" cssClass="col-xs-3 control-label">
					<fmt:message key="alarm.type"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<mh:dictionary var="type" key="alarmType"/>
					<mh:value path="type" items="${type}"/>
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="organName" cssClass="col-xs-3 control-label">
					<fmt:message key="alarm.organName"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<mh:value path="organName"/>
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="from" cssClass="col-xs-3 control-label">
					<fmt:message key="alarm.from"/>
				</sf:label>
				<div class="col-xs-9 form-field">
					<div class="col-xs-6" style="padding-left : 0px;">
						<mh:dictionary var='forms' key="alarmForm"/>
						<mh:value path="from" items="${forms}"/>
					</div>
					<div class="col-xs-6"  style="padding-right : 0px;">
						<p class="form-control-static" style="float:right;">
							<a href="#">
								查看原始告警
							</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 关联信息 -->
	<div class="row" style="margin-top:40px;margin-left: 0px; margin-right: 0px;">
		<ul class="nav nav-tabs" role="tablist">
			<li class="active"><a href="#profile" role="tab" data-toggle="tab">对比分析</a></li>
			<li><a href="#messages" role="tab" data-toggle="tab">处理记录</a></li>
			<li><a href="#settings" role="tab" data-toggle="tab">知识库</a></li>
		</ul>
	
		<div class="tab-content col-xs-12">
			<!-- 对比分析 -->
			<div class="tab-pane active" id="profile">
				<div class="row block-design">
					<div id="timeBucketChart" class="chart"></div>
				</div>
				<div class="row block-design">
					<div class="col-xs-4">
						<p style="text-align: center;">区域告警数量TOP 5</p>
						<ul class="list-group">
							<li class="list-group-item">
								<a href="#">
								河北</a>
								<span class="badge pull-right">39</span>
							</li>
							<li class="list-group-item">
								<a href="#">内蒙古</a>
								<span class="badge pull-right">36</span>
							</li>
							<li class="list-group-item">
								<a href="#">四川</a>
								<span class="badge pull-right">28</span>
							</li>
							<li class="list-group-item">
								<a href="#">广东</a>
								<span class="badge pull-right">18</span>
							</li>
							<li class="list-group-item">
								<a href="#">新疆</a>
								<span class="badge pull-right">9</span>
							</li>
						</ul>
					</div>
					<div class="col-xs-8">
						<div id="alarmAreaChart" class="chart"></div>
					</div>
				</div>
				<div class="row block-design">
					<div class="col-xs-4">
						<p style="text-align: center;">此设备告警类型TOP 5</p>
						<ul class="list-group">
							<li class="list-group-item">
								<a href="#">操作记录类</a>
								<span class="badge pull-right">228</span>
							</li>
							<li class="list-group-item">
								<a href="#">系统状态类</a>
								<span class="badge pull-right">191</span>
							</li>
							<li class="list-group-item">
								<a href="#">安全预警类</a>
								<span class="badge pull-right">93</span>
							</li>
							<li class="list-group-item">
								<a href="#">信息刺探类</a>
								<span class="badge pull-right">38</span>
							</li>
							<li class="list-group-item">
								<a href="#">恶意代码类</a>
								<span class="badge pull-right">3</span>
							</li>
						</ul>
					</div>
					<div class="col-xs-8">
						<div id="alarmTypesChart" class="chart"></div>
					</div>
				</div>
				<div class="row block-design">
					<div id="alarmLevelChart" class="chart" style="height:400px;"></div>
				</div>
				<div class="row block-design">
					<div id="alarmResourceChart" class="chart"></div>
				</div>
				
				<!-- <div class="row">
					<div class="col-xs-6">
						<div id="alarmTypeChart" style="height:240px;"></div>
					</div>
					<div class="col-xs-6">
						<div id="alarmDeviceChart" style="height:240px;"></div>
					</div>
				</div> -->
			</div>
			
			<!-- 处理记录 -->
			<div class="tab-pane" id="messages">
				<div class="handle-list">
					<table class="table table-bordered">
						<tr>
							<th>编号</th>
							<th>名称</th>
							<th>处理内容</th>
							<th>处理时间</th>
						</tr>
						<c:forEach var="a" begin="1" end="5">
							<tr>
								<td>0025</td>
								<td>aaa</td>
								<td>处理设备QH98-1告警成功</td>
								<td>2014.10.11</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			
			<!-- 知识库 -->
			<div class="tab-pane" id="settings">
			
			</div>
		</div>
	</div>

</sf:form>
<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
	$(function(){
		
		require(["chart/mcharts"], function(mcharts) {
			var configs = {
				el : "#alarm-state",
				theme : "blue",
		//		title : "告警级别",
				data:[{value: '${alarm.level}'}],
				rotate : false,
				dataZoom : false,
				ready : function() {
					this.defaults.toolbox = false;
					this.defaults.legend = false;
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
		            name:'告警等级',
		            type:'gauge',
		            startAngle: 180,
		            endAngle: 0,
		            min:0,
					max:5,
		            center : ['50%', '80%'],    // 默认全局居中
		            radius : 140,
		            axisLine: {            // 坐标轴线
		                lineStyle: {       // 属性lineStyle控制线条样式
		                    width:100,
		                }
		            },
		            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
		                formatter: function(v){
		                    switch (v+''){
		                        case '0.5': return '低';
		                        case '2.5': return '中';
		                        case '4.5': return '高';
		                        default: return '';
		                    }
		                },
		                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                    color: '#fff',
		                    fontSize: 14,
		                    fontWeight: 'bolder'
		                }
		            },
		            pointer: {
		                width:10,
		                length: '90%',
		                color: 'gray'
		            },
		            title : {
		                show : true,
		                offsetCenter: [0, '-55%'],       // x, y，单位px
		                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                    color: '#fff',
		                    fontSize: 24
		                }
		            },
		            detail : {
		                show : true,
		                backgroundColor: 'rgba(0,0,0,0)',
		                borderWidth: 0,
		                borderColor: '#ccc',
		                width: 100,
		                height: 40,
		                offsetCenter: [0, -35],       // x, y，单位px
		                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                    fontSize : 24
		                }
		            },
		        }]
			};
			new mcharts.Gauge(configs);
		
			
			//告警时间段分析
			var configs = {
				el : "#timeBucketChart",
				theme : "blue",
				title : "告警时间段分析",
				rotate : false,
				data : [[2013.10,320,80],[2013.11,280,94],
				        [2013.12,304,52],[2014.1,246,28],
				        [2014.2,186,37],[2014.3,127,18]
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
				},
				seriesConfig : [{
					name : '告警总数',
					type : 'bar',    //以什么类型展示    
					stack : "group",
					category : "0",//横坐标
					value : "1"  //纵坐标
				},{
					name : '此类型告警',
					type : 'line',
					stack : "group",
					category : "0",
					value : "2"
				}]
			};
			
			new mcharts.Column(configs);
			
			//区域分析饼图
			var configs = {
				el : "#alarmAreaChart",
				theme : "blue",
				rotate : false,
				data : [["广东",220],["四川",130],
				        ["天津",150],["西藏",58],
				        ["广西",39]
				        ],
				dataZoom : false,
				ready : function() {
					this.defaults.legend = false;
					this.defaults.title = {
				        text: '告警区域',
				        x: 246,
				        y: 140,
				        itemGap: 20,
				        textStyle : {
				            color : '#2e9bd1',
				            fontSize : 24,
				            fontWeight : 'bolder'
				        }
					}
				},
				seriesConfig : [{
					name : '区域告警',
					type : 'pie',
					radius : ['40%', '75%'],
		            center: ['50%', '60%'],
					stack : "group",
					category : "0",
					value : 1
				}]
			};
			new mcharts.Pie(configs);
			
			//告警类型分析饼图
			var configs = {
					el : "#alarmTypesChart",
					theme : "blue",
					title : "此设备告警类型分析",
					rotate : false,
					data : [["操作记录",220],["安全预警",130],
					        ["系统状态",150],["信息刺探",58],
					        ["恶意代码",39]
					        ],
					dataZoom : false,
					ready : function() {
						
					},
					seriesConfig : [{
						name : '告警类型',
						type : 'pie',
						radius : '75',
			            center: ['50%', '60%'],
						stack : "group",
						category : "0",
						value : 1
					}]
				};
			new mcharts.Pie(configs);
			
			//告警来源分析 las sni 平台 
			//安管平台,1:安全监控,2:日志审计,3:漏洞扫描,4:关联分析,5:安全人员
			var datas =[{
				"mon" : "安全监控",
				"monNum" : 120,
				"aud" : "日志审计",
				"audNum" : 90,
				"att" : "关联分析",
				"attNum" : 50,
				"ls" : "漏洞扫描",
				"lsNum" : 50,
				"renyuan":"安全人员",
				"ryNum":30
			}, {
				"mon" : "未被监控",
				"monNum" : 30,
				"aud" : "未被审计",
				"audNum" : 60,
				"att" : "未被关注",
				"attNum" : 10,
				"ls" : "未被扫描",
				"lsNum" : 150
			}];
			var labelTop = {
			    normal : {
			        label : {
			            show : true,
			            position : 'center',
			            textStyle: {
			                baseline : 'bottom'
			            }
			        },
			        labelLine : {
			            show : false
			        }
			    }
			};
			var labelBottom = {
			    normal : {
			        color: '#ccc',
			        label : {
			            show : true,
			            position : 'center',
			            formatter : function (a,b,c,d){
			            	return 100 - d + '%'
			            },
			            textStyle: {
			                baseline : 'top'
			            }
			        },
			        labelLine : {
			            show : false
			        }
			    },
			    emphasis: {
			        color: 'rgba(0,0,0,0)'
			    }
			};
			var pieConfigs = {
				el : "#alarmResourceChart",
				theme : "blue",
				data : datas,
				title : "告警来源分布图",
				ready : function() {
					$.each(this.defaults.series, function(index, item) {
						item.data[1].itemStyle=labelBottom;
						item.data[0].itemStyle=labelTop;
					});
				},
				seriesConfig : [{
					name : "las",
					type : 'pie',
					center : [120, 140],
				    radius : [40, 70],
					category : "mon",
					value : "monNum"
				},{
					name : "sni",
					type : 'pie',
					center : [290, 140],
				    radius : [40, 70],
					category : "aud",
					value : "audNum"
				},{
					name : "防火墙",
					type : 'pie',
					center : [460, 140],
				    radius : [40, 70],
					category : "ls",
					value : "lsNum"
				},{
					name : "路由器",
					type : 'pie',
					center : [630, 140],
				    radius : [40, 70],
					category : "att",
					value : "attNum"
				},{
					name : "安管平台",
					type : 'pie',
					center : [800, 140],
				    radius : [40, 70],
					category : "renyuan",
					value : "ryNum"
				}]
			};
			new mcharts.Pie(pieConfigs);
			
			//告警等级分析
			//告警时间段分析
			var configs = {
				el : "#alarmLevelChart",
				theme : "default",
				title : "告警等级漏洞图", 
				data:[
		                {value:60, name:'访问'},
		                {value:40, name:'咨询'},
		                {value:20, name:'订单'},
		                {value:80, name:'点击'},
		                {value:100, name:'展现'}
		            ],
				ready : function() {
					
				},
				calculable : true,
				seriesConfig : [{
					name:'漏斗图',
		            type:'funnel',
		            x: '20%',
		            x2: '30%',
		         //   y2: 60,
		            width: '60%',
		            min: 0,
		            max: 100,
		            minSize: '0%',
		            maxSize: '100%',
		            gap : 10,
					stack : "group",
					category : "name",
					value : "value"
				}]
			};
			new mcharts.Funnel(configs);
			
			
		});
		
		
	});
</script>

