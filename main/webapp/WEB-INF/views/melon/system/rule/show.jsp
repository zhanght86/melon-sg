<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>

<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="mRule" role="form" cssClass="form-horizontal form-show">
		
		<sf:hidden path="id"/>
		
		<div class="form-group">
			<sf:label path="filterEntity" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.filterEntity" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<mh:value path="filterEntity" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="enabled" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.enabled" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<mh:value path="enabled" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="action" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.action" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<mh:value path="action" id="idAction"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="last" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.last" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<mh:value path="last" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="order" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.order" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<mh:value path="order" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="desc" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.desc" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<mh:value path="desc" />
			</div>
		</div>
		
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<c:url value="era/rule/list" var="listUrl"/>
				<mh:button class="btn btn-primary"  href="${listUrl}">
					<fmt:message key="button.back" />
				</mh:button>
			</div>
		</div>
	</sf:form>
</div>

<script type="text/javascript">
$(document).ready(function() {
	function showAction(action) {
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
	
	$("#idAction").text(showAction("${mRule.action}"));
});
</script>