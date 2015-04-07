<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<script src="<mh:theme code='jquery.passfield.js'/>"></script>
<style type="text/css">
	#pass-strength .progress-bar {
		height:20px;
		background-color:#efefef;
		border:1px solid #e5e5e5;
		border-radius:2px;
	}
	
</style>

<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="passwordForm" role="form" cssClass="form-horizontal">
		<sf:hidden path="id"/>
		
		<div class="form-group" id="oriPassGroup">
			<label class="col-xs-3 control-label" for="oriPassword">
				<span class="form-required">*</span>
				<fmt:message key="security.account.oriPassword"/>
			</label>
			
			<div class="col-xs-8 form-field">
				<input type="text" maxlength="20" class="form-control" name="oriPassword" id="oriPassword">
				<sf:errors path="oriPassword"/>
			</div>
		</div>
		
		<div class="form-group" id=password>
			<sf:label path="password" cssClass="col-xs-3 control-label">
				<span class="form-required">*</span>
				<fmt:message key="security.account.secPassWord" />
			</sf:label>
			
			<div class="col-xs-8 form-field">
			    <sf:input path="password" cssClass="form-control" maxlength="128"/>
				<sf:errors path="password"/>
			</div>
		</div>
			
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary" type="button" id="btnSave"><fmt:message key="button.sure"/></mh:button>
				<mh:button class="btn btn-default" href="asset/secpass/list?inline=1"><fmt:message key="button.backList"/></mh:button>
			</div>
		</div>
	</sf:form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var passwordForm = $("#passwordForm"),
			oriCheck = false,
			oriPassword =$("#oriPassword");
		
		oriPassword.on("blur", function() {
			var data = {
				password :  $("#oriPassword").val(),
				id : $("#id").val()
			};
			$.post('<c:url value="/security/account/checkOriPass"/>', data, function(response) {
				oriCheck = response;
				if($.trim(oriPassword.val()) != "") {
					if(response) {
						$("#checkfal").remove();
						if($("#checktr").length==0) {
							$("#oriPassGroup").append('<span class="glyphicon glyphicon-ok col-xs-1" style="color: #76cc7c; padding:8px 15px 0 0" id="checktr"></span>');
						}
					} else {
						$("#checktr").remove();
						if($("#checkfal").length==0) {
							$("#oriPassGroup").append('<span class="glyphicon glyphicon-remove col-xs-1" style="color:#f55549; padding:8px 15px 0 0" id="checkfal"></span>');
						}
					}
				} else {
					$("#checkfal").remove();
					$("#checktr").remove();
				}
				
			});
		});
		//配置验证信息
		passwordForm.validate({
			rules : {
				oriPassword : {
					required : true
				},
				password : {
					required : true
				}
			}
		});
		
		$("#btnSave").click(function() {
			if(passwordForm.valid() && oriCheck) {
				passwordForm.submit();
			}
		});
		
		
	});
</script>