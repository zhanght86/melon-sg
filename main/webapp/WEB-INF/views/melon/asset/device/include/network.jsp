<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>
<%--@Author 甘焕--%>
<mh:section id="network">
	
	<jsp:attribute name="title">
		<fmt:message key="asset.device.net" />
	</jsp:attribute>
	
	<jsp:attribute name="body">
		<div class="col-xs-8 col-xs-offset-2 form-singlon">
		
			<%--是否虚拟机 --%>
			<div class="form-group" >
				<sf:label path="virtual" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.device.virtual" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:dictionary var="virtualItem" key="bool" />
					<sf:radiobuttons path="virtual" items="${virtualItem}" />
					<sf:errors path="virtual" />
				</div>
			</div>
	
			<%--虚拟机宿主  --%>
			<div class="form-group" id="master" ${device.virtual?'' : 'style="display:none;"'}>
				<sf:label path="masterId" cssClass="col-xs-3 control-label">
					<fmt:message key="hint.required" />
					<fmt:message key="asset.device.masterId" />
				</sf:label>
				<div class="col-xs-9 form-field">
				     <sf:input path="masterId" cssClass="form-control" maxlength="10" />
					 <hh:select id="masterId" onceUrl="asset/device/findByOrgan" />
					<sf:errors path="masterId" />
				</div>
			</div>
			
		
			<%--是否具有ip--%>
			<div class="form-group">
				<sf:label path="hasIp" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.device.hasIp" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:dictionary var="hasIpItem" key="bool" />
					<sf:radiobuttons path="hasIp" items="${hasIpItem}" />
					<sf:errors path="hasIp" />
				</div>
			</div>
			
			<div class="form-group" id="ipAddress" ${device.hasIp?'' : 'style="display:none;"'} >
				<div class="col-xs-12 form-editor form-field">
					<table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th colspan="4">IP列表</th>
							</tr>
							<tr>
								<th style="width:27px;">#</th>
								<th style="width:210px;">IP地址</th>
								<th style="width:200px;">MAC地址</th>
								<th style="width:80px;">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${device.ips}" var="ip" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>
										<input type="text" name="ipv4" value="${ip.ipv4}" class="form-control" style="width:180px;">
									</td>
									<td>
										<input type="text" name="mac" value="${ip.mac}" class="form-control" style="width:180px;">
									</td>
									<td>
										<div class="btn-group">
											<button type="button" class="btn btn-default btn-sm btn-plus" >
												 <span class="glyphicon glyphicon-plus"></span>
											</button>
											<a href="javascript:void(0);" class="btn btn-default btn-sm btn-minus" id="btn-minus">
												 <span class="glyphicon glyphicon-minus"></span>
											</a>
										</div>
									</td>
								</tr>
							</c:forEach>
								<tr style="display:none;" id="ipRowTmpl">
									<td></td>
									<td>
										<input type="text" name="ipv4" value="" class="form-control" style="width:180px;">
									</td>
									<td>
										<input type="text" name="mac" value="" class="form-control" style="width:180px;">
									</td>
									<td>
										<div class="btn-group">
											<a href="javascript:void(0);" class="btn btn-default btn-sm btn-plus">
												 <span class="glyphicon glyphicon-plus"></span>
											</a>
											<a href="javascript:void(0);" class="btn btn-default btn-sm btn-minus">
												 <span class="glyphicon glyphicon-minus"></span>
											</a>
										</div>
									</td>
								</tr>
						</tbody>
					</table>
				</div>
			 </div>
			 
			<%--是否有操作系统 --%>
			<div class="form-group">
				<sf:label path="hasOs" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.device.whetherSys" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:dictionary var="virtualItem" key="whether" />
					<sf:radiobuttons path="hasOs" items="${virtualItem}" />
					<sf:errors path="hasOs" />
				</div>
			</div>
		
			<%--操作系统名称--%>
			<div class="form-group" id="os_name" ${device.hasOs?'' : 'style="display:none;"'}" >
				<sf:label path="osName" cssClass="col-xs-3 control-label">
					<fmt:message key="hint.required"/> 
					<fmt:message key="asset.device.handleSystem" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:dictionary var="osname" key="device.os" />
					<sf:select path="osName" items="${osname }" cssClass="form-control"/>
					<sf:errors path="osName" />
				</div>
			</div>
		</div>
	</jsp:attribute>
	
</mh:section>