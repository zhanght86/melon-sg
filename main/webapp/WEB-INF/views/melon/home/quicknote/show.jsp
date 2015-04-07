<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<div class="quicknoted">
	<div class="col-xs-3 control-label">
		<a href="#" style="font-weight:bold;" data-toggle="modal" data-target="#listNotesModal" id="noteList">
			<fmt:message key="system.home.note"/>(${noteCounts}条)</a>
			<%-- <span style="color:#808080;">(${noteCounts}条)</span> --%>
	</div>
	<div class="col-xs-9 form-field">
		<a href="#" id="addNote" data-toggle="modal" data-target="#noteModal">添加批注</a>
	</div>
</div>
