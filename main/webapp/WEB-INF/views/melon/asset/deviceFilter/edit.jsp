<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>

<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<script src="<mh:theme code="jquery.steps.js"/>"></script>

<style type="text/css">
	#personMenuDropDown{
		margin-left:14px;
	}
</style>

<div style="height:200px;" id="loadForm">
	<sf:form modelAttribute="deviceFilter" role="form" cssClass="form-horizontal">
		<sf:hidden path="id"/>
		<!-- 到期提醒标题 -->
		<div style="margin-bottom:10px;">
			<div style="display:inline-block; margin-right:70px;">
				<fmt:message key="asset.deviceFilter.orderName"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<sf:input path="orderName" style="width:199px;" class="input-form"/>
			</div>
			<div style="display:inline-block;">
				<fmt:message key="asset.deviceFilter.remindTime"/>
				<hh:date id="remindTime"/>
				<sf:input path="remindTime" style="width:199px;" class="input-form"/>
			</div>
		</div>
		<hr style="margin-top: 0px; margin-bottom: 10px;" />
		<div style="margin-bottom:10px;">
			<fmt:message key="asset.deviceFilter.companyName" />
			<sf:hidden path="companyName"/>
			<sf:hidden path="companyId"/>
			<!-- 准备单位选择 -->
			<mh:dropdown id="organMenu">
				<jsp:attribute name="values">
					${deviceFilter.companyName}
				</jsp:attribute>
				<jsp:attribute name="menus">
					<li role="presentation" id="organButtons">
						<div class="input-group">
						  <span class="input-group-btn">
						    <a class="btn btn-primary btn-sm" type="button" href="javascript:void(0);" id="btnSearch">
								<span class="glyphicon glyphicon-search"></span>
							</a>
					      </span>
					      <input type="text" class="form-control" style="height:30px;" id="organSearch">
					      <span class="input-group-btn">
					        <a class="btn btn-primary btn-sm" href="javascript:void(0);" type="button">
					        	<fmt:message key="button.sure"/>
					        </a>
					      </span>
					    </div>
					</li>
					<li role="presentation" id="organSelect" style="height:250px;overflow:auto;">
						<mh:tree id="organ"
								rootId="1"
								url="organ/organization/find?includeDepart=false"
								sortName="order"
								scriptSelf="false">
						</mh:tree>
					</li>
				</jsp:attribute>
			</mh:dropdown>
		</div>
		<div style="margin-bottom:10px;">
			<!-- 设备责任人 -->
			<div style="display:inline-block;">
				<fmt:message key="asset.deviceFilter.chargeName"/>
				<sf:hidden path="chargeNames"/>
				<sf:hidden path="chargeIds"/>
				<%-- 准备责任人选择 --%>
				<mh:dropdown id="personMenu">
					<jsp:attribute name="values">
					</jsp:attribute>
					<jsp:attribute name="menus">
						<li role="presentation" id="personButtons">
							<div class="input-group">
							  <span class="input-group-btn">
							    <a class="btn btn-primary btn-sm" type="button" href="javascript:void(0);" id="personBtn">
									<span class="glyphicon glyphicon-search"></span>
								</a>
						      </span>
						      <input type="text" class="form-control" style="height:30px;" id="personSearch">
						      <span class="input-group-btn">
						        <a id="choosePerson" class="btn btn-primary btn-sm" href="javascript:void(0);" type="button">
						        	<fmt:message key="button.sure"/>
						        </a>
						      </span>
						    </div>
						</li>
						<li role="presentation" id="personSelect" style="height:160px;overflow:auto;">
							<mh:tree id="person"
									rootId="1"
									url="security/account/findUsers"
									sortName="order"
									scriptSelf="false"
									multiSelect="true">
							</mh:tree>
						</li>
					</jsp:attribute>
				</mh:dropdown>
			</div>
			
			<div style="display:inline-block; margin-left:2px;">
				<mh:dictionary key="assetRelation" var="relation"/>
				<sf:radiobuttons path="relation" items="${relation }"/>
			</div>
			
			<div style="display:inline-block;">
				<!-- 设备类型 -->
				<div>
					<fmt:message key="asset.deviceFilter.typeName"/>
					<sf:hidden path="typeNames"/>
					<sf:hidden path="typeIds"/>
					
					<%-- 准备类型选择 --%>
					<mh:dropdown id="typeMenu">
						<jsp:attribute name="values">
						</jsp:attribute>
						<jsp:attribute name="menus">
							<li role="presentation" id="typeButtons" >
								<div class="input-group">
								  <span class="input-group-btn">
								    <a class="btn btn-primary btn-sm" type="button" href="javascript:void(0);" id="typeBtn">
										<span class="glyphicon glyphicon-search"></span>
									</a>
							      </span>
							      <input type="text" class="form-control" style="height:30px;" id="typeSearch">
							      <span class="input-group-btn">
							        <a id="chooseType" class="btn btn-primary btn-sm" href="javascript:void(0);" type="button">
							        	<fmt:message key="button.sure"/>
							        </a>
							      </span>
							    </div>
							</li>
							<li role="presentation" id="typeSelect" style="height:160px;overflow:auto;">
								<mh:tree id="type"
										rootId="1"
										url="asset/type/find"
										sortName="order"
										scriptSelf="false"
										multiSelect="true">
								</mh:tree>
							</li>
						</jsp:attribute>
					</mh:dropdown>
				</div>
			</div>
		</div>
		
		<!-- 到期提醒标题 -->
		<div style="margin-bottom:10px;">
			<div style="display:inline-block; margin-right:80px;">
				<fmt:message key="asset.deviceFilter.startTime"/>
				<hh:date id="startTime"/>
				<sf:input path="startTime" style="width:170px;" class="input-form"/>
			</div>
			<div style="display:inline-block;">
				<fmt:message key="asset.deviceFilter.endTime"/>
				<hh:date id="endTime"/>
				<sf:input path="endTime" style="width:171px;" class="input-form"/>
			</div>
		</div>
		
		<div style="float:right;">
			<mh:button class="btn btn-primary btn-sm" id="findDeviceSelect"><fmt:message key="button.query"/></mh:button>
			<mh:button class="btn btn-primary btn-sm" id="btnSave"><fmt:message key="button.save"/></mh:button>
		</div>
	</sf:form>
</div>
<script type="text/javascript" src="<mh:theme code='jquery.vaildate.js'/>"></script>
<script type="text/javascript">

	$(document).ready(function() {
		//处理组织机构选择
		doOrganSearch();
		//处理责任人
		doPerson();
		//处理设备类型
		doType();
		
		//新增
		$("#saveDeviceFilter").on("click",function(){
			$("#loadForm").load('<c:url value="/asset/deviceFilter/edit"/>');
		});
	 	
		//保存
		$("#btnSave").on("click",function(event){
			 var data = $('#deviceFilter').serialize(),
			 	 getId = $("input[name='id']").val();
			 if(getId == ""){
				 getId = null;
			 }
			 $.post('<c:url value="/asset/deviceFilter/create/"/>'+getId, data, function(data){
				 window.location.reload();
			  }); 
			  //阻止默认的提交动作
			  event.preventDefault();
		   });
		
		//明细   修改
		$(".dfShow").click(function() {
			var id = $(this).attr("id");
			$("#loadForm").load("<c:url value='/asset/deviceFilter/update/' />"+id);
			var showData = $("#showData");
			 $.post('<c:url value="/asset/deviceFilter/list/"/>'+id, function(datas){
				 $("#showData").find("tr:gt(0)").remove();
				 $.each(datas, function(index, item) {
					 var tr = $("#template").clone().removeClass("temp");
					 tr.find("td:eq(0)").text(item.name);
					 tr.find("td:eq(1)").text(item.typeName);
					 tr.find("td:eq(2)").text(item.organName);
					 tr.find("td:eq(3)").text(item.chargeName);
					 tr.find("td:eq(4)").text(item.deadline);
					 tr.appendTo(showData);
				 });
			  });
			return false;
		}); 
		
		//查询
		$("#findDeviceSelect").on("click",function(){
			 var data = $('#deviceFilter').serialize(),
			 	 showData = $("#showData");
			 $.post('<c:url value="/asset/deviceFilter/select"/>', data, function(datas){
				 $("#showData").find("tr:gt(0)").remove();
				 $.each(datas, function(index, item) {
					 var tr = $("#template").clone().removeClass("temp");
					 tr.find("td:eq(0)").text(item.name);
					 tr.find("td:eq(1)").text(item.typeName);
					 tr.find("td:eq(2)").text(item.organName);
					 tr.find("td:eq(3)").text(item.chargeName);
					 tr.find("td:eq(4)").text(item.deadline);
					 tr.appendTo(showData);
				 });
			  });  
		});
		
		//获取选中的人员
		personTree.onReady(function() {
			//设置选中节点
			var ids = '${deviceFilter.chargeIds}',//从后台获取选中人员id
				idArray = ids.split(","),
				text = "",
				checkNodes = new Array();
			//将人员id转为数组
			$.each(idArray, function(index, item) {
				checkNodes.push(item);
			});
			//设置选中节点
			this.setChecked(checkNodes);
			//选中节点的内容拼接
			$.each(this.getChecked(true), function(index, item) {
				text += item.text+",";
			});
			//内容赋值
			$("#personMenu .dropdown-text").text(text);
			
		});
		
		//获取选中的设备类型
		typeTree.onReady(function() {
			//设置选中节点
			var ids = '${deviceFilter.typeIds}',//从后台获取选中人员id
				idArray = ids.split(","),
				text = "",
				checkNodes = new Array();
			//将人员id转为数组
			$.each(idArray, function(index, item) {
				checkNodes.push(item);
			});
			//设置选中节点
			this.setChecked(checkNodes);
			//选中节点的内容拼接
			$.each(this.getChecked(true), function(index, item) {
				text += item.text+",";
			});
			//内容赋值
			$("#typeMenu .dropdown-text").text(text);
			
		});
		
	});


	//处理组织机构选择
	function doOrganSearch() {
		var organSearch = $("#organSearch"),
			organText = $("#organMenu").find(".dropdown-text"),
			companyName = $("#companyName"),
			companyId = $("#companyId"),
			btnSearch = $("#btnSearch");
			checkOrgan = "$(deviceFilter.companyId)";
		//阻止默认的提交动作
		$("#organSelect").on("click", function(event) {
			event.stopPropagation();
		});
		//处理查询
		organSearch.on("click", function(event) {
			event.stopPropagation();
		});
		btnSearch.on("click", function(event) {
			organTree.search($.trim(organSearch.val()));
			event.stopPropagation();
		});
		//处理选择
		organTree.onSelect(function(data) {
			if(data.type == 1) {
				organText.text(data.text);
				companyName.val(data.text);
				companyId.val(data.id);
			}
		});
	}
	
	//处理负责人
	function doPerson(){
		var personSearch = $("#personSearch"),
			personText = $("#personMenu").find(".dropdown-text"),
			btnSearch = $("#personBtn"),
			persons = new Array(); 
		
		$("#personMenu #1>.jstree-checkbox").remove();
		//阻止默认的提交动作
		$("#personSelect").on("click", function(event) {
			event.stopPropagation();
		});
		//处理查询
		personSearch.on("click", function(event) {
			event.stopPropagation();
		});
		btnSearch.on("click", function(event) {
			personTree.search($.trim(personSearch.val()));
			event.stopPropagation();
		});
		
		//负责人勾选事件
		personTree.onCheck(function(data) {
			checkPerson(persons,personText);
		});
		
		//负责人勾掉事件
		personTree.onUnCheck(function(data){
			checkPerson(persons,personText);
		}); 
		
	}

	//负责人赋值
	function checkPerson(persons,personText){
		persons = personTree.getChecked(true);//获取选中的节点
			var name = new Array(),
				id = new Array();
			for ( var i = 0; i < persons.length; i++) {
				if(persons[i].id != 1){
					name.push(persons[i].text);
					id.push(persons[i].id);
				};
			}
			personText.text(name.join(","));
			$("#chargeIds").val(id.join(","));
			$("#chargeNames").val(personText);
			
	}

	//处理设备类型
	function doType(){
		var typeSearch = $("#typeSearch"),
			typeText = $("#typeMenu").find(".dropdown-text"),
			btnSearch = $("#typeBtn"),
			types = new Array(); 
		
		$("#typeMenu #1>.jstree-checkbox").remove();
		//阻止默认的提交动作
		$("#typeSelect").on("click", function(event) {
			event.stopPropagation();
		});
		//处理查询
		typeSearch.on("click", function(event) {
			event.stopPropagation();
		});
		btnSearch.on("click", function(event) {
			typeTree.search($.trim(typeSearch.val()));
			event.stopPropagation();
		});
		
		//设备类型勾选事件
		typeTree.onCheck(function(data) {
			checkType(types,typeText);
		});
		
		//设备类型勾掉事件
		typeTree.onUnCheck(function(data){
			checkType(types,typeText);
		}); 
		
	}
	
	//设备类型赋值
	function checkType(types,typeText){
		types = typeTree.getChecked(true);//获取选中的节点
			var name = new Array(),
				id = new Array();
			for ( var i = 0; i < types.length; i++) {
				if(types[i].id != 1){
					name.push(types[i].text);
					id.push(types[i].id);
				};
			}
			typeText.text(name.join(","));
			$("#typeIds").val(id.join(","));
			$("#typeNames").val(typeText);
	}

</script>

