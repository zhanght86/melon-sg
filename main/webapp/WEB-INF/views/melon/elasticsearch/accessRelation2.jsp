<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>
<div id="loadings">loading......</div>
<div style="height:100px;">
查询条件：
开始时间、结束时间：查询制定时间段的数据；关键节点：指定syslog的一个节点ip作为源地址去netfow或者tcpflow中查询数据；下级节点最大数量：每次探取的下级节点的数量；
叶子节点数量：往下探取的次数；索引：指定查询的索引
<br/>
显示图：
点击节点显示syslog的事件，点击线显示选择的索引事件
</div>
<div>
	开始时间：<input id="startDate" class="form-control" name="startDate" type="text" style="width:150px" maxlength="50"/>
	@<input id="startHH" type="text" value="00" style="width: 35px;" maxlength="2">:
	<input  id="startMM" type="text" value="00" style="width: 35px;" maxlength="2">:
	<input id="startSS" type="text" value="00" style="width: 35px;" maxlength="2">
	结束时间：<input id="endDate" class="form-control" name="endDate" type="text" style="width:150px"  maxlength="50"/>
		@<input id="endHH" type="text" style="width: 35px;" maxlength="2">:
	<input id="endMM" type="text" style="width: 35px;" maxlength="2">:
	<input id="endSS" type="text" style="width: 35px;" maxlength="2">:
	<br/>
	关键节点：<input id="sips" class="form-control" name="sips" value="192.168.2.11" type="text" style="width:250px" />
	下级节点最大数量 <input id="chirdNodeNum" name="chirdNodeNum" value="10"/>
	<br/>
	叶子节点数量：
		<select id="nodeNum" name="nodeNum">
			<option value="0">1</option>
			<option value="1" selected="selected">2</option>
			<option value="2">3</option>
			<option value="3">4</option>
			<option value="4">5</option>
		</select>
		
	索引：<select id="indexs" name="indexs">
		<option value="skyeye-tcpflow">skyeye-tcpflow</option>
		<option value="las-netflow">netflow</option>
	</select>
	<input type="button" value="查询" id="searchEsButton" onclick="chatTable.searchGetTime()">
	<hh:date id="startDate" maxDate="endDate"/>
	<hh:date id="endDate" maxDate="${newDate}"/>
</div>
<div id="chart" style="height:900px;text-align: center;"></div>
<link rel="stylesheet" href="<c:url value="/statics/kibana/css/bootstrap.light.min.css"/>">
 <link rel="stylesheet" href="<c:url value="/statics/scripts/echart/css/chartTalbe.css"/>">
 
 <jsp:include page="tableInfo.jsp" />     
 <script src="<c:url value="/statics/scripts/echart/js/echarts.js"/>"></script>
<script type="text/javascript" src="<mh:theme code='elasticsearch.jquery.js'/>"></script>
<script type = "text/javascript">
		var a= false;
		var b = false;
		var c = false;
		var jsonArray = {nodes:[],links:[]};
		var constMaxRadius = 10;
		var constMinRadius = 5;
		function createChart(){
			
			if(jsonArray.nodes.length==0){
				chatTable.removeAllChild("chart")
				$("#loadings").hide();
		    	$("#loadings").dialog("close");
		    	$("#chart").append("<div class='alert alert_info' style='text-align: center;margin-top: 200px;'>当前场景没有数据</div>");
		    	return ;
			}
			
			
			option = {
				    title : {
				        text: 'Force',
				        subtext: 'Force-directed tree',
				        x:'right',
				        y:'bottom'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: function(p,ticket,callback){
				        	var sAddr = p.indicator.substr(0,p.indicator.length-5);
				        	var dAddr = p.indicator2.substr(0,p.indicator2.length-5);
				        	var parmas=[];
				        	parmas.push({name:"sAddr",value:sAddr});
				        	parmas.push({name:"dAddr",value:dAddr});
				        	var index = $("#indexs").val();
				        	var res = "端口号  数量";
				        	searchEs.groupByName(null,index,"dPort",parmas,chatTable.from,chatTable.to,10,function(arrays){
				        		$.each(arrays,function(i,v){
				        			res+="<br/>"+v.term+"     "+v.count;
				        		})
				        		callback(ticket, res);
				        	})
				             return "loading";
							/* if(p.indicator){
							  return  p.indicator.substr(0,p.indicator.length-5)+"-"+p.indicator2.substr(0,p.indicator2.length-5);					
							}else{
							  return p.name.substr(0,(p.name.length-5));
							} */
				        	
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
				        data:['叶子节点','非叶子节点', '根节点']
				    },
				    series : [
				        {
				            type:'force',
				            name : "Force tree",
				            ribbonType: false,
				            categories : [
				                {
				                    name: '叶子节点'
				                },
				                {
				                    name: '非叶子节点'
				                },
				                {
				                    name: '根节点'
				                }
				            ],
				            itemStyle: {
				                normal: {
				                    label: {
				                        show: true,
				                        textStyle: {
				                            color: '#333'
				                        }
				                    },
				                    nodeStyle : {
				                        brushType : 'both',
				                        borderColor : 'rgba(255,215,0,0.6)',
				                        borderWidth : 1
				                    }
				                }
				            },
				            minRadius : constMinRadius,
				            maxRadius : constMaxRadius,
				            coolDown: 0.995,
				            steps: 10,
				            nodes : jsonArray.nodes,
				            links : jsonArray.links,
				            steps: 1
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
				        'echarts/chart/force'
				    ],
				    function(ec){
				    	$("#loadings").hide();
				    	$("#loadings").dialog("close");
				    	var myChart = ec.init(document.getElementById('chart'));
					    	myChart.on("click",function(params){
					    		chatTable.parmasChat=[];
								var log = $("#indexs").val();
								var titles =  ['receptTime','sAddr','sPort','dAddr','dPort','protocol'];
								
								chatTable.index = log;
								var names = params.name.split(' - ');
								var pName = params.name.substr(0,(params.name.length-5));
								var sdb = params.name.substr((params.name.length-1),1);
								if(log=="skyeye-tcpflow"){
									titles = ['sip','sport','dip','dport','proto','uplink_length','downlink_length'];
									if(names.length>1){
										chatTable.parmasChat.push({name:"sip",value:names[0].substr(0,(names[0].length-5))});
										chatTable.parmasChat.push({name:"dip",value:names[1].substr(0,(names[1].length-5))});
									}else{
										log = "las-syslog";
										titles = ['occurTime','devAddr','category','eventType','sAddr','dAddr','dPort','protocol'];
										chatTable.parmasChat.push({name:"sip",value:names[0].substr(0,(names[0].length-5))});
									}
								}else{
									if(names.length>1){
										chatTable.parmasChat.push({name:"sAddr",value:names[0].substr(0,(names[0].length-5))});
										chatTable.parmasChat.push({name:"dAddr",value:names[1].substr(0,(names[1].length-5))});
									}else{
										log = "las-syslog";
										titles = ['occurTime','devAddr','category','eventType','sAddr','dAddr','dPort','protocol'];
										chatTable.parmasChat.push({name:"sAddr",value:names[0].substr(0,(names[0].length-5))});
									}
								}
								chatTable.searchChat(log,titles);
				    	})
				    	myChart.setOption(option); 
				    })
		}
		function searchLasEs(syslog,lasNet,from,to){
			jsonArray = {nodes:[],links:[]};
			var index = $("#indexs").val();
			var sip = $("#sips").val();
			var nodeNum = $("#nodeNum").val()
			var params = [];
			var field ;
			var queryParms ;
			if(index=="skyeye-tcpflow"){
				field = "dip";
				params.push({name:"sip",value:sip})
				
			}else{
				field="dAddr"
				params.push({name:"sAddr",value:sip})
			}
			var date = new Date();
			jsonArray.nodes.push({"category":2,"name":sip+"9999s","value":10,id:"0","depth":"0"});
			var lengthNum = 0 ;
			var iN=0;
			var chirdNodeNum = $("#chirdNodeNum").val();
			function search(f,p,ip,num,time){
				searchEs.groupByName(null,index,f,p,from,to,chirdNodeNum,function(arrays){
					if(num==1&&arrays.length==0){
						chatTable.removeAllChild("chart")
						$("#loadings").hide();
				    	$("#loadings").dialog("close");
				    	$("#chart").append("<div style='text-align: center;margin-top: 200px;'><font color='red' size='300'>当前场景没有数据</font></div>");
				    	return ;
					}
					var category = 2;
					if(num<nodeNum){
						category = 0;
					}
					if(num==nodeNum){
						category = 1;
					}
					lengthNum+=arrays.length;
					$.each(arrays,function(i,v){
						iN++;
						var nodes = jsonArray.nodes;
						var name = v.term;
						var le = nodes.length+"";
						var value = 7;
						if(le.length==1){
							le+="000";
							value = 7;
						}
						if(le.length==2){
							le+="00";
							value = 6.5;
						}
						if(le.length==3){
							le+="0";
							value = 8;
						}
						if(category == 1){
							le+="d";
						}else{
							le+="s"
						}
						var node = {"category":category,
										"name":name + le,
										"value":value,
										id:nodes.length
										};
						jsonArray.nodes.push(node);
						var link = {"source":time,
									"target":node.id,
									"hight":1}
						jsonArray.links.push(link);
						if(num<nodeNum){
							params = [];
							if(index=="skyeye-tcpflow"){
									field = "dip";
									params.push({name:"sip",value:name})
							}else{
									field="dAddr"
									params.push({name:"sAddr",value:name})
							}
							search(field,params,v.term,num+1,node.id);
						}
					});	
					if(iN>=lengthNum&&num==nodeNum){
						createChart();
					}
				});	
			}
				search(field,params,sip,1,0);
		}
</script>
 <script src="<c:url value="/statics/scripts/echart/js/searchEs.js"/>"></script>
 <script src="<c:url value="/statics/scripts/echart/js/table.js"/>"></script>
 
