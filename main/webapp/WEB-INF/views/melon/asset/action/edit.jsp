<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>


<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="deviceAction" role="form" cssClass="form-horizontal" method="POST">

		<sf:hidden path="id"/>

		<sf:hidden path="actTime"/>
		
		<%--操作原因 --%>
		<div class="form-group">
			<sf:label path="type" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="asset.aciton.type" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<mh:dictionary var="type" key="usingState"/>
				<sf:select path="type" items="${type}" cssClass="form-control"/>
				<sf:errors path="type" />
			</div>
		</div>
		
		
		<%--操作说明--%>
		<div class="form-group">
			<sf:label path="remarks" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="asset.aciton.remarks" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:textarea path="remarks" cssClass="form-control" rows="5"/>
				<sf:errors path="remarks" />
			</div>
		</div>
		
		
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary small-btn" id="btnSave"><fmt:message key="button.save"/></mh:button>
			</div>
		</div>
		
		
	</sf:form>
</div>
	
<script type="text/javascript">
	$(document).ready(function() {
		var actionForm = $("#deviceAction");
		//验证表单
		actionForm.validate({
			rules : {
				/* label : {
					required : true
				} */
			}
		});
		
		 $("#btnSave").on("click",function(event){
			  if(actionForm.valid()){
				  actionForm.submit();
			  } 
			  //阻止默认的提交动作
			  event.preventDefault();
		 });
	});
</script>



