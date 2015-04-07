<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>



<link rel="stylesheet"
	ng-href="<c:url value="/statics/kibana/css/bootstrap.light.min.css"/>"
	href="<c:url value="/statics/kibana/css/bootstrap.light.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/statics/kibana/css/bootstrap-responsive.min.css"/>">

<link rel="stylesheet"
	href="<c:url value="/statics/kibana/css/font-awesome.min.css"/>">


<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript"
	src="<mh:theme code='elasticsearch.jquery.js'/>"></script>

<script type = "text/javascript">
				function toggle_details(e){
						var array = $("tr[allt=yyy]");
						$.each(array,function(i,item){
										console.info(item);
										if($(item).attr("id")!=e){
												$(item).hide();
										}
						})
						if($("#"+e).is(":hidden")){
								$("#"+e).show();	
							}else{
								$("#"+e).hide();
						}
					}
</script>

<div class="panel-content" 
	style="min-height: 350px;">
	<style>
.table-doc-table {
	margin-left: 0px !important;
	overflow-y: auto;
}

.table-sidebar {
	width: 200px;
	display: table-cell;
	padding-right: 10px;
}

.table-main {
	width: 100%;
	display: table-cell;
}

.table-container {
	display: table;
	height: 100px;
	width: 100%;
	table-layout: fixed;
}

.table-fieldname {
	white-space: nowrap;
}

.table-fieldname a {
	word-wrap: break-word;
	white-space: normal;
}

.table-details {
	table-layout: fixed;
}

.table-details-field {
	width: 200px;
}

.table-details-action {
	width: 60px;
	text-align: center;
}

.table-details-value {
	
}

.table-field-value {
	white-space: pre-wrap;
}

.table-facet {
	padding: 10px;
	border: 1px solid #666;
}
</style>
	<div class="table-container">
		<div 
			class="table-doc-table">
			<div style="min-height: 350px; overflow-y: auto">
				<table class="table-hover table table-condensed" style="font-size: 9pt;">
					<thead ng-show="panel.header">
						<tr>
							<!-- ngRepeat: field in panel.fields -->
							<th style="white-space: nowrap"
								class="ng-scope">id</th>
							<th style="white-space: nowrap"
								class="ng-scope">rulename</th>
							<th style="white-space: nowrap"
								class="ng-scope">id</th>
							<th style="white-space: nowrap"
								class="ng-scope">level</th>
							<th style="white-space: nowrap"
								class="ng-scope">occureTime</th>
							<th style="white-space: nowrap"
								class="ng-scope">status</th>
							<th style="white-space: nowrap"
								class="ng-scope">repeatNumber</th>
						</tr>
					</thead>
					<tbody class="ng-scope odd">
						<tr onClick="toggle_details('x11')" class="pointer">
							<td >
								1418100219251029
							</td>
							<td>
								似扫描行为检测
							</td>
							<td>
								似扫描行为检测
							</td>
							<td>
								似扫描行为检测
							</td>
							<td>
								似扫描行为检测
							</td>
							<td>
								似扫描行为检测
							</td>
							<td>
								似扫描行为检测
							</td>
							
						</tr>
						<tr  id="x11" allt="yyy" class="ng-scope">
							<td colspan="7" >
								<table
									class="table table-bordered table-condensed table-details ng-scope"
									>
									<thead>
										<tr>
											<th class="table-details-field">Field</th>
											<th class="table-details-value">Value</th>
										</tr>
									</thead>
									<tbody>
										<!-- ngRepeat: (key,value) in event.kibana._source track by $index -->
										<tr>
											<td style="word-wrap: break-word" >@timestamp</td>
											<td style="white-space: pre-wrap; word-wrap: break-word">2014-12-09T04:43:39.251Z</td>
										</tr>
										<tr>
											<td style="word-wrap: break-word" >@timestamp</td>
											<td style="white-space: pre-wrap; word-wrap: break-word">2014-12-09T04:43:39.251Z</td>
										</tr>
									</tbody>
								</table>
								</td>
						</tr>
					</tbody>
					<tbody class="ng-scope">
						<tr onClick="toggle_details('x12')" class="pointer">
							<td >
								1418100219251029
							</td>
							<td>
								似扫描行为检测
							</td>
							<td>
								似扫描行为检测
							</td>
							<td>
								似扫描行为检测
							</td>
							<td>
								似扫描行为检测
							</td>
							<td>
								似扫描行为检测
							</td>
							<td>
								似扫描行为检测
							</td>
							
						</tr>
							<tr  id="x12" allt="yyy" class="ng-scope">
							<td colspan="7" >
								<table
									class="table table-bordered table-condensed table-details ng-scope"
									>
									<thead>
										<tr>
											<th class="table-details-field">Field</th>
											<th class="table-details-value">Value</th>
										</tr>
									</thead>
									<tbody>
										<!-- ngRepeat: (key,value) in event.kibana._source track by $index -->
										<tr>
											<td style="word-wrap: break-word" >@timestamp</td>
											<td style="white-space: pre-wrap; word-wrap: break-word">2014-12-09T04:43:39.251Z</td>
										</tr>
										<tr>
											<td style="word-wrap: break-word" >@timestamp</td>
											<td style="white-space: pre-wrap; word-wrap: break-word">2014-12-09T04:43:39.251Z</td>
										</tr>
									</tbody>
								</table>
								</td>
						</tr>
						<!-- ngIf: event.kibana.details -->
					</tbody>
				</table>
				
			</div>
		</div>
	</div>
</div>

<script>
	var array = $("tr[allt=yyy]");
	array.hide();
</script>
