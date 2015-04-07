<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>

<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>

<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>
<link rel="stylesheet" href="<mh:theme code="jquery.steps.css"/>" media="all" />
<script src="<mh:theme code="jquery.steps.js"/>"></script>



<div class="col-page-title">
	<c:if test="${empty assetGroup.id}">
		<fmt:message key='asset.assetGroup.create' />
	</c:if>
	<c:if test="${!empty assetGroup.id}">
		${assetGroup.name}
	</c:if>
</div>

<sf:form modelAttribute="assetGroup" role="form" cssClass="form-horizontal">

	<sf:hidden path="id" />

	<sf:hidden path="path" />

	<sf:hidden path="code" />
	
	<sf:hidden path="parentId" />

	<sf:hidden path="createTime" />
	<div class="form-group">
		<sf:label path="name" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.abstract.name" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="name" cssClass="form-control" maxlength="50" />
			<sf:errors path="name" />
		</div>
	</div>


<%-- 	<div class="form-group">
		<sf:label path="order" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.assetType.order" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:input path="order" cssClass="form-control" maxlength="30" />
			<sf:errors path="order" />
			<label class="form-hint"><fmt:message key="hint.order"/></label> 
		</div>
	</div> --%>


	<%-- <div class="form-group">
		<sf:label path="netType" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.assetGroup.netType" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<mh:dictionary var="netType" key="netType" />
			<sf:radiobuttons path="netType" items="${netType}" />
			<sf:errors path="netType" />
		</div>
	</div> --%>
	
	<%--所属单位--%>
	<div class="form-group">
		<sf:label path="organId" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.abstract.organName" />
		</sf:label>
		<div class="col-xs-9 form-field">
			<sf:hidden path="organId"/>
			<sf:hidden path="organName"/>
			<sf:hidden path="organPath"/>
			<%-- 准备单位选择 --%>
			<mh:dropdown id="organMenu">
				<jsp:attribute name="values">
					${assetGroup.organName}
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
			
			<sf:errors path="organId" />
		</div>
	</div>

	<%--类型--%>
	<div class="form-group">
		<sf:label path="typeId" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.group.type" />
		</sf:label>
		<div class="col-xs-9 form-field">
			<sf:hidden path="typeName"/>
			<sf:hidden path="typePath"/>
			<sf:hidden path="typeId"/>
			
			<mh:dropdown id="typeMenu">
				<jsp:attribute name="values">
					${assetGroup.typeName}
				</jsp:attribute>
				<jsp:attribute name="menus">
					<li role="presentation" id="typeButtons" >
						<div class="input-group">
						  <span class="input-group-btn">
						    <a class="btn btn-primary btn-sm" type="button" href="javascript:void(0);" id="btnSearch">
								<span class="glyphicon glyphicon-search"></span>
							</a>
					      </span>
					      <input type="text" class="form-control" style="height:30px;" id="typeSearch">
					      <span class="input-group-btn">
					        <a class="btn btn-primary btn-sm" href="javascript:void(0);" type="button">
					        	<fmt:message key="button.sure"/>
					        </a>
					      </span>
					    </div>
					</li>
					<li role="presentation" id="typeSelect" style="height:160px;overflow:auto;">
						<mh:tree id="type"
								rootId="231"
								url="asset/type/find"
								sortName="order"
								scriptSelf="false">
						</mh:tree>
					</li>
				</jsp:attribute>
			</mh:dropdown>
			
			<sf:errors path="typeId" />
		</div>
	</div>
	
	<%-- 物理环境 --%>
	<div class="form-group">
		<sf:label path="enviId" cssClass="col-xs-3 control-label">
			<fmt:message key="asset.device.envi" />
		</sf:label>
		<div class="col-xs-9 form-field">
			<sf:hidden path="enviName"/>
			<sf:hidden path="enviPath"/>
			<sf:hidden path="enviId"/>
			<%-- 准备类型选择 --%>
			<mh:dropdown id="enviMenu"  dropup="true">
				<jsp:attribute name="values">
					${assetGroup.enviName}
				</jsp:attribute>
				<jsp:attribute name="menus">
					<li role="presentation" id="enviButtons" >
						<div class="input-group">
							<span class="input-group-btn">
								<a class="btn btn-primary btn-sm" type="button" href="javascript:void(0);" id="btnSearch">
									<span class="glyphicon glyphicon-search"></span>
								</a>
							</span>
							<input type="text" class="form-control" style="height:30px;" id="enviSearch">
							<span class="input-group-btn">
								<a class="btn btn-primary btn-sm" href="javascript:void(0);" type="button">
						        	<fmt:message key="button.sure"/>
						        </a>
						    </span>
						 </div>
					</li>
					<li role="presentation" id="enviSelect" style="height:220px;overflow:auto;">
						<mh:tree id="envi"
								rootId="1"
								url="asset/environment/find"
								sortName="order"
								scriptSelf="false">
						</mh:tree>
					</li>
				</jsp:attribute>
			</mh:dropdown>
			<sf:errors path="enviId" />
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="chargerName" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.abstract.chargeName" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:hidden path="chargerName" />
			<sf:input path="chargerId" cssClass="form-control" maxlength="30" />
			<hh:select id="chargerId" onceUrl="security/account/findUsers" />
			<sf:errors path="chargerId" />
		</div>
	</div>

	<%-- <div class="form-group">
		<sf:label path="guardRank" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.assetGroup.guardRank" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<mh:dictionary var="guardRank" key="guardRank" />
			<sf:radiobuttons path="guardRank" items="${guardRank}" />
			<sf:errors path="guardRank" />
		</div>
	</div> 

	<div class="form-group">
		<sf:label path="remarks" cssClass="col-xs-3 control-label">
			<fmt:message key="asset.abstract.remarks" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<sf:textarea path="remarks" cssClass="form-control" maxlength="500" />
		</div>
	</div>--%>

	<div class="form-group">
		<sf:label path="useState" cssClass="col-xs-3 control-label">
			<fmt:message key="asset.group.useState" />
		</sf:label>

		<div class="col-xs-9 form-field">
			<mh:dictionary var="useState" key="useState" />
			<sf:radiobuttons path="useState" items="${useState}" />
		</div>
	</div>
	

	<div class="form-group form-button-panel">
		<div class="col-xs-9 col-xs-offset-3 form-buttons">
			<mh:button class="btn btn-primary" id="btnSave">
				<fmt:message key="button.save" />
			</mh:button>
		</div>
	</div>

</sf:form>
<script type="text/javascript">
	$(document).ready(function() {
		
		var assetGroupForm = $("#assetGroup");
		//验证表单
		assetGroupForm.validate({
			rules : {
				name : {
					required : true,
					maxlength : [ 50 ]
				},
				code : {
					required : true,
					maxlength : [ 50 ]
				},
				chargerId : {
					required : true
				}
			}
		});
		//注册按钮事件,保存成功则添加节点到树中
		assetGroupForm.find("#btnSave").on("click",function(event) {
			var datas = assetGroupForm.serialize(), 
				url = assetGroupForm.attr("action");
			//动态加载的页面必须手动验证一次
			if (assetGroupForm.valid()) {
				$.post(url,datas,function(response) {
					var domain = response.domain, 
						parentId = domain.parentId;
					//新建操作
					if (response.actionType == 0) {
						assetGroupTree.create(parentId,domain,"last",function() {
							assetGroupTree.setSelected(parentId,false);
							assetGroupTree.setSelected(domain.id,true);
						});
					} else {
						//修改操作,将数据更新到节点
						assetGroupTree.update(domain.id,domain);
					}
					//输出提示信息
					assetGroupTree.sort(domain.parentId);
					Logger.success(response.message);
					});
				}
			event.preventDefault();
			});
		
		//负责人
		$("#chargerId").on("change", function(e) {
			var data = e.added;
			$("#chargerName").val(data.name);
		});
		
		//类型处理
		doTypeSearch();
		//单位处理
		doOrganSearch();
		//物理环境
		doEnvi();
		
		
	});
	
	//处理设备类型
	function doTypeSearch(){
		var typeSearch = $("#typeSearch"),
			typeText = $("#typeMenu").find(".dropdown-text"),
			typeName = $("#typeName"),
			typeId = $("#typeId"),
			typePath = $("#typePath"),
			btnSearch = $("#btnSearch");
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
		//处理选择
		typeTree.onSelect(function(data) {
			typeText.text(data.text);
			typeName.val(data.name);
			typeId.val(data.id);
			typePath.val(data.path);
		});
	}
	
	//处理物理环境
	function doEnvi(){
		var enviSearch = $("#enviSearch"),
		enviText = $("#enviMenu").find(".dropdown-text"),
		enviName = $("#enviName"),
		enviId = $("#enviId"),
		enviPath = $("#enviPath"),
		btnSearch = $("#btnSearch");
		//阻止默认的提交动作
		$("#enviSelect").on("click", function(event) {
			event.stopPropagation();
		});
		//处理查询
		enviSearch.on("click", function(event) {
			event.stopPropagation();
		});
		btnSearch.on("click", function(event) {
			enviTree.search($.trim(enviSearch.val()));
			event.stopPropagation();
		});
		//处理选择
		enviTree.onSelect(function(data) {
			enviText.text(data.text);
			enviName.val(data.name);
			enviId.val(data.id);
			enviPath.val(data.path);
		});
	}

	//处理组织机构选择
	function doOrganSearch() {
		var organSearch = $("#organSearch"),
			organText = $("#organMenu").find(".dropdown-text"),
			organName = $("#organName"),
			organId = $("#organId"),
			organPath = $("#organPath"),
			btnSearch = $("#btnSearch");
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
				organName.val(data.text);
				organId.val(data.id);
				organPath.val(data.path);
			}
		});
	}
</script>