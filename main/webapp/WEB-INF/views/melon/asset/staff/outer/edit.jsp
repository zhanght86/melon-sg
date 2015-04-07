<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>

<link rel="stylesheet" href="<mh:theme code='jquery.fileupload.css'/>" media="all" />
<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<link rel="stylesheet" href="<c:url value='/statics/themes/default/melon/melon-mail.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code="jquery.steps.js"/>"></script>
<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='jquery.fileupload.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='jquery.vaildate.js'/>"></script>
<style type="text/css">
	.fileinput-button {
		margin-top:10px;
	}
	.security-upload .fileinput-button{
		margin-left:14px;
	}
	/* .file-item-group{
		margin-left:168px;
	} */
	.certificates-upload .fileinput-button , .paperNum-upload .fileinput-button{
		margin-left:170px;
	}
	.checkoverflow .checkbox-inline{
		overflow:hidden;
	}
	#outerStaff .content{
		height:1300px;
	}
</style>
<sf:form modelAttribute="outerStaff" role="form" cssClass="form-horizontal">
	
		<sf:hidden path="id" />
		<sf:hidden path="secProtocolBusiness" />
		<sf:hidden path="certificateBusiness" />
		<sf:hidden path="code" />
	  
	   <div id="staff-wizard">
	  <%-- 基本信息 --%>
		<mh:section id="basic">
		
			<jsp:attribute name="title">
				<fmt:message key="security.account.basic"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
	  			<div class="col-xs-8 col-xs-offset-2 form-singlon">
	  			
				<div class="form-group">
					<sf:label path="name" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required" />
						<fmt:message key="melon.outerStaff.name" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="name" cssClass="form-control" maxlength="20" />
						<sf:errors path="name" />
					</div>
				</div>
			
				<div class="form-group">
					<sf:label path="number" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.outerStaff.number" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="number" cssClass="form-control" maxlength="20" />
						<sf:errors path="number" />
					</div>
				</div>
			
				<div class="form-group">
					<sf:label path="sex" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.outerStaff.sex" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<mh:dictionary key="sexType" var="sexEnum"/>
						<sf:radiobuttons path="sex" items="${sexEnum}"/>
						<sf:errors path="sex" />
					</div>
				</div>
			
				<div class="form-group">
					<sf:label path="birthday" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.outerStaff.birthday" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="birthday" cssClass="form-control"/>
						<sf:errors path="birthday" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="tel" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required"/> 
						<fmt:message key="melon.outerStaff.tel" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="tel" cssClass="form-control"/>
						<sf:errors path="tel" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="phone" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required"/> 
						<fmt:message key="melon.outerStaff.phone" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="phone" cssClass="form-control"/>
						<sf:errors path="phone" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="mail" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required"/> 
						<fmt:message key="melon.outerStaff.mail" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="mail" cssClass="form-control"/>
						<sf:errors path="mail" />
					</div>
				</div>
			</div>
		</jsp:attribute>
		
	</mh:section>
		
	<%-- 岗位信息 --%>
	<mh:section id="station">
	
		<jsp:attribute name="title">
			<fmt:message key="melon.staff.station"/>
		</jsp:attribute>
		
		<jsp:attribute name="body">
		
		<div class="col-xs-8 col-xs-offset-2 form-singlon">
		
			<div class="form-group">
				<sf:label path="companyName" cssClass="col-xs-3 control-label">
				    <fmt:message key="hint.required"/>
					<fmt:message key="melon.outerStaff.companyName"/>
				</sf:label>
				
				<div class="col-xs-9 form-field" >
					<sf:input path="companyName" cssClass="form-control" maxlength="400"/>
					<sf:errors path="companyName"/>
				</div>
			</div>
					
			<div class="form-group">
				<sf:label path="departName" cssClass="col-xs-3 control-label">
				    <fmt:message key="hint.required"/>
					<fmt:message key="melon.outerStaff.department"/>
				</sf:label>
				
				<div class="col-xs-9 form-field" id="departName-select">
				    <sf:input path="departName" cssClass="form-control" maxlength="400"/>
					<sf:errors path="departName"/>
				</div>
			</div>
			
			<div class="form-group">
			    <sf:label path="chargeName" cssClass="col-xs-3 control-label">
					<fmt:message key="hint.required"/> 
					<fmt:message key="melon.outerStaff.chargePerson"/>
				</sf:label>
				<div class="col-xs-9 form-field" id="chargeName-select">
					<!-- chargeName -->
				    <sf:hidden path="chargeName"/>
				    <sf:hidden path="chargePerson"/>
				    <mh:dropdown id="chargeMenu">
						<jsp:attribute name="values">
							${outerStaff.chargeName}
						</jsp:attribute>
						<jsp:attribute name="menus">
							<li role="presentation" id="organButtons">
								<div class="input-group">
								  <span class="input-group-btn">
								    <a class="btn btn-primary btn-sm" type="button" href="javascript:void(0);" id="btnChSearch">
										<span class="glyphicon glyphicon-search"></span>
									</a>
							      </span>
							      <input type="text" class="form-control" style="height:30px;" id="chargeSearch">
							      <span class="input-group-btn">
							        <a class="btn btn-primary btn-sm" href="javascript:void(0);" type="button">
							        	<fmt:message key="button.sure"/>
							        </a>
							      </span>
							    </div>
							</li>
							<li role="presentation" id="chargeSelect" style="height:250px;overflow:auto;">
								<mh:tree id="charge"
										rootId="${organId}"
										url="security/account/accTree"
										sortName="order"
										scriptSelf="false">
								</mh:tree>
							</li>
						</jsp:attribute>
					</mh:dropdown>
					<label class="form-hint"><fmt:message key="melon.staff.person.hint" /></label>
					<sf:errors path="chargePerson"/>
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="officeDate" cssClass="col-xs-3 control-label">
					<fmt:message key="melon.outerStaff.officeDate" />
				</sf:label>
		
				<div class="col-xs-9 form-field">
					<sf:input path="officeDate" cssClass="form-control" />
					<sf:errors path="officeDate" />
				</div>
			</div>
		
			<div class="form-group">
				<sf:label path="fullJobs" cssClass="col-xs-3 control-label">
					<fmt:message key="hint.required"/>
					<fmt:message key="melon.outerStaff.fullJob" />
				</sf:label>
		
				<div class="col-xs-9 form-field">
					<mh:dictionary key="outerstaff.fulljob" var="fullJobEnum"/>
					<sf:checkboxes path="fullJobs" items="${fullJobEnum}"/>
					<sf:errors path="fullJobs" />
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="examine" cssClass="col-xs-3 control-label">
					<fmt:message key="melon.outerStaff.examine" />
				</sf:label>
		
				<div class="col-xs-9 form-field">
					<mh:dictionary key="bool" var="booleanEnum" />
					<sf:radiobuttons path="examine" items="${booleanEnum}" />
					<sf:errors path="examine" />
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="security" cssClass="col-xs-3 control-label">
					<fmt:message key="melon.outerStaff.security" />
				</sf:label>
		
				<div class="col-xs-9 form-field">
					<sf:radiobuttons path="security" items="${booleanEnum}"/>
					<sf:errors path="security" />
				</div>
				<div class="security-upload">
					<mh:upload domainId="${outerStaff.secProtocolBusiness}" />
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="certificates" cssClass="col-xs-3 control-label">
					<fmt:message key="melon.outerStaff.certificate" />
				</sf:label>
		
				<div class="col-xs-9 form-field checkoverflow">
					<mh:dictionary key="interstaff.certificate" var="certificateEnum" />
					<sf:checkboxes path="certificates" items="${certificateEnum}"/>
					<sf:errors path="certificates" />
				</div>
				<div class="certificates-upload">
					<mh:upload domainId="${outerStaff.certificateBusiness}" />
				</div>
			</div>
			
			<div class="form-group" style="display :hidden" id="otherCertGroup">
				<sf:label path="otherCertificate" cssClass="col-xs-3 control-label">
					<fmt:message key="melon.outerStaff.otherCertificate" />
				</sf:label>
		
				<div class="col-xs-9 form-field">
					<sf:input path="otherCertificate" cssClass="form-control" maxlength="50" />
					<sf:errors path="otherCertificate" />
				</div>
			</div>
			
			
			<div class="form-group">
				<sf:label path="maintainAutor" cssClass="col-xs-3 control-label">
					<fmt:message key="melon.outerStaff.maintainAutor" />
				</sf:label>
		
				<div class="col-xs-9 form-field">
					<mh:dictionary key="outerstaff.maintainAutor" var="maintainAutorEnum" />
					<sf:checkboxes path="maintainAutor" items="${maintainAutorEnum}"/>
					<sf:errors path="maintainAutor" />
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="project" cssClass="col-xs-3 control-label">
					<fmt:message key="melon.outerStaff.project" />
				</sf:label>
		
				<div class="col-xs-9 form-field">
					<sf:textarea path="project" cssClass="form-control" maxlength="256" rows="5" />
					<sf:errors path="project" />
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="work" cssClass="col-xs-3 control-label">
					<fmt:message key="melon.outerStaff.work" />
				</sf:label>
		
				<div class="col-xs-9 form-field">
					<mh:dictionary key="outerstaff.work" var="workEnum" />
					<sf:checkboxes path="work" items="${workEnum}"/>
					<sf:errors path="work" />
				</div>
			</div>
			
			<div class="form-group" style="display :hidden" id="otherWorkGroup">
				<sf:label path="otherWork" cssClass="col-xs-3 control-label">
					<fmt:message key="melon.outerStaff.otherWork" />
				</sf:label>
		
				<div class="col-xs-9 form-field">
					<sf:input path="otherWork" cssClass="form-control" maxlength="50" />
					<sf:errors path="otherWork" />
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="remark" cssClass="col-xs-3 control-label">
					<fmt:message key="melon.outerStaff.remark" />
				</sf:label>
		
				<div class="col-xs-9 form-field">
					<sf:textarea path="remark" cssClass="form-control" maxlength="256" rows="5" />
					<sf:errors path="remark" />
				</div>
			</div>
		
		</div>
		</jsp:attribute>
	</mh:section>
	</div>
</sf:form>
<script type="text/javascript">
	$(document).ready(function() {
		
		var outerStaffForm = $("#outerStaff");
		//配置验证信息
		 outerStaffForm.validate({
			rules : {
				name : {
					required : true,
					maxlength : 20
				},
				number : {
					maxlength : 20
				},
				tel : {
					required : true,
					tel : true
				},
				phone : {
					required : true,
					mobile : true
				},
				mail : {
					required : true,
					email : true
				},
				fullJobs : {
					required : true
				},
				maintainAutor : {
					maxlength : 256
				},
				work : {
					maxlength : 256
				},
				project : {
					maxlength : 256
				},
				remark : {
					maxlength : 256
				}
			}
		}); 
		
		//配置向导
		$("#staff-wizard").steps({
			
			 headerTag: "h4",
			 bodyTag: "section",
			 transitionEffect: "slideLeft",
			 
			 enableCancelButton : true,

			 //逐步验证
			 onStepChanging: function (event, currentIndex, newIndex) {
				outerStaffForm.validate().settings.ignore = ":disabled,:hidden";
				return outerStaffForm.valid();
			 },
			 
			 onFinishing: function (event, currentIndex) {
				outerStaffForm.validate().settings.ignore = ":disabled";
			 	return outerStaffForm.valid();
			 },
			 //提交表单
			 onFinished: function (event, currentIndex)	{
				outerStaffForm.submit();
				$("#finish").prop("disabled", true);
				event.preventDefault();
			 },
			 onInit : function(event, currentIndex) {
					
			 },
			
			onCanceled : function(event) {
				window.location.href = '<c:url value="/staff/outer/home"/>';
			}
		});
		
		window.onload = function(){
			doChargeSearch();
			
			
			//其他checkbox 输入框
			//初始化时判断其他是否选中
			var otherCertificate = $("#otherCertGroup"),
				otherWork = $("#otherWorkGroup");
			//技能其他 
			if($('#certificates8').is(':checked')){
				otherCertificate.show();
			}else{
				var otherCertificateInput = otherCertificate.find("#otherCertificate");
				otherCertificateInput.val("");
				otherCertificate.hide();
			}
			$('#certificates8').on("click",function(){
				if($('#certificates8').is(':checked')){
					otherCertificate.show();
				}else{
					var otherCertificateInput = otherCertificate.find("#otherCertificate");
					otherCertificateInput.val("");
					otherCertificate.hide();
				}
			});
			
		/* 	//验证专职岗位     安全管理员和安全审计员只能选择一个
			$("#fullJobs3").on("click",function(){
				if($('#fullJobs3').is(':checked')){
					$("#fullJobs4").prop("disabled",true);
				} else {
					$("#fullJobs4").prop("disabled",false);
				}
			});
			$("#fullJobs4").on("click",function(){
				if($('#fullJobs4').is(':checked')){
					$("#fullJobs3").prop("disabled",true);
				} else {
					$("#fullJobs3").prop("disabled",false);
				}
			}); */
			
			//工作其他 
			if($('#work6').is(':checked')){
				otherWork.show();
			}else{
				var otherWorkInput = otherWork.find("#otherWork");
				otherWorkInput.val("");
				otherWork.hide();
			}
			$('#work6').on("click",function(){
				if($('#work6').is(':checked')){
					otherWork.show();
				}else{
					var otherWorkInput = otherWork.find("#otherWork");
					otherWorkInput.val("");
					otherWork.hide();
				}
			}); 
		};

	    //上级负责人选择
		function doChargeSearch() {
			var chargeSearch = $("#chargeSearch"),
				chargeText = $("#chargeMenu").find(".dropdown-text"),
				chargeName = $("#chargeName"),
				chargePerson = $("#chargePerson"),
				btnSearch = $("#btnChSearch");
			//阻止默认的提交动作
			$("#chargeSelect").on("click", function(event) {
				event.stopPropagation();
			});
			//处理查询
			chargeSearch.on("click", function(event) {
				event.stopPropagation();
			});
			btnSearch.on("click", function(event) {
				organTree.search($.trim(chargeSearch.val()));
				event.stopPropagation();
			});
			//处理选择
			chargeTree.onSelect(function(data) {
				if(data.type == 3) {
					chargeText.text(data.text);
					chargeName.val(data.text);
					chargePerson.val(data.id);
				}
			});
		}
	});
</script>
<hh:date id="birthday" showSelect="true" />
<hh:date id="officeDate" showSelect="true" />