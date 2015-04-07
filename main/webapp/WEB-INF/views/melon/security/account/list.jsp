<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>

<div class="page-query-heading">
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<mh:query>
			<jsp:attribute name="buttons">
				<mh:button href="security/account/create" class="btn btn-default"><fmt:message key="button.create"/></mh:button>
				<mh:button href="security/account/resetPass" class="btn btn-default should-selected"><fmt:message key="button.resetPass"/></mh:button>
				<mh:button href="security/account/export" class="btn btn-default"><fmt:message key="button.export"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<%--快速查询摆放区 --%>
				<sf:label path="username" cssClass="col-xs-4 control-label"><fmt:message key="security.account.username"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="username" cssClass="form-control"/>
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
				<div class="form-group">
					<div class="col-xs-5 form-field-group">
						<sf:label path="companyName" cssClass="col-xs-4 control-label"><fmt:message key="security.account.companyName"/></sf:label>
						<div class="col-xs-8 form-field">
						    <sf:hidden path="companyName"/>
							<sf:input path="companyId" cssClass="form-control" id="company"/>
        					<hh:select id="company" onceUrl="organ/organization/findOrgans?parentId=0&type=1"/>
        				</div>
					</div>
					<div class="col-xs-5 form-field-group">
						<sf:label path="departName" cssClass="col-xs-4 control-label"><fmt:message key="security.account.departName"/></sf:label>
						<div class="col-xs-8 form-field">
						    <sf:hidden path="departName"/>
							<sf:select path="departId" cssClass="form-control depart-select" />
						</div>
					</div>
				</div>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	
	<%-- <sec:authorize url="security/account/remove/1">
		<c:set value="security/account/remove" var="removeUrl"/>
	</sec:authorize> --%>

	<mh:grid id="account" 
			 queryUrl = "security/account/list"
			 showUrl = "security/account/show"
			 deleteUrl = "security/account/remove"
			 updateUrl = "security/account/update"
			 hasPages= "true" 
			 multiSelect = "false"
			 var="accountConfig">
		<mh:col name="name" title="security.account.name" width="20"/>
		<mh:col name="companyName" title="security.account.companyName" width="30"/>
		<mh:col name="departName" title="security.account.departName" width="20"/>
		<mh:col name="username" title="security.account.username" width="15"/>
		<mh:col name="code" title="security.account.code" width="15"/>
	</mh:grid>
	
</div>

<script type="text/javascript">

   $(document).ready(function() {
	   
	    //初始化部门列表
		function initDepart(organId) {
			var url = '<c:url value="/organ/organization/findOrgans?type=2&parentId="/>' + organId,
				departEl = $(".depart-select"),
				value = departEl.val();
			 $.getJSON(url, function(datas) {
				departEl.empty();
				$.each(datas, function(index, item) {
					var option = $("<option></option>").text(item.text).val(item.id).appendTo(departEl);
					if(value == item.id) {
						option.prop("selected", true); //设置选中值
					}
					$("#searchForm.departName").val(item.text); //设限选中部门名称
				});
			});
		}
	    
		//根据获取单位变化动态获取部门列表
		$("#company").on("change", function(e){
			var _this = $(this),
			    data  = e.added;
		      	$("#companyName").val(data.name),
				initDepart( _this.val());
	    }); 
		
   });
</script>