<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>

<div class="page-query-heading">
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<mh:query>
			<jsp:attribute name="buttons">
				<mh:button href="era/rule/create" class="btn btn-primary"><fmt:message key="button.create"/></mh:button>
				<mh:button class="btn btn-default" id="setRule"><fmt:message key="button.set"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
			  	<%--快速查询摆放区 --%>
				<sf:label path="desc" cssClass="col-xs-4 control-label"><fmt:message key="rule.desc"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="desc" cssClass="form-control"/>
				</div>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<script type="text/javascript">
function showAction(action, options, rowObject) {
	var result = "无";
	if(action == 1){
		result = "生成告警";
	}else if(action == 10){
		result = "生成工单";
	}else if(action == 11){
		result = "生成工单,生成告警";
	}else if(action == 100){
		result = "发送短信";
	}else if(action == 101){
		result = "生成告警,发送短信";
	}else if(action == 110){
		result = "生成工单,发送短信";
	}else if(action == 111){
		result = "生成工单,生成告警,发送短信";
	}
	return result;
}
</script>

<div class="page-query-body">
	<mh:grid id="rule"
			 queryUrl = "era/rule/list"
			 showUrl = "era/rule/show"
			 updateUrl="era/rule/update"
			 deleteUrl="era/rule/remove"
			 hasPages= "true"
			 multiSelect = "true"
			 var="ruleConfig">
		<mh:col name="desc" title="rule.desc" width="30"/>
		<mh:col name="enabled" title="rule.enabled" width="25"/>
		<mh:col name="last" title="rule.last" width="20" />
		<mh:col name="action" title="rule.action" formatter="showAction" width="25" />
	</mh:grid>
</div>

<script type="text/javascript">
$(document).ready(function() {
   	//配置规则
   	$("#setRule").on("click", function() {
   		var selRows = checkSelected();
   		
   		if(selRows || $.isNumeric(selRows)) {
			window.location = "<c:url value='/era/rule/tree/'/>" + selRows;
		}
   	});
    
    function checkSelected() {
		var selRows = ruleGrid.getSelected();

		if(selRows.length < 1) {
			Dialog.warn('<fmt:message key="hint.selNoRow"/>');
		} else if(selRows.length > 1) {
			Dialog.warn('<fmt:message key="hint.selManyRow"/>');
		} else {
			return selRows;
		}
		return false;
	}
});
</script>

