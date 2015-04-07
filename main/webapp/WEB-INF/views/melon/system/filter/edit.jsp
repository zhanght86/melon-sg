<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<link rel="stylesheet" href="<mh:theme code='jquery.jqGrid.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.jqGrid.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxgrid.js'/>"></script>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<script src="<mh:theme code="jquery.steps.js"/>"></script>

<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="filter" role="form" cssClass="form-horizontal" method="POST">
		<sf:hidden path="id"/>
		
		<div class="form-group">
			<sf:label path="filterName" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="era.filter.filterName" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:input path="filterName" cssClass="form-control"/>
				<sf:errors path="filterName" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="filterClass" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="era.filter.filterClass" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:input path="filterClass" cssClass="form-control" maxlength="100" />
				<sf:errors path="filterClass" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="enabled" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="era.filter.enabled" />
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<mh:dictionary var="boolType" key="bool"/>
				<sf:radiobuttons path="enabled" items="${boolType}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:hidden path="ruleId"/>
			<sf:label path="enabled" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="era.filter.rules" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:input path="ruleName" cssClass="form-control" maxlength="30" id="idRuleName" />
				<sf:errors path="ruleName" />
			</div>
			<mh:button id="assoRule"><fmt:message key="era.filter.button.addrules"/></mh:button>
		</div>
		<div id="tableShow" style="display:none">
			<div class="page-query-body"  title="选择"  width="600">
				<mh:grid id="rule"
						 queryUrl = "era/rule/selectList"
						 hasPages= "true"
						 multiSelect = "true"
						 var="ruleConfig">
					<mh:col name="desc" title="rule.desc" width="100"/>
					<mh:col name="enabled" title="rule.enabled" width="100"/>
					<mh:col name="last" title="rule.last" width="100" />
					<mh:col name="action" title="rule.action" formatter="showAction" width="190" />
				</mh:grid>
				<mh:button class="btn btn-primary" id="btnSave2"><fmt:message key="button.save"/></mh:button>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="desc" cssClass="col-xs-3 control-label">
				<fmt:message key="era.filter.desc" />
			</sf:label>
			<div class="col-xs-9 form-editor form-field">
				<sf:textarea path="desc" cssClass="form-control" rows="5"/>
			</div>
		</div>
		
	   <div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.save"/></mh:button>
				<a class="btn btn-default"   href="<c:url value="/era/filter/list"/>" ><fmt:message key="button.backList"/></a>
			</div>
		</div>
	   
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
<script type="text/javascript" src="<mh:theme code='jquery.vaildate.js'/>"></script>
<script type="text/javascript">
 $(document).ready(function() {
	$("#idRuleName").val("${desc}");
	 
 	var filter = $("#filter");
 	filter.validate({
 		rules : {
 			filterName:{
 				required:true
 			},
 			filterClass:{
 				required:true
 			},
 			ruleName:{
 				required:true
 			}
 		},
 	});
 	
 	var isShow = false;
 	
 	//关联规则
 	$("#assoRule").on("click", function(event) {
 		if (!isShow) {
 			$("#tableShow").show();
 			isShow = true;
 		} else {
 			$("#tableShow").hide();
 			isShow = false;
 		}
 	});
 	
 	$("#btnSave2").on("click", function(event) {
 		var	mruleId = ruleGrid.getFocus();
 		var url = "<c:url value='/era/rule/getmRule/'/>" + mruleId;
 		$.post(url, function(mRule){
 			var desc = mRule.desc;
 			$("#ruleName").val(desc);
 			$("#ruleId").val(mruleId);
		});
 		$("#tableShow").hide();
 	});
 	
 	$("#btnSave").on("click", function(event) {
 		if(filter.valid()) {
 			filter.submit();
		}
		event.preventDefault();
	});
 });
    
</script>