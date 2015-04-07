<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<div class="col-page-title">
	<c:if test="${empty dictionary.id}">
		<fmt:message key='security.resource.create' />
	</c:if>
	<c:if test="${!empty dictionary.id}">
		${dictionary.label}
	</c:if>
</div>

<sf:form modelAttribute="dictionary" role="form" cssClass="form-horizontal" style="padding-top:20px;">

	<sf:hidden path="id" />
	
	<sf:hidden path="path" />
	
	<sf:hidden path="parentId" />
	
		<div class="form-group">
			<sf:label path="value" cssClass="col-xs-3 control-label">
				<fmt:message key="base.dictionary.value" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="value" cssClass="form-control" maxlength="128" />
				<sf:errors path="value" />
				<label class="form-hint"><fmt:message key="base.dictionary.value.hint"/></label>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="label" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="base.dictionary.label" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="label" cssClass="form-control" maxlength="128" />
				<sf:errors path="label" />
				<label class="form-hint"><fmt:message key="base.dictionary.label.hint"/></label>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="order" cssClass="col-xs-3 control-label">
				<fmt:message key="base.dictionary.order" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="order" cssClass="form-control" maxlength="30" />
				<label class="form-hint"><fmt:message key="hint.order"/></label>
				<sf:errors path="order" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="addition" cssClass="col-xs-3 control-label">
				<fmt:message key="base.dictionary.addition" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="addition" cssClass="form-control" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="remarks" cssClass="col-xs-3 control-label">
				<fmt:message key="base.dictionary.remarks" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:textarea path="remarks" cssClass="form-control" />
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
		var dicForm = $("#dictionary");
		//验证表单
		dicForm.validate({
			rules : {
				label : {
					required : true
				}
			}
		});
		//注册按钮事件,保存成功则添加节点到树中
		dicForm.find("#btnSave").on("click", function(event) {
			var datas = dicForm.serialize(),
				url = dicForm.attr("action");
			//动态加载的页面必须手动验证一次
			if(dicForm.valid()) {
				$.post(url, datas, function(response) {
					var level = response.level,
						domain = response.domain,
						parentId = domain.parentId;
					//新建操作
					if(response.actionType == 0) {
						dictionaryTree.create(parentId, domain, "last", function() {
							dictionaryTree.setSelected(parentId, false);
							dictionaryTree.setSelected(domain.id, true);
						});
					} else {
						//修改操作,将数据更新到节点
						dictionaryTree.update(domain.id, domain);
					}
					//输出提示信息
					dictionaryTree.sort(domain.parentId);
					Logger.success(response.message);
				});
			}
			event.preventDefault();
		});
	});
</script>