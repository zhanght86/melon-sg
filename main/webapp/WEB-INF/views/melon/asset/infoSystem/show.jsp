<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

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


<mh:dictionary var="usingItem" key="usingState" />
<sf:form modelAttribute="infoSystem" role="form" cssClass="form-horizontal chart-form-show">
		<sf:hidden path="id"/>
		<sf:hidden path="organId"/>
		<sf:hidden path="organPath"/>
		<sf:hidden path="typeId"/>
		<sf:hidden path="typePath"/>
		<sf:hidden path="createTime"/>

	<div class="page-header">
	  <h3 style="display: inline-block;"><mh:value path="name" /></h3>
		<mh:attention businessId="${infoSystem.id}" type="2"/>
	</div>
	
	<div class="row">
		<div class="col-xs-4">
			<div id="alarm-state" class="asset-show-leftPic" style="height:200px;">
				
				<img alt="${infoSystem.name}" src="<mh:theme code='asset.imgPath'/>${imgName}" id="imgName"/>
				<div>
					<a href="<c:url value='/asset/type/show/${infoSystem.typeId }'/>" style="font-weight: bold;">
						<mh:value path="typeName" />
					</a>
					<c:choose>
						<c:when test="${infoSystem.using == 5}">
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
						
						<mh:button class="btn btn-default btn-sm" href="asset/infoSystem/contrast/${infoSystem.id}">
							<fmt:message key="button.contrast"/>
						</mh:button>
						
						<mh:button class="btn btn-default btn-sm"  href="asset/action/create/1?deviceId=${infoSystem.id }">
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
					<a href="<c:url value='/organ/organization/show/${infoSystem.organId }'/>">
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
					<c:forEach items="${infoSystem.domainNames}" var="domainName" varStatus="deol">
						<p class="form-control-static" style="margin-right:10px;">
							<a href="<c:url value='/asset/group/show/${infoSystem.domainIds[deol.index]}'/>">
								${domainName} 
							</a>
						</p>
					</c:forEach>
				</div>
			</div>

			<%--类型--%>
			<div class="form-group">
				<sf:label path="typeName" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.infoSystem.type" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<a href="<c:url value='/asset/type/show/${infoSystem.typeId }'/>">
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
						<a href="<c:url value='/security/account/show/${infoSystem.chargerId }'/>">
							<mh:value path="chargeName" />
						</a>
				</div>
			</div>
			
			<%--访问地址--%>
			<div class="form-group">
				<sf:label path="url" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.infoSystem.url" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<a href="http://${infoSystem.url}" target="_blank" ><mh:value path="url"/></a>
				</div>
			</div>
			
			<%--上线时间--%>
			<div class="form-group">
				<sf:label path="onlineTime" cssClass="col-xs-3 control-label">
					<fmt:message key="asset.infoSystem.onlineTime" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<mh:value path="onlineTime" />
				</div>
			</div>
			
			<%--安全等级--%>
				<div class="form-group">
					<sf:label path="safeLeven" cssClass="col-xs-3 control-label">
						<fmt:message key="asset.infoSystem.safeLeven" />
					</sf:label>
					<div class="col-xs-9 form-field">
						<mh:dictionary key="info.safeLeven" var="leven"/>	
						<mh:value path="safeLeven" items="${leven }" />			
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
				<mh:quicknote businessId="${infoSystem.id}" type="2"/>
			</div>
			

		</div>
	</div>
	
	<!-- 关联信息 -->
	<div class="row row-tab">
		<ul class="nav nav-tabs" role="tablist">
			<li class="active"><a href="#basic" role="tab" data-toggle="tab"><fmt:message key="asset.device.basicInfo" /></a></li>
			<li><a href="#profile"  id="assetAction" role="tab" data-toggle="tab"><fmt:message key="asset.device.operation" /></a></li>
			<li><a href="#messages" role="tab" data-toggle="tab"><fmt:message key="asset.device.alarm" /></a></li>
			<li><a href="#settings" role="tab" data-toggle="tab"><fmt:message key="asset.device.fragility" /></a></li>
		</ul>
		<div class="tab-content col-xs-12" style="margin-left:0px;padding:0px 80px;">
			<!-- 基本信息 -->
			<div class="tab-pane active" id="basic" >
			
				<div role="form" cssClass="form-horizontal form-show">
				<mh:section id="net">
				
					<jsp:attribute name="title">
						<fmt:message key="asset.infoSystem.duty" />
					</jsp:attribute>
					<jsp:attribute name="body">
					
						<%--是否关键--%>
						<div class="form-group">
							<sf:label path="ishinge" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.infoSystem.ishinge" />
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:dictionary var="hinge" key="bool" />
								<mh:value path="ishinge" items="${hinge}" />
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
										<br />
									</c:forEach>
								</c:if>
								<c:if test="${empty deviceNames}">
									无
								</c:if>
							</div>
						</div>
						
						<%-- 业务类型
						<div class="form-group">
							<sf:label path="sysType" cssClass="col-xs-3 control-label">
								<fmt:message key="db.sysInfo.busType" />
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:dictionary var="sysType" key="sysType"/>
								<mh:value path="sysType" items="${sysType }"/>
							</div>
						</div> --%> 
						<%--服务范围 
						<div class="form-group">
							<sf:label path="serScope" cssClass="col-xs-3 control-label">
								<fmt:message key="db.sysInfo.netScope" />
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:dictionary var="serScope" key="serScope"/>
								<mh:value path="serScope" items="${serScope }"/>
							<sf:errors path="serScope" />
							</div>
						</div>--%>
						
						<%--服务对象
						<div class="form-group">
							<sf:label path="serObject" cssClass="col-xs-3 control-label">
								<fmt:message key="db.sysInfo.serObject" />
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:dictionary var="serObject" key="serObject"/>
								<mh:value path="serObject" items="${serObject }"/>
							</div>
						</div> --%> 
								
						<%--覆盖范围
						<div class="form-group">
							<sf:label path="netScope" cssClass="col-xs-3 control-label">
								<fmt:message key="db.sysInfo.netScope" />
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:dictionary var="netScope" key="netScope"/>
								<mh:value path="netScope" items="${netScope }"/>
							</div>
						</div>--%> 
								
					
						<%--网络性质 
						<div class="form-group">
							<sf:label path="netType" cssClass="col-xs-3 control-label">
								<fmt:message key="db.sysInfo.netType" />
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:dictionary var="netType" key="netType"/>
								<mh:value path="netType" items="${netType }"/>
							</div>
						</div>--%> 
						
						
						<%--系统互联情况
						<div class="form-group">
							<sf:label path="connType" cssClass="col-xs-3 control-label">
								<fmt:message key="asset.infoSystem.connType" />
							</sf:label>
							<div class="col-xs-9 form-field">
								<mh:dictionary var="connType" key="connType"/>
								<mh:value path="connType" items="${connType }"/>
							</div>
						</div>--%>
					</jsp:attribute>
				</mh:section>
				
				<%-- 扩展属性--%>
				<mh:section id="dynamic">
					<jsp:attribute name="title">
						<fmt:message key="asset.abstract.dynamic" />
					</jsp:attribute>
					<jsp:attribute name="body">
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
			<img alt="${infoSystem.name}" src="<mh:theme code='asset.imgPath'/>${imgName}" class="device-image" id="imgNames"/>
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
	var actionUrl = "<c:url value='/asset/action/list/?deviceId=${infoSystem.id}'/>";
	
	$("#btnBack").on("click", function(event) {
		history.back();
        event.preventDefault();
	});
	
	//运维情况
	$("#assetAction").on("click" , function(event){
		$("#profile").load(actionUrl); //加载修改页面
	});
	
	//关注人员列表 
	$("#detailUserAtten").load("<c:url value='/home/attention/listUser?type=2&businessId=${infoSystem.id}'/>",function(){
		
	});
	
});
</script>

