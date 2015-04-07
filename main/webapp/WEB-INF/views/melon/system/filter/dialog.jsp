<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
	此页面用jsp:include方式调用，
	接口说明：需要修改url，通过url访问的dialog中的树
 --%>
<%-- <c:url value="/era/rule/selectList" var="forSelectUrl"/>
<div id="domainDialog" title="选择">
	<iframe src="${forSelectUrl}" id="domainFrame" height="300px" width="100%" frameborder="0">
	</iframe>
</div> --%>

<div class="page-query-body" style="display:block" id="domainDialog">
	<mh:grid id="rule"
			 queryUrl = "era/rule/list"
			 hasPages= "true"
			 multiSelect = "true"
			 var="ruleConfig">
		<mh:col name="desc" title="rule.desc" width="30"/>
		<mh:col name="enabled" title="rule.enabled" width="25"/>
		<mh:col name="last" title="rule.last" width="20" />
		<mh:col name="action" title="rule.action" formatter="showAction" width="25" />
	</mh:grid>
</div>
<script type="text/javascript">
function showAction(action, options, rowObject) {
	var result = "无";
	if(action == 1){
		result = "生成告警";
	}else if(action == 10){
		result = "生成工单";
	}else if(action == 11){
		result = "生成工单,生成告警";
	}else if(action == 100){
		result = "发送短信";
	}else if(action == 101){
		result = "生成告警,发送短信";
	}else if(action == 110){
		result = "生成工单,发送短信";
	}else if(action == 111){
		result = "生成工单,生成告警,发送短信";
	}
	return result;
}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		var domainDialog = $("#domainDialog");
		domainChooser = {
				grid : null,
				/**
					@param selectCallBack 在点击 
				**/
				init : function(selectCallBack) {
					domainDialog.dialog({
						autoOpen : false,
						height : "auto",
						width : 600,
						buttons : [
						           {
						        	   text : "保存",
						        	   click : function(event) {
						        		   var domain = $("#domainFrame").get(0).contentWindow.getSelected();
						        		   if(domain){
						        			   selectCallBack.call(window, domain);//domain是选中的资源文件的对象
							        		   domainDialog.dialog("close");
							        		   event.preventDefault();
						        		   }
						        		  
										}
									},{
										text : "取消",
										click : function(event) {
											domainDialog.dialog("close");
											event.preventDefault();
										}
									}
						]
					});
				},
				show : function() {
					domainDialog.dialog("open");
				},
				hide : function() {
					domainDialog.dialog("colse");
				}
		};
	});
</script>




