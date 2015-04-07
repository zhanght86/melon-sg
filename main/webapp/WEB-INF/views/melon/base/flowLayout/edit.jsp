<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="flowLayout" role="form" cssClass="form-horizontal">
		
		<sf:hidden path="id"/>
		
		<div class="form-group">
			<sf:label path="name" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/>
				<fmt:message key="base.flowLayout.name"/>
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:input path="name" cssClass="form-control" maxlength="50"/>
				<sf:errors path="name"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="type" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/>
				<fmt:message key="base.flowLayout.type"/>
			</sf:label>
			<div class="col-xs-9 form-field">
				<mh:dictionary var="type" key="flowLayout.type"/>
				<sf:select path="type">
					<sf:options path="type" items="${type}" cssClass="form-control"/>
				</sf:select>
				<sf:errors path="type"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="createTime" cssClass="col-xs-3 control-label">
				<fmt:message key="base.flowLayout.createTime"/>
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:input path="createTime" cssClass="form-control"/>
				<sf:errors path="createTime"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="remarks" cssClass="col-xs-3 control-label">
				<fmt:message key="base.flowLayout.remarks"/>
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:textarea path="remarks" cssClass="form-control" rows="5"/>
				<sf:errors path="remarks"/>
			</div>
		</div>
		
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.save"/></mh:button>
				<mh:button class="btn btn-default" href="base/flowLayout/list"><fmt:message key="button.backList"/></mh:button>
			</div>
		</div>
	</sf:form>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var checkTableForm = $("#flowLayout");
		//配置验证信息
		 checkTableForm.validate({
			rules : {
				//基本信息
				name :{
					required : true
				}
			}
		  });
		   $("#btnSave").on("click",function(event){
			  if(checkTableForm.valid()){
				  checkTableForm.submit();
			  } 
			  //阻止默认的提交动作
			  event.preventDefault();
		   });
		 
		
		
		
	});
</script>
