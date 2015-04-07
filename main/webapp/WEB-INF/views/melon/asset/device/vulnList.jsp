<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<link rel="stylesheet" href="<mh:theme code='jquery.fileupload.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.fileupload.js'/>"></script>




<form action="<c:url value="/asset/vuln/importSecondStep"/>" method="post">
弱点导入数据预览
<table id="tabDb" class="table table-bordered">
	 <thead>
          <tr>
            <th class="select"><input type="checkbox" id="selectAll"/></th>
            <th width="120">资产名称</th>
            <th>ip地址</th>
            <th>以导入弱点数量</th>
            <th>高严重性弱点数量 </th>
          </tr>
        </thead>
        <tbody>
         <c:if test="${vulnfoundlist != null}">
        <c:forEach var="vuln" items="${vulnfoundlist}" varStatus="status">
          <tr readonly="true">
            <td class="select"><input type="checkbox" name="checkBoxes" value='<c:out value="${vuln[0]}"/>'/></td>
            <td><c:out value="${vuln[4]}"/></td>
            <td><c:out value="${vuln[1]}"/></td>
            <td><c:out value="${vuln[3]}"/></td>
            <td><div class="progress"><c:out value="${vuln[5]}"/></div><c:out value="${vuln[2]}"/></td>
          </tr>
        </c:forEach>
      </c:if>
        </tbody>
</table>

<a id="btnBack" class="btn btn-default" href="javascript:void(0);" style="float:right;">
	<fmt:message key="button.cancel"/>
</a>
<a id="btnBack" class="btn btn-default" href="javascript:checkForm(0);" style="float:right;">
	<fmt:message key="button.sure"/>
</a>
</form>
</div>

<script type="text/javascript">
	var assetIds ="";//存放每个ip选择的资产
	var deviceid ="";//保存选择的弱点
</script>