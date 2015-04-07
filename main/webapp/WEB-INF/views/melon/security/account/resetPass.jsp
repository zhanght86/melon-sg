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
		
			<div class="form-group">
				<sf:label path="password" cssClass="col-xs-3 control-label">
					<fmt:message key="hint.required"/>
					<fmt:message key="security.account.newPassword"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<sf:password path="password" cssClass="form-control" maxlength="20"/>
					<sf:errors path="password"/>
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="confirmPassword" cssClass="col-xs-3 control-label">
					<fmt:message key="security.account.pwdStrength" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<div style="margin-top : 5px;" id="pass-strength">
					  <div class="progress-bar progress-bar-danger" style="width: 20%;">
					  </div>
					  <div class="progress-bar progress-bar-warning" style="width: 20%;margin-left:2px;">
					  </div>
					  <div class="progress-bar progress-bar-success" style="width: 20%;margin-left:2px;">
					  </div>
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="confirmPassword" cssClass="col-xs-3 control-label">
					<fmt:message key="hint.required"/>
					<fmt:message key="security.account.confirmPassword"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<sf:password path="confirmPassword" cssClass="form-control" maxlength="20"/>
					<sf:errors path="confirmPassword"/>
				</div>
			</div>
			<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary" type="button" id="btnReset"><fmt:message key="button.sure"/></mh:button>
				<mh:button class="btn btn-default" href="security/account/list"><fmt:message key="button.backList"/></mh:button>
			</div>
		</div>
	</sf:form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var patterns = "${pattern}",
			passwordForm = $("#passwordForm"),
			strengthBar = $("#pass-strength");
		var strengthLow = strengthBar.find(".progress-bar:eq(0)"),
			strengthMiddle = strengthBar.find(".progress-bar:eq(1)"),
			strengthHigh = strengthBar.find(".progress-bar:eq(2)");
		
		//验证密码强度
		$("#password").passField({
			acceptRate : 0.5,//至少到达到50%的强度
			checkMode : PassField.CheckModes.MODERATE,
			allowEmpty : false,
			showToggle : false,
			errorWrapClassName : "control-error",
			showWarn : false,
			showTip : false,
			pattern : patterns,
			locale : 'zh',
			length: { min: 6, max: 16 },
			validationCallback : function(el, validateResult) {
				var strength = validateResult.strength || 0;
				
				if(strength == 0){
					strengthLow.text("").css("background-color","##efefef");
					strengthMiddle.text("").css("background-color","##efefef");
					strengthHigh.text("").css("background-color","##efefef");
					$("#password").parent().find("label").remove();
					$("#password").parent().append('<label id="password-error" class="control-error" for="password">'+validateResult.messages[0]+'</label>');
				}
				if(strength > 0) {
					strengthLow.text("<fmt:message key='security.account.strengthLow'/>").css("background-color","#f55549");
					strengthMiddle.text("").css("background-color","#ccc");
					strengthHigh.text("").css("background-color","#ccc");
				}
				if(strength > 0.4) {
					strengthMiddle.text("<fmt:message key='security.account.strengthMiddle'/>").css("background-color","#f0ad4e");
					strengthLow.text("").css("background-color","#ccc");
					strengthHigh.text("").css("background-color","#ccc");
				}
				if(strength > 0.75) {
					strengthHigh.text("<fmt:message key='security.account.strengthHigh'/>").css("background-color","#5cb85c");
					strengthLow.text("").css("background-color","#ccc");
					strengthMiddle.text("").css("background-color","#ccc");
				}
				return validateResult;
			}
		});
		//配置验证信息
		passwordForm.validate({
			rules : {
				password : {
					required : true,
					password : false
				},
				confirmPassword :{
					password : false,
					equalTo : "#password"
				}
			}
		});
		
		passwordForm.find("#btnReset").click(function(event) {
			if(passwordForm.valid()) {
				var url = '<c:url value="/security/account/checkPass"/>',
					data = {
						accountId : function() {
							return  $("#id").val();
						},
						password : function() {
							return  $.trim($("#password").val());
						}
					};
				$.post(url, data, function(response) {
					if(!response) {
						passwordForm.submit();
					} else {
						$("#password").parent().append('<label id="password-error" class="control-error" for="password"><fmt:message key="security.account.passActivate.hint" /></label>');
					}
				});
			}
			
			event.preventDefault();
		});
	});
</script>