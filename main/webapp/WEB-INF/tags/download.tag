<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="empty"  description="文件下载控件" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="businessId"  rtexprvalue="true" required="true" description="业务主键，用于标识此文件属于什么业务"%>
<c:url value="/system/file/show/${businessId}" var="downloadUrl"/>
<iframe src="${downloadUrl}" style="width:100%"   frameborder="0">
</iframe>