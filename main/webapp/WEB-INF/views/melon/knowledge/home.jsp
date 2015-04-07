<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="portal-main">
	<div class="page-header">
	 	<p>
			<fmt:message key="organ.listOrgans.description"/>
		</p>
	</div>
	<div class="row">
		<div class="col-xs-4">
			<div class="melon-protal-dataArea" style="height:230px;">
				<fmt:message key="knowledge.type.threat">
					<fmt:param><strong class="userRate">${s1}</strong></fmt:param>
				</fmt:message><br/>
				<fmt:message key="knowledge.type.leak">
					<fmt:param><strong class="userRate">${s2}</strong></fmt:param>
				</fmt:message><br/>
				<fmt:message key="knowledge.type.attck">
					<fmt:param><strong class="userRate">${s3}</strong></fmt:param>
				</fmt:message><br/>
			</div>
			
			<a href="<c:url value='/knowledge/base/list/1'/>" class="left-charts-a">
				<fmt:message key='knowledge.type.threat.name'/>
			</a>
			<a href="<c:url value='/knowledge/base/list/2'/>" class="left-charts-a">
				<fmt:message key='knowledge.type.leak.name'/>
			</a>
			<a href="<c:url value='/knowledge/base/list/3'/>" class="left-charts-a" >
				<fmt:message key="knowledge.type.attck.name"/>
			</a>
		</div>
		<div class="col-xs-8">
			<div id="chart" style="height: 330px;"></div>
		</div>
	</div>
</div>
<div class="row" style="margin-right: -20px; margin-left: 0px;">
		<div class="col-sm-4 organ" style="width:800px;">
   			<div class="thumbnail">
   				<div class="caption">
					<h4>
						<fmt:message key="knowledge.base.count.desc">
					    	<fmt:param>5</fmt:param>
						</fmt:message>
					</h4>
					<div class="melon-portal-body">
						<ul class="list-group">
					      <c:forEach items="${list}" var="dl" begin="0" end="4">
					      	<li class="list-group-item">
					      		<a href="<c:url value='/knowledge/base/show/${dl[2]}'/>">${dl[0]}</a>
					      		<span class="badge pull-right">${dl[1]}</span>
					      	</li>
					      </c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-4 organ" style="width:800px;">
   			<div class="thumbnail">
				<div class="caption">
					<h4>
						<fmt:message key="knowledge.base.update.desc">
							<fmt:param>5</fmt:param>
						</fmt:message>
					</h4>
					<div class="melon-portal-body">
						<ul class="list-group">
					      <c:forEach items="${listUpdate}" var="tl" begin="0" end="4">
					      	<li class="list-group-item">
					      		<a href="<c:url value='/knowledge/base/show/${dl[2]}'/>">${tl[0]}</a>
					      		<span class="badge pull-right">${tl[1]}</span>
					      	</li>
					      </c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
</div>
<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
	
	$(document).ready(function() {
		require(["chart/mcharts"], function(mcharts) {
			
			
			//准备图形
			var configs = {
					el : "#chart",
					theme : "blue",
					title : "知识库数量统计图",
					rotate : false,
					data : [["威胁",'${s1}'],["漏洞",'${s2}'],["攻击",'${s3}']],
					dataZoom : false,
					ready : function() {
					},
					seriesConfig : [{
						name : '知识库',
						label : "0",
						type : 'pie',
						center : ['30%', 200],
					    radius : 100,
						stack : "group",
						category : "0",
						value : 1,
						itemStyle:{
					        normal:{
								label:{
								    show: true,
								    formatter: '{b} : {c}'
								},
								labelLine :{show:true}
				            }
				        }
					}]
				};
				var pie = new mcharts.Pie(configs);
		});
	});
</script>
