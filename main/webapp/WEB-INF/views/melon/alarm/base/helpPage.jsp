<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<style type="text/css">
	.page-header{
		padding-bottom:0px;
		margin-top:0px;
		border-bottom:0px;
		height:40px;
	}
	.help-dec-title{
		margin-top:16px;
		margin-bottom:6px;
		color:#b2b2b2;
		text-indent: 2em;
	}
	.table{
		margin-bottom:36px;
	}
	.alarm-mark{
		height:100%;
		background-color: red;
	}
	
	.out{ 
		background-image: url('<c:url value="/statics/themes/default/melon/images/alarm-helpPage-table.png" />');  
	 	height:40px;
	 	width:200px;
	 }
	.out .event-column{
		position:relative;
			top:18px;
			left:10px;
	}
	 .out .device-column{
	 	position: relative;
	 		left:30px;
	 		top:5px;
	 }
	 .result-name{
	 	display: inline-block;
	 	height:40px;
	 	line-height:40px;
	 	font-size:16px;
	 }
	 .result {
	 	height:40px;
	 	width:100px;
	 	display: inline-block;
	 }
	 .result-text{
	 	font-size:18px;
	 	color:#3C3C3C;
	 	text-align:center;
	 	line-height:40px;
	 }
</style>

	<div class="page-header">
	  	<div class="result-name">测评结果为：</div>
	  	<a href="#level" class="result"></a>
	</div>

	<p class="help-dec-title">	告警标志与业务系统自身的重要程度与事件的威胁级别相关。依据安全专项系统的重要性，
		将系统从高到低分为四级、三级、二级、一级，对应关系为：
	</p>	
	<table class="table table-bordered" id="alarmBlock" style="width:60%;">
		<tr>
			<td class="out" style="padding:0px;">
				<span class="event-column"> 安全事件威胁</span>			
				<span class="device-column">设备重要性</span>		
			</td>
			<td style="width:16%;">一级</td>
			<td style="width:16%;">二级</td>
			<td style="width:16%;">三级</td>
			<td style="width:16%;">四级</td>
		</tr>
		<tr>
			<td>一般</td>
			<td style="background-color: rgb(0,0,255)"></td>
			<td style="background-color: rgb(0,0,255)"></td>
			<td style="background-color: rgb(255,255,0)"></td>
			<td style="background-color: rgb(255,255,0)"></td>
		</tr>
		<tr>
			<td >中等</td>
			<td style="background-color: rgb(0,0,255)"></td>
			<td style="background-color: rgb(255,255,0)"></td>
			<td style="background-color: rgb(255,255,0)"></td>
			<td style="background-color: rgb(255,192,0)"></td>
		</tr>
		<tr>
			<td>重要</td>
			<td style="background-color: rgb(255,255,0)"></td>
			<td style="background-color: rgb(255,255,0)"></td>
			<td style="background-color: rgb(255,192,0)"></td>
			<td style="background-color: rgb(255,0,0)"></td>
		</tr>
		<tr>
			<td>非常重要</td>
			<td style="background-color: rgb(255,255,0)"></td>
			<td style="background-color: rgb(255,192,0)"></td>
			<td style="background-color: rgb(255,0,0)"></td>
			<td style="background-color: rgb(255,0,0)"></td>
		</tr>
	
	</table>	
		
	<P class="help-dec-title">
		依据安全事件对安全对象造成损失的程度、威胁以及涉及信息系统和网络的重要程度等因素，参照GB/T 22240-2008、
		YD/T 1729-2008、GB/Z 20986-2007对安全系统、安全事件级别的定义，安全事件的级别分为四级，包括：
	</P>
	
	<table class="table table-bordered">
		<tr>
			<th width="20%">名称</th>
			<th width="10%">等级</th>
			<th width="10%">编码</th>
			<th width="60%">描述</th>
		</tr>
		<tr>
			<td>非常重要事件</td>
			<td id="level">四级</td>
			<td>4</td>
			<td>重要系统上的安全、维护事件，重要业务系统被中断服务，或波及整个网络范围的攻击性事件</td>
		</tr>
		<tr>
			<td>重要级别事件</td>
			<td>三级</td>
			<td>3</td>
			<td>重要系统上的安全、维护事件，重要业务系统受到影响但没有被中断，处于降级服务或备份系统服务状态；或者是普通业务服务被中断；或者局部网络区域被攻击或资源强占，失去服务能力</td>
		</tr>
		<tr>
			<td>中等级别事件</td>
			<td>二级</td>
			<td>2</td>
			<td>区域性安全、维护事件，普通业务系统受到影响但没有中断服务，处于降级服务或备份系统服务状态；或者该事件具有发展为重要级别事件的极大可能，如无法控制的局部蠕虫爆发等</td>
		</tr>
		<tr>
			<td>一般级别事件</td>
			<td>一级</td>
			<td>1</td>
			<td>日常性的安全、维护事件，业务系统服务没受影响或影响很低，系统服务还处于正常服务状态</td>
		</tr>
		<tr>
			<td>无安全事件</td>
			<td>无</td>
			<td>0</td>
			<td></td>
		</tr>
	</table>
	
	<p class="help-dec-title">安全事件告警标志根据系统重要程度和安全事件级别综合确定，告警标志分为4级，分别为：</p>
	<table class="table table-bordered">
		<tr>
			<th width="10%">告警等级</th>
			<th width="20%">告警标志</th>
			<th width="10%">编码</th>
			<th width="30%">颜色编码</th>
			<th width="30%">描述</th>
		</tr>
		<tr>
			<td>紧急</td>
			<td class="alarm-mark" style="background-color: rgb(255,0,0)"></td>
			<td>4</td>
			<td>红色告警(R:255 G:0   B:0  )</td>
			<td>红色告警，非常紧急</td>
		</tr>
		<tr>
			<td>严重</td>
			<td class="alarm-mark" style="background-color: rgb(255,192,0)"></td>
			<td>3</td>
			<td>橙色告警(R:255 G:192 B:0  )</td>
			<td>橙色告警，比较严重</td>
		</tr>
		<tr>
			<td>重要</td>
			<td class="alarm-mark" style="background-color: rgb(255,255,0)"></td>
			<td>2</td>
			<td>黄色告警(R:255 G:255 B:0  )</td>
			<td>黄色告警，比较重要</td>
		</tr>
		<tr>
			<td>一般</td>
			<td class="alarm-mark" style="background-color: rgb(0,0,255)"></td>
			<td>1</td>
			<td>蓝色告警(R:0   G:0   B:255)</td>
			<td>蓝色告警，一般告警</td>
		</tr>
		<tr>
			<td>无</td>
			<td class="alarm-mark" style="background-color: rgb(0,255,0)"></td>
			<td>0</td>
			<td>绿色正常(R:0   G:255 B:0  )</td>
			<td>绿色，正常状态</td>
		</tr>
	</table>	
	
<script type="text/javascript">
	
	$(function(){
		//事件级别（横坐标）eventLevel 
		//设备级别（纵坐标）deviceLevel
		var eventNum = parseFloat('${alarm.eventLevel}')+1,
			deviceNum = parseFloat('${alarm.deviceLevel}')+1,
			tab = $("#alarmBlock");
		tab[0].rows[eventNum].cells[deviceNum].innerHTML = "您的等级";
		//获取选中的颜色
		var chooseBg = tab[0].rows[eventNum].cells[deviceNum].style.backgroundColor;
		$(".result").css("backgroundColor", chooseBg);
		
		if(chooseBg == "rgb(255, 192, 0)"){
			$(".result").text("三级").addClass("result-text");
		}else if(chooseBg == "rgb(0, 0, 255)"){
			$(".result").text("一级").addClass("result-text");
		}else if(chooseBg == "rgb(255, 255, 0)"){
			$(".result").text("二级").addClass("result-text");
		}else if(chooseBg == "rgb(255, 0, 0)"){
			$(".result").text("四级").addClass("result-text");
		}
		
	});
	
</script>		
		
		
