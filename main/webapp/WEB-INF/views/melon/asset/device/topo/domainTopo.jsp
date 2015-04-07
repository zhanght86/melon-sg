<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>


<img src=<c:url value="/statics/themes/default/melon/images/topo.jpg"/> border="0" usemap="#url" style="width:920px;height:400px;" />

<map name="url">
	<area id="_1" shape="rect" coords="0,0,165,240" href="listByDomain?rootId=222" alt="1" />
	<area id="_2" shape="rect" coords="165,0,308,240" href="listByDomain?rootId=221" alt="2" />
	<area id="_3" shape="rect" coords="308,0,475,240" href="listByDomain?rootId=220" alt="3" />
	<area id="_4" shape="rect" coords="475,0,630,240" href="listByDomain?rootId=223" alt="4" />
	<area id="_5" shape="rect" coords="630,0,780,240" href="5.jsp" alt="5" />
	<area id="_6" shape="rect" coords="780,0,910,240" href="6.jsp" alt="6" />
</map>
