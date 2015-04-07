<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="secPass" role="form" cssClass="form-horizontal form-show">
	
		<div class="form-group">
			<sf:label path="assetName" cssClass="col-xs-3 control-label">
				<fmt:message key="asset.secPass.assetName" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="assetName" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="ip" cssClass="col-xs-3 control-label">
				<fmt:message key="asset.secPass.ip" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="ip" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="account" cssClass="col-xs-3 control-label">
				<fmt:message key="asset.secPass.account" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="account" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="desPassWord" cssClass="col-xs-3 control-label">
				<fmt:message key="asset.secPass.passWord" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="desPassWord" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="updateDate" cssClass="col-xs-3 control-label">
				<fmt:message key="asset.secPass.updateDate" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="updateDate" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="dueDate" cssClass="col-xs-3 control-label">
				<fmt:message key="asset.secPass.dueDate" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="dueDate" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="remark" cssClass="col-xs-3 control-label">
				<fmt:message key="asset.secPass.remark" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="remark" />
			</div>
		</div>
		
		<div class="form-group" style="border-bottom :none">
			<label class="col-xs-3 control-label" ><fmt:message key="asset.secPass.history" /></label>
		</div>
		
		<div style="max-height:250px;margin-top: 10px;overflow: auto;">
			
			<table class="table table-bordered" style="margin-bottom:0">
			    <tr>
			    	<th><fmt:message key="asset.secPass.assetName" /></th>
			    	<th><fmt:message key="asset.secPass.ip" /></th>
			    	<th><fmt:message key="asset.secPass.account" /></th>
			    	<th><fmt:message key="asset.secPass.passWord" /></th>
			    	<th><fmt:message key="asset.secPass.updateDate" /></th>
			    	<th><fmt:message key="asset.secPass.dueDate" /></th>
			    </tr>
			    <c:forEach items="${histories}" var="item">
			    <tr>
			    	<td>${item.assetName}</td>
			    	<td>${item.ip}</td>
			    	<td>${item.account}</td>
			    	<td>${item.desPassWord}</td>
			    	<td>${item.updateDate}</td>
			    	<td>${item.dueDate}</td>
			    </tr>
			    </c:forEach>
			</table>
		</div>
	
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-default" id="btnBack" href="asset/secpass/list?inline=1">
					<fmt:message key="button.back" />
				</mh:button>
			</div>
		</div>
		
	</sf:form>
</div>