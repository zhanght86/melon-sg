<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
	<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<link href="<c:url value="/statics/scripts/echart/css/echartsHome.css"/>" >
	<link rel="stylesheet" href="<c:url value="/statics/kibana/css/bootstrap.light.min.css"/>">
<link rel="stylesheet" href="<c:url value="/statics/scripts/echart/css/chartTalbe.css"/>" >
<div id="loadings">loading......</div>
 <div style="height:100px;">
由IDS、天眼等可以发现端口扫描行为的设备日志确定端口扫描源IP及目的IP（在syslog中获取category等于信息刺探类）
展现最近一段时间（60分钟）扫描源及其访问的目的IP，未包含目的IP，默认为“未知”，点中目的IP后，显示此目的IP被此源IP访问的端口列表（在netflow中获取）
</div> 
    <!-- Fixed navbar -->
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
 <div id="main" class="main" style="height:600px;width: 900px;"></div>
      
 <jsp:include page="tableInfo.jsp" />         
</body>
    <script src="<c:url value="/statics/scripts/echart/js/echarts.js"/>"></script>
	<script type="text/javascript" src="<mh:theme code='elasticsearch.jquery.js'/>"></script>
	<script type="text/javascript">
		var option ;
		$("#loadings").dialog({
			title:"正在加载数据",
			modal : true,
			draggable : false,
			resizable : false,
			autoOpen : true,
			height : 250,
			width : 400
		});
		var nodes = [];
		var titles = [];
		var links = [];
		function searchLasEs(syslog,lasNet,from,to){
			nodes = [];
			titles = [];
			links = [];
			var client = chatTable.client;
			chatTable.from = from;
			chatTable.to = to;
			client.search({
				type:syslog,
				body: {
					 "facets": {
						    "terms": {
						      "terms": {
						        "field": "sAddr",
						        "size": 10000,
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
					nodes.push({name:item.term});
					titles.push(item.term);
					client.search({
						type:lasNet,
						body: {
							 "facets": {
								    "terms": {
								      "terms": {
								        "field": "dPort",
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
							nodes.push({name:em.term});
							links.push({source:item.term,target:em.term+'',weight:1,name:"访问"});
						})
						if(i==(arrays.length-1)){
							createChart();
						}
						/* $.each(item.group_by_daddr.buckets,function(i,em){
							jsonArray.nodes.push({"category":1,"name":em.key,"value":em.doc_count});
							jsonArray.links.push({"source":item.key,"target":em.key,"weight":em.doc_count});
						})  */
					});
				});
			})
		}
		
		function createChart(){
			if(nodes.length==0){
							chatTable.removeAllChild("main")
							$("#loadings").hide();
					    	$("#loadings").dialog("close");
					    	$("#main").append("<div style='text-align: center;margin-top: 200px;'><font color='red' size='300'>当前场景没有数据</font></div>");
					    	return ;
						}
			
			option = {
				    title : {
				        text: '端口扫描',
				        x:'right',
				        y:'bottom'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: function (params) {
				            if (params.indicator2) {    // is edge
				                return params.indicator + ' ' + params.name + ' ' + params.indicator2;
				            } else {    // is node
				                return params.name
				            }
				        }
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            restore : {show: true},
				            magicType: {show: true, type: ['force', 'chord']},
				            saveAsImage : {show: true}
				        }
				    },
				    legend: {
				        x: 'left',
				        data:titles
				    },
				    series : [
				        {
				            name: '端口扫描',
				            type:'chord',
				            sort : 'ascending',
				            sortSub : 'descending',
				            ribbonType: false,
				            radius: '60%',
				            itemStyle : {
				                normal : {
				                    label : {
				                    	show: true,
				                        rotate : true,
				                        textStyle: {
					                        color: '#333'
					                    }
				                    }
				                }
				            },
				            minRadius: 7,
				            maxRadius: 20,
				            // 使用 nodes links 表达和弦图
				            nodes: nodes,
				            links: links
				        }
				    ]
				};
			require.config({
		        paths: {
		            echarts: '<c:url value="/statics/scripts/echart/js"/>',
		            theme: '<c:url value="/statics/scripts/echart/js/theme/"/>'
		        }
		    });
			require(
			    [
			        'echarts',
			        'echarts/chart/chord',
			        'echarts/chart/force'
			    ],
			    function(ec){
			    	var myChart = ec.init(document.getElementById('main')); 
			    	$("#loadings").hide();
			    	$("#loadings").dialog("close")
			    	myChart.setOption(option); 
			    	myChart.on("click", function(param){
							  	
					    var log = "las-syslog";
					   	chatTable.parmasChat=[];
		
							var saddr ;
							var titles;
							if((param.name+"").indexOf('.')==-1){//表示是端口
								var sAddr ="";
								$.each(links,function(i,e){
									if(e.target==param.name){
										sAddr = sAddr+ "\""+e.source+"\",";
									}
								})
								if(sAddr.length>0){
									chatTable.parmasChat.push({name:"sAddr",value:"("+sAddr+"\"1\")"});
								}
								chatTable.parmasChat.push({name:"dPort",value:param.name+""});
								console.info(chatTable.parmasChat);
								log = "las-netflow";
								titles = ['receptTime','sAddr','sPort','dAddr','dPort','protocol'];
							}else{//表示是源IP:
								chatTable.parmasChat.push({name:"category",value:"\"/信息刺探类\""});
								chatTable.parmasChat.push({name:"sAddr",value:param.name});
								titles = ['occurTime','devAddr','category','eventType','sAddr','dAddr','dPort','protocol'];
							}
							chatTable.searchChat(log,titles);
			    		});
			    }
			);
			var ecConfig = require('echarts/config');
			
		}


	</script>

<script src="<c:url value="/statics/scripts/echart/js/table.js"/>"></script>
