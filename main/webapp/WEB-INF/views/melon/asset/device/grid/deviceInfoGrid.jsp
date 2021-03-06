<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<link rel="stylesheet" href="<mh:theme code='jquery.jqGrid.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.jqGrid.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxgrid.js'/>"></script>



<div class="page-query-heading">
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query" style="margin-right:0px;">
		<mh:query>
			<jsp:attribute name="quickQuery">
				<!-- 快速查询摆放区 -->
				<sf:label path="name" cssClass="col-xs-4 control-label"><fmt:message key="asset.abstract.name"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="name" cssClass="form-control" maxlength="50" />
				</div>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div> 

<div class="page-query-body" style="width:100%;">
	<mh:grid id="device" 
			 queryUrl = "asset/device/listByDeviceInfo?enPath=${enviPath }"
			 showUrl = "asset/device/show"
			 hasPages= "true" 
			 var="deviceConfig">
		<mh:col name="name" index="A_NAME" title="asset.abstract.name" width="20"/>
		<mh:col name="code" index="A_CODE" title="asset.abstract.code" width="20"/>
		<mh:col name="typeName" index="TYPE_NAME" title="asset.assetType.root" width="20"/>
		<mh:col name="organName" index="ORGAN_NAME" title="asset.abstract.organName" width="20"/>
		<mh:col name="using" index="US_ING" title="asset.abstract.using" width="20"/>
	</mh:grid>
</div>
<script type="text/javascript">
$(document).ready(function() {
	//alert('${enviPath}');
	$("#AD_BUTTON_QUERY").empty();
	//$("#enviPath").val();
	
});
</script>


