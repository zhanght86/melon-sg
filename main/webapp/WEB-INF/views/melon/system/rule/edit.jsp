<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="rule" role="form" cssClass="form-horizontal">
		<sf:hidden path="id"/>
		<sf:input path="type" type="hidden" id="type"/>
		<mh:dictionary var="boolType" key="bool"/>
		
		<div class="form-group">
			<sf:label path="filterEntity" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="rule.filterEntity" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:select path="filterEntity">
			 		<sf:option value=""><fmt:message key="rule.choice"/></sf:option>
			 	</sf:select>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="enabled" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="rule.enabled" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:radiobuttons path="enabled" items="${boolType}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="action" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.action" />
			</sf:label>
			<sf:input path="action" type="hidden"/>
			<div class="col-xs-9 form-field" id="idAction">
				<input type="checkbox" name="_action" value="1"/>生成告警
				<input type="checkbox" name="_action" value="10"/>生成工单
				<input type="checkbox" name="_action" value="100"/>发送短信
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="last" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.last" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:radiobuttons path="last" items="${boolType}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="order" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.order" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:input path="order" cssClass="form-control" maxlength="30" />
				<sf:errors path="order" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="desc" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="rule.desc" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:textarea path="desc" cols="20" cssClass="form-control"/>
				<sf:errors path="desc" />
			</div>
		</div>
	
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
			   <mh:button class="btn btn-primary" id="btnSave">
			      	<fmt:message key="button.save"/>
			   </mh:button>
			   <c:url value="/era/rule/list" var="listUrl"/>
			   <mh:button class="btn btn-default"  href="${listUrl}">
			      	<fmt:message key="button.back"/>
			   </mh:button>
			</div>
		</div>
	</sf:form>
</div>

<mh:dictionary  var="fileterEntity" key="rule.category"/>

<script type="text/javascript" src="<mh:theme code='jquery.vaildate.js'/>"></script>
<script type="text/javascript">
var fileterEntity = <mh:toJson value="${fileterEntity}"/>;

var form = $("#rule");
//设置Form验证
form.validate({
	rules : {
		filterEntity:{
			required:true
		},
		desc:{
			required:true
		}
	}
});

function setFilterEntityOptions(target) {
	//清空
	$("option:gt(0)",target).remove();
	
	for (var p in fileterEntity) {
		var fieldOption = $("<option></option>").val(p).text(fileterEntity[p]);
		fieldOption.appendTo(target);
		
		if('${rule.filterEntity}' == p){
			fieldOption.prop("selected", true);
		}
	}
}

function setAction(){
	var action = $("#action").val();

	if(action == 1){
		$(":checkbox[name=_action][value=1]").prop("checked",true);
	}else if(action == 10){
		$(":checkbox[name=_action][value=10]").prop("checked",true);
	}else if(action == 11){
		$(":checkbox[name=_action][value=1]").prop("checked",true);
		$(":checkbox[name=_action][value=10]").prop("checked",true);
	}else if(action == 100){
		$(":checkbox[name=_action][value=100]").prop("checked",true);
	}else if(action == 101){
		$(":checkbox[name=_action][value=1]").prop("checked",true);
		$(":checkbox[name=_action][value=100]").prop("checked",true);
	}else if(action == 110){
		$(":checkbox[name=_action][value=10]").prop("checked",true);
		$(":checkbox[name=_action][value=100]").prop("checked",true);
	}else if(action == 111){
		$(":checkbox[name=_action][value=1]").prop("checked",true);
		$(":checkbox[name=_action][value=10]").prop("checked",true);
		$(":checkbox[name=_action][value=100]").prop("checked",true);
	}
}
//
$(document).ready(function() {
	var ruleType = "${rule.type}";
	setFilterEntityOptions("#filterEntity");
	setAction();
	//保存
    $("#btnSave").on("click", function(event) {
    	var valid = form.valid();
    	
        if(valid) {
        	//处理执行动作数据
        	var actionChecked = $(":checkbox:checked"),action=0;

        	if(actionChecked.size()>0){
        		actionChecked.each(function(){
        			var val = $(this).val();
        			action += parseInt(val);
        		});
        	}
        	$("#action").val(action);
        	
        	if ("CONDITION" == ruleType) {
        		$("#type").val(0);
        	} else if ("JOIN" == ruleType) {
        		$("#type").val(1);
        	} else {
        		$("#type").val(2);
        	}

        	form.submit();
        }
		//阻止默认的提交动作
        event.preventDefault();
    });
});

</script>