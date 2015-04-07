<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<script src="<mh:theme code="jquery.steps.js"/>"></script>

<sf:form modelAttribute="account" role="form" cssClass="form-horizontal">
	<sf:hidden path="id"/>
	<sf:hidden path="version"/>
	<sf:hidden path="createTime"/>
	<sf:hidden path="updatePassTime"/>
	
	<div id="account-wizard">
		<%-- 基本信息 --%>
		<mh:section id="basic">
		
			<jsp:attribute name="title">
				<fmt:message key="security.account.basic"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
			<div class="col-xs-8 col-xs-offset-2 form-singlon">

				   <%--所属单位--%>
					<div class="form-group">
						<sf:label path="companyId" cssClass="col-xs-3 control-label">
							<fmt:message key="hint.required" />
							<fmt:message key="security.account.companyName" />
						</sf:label>
						<div class="col-xs-9 form-field">
							<sf:hidden path="companyName"/>
							<sf:hidden path="companyId"/>
							
							<!-- 准备单位选择 -->
							<mh:dropdown id="organMenu">
								<jsp:attribute name="values">
									<fmt:message key="button.choose"/>
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
												rootId="1"
												url="organ/organization/find?includeDepart=false"
												sortName="order"
												scriptSelf="false">
										</mh:tree>
									</li>
								</jsp:attribute>
							</mh:dropdown>
							
							<sf:errors path="companyId" />
						</div>
					</div>
						   
				
					<div class="form-group">
						<sf:label path="departName" cssClass="col-xs-3 control-label">
						    <fmt:message key="hint.required"/>
							<fmt:message key="security.account.departName"/>
						</sf:label>
						
						<div class="col-xs-9 form-field">
						    <sf:hidden path="departName"/>
							<sf:select path="departId" cssClass="form-control" maxlength="100"/>
							<sf:errors path="departName"/>
						</div>
					</div>
			 		
				 	<div class="form-group">
						<sf:label path="name" cssClass="col-xs-3 control-label">
						    <fmt:message key="hint.required"/>
							<fmt:message key="security.account.name" />
						</sf:label>
						
						<div class="col-xs-9 form-field">
							<sf:input path="name" cssClass="form-control" maxlength="100"/>
							<sf:errors path="name"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="code" cssClass="col-xs-3 control-label">
							<fmt:message key="hint.required"/>
							<fmt:message key="security.account.code"/>
						</sf:label>
						
						<div class="col-xs-9 form-field">
							<sf:input path="code" cssClass="form-control" maxlength="50"/>
							<sf:errors path="code"/>
						</div>
					</div>
					
					<div class="form-group">
					  	<sf:label path="order" cssClass="col-xs-3 control-label">
							<fmt:message key="security.account.order"/>
						</sf:label>
						
						<div class="col-xs-9 form-field">
							<sf:input path="order" cssClass="form-control" />
							<sf:errors path="order"/>
							<label class="form-hint"><fmt:message key="hint.order"/></label> 
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="duty" cssClass="col-xs-3 control-label">
							<fmt:message key="hint.required"/>
							<fmt:message key="security.account.duty" />
						</sf:label>
						
						<div class="col-xs-9 form-field">
							<sf:input path="duty" cssClass="form-control" maxlength="100"/>
							<sf:errors path="duty"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="sex" cssClass="col-xs-3 control-label">
							<fmt:message key="security.account.sex" />
						</sf:label>
						
						<div class="col-xs-9 form-field">
						   <mh:dictionary var="asex" key="sexType"/>
							<sf:radiobuttons path="sex" items="${asex}"/>
							<sf:errors path="sex"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="birthday" cssClass="col-xs-3 control-label">
							<fmt:message key="security.account.birthday" />
						</sf:label>
						
						<div class="col-xs-9 form-field">
							<sf:input path="birthday" cssClass="form-control" maxlength="50"/>
							<sf:errors path="birthday"/>
						</div>
					</div>
					
				</div>	
			</jsp:attribute>
		</mh:section>
	
		<%-- 权限信息 --%>
		<mh:section id="profile">
		
			<jsp:attribute name="title">
				<fmt:message key="security.account.profile"/>
			</jsp:attribute>
			
			<jsp:attribute name="body">
				<div class="col-xs-8 col-xs-offset-2 form-singlon">
					<sf:hidden path="accountExpired"/>
					<sf:hidden path="accountLocked"/>
					<sf:hidden path="credentialsExpired"/>	
			 
					<div class="form-group">
						<sf:label path="username" cssClass="col-xs-3 control-label">
							<fmt:message key="hint.required"/>
							<fmt:message key="security.account.username"/>
						</sf:label>
						
						<div class="col-xs-9 form-field">
							<sf:input path="username" cssClass="form-control"  maxlength="20"/>
							<sf:errors path="username"/>
						</div>
					</div>
						
					<div class="form-group" id="passwordcontent">
						<sf:label path="password" cssClass="col-xs-3 control-label">
							<fmt:message key="hint.required"/>
							<fmt:message key="security.account.password" />
						</sf:label>
						
						<div class="col-xs-9 form-field">
						    <sf:password path="password" cssClass="form-control" maxlength="128"/>
							<sf:errors path="password"/>
						</div>
					</div>
						
					<div class="form-group" id="confirmPassword">
						<sf:label path="confirmPassword" cssClass="col-xs-3 control-label">
							<fmt:message key="hint.required"/>
							<fmt:message key="security.account.confirmPassword"/>
						</sf:label>
						
						<div class="col-xs-9 form-field">
							<sf:password path="confirmPassword" cssClass="form-control" />
							<sf:errors path="confirmPassword"/>
						</div>
					</div>
						
					<div class="form-group">
						<sf:label path="roles" cssClass="col-xs-3 control-label">
							<fmt:message key="security.account.roles"/>
						</sf:label>
						
						<div class="col-xs-9 form-field">
							<sf:checkboxes items="${roles}" itemValue="id" itemLabel="name" path="roles" />
							<sf:errors path="roles"/>
						</div>
					</div>
						
				</div>	
			</jsp:attribute>
		</mh:section>
		
		<%--其他信息 --%>
		<mh:section id="other">
			<jsp:attribute name="title">
				<%-- <fmt:message key="security.account.other"/> --%>
				<fmt:message key="button.finish"/>
			</jsp:attribute>
			<jsp:attribute name="body">
				<div class="col-xs-8 col-xs-offset-2 form-singlon">
				
					<div class="form-group">
						<sf:label path="email" cssClass="col-xs-3 control-label">
						    <fmt:message key="hint.required"/>
							<fmt:message key="security.account.email"/>
						</sf:label>
						
						<div class="col-xs-9 form-field">
							<sf:input path="email" cssClass="form-control" maxlength="50"/>
							<sf:errors path="email"/>
						</div>
					</div>
				
				    <div class="form-group">
						<sf:label path="tel" cssClass="col-xs-3 control-label">
							<fmt:message key="security.account.tel"/>
						</sf:label>
						
						<div class="col-xs-9 form-field">
							<sf:input path="tel" cssClass="form-control" maxlength="50"/>
							<sf:errors path="tel"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="mobile" cssClass="col-xs-3 control-label">
							<fmt:message key="security.account.mobile"/>
						</sf:label>
						
						<div class="col-xs-9 form-field">
							<sf:input path="mobile" cssClass="form-control" maxlength="50"/>
							<sf:errors path="mobile"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="remarks" cssClass="col-xs-3 control-label">
							<fmt:message key="security.account.remarks"/>
						</sf:label>
						
						<div class="col-xs-9 form-field">
							<sf:textarea path="remarks" cssClass="form-control"  rows="5" maxlength="1000"/>
							<sf:errors path="remarks"/>
						</div>
					</div>
					
					<div class="form-group">
						<sf:label path="enabled" cssClass="col-xs-3 control-label">
							<fmt:message key="security.account.enabledNow"/>
						</sf:label>
						
						<div class="col-xs-9 form-field">
							<mh:dictionary var="boolenums" key="bool"/>
							<sf:radiobuttons path="enabled" items="${boolenums}"/>
							<sf:errors path="enabled"/>
						</div>
					</div>
				
				</div>
			</jsp:attribute>
		</mh:section>

	</div>
</sf:form>
<script type="text/javascript">
	$(document).ready(function() {
		var accountForm = $("#account");
		//配置验证信息
		 accountForm.validate({
			rules : {
				//基本信息
				companyName : {
					required : true
				},
				departId :{
					required : true
				}, 
				name : {
					required : true,
					stringChar : true
				},
				duty : {
					required : true,
					stringChar : true
				},
				order : {
					integer : true,
					min : 0,
					max : 9999
				},
				code : {
					required : true,
					code : true
				},
				//权限信息
				username : {
					required : true,
					stringChar : true,
					remote : {
						url : '<c:url value="/security/account/checkUnique"/>',
						type : 'POST',
						data : {
							username : function() {
								return $("#username").val();
							}
						}
					}
				},
				password : {
					required : true,
					password : true
				},
				confirmPassword :{
					equalTo : "#password"
				},
				roles : {
					required : true
				},
				//其他
				tel : {
					stringChar : true
				},
				mobile : {
					mobile : true
				},
				email : {
					required : true,
					email : true
				},
				remarks : {
					maxlength : 1000
				}
			}
		}); 
		
		//配置向导
		$("#account-wizard").steps({
			
			 headerTag: "h4",
			 bodyTag: "section",
			 transitionEffect: "slideLeft",
			 
			 enableCancelButton : true,

			 //逐步验证
			 onStepChanging: function (event, currentIndex, newIndex) {
				accountForm.validate().settings.ignore = ":disabled,:hidden";
				return accountForm.valid();
			 },
			 
			 onFinishing: function (event, currentIndex) {
				accountForm.validate().settings.ignore = ":disabled";
			 	return accountForm.valid();
			 },
			 //提交表单
			 onFinished: function (event, currentIndex)	{
				accountForm.submit();
				$("#finish").prop("disabled", true);
				event.preventDefault();
			 },
			 onInit : function(event, currentIndex) {
					
			 },
			
			onCanceled : function(event) {
				window.location.href = '<c:url value="/security/account/list"/>';
			}
		});
		
		//初始化部门列表
		function initDepart(organId) {
			var url = '<c:url value="/organ/organization/findOrgans?type=2&parentId="/>' + organId,
				departEl = $("#departId"),
				value = departEl.val();
			$.getJSON(url, function(datas) {
				departEl.empty();
				$.each(datas, function(index, item) {
					var option = $("<option></option>").text(item.text).val(item.id).appendTo(departEl);
					if(value == item.id) {
						option.prop("selected", true); //设置选中值
					}
					$("#departName").val(item.text); //设限选中部门名称
				});
			});
		}
		
		/*
		 * 所有文档加载完成后注册
		 */
		window.onload = function() {
			//处理单位选择
			doOrganSearch();
		};
		//处理组织机构选择
		function doOrganSearch() {
			var organSearch = $("#organSearch"),
				organText = $("#organMenu").find(".dropdown-text"),
				companyName = $("#companyName"),
				companyId = $("#companyId"),
				//organPath = $("#organPath"),
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
					initDepart(data.id);
					//organPath.val(data.path);
				}
			});
		}	
	});
</script>
<hh:date id="birthday" maxDate="${newDate }"/>