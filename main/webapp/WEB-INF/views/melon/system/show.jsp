<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>

<style type="text/css">
    #r11{
        width: 1080px;
        border: 1px;
        
    }
    #ul1{
        padding-right: 0px;
    }
    #li1{
        list-style:none;
        float: right;
    }
    #r16{
        border-left: 0px;
    }
    #r211{
        width: 50px;
        border-top: 0px;
        border-left: 0px;
    }
    .t{
        border-bottom: 0px;
    }
    #r212{
        width: 80px;
        border-top: 0px;
        border-left: 0px;
    }
    #r213{
        width: 50px;
        border-top: 0px;
        border-left: 0px;
    }
    #r214{
        width: 80px;
        border-top: 0px;
        border-left: 0px;
        border-right: 0px;
    }
    #c21{
        border:0px;
    }
    #c92{
        border-top:0px;
        border-left: 0px;
        border-right:0px;
    }
    #c22{
    	width:500px;
        border-left: 0px;
        border-right:0px;
    }
   
    #timeChart{
    	width:440px;
    	height:205px;
    }
    .t2{
    	border-left: 0px;
    }
    
    #r12,#r13,#r14,#r15{
        border-left: 0px;
        border-right: 0px;
    }
    #r16{
        border-left: 0px;
    }
     
    #r2211{
        width: 300px;
    }
    #r2212{
        width: 700px;
    }
    #r2213{
        width: 300px;
    }
    #r2214{
        width: 700px;
    }
    #ul2{
        padding-left: 0px;
    }
    #li2{
        list-style:none;
        float: left;
    }
    #h{
        /*position: absolute;*/
        clear:both;
    }
    #hg{
    	height:30px;
    	padding-top: 7px;
    }
    #leak,#fragility,#history,#correlation{
        display:none;
    }
    #btn{
    	width:60px;
    	height:20px;
    	background-color:;
    	margin-left: 3px;
    }
    #btn1{
    	width:60px;
    	height:20px;
    	background-color:;
    }
    #btn2{
    	margin-left: 3px;
    	width:71px;
    	height:20px;
    	margin-left: 11px;
    }
</style>
    
<div id="container">
    <table class="t" border="1" cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td id="r11">
            	<div id="hg">
            		预告警:网银木马 
            	</div>
            </td>
            <div>
	            <ul id="ul1">
	                <li id="li1"><button>派发工单</button></li>
	                <li id="li1"><button>告警</button></li>
	                <li id="li1"><button>忽略</button></li>
	                <li id="li1"><button>返回</button></li>
	                <li id="li1"><button>事件上报</button></li>
	            </ul>
            </div>      
        </tr>
        <tr>
            <td id="c21">
                <table class="t2" border="1" cellspacing="0" cellpadding="0" width="100%">
                    <tr>
                        <td id="r211">告警日志名</td>
                        <td id="r212">网银木马</td>
                        <td id="r213">告警日志级</td>
                        <td id="r214">高</td>
                    </tr>
                    <tr>
                        <td id="r211">告警日志类</td>
                        <td id="r212">恶意程序</td>
                        <td id="r213">告警日志子</td>
                        <td id="r214">木马</td>
                    </tr>
                    <tr>
                        <td id="r211">对象IP</td>
                        <td id="r212">192.168.100.101</td>
                        <td id="r213">次数</td>
                        <td id="r214">1</td>
                    </tr>
                    <tr>
                        <td id="r211">源地址</td>
                        <td id="r212">192.168.100.101</td>
                        <td id="r213">目的地址</td>
                        <td id="r214">0.0.0.0</td>
                    </tr>
                    <tr>
                        <td id="r211">源端口</td>
                        <td id="r212">0</td>
                        <td id="r213">目的端口</td>
                        <td id="r214">0</td>
                    </tr>
                    <tr>
                        <td id="r211">协议</td>
                        <td id="r212">TCP</td>
                        <td id="r213">来源</td>
                        <td id="r214">防病毒1</td>
                    </tr>
                    <tr>
                        <td id="r211">最后更新时</td>
                        <td id="r212">2014-09-15 10:23:33</td>
                        <td id="r213">告警时间</td>
                        <td id="r214">2014-09-15</td>
                    </tr>
                    <tr rowspan=3>
                        <td id="r211">原始告警信息</td>
                        <td colspan=3 id="c92">
                            事件名称：网银木马；源地址：192.168.100.101；源端口：0；目的地址：0.0.0.0；目的端口：0；
                            行为：.......;发现时间：2014-09-15 10:23:33
                            
                        </td>
                    </tr>
                    
                </table>
            </td>
            <td id="c22">
            	<div id="container3">
            		<div id="timeChart" class="chart"></div>
            	</div>
            	<!-- <div id="container3">
	            	<div class="tab-pane active" id="profile">
	            		<div class="row block-design">
							<div id="timeChart" class="chart"></div>
						</div>
	            	</div>
            	</div> -->
            </td>
        </tr>
    </table>
</div>

<div id="container2">
    <div id=h>
        <ul id="ul2">
            <li id="li2"><div id="btn1"><a href="#" onclick="change_div('asset')">资产</a></div></li>
            <li id="li2"><div id="btn"><a href="#" onclick="change_div('leak')">漏洞</a></div></li>
            <li id="li2"><div id="btn"><a href="#" onclick="change_div('fragility')">脆弱性</a></div></li>
            <li id="li2"><div id="btn"><a href="#" onclick="change_div('history')">历史事件</a></div></li>
            <li id="li2"><div id="btn2"><a href="#" onclick="change_div('correlation')">相关事件</a></div></li>
        </ul>
     </div>
     <div id="asset">
        <table class="t" border="1" cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td id="r2211">（资产）对象信息</td>
                <td id="r2212"></td>
                <td id="r2213"></td>
                <td id="r2214"></td>
            </tr>
            <tr>
                <td id="r2211">（资产）对象名称</td>
                <td id="r2212">XXXXX</td>
                <td id="r2213">资产类别</td>
                <td id="r2214">终端设备</td>
            </tr>
            <tr>
                <td id="r2211">责任人</td>
                <td id="r2212">XXXXXX</td>
                <td id="r2213">责任部门</td>
                <td id="r2214">XXXXXXXX</td>
            </tr>
            <tr>
                <td id="r2211">所属地域</td>
                <td id="r2212">总局</td>
                <td id="r2213">操作系统</td>
                <td id="r2214">Windows7 SP1</td>
            </tr>
            <tr>
                <td id="r2211">所属业务系统</td>
                <td id="r2212">办公终端</td>
                <td id="r2213">资产价值</td>
                <td id="r2214">1</td>
            </tr>
            <tr>
                <td id="r2211">IP</td>
                <td id="r2212">192.168.100.101</td>
                <td id="r2213">MAC</td>
                <td id="r2214">00：AA:BB:CC:DD:EE:FF</td>
            </tr>
            <tr>
                <td id="r2211">防病毒软件</td>
                <td id="r2212">360</td>
                <td id="r2213">病毒库更新</td>
                <td id="r2214">2014-09-15</td>
            </tr>
            
            <tr rowspan=5>
                <td id="r2211">补丁安装情况</td>
                <td colspan=3>
                    KB-XXXX
                    
                </td>
            </tr>
            <tr rowspan=3>
                <td id="r2211">其他安全防护</td>
                <td colspan=3>
                    事件名称：网银木马；源地址：192.168.100.101；源端口：0；目的地址：0.0.0.0；目的端口：0；
                    行为：.......;发现时间：2014-09-15 10:23:33
                    
                </td>
            </tr>
                        
        </table>
    </div>
    
    
    <div id="leak">
        <table class="t" border="1" cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td id="r2211">（漏洞）对象信息</td>
                <td id="r2212"></td>
                <td id="r2213"></td>
                <td id="r2214"></td>
            </tr>
            <tr>
                <td id="r2211">（漏洞）对象名称</td>
                <td id="r2212">XXXXX</td>
                <td id="r2213">资产类别</td>
                <td id="r2214">终端设备</td>
            </tr>
            <tr>
                <td id="r2211">责任人</td>
                <td id="r2212">XXXXXX</td>
                <td id="r2213">责任部门</td>
                <td id="r2214">XXXXXXXX</td>
            </tr>
            <tr>
                <td id="r2211">所属地域</td>
                <td id="r2212">总局</td>
                <td id="r2213">操作系统</td>
                <td id="r2214">Windows7 SP1</td>
            </tr>
            <tr>
                <td id="r2211">所属业务系统</td>
                <td id="r2212">办公终端</td>
                <td id="r2213">资产价值</td>
                <td id="r2214">1</td>
            </tr>
            <tr>
                <td id="r2211">IP</td>
                <td id="r2212">192.168.100.101</td>
                <td id="r2213">MAC</td>
                <td id="r2214">00：AA:BB:CC:DD:EE:FF</td>
            </tr>
            <tr>
                <td id="r2211">防病毒软件</td>
                <td id="r2212">360</td>
                <td id="r2213">病毒库更新</td>
                <td id="r2214">2014-09-15</td>
            </tr>
            
            <tr rowspan=5>
                <td id="r2211">补丁安装情况</td>
                <td colspan=3>
                    KB-XXXX
                    
                </td>
            </tr>
            <tr rowspan=3>
                <td id="r2211">其他安全防护</td>
                <td colspan=3>
                    事件名称：网银木马；源地址：192.168.100.101；源端口：0；目的地址：0.0.0.0；目的端口：0；
                    行为：.......;发现时间：2014-09-15 10:23:33
                    
                </td>
            </tr>
                        
        </table>
    </div>
    
    
    <div id="fragility">
        <table class="t" border="1" cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td id="r2211">（脆弱性）对象信息</td>
                <td id="r2212"></td>
                <td id="r2213"></td>
                <td id="r2214"></td>
            </tr>
            <tr>
                <td id="r2211">（脆弱性）对象名称</td>
                <td id="r2212">XXXXX</td>
                <td id="r2213">资产类别</td>
                <td id="r2214">终端设备</td>
            </tr>
            <tr>
                <td id="r2211">责任人</td>
                <td id="r2212">XXXXXX</td>
                <td id="r2213">责任部门</td>
                <td id="r2214">XXXXXXXX</td>
            </tr>
            <tr>
                <td id="r2211">所属地域</td>
                <td id="r2212">总局</td>
                <td id="r2213">操作系统</td>
                <td id="r2214">Windows7 SP1</td>
            </tr>
            <tr>
                <td id="r2211">所属业务系统</td>
                <td id="r2212">办公终端</td>
                <td id="r2213">资产价值</td>
                <td id="r2214">1</td>
            </tr>
            <tr>
                <td id="r2211">IP</td>
                <td id="r2212">192.168.100.101</td>
                <td id="r2213">MAC</td>
                <td id="r2214">00：AA:BB:CC:DD:EE:FF</td>
            </tr>
            <tr>
                <td id="r2211">防病毒软件</td>
                <td id="r2212">360</td>
                <td id="r2213">病毒库更新</td>
                <td id="r2214">2014-09-15</td>
            </tr>
            
            <tr rowspan=5>
                <td id="r2211">补丁安装情况</td>
                <td colspan=3>
                    KB-XXXX
                    
                </td>
            </tr>
            <tr rowspan=3>
                <td id="r2211">其他安全防护</td>
                <td colspan=3>
                    事件名称：网银木马；源地址：192.168.100.101；源端口：0；目的地址：0.0.0.0；目的端口：0；
                    行为：.......;发现时间：2014-09-15 10:23:33
                    
                </td>
            </tr>
                        
        </table>
    </div>
    
    
    <div id="history">
        <table class="t" border="1" cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td id="r2211">（历史事件）对象信息</td>
                <td id="r2212"></td>
                <td id="r2213"></td>
                <td id="r2214"></td>
            </tr>
            <tr>
                <td id="r2211">（历史事件）对象名称</td>
                <td id="r2212">XXXXX</td>
                <td id="r2213">资产类别</td>
                <td id="r2214">终端设备</td>
            </tr>
            <tr>
                <td id="r2211">责任人</td>
                <td id="r2212">XXXXXX</td>
                <td id="r2213">责任部门</td>
                <td id="r2214">XXXXXXXX</td>
            </tr>
            <tr>
                <td id="r2211">所属地域</td>
                <td id="r2212">总局</td>
                <td id="r2213">操作系统</td>
                <td id="r2214">Windows7 SP1</td>
            </tr>
            <tr>
                <td id="r2211">所属业务系统</td>
                <td id="r2212">办公终端</td>
                <td id="r2213">资产价值</td>
                <td id="r2214">1</td>
            </tr>
            <tr>
                <td id="r2211">IP</td>
                <td id="r2212">192.168.100.101</td>
                <td id="r2213">MAC</td>
                <td id="r2214">00：AA:BB:CC:DD:EE:FF</td>
            </tr>
            <tr>
                <td id="r2211">防病毒软件</td>
                <td id="r2212">360</td>
                <td id="r2213">病毒库更新</td>
                <td id="r2214">2014-09-15</td>
            </tr>
            
            <tr rowspan=5>
                <td id="r2211">补丁安装情况</td>
                <td colspan=3>
                    KB-XXXX
                    
                </td>
            </tr>
            <tr rowspan=3>
                <td id="r2211">其他安全防护</td>
                <td colspan=3>
                    事件名称：网银木马；源地址：192.168.100.101；源端口：0；目的地址：0.0.0.0；目的端口：0；
                    行为：.......;发现时间：2014-09-15 10:23:33
                    
                </td>
            </tr>
                        
        </table>
    </div>
    
    
    <div id="correlation">
        <table class="t" border="1" cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td id="r2211">（相关事件）对象信息</td>
                <td id="r2212"></td>
                <td id="r2213"></td>
                <td id="r2214"></td>
            </tr>
            <tr>
                <td id="r2211">（相关事件）对象名称</td>
                <td id="r2212">XXXXX</td>
                <td id="r2213">资产类别</td>
                <td id="r2214">终端设备</td>
            </tr>
            <tr>
                <td id="r2211">责任人</td>
                <td id="r2212">XXXXXX</td>
                <td id="r2213">责任部门</td>
                <td id="r2214">XXXXXXXX</td>
            </tr>
            <tr>
                <td id="r2211">所属地域</td>
                <td id="r2212">总局</td>
                <td id="r2213">操作系统</td>
                <td id="r2214">Windows7 SP1</td>
            </tr>
            <tr>
                <td id="r2211">所属业务系统</td>
                <td id="r2212">办公终端</td>
                <td id="r2213">资产价值</td>
                <td id="r2214">1</td>
            </tr>
            <tr>
                <td id="r2211">IP</td>
                <td id="r2212">192.168.100.101</td>
                <td id="r2213">MAC</td>
                <td id="r2214">00：AA:BB:CC:DD:EE:FF</td>
            </tr>
            <tr>
                <td id="r2211">防病毒软件</td>
                <td id="r2212">360</td>
                <td id="r2213">病毒库更新</td>
                <td id="r2214">2014-09-15</td>
            </tr>
            
            <tr rowspan=5>
                <td id="r2211">补丁安装情况</td>
                <td colspan=3>
                    KB-XXXX
                    
                </td>
            </tr>
            <tr rowspan=3>
                <td id="r2211">其他安全防护</td>
                <td colspan=3>
                    事件名称：网银木马；源地址：192.168.100.101；源端口：0；目的地址：0.0.0.0；目的端口：0；
                    行为：.......;发现时间：2014-09-15 10:23:33
                    
                </td>
            </tr>
                        
        </table>
    </div>
    
</div>

    
<script type="text/javascript" src="<mh:theme code='esl.js'/>"></script>
<script type="text/javascript">

function change_div(id){
	  if (id == 'asset') {
	     $("#leak").attr('style','display:none');
	     $("#fragility").attr('style','display:none');
	     $("#history").attr('style','display:none');
	     $("#correlation").attr('style','display:none');
	     $("#asset").attr('style','display:block');
	  }else if(id == 'leak'){
		     $("#asset").attr('style','display:none');
		     $("#fragility").attr('style','display:none');
		     $("#history").attr('style','display:none');
		     $("#correlation").attr('style','display:none');
		     $("#leak").attr('style','display:block');
	  }else if(id == 'fragility'){
		     $("#asset").attr('style','display:none');
		     $("#leak").attr('style','display:none');
		     $("#history").attr('style','display:none');
		     $("#correlation").attr('style','display:none');
		     $("#fragility").attr('style','display:block');
	  }else if(id == 'history'){
		     $("#asset").attr('style','display:none');
		     $("#leak").attr('style','display:none');
		     $("#fragility").attr('style','display:none');
		     $("#correlation").attr('style','display:none');
		     $("#history").attr('style','display:block');
	  }else if(id == 'correlation'){
		     $("#asset").attr('style','display:none');
		     $("#leak").attr('style','display:none');
		     $("#fragility").attr('style','display:none');
		     $("#history").attr('style','display:none');
		     $("#correlation").attr('style','display:block');
	  }
	  
}

	$(document).ready(function() {
		require(["chart/mcharts"], function(mcharts) {
			
			//每日安全统计
			var safeData = [
				["0:00",53,28,15], ["2:00",63,45,18], ["4:00",48,67,28],                     
				["6:00",58,32,22], ["8:00",75,25,42], ["10:00",39,55,54],                  
				["12:00",43,66,43], ["14:00",53,80,32],["16:00",77,64,15], 
				["18:00",99,25,36],["20:00",50,36,10], ["22:00",62,52,8]];
			var configs = {
				el : "#timeChart",
				theme : "blue",
				title : "每日安全统计图",
				data : safeData,
				dataZoom : false,		//数据区域缩放，直角坐标系图表有效
				ready : function() {
				},
				seriesConfig : [{
					name : '防火墙',
					type : 'line',
				//	stack : "group",
					category : "0",
					value : 1
				},{
					name : '路由器',
					type : 'line',
				//	stack : "group",
					category : "0",
					value : 2
				},{
					name : 'aaa',
					type : 'line',
				//	stack : "group",
					category : "0",
					value : 3
				}]
			};
			new mcharts.Column(configs);
			
		});
		
		
		
	});
	
</script>
