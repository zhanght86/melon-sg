<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />

<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>

<%-- 单独的表单必须嵌入到此样式标签里 --%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="role" role="form" cssClass="form-horizontal">
		
		<sf:hidden path="id"/>
		<sf:hidden path="resources"/>
		
		<div class="form-group">
			<sf:label path="name" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/>
				<fmt:message key="security.role.name"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<sf:input path="name" cssClass="form-control" maxlength="20"/>
				<sf:errors path="name"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="code" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/>
				<fmt:message key="security.role.code"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<sf:input path="code" cssClass="form-control" maxlength="20"/>
				<sf:errors path="code"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="level" cssClass="col-xs-3 control-label">
				<fmt:message key="security.role.level"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<mh:dictionary var="levelEnum" key="organLevel"/>
				<sf:checkboxes path="level"  items="${levelEnum}"/>
				<sf:errors path="level"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="mutex" cssClass="col-xs-3 control-label">
				<fmt:message key="security.role.mutex"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<mh:dictionary var="boolenums" key="bool"/>
				<sf:radiobuttons path="mutex" items="${boolenums}"/>
				<sf:errors path="mutex"/>
				<label class="form-hint"><fmt:message key="security.role.mutex.hint"/></label>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="order" cssClass="col-xs-3 control-label">
				<fmt:message key="security.role.order"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<sf:input path="order" cssClass="form-control" maxlength="4"/>
				<sf:errors path="order"/>
				<label class="form-hint"><fmt:message key="hint.order"/></label>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="resources" cssClass="col-xs-3 control-label">
				<fmt:message key="security.role.resource"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<mh:tree id="menu"
						rootId="1"
						url="security/menuResource/choose"
						sortName="order"
						multiSelect="true"
						scriptSelf="false">
				</mh:tree>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="remarks" cssClass="col-xs-3 control-label">
				<fmt:message key="security.role.remarks"/>
			</sf:label>
			
			<div class="col-xs-9 form-field">
				<sf:textarea path="remarks" cssClass="form-control" rows="5"/>
				<sf:errors path="remarks"/>
			</div>
		</div>
		
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.save"/></mh:button>
				<mh:button class="btn btn-default" href="security/role/list"><fmt:message key="button.backList"/></mh:button>
			</div>
		</div>
		
	</sf:form>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		if(${id != null}){
			$("#code").attr("readOnly",true);
		}
		var checkedMenu = <mh:toJson value="${role.resources}"/>,
			rootId = AjaxTree.prototype.ROOT_ID,
			resources = $("#resources"),
			btnSave = $("#btnSave"),
			roleForm = $("#role");
		
		//验证表单
		roleForm.validate({
			rules : {
				name : {
					required : true,
					stringChar : true
				},
				code : {
					required : true
					<c:if test="${empty role.id}">
					,remote : {
						url : '<c:url value="/security/role/checkUnique"/>',
						type : 'POST',
						data : {
							code : function() {
								return $("#code").val();
							}
						}
					}
					</c:if>
					
				},
				order : {
					integer : true,
					max : 9999
				}
			}
		});
		
		//添加上选中状态
		menuTree.onReady(function() {
			//选中已有的节点
			if($.isArray(checkedMenu)) {
				$.each(checkedMenu, function(index, item) {
					menuTree.setChecked(item.id);
				});
			}
			//阻止选择根节点
			var root = $(menuTree.getNode(rootId, true)),
				rootCheck = root.find(".jstree-checkbox:first");
			rootCheck.remove();
		});
		//级联选中祖先节点
		menuTree.onActivate(function(event, node) {
			var checked = menuTree.isChecked(node),
				parentNode;
			if(checked) {
				parentNode = menuTree.getParent(node);
				while(parentNode != rootId && node.id != rootId) {
					this.setChecked(parentNode);
					parentNode = menuTree.getParent(parentNode);
				}
			}
		});
		//处理保存按钮
		btnSave.on("click", function(event) {
			if(roleForm.valid()){
			//选中节点时必须选中其所有的祖先节点
			var checked = menuTree.getChecked(),
				i = 0;
			//删除根节点
			for(; i < checked.length; i++) {
				if(checked[i] == rootId) {
					checked.splice(i, 1);
					break;
				}
			}
			//填回到节点
			resources.val(checked.join());
			//提交表单
				roleForm.submit();
			}
			//阻止默认的提交动作
			event.preventDefault();
		});
	});
</script>
