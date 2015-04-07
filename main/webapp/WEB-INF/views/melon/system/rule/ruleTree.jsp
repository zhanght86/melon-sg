<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>

<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="rule" role="form" cssClass="form-horizontal">
		<sf:hidden path="id"/>
		<sf:hidden path="expression" id="expression"/>
		<sf:hidden path="type"/>
		<sf:hidden path="filterEntity"/>
		<sf:hidden path="enabled"/>
		<sf:hidden path="action"/>
		<sf:hidden path="last"/>
		<sf:hidden path="order"/>
		<sf:hidden path="desc"/>

		<div class="form-group">
			<sf:label path="expression" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.expression" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<mh:value path="expression"/>
			</div>
		</div>

		<div class="form-group">
			<sf:label path="filterEntity" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.filterEntity" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<mh:value path="filterEntity"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="field" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.field" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<select id="idFieldOne">
				  <option value ="level">告警级别</option>
				  <option value ="deviceIp">告警IP</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="operator" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.operator" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<select id="idOperatorOne">
				  <option value ="<">小于</option>
				  <option value ="==">等于</option>
				  <option value =">">大于</option>
				  <option value ="!=">不等于</option>
				  <option value ="<=">小于等于</option>
				  <option value =">=">大于等于</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="value" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="rule.value" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<input cssClass="form-control" maxlength="30" id="valueOne"/>
				<sf:errors path="value" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="joinType" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.joinType" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<select id="idJoinType">
				  <option value ="&&">与</option>
				  <option value ="||">或</option>
				  <option value ="!">非</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="field" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.field" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<select id="idFieldTwo">
					<option value ="deviceIp">告警IP</option>
					<option value ="level">告警级别</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="operator" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.operator" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<select id="idOperatorTwo">
				  <option value ="<">小于</option>
				  <option value ="==">等于</option>
				  <option value =">">大于</option>
				  <option value ="!=">不等于</option>
				  <option value ="<=">小于等于</option>
				  <option value =">=">大于等于</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="value" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="rule.value" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<input cssClass="form-control" maxlength="30" id="valueTwo"/>
				<sf:errors path="value" />
			</div>
		</div>
		
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
			   <mh:button class="btn btn-primary" id="btnSave">
			      	<fmt:message key="button.save"/>
			   </mh:button>
			   <c:url value="era/rule/list" var="listUrl"/>
			   <mh:button class="btn btn-default"  href="${listUrl}">
			      	<fmt:message key="button.back"/>
			   </mh:button>
			</div>
		</div>
	</sf:form>
</div>

<script type="text/javascript" src="<mh:theme code='jquery.vaildate.js'/>"></script>

<script type="text/javascript">
$(document).ready(function() {
	var form = $("#rule");
	
	$("#btnSave").on("click", function(event) {
    	var exp = "";
    	
    	exp = "((" + $("#idFieldOne").val() + " " + $("#idOperatorOne").val() + " "
    			+ $("#valueOne").val() + ") " + $("#idJoinType").val() + " (" +
    			$("#idFieldTwo").val() + " " + $("#idOperatorTwo").val() + " \""
    			+ $("#valueTwo").val() + "\"))";
    	
    	
    	$("#expression").val(exp);
		
        form.submit();
		//阻止默认的提交动作
        event.preventDefault();
    });
});
</script>