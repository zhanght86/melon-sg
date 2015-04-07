<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<div id="loadings">loading......</div>
<div style="height:100px;">
默认从ES中抽取最近60分钟的蠕虫事件type=5002或msg包含蠕虫 ，按照源IP事件计数，源IP与目的IP进行连线，连线为从syslog（或netflow）中获取的该源到目的地址，且目的端口为（69、135、139、445、1025、4444、5554、9996）的记录 
</div>
<div>
	开始时间：<input id="startDate" class="form-control" name="startDate" type="text" style="width:150px" maxlength="50"/>
	@<input id="startHH" type="text" value="00" style="width: 35px;" maxlength="2">:
	<input  id="startMM" type="text" value="00" style="width: 35px;" maxlength="2">:
	<input id="startSS" type="text" value="00" style="width: 35px;" maxlength="2">:
	结束时间：<input id="endDate" class="form-control" name="endDate" type="text" style="width:150px"  maxlength="50"/>
		@<input id="endHH" type="text" style="width: 35px;" maxlength="2">:
	<input id="endMM" type="text" style="width: 35px;" maxlength="2">:
	<input id="endSS" type="text" style="width: 35px;" maxlength="2">:
	<input type="button" value="查询" id="searchEsButton" onclick="chatTable.searchGetTime()">
	<hh:date id="startDate" maxDate="endDate"/>
	<hh:date id="endDate" />
</div>
<div id="chart" style="height:900px;text-align: center;"></div>
<link rel="stylesheet" href="<c:url value="/statics/kibana/css/bootstrap.light.min.css"/>">
 <link rel="stylesheet" href="<c:url value="/statics/scripts/echart/css/chartTalbe.css"/>">
 
 <jsp:include page="tableInfo.jsp" />     

<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='elasticsearch.jquery.js'/>"></script>
<script type = "text/javascript">
	
		function searchLasEs(syslog,lasNet,from,to){
			chatTable.from = from;
			chatTable.to = to;
			require(["chart/mcharts"], function(mcharts) {
				var jsonArray = {nodes:[],links:[]};
				var client = chatTable.client;
				client.search({
					type:syslog,
					body: {
						 "facets": {
							    "terms": {
							      "terms": {
							        "field": "sAddr",
							        "size": 1000,
							        "order": "count",
							        "exclude": []
							      },
							      "facet_filter": {
							        "fquery": {
							          "query": {
							            "filtered": {
							              "query": {
							                "bool": {
							                  "should": [
							                    {
							                      "query_string": {
							                        "query":"category:\"/恶意代码类\""
							                      }
							                    }
							                  ]
							                }
							              },
							              "filter": {
							                  "bool": {
							                    "must": [
							                      {
							                        "range": {
							                          "@timestamp": {
							                            "from": from,
							                            "to": to
							                          }
							                        }
							                      }
							                    ]
							                  }
							                }
							            }
							          }
							        }
							      }
							    }
							  },
							  "size": 0
						
				  }
				},function(err,res){
					if(!res){
						createChart();
					}
					var arrays = res.facets.terms.terms;
					if(arrays.length==0){
						createChart();
					}
					$.each(arrays,function(i,item){
						jsonArray.nodes.push({"category":0,"name":item.term,"value":item.count});
						client.search({
							type:'skyeye-tcpflow',
							body: {
								 "facets": {
									    "terms": {
									      "terms": {
									        "field": "dip",
									        "size": 50,
									        "order": "count",
									        "exclude": []
									      },
									      "facet_filter": {
									        "fquery": {
									          "query": {
									            "filtered": {
									              "query": {
									                "bool": {
									                  "should": [
									                    {
									                      "query_string": {
									                        "query":"sip:\""+item.term+"\""
									                      }
									                    }
									                  ]
									                }
									              },
									              "filter": {
									                  "bool": {
									                    "must": [
									                      {
									                        "range": {
									                          "@timestamp": {
									                            "from": from,
									                            "to": to
									                          }
									                        }
									                      }
									                    ]
									                  }
									                }
									            }
									          }
									        }
									      }
									    }
									  },
									  "size": 0
						   }
						},function(err,res){
							console.info(res);
							if(res){
								var array = res.facets.terms.terms;
								$.each(array,function(i,em){
									jsonArray.nodes.push({"category":1,"name":em.term,"value":em.count});
									jsonArray.links.push({"source":item.term,"target":em.term,"hight":1});
								})
							}
							if(i==(arrays.length-1)){
								createChart();
							}
							/* $.each(item.group_by_daddr.buckets,function(i,em){
								jsonArray.nodes.push({"category":1,"name":em.key,"value":em.doc_count});
								jsonArray.links.push({"source":item.key,"target":em.key,"weight":em.doc_count});
							})  */
						});
					})
					function createChart(){
						if(jsonArray.nodes.length==0){
							chatTable.removeAllChild("chart")
							$("#loadings").hide();
					    	$("#loadings").dialog("close");
					    	$("#chart").append("<div style='text-align: center;margin-top: 200px;'><font color='red' size='300'>当前场景没有数据</font></div>");
					    	return ;
						}
						var configs = {
							el : "#chart",
							theme : "macarons",
							title : "蠕虫传播 ",
							data : JSON.parse(JSON.stringify(jsonArray)),
							legends : ["源ip", "目的ip"],
							ready : function() {
								//this.defaults.xAxis[0].boundaryGap = true;
								//this.defaults.xAxis[0].axisLabel.rotate = 45;
							},
							seriesConfig : [{
								name : '邮件营销',
								type : 'force',
								categories : [{name: '源ip'},
											  {name: '目的ip'}]
							}]
						};
						$("#loadings").hide();
				    	$("#loadings").dialog("close");
						var force = new mcharts.Force(configs);
						
						
						force.onClick(function(params) {
							chatTable.parmasChat=[];
							var log = "las-syslog";
							if(params.seriesName=='目的ip'){
								var links = jsonArray.links;
								chatTable.parmasChat.push({name:"dip",value:params.name});
								var sAddr ="";
								$.each(links,function(i,e){
									if(e.target==params.name){
										sAddr = sAddr+ "\""+e.source+"\",";
									}
								})
								if(sAddr.length>0){
									chatTable.parmasChat.push({name:"sip",value:"("+sAddr+"\"1\")"});
								}
								log = "skyeye-tcpflow";
							}else{
								chatTable.parmasChat.push({name:"category",value:"\"/恶意代码类\""});
								chatTable.parmasChat.push({name:"sAddr",value:params.name});
							}
							chatTable.index = log;
							
							var titles = ['occurTime','devAddr','category','eventType','sAddr','dAddr','dPort','protocol'];
							if(params.seriesName=='目的ip'){
								titles = ['sip','sport','dip','dport','proto','uplink_length','downlink_length'];
							}
							chatTable.searchChat(log,titles);
						});
						
					}
				}
				)
				
			});
		};
</script>
 <script src="<c:url value="/statics/scripts/echart/js/table.js"/>"></script>
