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
	<c:if test="${empty organ.id}">
		<fmt:message key='organ.organization.create' />
	</c:if>
	<c:if test="${!empty organ.id}">
		${organ.name}
	</c:if>
</div>

<sf:form modelAttribute="organ" role="form" cssClass="form-horizontal">

	<sf:hidden path="id" />
	<sf:hidden path="parentId" />
	<sf:hidden path="type" />
	<sf:hidden path="path" />
	
	<div class="form-group">
		<sf:label path="type" cssClass="col-xs-3 control-label">
			<fmt:message key="organ.organization.type" />
		</sf:label>

		<div class="col-xs-9 form-field" style="padding-top:7px;">
			<mh:dictionary var="typeEnum" key="organType" />
			<mh:value path="type" items="${typeEnum}" />
			<sf:errors path="type" />
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="level" cssClass="col-xs-3 control-label">
			<fmt:message key="organ.organization.level" />
		</sf:label>

		<div class="col-xs-9 form-field level" >
			<mh:dictionary var="levelEnum" key="organLevel" />
			<sf:radiobuttons path="level" items="${levelEnum}"/>
			<sf:errors path="level" />
		</div>
	</div>
  
	<div class="form-group">
		<sf:label path="name" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="organ.organization.name" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="name" cssClass="form-control" maxlength="50" />
			<sf:errors path="name" />
		</div>
	</div>

	<div class="form-group">
		<sf:label path="code" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="organ.organization.code" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="code" cssClass="form-control" maxlength="50" />
			<sf:errors path="code" />
		</div>
	</div>

	<div class="form-group">
		<sf:label path="order" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="organ.organization.order" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="order" cssClass="form-control" maxlength="4" />
			<sf:errors path="order" />
			<label class="form-hint"><fmt:message key="hint.order" /></label>
		</div>
	</div>

	<div class="form-group">
		<sf:label path="principal" cssClass="col-xs-3 control-label">
			<fmt:message key="organ.organization.principal" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="principal" cssClass="form-control" maxlength="50" />
			<sf:errors path="principal" />
		</div>
	</div>

	<div class="form-group">
		<sf:label path="prinTel" cssClass="col-xs-3 control-label">
			<fmt:message key="organ.organization.prinTel" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="prinTel" cssClass="form-control" maxlength="50" />
			<sf:errors path="prinTel" />
		</div>
	</div>

	<div class="form-group">
		<sf:label path="contacter" cssClass="col-xs-3 control-label">
			<fmt:message key="organ.organization.contacter" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="contacter" cssClass="form-control" maxlength="50" />
			<sf:errors path="contacter" />
		</div>
	</div>

	<div class="form-group">
		<sf:label path="conTel" cssClass="col-xs-3 control-label">
			<fmt:message key="organ.organization.conTel" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="conTel" cssClass="form-control" maxlength="50" />
			<sf:errors path="conTel" />
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="address" cssClass="col-xs-3 control-label">
			<fmt:message key="organ.organization.address" />
		</sf:label>
	
		<div class="col-xs-9 form-field">
			<sf:textarea path="address" rows="3" cssClass="form-control" maxlength="100" />
			<sf:errors path="address" />
		</div>
	</div>

	<div class="form-group">
		<sf:label path="remark" cssClass="col-xs-3 control-label">
			<fmt:message key="organ.organization.remark" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:textarea path="remark" cssClass="form-control" rows="5" maxlength="500" />
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
		var organForm = $("#organ");
		//定位第一个元素
		organForm.find("#name").focus();
		//添加验证规则
		organForm.validate({
			rules : {
				code : {
					required : true,
					stringChar : true,
					remote : {
						url : '<c:url value="/organ/organization/checkByCode"/>',
						type : 'POST',
						data : {
							code : function() {
								return  $.trim($("#code").val());
							},
							id : function() {
								return  $("#id").val();
							}
						}
					}
				},
				
				name : {
					required : true,
					maxlength : 50,
					remote : {
						url : '<c:url value="/organ/organization/checkByName"/>',
						type : 'POST',
						data : {
							name : function() {
								return  $.trim($("#name").val());
							},
							id : function() {
								return  $("#id").val();
							},
							type : function() {
								return  $("#type").val();
							}
						}
					}
				},
				
				order : {
					required : true,
					integer : true
				},
				
				//多行文本框必须加入长度验证
				address : {
					maxlength : 100
				},
				
				remark : {
					maxlength : 500
				}
			},
			
		});
		//注册保存按钮
		//注册按钮事件,保存成功则添加节点到树中
		organForm.find("#btnSave").on("click", function(event) {
			var datas = organForm.serialize(),
				url = organForm.attr("action");
			if(organForm.valid()) {
				$.post(url, datas, function(response) {
					var level = response.level,
						domain = response.domain,
						parentId = domain.parentId;
					//新建操作
					if(response.actionType == 0) {
						organTree.create(parentId, domain, "last", function() {
							organTree.setSelected(parentId, false);
							organTree.setSelected(domain.id, true);
						});
					} else {
						//修改操作,将数据更新到节点
						organTree.update(domain.id, domain);
					}
					//输出提示信息
					organTree.sort(domain.parentId);
					Logger.success(response.message);
				});
			}
			event.preventDefault();
		});
	});
</script>

