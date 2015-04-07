<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<style type="text/css">
	#propMap input {
		display:inline-block;
		height:30px;
		margin:5px 0
	}
	 .tmplate {
		display : none;
	}
</style>

<sf:form modelAttribute="bpmProcess" role="form" cssClass="form-horizontal">
	<sf:hidden path="id"/>
	<sf:hidden path="version"/>
	<sf:hidden path="createTime"/>
	
	<div id="bpmProcess-wizard">
		<%-- 基本信息 --%>
		<mh:section id="basic">
			<jsp:attribute name="title">
				<fmt:message key="workflow.bpmProcess.basic"/>
			</jsp:attribute>
			<jsp:attribute name="body">
				<div class="col-xs-8 col-xs-offset-2 form-singlon">
				
				 	<div class="form-group">
						<sf:label path="code" cssClass="col-xs-3 control-label">
							<fmt:message key="workflow.bpmProcess.code"/>
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:input path="code" cssClass="form-control" />
							<sf:errors path="code"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="name" cssClass="col-xs-3 control-label">
							<fmt:message key="workflow.bpmProcess.name"/>
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:input path="name" cssClass="form-control" maxlength="50"/>
							<sf:errors path="name"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="type" cssClass="col-xs-3 control-label">
							<fmt:message key="workflow.bpmProcess.type"/>
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:input path="type" cssClass="form-control" maxlength="50"/>
							<sf:errors path="type"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="props" cssClass="col-xs-3 control-label">
							<fmt:message key="workflow.bpmProcess.props"/>
						</sf:label>
						<div class="col-xs-9 form-field" id="propMap">
						    <button class="btn btn-xs btn-property" type="button" id="btnAdd" ><fmt:message key="button.addAttr"/>&nbsp;<i class="glyphicon glyphicon-plus"></i></button>
							 <c:forEach items="${bpmProcess.props}" var="prop">
								<div>
									<input name="propKey" class="form-control propKey" style="width:30%;" maxlength="20" value="${prop.key}">
									<span>-</span>
									<input name="propValue" class="form-control propValue" style="width:60%;" maxlength="100" value="${prop.value}">
									<div class="file-remove-icon2 btnRemove" style="padding:0 4px;" ></div>
								</div>
							</c:forEach> 
						</div>
					</div>
				</div>	
			</jsp:attribute>
		</mh:section>
	
		<%-- 流程图信息 --%>
		<mh:section id="process">
			<jsp:attribute name="title">
				<fmt:message key="workflow.bpmProcess.process"/>
			</jsp:attribute>
			<jsp:attribute name="body">
				<div class="col-xs-8 col-xs-offset-2 form-singlon">
					
				</div>	
			</jsp:attribute>
		</mh:section>
		
		<%--其他信息 --%>
		<mh:section id="other">
			<jsp:attribute name="title">
				<fmt:message key="workflow.bpmProcess.other"/>
			</jsp:attribute>
			<jsp:attribute name="body">
				<div class="col-xs-8 col-xs-offset-2 form-singlon">
					<div class="form-group">
						<sf:label path="remarks" cssClass="col-xs-3 control-label">
							<fmt:message key="workflow.bpmProcess.remarks"/>
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:textarea path="remarks" cssClass="form-control"  rows="5"/>
							<sf:errors path="remarks"/>
						</div>
					</div>
				</div>
			</jsp:attribute>
		</mh:section>

	</div>
</sf:form>
<div class="tmplate" >
	<input name="propKey" class="form-control propKey" style="width:30%;" maxlength="20" placeholder="<fmt:message key='workflow.bpmProcess.propKey' />" />
	<span>-</span>
	<input name="propValue" class="form-control propValue" style="width:60%;" maxlength="100" placeholder="<fmt:message key='workflow.bpmProcess.propValue' />" />
	<div class="file-remove-icon2 btnRemove" style="padding:0 4px;" ></div>
</div>
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<script src="<mh:theme code="jquery.steps.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var bpmProcess = $("#bpmProcess");
		//配置验证信息
		 bpmProcess.validate({
			rules : {
				//基本信息
				name : {
					required : true,
				},
				code : {
					required : true
				},
			}
		}); 
		
		//配置向导
		$("#bpmProcess-wizard").steps({
			
			 headerTag: "h4",
			 bodyTag: "section",
			 transitionEffect: "slideLeft",
			 //逐步验证
			 onStepChanging: function (event, currentIndex, newIndex) {
				bpmProcess.validate().settings.ignore = ":disabled,:hidden";
				return bpmProcess.valid();
			 },
			 
			 onFinishing: function (event, currentIndex) {
				 bpmProcess.validate().settings.ignore = ":disabled,:hidden";
			 	return bpmProcess.valid();
			 },
			 //提交表单
			 onFinished: function (event, currentIndex)	{
				 bpmProcess.submit();
			 }
		});
		
		
		//添加按钮事件
		$("#btnAdd").click(function(event) {	
			$('.tmplate').clone(true).removeClass("tmplate").appendTo("#propMap");
			event.preventDefault();
		});
		
		//删除按钮事件
		$(".btnRemove").click(function(event) {
			$(this).parent().remove();
			event.preventDefault();
		});
		
	});
</script>