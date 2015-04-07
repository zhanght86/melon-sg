<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
	
<style type="text/css">
	.form-control {
		background-color: #FFFFFF;
	  	border:0px;
	    color: #555555;
	    font-size: 14px;
	    height: 24px;
	    padding: 6px 12px;
	    transition: border-color 0.15s ease-in-out 0s, box-shadow 0.15s ease-in-out 0s;
	    vertical-align: middle;
	    width: 100%;
	    box-shadow:none;
	}
	.form-control:focus{
		box-shadow:none;
		cursor:text;
	}
	.melon-buttons{
		text-align: center;
	}
	.td-checkbox label{
		margin-left:10px;
		font-size:12px;
		width:20%;
	}
	.td-checkbox label input{
		margin-top:1px;
	}
	.page-header{
		margin-bottom : 0px;
		margin-top : -12px;
	}
</style>

<div class="page-header">
  <h4><fmt:message key="db.basicinfo.basicinfo"/></h4>
</div>
<sf:form modelAttribute="basicInfo" role="form" method="POST">
		<table class="table form-horizontal table-bordered">
			<tr>
				<td align="center" width="15%">
					<b><fmt:message key="db.basicinfo.organname"/></b>
				</td>
				<td colspan="4">
					<sf:input path="organName" cssClass="form-control"/>
				</td>
			</tr>
			<tr>
				<td align="center"><b><fmt:message key="db.basicinfo.organAddr"/></b></td>
				<td colspan="4">
					<sf:input path="organAddr" cssClass="form-control" maxlength="50" />
				</td>
			</tr>
			<tr>
				<td align="center"><b><fmt:message key="db.basicinfo.postCode"/></b></td>
				<td colspan="2">
					<sf:input cssClass="form-control" path="postCode" />
				</td>
				<td align="center" width="15%"><b><fmt:message key="db.basicinfo.organCode"/></b></td>
				<td>
					<sf:input cssClass="form-control" path="organCode" />
				</td>
			</tr>
			<tr>
				<td align="center" rowspan="2">
					<b><fmt:message key="db.basicinfo.chNames"/></b>
				</td>
				<td align="center" width="10%">
					<b><fmt:message key="db.basicinfo.chName"/></b>
				</td>
				<td>
					<sf:input path="chName" cssClass="form-control"/>
				</td>
				<td align="center">
					<b><fmt:message key="db.basicinfo.chDuty"/></b>
				</td>
				<td>
					<sf:input path="chDuty" cssClass="form-control" />
				</td>
			</tr>
			<tr>
				<td align="center">
					<b><fmt:message key="db.basicinfo.chTel"/></b>
				</td>
				<td>
					<sf:input path="chTel" cssClass="form-control" />
				</td>
				<td align="center">
					<b><fmt:message key="db.basicinfo.chMail"/></b>
				</td>
				<td>
					<sf:input path="chMail" cssClass="form-control"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<b><fmt:message key="db.basicinfo.departName"/></b>
				</td>
				<td colspan="4">
					<sf:input path="departName" cssClass="form-control"/>
				</td>
			</tr>
			<tr>
				<td align="center" rowspan="3">
					<b><fmt:message key="db.basicinfo.department"/></b>
				</td>
				<td align="center">
					<b><fmt:message key="db.basicinfo.deName"/></b>
				</td>
				<td>
					<sf:input path="deName" cssClass="form-control"/>
				</td>
				<td align="center">
					<b><fmt:message key="db.basicinfo.deDuty"/></b>
				</td>
				<td>
					<sf:input path="deDuty"  cssClass="form-control"/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<b><fmt:message key="db.basicinfo.deTel"/></b>
				</td>
				<td>
					<sf:input path="deTel" cssClass="form-control"/>
				</td>
				<td align="center" rowspan="2">
					<b><fmt:message key="db.basicinfo.deMail"/></b>
				</td>
				<td rowspan="2">
					<sf:input path="deMail" cssClass="form-control" />
				</td>
			</tr>
			<tr>
				<td align="center">
					<b><fmt:message key="db.basicinfo.dePhone"/></b>
				</td>
				<td>
					<sf:input path="dePhone" cssClass="form-control"/>
				</td>
			</tr>
			 <tr>
				<td align="center">
					<b><fmt:message key="db.basicinfo.belong"/></b>
				</td>
				<td colspan="4" class="td-checkbox">
				   <mh:dictionary var="belongEnum" key="organBelong"/>
					<sf:radiobuttons path="belong" items="${belongEnum }"/>
					<sf:errors path="belong"></sf:errors>
				</td>
			</tr>
			<tr>
				<td align="center">
					<b><fmt:message key="db.basicinfo.organType"/></b>
				</td>
				<td colspan="4" class="td-checkbox">
					<mh:dictionary var="orgType" key="orgType"/>
					<sf:radiobuttons path="organType" items="${orgType }"/>
					<sf:errors path="organType"></sf:errors>
				</td>
			</tr>
			<tr>
				<td align="center">
					<b><fmt:message key="db.basicinfo.tradeType"/></b>
				</td>
				<td colspan="4" class="td-checkbox">
					<mh:dictionary var="tradeType" key="tradeType"/>
					<sf:radiobuttons path="tradeType" items="${tradeType}"/>
					<sf:errors path="tradeType"></sf:errors>
				</td>
			</tr>
		</table>
		
		<div class="form-group melon-buttons">
			<button class="btn btn-primary"   id="btnSave" ><fmt:message key="button.save"/></button>
			<a class="btn btn-default"   href="<c:url value="/db/home"/>" ><fmt:message key="button.backList"/></a>
		</div>

</sf:form> 
<script type="text/javascript">
		$(document).ready(function(){
			//初始化Form表单,格式化表单，必不可少
			var organ = $('form');
			$("#btnSave").on("click", function(event) {
					var valid = form.valid(),
			       		infoId ='${infoId}',
			       		url = "";
			        if(valid) {
			        	if(infoId!=""){
			        		url = '<c:url value="/db/basicInfo/edit/"/>'+infoId;
			        	}else{
			        		url = '<c:url value="/db/basicInfo/create"/>';
			        	}
			        }
		        	form.attr("action",url);
		        	form.submit();
			        //阻止默认的提交动作
			        event.preventDefault(); 
			});
		});	

</script>
