<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>
<%--@Author 甘焕--%>
<div class="col-page-title">
	<c:choose>
		<c:when test="${empty field.id}">
			<fmt:message key="asset.assetField.create" />
		</c:when>
		<c:otherwise>
			${field.label}
		</c:otherwise>
	</c:choose>
</div>

<sf:form modelAttribute="field" role="form" cssClass="form-horizontal">

	<sf:hidden path="id" />
	<sf:hidden path="deviceType"/>
	
	<div class="form-group">
		<sf:label path="label" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.assetField.label" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="label" cssClass="form-control" maxlength="20" />
			<sf:errors path="label" />
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="name" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.assetField.name" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="name" cssClass="form-control" maxlength="20" />
			<sf:errors path="name" />
			<label class="form-hint"><fmt:message key="hint.fieldName"/></label> 
		</div>
	</div>

	<div class="form-group">
		<sf:label path="order" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.assetField.order" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="order" cssClass="form-control" />
			<sf:errors path="order" />
			<label class="form-hint"><fmt:message key="hint.order"/></label> 
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="required" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.assetField.required" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<mh:dictionary var="required" key="bool"  />
			<sf:radiobuttons path="required" items="${required}" />
			<sf:errors path="required" />
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="fieldType" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.assetField.fieldType" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<mh:dictionary var="fieldType" key="assetField.fieldType"  />
			<sf:radiobuttons path="fieldType" items="${fieldType}" />
			<sf:errors path="fieldType" />
		</div>
	</div>
	
	<div class="form-group" style="display: none;" id="dictGroup">
		<sf:label path="dictId" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.assetField.dictValue" />
		</sf:label>
		
		<div class="col-xs-9 form-field">
			<sf:hidden path="dictId"/>
			
			<!-- 数据字典选择 -->
			<mh:dropdown id="dictMenu">
				<jsp:attribute name="values">
					<c:choose>
						<c:when test="${!empty field.dictLabel}">
							${field.dictLabel}
						</c:when>
						<c:otherwise>
							<fmt:message key="button.choose"/>
						</c:otherwise>
					</c:choose>
				</jsp:attribute>
				<jsp:attribute name="menus">
					<li role="presentation" id="dictButtons">
						<div class="input-group">
						  <span class="input-group-btn">
						    <a class="btn btn-primary btn-sm" type="button" href="javascript:void(0);" id="btnSearch">
								<span class="glyphicon glyphicon-search"></span>
							</a>
					      </span>
					      <input type="text" class="form-control" style="height:30px;" id="dictSearch">
					      <span class="input-group-btn">
					        <a class="btn btn-primary btn-sm" href="javascript:void(0);" type="button">
					        	<fmt:message key="button.sure"/>
					        </a>
					      </span>
					    </div>
					</li>
					<li role="presentation" id="dictSelect" style="height:250px;overflow:auto;">
						<mh:tree id="dict"
								 rootId="1"
								 url="base/dictionary/find"
								 sortName="order"
								 scriptSelf="false">
						</mh:tree>
					</li>
				</jsp:attribute>
			</mh:dropdown>
			
			<sf:errors path="defaultValue" />
		</div>
	</div>
	
	<div class="form-group" id="defaultGroup">
		<sf:label path="defaultValue" cssClass="col-xs-3 control-label">
			<fmt:message key="asset.assetField.defaultValue" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="defaultValue" cssClass="form-control" maxlength="256" />
			<sf:errors path="defaultValue" />
		</div>
	</div>
	
	<div class="form-group" id="validGroup">
		<sf:label path="validType" cssClass="col-xs-3 control-label">
			<fmt:message key="asset.assetField.validType" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<mh:dictionary var="validType" key="assetField.validType"  />
			<sf:radiobuttons path="validType" items="${validType}" />
			<sf:errors path="validType" />
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="hint" cssClass="col-xs-3 control-label">
			<fmt:message key="asset.assetField.hint" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:textarea path="hint" cssClass="form-control" rows="3" cols="5" maxlength="200" />
			<sf:errors path="hint" />
		</div>
	</div>

	<div class="form-group form-button-panel">
		<div class="col-xs-9 col-xs-offset-3 form-buttons">
			<mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.save"/></mh:button>
			<mh:button class="btn btn-default" id="btnBack"><fmt:message key="button.back"/></mh:button>
		</div>
	</div>
	
</sf:form>
<script type="text/javascript">

//隐藏输入信息
function initFieldType(data) {
	switch(data) {
		case '0' :
		break;
		
		case '1' :
		$("#dictGroup").hide();
		$("#validGroup").show();
		$("#defaultGroup").show();
		break;
		
		default :
		$("#dictGroup").show();
		$("#validGroup").hide();
		$("#defaultGroup").hide();
		break;
	};
}

//处理数据字典选择
function initDictTree() {
	var dictSearch = $("#dictSearch"),
		dictText = $("#dictMenu").find(".dropdown-text"),
		dictId = $("#dictId"),
		btnSearch = $("#btnSearch");
	//阻止默认的提交动作
	$("#dictSelect").on("click", function(event) {
		event.stopPropagation();
	});
	//处理查询
	dictSearch.on("click", function(event) {
		event.stopPropagation();
	});
	//
	btnSearch.on("click", function(event) {
		dictTree.search($.trim(dictSearch.val()));
		event.stopPropagation();
	});
	//处理选择
	dictTree.onSelect(function(data) {
		dictText.text(data.text);
		dictId.val(data.id);
	});
}
//Dom载入完成后执行操作
$(document).ready(function() {
	var fieldForm = $("#field");
	//验证表单
	fieldForm.validate({
		rules : {
			label: {
				required : true,
				maxlength : [20]
			},
			name : {
				required : true,
				englishChar : true,
				maxlength : [20]
			},
			order :{
				required : true,
				digits : true
			},
			required : {
				required : true
			},
			fieldType : {
				required : true
			},
			defaultValue : {
				maxlength : [256]
			},
			hint : {
				maxlength : [200] 
			}
			
		}
	});
	//注册按钮事件,保存成功则添加节点到树中
	fieldForm.find("#btnSave").on("click", function(event) {
		var datas = fieldForm.serialize(),
			url = fieldForm.attr("action");
		//动态加载的页面必须手动验证一次
		if(fieldForm.valid()) {
			$.post(url, datas, function(response) {
				var domain = response.domain;
				fieldsGroup.load(groupUrl + node.id);
			});
		}
		event.preventDefault();
	});
	
	//返回按钮
	fieldForm.find("#btnBack").on("click", function(event) {
		Fields.loadGroup();
		event.preventDefault();
	});
	
	//初始化输入类型
	initFieldType(${field.fieldType});
	//初始化数据字典
	initDictTree();
	//切换输入类型
	$(":radio[name=fieldType]").on("change", function() {
		initFieldType(this.value);
	});
	
}); 
</script>