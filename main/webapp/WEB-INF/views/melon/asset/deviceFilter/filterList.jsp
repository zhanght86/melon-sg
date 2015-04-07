<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>

<style type="text/css">
	.temp {
		display :none;
	}

</style>
<div style=" margin-top:50px;height:300px;">
	<table class="table table-bordered">
		<thead>
			<tr>
				<th><fmt:message key="asset.secPass.assetName"/></th>
				<th><fmt:message key="asset.assetType.root"/></th>
				<th><fmt:message key="asset.deviceFilter.companyName"/></th>
				<th><fmt:message key="asset.deviceFilter.chargeName"/></th>
				<th><fmt:message key="asset.devicefilter.deadTime"/></th>
			</tr>
		</thead>
		<tbody id="showData">
			<tr id="template" class="temp">
				<td><a href=''></a></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
</div>