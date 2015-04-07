<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />

<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>


<div class="col-xs-4 tree-left">
	<div class="input-group">
		<input type="text" class="form-control" style="height: 30px;" id="typeName"> 
		<span class="input-group-btn"> 
			<a class="btn btn-primary btn-sm" type="button"	href="javascript:void(0);" id="btnSearch"> 
				<span class="glyphicon glyphicon-search"></span>
			</a>
		</span> 
	</div>

	<mh:tree id="assetType" 
			 rootId="1"
			 url="asset/type/find"
			 sortName="order"
			 scriptSelf="false">
	</mh:tree> 
</div>

<div class="col-xs-8 col-right" id ="fieldsGroup" style="padding : 20px 15px 0px 15px;">
</div>

<script type="text/javascript">
	var typeName = $("#typeName"),
		btnSearch = $("#btnSearch"),
		fieldsGroup = $("#fieldsGroup"),
		node,
		groupUrl = "<c:url value='/asset/field/list/'/>",
		createUrl = "<c:url value='/asset/field/create/' />",
		previewUrl = "<c:url value='/asset/field/preview/' />",
		updateUrl = "<c:url value='/asset/field/update/' />",
		removeUrl = "<c:url value='/asset/field/remove/' />";
		
	var Fields = {
			
		/**/
		loadGroup : function() {
			fieldsGroup.load(groupUrl + node.id); //加载列表页面 
		},
		
		loadUpdate : function(id) {
			fieldsGroup.load(updateUrl + id);//加载修改页面 
		},
		
		loadCreate : function() {
			fieldsGroup.load(createUrl + node.id); //加载新建页面 
		},
		
		loadPreview : function() {
			fieldsGroup.load(previewUrl + node.id); 
		}
	};
	$(document).ready(function() {
		
		//树形节点的查询
		btnSearch.on("click",function(event){
			assetTypeTree.search($.trim(typeName.val()));
			event.preventDefault();
		});
		//默认选中根节点
		assetTypeTree.onReady();
		//载入
		assetTypeTree.onSelect(function(data) {
			node = data;
			Fields.loadGroup();
		});
	});
</script>