<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="page-query-heading">
	<div id="chart" style="height:300px;display : none;"></div>
	<sf:form modelAttribute="pageQuery.searchForm" role="form" cssClass="form-horizontal form-query">
		<mh:query >
			<jsp:attribute name="buttons">
				<mh:button href="system/logger/list" class="btn btn-default"><fmt:message key="system.logger.more"/></mh:button>
				<mh:button href="system/logger/activeUser" class="btn btn-default"><fmt:message key="system.logger.ative"/></mh:button>
			</jsp:attribute>
			
			<jsp:attribute name="quickQuery">
				<%--快速查询摆放区 --%>
				<sf:label path="operator.username" cssClass="col-xs-4 control-label"><fmt:message key="system.logger.operator"/></sf:label>
				<div class="col-xs-8 form-field">
					<sf:input path="operator.username" cssClass="form-control"/>
				</div>
			</jsp:attribute>
		</mh:query>
	</sf:form>
</div>
<div class="page-query-body">
	<mh:grid id="logger"
			queryUrl="system/logger/listUser"
			dataType="ARRAY"
			showUrl="system/logger/showUser" 
			hasPages= "false"
			var="loggerConfig">
			<mh:col name="operator.id"  title="system.logger.operator" width="0" hidden="true"/>
			<mh:col name="operator.username" title="system.logger.operator"  width="20" />
			<mh:col name="counts" title="system.logger.counts"  width="20" />
			<mh:col name="endTime" title="system.logger.endTime"  width="30" />
			<mh:col name="startTime" title="system.logger.startTime"  width="30" />
	</mh:grid>
</div>
<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
//截取到页面的Ajax请求
$(document).ajaxSuccess(
	function(event, xhr, settings) {
		var queryPattern = /system\/logger\/listUser$/i,//正则表达式,匹配请求的地址
			chartView = $("#chart");
		if(queryPattern.test(settings.url)) {
			//将获取的xhr数据解析成数组格式
			var pageData = xhr.responseJSON,
				results = pageData.results;
			if($.isPlainObject(pageData)) {
				chartView.show();
				//准备图形
				require(["chart/mcharts"], function(mcharts) {
					var configs = {
						el : chartView,
						theme : "blue",
						title : "<fmt:message key='chart.logger.title'/>",
						rotate : false,
						data : results,
						dataZoom : false,
						ready : function() {
							if(results.length < 6) {
								this.defaults.series[0].barWidth = 50;
							}
						},
						seriesConfig : [{
							name : '<fmt:message key="system.logger.counts"/>',
							type : 'bar',
							category : "1",
							value : "2"
						}]
					};
					//生成线形趋势图
					new mcharts.Column(configs);
				});
			}
		}
	}
);
</script>
