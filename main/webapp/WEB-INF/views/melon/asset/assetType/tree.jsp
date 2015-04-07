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
	  	<button class="btn btn-primary btn-sm" id="btnCreate"><fmt:message key="button.create"/></button>
		<button class="btn btn-default btn-sm" id="btnRemove" style="margin-left: 6px;"><fmt:message key="button.remove"/></button>
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
			 url="asset/type/find"
			 sortName="order"
			 scriptSelf="false">
	</mh:tree> 
</div>
<div class="col-xs-8 col-right" id ="ASSETTYPE_CONTAINER">
 
</div>

<script type="text/javascript">
$(function(){
	var createUrl = "<c:url value='/asset/type/create/'/>",
		updateUrl = "<c:url value='/asset/type/update/'/>",
		removeUrl = "<c:url value='/asset/type/remove/'/>",
		assetForm = $("#ASSETTYPE_CONTAINER"),
		btnSearch = $("#btnSearch"),
		btnRemove = $("#btnRemove"),
		btnCreate = $("#btnCreate"),
		searchName = $("#searchName");
	//树形节点的查询
	btnSearch.on("click",function(event){
		assetTypeTree.search($.trim(searchName.val()));
		event.preventDefault();
	});
	//菜单资源选择注册
	assetTypeTree.onSelect(function(data) {
		var id = data.id;
		assetForm.load(updateUrl + id); //加载修改页面 
		//处理删除按钮状态
		if(assetTypeTree.isRoot(id)) {
			btnRemove.prop("disabled", true);
		} else {
			btnRemove.prop("disabled", false);
		}
	});
	//默认选中根节点
	assetTypeTree.onReady();
	//处理新增按钮
	btnCreate.on("click",function(){
		//请求create页面
		var id = assetTypeTree.getSelected();
		assetForm.load(createUrl + id); //加载新增页面 
	});
	//处理删除按钮
	btnRemove.on("click",function(event){
		var id = assetTypeTree.getSelected(),
			parentId = assetTypeTree.getParent(id),
			url = removeUrl + id;
		//确认删除过程
		Dialog.confirm(function() {
			$.post(url, function(data) {
				//如果删除成功
				if(Melon.isSuccess(data)) {
					assetTypeTree.remove(id);
					assetTypeTree.setSelected(parentId, true);
					assetTypeTree.setSelected(id, false);
					Logger.success("<fmt:message key='security.resource.success.hint'/>");
				} else {
					Logger.error("<fmt:message key='operation.failed'/>");
				}
			});
		}, "<fmt:message key='tree.remove.hint' />");
		//阻止默认的表单提交
		event.preventDefault();
	});
});
</script>