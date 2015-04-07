<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<div class="portal-main">
	<div class="page-header">
	  <p>
	  	<fmt:message key="asset.home.desc"/>
	  </p>
	</div>
	<div class="row">
		<div class="col-xs-4">
			<ul class="list-group">
				<li class="list-group-item">
					<a href="#">区域视图</a>
					<span class="badge pull-right">76</span>
				</li>
				<li class="list-group-item">
					<a href="#">安全视图</a>
					<span class="badge pull-right">12</span>
				</li>
				<li class="list-group-item">
					<a href="#">告警视图</a>
					<span class="badge pull-right">42</span>
				</li>
				<li class="list-group-item">
					<a href="#">拓扑视图</a>
					<span class="badge pull-right">42</span>
				</li>
				<li class="list-group-item">
					<a href="#">监控视图</a>
					<span class="badge pull-right">22</span>
				</li>
				<li class="list-group-item">
					<a href="#">审计视图</a>
					<span class="badge pull-right">32</span>
				</li>
			</ul>
		</div>
		<div class="col-xs-8">
			<div id="circleChart" style="height:250px;">
			</div>
		</div>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-body">
		<div id="chartMap" style="height:400px;"></div>
	</div>
</div>
<div class="row" style="margin:0px -20px 0px 0px;">
	
	<div class="col-xs-4 organ" >
		<div class="thumbnail" >
  			<div class="caption" style="background-color:#e5e5dc;">
				
				<a href="<c:url value='/asset/device/listOrgans'/>">
					<h4>
						设备对象
					</h4>
				</a>
				<p>
					资产对象总数：254个
				</p>
				<p>
					疑问对象数量：61个
				</p>
			</div>
  		</div>
	</div>
	<div class="col-xs-4 organ">
		<div class="thumbnail">
  			<div class="caption" style="background-color:#e5e5dc;">
				<a href="<c:url value='/asset/infoSystem/listOrgans'/>">
					<h4>信息系统
					</h4>
				</a>
				<p>
					资产类型总数：55 个
				</p>
				<p>
					疑问资产类型数量：2 个
				</p>
			</div>
  		</div>
	</div>
	<div class="col-xs-4 organ">
		<div class="thumbnail">
  			<div class="caption" style="background-color:#e5e5dc;">
				<a href="<c:url value='/asset/group/home'/>">
					<h4>安全域
					</h4>
				</a>
				<p>
					安全域总数：143 个
				</p>
				<p>
					疑问安全域数量：18 个
				</p>
			</div>
  		</div>
	</div>
</div>

<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	var typeView = <mh:toJson value="${typeView}"/>;
	
	require(["chart/mcharts"], function(mcharts) {
		//
		var datas =[{
			"mon" : "被监控",
			"monNum" : 120,
			"aud" : "被审计",
			"audNum" : 90,
			"att" : "被核查",
			"attNum" : 50,
			"ls" : "被扫描",
			"lsNum" : 50
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
		//var datas = [["被监控", 120, 30], ["被审计", 90, 60], ["被关注", 50, 100]];
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
			el : "#circleChart",
			theme : "blue",
			data : datas,
			title : "资产服务分布图",
			ready : function() {
				$.each(this.defaults.series, function(index, item) {
					item.data[1].itemStyle=labelBottom;
					item.data[0].itemStyle=labelTop;
				});
			},
			seriesConfig : [{
				name : "监控服务",
				type : 'pie',
				center : [80, 140],
			    radius : [40, 60],
				category : "mon",
				value : "monNum"
			},{
				name : "日志分析",
				type : 'pie',
				center : [230, 140],
			    radius : [40, 60],
				category : "aud",
				value : "audNum"
			},{
				name : "漏洞扫描",
				type : 'pie',
				center : [380, 140],
			    radius : [40, 60],
				category : "ls",
				value : "lsNum"
			},{
				name : "基线核查",
				type : 'pie',
				center : [530, 140],
			    radius : [40, 60],
				category : "att",
				value : "attNum"
			}]
		};
		var pie = new mcharts.Pie(pieConfigs);
		
			var d=$.map(typeView,function(n){
				return {
					0:n[0],
					1:n[1],
					rootId:n[2]
				};
			});
			//准备图形
			var configs = {
				el : "#chartMap",
				theme : "blue",
				title : '<fmt:message key="asset.device.listOrgan.chart"/>',
				rotate : false,
				data : d,
				dataZoom : true,
				ready : function() {
					//屏蔽Legend
					//this.defaults.xAxis[0].axisLabel.rotate = rotate;//旋转标签
					//this.defaults.dataZoom.end = end;
					//this.defaults.dataZoom.zoomLock = false;//限定只显示5个
					//this.defaults.yAxis[0].name= "<fmt:message key='device.listOrgans.chart.yAxisName'/>";
					$.extend(this.defaults.title,{	//标题样式
						x:"center",
						y:"bottom",
						textStyle:{
							color: '#333',
							fontSize:14
						}
					});
					$.extend(this.defaults.tooltip,{	//tooltip
						formatter: function (params,ticket,callback) {
				            var data=params[0],
				            	str=data[0],
				            	index=ticket.split(":")[1]
				            	unit="台";
				            if(index==0){
				            	str="信息系统数量";
				            	unit="套";
				            }else if(index==1){
				            	str="安全域数量";
				            	unit="个";
				            }
				            return "<span>"+data[1]+"</span><br/><span>"+str+" : "+data[2]+unit+"</span>";
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
							},
							interval:0
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
				}]
			};
			//生成线形趋势图
			var bar=new mcharts.Column(configs);
			var ecConfig = require('echarts/config');
			bar.chart.on(ecConfig.EVENT.CLICK, function(param){
				var url;
				switch(param.dataIndex){
				case 0:
					url="<c:url value='infoSystem/listByType?rootId="+param.data.rootId+"'/>";
					break;
				case 1:
					url="<c:url value='group/listByType?rootId="+param.data.rootId+"'/>"
					break;
				case 2:
					url="<c:url value='device/listByEnvi'/>";
					break;
				case 6:
					url="<c:url value='device/list?osFlag=1'/>";
					break;
				default:
					url="<c:url value='device/listByType?rootId="+param.data.rootId+"'/>";
					break;
				}
				location.href=url;
			});
		});
	
});	

</script>





