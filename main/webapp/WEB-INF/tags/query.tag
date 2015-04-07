<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag description="查询区域" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="buttons" fragment="true" description="按钮区域"%>
<%@ attribute name="quickQuery" fragment="true" description="查询区域"%>
<%@ attribute name="advanceQuery" fragment="true" description="高级查询区域"%>
<div class="form-group form-query-actions">

		<div class="action-buttons col-xs-5">
			<jsp:invoke fragment="buttons"/>
		</div>
		
		<div class="col-xs-5 form-field-group">
			<jsp:invoke fragment="quickQuery"/>
		</div>
		
		<!-- 查询按钮摆放区,包含查询按钮及高级查询按钮 -->
		<div class="col-xs-2 query-buttons form-field-group" style="padding-right : 0px;padding-left : 0px;">
			<button type="button" class="btn btn-primary" id="BUTTON_QUERY">
				<span class="glyphicon glyphicon-search"></span>&nbsp;<fmt:message key="button.query"/>
			</button>
			<a href="javascript:void(0)" class="advance-search" id="AD_BUTTON_QUERY"><fmt:message key="button.advancedQuery"/>
				<div class="advance-open"></div>
			</a>
		</div>
</div>		
<jsp:invoke fragment="advanceQuery"/>

