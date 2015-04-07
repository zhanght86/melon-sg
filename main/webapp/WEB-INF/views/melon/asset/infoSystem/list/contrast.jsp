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
			    	<a href="<c:url value='/asset/infoSystem/show/${findByIds[0].id }'/>">
			    	${findByIds[0].name }
			    	</a>	
			    </fmt:param>
			    <fmt:param>
				    <a href="<c:url value='/asset/infoSystem/show/${findByIds[1].id }'/>">
				    	${findByIds[1].name }
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
			<c:forEach items="${findByIds }" var="sys">
				<td>
					<a href="<c:url value='/asset/infoSystem/show/${sys.id }'/>">
						${sys.name }
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
			<c:forEach items="${findByIds }" var="sys">
				<td>${sys.code }</td>
			</c:forEach>
		</tr>	
		<tr>
			<td>
				<label>
					<fmt:message key="asset.abstract.organName" />
				</label>
			</td>
			<c:forEach items="${findByIds }" var="sys">
				<td>
					<a href="<c:url value='/organ/organization/show/${sys.organId }'/>">
						${sys.organName }
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
			<c:forEach items="${findByIds }" var="sys">
				<td>
					<c:forEach items="${sys.domainNames}" var="domainName" varStatus="deol">
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
			<c:forEach items="${findByIds }" var="sys">
				<td>
				<a href="<c:url value='/asset/type/show/${sys.typeId }'/>">
					${sys.typeName }
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
			<c:forEach items="${findByIds }" var="sys">
				<td>
				<a href="<c:url value='/security/account/show/${sys.chargerId }'/>">
					${sys.chargeName }
				</a>
				</td>
			</c:forEach>
		</tr>	
		<tr>
			<td>
				<label>
					<fmt:message key="asset.infoSystem.url" />
				</label>
			</td>
			<c:forEach items="${findByIds }" var="sys">
				<td>
				<a href="http://${sys.url}" target="_blank" >${sys.url }</a>
				</td>
			</c:forEach>
		</tr>	
		<tr>
			<td>
				<label>
					<fmt:message key="asset.infoSystem.onlineTime" />
				</label>
			</td>
			<c:forEach items="${findByIds }" var="sys">
				<td>${sys.onlineTime }</td>
			</c:forEach>
		</tr>	
		<tr>
			<td>
				<label>
					<fmt:message key="asset.abstract.using" />
				</label>
			</td>
			<c:forEach items="${findByIds }" var="sys">
				<td>
					<mh:dictionary key="usingState" value="${sys.using}"/>
				</td>
			</c:forEach>
		</tr>	
		<tr>
			<td>
				<label>
					<fmt:message key="db.sysInfo.busType" />
				</label>
			</td>
			<c:forEach items="${findByIds }" var="sys">
				<td>
					<mh:dictionary key="sysType" value="${sys.sysType}"/>
				</td>
			</c:forEach>
		</tr>		
		<tr>
			<td>
				<label>
					<fmt:message key="db.sysInfo.serScope" />
				</label>
			</td>
			<c:forEach items="${findByIds }" var="sys">
				<td>
					<mh:dictionary key="serScope" value="${sys.serScope}"/>
				</td>
			</c:forEach>
		</tr>		
		<tr>
			<td>
				<label>
					<fmt:message key="db.sysInfo.serObject" />
				</label>
			</td>
			<c:forEach items="${findByIds }" var="sys">
				<td>
					<mh:dictionary key="serObject" value="${sys.serObject}"/>
				</td>
			</c:forEach>
		</tr>		
		<tr>
			<td>
				<label>
					<fmt:message key="db.sysInfo.netScope" />
				</label>
			</td>
			<c:forEach items="${findByIds }" var="sys">
				<td>
					<mh:dictionary key="netScope" value="${sys.netScope}"/>
				</td>
			</c:forEach>
		</tr>		
		<tr>
			<td>
				<label>
					<fmt:message key="db.sysInfo.netType" />
				</label>
			</td>
			<c:forEach items="${findByIds }" var="sys">
				<td>
					<mh:dictionary key="netType" value="${sys.netType}"/>
				</td>
			</c:forEach>
		</tr>		
		<tr>
			<td>
				<label>
					<fmt:message key="asset.infoSystem.connType" />
				</label>
			</td>
			<c:forEach items="${findByIds }" var="sys" >
				<td>
					<mh:dictionary key="connType" value="${sys.connType}"/>
				</td>
			</c:forEach>
		</tr>
		<tr>
			<td>
					
				<label><fmt:message key="asset.assetField.create" /></label>
			</td>
			<td>
				<c:forEach items="${idFields }" var="idfileds">
					<div class="row">
						<div class="form-group">
							<label class="col-xs-5 control-label">
								${idfileds.label }
							</label>
							<div class="col-xs-7 form-field">
								${idfileds.defaultValue }
							</div>
						</div>
					</div>
				</c:forEach>
			</td>
			<td>
				<c:forEach items="${sysIdFields }" var="sysidfileds">
					<div class="row">
						<div class="form-group">
							<label class="col-xs-5 control-label">
								${sysidfileds.label }
							</label>
							<div class="col-xs-7 form-field">
								${sysidfileds.defaultValue }
							</div>
						</div>
					</div>
				</c:forEach>
			</td>		
		</tr>
		<tr>
			<td>
				<label>
					<fmt:message key="asset.abstract.remarks" />
				</label>
			</td>
			<c:forEach items="${findByIds }" var="sys">
				<td>${sys.remarks }</td>
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


