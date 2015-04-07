<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style type="text/css">
	.page-list-body .table > tbody > tr > th{
		text-align : center;
	}
	.page-list-body .table > tbody > tr .td-space{
		padding : 0px;
	}
	.page-list-body .table > tbody > tr .td-space .table{
		margin-bottom: 0px;
		border-width : 0px;
	}
	
</style>
<div class="page-header">
	<h3>
		<fmt:message key="device.repeat.title">
			<fmt:param>${fn:length(repeat)}</fmt:param>
		</fmt:message>
	</h3>
</div>
<table class="table table-bordered">
	<tr>
		<th width="5%"></th>
		<th width="25%"><fmt:message key="asset.abstract.name"/></th>
		<th width="22%"><fmt:message key="asset.abstract.organName"/></th>
		<th width="20%"><fmt:message key="asset.abstract.chargeName"/></th>
		<th width="10%"><fmt:message key="asset.device.virtual"/></th>
		<th width="10%"><fmt:message key="asset.abstract.using"/></th>
		<th width="10%">操作</th>
	</tr>
	<c:forEach items="${repeat }" var="repeats" varStatus="index">
		<tr>
			<td colspan="7" class="td-space">
				<table class="table table-bordered" >
						<tr>
							<td colspan="6" style="border-top : 0px; border-left : 0px; border-right : 0px;">
								<fmt:message key="device.repeat.rowTitle"/>${repeats[0].name }
							</td>
							<td>
								<a href="<c:url value="startContrast/${repeats[0].id},${repeats[1].id}"/>">对比</a>
							</td>
						</tr>
						
						<c:forEach items="${repeats }" var="repe" varStatus="num">
							<tr>
								<td style="width : 5%;border-left: 0px;border-bottom: 0px; width:">${num.count}</td>
								<td style="width : 25%;border-bottom: 0px;">
									<a class="jqrow-action jqrow-action-show" href="<c:url value='/asset/device/show/${repe.id}'/>">
										${repe.name}
									</a>
								</td>
								<td style="width : 22%;border-bottom: 0px;">${repe.organName}</td>
								<td style="width : 20%;border-bottom: 0px;">${repe.chargeName}</td>
								<td style="width : 10%;border-bottom: 0px;">
									<mh:dictionary key="bool" value="${repe.virtual}"/>
								</td>
								<td style="width : 10%;border-bottom: 0px;">
									<mh:dictionary key="usingState" value="${repe.using}"/>
								</td>
								<td style="width : 8%;border-right: 0px; border-bottom : 0px;">
									<a class="jqrow-action jqrow-action-edit" href="<c:url value="update/${repe.id}" />"></a><a class="jqrow-action jqrow-action-delete" href="<c:url value="remove/${repe.id}" />"></a>
								</td>
							</tr>
						</c:forEach>				
				</table>
			</td>
		</tr>	
	</c:forEach>
</table>