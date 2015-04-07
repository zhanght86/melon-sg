<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<c:forEach items="${fields}" var="field" varStatus="num">
	<div class="form-group">
		<%-- 表单label --%>
		<label class="col-xs-3 control-label" for="${field.fieldName}">
			<c:if test="${field.required}">
				<span class="form-required">*</span>
			</c:if>
			${field.label}
		</label>
		<%-- 表单输入部分 --%> 
		<div class="col-xs-9 form-field">
			<c:choose>
				<%-- 输入类型为text，单行输入框 --%>
				<c:when test="${field.fieldType == 0}">
					<input id="${field.fieldName}" class="form-control" type="text" value="${field.defaultValue}" name="${field.fieldName}">
				</c:when>
				
				<%-- 输入类型为textarea，多行输入框 --%>
				<c:when test="${field.fieldType == 1}">
					<textarea cols="5" rows="3" class="form-control" name="${field.fieldName}" id="${field.fieldName}">${field.defaultValue}</textarea>
				</c:when>
				
				<%-- 输入类型为radio，单选按钮 --%>
				<c:when test="${field.fieldType == 2}">
					<%-- 取数据字典值 --%>
					<c:forEach items="${field.dicts}" var="dict" varStatus="state1">
						<label class="radio-inline" for="${field.fieldName}_${state1.count}">
							<input type="radio" ${dict.value == field.defaultValue? "checked='checked'" : ""} value="${dict.value}" name="${field.fieldName}" id="${field.fieldName}_${state1.count}">
							${dict.label}
						</label>
					</c:forEach>
				</c:when>
				
				<%-- 输入类型为checkbox，复选按钮 --%>
				<c:when test="${field.fieldType == 3}">
					<%-- 取数据字典值  --%>
					<c:forEach items="${field.dicts}" var="dict" varStatus="state2">
						<label class="checkbox-inline" for="${field.fieldName}_${state2.count}">
							<input type="checkbox" ${fn:contains(field.defaultValue, dict.value) ? "checked='checked'" : ""} value="${dict.value}" name="${field.fieldName}" id="${field.fieldName}_${state2.count}">
							${dict.label}
						</label>
					</c:forEach>
				</c:when>
				
				<%-- 输入类型为select，下拉框  --%>
				<c:when test="${field.fieldType == 4}">
					<select class="form-control" name="${field.fieldName}" id="${field.fieldName}" >
						<%-- 取数据字典值  --%>
						<c:forEach items="${field.dicts}" var="dict">
							<option value="${dict.value}" ${dict.value == field.defaultValue? "selected='true'" : ""}>${dict.label}</option>
						</c:forEach>
					</select>
				</c:when>
			</c:choose>
			<%-- 判断是否有提示信息 --%>
			<c:if test="${!empty field.hint}">
				<label class="form-hint">${field.hint}</label>
			</c:if>
		</div>
	</div>
</c:forEach>