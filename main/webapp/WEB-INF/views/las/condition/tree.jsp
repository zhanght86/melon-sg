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
	    <li><a href="javascript:void(0);" id="btn-province" data-type="0">事件</a></li>
	    <li><a href="javascript:void(0);" id="btn-company" data-type="1">逻辑运算</a></li>
	    <li><a href="javascript:void(0);" id="btn-province" data-type="2">条件表达式</a></li>
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
	<mh:tree id="condition"
			rootId="1"
			url="las/condition/find/1/${ruleId}"
			sortName="order"
			scriptSelf="false">
	</mh:tree>
</div>
<div class="col-xs-8 col-right" id="conditionForm"></div> 
<script type="text/javascript">
	$(document).ready(function() {
		var createUrl = "<c:url value='/las/condition/create/'/>",
			updateUrl = "<c:url value='/las/condition/update/'/>",
			removeUrl = "<c:url value='/las/condition/remove/'/>",
			conditionForm = $("#conditionForm"),
			btnSearch = $("#btnSearch"),
			btnRemove = $("#btnRemove"),
			btnCreate = $("#btnCreate"),
			searchName = $("#searchName"),
			ruleId = ${ruleId};
		//树形节点的查询
		btnSearch.on("click",function(event){
			conditionTree.search($.trim(searchName.val()));
			event.preventDefault();
		});
		//条件选择注册
		conditionTree.getSelectAll(function(data) {
			var id = data.original.id,
				type = data.original.conditionType,
				children = data.children;
			loadForm(id);//加载修改页面 
			//根节点与普通节点判断
			if(conditionTree.isRoot(id)) {
				btnRemove.prop("disabled", true);
				btnCreate.find("button").prop("disabled", false);
				btnCreate.find("li:eq(0)").show();
				btnCreate.find("li:gt(0)").hide();
			} else {
				btnRemove.prop("disabled", false);
				//处理新建按钮
				if(type == 0) {
					//事件节点,节点下只能包括一个条件子节点或者逻辑子节点
					//没有子节点
					if(children.length == 0) {
						btnCreate.find("button").prop("disabled", false);
						btnCreate.find("li:eq(0)").hide();
						btnCreate.find("li:gt(0)").show();
					} else {
						$.each(children, function(index, item) {
							//存在逻辑节点，则不允许再添加节点
							if(conditionTree.getNode(item, false).original.conditionType == 1) {
								btnCreate.find("button").prop("disabled", true);
							} else {
								//若存在条件节点，则可继续添加条件节点
								btnCreate.find("button").prop("disabled", false);
								btnCreate.find("li:eq(2)").show();
								btnCreate.find("li:lt(2)").hide();
							}
						});
					}
					
				} else if(type == 1){
					//逻辑节点
					btnCreate.find("button").prop("disabled", false);
					btnCreate.find("li:eq(0)").hide();
					btnCreate.find("li:gt(0)").show();
					
				} else {
					//条件节点
					btnCreate.find("button").prop("disabled", true);
				}
			}
		
		});
		//默认选中根节点
		conditionTree.onReady();
		//载入表单
		function loadForm(id, ruleType, condType) {
			var url = updateUrl + id;
			//必定是新建页面
			if(ruleType !== undefined) {
				url = createUrl + id + "?ruleType=" + ruleType + "&condType=" + condType + "&ruleId=" + ruleId;
			}
			conditionForm.load(url);
		}
		
		//处理新增按钮选项
		btnCreate.find("a[data-type]").on("click", function(event) {
			var type = $(this).attr("data-type"),
				nodes = conditionTree.getSelected(true);
			loadForm(nodes[0].original.id, nodes[0].original.ruleType, type);
		});
		
		//处理删除按钮
		btnRemove.on("click",function(event){
			var id = conditionTree.getSelected(),
				parentId = conditionTree.getParent(id),
				url = removeUrl + id;
			//确认删除过程
			Dialog.confirm(function() {
				$.post(url, function(data) {
					//如果删除成功
					if(Melon.isSuccess(data)) {
						conditionTree.remove(id);
						conditionTree.setSelected(parentId, true);
						conditionTree.setSelected(id, false);
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