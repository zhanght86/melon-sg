<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<style type="text/css">
	.melon-portal-heading{
		padding:14px 5px;
		color:#242424;
	}
	.melon-portal-body{
		background-color: #48b3e0;
		width:100%;
		height:200px;
	}
	.melon-portal-body-user{
		background-color: #76cc7c;
	}
	.melon-portal-duty{
		background-color: #ffffff;
	}
	.melon-portal-footer{
		padding:14px 0px;
		font-size:12px;
		color:#808080;
	}
	.melon-portal-col-xs-4{
		border:1px solid #dbdbdb;
		border-left:0px;
		padding:0px 20px;
	}
	.melon-portal-station-chart{
		margin-top:20px;
	}
	.melon-portal-station-list{
		margin-left:-1px;
		border-left:1px solid #dbdbdb;
		padding:0px 20px 20px 20px;
	}
	.melon-portal-duty-chart{
		height:295px;
		border:1px solid #dbdbdb;
		border-left:0px;
	}
	.melon-portal-duty-list{
		border-bottom:1px solid #dbdbdb;
		border-top:1px solid #dbdbdb;
	}
	.melon-portal-unit-counts{
		font-size:48px;
		color:#ffffff;
	}
	.melon-portal-unit-counts{
		display:inline-block;
		margin-top:50px;
		font-size:48px;
		color:#ffffff;
		text-align: center;
		width:100%;
		
	}
	.melon-portal-unit-detailCounts{
		margin:10px 0px 0px 30px;
	}
	.melon-portal-duty-list .list-group, .melon-portal-col-xs-4 .list-group {
		margin-bottom:0px;
		padding:0px 5px;
	}
	.melon-portal-duty-list .list-group .list-group-item, .melon-portal-col-xs-4 .list-group .list-group-item{
		border-left:0px;
		border-right:0px;
	}
	.melon-portal-duty-list .list-group .list-group-item a, .melon-portal-col-xs-4 .list-group .list-group-item a{
		color : #2e9bd1;
	}
	.melon-portal-duty-list .list-group .list-group-item span, .melon-portal-col-xs-4 .list-group .list-group-item span{
		float:right;
		color : #808080;
	}
	.melon-portal-duty-foot{
		color:#e0c200;
		padding-left:5px;
		padding-top:8px;
	}
	.melon-portal-station-list .station-list-levelCity{
		background-color: #83d5f2;
		height:120px;
	}
	.melon-portal-station-list .station-list-highCity{
		background-color: #308fc7;
		height:90px;
	}
	.melon-portal-station-list .station-list-noRegCity{
		background-color: #5c5c5c;
		height:50px;
	}
	.station-list-level-txt{
		color : #ffffff;
		font-size:12px;
		padding:10px;
	}
</style>
<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<div class="row" style="padding:14px 25px 40px 25px;">
	<div class="col-xs-4" style="height:300px;">
		<h4 style="margin-top: 5px;">系统管理</h4>
		<p >系统整体运转良好<br/>
			CPU利用率<strong style="color:#c72a28;font-size:16px;">${system.cpu}%</strong><br/>
			内存利用率<strong style="color:#c72a28;font-size:16px;">3800%</strong>111111
		</p>
		<a href="#" class="btn btn-default" style="margin-bottom:24px;">查看系统状态</a>
	</div>
	<div class="col-xs-8">
		<div id="chart" style="height: 300px;"></div>
	</div>
</div>
<div class="row" style="margin:0px -20px;">
	<div class="col-xs-4 melon-portal-col-xs-4">
		<div class="melon-portal-heading">单位管理</div>
		<div class="melon-portal-body ">
			<span class="melon-portal-unit-counts">
				23<span style="font-size:18px;margin-left:10px;">家</span>
			</span>
			<div class="melon-portal-unit-detailCounts">
				<span>直属单位、省级单位：11家</span><br/>
				<span>地级市单位：12家</span>
			</div>
		</div>
		<div class="melon-portal-footer">单位信息</div>
	</div> 
	<div class="col-xs-4 melon-portal-col-xs-4">
		<div class="melon-portal-heading">用户管理</div>
		<div class="melon-portal-body melon-portal-body-user">
			<span class="melon-portal-unit-counts">
				120<span style="font-size:18px;margin-left:10px;">家</span>
			</span>
		</div>
		<div class="melon-portal-footer">用户信息</div>
	</div>
	<div class="col-xs-4 melon-portal-col-xs-4" style="border-right:0px;">
		<div class="melon-portal-heading">岗位管理</div>
			<ul class="list-group">
			  <li class="list-group-item"><a href="#">安全管理员</a><span>23</span></li>
			  <li class="list-group-item"><a href="#">安全联络员</a><span>5</span></li>
			  <li class="list-group-item"><a href="#">系统管理员</a><span>10</span></li>
			  <li class="list-group-item"><a href="#">安全审计员</a><span>2</span></li>
			  <li class="list-group-item"><a href="#">安全分析员</a><span>3</span></li>
			</ul>
		<div class="melon-portal-footer melon-portal-duty-foot">安全审计员数量偏少</div>
	</div>
</div>	
<div class="row" style="margin:0px -20px;">
	<div class="col-xs-8 melon-portal-station-chart">
		<div id="chartPie" style="height: 300px;"></div>
		<div>岗位管理</div>
	</div>
	<div class="col-xs-4 melon-portal-station-list">
		<div class="melon-portal-heading">单位管理</div>
		<div class="station-list-levelCity">
			<div class="station-list-level-txt">地级市单位：22家</div>
		</div>
		<div class="station-list-highCity">
			<div class="station-list-level-txt">直属单位、省级单位：10家</div>
		</div>
		<div class="station-list-noRegCity">
			<div class="station-list-level-txt">未注册单位：8家</div>
		</div>
	</div>
</div>
<!-- <div class="row" style="margin:0px -20px;">
	<div class="col-xs-8 melon-portal-duty-chart">
		<div>图形区域</div>
		<div>岗位管理</div>
	</div>
	<div class="col-xs-4 melon-portal-duty-list">
		<div class="melon-portal-heading">岗位管理</div>
		<ul class="list-group">
		  <li class="list-group-item"><a href="#">安全管理员</a><span>15</span></li>
		  <li class="list-group-item"><a href="#">安全联络员</a><span>18</span></li>
		  <li class="list-group-item"><a href="#">系统管理员</a><span>3</span></li>
		  <li class="list-group-item"><a href="#">安全审计员</a><span>4</span></li>
		  <li class="list-group-item"><a href="#">安全分析员</a><span>1</span></li>
		</ul>
		<div class="melon-portal-footer melon-portal-duty-foot" style="color:#c72a28;">安全分析员数量偏少</div>
	</div>
</div>
<div class="row">
	<div class="col-xs-6">
		<h4>用户管理</h4>
		<p>系统目前共有单位34家，注册用户128人，平均每个单位拥有安全员3人，其中有9家单位还没有用户。</p>

		<h4>岗位管理</h4>
		<p>系统目前拥有的岗位有安全管理员，系统分析员，系统管理员，系统审计员等10个角色</p>

		<h4>单位管理</h4>
		<p>系统目前共有单位34家，注册用户128人，平均每个单位拥有安全员3人，其中有9家单位还没有用户。</p>
	</div>

	<div class="col-xs-6">
		<h4>用户管理</h4>
		<p>
			系统目前共有单位34家，注册用户128人，平均每个单位拥有安全员3人，其中有9家单位还没有用户。<a
				class="btn btn-success" href="#" role="button">添加用户</a>
		</p>

		<h4>岗位管理</h4>
		<p>系统目前拥有的岗位有安全管理员，系统分析员，系统管理员，系统审计员等10个角色</p>

		<h4>单位管理</h4>
		<p>系统目前共有单位34家，注册用户128人，平均每个单位拥有安全员3人，其中有9家单位还没有用户。</p>
	</div>
</div> -->
<script type="text/javascript">
var datas = <mh:toJson value="${onlines}"/>,
	rotate = 0,
	count = 0,
	end = 100;
	
var dat = [["山东", 15],
       	["山西", 10],
       	["新疆",8],
    	["内蒙古", 6],
    	["重庆", 5]];	
$(document).ready(function() {
	
	if(datas.length > 5) {
		rotate = 30;
		end = Math.round((5/datas.length) * 100);
	}
	//获取在线用户的人数
	$.each(datas, function(index, item) {
		count += item[1];
		
	});
	//$("#deviceSums").text(count);
	
	require(["chart/mcharts"], function(mcharts) {
		
		var configs = {
			el : "#chart",
			theme : "blue",
			title : "系统运行状态图",
			rotate : false,
			data : "<c:url value='/statics/json/datas-array.json'/>",
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
				radius : 140,
				name : '内存使用率',
				label : "0",		//标题
				calculable:true,   //是否分隔段数，true为10段
				type : 'gauge',
				category : "0",
				value : 1,
				axisLine: {
	            	// 坐标轴线
	            	show : true,
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color : [
 		                	[0.2, "#5cd193"],  //百分比   ，  颜色值
 		                	[0.8, "#4cb4e0"],
 		                	[1, "#eb4c46"],
 		                ]
	                },
	                
	            },
	            pointer: {
	                width : 10,
	                length: '80%',
	                color: '#f58a2c'
	            }, 
	            title: {	//仪表盘标题样式 
            	    show : true,
            	    offsetCenter: [0, '-67%'],
            	    textStyle: {
            	        color: '#2e9bd1',
            	        fontSize : 16
            	    }
	            }
			},{
				radius : 50,
				name : 'CPU利用率',
				label : "1",
				type : 'gauge',
				category : "0",
				value : 2,
				axisLine: {
	            	// 坐标轴线
	            	show : true,
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color : [
 		                	[0.2, "#5cd193"],
 		                	[0.4, "#4cb4e0"],
 		                	[1, "#eb4c46"],
 		                ]
	                }
	            },
				pointer: {
	                width : 10,
	                length: '60%',
	                color: 'gray'
	            },
	            title: {	//仪表盘标题样式 
            	    show : true,
            	    offsetCenter: [0, '-40%'],
            	    textStyle: {
            	        color: '#5cd193',
            	        fontSize : 16
            	    }
	            },
				detail : {
					offsetCenter: [0, 20],
					textStyle: {       
					// 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    fontSize : 24,
	                    color: '#5cd193'
	                }
				}
			}]
		};
		new mcharts.Gauge(configs);
		
		var configs = {
				el : "#chartPie",
				theme : "blue",
				title : "在线用户分布图",
				rotate : false,
				data :  dat,
				dataZoom : false, 
				ready : function() {
					
				},
				seriesConfig : [{
					name : '在线用户1',
					label : "0",
					type : 'pie',
					center : ['50%', 200],
				    radius : 100,
					stack : "group",
					category : "0",
					value : 1
				}]
			};
			new mcharts.Pie(configs);
	});
});
</script>