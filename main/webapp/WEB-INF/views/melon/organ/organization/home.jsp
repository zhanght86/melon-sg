<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>


<div id="organMap" style="height:600px;">
	
</div>

<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		require(["chart/mcharts"], function(mcharts) {
			var configs = {
				el : "#organMap",
				theme : "blue",
				title : "组织机构",
				data : "<c:url value='/statics/json/datas-array.json'/>",
				ready : function() {
					this.defaults.series[0].data =[
		                {name:'广东',selected:true}
		            ];
		            this.onClick(function(params) {
						console.debug(params);
					});
				},
				seriesConfig : [{
					name : '人员分布',
					type : 'map',
					mapType: 'china',
					itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
				}]
			};
			var map = new mcharts.Map(configs);
		});
		
	});
</script>