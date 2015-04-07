<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<!doctype html>
<html lang="zh">
	<head>
		<jsp:include page="/META-INF/tiles-config/templates/include/desktop-resources.jsp"/>
		<script src="<mh:theme code='jquery.passfield.js'/>"></script>
		<style type="text/css">
			#pass-strength .progress-bar {
				height:20px;
				background-color:#efefef;
				border:1px solid #e5e5e5;
				border-radius:2px;
			}
			
		</style>	
	</head>
	<body data-spy="scroll" data-target="#melon-nav-panel" data-offset="153">
		<div class="container melon-body clearfix" id="melon-body" style="margin-top:100px">
			<script type="text/javascript" src="<mh:theme code='jquery.vaildate.js'/>"></script>
			<div class="page-edit">
				<div class="page-edit-header" style="font-size:20px;margin-bottom: 30px">
				${title}
				</div>
				<div class="page-edit-body">
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
									<mh:button class="btn btn-primary" type="button" id="btnActivate"><fmt:message key="button.activate"/></mh:button>
									<mh:button class="btn btn-default" type="link"  href="j_security_logout"><fmt:message key="button.cancleLogin"/></mh:button>
								</div>
							</div>
						</sf:form>
					</div>
				</div>
			</div>
		</div>
		<c:if test="${!empty ACTION_HINT}">
			<script type="text/javascript">
				$(document).ready(function() {
					Logger.out('${ACTION_HINT.message}', '${ACTION_HINT.level}');
				});	
			</script>
		</c:if>
		<script type="text/javascript">
			$(document).ready(function() {
				
				var patterns = "${pattern}",
					passwordForm = $("#passwordForm"),
					strengthBar = $("#pass-strength"),
					strengthLow = strengthBar.find(".progress-bar:eq(0)"),
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
					length: { min: 8, max: 16 },
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
				
				passwordForm.find("#btnActivate").click(function(event) {
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
	</body>
</html>
