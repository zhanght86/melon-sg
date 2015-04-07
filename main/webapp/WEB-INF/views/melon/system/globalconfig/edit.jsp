<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>
<style type="text/css">
	#attributeMap input {
		display:inline-block;
		height:30px;
		margin:5px 0
	}
	 .tmplate {
		display : none;
	}
</style>

<sf:form modelAttribute="globalConfig" role="form" cssClass="form-horizontal globalConfig">
	
	<sf:hidden path="id"/>
	<sf:hidden path="organName" />
	<sf:hidden path="ipLeftRange" />
	<sf:hidden path="ipRightRange" />
	<div id="globalConfig-wizard">
		<!-- 信息配置 -->
		<mh:section id="info">
			<jsp:attribute name="title">
				<fmt:message key="system.globalConfig.info"/>
			</jsp:attribute>
				<jsp:attribute name="body">
					<div class="form-group">
						<sf:label path="organId" cssClass="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.organName" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<sf:input path="organId" cssClass="form-control" maxlength="20"/>
							<hh:select id="organId" onceUrl="organ/organization/findOrgans?type=1&parentId=0"/>
							<sf:errors path="organId" />
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="servicer" cssClass="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.servicer" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<sf:input path="servicer" cssClass="form-control" maxlength="20"/>
							<sf:errors path="servicer" />
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="serviceTel" cssClass="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.serviceTel" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<sf:input path="serviceTel" cssClass="form-control" maxlength="20"/>
							<sf:errors path="serviceTel" />
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="serviceMail" cssClass="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.serviceMail" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<sf:input path="serviceMail" cssClass="form-control" maxlength="20"/>
							<sf:errors path="serviceMail" />
						</div>
					</div>
					
					
					
					<div class="form-group">
						<sf:label path="systemHost" cssClass="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.systemHost" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<sf:input path="systemHost" cssClass="form-control" maxlength="20"/>
							<sf:errors path="systemHost" />
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="systemMail" cssClass="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.systemMail" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<sf:input path="systemMail" cssClass="form-control" maxlength="50"/>
							<sf:errors path="systemMail" />
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="systemMailPassWord" cssClass="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.systemMailPassWord" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<sf:password path="systemMailPassWord" cssClass="form-control" maxlength="50"/>
							<sf:errors path="systemMailPassWord" />
						</div>
					</div>
					
					
					
					
					
					
					
					
					<div class="form-group">
						<label class="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.attributes" />
						</label>
				
						<div class="col-xs-9 form-field" id="attributeMap">
							<button class="btn btn-xs btn-property" type="button" id="btnAdd" ><fmt:message key="button.addAttr"/>&nbsp;<i class="glyphicon glyphicon-plus"></i></button>
							<span class="form-hint" style="display:inline-block;"><fmt:message key="system.globalConfig.attributes.hint" /></span>		
							
							<c:forEach items="${globalConfig.attributes}" var="attribute">
								<div>
									<input name="attributesKey" class="form-control attributeKey" style="width:30%;" maxlength="20" value="${attribute.key}">
									<span>-</span>
									<input name="attributesValue" class="form-control attributeValue" style="width:60%;" maxlength="100" value="${attribute.value}">
									<div class="file-remove-icon2 btnRemove" style="padding:0 4px;" ></div>
								</div>
							</c:forEach>
							
						</div>
					</div>
					
				</jsp:attribute>
			</mh:section>
			
			<!-- 行业配置 -->
			<%-- <mh:section id="indus">
			<jsp:attribute name="title">
				<fmt:message key="system.globalConfig.indus"/>
			</jsp:attribute>
				<jsp:attribute name="body">
					<div class="form-group">
						<sf:label path="organId" cssClass="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.organName" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<sf:input path="organId" cssClass="form-control" maxlength="20"/>
							<hh:select id="organId" onceUrl="organ/organization/findOrgans?type=1&parentId=0"/>
							<sf:errors path="organId" />
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="servicer" cssClass="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.servicer" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<sf:input path="servicer" cssClass="form-control" maxlength="20"/>
							<sf:errors path="servicer" />
						</div>
					</div>
					
				</jsp:attribute>
			</mh:section> --%>
			
			<%--其他信息 --%>
			<mh:section id="other">
			<jsp:attribute name="title">
				<fmt:message key="system.globalConfig.security"/>
			</jsp:attribute>
				<jsp:attribute name="body">
					<div class="form-group">
						<sf:label path="passStrength" cssClass="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.passStrength" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<mh:dictionary key="pass.strength" var="passEnum"/>
							<sf:checkboxes path="passStrength" items="${passEnum}"/>
							<sf:errors path="passStrength" />
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="openStart" cssClass="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.open" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<select name="openStart" class="form-control" style="width:100px;display:inline">
								<c:forEach items="${hours}" var="hour">
									<option value="${hour}">${hour}</option>
								</c:forEach>
							</select>
							<span>时至</span>
							<select name="openEnd" class="form-control" style="width:100px;display:inline;">
								<c:forEach items="${hours}" var="hour">
									<option value="${hour}">${hour}</option>
								</c:forEach>
							</select>
							<span>时</span>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="ipLeftRange" class="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.ip" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<input type="text" class="form-control" style="width:11%;display:inline" name="ipLeft" maxlength="3"/>.
							<input type="text" class="form-control" style="width:11%;display:inline" name="ipLeft" maxlength="3"/>.
							<input type="text" class="form-control" style="width:11%;display:inline" name="ipLeft" maxlength="3"/>.
							<input type="text" class="form-control" style="width:11%;display:inline" name="ipLeft" maxlength="3"/>-
							<input type="text" class="form-control" style="width:11%;display:inline" name="ipRight" maxlength="3"/>.
							<input type="text" class="form-control" style="width:11%;display:inline" name="ipRight" maxlength="3"/>.
							<input type="text" class="form-control" style="width:11%;display:inline" name="ipRight" maxlength="3"/>.
							<input type="text" class="form-control" style="width:11%;display:inline" name="ipRight" maxlength="3"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="tryTimes" class="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.try" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<sf:input path="tryTimes" cssClass="form-control" maxlength="2" />
							<sf:errors path="tryTimes" />
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="lockedTime" cssClass="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.locked" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<sf:input path="lockedTime" cssClass="form-control" maxlength="3"/>
							<sf:errors path="lockedTime" />
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="validity" cssClass="col-xs-3 control-label">
							<fmt:message key="system.globalConfig.validity" />
						</sf:label>
				
						<div class="col-xs-9 form-field">
							<sf:input path="validity" cssClass="form-control" maxlength="3"/>
							<sf:errors path="validity" />
						</div>
					</div>
				</jsp:attribute>
			</mh:section>			
		</div>
</sf:form>

<div class="tmplate" >
	<input name="attributesKey" class="form-control attributeKey" style="width:30%;" maxlength="20" placeholder="<fmt:message key='system.globalConfig.attributes.key' />" />
	<span>-</span>
	<input name="attributesValue" class="form-control attributeValue" style="width:60%;" maxlength="100" placeholder="<fmt:message key='system.globalConfig.attributes.value' />" />
	<div class="file-remove-icon btnRemove" style="padding:0 4px;" ></div>
</div>
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<script src="<mh:theme code="jquery.steps.js"/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	var globalConfigForm = $("#globalConfig");
	globalConfigForm.validate({
		rules : {
			organName : {
				required : true,
				stringChar : true
			},
			servicer : {
				stringChar : true
			},
			serviceTel : {
				tel :true
			},
			serviceMail : {
				email : true
			},
			validity : {
				required : true,
				integer : true
			},
			ipLeft : {
				required : true,
				integer : true,
				range : [0,255]
			},
			ipRight : {
				required : true,
				integer : true,
				range : [0,255]
			}
		},
		
	});
	
	function init() {
		var ipLeft = '${globalConfig.ipLeftRange}',
			ipRight = '${globalConfig.ipRightRange}',
			openStart = '${globalConfig.openStart}',
			openEnd = '${globalConfig.openEnd}',
			leftItem = ipLeft.split("."),
			rightItem = ipRight.split(".");
		$.each(leftItem, function(index, value) {
			$('input[name="ipLeft"]').eq(index).val(value);
		});
		$.each(rightItem, function(index, value) {
			$('input[name="ipRight"]').eq(index).val(value);
		});
		//设置时间选中
		$("[name=openStart]").find('option[value='+openStart+']').prop("selected", true);
		$("[name=openEnd]").find('option[value='+openEnd+']').prop("selected", true);
	}
	
	function IpControl() {
		$('input[name="ipLeft"]').keyup(function() {
			if($(this).index() != 3) {
				if($(this).val().length == 3) {
					$(this).next().focus();
				}
			}
		});
		$('input[name="ipRight"]').keyup(function() {
			if($(this).index() != 3) {
				if($(this).val().length == 3) {
					$(this).next().focus();
				}
			}
		});
	}
	
	//配置向导
	$("#globalConfig-wizard").steps({
		
		 headerTag: "h4",
		 bodyTag: "section",
		 transitionEffect: "slideLeft",
		 //逐步验证
		 onStepChanging: function (event, currentIndex, newIndex) {
			 globalConfigForm.validate().settings.ignore = ":disabled,:hidden";
			return globalConfigForm.valid();
		 },
		 
		 onFinishing: function (event, currentIndex) {
			 globalConfigForm.validate().settings.ignore = ":disabled";
		 	return globalConfigForm.valid();
		 },
		 //提交表单
		 onFinished: function (event, currentIndex)	{
			 var ipLeft='', ipRight='';
			 $('input[name="ipLeft"]').each(function(key,item) {
				 ipLeft += $(item).val()+".";
			 });
			 
			 $('input[name="ipRight"]').each(function(key,item) {
				 ipRight += $(item).val()+".";
			 });
			 $("#ipLeftRange").val(ipLeft);
			 $("#ipRightRange").val(ipRight);
			 globalConfigForm.submit();
		 }
	});
	
	/*
	 * 所有文档加载完成后注册
	 */
	window.onload = function() {
		//添加按钮事件
		$("#btnAdd").click(function(event) {	
			$('.tmplate').clone(true).removeClass("tmplate").appendTo("#attributeMap");
			event.preventDefault();
		});
		
		//删除按钮事件
		$(".btnRemove").click(function(event) {
			$(this).parent().remove();
			event.preventDefault();
		});
		
		//获取组织机构名称
		$("#organId").on("change", function(e) {
			var data  = e.added;
			$("#organName").val(data.name);
		});
		
		init();
		
		IpControl();
		
	};
	
});

</script>


