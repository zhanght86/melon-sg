<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>

<div class="page-query-heading">
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<mh:query>
			<jsp:attribute name="buttons">
				<mh:button href="asset/device/create" class="btn btn-default"><fmt:message key="button.create"/></mh:button>
				<mh:button href="asset/device/repeat" class="btn btn-default"><fmt:message key="button.repeat"/></mh:button>
				<mh:button href="asset/device/export" class="btn btn-default"><fmt:message key="button.export"/></mh:button>
				<mh:button href="asset/device/importList" class="btn btn-default"><fmt:message key="button.import"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<%--快速查询摆放区 --%>
				<sf:label path="organName" cssClass="col-xs-4 control-label"><fmt:message key="asset.abstract.organName"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="organName" cssClass="form-control" maxlength="50" />
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
			
				<div class="form-group">
					<div class="col-xs-5 form-field-group">
						<sf:label path="name" cssClass="col-xs-4 control-label"><fmt:message key="asset.abstract.name"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="name" cssClass="form-control" maxlength="50" />
						</div>
					</div>
					
					<div class="col-xs-5 form-field-group">
						<sf:label path="code" cssClass="col-xs-4 control-label"><fmt:message key="asset.abstract.code"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="code" cssClass="form-control" maxlength="50" />
						</div>
					</div>
				</div>
				
				<div class="form-group">	
					<div class="col-xs-5 form-field-group">
						<sf:label path="producer" cssClass="col-xs-4 control-label"><fmt:message key="asset.device.producer"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="producer" cssClass="form-control" maxlength="50" />
						</div>
					</div>
					
					<div class="col-xs-5 form-field-group">
						<sf:label path="model" cssClass="col-xs-4 control-label"><fmt:message key="asset.device.model"/></sf:label>
						<div class="col-xs-8 form-field">
							<sf:input path="model" cssClass="form-control" maxlength="50" />
						</div>
					</div>
				</div>	
				
				<div class="form-group">
					
					<div class="col-xs-5 form-field-group">
						<sf:label path="using" cssClass="col-xs-4 control-label"><fmt:message key="asset.abstract.using"/></sf:label>
						<div class="col-xs-8 form-field">
							<select id="searchForm.using" class="form-control depart-select" name="searchForm.using">
								<option value="-1"><fmt:message key="asset.selected"></fmt:message></option>
								<option value="0"><fmt:message key="asset.no"></fmt:message></option>
								<option value="1"><fmt:message key="asset.yes"></fmt:message></option>
							</select>
						</div>
					</div>
				</div>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	<mh:grid id="device" 
			 queryUrl = "asset/device/list?osFlag=${empty osFlag?-1:osFlag }"
			 showUrl = "asset/device/show"
			 deleteUrl = "asset/device/remove"
			 updateUrl = "asset/device/update"
			 hasPages= "true"
			 var="deviceConfig">
		<mh:col name="name" title="asset.abstract.name" width="20"/>
		<mh:col name="chargeName" title="asset.abstract.chargeName" width="20"/>
		<mh:col name="organName" title="asset.abstract.organName" width="20"/>
		<mh:col name="virtual" index="virtual" title="asset.device.virtual" width="20"/>
		<mh:col name="using" index="using" title="asset.abstract.using" width="20"/>
	</mh:grid>
</div>
