<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
	<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

    
<link rel="stylesheet" href="<c:url value="/statics/kibana/css/bootstrap.light.min.css"/>">
<link href="<c:url value="/statics/scripts/echart/css/echartsHome.css"/>" >
 <link rel="stylesheet" href="<c:url value="/statics/scripts/echart/css/chartTalbe.css"/>" >
    
    <div id="loadings">loading......</div>
 <div style="height:100px;">
时间散点图，默认从ES中抽取最近一个月的恶意程序事件category=5，按照恶意程序的六种类型（计算机病毒、网络蠕虫、僵尸软件、木马、网页挂马、间谍软件）进行分类统计，并在时间散点图上显示。
每天每个类型统计一个值，纵坐标为该事件在当天的事件总数，圈大小为当天该事件对应的源IP数量。
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
<div id="main" class="main" style="height:600px;width: 900px;"></div>   
 <jsp:include page="tableInfo.jsp" />     
    
    <script src="<c:url value="/statics/scripts/echart/js/echarts.js"/>"></script>
    <script src="<c:url value="/statics/scripts/echart/js/codemirror.js"/>"></script>
	<script type="text/javascript" src="<mh:theme code='elasticsearch.jquery.js'/>"></script>
	<script type="text/javascript">
			var d = [];
			var d1 = [];
			var d2 = [];
			var d3 = [];
			var d4 = [];
			var d5 =[];
			var d6 = [];
			var max = 10;
			$("#loadings").dialog({
				title:"正在加载数据",
				modal : true,
				draggable : false,
				resizable : false,
				autoOpen : true,
				height : 250,
				width : 400
			});
			var client ;
			var ix = 0;
			var n = 0;
			 function a(arraya,dd,eventName){
				if(arraya.length==0){
					ix+=1;
					n++;
				}
				y=arraya.length;
				var x =false;
				 $.each(arraya,function(i,item){
						if(i==(arraya.length-1)){
							ix+=1;
							x=true;
						}
						searchSaddr(item,dd,eventName,x);
				 });
				 setTimeout(function(){
					 if(n==7){
							chatTable.removeAllChild("main")
							$("#loadings").hide();
					    	$("#loadings").dialog("close");
					    	$("#main").append("<div style='text-align: center;margin-top: 200px;'><font color='red' size='300'>当前场景没有数据</font></div>");
					    	
					    	return ;
						}	 
				 },2000);
				 
				 
			 }
			function searchSaddr(item,dd,eventName,i){
				 client.search({
					 type:"las-syslog",
					 body:{
						 "facets": {
							    "terms": {
							      "terms": {
							        "field": "sAddr",
							        "size": 50000,
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
							                        "query":"eventType:"+eventName
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
							                            "from": item.time,
							                            "to": item.time+(10*60*1000)
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
					 
					 if(max<item.count){
						 max = item.count;
					 }
					 var date = new Date(item.time);
					 dd.push([date,
		                        item.count,
		                        array.length+50
						         ]) 
			         if(ix==7&&i){
							setTimeout(createChart,2000);
							
						}
				 })
			}
			function searchLasEs(syslog,lasNet,from,to){
				max = 10;
				ix = 0;
				n=0;
				d = [];
				d1 = [];
				d2 = [];
				d3 = [];
				d4 = [];
				d5 =[];
				d6 = [];
				client = chatTable.client;
				chatTable.from = from;
				chatTable.to = to;	
				client.search({
				type:syslog,
				body: {
					  "facets": {
						    "a": {
						      "date_histogram": {
						        "field": "@timestamp",
						        "interval": "10m"
						      },
						      "global": true,
						      "facet_filter": {
						        "fquery": {
						          "query": {
						            "filtered": {
						              "query": {
						                "query_string": {
						                  "query": "eventType:计算机病毒"
						                }
						              },
						              "filter": {
						                  "bool": {
						                    "must": [
						                      {
						                        "range": {
						                          "@timestamp": {
						                            "from": chatTable.from,
						                            "to": chatTable.to
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
						    },
						    "b": {
							      "date_histogram": {
							        "field": "@timestamp",
							        "interval": "10m"
							      },
							      "global": true,
							      "facet_filter": {
							        "fquery": {
							          "query": {
							            "filtered": {
							              "query": {
							                "query_string": {
							                  "query":"eventType:网络蠕虫"
							                }
							              },
							              "filter": {
							                  "bool": {
							                    "must": [
							                      {
							                        "range": {
							                          "@timestamp": {
							                            "from": chatTable.from,
							                            "to": chatTable.to
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
							    },
							    "c": {
								      "date_histogram": {
								        "field": "@timestamp",
								        "interval": "10m"
								      },
								      "global": true,
								      "facet_filter": {
								        "fquery": {
								          "query": {
								            "filtered": {
								              "query": {
								                "query_string": {
								                  "query": "eventType:僵尸软件"
								                }
								              },
								              "filter": {
								                  "bool": {
								                    "must": [
								                      {
								                        "range": {
								                          "@timestamp": {
								                            "from": chatTable.from,
								                            "to": chatTable.to
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
						    ,
						    "d": {
							      "date_histogram": {
							        "field": "@timestamp",
							        "interval": "10m"
							      },
							      "global": true,
							      "facet_filter": {
							        "fquery": {
							          "query": {
							            "filtered": {
							              "query": {
							                "query_string": {
							                  "query": "eventType:木马"
							                }
							              },
							              "filter": {
							                  "bool": {
							                    "must": [
							                      {
							                        "range": {
							                          "@timestamp": {
							                            "from": chatTable.from,
							                            "to": chatTable.to
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
						    ,
						    "e": {
							      "date_histogram": {
							        "field": "@timestamp",
							        "interval": "10m"
							      },
							      "global": true,
							      "facet_filter": {
							        "fquery": {
							          "query": {
							            "filtered": {
							              "query": {
							                "query_string": {
							                  "query": "eventType:网页挂马"
							                }
							              },
							              "filter": {
							                  "bool": {
							                    "must": [
							                      {
							                        "range": {
							                          "@timestamp": {
							                            "from": chatTable.from,
							                            "to": chatTable.to
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
						    ,
						    "f": {
							      "date_histogram": {
							        "field": "@timestamp",
							        "interval": "10m"
							      },
							      "global": true,
							      "facet_filter": {
							        "fquery": {
							          "query": {
							            "filtered": {
							              "query": {
							                "query_string": {
							                  "query": "eventType:间谍软件"
							                }
							              },
							              "filter": {
							                  "bool": {
							                    "must": [
							                      {
							                        "range": {
							                          "@timestamp": {
							                            "from": chatTable.from,
							                            "to": chatTable.to
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
						    ,
						    "g": {
							      "date_histogram": {
							        "field": "@timestamp",
							        "interval": "10m"
							      },
							      "global": true,
							      "facet_filter": {
							        "fquery": {
							          "query": {
							            "filtered": {
							              "query": {
							                "query_string": {
							                  "query": "eventType:其他"
							                }
							              },
							              "filter": {
							                  "bool": {
							                    "must": [
							                      {
							                        "range": {
							                          "@timestamp": {
							                            "from": chatTable.from,
							                            "to": chatTable.to
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
					
				}},function(err,res){
						
						 var arraya = res.facets.a.entries;
						 var arrayb = res.facets.b.entries;
						 var arrayc = res.facets.c.entries;
						 var arrayd = res.facets.d.entries;
						 var arraye = res.facets.e.entries;
						 var arrayf = res.facets.f.entries;
						 var arrayg = res.facets.g.entries;
						a(arraya,d,"计算机病毒");
						a(arrayb,d1,"网络蠕虫");
						a(arrayc,d2,"僵尸软件");
						a(arrayd,d3,"木马");
						a(arraye,d4,"网页挂马");
						a(arrayf,d5,"间谍软件");
						a(arrayg,d6,"其他");
					})
			}
	function createChart(){
		var option = {
			    title : {
			        text : '时间坐标散点图',
			        subtext : 'dataZoom支持'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter : function (params) {
			            return params.seriesName + ' （'  + '类目' + params.value[0] + '）<br/>'
			                   + params.value[1] + ', ' 
			                   +(params.value[2]-50);
			        }
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    dataZoom: {
			        show: true,
			        start : 30,
			        end : 70
			    },
			    legend : {
			        data : ['计算机病毒','网络蠕虫','僵尸软件','木马','网页挂马','间谍软件','其他']
			    },
			    datadRange: {
			        min: 0,
			        max: 100,
			        orient: 'horizontal',
			        y: 30,
			        x: 'center',
			        //text:['高','低'],           // 文本，默认为数值文本
			        color:['lightgreen','orange'],
			        splitNumber: 5
			    },
			    grid: {
			        y2: 80
			    },
			    xAxis : [
			        {
			        	
			            type : 'time',
			            splitNumber:12
			        }
			    ],
			    yAxis : [
			        {
			        	min:0,
			        	max:max,
			            type : 'value'
			        }
			    ],
			    animation: false,
			    series : [
			        {
			            name:'计算机病毒',
			            type:'scatter',
			            symbolSize: function (value){
			                return Math.round(value[2]/10);
			            },
			            data: d
			        },
			        {
			            name:'网络蠕虫',
			            type:'scatter',
			            symbolSize: function (value){
			                return Math.round(value[2]/10);
			            },
			            data: d1
			        },
			        {
			            name:'僵尸软件',
			            type:'scatter',
			            symbolSize: function (value){
			                return Math.round(value[2]/10);
			            },
			            data: d2
			        },
			        {
			            name:'木马',
			            type:'scatter',
			            symbolSize: function (value){
			                return Math.round(value[2]/10);
			            },
			            data: d3
			        },
			        {
			            name:'网页挂马',
			            type:'scatter',
			            symbolSize: function (value){
			                return Math.round(value[2]/10);
			            },
			            data: d4
			        },
			        {
			            name:'间谍软件',
			            type:'scatter',
			            symbolSize: function (value){
			                return Math.round(value[2]/10);
			            },
			            data: d5
			        },
			        {
			            name:'其他',
			            type:'scatter',
			            symbolSize: function (value){
			                return Math.round(value[2]/10);
			            },
			            data: d6
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
		        'echarts/chart/scatter',
		        'echarts/chart/force'
		    ],
		    function(ec){
		    	console.info(d);
		    	var myChart = ec.init(document.getElementById('main')); 
				myChart.on("click",function(parmas){
					chatTable.from = parmas.value[0].getTime();
					chatTable.to = parmas.value[0].getTime()+(10*60*1000);
					chatTable.parmasChat=[];
					var titles = ['occurTime','devAddr','category','eventType','sAddr','dAddr','dPort','protocol'];
					chatTable.parmasChat.push({name:"eventType",value:parmas.seriesName});
					chatTable.searchChat("las-syslog",titles);
				});
				$("#loadings").hide();
		    	$("#loadings").dialog("close")
		    	myChart.setOption(option); 
		    }
		);
	}
	

	</script>

 <script src="<c:url value="/statics/scripts/echart/js/table.js"/>"></script>
 
