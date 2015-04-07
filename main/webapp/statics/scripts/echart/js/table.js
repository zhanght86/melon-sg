var chatTable = {
		array:{},
		cuurPage:1,
		pageSize:15,
		titles:{},
		parmasChat:[],
		index:"",
		from:0,
		to:0,
		client : new $.es.Client({
			  hosts: '10.70.1.10:9200'
			}),
		tableDetail:function(ids,e,table){
			var trDetail = $("<tr id='"+ids+"' allt='yyy' class='ng-scope'></tr>");
			var td = $("<td colspan='7'></td>");
			td.appendTo(trDetail);
			var tableDetail =$("<table class='table table-bordered table-condensed table-details ng-scope'></table>");
			var tr1 = $("<tr></tr>");
			var th1 = $("<th class='table-details-field'>Field</th>");
			th1.appendTo(tr1);
			var th1 = $("<th class='table-details-field'>Value</th>");
			th1.appendTo(tr1);
			tr1.appendTo(tableDetail);
			$.each(e._source,function(key,value){
				var tr1 = $("<tr></tr>");
				$("<td style='word-wrap: break-word' >"+key+"</td>").appendTo(tr1);
				$("<td style='white-space: pre-wrap; word-wrap: break-word' >"+value+"</td>").appendTo(tr1);
				tr1.appendTo(tableDetail);
			})
			tableDetail.appendTo(td);
			td.appendTo(trDetail);
			trDetail.hide();
			trDetail.appendTo(table);
		},
		tableEs:function(titles,array){
			if($("#alertTableList")){
				$("#alertTableList").remove();
			}
			var table = $("<table id='alertTableList' class='table-hover table table-condensed' style='font-size: 9pt;'>");
			table.appendTo($("#alertTable"));
			var tr = $("<tr></tr>");
			$.each(titles,function(i,v){
				$("<th  class='ng-scope'>"+v+"</th>").appendTo(tr);
			});
			tr.appendTo(table);
			var ths = this;
			$.each(array,function(i,e){
				var tbody = $("<tbody class='ng-scope odd'></tbody>")
				var ids = "x"+i;
				var tr = $("<tr onClick='chatTable.toggle_details(\""+ids+"\")' class='pointer'></tr>");
				$.each(titles,function(i,v){
					$("<td>"+e._source[v]+"</td>").appendTo(tr);
				});
				tr.appendTo(tbody);
				ths.tableDetail(ids,e,tbody);
				tbody.appendTo(table);
			})
			$("#alertTable").append("</table>");
		},
		
		
		queryEs:function(){
			var as = $("input[params='paramsInput']");
			var arra = [];
			var paramsValue = "";
			$.each(as,function(i,v){
				if($(v).val().length>0){
					if(paramsValue.length==0){
						paramsValue = v.name+":\""+$(v).val()+"\" ";
					}else{
						paramsValue += " AND "+v.name+":\""+$(v).val()+"\" ";
					}
					arra.push(v.name);
				}
			})
			$.each(this.parmasChat,function(i,v){
				var x = false;
				$.each(arra,function(i,e){
					if(v.name==e){
						x = true;
					}
				})
				if(!x){
					if(paramsValue.length==0){
						paramsValue += v.name+":"+v.value;
					}else{
						if(v.node=="1"){
							paramsValue += " OR " +v.name+":"+v.value;
						}else{
							paramsValue += " AND " +v.name+":"+v.value;
						}
					}
				}
			})
			if(paramsValue==""){
				paramsValue = "*";
			}
			this.client.search({
				type:chatTable.index,
				body:{
					"query": {
					    "filtered": {
					      "query": {
					        "bool": {
					          "should": [
					            {
					              "query_string": {
					                "query": paramsValue
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
			                            "from": this.from,
			                            "to": this.to
			                          }
			                        }
			                      }
			                    ]
			                  }
			             }
					   }
					},
					"size": 500
				}
			},function(err,res){
				var array = res.hits.hits;
				chatTable.array = array;
				chatTable.cuurPage = 1;
				chatTable.tablePage();
			})
		},
		cleanQuery:function(){
			var as = $("input[params='paramsInput']");
			$.each(as,function(i,v){
				$(v).val("");
			    
			})
			this.queryEs();
			$("tr[params='params']").hide();
		},
		paramValues:function(v){
			$("tr[params='params']").hide();
			var b = false;
			$.each($("tr[params='params']"),function(i,x){
				var oTr = $(x);
				if(oTr.attr("name")==v){
					oTr.show();
					b = true;
				}
			})
			if(!b){
				var tr = $("<tr params='params' name=\""+v+"\"></tr>")
				var td = $("<td colspan='8'>"+v+"  =  <input  type='text' value=''  params='paramsInput' name=\""+v+"\"/>  <a href='javascript:void(0)' onClick='chatTable.queryEs()'>查询</a>  <a href='javascript:void(0)' onClick='chatTable.cleanQuery()'>清理</a></td>")
				td.appendTo(tr);
				tr.appendTo($("#alertTableList"));	
			}
		},
		tablePage:function(){
			var trParams = $("tr[params='params']");
			if($("#alertTableList")){
				$("#alertTableList").remove();
			}
			
			var table = $("<table id='alertTableList' class='table-hover table table-condensed' style='font-size: 9pt;'>");
			table.appendTo($("#alertTable"));
			var tr = $("<tr></tr>");
			$.each(this.titles,function(i,v){
				$("<th  onClick = 'chatTable.paramValues(\""+v+"\")' class='ng-scope pointer'>"+v+"</th>").appendTo(tr);
			});
			tr.appendTo(table);
			if(trParams){
				trParams.appendTo(table);
			}
			var ths = this;
			$.each(ths.array,function(i,e){
				if(i<(ths.cuurPage-1)*ths.pageSize){
					return;
				}
				if(i>=ths.cuurPage*ths.pageSize){
					return false;
				}
				
				var tbody = $("<tbody class='ng-scope odd'></tbody>")
				var ids = "x"+i;
				var tr = $("<tr onClick='chatTable.toggle_details(\""+ids+"\")' class='pointer'></tr>");
				$.each(ths.titles,function(i,v){
					$("<td>"+e._source[v]+"</td>").appendTo(tr);
				});
				tr.appendTo(tbody);
				ths.tableDetail(ids,e,tbody);
				tbody.appendTo(table);
			})
			$("#alertTable").append("</table>");
			this.pages();
			
		},
		removeAllChild:function(div){
			$.each($("#"+div),function(i,v){
					$("#"+div).eq(i).children("div").remove();
			})
		},
		pages:function(){
			var divPage = $("#alertPage");
			this.removeAllChild("alertPage");
			var div = $("<div class='span1 offset3' style='text-align: right'></div>");
			if(chatTable.cuurPage>1){
				var li = $("<i onclick='chatTable.previousPage()' class='icon-circle-arrow-left pointer' ></i>");
				li.appendTo(div);
			}else{
				var li = $("<i class='icon-circle-arrow-left' ></i>");
				li.appendTo(div);
			}
			div.appendTo(divPage);
			var div = $("<div class='span4' style='text-align: center'></div>");
			var strong = $("<strong class='ng-binding'>"+(chatTable.cuurPage-1)*chatTable.pageSize+"</strong>");
			strong.appendTo(div);
			var strong = $("<strong class='ng-binding'>to</strong>");
			strong.appendTo(div);
			var strong = $("<strong class='ng-binding'>"+chatTable.cuurPage*chatTable.pageSize+"</strong>");
			strong.appendTo(div);
			var strong = $("<strong class='ng-binding'>共"+chatTable.array.length+"条，共"+(parseInt(chatTable.array.length/chatTable.pageSize)+1)+"页</strong>");
			strong.appendTo(div);
			div.appendTo(divPage);
			var div = $("<div class='span1' style='text-align: left'></div>");
			if(chatTable.cuurPage>(chatTable.array.length/chatTable.pageSize)){
				var li = $("<i class='icon-arrow-right'></i>");
				li.appendTo(div);
			}else{
				var li = $("<i onclick='chatTable.nextPage()' class='icon-arrow-right pointer'></i>");
				li.appendTo(div);
			}
			div.appendTo(divPage);
			/*var div =$("<div class='span1' style='text-align: left'></div>");
			var select = $("<select id = 'pageSizeS1'><option value='10'>10</option><option value='20'>20</option><option value='30'>30</option><option value='50'>50</option><option value='100'>100</option></select>");
			select.appendTo(div);
			div.appendTo(divPage);
			$("#pageSizeS1 option").click(function(){
				alert(chatTable.pageSize)
				chatTable.pageSize = $(this).value;
				chatTable.tablePage();
			})*/
		},
		toggle_details:function(e){
			var array = $("tr[allt=yyy]");
			$.each(array,function(i,item){
					if($(item).attr("id")!=e){
							$(item).hide();
					}
			})
			if($("#"+e).is(":hidden")){
					$("#"+e).show();	
				}else{
					$("#"+e).hide();
			}
		},
		previousPage:function(){
			chatTable.cuurPage = chatTable.cuurPage-1;
			chatTable.tablePage();
		},
		nextPage:function(){
			chatTable.cuurPage = chatTable.cuurPage+1;
			chatTable.tablePage();
		},
		searchChat:function(log,titles){
			chatTable.index = log;
			chatTable.titles = titles;
			chatTable.cuurPage = 1;
			chatTable.queryEs();
		},
		searchGetTime:function(){
			//删除详细
			this.removeAllChild("alertPage");
			$("#alertTableList").remove();
			loadings();
			var myDate = new Date();
			var startDates = $("#startDate").val().split(" ");
			var endDates = $("#endDate").val().split(" ");
			var symd = startDates[0].split("-");
			var eymd = endDates[0].split("-");
			$("#startHH").val(this.zero($("#startHH").val()));
			$("#startMM").val(this.zero($("#startMM").val()));
			$("#startSS").val(this.zero($("#startSS").val()));
			$("#endHH").val(this.zero($("#endHH").val()));
			$("#endMM").val(this.zero($("#endMM").val()));
			$("#endSS").val(this.zero($("#endSS").val()));
			var form = new Date(symd[0],(symd[1]-1),symd[2],$("#startHH").val(),$("#startMM").val(),$("#startSS").val());
			var to = new Date(eymd[0],(eymd[1]-1),eymd[2],$("#endHH").val(),$("#endMM").val(),$("#endSS").val());
			chatTable.from = form.getTime();
			chatTable.to = to.getTime();
			searchLasEs("las-syslog","las-netflow",form.getTime(),to.getTime());
		},
		zero:function(num){
			if(num<10){
				return "0"+parseInt(num);
			}
			return num;
		},
		//根据时间拼装index
		searchDateIndex:function(){
			if($("#startDate").val().length==0){
				alert("请选择开始时间");
				return ;
			}
			if($("#endDate").val().length==0){
				alert("请选择结束时间");
				return ;
			}
			var startDates = $("#startDate").val().split("-");
			var endDates = $("#endDate").val().split("-");
			var indexSyslog = "las-syslog-"+$("#startDate").val().replace("-",".").replace("-",".");
			var indexNetflow = "las-netflow-"+$("#startDate").val().replace("-",".").replace("-",".");
			var startDate = new Date(startDates[0],startDates[1],startDates[2]);
			var endDate = new Date(endDates[0],endDates[1],endDates[2]);
			var diff = parseInt((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)); //工作天数
			for(var i=1;i<=diff;i++){
				var day = parseInt(startDates[2])+i;
				var month = startDates[1];
				var year = startDates[0];
				var lastDay = this.getLastDay(year,month);
				if(day>lastDay){
					day=day-lastDay;
					month+=1;
				}
				if(month>12){
					month=month-12;
					year=year+1;
				}
				if(day<10){
					day = '0'+''+day;
				}
				indexSyslog+=",las-syslog-"+year+"."+month+"."+day;
				indexNetflow+=",las-netflow-"+year+"."+month+"."+day;
			}
			searchLasEs(indexSyslog,indexNetflow);
		},
		getLastDay:function(year,month){
				 var new_year = year;  //取当前的年份   
				 var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）   
				 if(month>12)      //如果当前大于12月，则年份转到下一年   
				 {   
				 new_month -=12;    //月份减   
				 new_year++;      //年份增   
				 }   
				 var new_date = new Date(new_year,new_month,1);        //取当年当月中的第一天   
				 return (new Date(new_date.getTime()-1000*60*60*24)).getDate();//获取当月最后一天日期   
		},
		isTime:function(str){
			 var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/); 
			  if(r==null)
				  return false; 
			  var d = new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]); 
			  return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()==r[7]);
		}
};
function setSystemDate(){
	loadings();
	var mydate = new Date();
	var month = mydate.getMonth()+1;
	if(month<10){
		month = '0'+''+month;
	}
	var date = mydate.getDate();
	if(date<10){
		date = '0'+''+date;
	}				
	var time = mydate.getFullYear()+"-"+month+"-"+date;
	$("#startDate").val(time);
	$("#endDate").val(time);
	var hours = mydate.getHours();
	if(hours<10){
		hours = "0"+hours
	}
	var minutes = mydate.getMinutes()
	if(minutes<10){
		minutes="0"+minutes
	}
	var seconds = mydate.getSeconds();
	if(seconds<10){
		seconds="0"+seconds;
	}
	$("#endHH").val(hours);
	$("#endMM").val(minutes);
	$("#endSS").val(seconds);
	var form = new Date(mydate.getFullYear(),mydate.getMonth(), mydate.getDate(),$("#startHH").val(),$("#startMM").val(),$("#startSS").val());
	chatTable.from = form.getTime();
	chatTable.to = mydate.getTime();
	searchLasEs("las-syslog","las-netflow",form.getTime(),mydate.getTime());
}
function loadings(){
	$("#loadings").dialog({
		title:"正在加载数据",
		modal : true,
		draggable : false,
		resizable : false,
		autoOpen : true,
		height : 250,
		width : 400
	});
}
setSystemDate();
