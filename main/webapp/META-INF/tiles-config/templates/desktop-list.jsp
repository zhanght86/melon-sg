<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<link rel="stylesheet" href="<mh:theme code='jquery.jqGrid.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.jqGrid.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxgrid.js'/>"></script>
<div class="page-list">
	<div class="page-list-header"></div>
	<div class="page-list-body">
		<tiles:insertAttribute name="body"/>
	</div>
	<div class="page-list-footer">
	</div>
</div>