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
	<c:if test="${empty type.id}">
		<fmt:message key='melon.regulation.create' />
	</c:if>
	<c:if test="${!empty type.id}">
		${type.name}
	</c:if>
</div>

<sf:form modelAttribute="type" role="form" cssClass="form-horizontal">

	<sf:hidden path="id" />
	<sf:hidden path="parentId" />
	<sf:hidden path="path" />
	
	<div class="form-group">
		<sf:label path="name" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="melon.regulation.type.name" />
		</sf:label>

		<div class="col-xs-9 form-field" style="padding-top:7px;">
			<sf:input path="name" cssClass="form-control" maxlength="20" />
			<sf:errors path="name" />
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="code" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="melon.regulation.type.code" />
		</sf:label>

		<div class="col-xs-9 form-field level" >
			<sf:input path="code" cssClass="form-control" maxlength="20" />
			<sf:errors path="code" />
		</div>
	</div>
  
	<div class="form-group">
		<sf:label path="level" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="melon.regulation.type.level" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="level" cssClass="form-control" maxlength="20" />
			<sf:errors path="level" />
		</div>
	</div>

	<div class="form-group">
		<sf:label path="order" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="melon.regulation.type.order" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="order" cssClass="form-control" maxlength="4" />
			<sf:errors path="order" />
			<label class="form-hint"><fmt:message key="hint.order" /></label>
		</div>
	</div>

	<div class="form-group">
		<sf:label path="remark" cssClass="col-xs-3 control-label">
			<fmt:message key="melon.regulation.type.remark" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:textarea path="remark" cssClass="form-control" rows="5" maxlength="256" />
			<sf:errors path="remark" />
		</div>
	</div>

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
		var typeForm = $("#type");
		//定位第一个元素
		typeForm.find("#name").focus();
		//添加验证规则
		typeForm.validate({
			rules : {
				code : {
					required : true,
					stringChar : true,
					maxlength : 20,
				},
				
				name : {
					required : true,
					maxlength : 20
				},
				
				order : {
					required : true,
					integer : true
				},
				
				level : {
					required : true,
				},
				
				remark : {
					maxlength : 256
				}
			},
			
		});
		//注册保存按钮
		//注册按钮事件,保存成功则添加节点到树中
		typeForm.find("#btnSave").on("click", function(event) {
			var datas = typeForm.serialize(),
				url = typeForm.attr("action");
			if(typeForm.valid()) {
				$.post(url, datas, function(response) {
					var domain = response.domain,
						parentId = domain.parentId;
					//新建操作
					if(response.actionType == 0) {
						typeTree.create(parentId, domain, "last", function() {
							typeTree.setSelected(parentId, false);
							typeTree.setSelected(domain.id, true);
						});
					} else {
						//修改操作,将数据更新到节点
						typeTree.update(domain.id, domain);
					}
					//输出提示信息
					typeTree.sort(domain.parentId);
					Logger.success(response.message);
				});
			}
			event.preventDefault();
		});
	});
</script>

