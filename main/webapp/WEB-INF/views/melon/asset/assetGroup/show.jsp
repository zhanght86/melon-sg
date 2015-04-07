<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="col-xs-8  col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="assetGroup" role="form" cssClass="form-horizontal form-show">
		
		<mh:section id="basic">
			<jsp:attribute name="title">
				<fmt:message key="security.account.basic"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
			
				<div class="form-group">
					<sf:label path="name" cssClass="col-xs-3 control-label">
						<fmt:message key="asset.abstract.name"/>
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:value path="name"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="code" cssClass="col-xs-3 control-label">
						<fmt:message key="asset.abstract.code"/>
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:value path="code"/>
					</div>
				</div>
				
				
				<div class="form-group">
					<sf:label path="netType" cssClass="col-xs-3 control-label">
						<fmt:message key="asset.assetGroup.netType"/>
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:dictionary var="netType" key="netType" />
						<mh:value path="netType" items="${netType}"/>
					</div>
				</div>
				
					
				<div class="form-group">
					<sf:label path="chargerName" cssClass="col-xs-3 control-label">
						<fmt:message key="asset.abstract.chargeName"/>
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:value path="chargerName"/>
					</div>
				</div>
				
				<%-- <div class="form-group">
					<sf:label path="guardRank" cssClass="col-xs-3 control-label">
						<fmt:message key="asset.assetGroup.guardRank"/>
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:dictionary var="guardRank" key="guardRank" />
						<mh:value path="guardRank" items="${guardRank}"/>
					</div>
				</div> --%>
				
			 	<div class="form-group">
				    <sf:label path="remarks" cssClass="col-xs-3 control-label">
						<fmt:message key="asset.abstract.remarks"/>
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:value path="remarks"/>
					</div>
				</div>
				
			</jsp:attribute>
		</mh:section>	
		
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button type="link" id="btnBack"  class="btn btn-default" href="#"><fmt:message key="organ.organization.back"/></mh:button>
			</div>
		</div>
	</sf:form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("#btnBack").on("click", function(event) {
		history.back();
        event.preventDefault();
	});
});
</script>