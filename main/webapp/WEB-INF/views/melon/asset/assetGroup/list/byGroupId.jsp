<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<mh:section id="basic">

	<jsp:attribute name="title">
		<div class="titles" style="height: 20px;">
			<fmt:message key="asset.assetGroup.list.device">
			    <fmt:param><span>${groupName.name}</span></fmt:param>
			    <fmt:param><label style="color:#45a6d6">${fn:length(assetTable)}</label></fmt:param>
			</fmt:message>
		</div>
		
	</jsp:attribute>
	
	<jsp:attribute name="body">
		<div class="row" style="margin-left: 0px; margin-right: 0px;">
			
			<ul class="nav nav-tabs">
                <li class="active"><a href="#basics" id="deviceObj" data-toggle="tab"><fmt:message key="asset.device.name"/></a></li>
                <li><a href="#profile" id="infoSys" data-toggle="tab"><fmt:message key="asset.info.name"/></a></li>
            </ul>
		</div>		
		
		<div class="tab-content">	
		<div class="tab-pane active" id="basics" role="form" cssClass="form-horizontal form-show">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th><fmt:message key="security.account.num" /></th>
						<th><fmt:message key="asset.abstract.name" /></th>
						<th><fmt:message key="asset.abstract.code" /></th>
						<th><fmt:message key="asset.abstract.chargeName" /></th>
						<th><fmt:message key="asset.abstract.organName" /></th>
						<th><fmt:message key="asset.abstract.using" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${assetTable}" var="device" varStatus="num">
						<tr>
							<td width="5%">${num.count}</td>
							<td width="20%">
								<a class="jqrow-action jqrow-action-show" href="<c:url value='/asset/device/show/${device.id}'/>">
									${device.name}
								</a>
							</td>
							<td width="20%">${device.code}</td>
							<td width="20%">${device.chargeName}</td>
							<td width="20%">${device.organName}</td>
							<td width="20%">
								<mh:dictionary key="usingState" value="${device.using}"/>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div class="tab-pane" id="profile">
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th><fmt:message key="security.account.num" /></th>
						<th><fmt:message key="asset.abstract.name" /></th>
						<th><fmt:message key="asset.abstract.code" /></th>
						<th><fmt:message key="asset.abstract.chargeName" /></th>
						<th><fmt:message key="asset.abstract.organName" /></th>
						<th><fmt:message key="asset.abstract.using" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${sysTable}" var="info" varStatus="num">
						<tr>
							<td width="5%">${num.count}</td>
							<td width="20%">
								<a class="jqrow-action jqrow-action-show" href="<c:url value='/asset/infoSystem/show/${info.id}'/>">
									${info.name}
								</a>
							</td>
							<td width="20%">${info.code}</td>
							<td width="20%">${info.chargeName}</td>
							<td width="20%">${info.organName}</td>
							<td width="20%">
								<mh:dictionary key="usingState" value="${info.using}"/>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</div>
	</jsp:attribute>
</mh:section>
<script type="text/javascript">
	$(function(){
		$("#deviceObj").on("click",function(){
			$(".titles").html("<fmt:message key='asset.assetGroup.list.device'>"+
								    "<fmt:param><span>${groupName.name}</span></fmt:param>"+
								    "<fmt:param><label style='color:#45a6d6'>${fn:length(assetTable)}</label></fmt:param>"+
							  "</fmt:message>"); 
		});
		$("#infoSys").on("click",function(){
			$(".titles").html("<fmt:message key='asset.assetGroup.list.info'>"+
								    "<fmt:param><span>${groupName.name}</span></fmt:param>"+
								    "<fmt:param><label style='color:#45a6d6'>${fn:length(sysTable)}</label></fmt:param>"+
							  "</fmt:message>");
		});
	});
</script>
