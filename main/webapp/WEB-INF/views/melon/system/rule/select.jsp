<%
	/**
     * @since 1.0 2013-5-21,下午4:28:44
     *
     * @author  <a href="mailto:gaobg@legendsec.com">高保国</a>
     * @version  1.0
     */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<sp:theme code="jqGrid.css" var="jqGrid"/>
<link rel="stylesheet" type="text/css" href="<c:url value="${jqGrid}"/>" media="all" />


<mh:Grid url="/era/rule/list" id="mRuleGrid" checkable="false" hasPages="true" height="300">
	<mh:Column name="desc" title="system.rule.desc" width="200" />
	<mh:Column name="enabled" title="system.rule.isEnabled"/>
	<mh:Column name="last" title="system.rule.last" />
	<mh:Column name="order" title="system.rule.order"/>
<%-- </mh:Grid><jsp:include page="/WEB-INF/tiles3/include/jquery-grid.jsp" /> --%>
<script type="text/javascript">
var mRuleGrid;
    $(document).ready(function() {
    	
    	
    	//初始化Grid
        mRuleGrid = new melon.AjaxGrid(mRuleGrid, {
            query : function() {
                return {desc : $("#desc").val()};
            }
        });
      //设置当前的查询表格，必须声明此语句
		melon.Query.setGrid(mRuleGrid);
     	//注册查询按钮
    	$("#query").on("click", function() {
    		mRuleGrid.query();
    	});
     	
    });
    function getSelected(){
    	var selectRowId = mRuleGrid.getSelectedOnly();
 		if(!selectRowId){
 			melon.alert("<fmt:message key='era.filter.rules.confirm'/>");
 			return false;
 		}
 		var rowData = mRuleGrid.getRowData(selectRowId);
 		return {ruleId:selectRowId,ruleName:rowData.desc};
    }
</script>