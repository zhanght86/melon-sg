<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper"%>

<link rel="stylesheet" href="<mh:theme code='jquery.fileupload.css'/>" media="all" />
<link rel="stylesheet" href="<mh:theme code='jquery.select.css'/>" media="all" />
<link rel="stylesheet" href="<mh:theme code='jquery.jstree.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='ckeditor.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='jquery.jstree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='melon.ajaxtree.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='jquery.select.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='jquery.fileupload.js'/>"></script>
<script type="text/javascript" src="<mh:theme code='jquery.vaildate.js'/>"></script>
<style type="text/css">
	.fileinput-button {
		margin-top:10px;
	}
	.security-upload .fileinput-button{
	}
	.file-item-group{
		margin-left:168px;
	}
	.certificates-upload .fileinput-button , .paperNum-upload .fileinput-button{
		margin-left:170px;
	}
	.dropdown-text {
		overflow: hidden
	}
</style>
<mh:dictionary key="bool" var="booleanEnum" />
<mh:dictionary key="boolInt" var="boolIntEnum" />
<mh:dictionary key="interstaff.partjob" var="partJobEnum"/>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	
	<sf:form modelAttribute="regulation" role="form" cssClass="form-horizontal">
	
		<sf:hidden path="id" />
		<sf:hidden path="taskId" />
		<sf:hidden path="parentId" />
		<sf:hidden path="origin" />
	  
		<div class="form-group">
			<sf:label path="name" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required" />
				<fmt:message key="melon.regulation.name" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<sf:input path="name" cssClass="form-control" maxlength="100" />
				<sf:errors path="name" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="code" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.regulation.code" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<sf:input path="code" cssClass="form-control" maxlength="100" />
				<sf:errors path="code" />
			</div>
		</div>
	
		<div class="form-group">
			<sf:label path="number" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.regulation.number" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<sf:input path="number" cssClass="form-control" maxlength="100" />
				<sf:errors path="number" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="type" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/>
				<fmt:message key="melon.regulation.type" />
			</sf:label>
	
			<div class="col-xs-9 form-field" id="typeCheck">
				<sf:hidden path="type"/>
				<sf:hidden path="typeName"/>
				<!-- 准备单位选择 -->
				<mh:dropdown id="organMenu">
					<jsp:attribute name="values">
						${regulation.typeName}
					</jsp:attribute>
					<jsp:attribute name="menus">
						<li role="presentation" id="organButtons">
							<div class="input-group">
							  <span class="input-group-btn">
							    <a class="btn btn-primary btn-sm btnSearch" type="button" href="javascript:void(0);" >
									<span class="glyphicon glyphicon-search"></span>
								</a>
						      </span>
						      <input type="text" class="form-control organSearch" style="height:30px;" >
						      <span class="input-group-btn">
						        <a class="btn btn-primary btn-sm btnSearch" href="javascript:void(0);" type="button">
						        	<fmt:message key="button.sure"/>
						        </a>
						      </span>
						    </div>
						</li>
						<li role="presentation" class="organSelect" style="height:250px;overflow:auto;">
							<mh:tree id="type"
									rootId="1"
									url="asset/regulationType/find"
									sortName="order"
									scriptSelf="false"
									>
							</mh:tree>
						</li>
					</jsp:attribute>
				</mh:dropdown>
				<sf:errors path="type" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="level" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.regulation.leveL" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<sf:input path="level" cssClass="form-control" maxlength="100" readonly="true" />
				<sf:errors path="level" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="issueOrganId" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/>
				<fmt:message key="melon.regulation.issueOrgan" />
			</sf:label>
	
			<div class="col-xs-9 form-field" id="issueOrganSelect">
				<sf:hidden path="issueOrganName"/>
				<sf:hidden path="issueOrganId"/>
				<!-- 准备单位选择 -->
				<mh:dropdown id="organMenu">
					<jsp:attribute name="values">
						${regulation.issueOrganName}
					</jsp:attribute>
					<jsp:attribute name="menus">
						<li role="presentation" id="organButtons">
							<div class="input-group">
							  <span class="input-group-btn">
							    <a class="btn btn-primary btn-sm btnSearch" type="button" href="javascript:void(0);" >
									<span class="glyphicon glyphicon-search"></span>
								</a>
						      </span>
						      <input type="text" class="form-control organSearch" style="height:30px;" >
						      <span class="input-group-btn">
						        <a class="btn btn-primary btn-sm btnSearch" href="javascript:void(0);" type="button">
						        	<fmt:message key="button.sure"/>
						        </a>
						      </span>
						    </div>
						</li>
						<li role="presentation" class="organSelect" style="height:250px;overflow:auto;">
							<mh:tree id="issue"
									rootId="${organId}"
									url="organ/organization/find?includeDepart=false"
									sortName="order"
									scriptSelf="false">
							</mh:tree>
						</li>
					</jsp:attribute>
				</mh:dropdown>
				<sf:errors path="issueOrganId" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="writeOrganId" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/>
				<fmt:message key="melon.regulation.writeOrgan" />
			</sf:label>
	
			<div class="col-xs-9 form-field" id="writeOrganSelect">
				<sf:hidden path="writeOrganName"/>
				<sf:hidden path="writeOrganId"/>
				<!-- 准备单位选择 -->
				<mh:dropdown id="organMenu">
					<jsp:attribute name="values">
						${regulation.writeOrganId}
					</jsp:attribute>
					<jsp:attribute name="menus">
						<li role="presentation" id="organButtons">
							<div class="input-group">
							  <span class="input-group-btn">
							    <a class="btn btn-primary btn-sm btnSearch" type="button" href="javascript:void(0);" >
									<span class="glyphicon glyphicon-search"></span>
								</a>
						      </span>
						      <input type="text" class="form-control organSearch" style="height:30px;" >
						      <span class="input-group-btn">
						        <a class="btn btn-primary btn-sm btnSearch" href="javascript:void(0);" type="button">
						        	<fmt:message key="button.sure"/>
						        </a>
						      </span>
						    </div>
						</li>
						<li role="presentation" class="organSelect" style="height:250px;overflow:auto;">
							<mh:tree id="write"
									rootId="${organId}"
									url="organ/organization/find?includeDepart=false"
									sortName="order"
									scriptSelf="false">
							</mh:tree>
						</li>
					</jsp:attribute>
				</mh:dropdown>
				<sf:errors path="writeOrganId" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="ranges" cssClass="col-xs-3 control-label">
				<fmt:message key="hint.required"/>
				<fmt:message key="melon.regulation.range" />
			</sf:label>
	
			<div class="col-xs-9 form-field" id="rangeSelect">
				<sf:hidden path="ranges"/>
				<sf:hidden path="rangeNames"/>
				<!-- 准备单位选择 -->
				<mh:dropdown id="organMenu">
					<jsp:attribute name="values">
						${regulation.rangeNames}
					</jsp:attribute>
					<jsp:attribute name="menus">
						<li role="presentation" id="organButtons">
							<div class="input-group">
							  <span class="input-group-btn">
							    <a class="btn btn-primary btn-sm btnSearch" type="button" href="javascript:void(0);" >
									<span class="glyphicon glyphicon-search"></span>
								</a>
						      </span>
						      <input type="text" class="form-control organSearch" style="height:30px;">
						      <span class="input-group-btn">
						        <a class="btn btn-primary btn-sm btnSearch" href="javascript:void(0);" type="button">
						        	<fmt:message key="button.sure"/>
						        </a>
						      </span>
						    </div>
						</li>
						<li role="presentation" class="organSelect" style="height:250px;overflow:auto;">
							<mh:tree id="range"
									rootId="${organId}"
									url="organ/organization/getSubOrgan?selfOrgan=true"
									sortName="order"
									scriptSelf="false"
									multiSelect="true">
							</mh:tree>
						</li>
					</jsp:attribute>
				</mh:dropdown>
				<sf:errors path="writeOrganId" />
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="content" cssClass="col-xs-3 control-label">
				<fmt:message key="melon.regulation.content" />
			</sf:label>
			
			<div class="col-xs-9 form-editor form-field">
				<sf:textarea path="content" cssClass="form-control" rows="20"/>
			</div>
		</div>
		
	
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary" id="btnSave" type="submit">
					<fmt:message key="button.save" />
				</mh:button>
				<mh:button class="btn btn-default" id="btnBack" href="regulation/regulate/list">
					<fmt:message key="button.back" />
				</mh:button>
			</div>
		</div>
	
	</sf:form>
</div>
<script type="text/javascript">
	//处理组织机构选择
	function doTreeSearch(el) {
		var organSearch = $(el).find(".organSearch"),
			organText = $(el).find("#organMenu").find(".dropdown-text"),
			btnSearch = $(el).find(".btnSearch");
		//阻止默认的提交动作
		$(".organSelect").on("click", function(event) {
			event.stopPropagation();
		});
		//处理查询
		organSearch.on("click", function(event) {
			event.stopPropagation();
		});
		
		//处理选择
		if(el === "#writeOrganSelect") {
			writeTree.onSelect(function(data) {
				var companyName = $(el).find("#writeOrganName"),
				companyId = $(el).find("#writeOrganId");
				if(data.type == 1) {
					organText.text(data.text);
					companyName.val(data.text);
					companyId.val(data.id);
				}
			});
			
			btnSearch.on("click", function(event) {
				writeTree.search($.trim(organSearch.val()));
				event.stopPropagation();
			}); 
		} else if(el === "#issueOrganSelect") {
			issueTree.onSelect(function(data) {
				var companyName = $(el).find("#issueOrganName"),
					companyId = $(el).find("#issueOrganId");
				if(data.type == 1) {
					organText.text(data.text);
					companyName.val(data.text);
					companyId.val(data.id);
				}
			});
			
			btnSearch.on("click", function(event) {
				issueTree.search($.trim(organSearch.val()));
				event.stopPropagation();
			}); 
		} else if(el === "#rangeSelect") {
			rangeTree.onCheck(function(data) {
				var ids = "",
					text = "";
				//获取选中值
				$.each(rangeTree.getChecked(true), function(index, item) {
					if(item.type == 1) {
						text += item.text+",";
						ids += item.id+",";
					}
				});
				$("#ranges").val(ids);
				$("#rangeNames").val(text);
				organText.text(text);
			});
			rangeTree.onUnCheck(function() {
				
				var ids = "",
				text = "";
				//获取选中值
				$.each(rangeTree.getChecked(true), function(index, item) {
					if(item.type == 1) {
						text += item.text+",";
						ids += item.id+",";
					}
				});
				$("#ranges").val(ids);
				$("#rangeNames").val(text);
				organText.text(text);
			});
			
			btnSearch.on("click", function(event) {
				rangeTree.search($.trim(organSearch.val()));
				event.stopPropagation();
			});
		} else if(el === "#typeCheck") {
			typeTree.onSelect(function(data) {
				var level = $("#level"),
					typeName = $(el).find("#typeName"),
					type = $(el).find("#type");
				organText.text(data.text);
				typeName.val(data.text);
				type.val(data.id);
				level.val(data.level)
			});
			
			btnSearch.on("click", function(event) {
				typeTree.search($.trim(organSearch.val()));
				event.stopPropagation();
			});
		}
		
	}	

	$(document).ready(function() {
		//树选择
		doTreeSearch("#issueOrganSelect");
		doTreeSearch("#writeOrganSelect");
		doTreeSearch("#rangeSelect");
		doTreeSearch("#typeCheck");
		CKEDITOR.replace("content");
		//人员选择
		$("#chargePerson").on("change", function(e) {
			var data = e.added;
			$("#chargeName").val(data.name);
		});	
		
	});
</script>

