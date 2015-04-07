<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<c:choose>
	<c:when test="${empty fields}">
		
		<mh:section id="basic" >
		
			<jsp:attribute name="title">
				<fmt:message key="asset.assetType.root"/>
			</jsp:attribute>
			
			<jsp:attribute name="remarks">
				<div class="alert alert-info">
					<fmt:message key="asset.assetType.root.title" />
					<p>
						<button class="btn btn-primary btnCreate btn-sm" id="createField" style="margin-top : 5px;">
							<fmt:message key="button.addField" />
						</button>
					</p>
				</div>
			</jsp:attribute>
			
			<jsp:attribute name="body">
				<fmt:message key="asset.assetType.root.description"/>
			</jsp:attribute>
			
		</mh:section>
	</c:when>
	<c:otherwise>
		
		<div class="col-page-title">
			<fmt:message key="asset.assetField.create" />
		</div>
		
		<div class="row"  style="text-align : right; margin-top : 10px; margin-bottom : 10px;">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<button class="btn btn-primary" id="btnPreview"><fmt:message key="button.preview"/></button>
				<button class="btn btn-default" id="btnCreate"><fmt:message key="button.adding"/></button>
			</div>
		</div>
		
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th width="5%">#</th>
					<th width="25%"><fmt:message key="asset.assetField.label" /></th>
					<th width="20%"><fmt:message key="asset.assetField.fieldType" /></th>
					<th width="12%"><fmt:message key="asset.assetField.required" /></th>
					<th width="26%"><fmt:message key="asset.assetField.defaultValue" /></th>
					<th width="12%"><fmt:message key="grid.operator" /></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${fields}" var="field" varStatus="num">
					<tr>
						<td>${num.count}</td>
						<td>${field.label}</td>
						<td>
							<mh:dictionary key="assetField.fieldType" value="${field.fieldType}"/>
						</td>
						<td>
							<mh:dictionary key="bool" value="${field.required}"/>
						</td>
						<td>${field.defaultValue}</td>
						<td >
							<button class="btnUpdate btn btn-default btn-xs" data-id="${field.id}">
								<span class="glyphicon glyphicon-pencil" ></span>
							</button>
							<button class="btn btn-default btn-xs btnRemove" data-id="${field.id}">
								<span class="glyphicon glyphicon-trash" ></span>
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
	$(document).ready(function() {
		//初始新建按钮
		$("#createField").click(function() {
			Fields.loadCreate();
		});
		//新建按钮
		$("#btnCreate").click(function() {
			Fields.loadCreate();
		});
		//编辑按钮
		$(".btnUpdate").click(function() {
			Fields.loadUpdate($(this).attr("data-id"));
		});
		//删除按钮
		$(".btnRemove").click(function() {
			//添加删除提示信息
			var url = removeUrl + $(this).attr("data-id");
			//确认删除过程
			Dialog.confirm(function() {
				$.post(url, function(data) {
					//如果删除成功
					if(Melon.isSuccess(data)) {
						Logger.success("<fmt:message key='operation.success'/>");
						Fields.loadGroup();
					} else {
						Logger.error("<fmt:message key='operation.failed'/>");
					}
				});
			}, "<fmt:message key='remove.confirm.hint' />");
		});
		//预览按钮
		$("#btnPreview").click(function() {
			Fields.loadPreview();
		});
	});

</script>

