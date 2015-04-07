<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<style type="text/css">
	/* 设置表格中字体的样式 */
	.td-label{
		text-align: center;
		font-weight: bold;
	}
	.table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td{
		vertical-align: middle;
	}
	/* 设置文本输入框的样式 */
	.td-input{
		width: 100%;
		border: none;
	}
	/* 内嵌表格中输入框的样式 */
	.netTable-input{
		border:none;
		border-bottom:1px solid;
		width:60px;
		text-align: center;
	}
	 .radio-inline{
	 	padding-top:0px !important;
	 }
	 /* 消除td表格与内嵌表格之间得到距离 */
	 .td-padding{
	 	padding: 0px !important;
	 }
	 /* 消除内嵌表格的上边框  */
	 .td-remove-border-top{
	 	border-top: 0px !important;
	 }
	  /* 消除内嵌表格的右边框  */
	 .td-remove-border-right{
	 	border-right: 0px !important;
	 }
	  /* 消除内嵌表格的右边框  */
	 .td-remove-border-bottom{
	 	border-bottom: 0px !important;
	 }
	 
	 .td-remove-border-left{
	 	border-left: 0px !important;
	 }
	 /* 消除内嵌表格与td之间的底边框距离 */
	 .table-space{
	 	margin-bottom: 0px !important;
	 }
	 .melon-buttons{
		text-align: center;
	}
	.page-header{
		margin-bottom : 0px;
		margin-top : -12px;
	}s
</style>

<div class="page-header">
	<h4><fmt:message key="db.sysInfo.title"/></h4>
</div>

<div class="page-body">
	<sf:form modelAttribute="systemInfo" role="form" method="POST">
		<table class="table form-horizontal table-bordered">
			<tr>
				<td colspan="2" width="20%" class="td-label">
					<fmt:message key="db.sysInfo.sysName"></fmt:message>
				</td>
				<td width="40%">
					<sf:input path="sysName" class="td-input" />
				</td>
				<td width="15%" class="td-label">
					<fmt:message key="db.sysInfo.sysCode"/>
				</td>
				<td width="20%">
					<sf:input path="sysCode" class="td-input" />
				</td>
			</tr>
			<tr>
				<td rowspan="2" class="td-label">
					<fmt:message key="db.sysInfo.sysSituation"/>				
				</td>
				<td class="td-label">
					<fmt:message key="db.sysInfo.busType"/>
				</td>
				<td colspan="3">
					<mh:dictionary var="busType" key="dbBusType"/>
					<sf:radiobuttons path="busType" items="${busType}"/>
				</td>
			</tr>
			<tr>
				<td class="td-label">
					<fmt:message key="db.sysInfo.busDesc"/>
				</td>
				<td colspan="3">
					<sf:textarea path="busDesc" class="td-input"  style="resize:none;"/>
				</td>
			</tr>
			<tr>
				<td rowspan="2"  width="80px;" class="td-label">
					<fmt:message key="db.sysInfo.serSituation"/>
				</td>
				<td class="td-label">
					<fmt:message key="db.sysInfo.serScope"/>
				</td>
				<td colspan="4" id="serScope">
					<mh:dictionary var="serScope" key="dbSerScope"/>
					<sf:radiobuttons path="serScope" items="${serScope }"/>
				</td>
			</tr>
			<tr>
				<td class="td-label">
					<fmt:message key="db.sysInfo.serObject"/>
				</td>
				<td colspan="3">
					<mh:dictionary key="dbSerObject" var="serObject"/>
					<sf:radiobuttons path="serObject" items="${serObject }"/>
				</td>
			</tr>
			<tr>
				<td rowspan="2" class="td-label">
					<fmt:message key="db.sysInfo.sysPlat"/>
				</td>
				<td class="td-label">
					<fmt:message key="db.sysInfo.netScope"/>
				</td>
				<td colspan="3">
					<mh:dictionary var="netScope" key="dbNetScope"/>
					<sf:radiobuttons path="netScope" items="${netScope }"/>
				</td>
			</tr>
			<tr>
				<td class="td-label">
					<fmt:message key="db.sysInfo.netType"/>
				</td>
				<td colspan="3">
					<mh:dictionary var="netType" key="dbNetType"/>
					<sf:radiobuttons path="netType" items="${netType }"/>
					<sf:input path="netOther" class="netTable-input" />
				</td>
			</tr>
			<tr>
				<td colspan="2" class="td-label">
					<fmt:message key="db.sysInfo.connType"/>
				</td>
				<td colspan="3">
					<mh:dictionary var="connType" key="dbConnType"/>
					<sf:radiobuttons path="connType" items="${connType }"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="td-label">
					<fmt:message key="db.sysInfo.keyProduct"/>
				</td>
				<td colspan="3" class="td-padding">
					<table class="table form-horizontal table-bordered td-remove-border-top td-remove-border-right td-remove-border-bottom td-remove-border-left table-space">
						<tr>
							<td rowspan="2"  width="7%" class="td-label td-remove-border-top td-remove-border-right td-remove-border-left">
								<fmt:message key="db.sysInfo.sNumber"/>
							</td>
							<td rowspan="2"  width="18%" class="td-label td-remove-border-top" >
								<fmt:message key="db.sysInfo.proType"/>
							</td>
							<td rowspan="2"  width="17%" class="td-label td-remove-border-top">
								<fmt:message key="db.sysInfo.number"/>
							</td>
							<td colspan="3" class="td-label td-remove-border-top td-remove-border-right ">
								<fmt:message key="db.sysInfo.usedRate"/>
							</td>
						</tr>
						<tr>
							<td  class="td-label">
								<fmt:message key="db.sysInfo.allUsed"/>
							</td>
							<td class="td-label">
								<fmt:message key="db.sysInfo.notUsed"/>
							</td>
							<td class="td-label td-remove-border-right">
								<fmt:message key="db.sysInfo.partUsed"/>
							</td>
						</tr>
						<tr>
							<td class="td-remove-border-left">
								1
							</td>
							<td class="td-label">
								<fmt:message key="db.sysInfo.dev_1"/>
							</td>
							<td>
								<sf:input path="devCount_1" class="netTable-input" />
								<fmt:message key="db.sysInfo.ge"/>
							</td>
							<td>
								<sf:radiobutton path="devUsed_1" label=""/>
							</td>
							<td>
								<sf:radiobutton path="devUsed_1" label=""/>
							</td>
							<td class="td-remove-border-right">
								<sf:radiobutton path="devUsed_1" label=""/>
								<sf:input path="devRatio_1" class="netTable-input"/>%
							</td>
						</tr>
						<tr>
							<td class="td-remove-border-left">
								2
							</td>
							<td class="td-label">
								<fmt:message key="db.sysInfo.dev_2"/>
							</td>
							<td>
								<sf:input path="devCount_2" class="netTable-input"/>
								<fmt:message key="db.sysInfo.ge"/>
							</td>
							<td>
								<sf:radiobutton path="devUsed_2" label=""/>
							</td>
							<td>
								<sf:radiobutton path="devUsed_2" label=""/>
							</td>
							<td class="td-remove-border-right">
								<sf:radiobutton path="devUsed_2" label=""/>
								<sf:input path="devRatio_2" class="netTable-input"/>%
							</td>
						</tr>
						<tr>
							<td class="td-remove-border-left">
								3
							</td>
							<td class="td-label">
								<fmt:message key="db.sysInfo.dev_3"/>
							</td>
							<td>
								<sf:input path="devCount_3" class="netTable-input"/>
								<fmt:message key="db.sysInfo.ge"/>
							</td>
							<td>
								<sf:radiobutton path="devUsed_3" label=""/>
							</td>
							<td>
								<sf:radiobutton path="devUsed_3" label=""/>
							</td>
							<td class="td-remove-border-right">
								<sf:radiobutton path="devUsed_3" label=""/>
								<sf:input path="devRatio_3" class="netTable-input"/>%
							</td>
						</tr>
						<tr>
							<td class="td-remove-border-left">
								4
							</td>
							<td class="td-label">
								<fmt:message key="db.sysInfo.dev_4"/>
							</td>
							<td>
								<sf:input path="devCount_4" class="netTable-input"/>
								<fmt:message key="db.sysInfo.ge"/>
							</td>
							<td>
								<sf:radiobutton path="devUsed_4" label=""/>
							</td>
							<td>
								<sf:radiobutton path="devUsed_4" label=""/>
							</td>
							<td class="td-remove-border-right">
								<sf:radiobutton path="devUsed_4" label=""/>
								<sf:input path="devRatio_4" class="netTable-input"/>%
							</td>
						</tr>
						<tr>
							<td class="td-remove-border-left">
								5
							</td>
							<td class="td-label">
								<fmt:message key="db.sysInfo.dev_5"/>
							</td>
							<td>
								<sf:input path="devCount_5" class="netTable-input"/>
								<fmt:message key="db.sysInfo.ge"/>
							</td>
							<td>
								<sf:radiobutton path="devUsed_5" label=""/>
							</td>
							<td>
								<sf:radiobutton path="devUsed_5" label=""/>
							</td>
							<td class="td-remove-border-right">
								<sf:radiobutton path="devUsed_5" label=""/>
								<sf:input path="devRatio_5" class="netTable-input"/>%
							</td>
						</tr>
						<tr>
							<td class="td-remove-border-bottom td-remove-border-left">
								6
							</td>
							<td class="td-label td-remove-border-bottom">
								<fmt:message key="db.sysInfo.dev_6"/>
								<sf:input path="devOther_6" class="netTable-input"/>
							</td>
							<td class="td-remove-border-bottom">
								<sf:input path="devCount_6" class="netTable-input"/>
								<fmt:message key="db.sysInfo.ge"/>
							</td>
							<td class="td-remove-border-bottom">
								<sf:radiobutton path="devUsed_6" label=""/>
							</td>
							<td class="td-remove-border-bottom">
								<sf:radiobutton path="devUsed_6" label=""/>
							</td>
							<td class="td-remove-border-right td-remove-border-bottom">
								<sf:radiobutton path="devUsed_6" label=""/>
								<sf:input path="devRatio_6" class="netTable-input"/>%
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="td-label">
					<fmt:message key="db.sysInfo.sysUseredSer"/>
				</td>
				<td colspan="3" class="td-padding">
					<table  class="table form-horizontal table-bordered td-remove-border-top td-remove-border-right td-remove-border-bottom td-remove-border-left table-space">
						<tr>
							<td rowspan="2" class="td-label td-remove-border-top td-remove-border-left">
								<fmt:message key="db.sysInfo.sNumber"/>
							</td>
							<td rowspan="2" colspan="2" class="td-label td-remove-border-top">
								<fmt:message key="db.sysInfo.serUsed"/>
							</td>
							<td colspan="3" class="td-label td-remove-border-top td-remove-border-right">
								<fmt:message key="db.sysInfo.serDutyType"/>
							</td>
						</tr>
						<tr>
							<td class="td-label">
								<fmt:message key="db.sysInfo.Industry"/>
							</td>
							<td class="td-label">
								<fmt:message key="db.sysInfo.otherProvider"/>
							</td>
							<td class="td-label td-remove-border-right">
								<fmt:message key="db.sysInfo.foreignProvide"/>
							</td>
						</tr>
						<tr>
							<td  width="7%" class="td-remove-border-left">
								1
							</td>
							<td  width="18%" class="td-label">
								<fmt:message key="db.sysInfo.ser_1"/>
							</td>
							<td width="17%">
								<mh:dictionary key="whether" var="whether"/>
								<sf:radiobuttons path="serUsered_1" items="${whether }"/>
							</td>
							<td>
								<sf:radiobutton path="serBelong_1" label=""/>
							</td>
							<td>
								<sf:radiobutton path="serBelong_1" label=""/>
							</td>
							<td class="td-remove-border-right">
								<sf:radiobutton path="serBelong_1" label=""/>
							</td>
						</tr>
						<tr>
							<td class="td-remove-border-left">
								2
							</td>
							<td class="td-label">
								<fmt:message key="db.sysInfo.ser_2"/>
							</td>
							<td>
								<mh:dictionary key="whether" var="whether"/>
								<sf:radiobuttons path="serUsered_2" items="${whether }"/>
							</td>
							<td>
								<sf:radiobutton path="serBelong_2" label=""/>
							</td>
							<td>
								<sf:radiobutton path="serBelong_2" label=""/>
							</td>
							<td class="td-remove-border-right">
								<sf:radiobutton path="serBelong_2" label=""/>
							</td>
						</tr>
						<tr>
							<td class="td-remove-border-left">
								3
							</td>
							<td class="td-label">
								<fmt:message key="db.sysInfo.ser_3"/>
							</td>
							<td>
								<mh:dictionary key="whether" var="whether"/>
								<sf:radiobuttons path="serUsered_3" items="${whether }"/>
							</td>
							<td>
								<sf:radiobutton path="serBelong_3" label=""/>
							</td>
							<td>
								<sf:radiobutton path="serBelong_3" label=""/>
							</td>
							<td class="td-remove-border-right">
								<sf:radiobutton path="serBelong_3" label=""/>
							</td>
						</tr>
						<tr>
							<td class="td-remove-border-left">
								4
							</td>
							<td class="td-label">
								<fmt:message key="db.sysInfo.ser_4"/>
							</td>
							<td>
								<mh:dictionary key="whether" var="whether"/>
								<sf:radiobuttons path="serUsered_4" items="${whether }"/>
							</td>
							<td>
								<sf:radiobutton path="serBelong_4" label=""/>
							</td>
							<td>
								<sf:radiobutton path="serBelong_4" label=""/>
							</td>
							<td class="td-remove-border-right">
								<sf:radiobutton path="serBelong_4" label=""/>
							</td>
						</tr>
						<tr>
							<td class="td-remove-border-left">
								5
							</td>
							<td class="td-label">
								<fmt:message key="db.sysInfo.ser_5"/>
							</td>
							<td>
								<mh:dictionary key="whether" var="whether"/>
								<sf:radiobuttons path="serUsered_5" items="${whether }"/>
							</td>
							<td>
								<sf:radiobutton path="serBelong_5" label=""/>
							</td>
							<td>
								<sf:radiobutton path="serBelong_5" label=""/>
							</td>
							<td class="td-remove-border-right">
								<sf:radiobutton path="serBelong_5" label=""/>
							</td>
						</tr>
						<tr>
							<td class="td-remove-border-left"> 
								6
							</td>
							<td class="td-label">
								<fmt:message key="db.sysInfo.ser_6"/>
							</td>
							<td>
								<mh:dictionary key="whether" var="whether"/>
								<sf:radiobuttons path="serUsered_6" items="${whether }"/>
							</td>
							<td>
								<sf:radiobutton path="serBelong_6" label=""/>
							</td>
							<td>
								<sf:radiobutton path="serBelong_6" label=""/>
							</td>
							<td class="td-remove-border-right">
								<sf:radiobutton path="serBelong_6" label=""/>
							</td>
						</tr>
						<tr>
							<td class="td-remove-border-left">
								7
							</td>
							<td class="td-label">
								<fmt:message key="db.sysInfo.ser_7"/>
							</td>
							<td>
								<mh:dictionary key="whether" var="whether"/>
								<sf:radiobuttons path="serUsered_7" items="${whether }"/>
							</td>
							<td>
								<sf:radiobutton path="serBelong_7" label=""/>
							</td>
							<td>
								<sf:radiobutton path="serBelong_7" label=""/>
							</td>
							<td class="td-remove-border-right">
								<sf:radiobutton path="serBelong_7" label=""/>
							</td>
						</tr>
						<tr>
							<td class="td-remove-border-bottom td-remove-border-left">
								8
							</td>
							<td  class="td-label td-remove-border-bottom">
								<fmt:message key="db.sysInfo.dev_6"/>
								<sf:input path="serUsered_other" class="netTable-input"/>
							</td>
							<td class="td-remove-border-bottom">
								<mh:dictionary key="whether" var="whether"/>
								<sf:radiobuttons path="serUsered_8" items="${whether }"/>
							</td>
							<td class="td-remove-border-bottom">
								<sf:radiobutton path="serBelong_8" label=""/>
							</td>
							<td class="td-remove-border-bottom">
								<sf:radiobutton path="serBelong_8" label=""/>
							</td>
							<td class="td-remove-border-right td-remove-border-bottom">
								<sf:radiobutton path="serBelong_8" label=""/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="td-label">
					<fmt:message key="db.sysInfo.exOrganName"/>
				</td>
				<td colspan="3" >
					<sf:input path="exOrganName" class="td-input"  />
				</td>
			</tr>
			<tr>
				<td colspan="2" class="td-label">
					<fmt:message key="db.sysInfo.exTime"/>
				</td>
				<td colspan="3">
					<sf:input id="exTime" path="exTime" class="td-input" />
					<hh:date id="exTime"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="td-label">
					<fmt:message key="db.sysInfo.sonSystem"/>
				</td>
				<td colspan="3">
					<mh:dictionary key="bool" var="bool"/>
					<sf:radiobuttons path="sonSystem" items="${bool}"/>
					<span><fmt:message key="db.sysInfo.ifOrNot"/></span>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="td-label">
					<fmt:message key="db.sysInfo.parentSys"/>
				</td>
				<td colspan="3">
					<sf:input path="parentSys" class="td-input" />
				</td>
			</tr>
			<tr>
				<td colspan="2" class="td-label">
					<fmt:message key="db.sysInfo.parentSysOrgan"/>
				</td>
				<td colspan="3">
					<sf:input path="parentSysOrgan" class="td-input" />
				</td>
			</tr>
			
		</table>
		<div class="form-group melon-buttons">
			<button class="btn btn-primary"   id="btnSave" ><fmt:message key="button.save"/></button>
			<a class="btn btn-default"   href="<c:url value="/db/home"/>" ><fmt:message key="button.backList"/></a>
		</div>
	</sf:form>
</div>


<script type="text/javascript">
	$(document).ready(function(){
		var ss = $("#serScope")[0].children[3];
		var dd = $('<input type="text" class="netTable-input"/>').insertAfter(ss);
		$('<lable>个</label>').insertAfter(dd);
		var ff =  $("#serScope")[0].children[1];
		var ee = $('<input type="text" class="netTable-input"/>').insertAfter(ff);
		$('<lable>个</label>').insertAfter(ee);

		var organ = $('form');
		$("#btnSave").on("click", function(event) {
			var valid = form.valid(),
	       		infoId ='${infoId}',
	       		url = "";
	        if(valid) {
	        	if(infoId!=""){
	        		url = '<c:url value="/db/sysInfo/edit/"/>'+infoId;
	        	}else{
	        		url = '<c:url value="/db/sysInfo/create"/>';
	        	}
	        }
        	form.attr("action",url);
        	form.submit();
	        //阻止默认的提交动作
	        event.preventDefault(); 
		});
	});

</script>
