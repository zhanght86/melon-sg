<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<div class="col-page-title">
	<c:if test="${empty knowledgeType.id}">
		<fmt:message key='knowledge.type.create' />
	</c:if>
	<c:if test="${!empty knowledgeType.id}">
	  	${knowledgeType.title}
	</c:if>
</div>

<sf:form modelAttribute="knowledgeType" role="form" cssClass="form-horizontal" style="padding-top:20px;">

	<sf:hidden path="id" />
	
	<sf:hidden path="path" />
	
	<sf:hidden path="parentId" />
	
	<div class="form-group">
		<sf:label path="title" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="knowledge.type.title" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="title" cssClass="form-control" maxlength="50" />
			<sf:errors path="title" />
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="code" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="knowledge.type.code" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="code" cssClass="form-control" maxlength="50" readonly="true"/>
			<sf:errors path="code" />
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="order" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="knowledge.type.order" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="order" cssClass="form-control" maxlength="50" />
			<sf:errors path="order" />
		</div>
	</div>

	<div class="form-group">
		<sf:label path="remarks" cssClass="col-xs-3 control-label">
			<fmt:message key="knowledge.type.remarks" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:textarea path="remarks" cssClass="form-control" rows="5" maxlength="500"/>
		</div>
	</div>


	<div class="form-group form-button-panel">
		<div class="col-xs-9 col-xs-offset-3 form-buttons">
			<mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.save"/></mh:button>
		</div>
	</div>
	
</sf:form>
<script type="text/javascript">
$(document).ready(function() {
	var knowledgeTypeForm = $("#knowledgeType");
	//验证表单
	knowledgeTypeForm.validate({
		rules : {
			title : {
				required : true,
				maxlength:[50]
			},
			remarks: {
				maxlength:[500]
			},
			order :{
				digits:true
			}
		}
	});
	//注册按钮事件,保存成功则添加节点到树中
	knowledgeTypeForm.find("#btnSave").on("click", function(event) {
		var datas = knowledgeTypeForm.serialize(),
			url = knowledgeTypeForm.attr("action");
		//动态加载的页面必须手动验证一次
		if(knowledgeTypeForm.valid()) {
			$.post(url, datas, function(response) {
				var domain = response.domain,
					parentId = domain.parentId;
				//新建操作
				if(response.actionType == 0) {
					knowledgeTypeTree.create(parentId, domain, "last", function() {
						knowledgeTypeTree.setSelected(parentId, false);
						knowledgeTypeTree.setSelected(domain.id, true);
					});
				} else {
					//修改操作,将数据更新到节点
					knowledgeTypeTree.update(domain.id, domain);
				}
				//输出提示信息
				knowledgeTypeTree.sort(domain.parentId);
				Logger.success(response.message);
			});
		}
		event.preventDefault();
	});
});
</script>