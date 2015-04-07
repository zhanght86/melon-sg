<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<mh:dictionary var="boolenums" key="bool"/>

<div class="col-page-title">
	<c:if test="${empty menuResource.id}">
		<fmt:message key='security.resource.create' />
	</c:if>
	<c:if test="${!empty menuResource.id}">
		${menuResource.shortName}
	</c:if>
</div>

<sf:form modelAttribute="menuResource" role="form" cssClass="form-horizontal">

	<sf:hidden path="id" />
	
	<sf:hidden path="path" />
	
	<sf:hidden path="deep" />
	
	<sf:hidden path="parentId" />
	
		<div class="form-group">
			<sf:label path="shortName" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="security.resource.shortName" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="shortName" cssClass="form-control" maxlength="12" />
				<sf:errors path="shortName" />
				<label class="form-hint"><fmt:message key="security.resource.shortName.hint"/></label>
			</div>
		</div>

		<div class="form-group">
			<sf:label path="title" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="security.resource.title" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="title" cssClass="form-control" maxlength="50" />
				<sf:errors path="title" />
				<label class="form-hint"><fmt:message key="security.resource.title.hint"/></label>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="type" cssClass="col-xs-3 control-label">
				<fmt:message key="security.resource.type" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<div class="clearfix">
					<mh:dictionary var="menuTypeEnums" key="menuType"/>
					<sf:radiobuttons path="type" items="${menuTypeEnums}"/>
				</div>
				<label class="form-hint"><fmt:message key="security.resource.type.hint"/></label>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="url" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="security.resource.url" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="url" cssClass="form-control" maxlength="128" />
				<sf:errors path="url" />
			</div>
		</div>

		<div class="form-group">
			<sf:label path="order" cssClass="col-xs-3 control-label">
				<fmt:message key="security.resource.order" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="order" cssClass="form-control" maxlength="30" />
				<label class="form-hint"><fmt:message key="hint.order"/></label>
				<sf:errors path="order" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="enabled" cssClass="col-xs-3 control-label">
				<fmt:message key="security.resource.enabled" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:radiobuttons path="enabled" items="${boolenums}"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="openStyle" cssClass="col-xs-3 control-label">
				<fmt:message key="security.resource.openStyle" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<mh:dictionary var="openStyle" key="openStyle"/>
				<sf:select path="openStyle" cssClass="form-control">
					<sf:options items="${openStyle}" path="openStyle"/>
				</sf:select>
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="iconCss" cssClass="col-xs-3 control-label">
				<fmt:message key="security.resource.iconCss" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="iconCss" cssClass="form-control" />
			</div>
		</div>

	<div class="form-group form-button-panel">
		<div class="col-xs-9 col-xs-offset-3 form-buttons">
			<mh:button class="btn btn-primary small-btn" id="btnSave"><fmt:message key="button.save"/></mh:button>
		</div>
	</div>
	
</sf:form>
<script type="text/javascript">
	$(document).ready(function() {
		var menuForm = $("#menuResource"),
			urlGroup = $("#import-url-group");//把相关的变量都统一声明了
		//验证表单
		menuForm.validate({
			rules : {
				shortName : {
					required : true,
					maxlength:[4]
				},
				title : {
					required : true
				},
				order : {
					integer : true,
					max : 9999
				},
				url : {
					required : true
					<c:if test="${empty menuResource.id}">
					,remote : {
						url : '<c:url value="/security/menuResource/checkUnique"/>',
						type : 'POST',
						data : {
							url : function() {
								return $("#url").val();
							}
						}
					}
					</c:if>
				}
			}
		});
		//注册按钮事件,保存成功则添加节点到树中
		menuForm.find("#btnSave").on("click", function(event) {
			var datas = menuForm.serialize(),
				url = menuForm.attr("action");
			//动态加载的页面必须手动验证一次
			if(menuForm.valid()) {
				$.post(url, datas, function(response) {
					var level = response.level,
						domain = response.domain,
						parentId = domain.parentId;
					//新建操作
					if(response.actionType == 0) {
						menuTree.create(parentId, domain, "last", function() {
							menuTree.setSelected(parentId, false);
							menuTree.setSelected(domain.id, true);
						});
					} else {
						//修改操作,将数据更新到节点
						menuTree.update(domain.id, domain);
					}
					//输出提示信息
					menuTree.sort(domain.parentId);
					Logger.success(response.message);
				});
			}
			event.preventDefault();
		});
	});
</script>