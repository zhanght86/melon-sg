<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<script type="text/javascript" src="<mh:theme code='ckeditor.js'/>"></script>
<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<script src="<mh:theme code="jquery.steps.js"/>"></script>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="knowledge" role="form" cssClass="form-horizontal" method="POST">

	<sf:hidden path="id" />
	<sf:hidden path="count"/>
	
		<div class="form-group">
			<sf:label path="title" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="knowledge.base.title" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="title" cssClass="form-control"  />
				<sf:errors path="title" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="issueOrgan" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="knowledge.base.issueOrgan" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="issueOrgan" cssClass="form-control" maxlength="12" />
				<sf:errors path="issueOrgan" />
			</div>
		</div>

		
		<div class="form-group">
			<sf:label path="issueDate" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="knowledge.base.issueDate" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="issueDate" cssClass="form-control" readOnly="true"/>
				<hh:date id="issueDate" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="keywords" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="knowledge.base.keywords" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:input path="keywords" cssClass="form-control" maxlength="64" />
				<sf:errors path="keywords" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="type" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="knowledge.base.type" />
			</sf:label>

			<div class="col-xs-9 form-field">
				<sf:hidden path="type"/>
				
				<mh:dropdown id="typeMenu">
					<jsp:attribute name="values">
						${knowledge.type}
					</jsp:attribute>
					<jsp:attribute name="menus">
						<li role="presentation" id="typeButtons">
							<div class="input-group">
							  <span class="input-group-btn">
							    <a class="btn btn-primary btn-sm" type="button" href="javascript:void(0);" id="btnSearch">
									<span class="glyphicon glyphicon-search"></span>
								</a>
						      </span>
						      <input type="text" class="form-control" style="height:30px;" id="typeSearch">
						      <span class="input-group-btn">
						        <a class="btn btn-primary btn-sm" href="javascript:void(0);" type="button">
						        	<fmt:message key="button.sure"/>
						        </a>
						      </span>
						    </div>
						</li>
						<li role="presentation" id="typeSelect" style="height:250px;overflow:auto;">
							<mh:tree id="type"
									rootId="1"
									url="knowledge/type/find"
									sortName="order"
									scriptSelf="false">
							</mh:tree>
						</li>
					</jsp:attribute>
				</mh:dropdown>
					
				<sf:errors path="type" />
	 		</div>
		</div>
	
		<div class="form-group">
			<div class="col-xs-12 form-editor form-field">
				<sf:textarea path="content" cssClass="form-control" rows="20"/>
			</div>
		</div>
	
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.save"/></mh:button>
				<a class="btn btn-default"   href="<c:url value="/knowledge/base/list"/>" ><fmt:message key="button.backList"/></a>
			</div>
		</div>
</sf:form>
</div>
<script type="text/javascript" src="<mh:theme code='jquery.vaildate.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	CKEDITOR.replace("content");
	var checkTableForm = $("#knowledge");
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
			issueDate :{
				required : true
			},
			keywords :{
				required : true
			},
			type :{
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


/*
 * 所有文档加载完成后注册
 */
window.onload = function() {
	//类型处理
	doType();
}
//处理物理环境
function doType(){
	var typeSearch = $("#typeSearch"),
	typeText = $("#typeMenu").find(".dropdown-text"),
	type = $("#type"),
	btnSearch = $("#btnSearch");
	//阻止默认的提交动作
	$("#typeSelect").on("click", function(event) {
		event.stopPropagation();
	});
	//处理查询
	typeSearch.on("click", function(event) {
		event.stopPropagation();
	});
	btnSearch.on("click", function(event) {
		enviTree.search($.trim(enviSearch.val()));
		event.stopPropagation();
	});
	//处理选择
	typeTree.onSelect(function(data) {
		typeText.text(data.text);
		type.val(data.id);
	});
}
</script>