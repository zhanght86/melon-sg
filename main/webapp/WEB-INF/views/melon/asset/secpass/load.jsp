<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<div class="page-query-heading">
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<mh:query>
			<jsp:attribute name="buttons">
				<mh:button href="asset/secpass/create" class="btn btn-primary"><fmt:message key="button.create"/></mh:button>
				<mh:button href="security/account/resetSecPass" class="btn btn-default"><fmt:message key="button.resetSecPass"/></mh:button>
			</jsp:attribute>
			<jsp:attribute name="quickQuery">
				<!-- 快速查询摆放区 -->
				<sf:label path="assetName" cssClass="col-xs-4 control-label"><fmt:message key="asset.secPass.assetName"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="assetName" cssClass="form-control" maxlength="50" />
				</div>
			</jsp:attribute>
			<jsp:attribute name="advanceQuery">
				
				<div class="form-group">	
				
					<div class="col-xs-5 form-field-group">
						<sf:label path="ip" cssClass="col-xs-4 control-label"><fmt:message key="asset.secPass.ip"/></sf:label>
						
						<div class="col-xs-8 form-field">
							<sf:input path="ip" cssClass="form-control" maxlength="50" />
						</div>
					</div>
				
				</div>
				
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>

<div class="page-query-body">
	<mh:grid id="secPass" 
			 queryUrl = "asset/secpass/list"
			 showUrl = "asset/secpass/show"
			 deleteUrl = "asset/secpass/remove"
			 updateUrl = "asset/secpass/update"
			 hasPages= "true" 
			 var="secPassConfig">
		<mh:col name="account" title="asset.secPass.account" width="20"/>
		<mh:col name="desPassWord" index="passWord" title="asset.secPass.passWord" width="20"/>
		<mh:col name="assetName" title="asset.secPass.assetName" width="20"/>
		<mh:col name="ip" title="asset.secPass.ip" width="20"/>
		<mh:col name="dueDate" title="asset.secPass.dueDate" width="20"/>
	</mh:grid>
</div>

<script type="text/javascript">
$(document).ready(function() {
	var curStr = "${now}",
		cur = new Date(curStr.replace("-", "/").replace("-", "/"));//当天时间
	//table加载完成之后
	secPassGrid.setComplete(function() {
		$('td[aria-describedby="secPass_GRID_dueDate"]').each(function(index, item){
			var dueDate =  new Date($(item).text().replace("-", "/").replace("-", "/"));
			//到期
			if(dueDate-cur == 0) {
				var text= $(item).text()+'<fmt:message key="asset.secPass.due" />';
				$(item).text(text).css("color", "#f55549");
			}
			//过期
			if(dueDate-cur < 0) {
				var text= $(item).text()+'<fmt:message key="asset.secPass.past" />';
				$(item).text(text).css("color", "#f55549");
			}
		});
	});
});

</script>

