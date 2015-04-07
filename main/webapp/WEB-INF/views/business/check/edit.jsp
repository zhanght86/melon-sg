<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<script src="<mh:theme code="jquery.steps.js"/>"></script>
<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="checkTable" role="form" cssClass="form-horizontal">
		
		<sf:hidden path="id"/>
		<sf:hidden path="businessId"/>
		
		<div class="form-group">
			<sf:label path="title" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/>
				<fmt:message key="checkTable.title"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<sf:input path="title" cssClass="form-control" maxlength="50"/>
				<sf:errors path="title"/>
			</div>
		</div>

		<div class="form-group">
			<sf:label path="issueOrgan" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/>
				<fmt:message key="checkTable.issueOrgan"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<sf:input path="issueOrgan" cssClass="form-control" maxlength="50"/>
				<sf:errors path="issueOrgan"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="baseUrl" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/>
				<fmt:message key="checkTable.baseUrl"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<sf:input path="baseUrl" cssClass="form-control" maxlength="50"/>
				<sf:errors path="baseUrl"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="issueTime" cssClass="col-xs-3 control-label">
				<fmt:message key="checkTable.issueTime" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:input id="issueTime" path="issueTime" cssClass="form-control" maxlength="50"  />
				<hh:date id="issueTime" maxDate="${maxDate}"/>
				<sf:errors path="issueTime" />
			</div>
		</div>
		<!-- 附件 -->
		<div class="form-group">
			<sf:label path="remarks" cssClass="col-xs-3 control-label">
				<fmt:message key="checkTable.remarks"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<sf:textarea path="remarks" cssClass="form-control" maxlength="500" rows="5"/>
				<sf:errors path="remarks"/>
			</div>
		</div>
		
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.save"/></mh:button>
				<mh:button class="btn btn-default" href="business/checkTable/list"><fmt:message key="button.backList"/></mh:button>
			</div>
		</div>
		
	</sf:form>
</div>
<script type="text/javascript" src="<mh:theme code='jquery.vaildate.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	var checkTableForm = $("#checkTable");
	//配置验证信息
	 checkTableForm.validate({
		rules : {
			//基本信息
			title :{
				required : true
			},
			issueOrgan :{
				required : true
			}, 
			baseUrl : {
				required : true
			},
			//其他
			remarks : {
				maxlength : 500
			}
		}
	  });
		//工信部、国务院
	   $("#issueOrganId").on("change",function(e){
		   data = e.added;
		   $("#issueOrgan").val(data.name);
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
