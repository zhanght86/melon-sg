<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />

<style type="text/css">
	#import-url-group .input-group{
		margin-bottom: 7px;
	}
</style>

<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>

<div class="col-xs-4 tree-left">
	信息系统树
	
	<mh:tree id="deviceInfo" 
			 rootId="1"
			 url="/asset/infoSystem/find"
			 scriptSelf="false">
	</mh:tree> 
</div>
<div class="col-xs-8 col-right" id ="ASSETTYPE_CONTAINER">
 
</div>

<script type="text/javascript">
$(function(){
	 var byDeviceInfoGridUrl = "<c:url value='/asset/device/byDeviceInfoGrid/'/>", 
		assetForm = $("#ASSETTYPE_CONTAINER"),
		btnSearch = $("#btnSearch"),
		searchName = $("#searchName");
	//树形节点的查询
	btnSearch.on("click",function(event){
		deviceInfoTree.search($.trim(searchName.val()));
		event.preventDefault();
	});
	//菜单资源选择注册
	deviceInfoTree.onSelect(function(data) {
		//console.info(data);
		assetForm.load(byDeviceInfoGridUrl + data.id); //加载修改页面 
	});
	//默认选中根节点
	deviceInfoTree.onReady(); 
	
	
});
</script>