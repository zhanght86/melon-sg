<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<sf:form modelAttribute="device" role="form" cssClass="form-horizontal chart-form-show">
	<mh:dictionary var="usingItem" key="usingState" />

	<div class="row">
		<div class="col-xs-8" style="padding-right:30px;">
			<%--名称--%>
			<div class="form-group">
				<sf:label path="name" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.abstract.name" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:value path="name" />
				</div>
			</div>

			<%--编码--%>
			<div class="form-group">
				<sf:label path="code" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.abstract.code" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:value path="code" />
				</div>
			</div>
			
			<%--所属单位--%>
			<div class="form-group">
				<sf:label path="organName" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.abstract.organName" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<a href="<c:url value='/organ/organization/show/${device.organId }'/>">
						<mh:value path="organName" />
					</a>
				</div>
			</div>

			<%--类型--%>
			<div class="form-group">
				<sf:label path="typeName" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.assetType.root" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<a href="<c:url value='/asset/type/show/${device.typeId }'/>">
						<mh:value path="typeName" />
					</a>
				</div>
			</div>

			<%--负责人--%>
			<div class="form-group">
				<sf:label path="chargeName" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.abstract.chargeName" />
				</sf:label>
				<div class="col-xs-9 form-field">
						<a href="<c:url value='/security/account/show/${device.chargerId }'/>">
							<mh:value path="chargeName" />
						</a>
				</div>
			</div>

			<%--使用状态--%>
			<div class="form-group">
				<sf:label path="using" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.abstract.using" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:value path="using" items="${usingItem}" />
				</div>
			</div>
		</div>
	</div>
</sf:form>

<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	

});
</script>

