<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<style type="text/css">
 .accountPhoto {
    background-image : url('<c:url value="/statics/themes/default/melon/images/portrait.png"/>');
    margin:0 auto;
    margin-top:60px;
 }
  .btn-upload {
    margin-top:20px;
    margin-left:80px;
 }
 </style>
<sf:form modelAttribute="account" role="form" cssClass="form-horizontal chart-form-show">
	<sf:hidden path="id"/>
	
	<div class="row">
	
		<div class="col-xs-4">
			<!-- 显示图片-->
			<div class="accountPhoto" style="height:200px;width:220px"></div>
     		<a href="#" class="btn btn-primary btn-upload" >
				<fmt:message key="button.uploadPortrait"/>
			</a>
		</div>
		
		<div class="col-xs-8" style="padding-right:30px;">
			<div class="form-group">
				<div class="form-button-panel">
					<div class="col-xs-12 form-buttons">
						<mh:button class="btn btn-primary btn-sm" href="security/account/update/${account.id}">
							<fmt:message key="button.update"/>
						</mh:button>
						<mh:button class="btn btn-default btn-sm" href="security/account/list">
							<fmt:message key="button.back"/>
						</mh:button>
					</div>
				</div>
			</div>
		
			<%-- 基本信息  --%>
			<div class="form-group">
				<sf:label path="companyName" cssClass="col-xs-3 control-label">
					<fmt:message key="security.account.companyName"/>
				</sf:label>
					
				<div class="col-xs-9 form-field">
					<mh:value path="companyName"/>
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="departName" cssClass="col-xs-3 control-label">
					<fmt:message key="security.account.departName"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<mh:value path="departName"/>
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="name" cssClass="col-xs-3 control-label">
					<fmt:message key="security.account.name"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<mh:value path="name"/>
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="code" cssClass="col-xs-3 control-label">
					<fmt:message key="security.account.code"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<mh:value path="code"/>
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="duty" cssClass="col-xs-3 control-label">
					<fmt:message key="security.account.duty"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<mh:value path="duty"/>
				</div>
			</div>
			
			
			<div class="form-group">
				<sf:label path="sex" cssClass="col-xs-3 control-label">
					<fmt:message key="security.account.sex"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<mh:dictionary var="boolsex" key="sexType"/>
					<mh:value path="sex" items="${boolsex}"/>
				</div>
			</div>
			
			<c:if test="${!empty account.birthday}">
			<div class="form-group">
				<sf:label path="birthday" cssClass="col-xs-3 control-label">
					<fmt:message key="security.account.birthday"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<mh:value path="birthday"/>
				</div>
			</div>
			</c:if>
			
			<div class="form-group">
				<sf:label path="email" cssClass="col-xs-3 control-label">
					<fmt:message key="security.account.email"/>
				</sf:label>
			
				<div class="col-xs-9 form-field">
					<mh:value path="email"/>
				</div>
			</div>
		
		    <c:if test="${!empty account.tel}">
			<div class="form-group">
				<sf:label path="tel" cssClass="col-xs-3 control-label">
					<fmt:message key="security.account.tel"/>
				</sf:label>
			
				<div class="col-xs-9 form-field">
					<mh:value path="tel"/>
				</div>
			</div>
			</c:if>
		
		    <c:if test="${!empty account.mobile}">
			<div class="form-group">
				<sf:label path="mobile" cssClass="col-xs-3 control-label">
					<fmt:message key="security.account.mobile"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<mh:value path="mobile"/>
				</div>
			</div>
			</c:if>
		</div>
	</div>
	
	<!-- 关联信息 -->
	<div class="row" style="margin-top:40px;margin-left: 0px; margin-right: 0px;">
		<ul class="nav nav-tabs" role="tablist">
			<li class="active"><a href="#authority" role="tab" data-toggle="tab">权限信息</a></li>
			<li><a href="#TRACK_ASSETS" id="asset" role="tab" data-toggle="tab">负责资产</a></li>
			<li><a href="#attentions" role="tab" data-toggle="tab">他的关注</a></li>
			<li><a href="#notes" role="tab" data-toggle="tab">他的注解</a></li>
			<li><a href="#TRACK_CONTAINER" id="track" role="tab" data-toggle="tab">他的轨迹</a></li>
			
		</ul>
	
		<div class="tab-content col-xs-12">
			<!-- 权限信息 -->
			<div class="tab-pane active" id="authority" role="form" cssClass="form-horizontal form-show">
				<div class="form-group">
					<sf:label path="username" cssClass="col-xs-3 control-label">
						<fmt:message key="security.account.username"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="username"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="roles" cssClass="col-xs-3 control-label">
						<fmt:message key="security.account.roles"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<div class="col-xs-9 form-field">
							<ol class="form-control-items">
								<c:forEach items="${account.roles}" var="role">
									<li class="form-control-item">
										<a href="<c:url value='/security/role/show/${role.id}'/>">${role.name}</a>
									</li>
								</c:forEach>
							</ol>
						</div>
					</div>
				</div>
			
				<div class="form-group">
					<sf:label path="enabled" cssClass="col-xs-3 control-label">
						<fmt:message key="security.account.enabled"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:dictionary var="boolenums" key="bool"/>
						<mh:value path="enabled" items="${boolenums}"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="accountExpired" cssClass="col-xs-3 control-label">
						<fmt:message key="security.account.accountExpired"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:dictionary var="boolenums" key="bool"/>
						<mh:value path="accountExpired" items="${boolenums}"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="accountLocked" cssClass="col-xs-3 control-label">
						<fmt:message key="security.account.accountLocked"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:dictionary var="boolenums" key="bool"/>
						<mh:value path="accountLocked" items="${boolenums}"/>
					</div>
				</div>
				
				<div class="form-group">
					<sf:label path="credentialsExpired" cssClass="col-xs-3 control-label">
						<fmt:message key="security.account.credentialsExpired"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:dictionary var="boolenums" key="bool"/>
						<mh:value path="credentialsExpired" items="${boolenums}"/>
					</div>
				</div>
				
				<c:if test="${!empty account.remarks}">
				<div class="form-group">
					<sf:label path="remarks" cssClass="col-xs-3 control-label">
						<fmt:message key="security.account.remarks"/>
					</sf:label>
					
					<div class="col-xs-9 form-field">
						<mh:value path="remarks" cssStyle="font-size : 14px;"/>
					</div>
				</div>
				</c:if>
			</div>
			
			<!-- 负责资产 -->
			<div class="tab-pane" id="TRACK_ASSETS">
				
			</div>
			
			<!-- 他的关注 -->
			<div class="tab-pane" id="attentions">
				他还没有关注信息
			</div>
			
			<!-- 他的注解 -->
			<div class="tab-pane" id="notes">
				他还没有注解
			</div>
			<!-- 他的轨迹 -->
			<div class="tab-pane" id="TRACK_CONTAINER">
			</div>
		</div>
	</div>
     
</sf:form>
<script type="text/javascript">
$(document).ready(function(){
		var trackUrl = "<c:url value='/system/logger/trackUser/'/>",
			assetUrl ="<c:url value='/asset/device/listByPerson/'/>",
		 	userId = "${id}",		   
			trackForm = $("#TRACK_CONTAINER"),
			assetForm = $("#TRACK_ASSETS");
		
		$("#track").on('click',function(event){
			trackForm.load(trackUrl + userId); //加载修改页面
  		});
		
		$("#asset").on('click',function(event){
			assetForm.load( assetUrl + userId); //加载修改页面
  		});
});
</script>

