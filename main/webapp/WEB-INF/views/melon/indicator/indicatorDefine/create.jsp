<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<div class="col-page-title">
	<c:if test="${empty indicatorDefine.id}">
		<fmt:message key='indicator.defined.create' />
	</c:if>
	<c:if test="${!empty indicatorDefine.id}">
	  	${indicatorDefine.name}
	</c:if>
</div>

<sf:form modelAttribute="indicatorDefine" role="form" cssClass="form-horizontal" style="padding-top:20px;">

	<sf:hidden path="id" />
	
	<sf:hidden path="path" />
	
	<sf:hidden path="parentId" />
	
	<section>
		<div class="form-group">
			<sf:label path="name" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="indicator.defined.name" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="name" cssClass="form-control" maxlength="50" />
				<sf:errors path="name" />
			</div>
		</div>
	</section>
	
	<section>
		<div class="form-group">
			<sf:label path="code" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="indicator.defined.code" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="code" cssClass="form-control" maxlength="50" readonly="true"/>
				<sf:errors path="code" />
			</div>
		</div>
	</section>
	
	<section>
		<div class="form-group">
			<sf:label path="order" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="indicator.defined.order" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<mh:dictionary var="iorder" key="indiorder"/>
				<sf:radiobuttons path="order" items="${iorder}"/>
				<sf:errors path="order" />
			</div>
		</div>
	</section>
	
	
	<section>
		<div class="form-group">
			<sf:label path="type" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="indicator.defined.type" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<mh:dictionary var="itype" key="indiType"/>
				<sf:select path="type" cssClass="form-control" items="${itype}"/>
				<sf:errors path="type" />
			</div>
		</div>
	</section>
	
	<section>
		<div class="form-group">
			<sf:label path="enabled" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="indicator.defined.enabled" />
			</sf:label>

			<div class="col-xs-9 form-field">
			    <mh:dictionary var="state" key="bool"/>
				<sf:radiobuttons path="enabled" items="${state}" />
				<sf:errors path="enabled" />
			</div>
		</div>
	</section>
	<section>
		<div class="form-group">
			<sf:label path="remarks" cssClass="col-xs-3 control-label">
				<fmt:message key="indicator.defined.remarks" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:textarea path="remarks" cssClass="form-control" maxlength="500"/>
			</div>
		</div>
	</section>


	<div class="form-group form-button-panel">
		<div class="col-xs-9 col-xs-offset-3 form-buttons">
			<mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.save"/></mh:button>
		</div>
	</div>
	
</sf:form>
<script type="text/javascript">
$(document).ready(function() {
	var indicatorDefineForm = $("#indicatorDefine");
	//验证表单
	indicatorDefineForm.validate({
		rules : {
			name : {
				required : true,
				maxlength:[50]
			},
			/* code : {
				required : true,
				maxlength:[50]
			}, */
			remarks: {
				maxlength:[500]
			},
			order :{
				digits:true
			}
		}
	});
	//注册按钮事件,保存成功则添加节点到树中
	indicatorDefineForm.find("#btnSave").on("click", function(event) {
		var datas = indicatorDefineForm.serialize(),
			url = indicatorDefineForm.attr("action");
		//动态加载的页面必须手动验证一次
		if(indicatorDefineForm.valid()) {
			$.post(url, datas, function(response) {
				var domain = response.domain,
					parentId = domain.parentId;
				//新建操作
				if(response.actionType == 0) {
					indicatorDefineTree.create(parentId, domain, "last", function() {
						indicatorDefineTree.setSelected(parentId, false);
						indicatorDefineTree.setSelected(domain.id, true);
					});
				} else {
					//修改操作,将数据更新到节点
					indicatorDefineTree.update(domain.id, domain);
				}
				//输出提示信息
				indicatorDefineTree.sort(domain.parentId);
				Logger.success(response.message);
			});
		}
		event.preventDefault();
	});
});
</script>