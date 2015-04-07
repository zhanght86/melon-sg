<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<mh:section id="addition">

	<jsp:attribute name="title">
		<fmt:message key="asset.abstract.dynamic" />
	</jsp:attribute>
	
	<jsp:attribute name="body">
		<div class="col-xs-8 col-xs-offset-2 form-singlon" id="assetFields">
		</div>
	</jsp:attribute>
</mh:section>