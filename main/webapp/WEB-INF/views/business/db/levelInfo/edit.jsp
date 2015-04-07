<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<link rel="stylesheet" href="<mh:theme code='jquery.fileupload.css'/>" media="all" />

<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='jquery.fileupload.js'/>"></script>

<style>
	.td-label{
		text-align: center;
		font-weight: bold;
	}
	.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td{
		vertical-align: middle;
	}
	 .radio-inline{
	 	padding-top:0px !important;
	 }
	 .radio-inline, .checkbox-inline {
	 	vertical-align: bottom;
	 }
	 
	 .td-input{
	 	width: 100%;
	 	border:none;
	 }
	 .melon-buttons{
		text-align: center;
	}
	.page-header{
		margin-bottom : 0px;
		margin-top : -12px;
	}
	.ajax-file-upload{
		margin-bottom : -10px;
	}
</style>

<div class="page-header">
	<h4><fmt:message key="db.levelInfo.title"/></h4>
</div>

<div class="page-body">
	<sf:form modelAttribute="dbLevelInfo" role="form" method="POST">
		<table class="table form-horizontal table-bordered" width="80%">
			<tr>
				<td class="td-label" width="25%">
					<fmt:message key="db.sysInfo.sysName"/>
				</td>
				<td width="40%">
					<sf:input class="td-input" path="sysName"/>
				</td>
				<td class="td-label">
					<fmt:message key="db.sysInfo.sysCode"/>
				</td>
				<td>
					<sf:input class="td-input" path="sysCode"/>
				</td>
			</tr>
			<tr>
				<td rowspan="6"class="td-label" width="20%">
					<fmt:message key="db.levelInfo.proLevel"/>
				</td>
				<td colspan="2" class="td-label ">
					<fmt:message key="db.levelInfo.demageLevel"/>
				</td>
				<td width="20%" class="td-label">
					<fmt:message key="db.levelInfo.level"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" >
					<fmt:message key="db.levelInfo.demageLevel1"/>
				</td>
				<td>
					<sf:radiobutton path="proLevel" label=""/>
					<fmt:message key="db.levelInfo.level1"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" >
					<fmt:message key="db.levelInfo.demageLevel2"/>
				</td>
				<td>
					<sf:radiobutton path="proLevel" label=""/>
					<fmt:message key="db.levelInfo.level2"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" >
					<fmt:message key="db.levelInfo.demageLevel3"/>
				</td>
				<td>
					<sf:radiobutton path="proLevel" label=""/>
					<fmt:message key="db.levelInfo.level3"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" >
					<fmt:message key="db.levelInfo.demageLevel4"/>
				</td>
				<td>
					<sf:radiobutton path="proLevel" label=""/>
					<fmt:message key="db.levelInfo.level4"/>
				</td>
			</tr>
			<tr>
				<td colspan="2"  >
					<fmt:message key="db.levelInfo.demageLevel5"/>
				</td>
				<td>
					<sf:radiobutton path="proLevel" label=""/>
					<fmt:message key="db.levelInfo.level5"/>
				</td>
			</tr>
			<tr>
				<td rowspan="5"class="td-label">
					<fmt:message key="db.levelInfo.safeLevel"/>
				</td>
				<td colspan="2"   >
					<fmt:message key="db.levelInfo.demageLevel1"/>
				</td>
				<td>
					<sf:radiobutton path="safeLevel" label=""/>
					<fmt:message key="db.levelInfo.level1"/>
				</td>
			</tr>
			<tr>
				<td colspan="2"  >
					<fmt:message key="db.levelInfo.demageLevel2"/>
				</td>
				<td>
					<sf:radiobutton path="safeLevel" label=""/>
					<fmt:message key="db.levelInfo.level2"/>
				</td>
			</tr>
			<tr>
				<td colspan="2"  >
					<fmt:message key="db.levelInfo.demageLevel3"/>
				</td>
				<td>
					<sf:radiobutton path="safeLevel" label=""/>
					<fmt:message key="db.levelInfo.level3"/>
				</td>
			</tr>
			<tr>
				<td colspan="2"  >
					<fmt:message key="db.levelInfo.demageLevel4"/>
				</td>
				<td>
					<sf:radiobutton path="safeLevel" label=""/>
					<fmt:message key="db.levelInfo.level4"/>
				</td>
			</tr>
			<tr>
				<td colspan="2"   >
					<fmt:message key="db.levelInfo.demageLevel5"/>
				</td>
				<td>
					<sf:radiobutton path="safeLevel" label=""/>
					<fmt:message key="db.levelInfo.level5"/>
				</td>
			</tr>
			<tr>
				<td class="td-label">
					<fmt:message key="db.levelInfo.sysLevel"/>
				</td>
				<td colspan="3" >
					<mh:dictionary var="sysLevel" key="dbSysLevel"/>
					<sf:radiobuttons path="sysLevel" items="${sysLevel}"/>
				</td>
			</tr>
			<tr>
				<td class="td-label">
					<fmt:message key="db.levelInfo.gradeTime"/>
				</td>
				<td colspan="3" >
					<sf:input class="td-input" path="gradeTime"/>
					<hh:date id="gradeTime"/>
				</td>
			</tr>
			<tr>
				<td  class="td-label">
					<fmt:message key="db.levelInfo.isReview"/>
				</td>
				<td colspan="3" >
					<mh:dictionary var="review" key="dbIsReview"/>
					<sf:radiobuttons path="review" items="${review}"/>
				</td>
			</tr>
			<tr>
				<td class="td-label">
					<fmt:message key="db.levelInfo.isLeaderDept"/>
				</td>
				<td colspan="3" >
					<mh:dictionary var="leaderDept" key="whether"/>
					<sf:radiobuttons path="leaderDept" items="${leaderDept}"/>
					<fmt:message key="db.levelInfo.ifHave"/>
				</td>
			</tr>
			<tr>
				<td class="td-label"> 
					<fmt:message key="db.levelInfo.leaderDeptName"/>
				</td>
				<td colspan="3">
					<sf:input class="td-input" path="leaderDeptName"/>
				</td>
			</tr>
			<tr>
				<td class="td-label">
					<fmt:message key="db.levelInfo.isLeaderRev"/>
				</td>
				<td  colspan="3" >
					
					<mh:dictionary var="leaderRev" key="dbIsLeaderRev"/>
					<sf:radiobuttons path="leaderRev" items="${leaderRev}"/>
				</td>
			</tr>
			<tr>
				<td  class="td-label">
					<fmt:message key="db.levelInfo.levelReport"/>
				</td>
				<td colspan="3" >
					<mh:dictionary var="levelReport" key="whether"/>
					<sf:radiobuttons path="levelReport" items="${levelReport}"/>
					<label style="margin-bottom: 0px;margin-left:100px;">
						<sf:hidden path="attachId"  cssClass="form-control"/>
						<mh:upload domainId="${dbLevelInfo.attachId}" />
					</label>
				</td>
			</tr>
		</table>
		<div class="form-group melon-buttons">
			<button class="btn btn-primary"   id="btnSave" ><fmt:message key="button.save"/></button>
			<a class="btn btn-default"   href="<c:url value="/db/home"/>" ><fmt:message key="button.backList"/></a>
		</div>
	</sf:form>
</div>
