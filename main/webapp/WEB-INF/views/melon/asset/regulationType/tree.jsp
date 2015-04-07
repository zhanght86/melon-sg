<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />

<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>


<div class="col-xs-4 col-left">
	
	<button class="btn btn-primary btn-sm" id="btnCreate" style="margin-left: 6px;"><fmt:message key="button.create"/></button>
	<button class="btn btn-default btn-sm" id="btnRemove" style="margin-left: 6px;"><fmt:message key="button.remove"/></button>
	<form class="navbar-form navbar-right" role="search" onsubmit="return false;" style="margin-top: 0px;padding-right:0px;">
		<div class="form-group">
			<div class="input-group">
				<input type="text" class="form-control" style="width: 125px; height: 30px;margin-right:1px;" id="searchName">
				<span class="input-group-btn">
					<button class="btn btn-primary btn-sm" type="button" id="btnSearch">
						<span class="glyphicon glyphicon-search"></span>
					</button>
				</span>
			</div>
		</div>
	</form>
	<mh:tree id="type"
			rootId="1"
			url="asset/regulationType/find"
			sortName="order"
			scriptSelf="false">
	</mh:tree>
</div>
<div class="col-xs-8 col-right" id="typeForm"></div> 
<script type="text/javascript">
	$(document).ready(function() {
		var createUrl = "<c:url value='/asset/regulationType/create/'/>",
			updateUrl = "<c:url value='/asset/regulationType/update/'/>",
			removeUrl = "<c:url value='/asset/regulationType/remove/'/>",
			typeForm = $("#typeForm"),
			btnSearch = $("#btnSearch"),
			btnRemove = $("#btnRemove"),
			btnCreate = $("#btnCreate"),
			searchName = $("#searchName");
		//树形节点的查询
		btnSearch.on("click",function(event){
			typeTree.search($.trim(searchName.val()));
			event.preventDefault();
		});
		//菜单资源选择注册
		typeTree.onSelect(function(data) {
			var id = data.id;
			loadForm(id);//加载修改页面 
			//处理删除按钮状态
			if(typeTree.isRoot(id)) {
				btnRemove.prop("disabled", true);
			} else {
				btnRemove.prop("disabled", false);
			}

		});
		//默认选中根节点
		typeTree.onReady();
		//载入表单
		function loadForm(id, create) {
			var url = updateUrl + id;
			//必定是新建页面
			if(create !== undefined) {
				url = createUrl + id;
			}
			typeForm.load(url);
		}
		//处理新增按钮选项
		btnCreate.on("click", function(event) {
			var id = typeTree.getSelected();
			loadForm(id, 1);
		});
		
		//处理删除按钮
		btnRemove.on("click",function(event){
			var id = typeTree.getSelected(),
				parentId = typeTree.getParent(id),
				url = removeUrl + id;
			//确认删除过程
			Dialog.confirm(function() {
				$.post(url, function(data) {
					//如果删除成功
					if(Melon.isSuccess(data)) {
						typeTree.remove(id);
						typeTree.setSelected(parentId, true);
						typeTree.setSelected(id, false);
						Logger.success("<fmt:message key='melon.regType.remove.hint'/>");
					} else {
						Logger.error("<fmt:message key='security.resource.failed.hint'/>");
					}
				});
			}, "<fmt:message key='melon.regType.remove.confirm' />");
			//阻止默认的表单提交
			event.preventDefault();
		});
	});
</script>