<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<mh:section id="basic" >

	<jsp:attribute name="title">
		<fmt:message key="base.dictionary.root"/>
	</jsp:attribute>
	
	<jsp:attribute name="remarks">
		<fmt:message key="security.resource.root.title" />
	</jsp:attribute>
	
	<jsp:attribute name="body">
		数据字典说明描述......
	</jsp:attribute>
	
</mh:section>