<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style type="text/css">
	.list-group span{
		font-size:18px;
	}
</style>	
	
 	<ul class="list-group" style="margin-top:20px;width:200px;">
		<li class="list-group-item">
			<a href="<c:url value='/home/attention/my?userId=${userId}'/>">
				 设备对象 <span>${fn:length(deviceList)}</span>
			</a>
		</li>
		<li class="list-group-item">
			<a href="<c:url value='/home/attention/my?userId=${userId}'/>">
				 信息系统  <span>${fn:length(infoList)}</span>
			</a>
		</li>
		<li class="list-group-item">
			<a href="<c:url value='/home/attention/my?userId=${userId}'/>">
				 告警 <span>0</span>
			</a>
		</li>
	</ul>
			
