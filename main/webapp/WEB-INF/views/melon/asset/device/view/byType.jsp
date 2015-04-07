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

    <form class="navbar-form navbar-right" role="search" onsubmit="return false;" style="margin-top: 0px;padding-right:0px;">
		<div class="form-group">
			<div class="input-group">
				<input type="text" class="form-control"	style="width: 125px; height: 30px;margin-right:1px;" id="searchName">
				<span class="input-group-btn">
					<button class="btn btn-primary btn-sm" type="button" id="btnSearch">
						<span class="glyphicon glyphicon-search"></span>
					</button>
				</span>
			</div>
		</div>
	</form>
    <mh:tree id="assetType" 
			 rootId="1"
			 url="asset/type/findCustomTree?type=1&rootId=${rootId }"
			 sortName="order"
			 scriptSelf="false">
	</mh:tree>
</div>
<div class="col-xs-8 col-right" id ="DEVICEORGAN_CONTAINER">
 
</div>

<script type="text/javascript">
$(function(){
	var byTypeGridUrl = "<c:url value='/asset/device/byTypeGrid/'/>", 
	assetForm = $("#DEVICEORGAN_CONTAINER"),
	btnSearch = $("#btnSearch"),
	searchName = $("#searchName");
	//树形节点的查询
	btnSearch.on("click",function(event){
		assetTypeTree.search($.trim(searchName.val()));
		event.preventDefault();
	});
	//菜单资源选择注册
	assetTypeTree.onSelect(function(data) {
		assetForm.load(byTypeGridUrl + data.path); //加载修改页面 
	});
	//默认选中根节点
	assetTypeTree.onReady();
});
</script>