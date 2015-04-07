<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="portal-main">
	<div class="page-header">
		<table style="width:100%">
			<tr>
				<td>
					<h4><fmt:message key="report.title"/></h4>
				 	<p>
						<fmt:message key="report.description"/>
					</p>
				</td>
				<td align="right">
					<a class="btn btn-primary" href="<c:url value='/report/create' />" style="margin-bottom:9px">注册报表</a>
				</td>
			</tr>
		</table>
	</div>
</div>

<div class="row">

	<div class="col-sm-4">
		<div  class="list-group" >
			<!-- 头部 -->
			<a href="#" class="list-group-item active">
				<span colspan="2" style="color:#fff" align="center">
					<fmt:message key="report.systemReport"></fmt:message>
				</span>
				</br>
				<span colspan="2" align="center">
					<fmt:message key="report.countBytype">
						<fmt:param>${counts[0][1]}</fmt:param>	
					</fmt:message>
				</span>
			</a>
			
			<!-- 内容 -->
			<c:forEach varStatus="b" items="${sys}" var="sys_r">
				<c:if test="${! empty sys_r.name}">
					<a href="${birt}${sys_r.fileName}" target="_blank" class="list-group-item"><span class="badge">${sys_r.createDate}</span>${sys_r.name}</a>
				</c:if>
			</c:forEach>
			
			<!-- 按钮 -->
			<c:if test="${fn:length(sys)>0}">
				<a href="<c:url value="/report/list/1" />" class="list-group-item more-button"><fmt:message key="report.more" /></a>
			</c:if>
		</div>
	</div>
	
	<div class="col-sm-4">
		<div  class="list-group" >
			<!-- 头部 -->
			<a href="#" class="list-group-item active">
				<span colspan="2" style="color:#fff" align="center">
					<fmt:message key="report.logReport"></fmt:message>
				</span>
				</br>
				<span colspan="2" align="center">
					<fmt:message key="report.countBytype">
						<fmt:param>${counts[1][1]}</fmt:param>	
					</fmt:message>
				</span>
			</a>
			
			<!-- 内容 -->
			<c:forEach varStatus="c" items="${log }" var="log_r">
				<c:if test="${! empty log_r.name}">
					<a href="${birt}${log_r.fileName}" target="_blank" class="list-group-item"><span class="badge">${log_r.createDate}</span>${log_r.name}</a>
				</c:if>
			</c:forEach>
			
			<!-- 按钮 -->
			<c:if test="${fn:length(log)>0}">
				<a href="<c:url value="/report/list/2" />" class="list-group-item more-button"><fmt:message key="report.more" /></a>
			</c:if>
		</div>
	</div>
	
	<div class="col-sm-4">
		<div  class="list-group" >
			<!-- 头部 -->
			<a href="#" class="list-group-item active">
				<span colspan="2" style="color:#fff" align="center">
					<fmt:message key="report.synReport"></fmt:message>
				</span>
				</br>
				<span colspan="2" align="center">
					<fmt:message key="report.countBytype">
						<fmt:param>${counts[2][1]}</fmt:param>	
					</fmt:message>
				</span>
			</a>
			
			<!-- 内容 -->
			<c:forEach varStatus="d" items="${syn }" var="syn_r">
				<c:if test="${! empty syn_r.name}">
					<a href="${birt}${syn_r.fileName}" target="_blank" class="list-group-item"><span class="badge">${syn_r.createDate}</span>${syn_r.name}</a>
				</c:if>
			</c:forEach>
			
			<!-- 按钮 -->
			<c:if test="${fn:length(syn)>0}">
				<a href="<c:url value="/report/list/3" />" class="list-group-item more-button"><fmt:message key="report.more" /></a>
			</c:if>
		</div>
	</div>
</div>

<script type="text/javascript">
	
</script>

