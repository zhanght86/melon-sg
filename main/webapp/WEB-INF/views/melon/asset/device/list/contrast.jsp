<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>


<style>
	.fontbright{
		color: red;
	}
	.form-group .control-label{
		font-size:14px;
	}

</style>

<div class="page-query-body">
	<div class="page-header">
	  <h3 style="display: inline-block;">
	  	<fmt:message key="asset.contrast.view">
		    <fmt:param>
		    	<a href="<c:url value='/asset/device/show/${devices[0].id }'/>">
		    	${devices[0].name }
		    	</a>	
		    </fmt:param>
		    <fmt:param>
			    <a href="<c:url value='/asset/device/show/${devices[1].id }'/>">
			    	${devices[1].name }
			    </a>
		    </fmt:param>
		</fmt:message>
	  </h3>
	  <a id="btnBack" class="btn btn-default" href="javascript:void(0);" style="float:right;">
			<fmt:message key="button.repeatContrast"/>
	  </a>
	</div>
	
	<table class="table table-bordered" id="tab">
		<tr>
			<td>
				<label>
					<fmt:message key="asset.abstract.name" />
				</label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>
					<a href="<c:url value='/asset/device/show/${device.id }'/>">
						${device.name }
					</a>
				</td>
			</c:forEach>
		</tr>
		<tr>
			<td>
				<label>
					<fmt:message key="asset.abstract.code" />
				</label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>${device.code }</td>
			</c:forEach>
		</tr>	
		<tr>
			<td>
				<label>
					<fmt:message key="asset.abstract.organName" />
				</label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>
					<a href="<c:url value='/organ/organization/show/${device.organId }'/>">
						${device.organName }
					</a>
				</td>
			</c:forEach>
		</tr>	
		<tr>
			<td>
				<label>
					<fmt:message key="asset.abstract.domainName" />
				</label>
			</td>	
			<c:forEach items="${devices }" var="device">
				<td>
					<c:forEach items="${device.domainNames}" var="domainName" varStatus="deol">
						<p class="form-control-static" style="margin-right:10px;">
							<a href="<c:url value='/asset/group/show/${infoSystem.domainIds[deol.index]}'/>">
								${domainName} 
							</a>
						</p>
					</c:forEach>
				</td>
			</c:forEach>
		</tr>	
		<tr>
			<td>
				<label>
					<fmt:message key="asset.assetType.root" />
				</label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>
					<a href="<c:url value='/asset/type/show/${device.typeId }'/>">
						${device.typeName }
					</a>
				</td>
			</c:forEach>
		</tr>	
		<tr>
			<td>
				<label>
					<fmt:message key="asset.abstract.chargeName" />
				</label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>
				<a href="<c:url value='/security/account/show/${device.chargerId }'/>">
					${device.chargeName }
				</a>
				</td>
			</c:forEach>
		</tr>	
		<tr>
			<td>
				<label>
					<fmt:message key="asset.device.virtual" />
				</label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>
					<mh:dictionary key="bool" value="${device.virtual}"/>
					<c:if test="${device.virtual}">
						<a class="jqrow-action jqrow-action-show" href="<c:url value='/asset/device/show/${device.masterId}'/>" >
							<fmt:message key="asset.device.lookDada" />
						</a>
					</c:if>
				</td>
			</c:forEach>
		</tr>	
		
		<tr>
			<td>
				<label>
					<fmt:message key="asset.device.hasIp" />
				</label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>
					<mh:dictionary key="bool" value="${device.hasIp}"/>
				</td>
			</c:forEach>
		</tr>	
		<tr>
			<td>
				<label><fmt:message key="asset.device.IpAddress"/></label>
			</td>
			
			<c:forEach items="${devices }" var="device">
				<td>
					<c:forEach items="${device.ips }" var="ip">
						<div class="form-control-static">${ip.ipv4}</div><br/>
					</c:forEach>
				</td>
			</c:forEach>
			
		</tr>
				
		<tr>
			<td>
				<label>
					<fmt:message key="asset.device.model" />
				</label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>
					${device.model }
				</td>
			</c:forEach>
		</tr>	
		<tr>
			<td>
				<label>
					<fmt:message key="asset.device.producer" />
				</label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>
					${device.producer }
				</td>
			</c:forEach>
		</tr>		
		<tr>
			<td>
				<label>
					<fmt:message key="asset.device.producerTel" />
				</label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>
					${device.producerTel}
				</td>
			</c:forEach>
		</tr>		
		<tr>
			<td>
				<label>
					<fmt:message key="asset.device.produceTime" />
				</label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>
					${device.produceTime}
				</td>
			</c:forEach>
		</tr>		
		<tr>
			<td>
				<label>
					<fmt:message key="asset.device.whetherSys" />
				</label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>
					<mh:dictionary key="bool" value="${device.hasOs}"/>
				</td>
			</c:forEach>
		</tr>		
		<tr>
			<td>
				<label>
					<fmt:message key="asset.device.handleSystem" />
				</label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>
					<mh:dictionary key="device.os" value="${device.osName}"/>
				</td>
			</c:forEach>
		</tr>		
		<tr>
			<td>
				<label><fmt:message key="asset.assetField.create" /></label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>
					<c:forEach items="${device.field }" var="field">
						<div class="row">
							<div class="form-group">
								<label class="col-xs-5 control-label">
									${field.label }
								</label>
								<div class="col-xs-7 form-field">
									${field.defaultValue }
								</div>
							</div>
						</div>	
					</c:forEach>
				</td>
			</c:forEach>
		</tr>
		<tr>
			<td>
				<label>
					<fmt:message key="asset.abstract.remarks" />
				</label>
			</td>
			<c:forEach items="${devices }" var="device">
				<td>${device.remarks }</td>
			</c:forEach>
		</tr>		
	</table>
</div>

<script type="text/javascript">

	$("#btnBack").on("click", function(event) {
		history.back();
	    event.preventDefault();
	});

	/*
	 * 所有文档加载完成后注册
	 */
	window.onload = function() {
		//对比高亮
		doSelect();
	}
	
	function doSelect(){
		$("#tab tr").each(function(index,item){
			var tds = $(this).find("td"),objecOn,objecTw;
			objecOn = tds.eq(1).html();
			objecTw = tds.eq(2).html();
			if(objecOn !== objecTw){
				//TODO 此处设置不同字设置高亮
				//$(this).addClass("fontbright");
			}
		});
	}
	
	//鼠标滑动效果
	$("#tab tr").hover(function(){
	    $(this).addClass("ui-state-hover");
	},function(){
	    $(this).removeClass("ui-state-hover"); 
	});
	
	//选择效果 
	$("#tab tr").click(function(){
		$("#tab tr").removeClass("ui-state-highlight"); 
		$(this).addClass("ui-state-highlight");
	});
	
</script>


