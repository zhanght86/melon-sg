<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<link rel="stylesheet" href="<mh:theme code='jquery.ui.css'/>" media="all" />
<link rel="stylesheet" href="<mh:theme code='bootstrap.css'/>" media="all" />
<link rel="stylesheet" href="<mh:theme code='melon.css'/>" media="all" />
<c:out value="<!--[if lt IE 9]>" escapeXml="false"/>
	<link rel="stylesheet" href="<mh:theme code='melon.hacker.css'/>" media="all" />
   	<script src="<mh:theme code='html5-respond.js'/>"></script>
<c:out value="<![endif]-->" escapeXml="false"/>
<link rel="stylesheet" href="<c:url value="/statics/kibana/css/bootstrap.light.min.css"/>">
 <link rel="stylesheet" href="<c:url value="/statics/scripts/echart/css/chartTalbe.css"/>">
 <div id="loadings">loading......</div>
 <jsp:include page="tableInfo.jsp" />     
<script type="text/javascript"  src="<mh:theme code='jquery.js'/>"></script>
<script type="text/javascript"  src="<mh:theme code='bootstrap.js'/>"></script>
<script type="text/javascript"  src="<mh:theme code='jquery.ui.js'/>"></script>
<script type="text/javascript"  src="<mh:theme code='melon.commons.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='elasticsearch.jquery.js'/>"></script>
<script>
function searchLasEs(a,b,c,d){}
function search(){
	$("#loadings").hide();
	$("#loadings").dialog("close");
	chatTable.index ='las-syslog';
	chatTable.parmasChat=[];
	chatTable.from=0;
	chatTable.to=new Date().getTime();
	var ids = "\"<c:out value='${ids}'/>\"".replace(/,/g,"\",\"");
	chatTable.parmasChat.push({name:"ID",value:"("+ids+")"});
	var titles = ['occurTime','devAddr','category','eventType','sAddr','dAddr','dPort','protocol'];
	chatTable.searchChat('las-syslog',titles);
}
</script>
<script src="<c:url value="/statics/scripts/echart/js/table.js"/>"></script>
<script>
search();
</script>
