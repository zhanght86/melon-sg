<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<script src="<mh:theme code="jquery.vaildate.js"/>"></script>
<div class="page-header">
  <h4>找回密码</h4>
</div>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="account" role="form" cssClass="form-horizontal">
		<sf:hidden path="id"/>
		
			<div class="form-group">
				<sf:label path="username" cssClass="col-xs-3 control-label">
					<fmt:message key="hint.required"/>
					<fmt:message key="security.account.username"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
				    <sf:input path="username" cssClass="form-control"  maxlength="20"/>
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="name" cssClass="col-xs-3 control-label">
					<fmt:message key="security.account.getPasswordAnswer"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<fmt:message key="security.account.getPassword.hint"/>
			    </div>
		     </div>
		     
		     <div class="form-group">
				<sf:label path="email" cssClass="col-xs-3 control-label">
					<fmt:message key="hint.required"/>
					<fmt:message key="security.account.getPasswordResult"/>
				</sf:label>
				
				<div class="col-xs-9 form-field">
					<sf:input path="email" cssClass="form-control" maxlength="20"/>
					<sf:errors path="email"/>
				</div>
			</div>
			
			<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
			    <mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.sure"/></mh:button>
			    <mh:button class="btn btn-default" href="/login"><fmt:message key="button.login"/></mh:button>
			</div>
		</div>
	</sf:form>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var accountForm = $("#account");
		//验证表单
		accountForm.validate({
			rules : {
				username : {
					required : true,
					remote : {
						url : '<c:url value="/security/account/checkExist"/>',
						type : 'POST',
						data : {
							username : function() {
								return $("#username").val();
							}
						}
					}
				},
				email : {
					required : true,
					email : true
			   }
			},
		message : {
			username : "用户不存在请重新输入" 	
		}
		});
		
		//处理保存按钮
		$("#btnSave").on("click", function(event) {
			if(accountForm.valid()){
			//提交表单
				accountForm.submit();
			}
			//阻止默认的提交动作
			event.preventDefault();
		});
	});
</script>