<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<script src="<mh:theme code="jquery.steps.js"/>"></script>

<style type="text/css">
	.btn-block {
		display : inline-block;
		width : 199px;
	}
	.dropdown {
    	display: inline-block;
	}
	.form-horizontal .radio, .form-horizontal .checkbox, .form-horizontal .radio-inline, .form-horizontal .checkbox-inline {
		padding-top : 0px;
	}
	.input-form{
		padding-top : 6px;
		padding-bottom : 6px;
	}
	.dfShow .task-title {
		display:inline-block;
		width:160px;
		white-space: nowrap;
		word-break:break-all;
		overflow:hidden;
		text-overflow:ellipsis;
		font-size:14px;
		color:#3c3c3c;
	}
	.list-group-item{
		padding: 7px;
	}
	
	
</style>

<div class="portal-main">
	<div class="row">
		<div class="col-xs-4" style="height: 120px;">
			<div class="melon-protal-dataArea" style="height: 120px;">
				<fmt:message key="asset.deviceFilter.countDevice1"/>
				<label style="color:#45a6d6"><h4>${countDevice}</h4></label>
				<fmt:message key="asset.deviceFilter.countDevice2"/>
			</div>
			
			<button id="saveDeviceFilter" class="btn btn-primary portal-chart-a">
				<fmt:message key="asset.deviceFilter.task"/>
			</button>
			<div style=" margin-top:10px;height:300px;">
				
				<div>
					<h4><fmt:message key="asset.deviceFilter.taskList"/></h4>
				</div>
				
				<ul class="list-group" id="filterList">
					<c:forEach items="${deviceFilters}" var="deviceFilter">
						<li class="list-group-item">
							<a href="" class="dfShow" id="${deviceFilter.id}" title="${deviceFilter.orderName}">
								<span class="task-title">${deviceFilter.orderName}</span>
								<span >${deviceFilter.remindTime}</span>
							</a>
							<a id="deleteTask" class="file-remove-icon" ></a>	
						</li>
					</c:forEach>
				</ul>
				
			</div>
		</div>
		<div class="col-xs-8">
			<%-- 新建查询信息 --%>
			<jsp:include page="/WEB-INF/views/melon/asset/deviceFilter/edit.jsp"/>
			<%-- 设备筛选结果 --%>
			<jsp:include page="/WEB-INF/views/melon/asset/deviceFilter/filterList.jsp"/>
			
		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		
		//初始化页面时给列表默认值
		var id = '${deviceFilter.id}';
		var showData = $("#showData");
		 $.post('<c:url value="/asset/deviceFilter/list/"/>'+id, function(datas){
			 $("#showData").find("tr:gt(0)").remove();
			 $.each(datas, function(index, item) {
				 var tr = $("#template").clone().removeClass("temp");
				 tr.find("td:eq(0)").find(":first-child").text(item.name)
				 .attr("href",'<c:url value="/asset/device/show/"/>'+item.id);
				 tr.find("td:eq(1)").text(item.typeName);
				 tr.find("td:eq(2)").text(item.organName);
				 tr.find("td:eq(3)").text(item.chargeName);
				 tr.find("td:eq(4)").text(item.deadline);
				 tr.appendTo(showData);
			 });
		  }); 
		 
		
		
		//href='<c:url value="/asset/deviceFilter/remove/"/>${deviceFilter.id}'
		$("#filterList").on("click", "#deleteTask", function(){
			var id = $(this).prev().attr("id");
			 $.post('<c:url value="/asset/deviceFilter/remove/"/>'+id, function(datas){
				 window.location.reload();
			 }); 
		});
	});
</script>


