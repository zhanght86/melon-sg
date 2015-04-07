<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--@Author 甘焕--%>
<style type="text/css">
	.nav-tabs li a{
		padding-top:0px;
		padding-bottom:5px;
	}
	.nav-tabs li a div{
		font-size:24px;
		font-weight:bold;
		text-align: center;
		height:28px;
	}
	.nav-tabs > li > a{
		color:#808080;
	}
	.nav-tabs > li.active > a, .nav-tabs > li.active > a:hover, .nav-tabs > li.active > a:focus{
		color:#2e9bd1;
	}
</style>


<div> 
	<div class="page-header" style="border-bottom:0px;margin-bottom:0px;">
	  <h3 style="display: inline-block;">我批注的对象</h3>
	</div>
	<div class="row row-tab" style="margin-left: -20px; margin-right: -20px;">
		<ul class="nav nav-tabs" role="tablist" style="padding:0px 20px;">
			<li class="active">
				<a href="#infoSystemTab" role="tab" data-toggle="tab">
					<div>${fn:length(deviceList)}</div>设备对象
				</a>
			</li>
			<li>
				<a href="#assetTab" id="assetAction" role="tab" data-toggle="tab">
					<div>${fn:length(infoList)}</div>信息系统
				</a>
			</li>
			<li>
				<a href="#alarmTab" role="tab" data-toggle="tab">
					<div>0</div>告警
				</a>
			</li>
		</ul>
		<div class="tab-content col-xs-12" style="padding:30px 20px;">
			<div class="tab-pane active" id="infoSystemTab" >
				<table class="table table-bordered">
					<tr>
						<th width="20%">设备名称</th> 
						<th width="20%">批注时间</th>
						<th width="60%">批注内容</th>
					</tr>
					<c:forEach items="${deviceList}" var="list">
						<tr>
							<td width="20%"><a href="<c:url value='/asset/device/show/'/>${list.businessId}" id="detail">${list.name}</a></td>
							<td width="20%">${list.createTime}</td>
							<td width="60%">${list.remarks}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="tab-pane" id="assetTab">
				<table class="table table-bordered">
					<tr>
						<th width="20%">系统名称</th> 
						<th width="20%">批注时间</th>
						<th width="60%">批注内容</th>
					</tr>
					<c:forEach items="${infoList}" var="list">
						<tr>
							<td width="20%"><a href="<c:url value='/asset/infoSystem/show/'/>${list.businessId}" id="detail">${list.name}</a></td>
							<td width="20%">${list.createTime}</td>
							<td width="60%">${list.remarks}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="tab-pane" id="alarmTab">
				告警关注请稍后...
			</div>
		</div>
	</div>
	
</div> 

