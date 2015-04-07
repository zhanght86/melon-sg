<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<style type="text/css">
	.level>label {
		width : 50% ! important; 
	}
</style>

<div class="col-page-title">
	<c:if test="${empty condition.id}">
		<fmt:message key='las.rule.create' />
	</c:if>
	<c:if test="${!empty condition.id}">
		${condition.name}
	</c:if>
</div>

<sf:form modelAttribute="condition" role="form" cssClass="form-horizontal">

	<sf:hidden path="id" />
	<sf:hidden path="parentId" />
	<sf:hidden path="ruleType" />
	<sf:hidden path="conditionType" />
	<sf:hidden path="path" />
	<sf:hidden path="ruleId" />
	
	<div class="form-group">
		<sf:label path="name" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="las.rule.condition.name" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="name" cssClass="form-control" maxlength="50" />
			<sf:errors path="name" />
		</div>
	</div>
	
	<!-- 事件节点 -->
	<c:if test="${condition.conditionType == 0}">
	
		<div class="form-group">
			<sf:label path="content" cssClass="col-xs-3 control-label">
				<fmt:message key="las.rule.condition.content" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:value path="content"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="remark" cssClass="col-xs-3 control-label">
				<fmt:message key="las.rule.condition.remark" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<sf:textarea path="remark" rows="5" cssClass="form-control" maxlength="100"/>
			</div>
		</div>
	</c:if>
	
	<!-- 逻辑节点 -->
	<c:if test="${condition.conditionType == 1}" >
		<div class="form-group">
			<sf:label path="logicalOper" cssClass="col-xs-3 control-label">
				<fmt:message key="las.rule.condition.logicalOper" />
			</sf:label>
	
			<div class="col-xs-9 form-field" style="padding-top:7px;">
				<mh:dictionary var="logicalOpers" key="las.rule.logicalOpers"/>
	            <sf:select path="logicalOper" cssClass="form-control">
	                <sf:options  items="${logicalOpers}" />
	            </sf:select>
			</div>
		</div>
	</c:if>
	
	<!-- 具体条件 -->
	<c:if test="${condition.conditionType == 2}">
		<div class="form-group">
			<sf:label path="field" cssClass="col-xs-3 control-label">
				<fmt:message key="las.rule.condition.field" />
			</sf:label>
	
			<div class="col-xs-9 form-field" style="padding-top:7px;">
	           <select class="form-control" name="field" id="field">
	                <c:forEach items="${fields}" var="field">
	                	<option value="${field.value}">${field.text}</option>
	                </c:forEach>
	            </select>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="operator" cssClass="col-xs-3 control-label">
				<fmt:message key="las.rule.condition.oper" />
			</sf:label>
	
			<div class="col-xs-9 form-field" style="padding-top:7px;">
				<mh:dictionary var="opers" key="las.rule.opers"/>
	            <sf:select path="operator" cssClass="form-control">
	                <sf:options  items="${opers}" />
	            </sf:select>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="value" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="las.rule.condition.value" />
			</sf:label>
	
			<div class="col-xs-9 form-field" style="padding-top:7px;">
	           	<sf:input path="value" cssClass="form-control" />
	           	<sf:errors path="value" />
			</div>
		</div>
		
	</c:if>
	
	<div class="form-group form-button-panel">
		<div class="col-xs-9 col-xs-offset-3 form-buttons">
			<mh:button class="btn btn-primary" id="btnSave">
				<fmt:message key="button.save" />
			</mh:button>
		</div>
	</div>

</sf:form>

<script type="text/javascript">
	$(document).ready(function() {
		var conditionForm = $("#condition"),
			condition = <mh:toJson value="${condition}"/>;
		//添加验证规则
		conditionForm.validate({
			rules : {
				
				name : {
					required : true,
					maxlength : 50,
				},
				remark : {
					maxlength : 500
				},
				value : {
					required : true
				}
				
			}
			
		});
		
		//条件节点
		if(condition.conditionType == 2) {
			var value = condition.field;
			$("#field").find("option[value="+value+"]").prop("selected",true);
		}
		
		//注册保存按钮
		conditionForm.find("#btnSave").on("click", function(event) {
			var datas = conditionForm.serialize(),
				url = conditionForm.attr("action");
			if(conditionForm.valid()) {
				$.post(url, datas, function(response) {
					var level = response.level,
						result = response.domain;
						//parentId = domain.parentId;
					//注册按钮事件,保存成功则添加节点到树中
					//新建操作
					if(response.actionType == 0) {
						
						//返回一个节点
						if(result.length == 1) {
							var domain = result[0];
							conditionTree.create(domain.parentId, domain, "last", function() {
								conditionTree.setSelected(domain.parentId, false);
								conditionTree.setSelected(domain.id, true);
							});
						} else if(result.length == 3) {
							//删除原来的条件节点
							conditionTree.remove(result[1].id);
							$.each(result, function(index, item) {
								conditionTree.create(item.parentId, item, "last");
							});
							conditionTree.setSelected(result[0].parentId, false);
							conditionTree.setSelected(result[2].id, true);
						}
						conditionTree.sort(result[0].parentId);
						
					} else {
						//修改操作,将数据更新到节点
						conditionTree.update(result.id, result);
						conditionTree.sort(result.parentId);
					}
					//输出提示信息
					Logger.success(response.message);
				});
			}
			event.preventDefault();
		});
	});
</script>

