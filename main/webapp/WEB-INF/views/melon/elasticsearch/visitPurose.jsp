<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<link href="<c:url value="/statics/scripts/echart/css/echartsHome.css"/>" >
	<link rel="stylesheet" href="<c:url value="/statics/kibana/css/bootstrap.light.min.css"/>">
<link rel="stylesheet" href="<c:url value="/statics/scripts/echart/css/chartTalbe.css"/>" >
<div id="loadings">loading......</div>
<div>
由IDS、天眼等可以发现端口扫描行为的设备日志确定端口扫描源IP及目的IP（在syslog中获取category等于信息刺探类）
展现最近一段时间（60分钟）扫描源及其访问的目的IP，未包含目的IP，默认为“未知”，点中目的IP后，显示此目的IP被此源IP访问的端口列表（在netflow中获取）
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
<div id="chart" style="height:900px;"></div>
<jsp:include page="tableInfo.jsp" />  
<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='elasticsearch.jquery.js'/>"></script>
<script type="text/javascript">
		$("#loadings").dialog({
			title:"正在加载数据",
			modal : true,
			draggable : false,
			resizable : false,
			autoOpen : true,
			height : 250,
			width : 400
		});
		function searchLasEs(syslog,lasNet,from,to){
			require(["chart/mcharts"], function(mcharts) {
				chatTable.from = from;
				chatTable.to = to;
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
							                        "query":"category:\"/信息刺探类\""
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
							type:lasNet,
							body: {
								 "facets": {
									    "terms": {
									      "terms": {
									        "field": "dAddr",
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
									                        "query":"sAddr:\""+item.term+"\""
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
							var array = res.facets.terms.terms;
							$.each(array,function(i,em){
								jsonArray.nodes.push({"category":1,"name":em.term,"value":em.count});
								jsonArray.links.push({"source":item.term,"target":em.term,"hight":1});
							})
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
							title : "网络扫描",
							data : JSON.parse(JSON.stringify(jsonArray)),
							legends : ["源ip", "目的ip"],
							ready : function() {
								//this.defaults.xAxis[0].boundaryGap = true;
								//console.debug(this.defaults);
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
							var titles;
							
							if(params.seriesName=='目的ip'){
								var links = jsonArray.links;
								
								chatTable.parmasChat.push({name:"dAddr",value:params.name});
								var sAddr ="";
								$.each(links,function(i,e){
									if(e.target==params.name){
										sAddr = sAddr+ "\""+e.source+"\",";
									}
								})
								if(sAddr.length>0){
									
									chatTable.parmasChat.push({name:"sAddr",value:"("+sAddr+"\"1\")"});
								}
								log = "las-netflow";
								titles = ['receptTime','sAddr','sPort','dAddr','dPort','protocol'];
							}else{
								chatTable.parmasChat.push({name:"category",value:"\"/信息刺探类\""});
								chatTable.parmasChat.push({name:"sAddr",value:params.name});
								titles = ['occurTime','devAddr','category','eventType','sAddr','dAddr','dPort','protocol'];
							}
							chatTable.index = log;
							chatTable.searchChat(log,titles);
						});
						
					}
				}
				)
				
			})
		}
</script>
<script src="<c:url value="/statics/scripts/echart/js/table.js"/>"></script>
