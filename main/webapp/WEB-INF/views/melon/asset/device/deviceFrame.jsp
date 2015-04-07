<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	#assetDialog{
		background-color: #fff;
	}
</style>
<c:url value="/asset/device/deviceGrid" var="dialogUrl"/>
<div id="assetDialog" title="请选择" style="background-color: #fff;">
	<iframe src="${dialogUrl}" id="assetFrame" height="100%" width="560" frameborder="0" style="background-image: none;background-color: #fff;">
	</iframe>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		assetChooser = {
				grid : null,
				init : function(selectCallBack) {
					$("#assetDialog").dialog({
						modal: true,
						autoOpen : false,
						height : 500,
						width :600,
						buttons : [
						           {
						        	   text : "保存",
						        	   click : function(event) {
						        		   var assets = $("#assetFrame").get(0).contentWindow.getAssets();
						        		   selectCallBack.call(window,assets);
						        		   $("#assetDialog").dialog("close");
 						        		   event.preventDefault();
										}
									},{
										text : "取消",
										click : function(event) {
											$("#assetDialog").dialog("close");
											event.preventDefault();
										}
									}
						]
					});
				},
				show : function() {
					$("#assetDialog").dialog("open");
				},
				hide : function() {
					$("#assetDialog").dialog("colse");
				}
		};
		
	});
</script>
