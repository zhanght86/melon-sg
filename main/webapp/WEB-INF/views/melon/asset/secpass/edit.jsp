<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<div class="col-xs-8 col-xs-offset-2 form-singlon">
	
	<sf:form modelAttribute="secPass" role="form" cssClass="form-horizontal">
	
		<sf:hidden path="id" />
		<sf:hidden path="state" />
		<sf:hidden path="user.userId" />
		<sf:hidden path="user.username" />
		<sf:hidden path="user.organId" />
		<sf:hidden path="user.organName" />
		<sf:hidden path="createDate" />
		<sf:hidden path="updateDate" />
		<sf:hidden path="mainId" />
	  
		<div class="form-group">
			<sf:label path="assetName" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="asset.secPass.assetName" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<sf:input path="assetName" cssClass="form-control" maxlength="100" />
				<sf:errors path="assetName" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="ip" cssClass="col-xs-3 control-label">
				<fmt:message key="asset.secPass.ip" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<sf:input path="ip" cssClass="form-control" maxlength="100" />
				<sf:errors path="ip" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="account" cssClass="col-xs-3 control-label">
				<fmt:message key="asset.secPass.account" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<sf:input path="account" cssClass="form-control" maxlength="50" />
				<sf:errors path="account" />
			</div>
		</div>
	
		<c:choose>
			<c:when test="${!empty adPass}">
				<div class="form-group">
					<sf:label path="desPassWord" cssClass="col-xs-3 control-label">
						<fmt:message key="asset.secPass.passWord" />
					</sf:label>
			
					<div class="col-xs-7 form-field">
						<sf:input path="desPassWord" cssClass="form-control" maxlength="20" />
						<sf:errors path="desPassWord" />
					</div>
					
					<div class="col-xs-2 form-field" style="padding :4px 0 0 0; ">
						<a href="#" class="glyphicon glyphicon-arrow-left" id="fill"></a>
						<span style="width :65px;display: inline-block;font-size :12px; text-align: center" id="adPass"></span>
						<a href="#" class="glyphicon glyphicon-refresh" id="refresh"></a>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="form-group">
					<sf:label path="desPassWord" cssClass="col-xs-3 control-label">
						<fmt:message key="asset.secPass.passWord" />
					</sf:label>
			
					<div class="col-xs-9 form-field">
						<sf:input path="desPassWord" cssClass="form-control" maxlength="20" />
						<sf:errors path="desPassWord" />
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		
	
		<div class="form-group">
			<sf:label path="alert" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="asset.secPass.alert" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<sf:input path="alert" cssClass="form-control" maxlength="50" />
				<sf:errors path="alert" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="remark" cssClass="col-xs-3 control-label">
				<fmt:message key="asset.secPass.remark" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<sf:textarea path="remark" cssClass="form-control" rows="5" maxlength="256" />
				<sf:errors path="remark" />
			</div>
		</div>
	
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary" id="btnSave">
					<fmt:message key="button.save" />
				</mh:button>
				<mh:button class="btn btn-default" id="btnBack" href="asset/secpass/list?inline=1">
					<fmt:message key="button.back" />
				</mh:button>
			</div>
		</div>
	
	</sf:form>
</div>
<script type="text/javascript">
	//随机建议密码生成
	function randPassword() { 
		var text=['abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ','1234567890']; 
		var rand = function(min, max){return Math.floor(Math.max(min, Math.random() * (max+1)));} 
		var len = 8; // 长度为8-10
		var pw = ''; 
		pw += text[0].charAt(rand(0, text[0].length));
		for(var i=0; i<len-1; ++i) 
		{ 
			var strpos = rand(0, 2); 
			pw += text[strpos].charAt(rand(0, text[strpos].length)); 
		} 
		return pw; 
	} 

	$(document).ready(function() {
		var secPass = $("#secPass"),
			adPass = randPassword();
		$("#adPass").text(adPass);
		//添加验证规则
		secPass.validate({
			rules : {
				assetName : {
					required : true
				},
				ip : {
					ipadress : true
				},
				alert : {
					required : true,
					integer : true
				},
				remark : {
					maxlength : 256
				}
			},
			
		});
		//注册保存按钮
		//注册按钮事件,保存成功则添加节点到树中
		secPass.find("#btnSave").on("click", function(event) {
			if(secPass.valid()) {
				secPass.submit();
			}
			event.preventDefault();
		});
		
		$("#fill").click(function() {
			$("#desPassWord").val($("#adPass").text());
			return false;
		});
		
		$("#refresh").click(function() {
			adPass = randPassword();
			$("#adPass").text(adPass);
			return false;
		})
	});
</script>

