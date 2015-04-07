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
<script type="text/javascript" src="<mh:theme code="jquery.steps.js"/>"></script>
<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='jquery.fileupload.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='jquery.vaildate.js'/>"></script>
<style type="text/css">
	.fileinput-button{
		margin-top:10px;
	}
	.security-upload .fileinput-button{
	}
	.certificates-upload .fileinput-button , .paperNum-upload .fileinput-button{
		margin-left:170px;
	}
	#interStaff .content{
		height:3400px;
	}
	.checkoverflow .checkbox-inline{
		overflow:hidden;
	}
	
	.certificates-upload .ajax-file-upload .fileinput-button{
		margin-left:135px;
	}
	.paperNum-upload .fileinput-button{
		margin-left:15px;
	}
</style>
<sf:form modelAttribute="interStaff" role="form" cssClass="form-horizontal">
	
		<sf:hidden path="id" />
		<sf:hidden path="secProtocolBusiness" />
		<%-- <sf:hidden path="certificateBusiness" /> --%>
		<sf:hidden path="paperBusiness" />
		<sf:hidden path="code" />
	   
	    <div id="staff-wizard">
	    <%-- 基本信息 --%>
		<mh:section id="basic">
		
			<jsp:attribute name="title">
				<fmt:message key="melon.staff.basic"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
	  			<div class="col-xs-8 col-xs-offset-2 form-singlon">
	  			
				<div class="form-group">
					<sf:label path="name" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required" />
						<fmt:message key="melon.interStaff.name" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="name" cssClass="form-control" maxlength="20" />
						<sf:errors path="name" />
					</div>
				</div>
			
				<div class="form-group">
					<sf:label path="number" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.number" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="number" cssClass="form-control" maxlength="20" />
						<sf:errors path="number" />
					</div>
				</div>
			
				<div class="form-group">
					<sf:label path="sex" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.sex" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<mh:dictionary key="sexType" var="sexEnum"/>
						<sf:radiobuttons path="sex" items="${sexEnum}"/>
						<sf:errors path="sex" />
					</div>
				</div>
			
				<div class="form-group">
					<sf:label path="birthday" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.birthday" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="birthday" cssClass="form-control"/>
						<sf:errors path="birthday" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="tel" cssClass="col-xs-3 control-label">
					<fmt:message key="hint.required"/> 
						<fmt:message key="melon.interStaff.tel" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="tel" cssClass="form-control" />
						<sf:errors path="tel" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="phone" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required"/> 
						<fmt:message key="melon.interStaff.phone" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="phone" cssClass="form-control" />
						<sf:errors path="phone" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="mail" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required"/> 
						<fmt:message key="melon.interStaff.mail" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="mail" cssClass="form-control" />
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
					<sf:label path="organId" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required"/>
						<fmt:message key="melon.interStaff.organ" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:hidden path="organName"/>
						<sf:hidden path="organId"/>
						<!-- 准备单位选择 -->
						<mh:dropdown id="organMenu">
							<jsp:attribute name="values">
								${interStaff.organName}
							</jsp:attribute>
							<jsp:attribute name="menus">
								<li role="presentation" id="organButtons">
									<div class="input-group">
									  <span class="input-group-btn">
									    <a class="btn btn-primary btn-sm" type="button" href="javascript:void(0);" id="btnSearch">
											<span class="glyphicon glyphicon-search"></span>
										</a>
								      </span>
								      <input type="text" class="form-control" style="height:30px;" id="organSearch">
								      <span class="input-group-btn">
								        <a class="btn btn-primary btn-sm" href="javascript:void(0);" type="button">
								        	<fmt:message key="button.sure"/>
								        </a>
								      </span>
								    </div>
								</li>
								<li role="presentation" id="organSelect" style="height:250px;overflow:auto;">
									<mh:tree id="organ"
											rootId="${organId}"
											url="organ/organization/getSubOrgan"
											sortName="order"
											scriptSelf="false">
									</mh:tree>
								</li>
							</jsp:attribute>
						</mh:dropdown>
						<label class="form-hint"><fmt:message key="melon.staff.organ.hint" /></label>
						<sf:errors path="organId" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="departName" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.department"/>
					</sf:label>
					
					<div class="col-xs-9 form-field" id="departName-select">
					    <sf:input path="departName" cssClass="form-control" />
						<sf:errors path="departName"/>
					</div>
				</div>
				
				<div class="form-group">
				    <sf:label path="chargeName" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required"/> 
						<fmt:message key="melon.interStaff.chargePerson"/>
					</sf:label>
					<div class="col-xs-9 form-field" id="chargeName-select">
						<!-- chargeName -->
					    <sf:hidden path="chargeName"/>
					    <sf:hidden path="chargePerson"/>
					    <mh:dropdown id="chargeMenu">
							<jsp:attribute name="values">
								${interStaff.chargeName}
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
						<fmt:message key="melon.interStaff.officeDate" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="officeDate" cssClass="form-control" />
						<sf:errors path="officeDate" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="title" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required"/>
						<fmt:message key="melon.interStaff.title" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<mh:dictionary key="interstaff.title" var="titleEnum" />
						<sf:radiobuttons path="title" items="${titleEnum}"/>
						<sf:errors path="title" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="techTitle" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required"/>
						<fmt:message key="melon.interStaff.techTitle" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<mh:dictionary key="interstaff.techtitle" var="techtitleEnum" />
						<sf:radiobuttons path="techTitle" items="${techtitleEnum}"/>
						<sf:errors path="techTitle" />
					</div>
				</div>
			
				<div class="form-group">
					<sf:label path="fullJobs" cssClass="col-xs-3 control-label">
						<fmt:message key="hint.required"/>
						<fmt:message key="melon.interStaff.fullJob" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<mh:dictionary key="interstaff.fulljob" var="fullJobEnum"/>
						<sf:checkboxes path="fullJobs" items="${fullJobEnum}"/>
						<label class="form-hint"><fmt:message key="melon.interStaff.fullJob.hint" /></label>
						<sf:errors path="fullJobs" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="partJobs" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.partJob" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<mh:dictionary key="interstaff.partjob" var="partJobEnum"/>
						<sf:checkboxes path="partJobs" items="${partJobEnum}"/>
						<sf:errors path="partJobs" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="historyJob" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.historyJob" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:radiobuttons path="historyJob" items="${partJobEnum}"/>
						<sf:errors path="historyJob" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="techSkills" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.techSkill" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<mh:dictionary key="interstaff.techskill" var="teckSkillEnum" />
						<sf:checkboxes path="techSkills" items="${teckSkillEnum}"/>
						<sf:errors path="techSkills" />
					</div>
				</div>
				
				<div class="form-group" style="display :hidden" id="otherSkillGroup">
					<sf:label path="otherSkill" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.otherSkill" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="otherSkill" cssClass="form-control" maxlength="50" />
						<sf:errors path="otherSkill" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="examine" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.examine" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<mh:dictionary key="bool" var="booleanEnum" />
						<sf:radiobuttons path="examine" items="${booleanEnum}" />
						<sf:errors path="examine" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="lecturer" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.lecturer" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<mh:dictionary key="boolInt" var="boolIntEnum" />
						<sf:radiobuttons path="lecturer" items="${boolIntEnum}"/>
						<sf:errors path="lecturer" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="expert" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.expert" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:radiobuttons path="expert" items="${boolIntEnum}"/>
						<sf:errors path="expert" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="projectTeam" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.projectTeam" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:radiobuttons path="projectTeam" items="${boolIntEnum}"/>
						<sf:errors path="projectTeam" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="security" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.security" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:radiobuttons path="security" items="${booleanEnum}"/>
						<sf:errors path="security" />
					</div>
					<div class="security-upload">
						<mh:upload domainId="${interStaff.secProtocolBusiness}" />
					</div>
				</div>
				
				<div class="form-group" >
					<sf:label path="certificate" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.certificate" />
					</sf:label>
			
					<div class="col-xs-9 form-field checkoverflow" >
						<%-- <sf:hidden path = "certificates"/> --%>
						<sf:hidden path="certificate"/>
						<jsp:include page="/WEB-INF/views/melon/asset/staff/inter/editCert.jsp" flush="false"/>
					</div>
				</div>
				
				<div class="form-group" style="display :hidden" id="otherCertGroup">
					<sf:label path="otherCertificate" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.otherCertificate" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="otherCertificate" cssClass="form-control" maxlength="50" />
						<sf:errors path="otherCertificate" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="paperNum" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.paperNum" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="paperNum" cssClass="form-control" maxlength="50" />
						<sf:errors path="paperNum" />
					</div>
					<div class="paperNum-upload">
						<mh:upload domainId="${interStaff.paperBusiness}" />
					</div>	
				</div>
				
				<div class="form-group">
					<sf:label path="profession" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.profession" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:textarea path="profession" cssClass="form-control" maxlength="256" rows="5" />
						<sf:errors path="profession" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="education" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.education" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:textarea path="education" cssClass="form-control" maxlength="256" rows="5" />
						<sf:errors path="remark" />
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="remark" cssClass="col-xs-3 control-label">
						<fmt:message key="melon.interStaff.remark" />
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
	//处理组织机构选择
	function doOrganSearch() {
		var organSearch = $("#organSearch"),
			organText = $("#organMenu").find(".dropdown-text"),
			companyName = $("#organName"),
			companyId = $("#organId"),
			btnSearch = $("#btnSearch");
		//阻止默认的提交动作
		$("#organSelect").on("click", function(event) {
			event.stopPropagation();
		});
		//处理查询
		organSearch.on("click", function(event) {
			event.stopPropagation();
		});
		btnSearch.on("click", function(event) {
			organTree.search($.trim(organSearch.val()));
			event.stopPropagation();
		});
		//处理选择
		organTree.onSelect(function(data) {
			if(data.type == 1) {
				organText.text(data.text);
				companyName.val(data.text);
				companyId.val(data.id);
				//initDepart(data.id);
			}
		});
	}	
	
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

	$(document).ready(function() {
		
		var interStaffForm = $("#interStaff");
		//配置验证信息
		 interStaffForm.validate({
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
				chargeName : {
					required : true
				},
				fullJobs : {
					required : true
				},
				title : {
					required : true
				},
				techTitle : {
					required : true
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
				interStaffForm.validate().settings.ignore = ":disabled,:hidden";
				return interStaffForm.valid();
			 },
			 
			 onFinishing: function (event, currentIndex) {
				interStaffForm.validate().settings.ignore = ":disabled";
			 	return interStaffForm.valid();
			 },
			 //提交表单
			 onFinished: function (event, currentIndex)	{
				 var $ids = $("input[type='checkbox'][name='cert_select']:checked").map(function(){  
		            return $(this).val();  
		        }).get().join(",");
				 $("#certificate").val($ids);
				 $("#cert_checked").val($ids);
				interStaffForm.submit();
				$("#finish").prop("disabled", true);
				event.preventDefault();
			 },
			 onInit : function(event, currentIndex) {
					
			 },
			
			onCanceled : function(event) {
				window.location.href = '<c:url value="/staff/inter/home"/>';
			}
		});
		
		window.onload = function(){
		//组织机构选择
		doOrganSearch();
		//负责人选择
		doChargeSearch();
		
		//其他checkbox 输入框
		//初始化时判断其他是否选中
		var otherSkill = $("#otherSkillGroup"),
			otherCertificate = $("#otherCertGroup");
		//技术技能其他 
		if($('#techSkills22').is(':checked')){
			otherSkill.show();
		}else{
			var otherSkillInput = otherSkill.find("#otherSkill");
			otherSkillInput.val("");
			otherSkill.hide();
		}
		$('#techSkills22').on("click",function(){
			if($('#techSkills22').is(':checked')){
				otherSkill.show();
			}else{
				var otherSkillInput = otherSkill.find("#otherSkill");
				otherSkillInput.val("");
				otherSkill.hide();
			}
		});
		//培训其他验证
		if($('#certificates11').is(':checked')){
			otherCertificate.show();
		}else{
			var otherCertInput = otherCertificate.find("#otherCertificate");
			otherCertInput.val("");
			otherCertificate.hide();
		}
		$('#certificates11').on("click",function(){
			if($('#certificates11').is(':checked')){
				otherCertificate.show();
			}else{
				var otherCertInput = otherCertificate.find("#otherCertificate");
				otherCertInput.val("");
				otherCertificate.hide();
			}
		});
		
		//验证专职岗位     安全管理员和安全审计员只能选择一个
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
		});
		
		};
	});
</script>
<hh:date id="birthday" showSelect="true" />
<hh:date id="officeDate" showSelect="true" />
