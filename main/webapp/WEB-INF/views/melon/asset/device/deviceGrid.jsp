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

<style type="text/css">
	#assetFrame .gview_device_GRID,.ui-jqgrid.ui-widget.ui-widget-content.ui-corner-all,#gview_device_GRID,
	    .ui-state-default.ui-jqgrid-hdiv,.ui-jqgrid-bdiv,.ui-jqgrid-hbox,.ui-jqgrid-htable,#device_GRID {
		width:559px !important;
	}
	
</style>

<%-- <div class="page-query-heading">
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
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
</div> --%>

<div class="page-query-body" style="width:100%;">
	<mh:grid id="device" 
			 queryUrl = "asset/device/deviceGrid"
			 hasPages= "false" 
			 var="deviceConfig"
			 multiSelect="true">
		<mh:col name="name" title="asset.abstract.name" width="20"/>
		<mh:col name="code" title="asset.abstract.code" width="20"/>
		<mh:col name="chargeName" title="asset.abstract.chargeName" width="20"/>
		<mh:col name="organName" title="asset.abstract.organName" width="20"/>
		<mh:col name="using" index="using" title="asset.abstract.using" width="20"/>
	</mh:grid>
</div>
<script type="text/javascript">

$(document).ready(function() {
	$("#AD_BUTTON_QUERY").empty();
});
	function getAssets(){
		var deviceIds =[],result = [];
		deviceIds = deviceGrid.getSelected();
		for(var i = 0 ; i < deviceIds.length ; i++){
			var rowData = deviceGrid.getRowData(deviceIds[i]),
			name = rowData.name;
			var item = {
					id :  deviceIds[i],
					name : name
			};
			result.push(item);
		} 
		return result;
	}
</script>


