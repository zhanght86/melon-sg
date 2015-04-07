<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />

<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>


<div class="col-xs-4 col-left">
	<div class="btn-group"  id="btnCreate">
	  <button type="button" class="btn btn-primary dropdown-toggle btn-sm" data-toggle="dropdown">
	    <fmt:message key="button.create"/><span class="caret"></span>
	  </button>
	  <ul class="dropdown-menu" role="menu">
	    <li><a href="javascript:void(0);" id="btn-province" data-type="0"><fmt:message key="organ.organization.province"/></a></li>
	    <li><a href="javascript:void(0);" id="btn-company" data-type="1"><fmt:message key="organ.organization.company"/></a></li>
	  </ul>
	</div>
	
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
	<mh:tree id="organ"
			rootId="1"
			url="organ/organization/find"
			sortName="order"
			scriptSelf="false">
	</mh:tree>
</div>
<div class="col-xs-8 col-right" id="organForm"></div> 
<script type="text/javascript">
	$(document).ready(function() {
		var createUrl = "<c:url value='/organ/organization/create/'/>",
			updateUrl = "<c:url value='/organ/organization/update/'/>",
			removeUrl = "<c:url value='/organ/organization/remove/'/>",
			organForm = $("#organForm"),
			btnSearch = $("#btnSearch"),
			btnRemove = $("#btnRemove"),
			btnCreate = $("#btnCreate"),
			searchName = $("#searchName");
		//树形节点的查询
		btnSearch.on("click",function(event){
			organTree.search($.trim(searchName.val()));
			event.preventDefault();
		});
		//菜单资源选择注册
		organTree.onSelect(function(data) {
			var id = data.id,
				type = data.type;
			loadForm(id);//加载修改页面 
			//处理删除按钮状态
			if(organTree.isRoot(id)) {
				btnRemove.prop("disabled", true);
			} else {
				btnRemove.prop("disabled", false);
			}
			//处理新建按钮
			if(type==2) {
				btnCreate.find("button").prop("disabled", true);
			}else {
				btnCreate.find("button").prop("disabled", false);
			}
		});
		//默认选中根节点
		organTree.onReady();
		//载入表单
		function loadForm(id, type) {
			var url = updateUrl + id;
			//必定是新建页面
			if(type !== undefined) {
				url = createUrl + id + "?type=" + type;
			}
			organForm.load(url);
		}
		//处理新增按钮选项
		btnCreate.find("a[data-type]").on("click", function(event) {
			var type = $(this).attr("data-type"),
				id = organTree.getSelected();
			loadForm(id, type);
		});
		//处理新增按钮
		btnCreate.find(">button").on("click",function(){
			var node = organTree.getSelected(true);
			if(node.length > 0) {
				var data = node[0].original,
					type = data.type;
				if(type !== 0) {
					//部门与单位下都只能创建部门
					loadForm(data.id, 2);//载入新建表单
					return false;
				}
			}
		});
		
		//处理删除按钮
		btnRemove.on("click",function(event){
			var id = organTree.getSelected(),
				parentId = organTree.getParent(id),
				url = removeUrl + id;
			//确认删除过程
			Dialog.confirm(function() {
				$.post(url, function(data) {
					//如果删除成功
					if(Melon.isSuccess(data)) {
						organTree.remove(id);
						organTree.setSelected(parentId, true);
						organTree.setSelected(id, false);
						Logger.success("<fmt:message key='organ.organization.remove.hint'/>");
					} else {
						Logger.error("<fmt:message key='security.resource.failed.hint'/>");
					}
				});
			}, "<fmt:message key='organ.organization.remove.confirm' />");
			//阻止默认的表单提交
			event.preventDefault();
		});
	});
</script>