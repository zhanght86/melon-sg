<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<script type="text/javascript" src="<c:url value="/statics/scripts/require-jquery.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/statics/scripts/melon_zh_CN.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/statics/scripts/melon.js"/>"></script>
	<script type="text/javascript">
	 require.config({
         baseUrl : '<c:url value="/statics/scripts"/>',
         shim :  {
             "lib/additional-methods" : ["lib/jquery.validate"],
             "jqplotplugin/jqplot.canvasTextRenderer" : ["jqplot/jquery.jqplot"],
             "jqplotplugin/jqplot.canvasAxisLabelRenderer" : ["jqplotplugin/jqplot.canvasTextRenderer"],
             'backbone' : {
                 deps : ['underscore'],
                 exports : 'Backbone'
             },
             'underscore' : {
                 exports : '_'
             }
         }
	 });
	 require(["underscore", 'lib/jquery.treetable'], function() {
		 
	 });
	</script>
	<title></title>
</head>
<body>
	<sp:theme code="treetable.css" var="treetable" />
    <link rel="stylesheet" type="text/css" href='<c:url value="${treetable}"/>' media="all" />

<table id="mRuleTree">
    <thead>
        <tr>
            <th>Name</th>
            <th>Kind</th>
            <th>Size</th>
        </tr>
    </thead>
    <tbody>
        <tr data-tt-id="1">
            <td>Parent</td>
            <td>1</td>
            <td>1</td>
        </tr>
        <tr data-tt-id="2" data-tt-parent-id="1" id="childNode">
            <td>Child</td>
            <td>2</td>
            <td>2</td>
        </tr>
    </tbody>
</table>

<script type="text/template" id="change">
    <div>&{username}</div>
</script>

<script type="text/javascript">
require(['underscore','lib/jquery.treetable'], function(_) {
    _.templateSettings = {
      interpolate : /\&\{(.+?)\}/g
    };
    $(document).ready(function() {
    	var c = $("#change").html();
    	alert(_.template(c, {username : "李浩"}));
        $("#mRuleTree").treetable();
        $("#mRuleTree").treetable("loadBranch", $("#mRuleTree").treetable("node", 2), "<tr><td></td>无病<td>3</td><td>3</td></tr>");
    });
});
</script>
</body>
</html>