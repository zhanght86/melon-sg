<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style type="text/css">
	.page-header{
		border-bottom:0px;
		margin-bottom:0px;
	}
	
	.asset-show-leftPic{	
		margin-top:48px;
		text-align: center;
	}
	.chart-form-show .row-tab .tab-content .right-image{
		position:absolute;
			left:600px;
			top:50px;
	}
	.chart-form-show .row-tab .tab-content .device-image{
		Filter:Alpha(opacity=30);
		-moz-opacity:0.3;
		opacity:0.3;
		height:128px;
		width:128px;
	}
	.page-header .attention{
		font-size:12px;
		color:#808080;
		margin-left:20px;
	} 
</style>
<sf:form modelAttribute="device" role="form" cssClass="form-horizontal chart-form-show">
	<sf:hidden path="id" />
	<sf:hidden path="domainIds" />
	<sf:hidden path="organId" />
	<sf:hidden path="organPath" />
	<sf:hidden path="typeId" />
	<sf:hidden path="typePath" />
	<sf:hidden path="createTime" />
	<sf:hidden path="chargerId" />

	<div class="page-header">
	  <h3 style="display: inline-block;"><mh:value path="name" /></h3>
	  <mh:attention businessId="${device.id}" type="1"/>
	</div>
	<div class="row">
		<div class="col-xs-4">
			<div id="alarm-state" class="asset-show-leftPic" style="height:200px;">
				<img alt="${device.name}" src="<mh:theme code='asset.imgPath'/>${imgName}" id="imgName"/>
				<div>
					<a href="<c:url value='/asset/type/show/${device.typeId }'/>" style="font-weight: bold;">
						<mh:value path="typeName" />
					</a>
					<mh:dictionary var="usingItem" key="usingState" />
					<c:choose>
						<c:when test="${device.using == 5}">
							<span style="color:#f55549;">
								【<mh:value path="using" items="${usingItem}" />】
							</span>
						</c:when>
						<c:otherwise>
							<span style="color:#0b8f63;">
								【<mh:value path="using" items="${usingItem}" />】
							</span>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		
		<div class="col-xs-8" style="padding-right:30px;">
			<div class="form-group">
				<div class="form-button-panel">
					<div class="col-xs-12 form-buttons">
						<mh:button class="btn btn-default btn-sm" href="asset/device/contrast/${device.id}">
							<fmt:message key="button.contrast"/>
						</mh:button>
						
						
						
						<mh:button class="btn btn-default btn-sm"  href="asset/action/create/0?deviceId=${device.id }">
							<fmt:message key="button.updateStart"/>
						</mh:button>
						
						<mh:button type="link" id="btnBack" class="btn btn-default btn-sm" href="javascript:void(0);">
							<fmt:message key="button.back"/>
						</mh:button>
					</div>
				</div>
			</div>

			<%--名称--%>
			<div class="form-group">
				<sf:label path="name" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.abstract.name" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:value path="name" />
				</div>
			</div>

			<%--编码--%>
			<div class="form-group">
				<sf:label path="code" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.abstract.code" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:value path="code" />
				</div>
			</div>
			<%--所属单位--%>
			<div class="form-group">
				<sf:label path="organName" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.abstract.organName" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<a href="<c:url value='/organ/organization/show/${device.organId }'/>">
						<mh:value path="organName" />
					</a>
				</div>
			</div>

			<%--所属安全域--%>
			<div class="form-group">
				<sf:label path="domainNames" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.abstract.domainName" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<c:set var="item" value="0" />
					<c:forEach items="${device.domainNames}" var="domainName" varStatus="deol">
						<p class="form-control-static" style="margin-right:10px;">
							<a href="<c:url value='/asset/group/show/${device.domainIds[deol.index]}'/>">
								${domainName} 
							</a>
						</p>
					</c:forEach>
				</div>
			</div>

			<%--类型--%>
			<div class="form-group">
				<sf:label path="typeName" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.assetType.root" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<a href="<c:url value='/asset/type/show/${device.typeId }'/>">
						<mh:value path="typeName" />
					</a>
				</div>
			</div>

			<%--负责人--%>
			<div class="form-group">
				<sf:label path="chargeName" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.abstract.chargeName" />
				</sf:label>
				<div class="col-xs-9 form-field">
						<a href="<c:url value='/security/account/show/${device.chargerId }'/>">
							<mh:value path="chargeName" />
						</a>
				</div>
			</div>

			<%--使用状态--%>
			<div class="form-group">
				<sf:label path="using" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.abstract.using" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:value path="using" items="${usingItem}" />
				</div>
			</div>
			<div class="form-group">
				<mh:quicknote businessId="${device.id}" type="1"/>
			</div>
			
			
			

		</div>
	</div>
	
	<!-- 关联信息 -->
	<div class="row row-tab">
		<ul class="nav nav-tabs" role="tablist">
			<li class="active"><a href="#basic" role="tab" data-toggle="tab"><fmt:message key="asset.device.basicInfo" /></a></li>
			<li><a href="#profile" id="assetAction" role="tab" data-toggle="tab"><fmt:message key="asset.device.operation" /></a></li>
			<li><a href="#messages" role="tab" data-toggle="tab"><fmt:message key="asset.device.alarm" /></a></li>
			<li><a href="#settings" role="tab" data-toggle="tab"><fmt:message key="asset.device.fragility" /></a></li>
		</ul>
		<div class="tab-content col-xs-12" style="margin-left:0px;padding:0px 80px;">
			<!-- 基本信息 -->
			<div class="tab-pane active" id="basic" > 
				<div role="form" cssClass="form-horizontal form-show">
				<mh:section id="net">
					<jsp:attribute name="title">
						<fmt:message key="asset.device.net" />
						<%-- 是虚拟机则显示宿主 --%>
						<c:if test="${device.virtual}">
							<a class="jqrow-action jqrow-action-show" href="<c:url value='/asset/device/show/${device.masterId}'/>" style="margin-left:64px;">
								<fmt:message key="asset.device.lookDada" />
							</a>
						</c:if>
					</jsp:attribute>
					<jsp:attribute name="body">
						<%--是否虚拟机 --%>
						<div class="form-group">
							<sf:label path="virtual" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.virtual" />
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:dictionary var="boolItem" key="bool" />
								<mh:value path="virtual" items="${boolItem}" />
								
							</div>
						</div>
						<%--是否具有ip--%>
						<div class="form-group">
							<sf:label path="hasIp" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.hasIp"/>
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:value path="hasIp" items="${boolItem }"/>
							</div>
						</div>
						<c:if test="${device.hasIp}">
							<div class="form-group">
								<sf:label path="hasIp" cssClass="col-xs-3 control-label">
									<fmt:message key="asset.device.IpAddress"/>
								</sf:label>
								<div class="col-xs-9 form-field">
									<c:forEach items="${device.ips }" var="ip">
										<div class="form-control-static">${ip.ipv4}</div><br/>
									</c:forEach>
								</div>
							</div>
	
							<div class="form-group">
								<sf:label path="hasIp" cssClass="col-xs-3 control-label">
									<fmt:message key="asset.device.mac"/>
								</sf:label>
								<div class="col-xs-9 form-field">
									<c:forEach items="${device.ips }" var="ip">
										<div class="form-control-static">${ip.mac}</div><br/>
									</c:forEach>
								</div>
							</div>
							
						</c:if>
						
						<%--物理环境 --%>
						<div class="form-group">
							<sf:label path="enviName" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.envi"/>
							</sf:label>
							<div class="col-xs-9 form-field">
								<a href="<c:url value='/asset/environment/show/${device.enviId }'/>">
									<mh:value path="enviName" />
								</a>
							</div>
						</div>
						
						<%--设备硬件配置 --%>
						<div class="form-group">
							<sf:label path="hardWare" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.hardWare" />
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:value path="hardWare" />
							</div>
						</div>
						
						<%--主要软件 --%>
						<div class="form-group">
							<sf:label path="softWare" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.softWare" />
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:value path="softWare" />
							</div>
						</div>
						
						<%--cia--%>
					 	<div class="form-group">
						    <sf:label path="CIAValue" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.cia"/>
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:dictionary key="deviceCIAValue" var="ciav"/>
								<mh:value path="CIAValue" items="${ciav }"/>
							</div>
						</div>
						
						<%--设备维护人--%>
					 	<div class="form-group">
						    <sf:label path="keepPersonName" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.keepPersonName"/>
							</sf:label>
							<div class="col-xs-9 form-field">
								<a href="<c:url value='/security/account/show/${device.keepPersonId }'/>">
									<mh:value path="keepPersonName"/>
								</a>
							</div>
						</div>
						<%--设备使用人--%>
					 	<div class="form-group">
						    <sf:label path="usePersonName" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.usePersonName"/>
							</sf:label>
							<div class="col-xs-9 form-field">
								<a href="<c:url value='/security/account/show/${device.usePersonId }'/>">
									<mh:value path="usePersonName"/>
								</a>
							</div>
						</div>
			
						
						<%--备份设备 --%>
						<div class="form-group">
							<sf:label path="deviceId" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.deviceId" />
							</sf:label>
							<div class="col-xs-9 form-field">
								<c:if test="${!empty device.deviceId }">
									<a href="<c:url value='/asset/device/show/${device.deviceId }'/>">
									查看备份系统
									</a>
								</c:if>
								<c:if test="${empty device.deviceId }">
									无
								</c:if>
							</div>
						</div>
						
						<%-- 关联的设备 --%>
						<div class="form-group">
							<sf:label path="deviceIds" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.infoSystem.conn.device" />
							</sf:label>
							<div class="col-xs-9 form-field">
								<c:if test="${ !empty deviceNames}">
									<c:forEach items="${deviceNames }" var="des" varStatus="deol">
										<a href="<c:url value='/asset/device/show/${deviceIds[deol.index]}'/>">
											${des} 
										</a>
										<br/>
									</c:forEach>
								</c:if>
								<c:if test="${empty deviceNames}">
									无
								</c:if>
							</div>
						</div>
					</jsp:attribute>
				</mh:section>
				<mh:section id="produ">
					<jsp:attribute name="title">
						<fmt:message key="asset.device.produ"/>
					</jsp:attribute>
					<jsp:attribute name="body">
						<%--型号--%>
						<div class="form-group">
							<sf:label path="model" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.model"/>
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:value path="model"/>
							</div>
						</div>
					
						<%--厂商--%>
						<div class="form-group">
							<sf:label path="producer" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.producer"/>
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:value path="producer"/>
							</div>
						</div>
						
						<%--厂商联系方式--%>
						<div class="form-group">
							<sf:label path="producerTel" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.producerTel"/>
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:value path="producerTel"/>
							</div>
						</div>
						
						<%--出厂日期--%>
						<div class="form-group">
							<sf:label path="produceTime" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.produceTime"/>
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:value path="produceTime"/>
							</div>
						</div>
						
						<%--保修开始时间 --%>
						<div class="form-group">
							<sf:label path="catTime" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.catTime" />
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:value path="catTime"/>
							</div>
						</div>
			
						
						<div class="form-group">
							<sf:label path="deadline" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.device.deadline"/>
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:value path="deadline"/>
							</div>
						</div>
						
						
					</jsp:attribute>
				</mh:section>	
				
				<%-- 扩展属性--%>
				<mh:section id="dynamic">
					<jsp:attribute name="title">
						<fmt:message key="asset.abstract.dynamic" />
					</jsp:attribute>
					<jsp:attribute name="body">
							<%--是否有操作系统 --%>
							<div class="form-group">
								<sf:label path="hasOs" cssClass="col-xs-3 control-label">
									<fmt:message key="asset.device.whetherSys" />
								</sf:label>
								<div class="col-xs-9 form-field">
									<mh:dictionary var="virtualItem" key="bool" />
									<mh:value path="hasOs" items="${virtualItem }"/>
								</div>
							</div>
							<%-- 有操作系统则显示 --%>
							<c:if test="${device.hasOs}">
								<%--操作系统名称--%>
								<div class="form-group">
									<sf:label path="osName" cssClass="col-xs-3 control-label">
										<fmt:message key="asset.device.handleSystem"/>
									</sf:label>
									<div class="col-xs-9 form-field">
										<mh:dictionary var="osname" key="device.os" />
										<mh:value path="osName" items="${osname }"/>
									</div>
								</div>
							</c:if>
							<c:if test="${!empty assetFields }">
								<c:forEach items="${assetFields }" var="field">
									<div class="form-group">
										<sf:label path="" cssClass="col-xs-3 control-label">
											${field.label }
										</sf:label>
										<div class="col-xs-9 form-field">
											${field.defaultValue }
										</div>
									</div>
									
								</c:forEach>
							</c:if>
					</jsp:attribute>
				</mh:section>	
		<%-- 完成--%>
		<mh:section id="remarks">
			<jsp:attribute name="title">
				<fmt:message key="asset.abstract.remarks"/>
			</jsp:attribute>
			<jsp:attribute name="body">
				<div class="form-group">		
					<sf:label path="remarks" cssClass="col-xs-3 control-label">
							<fmt:message key="asset.abstract.remarks"/>
						</sf:label>
						<div class="col-xs-9 form-field">
							<mh:value path="remarks"/>
						</div>
					</div>
			</jsp:attribute>
		</mh:section>	
			</div>
			<div class="right-image">
				<img alt="${device.name}" src="<mh:theme code='asset.imgPath'/>${imgName}" class="device-image" id="imgNames"/>
			</div>
		</div>
			<!-- 趋势分析 -->
			<div class="tab-pane" id="profile">
			
			</div>
			
			<!-- 处理记录 -->
			<div class="tab-pane" id="messages">
			
			</div>
			
			<!-- 知识库 -->
			<div class="tab-pane" id="settings">
			
			</div>
		</div>
	</div>

</sf:form>

<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
$(document).ready(function() {
	var actionUrl = "<c:url value='/asset/action/list/?deviceId=${device.id}'/>";
	$("#btnBack").on("click", function(event) {
		history.back();
        event.preventDefault();
	});

	//运维情况
	$("#assetAction").on("click" , function(event){
		$("#profile").load(actionUrl); //加载修改页面
	});
	

});
</script>

