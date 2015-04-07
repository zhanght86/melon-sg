<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<div class="col-page-title">
	<c:if test="${empty assetType.id}">
		<fmt:message key='asset.assetType.create' />
	</c:if>
	<c:if test="${!empty assetType.id}">
		${assetType.name}
	</c:if>
</div>

<sf:form modelAttribute="assetType" role="form" cssClass="form-horizontal">

	<sf:hidden path="id" />
	
	<sf:hidden path="path" />
	
	<sf:hidden path="parentId" />

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

	<div class="form-group">
		<sf:label path="code" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.abstract.code" />
		</sf:label>
		<div class="col-xs-9 form-field">
			<sf:input path="code" cssClass="form-control" maxlength="50" />
			<sf:errors path="code" />
			<label class="form-hint"><fmt:message key="hint.type.code"/></label> 
		</div>
	</div>

	<div class="form-group">
		<sf:label path="order" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.assetType.order" />
		</sf:label>
		<div class="col-xs-9 form-field">
			<sf:input path="order" cssClass="form-control" maxlength="30" />
			<sf:errors path="order" />
			<label class="form-hint"><fmt:message key="hint.order"/></label> 
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="imgName" cssClass="col-xs-3 control-label">
			<fmt:message key="hint.required" />
			<fmt:message key="asset.assetType.imgName" />
		</sf:label>
		<div class="col-xs-9 form-field">
			<sf:input path="imgName" cssClass="form-control" maxlength="30"/>
			<sf:errors path="imgName" />
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="" cssClass="col-xs-3 control-label">
			<fmt:message key="asset.assetType.imgNamevi" />
		</sf:label>
		<div class="col-xs-9 form-field">
			<img alt="" src="" id="imgNamevi">
		</div>
	</div>

	<div class="form-group">
		<sf:label path="remarks" cssClass="col-xs-3 control-label">
			<fmt:message key="asset.abstract.remarks" />
		</sf:label>
		<div class="col-xs-9 form-field">
			<sf:textarea path="remarks" cssClass="form-control" maxlength="500" rows="5" />
		</div>
	</div>
	

	<div class="form-group form-button-panel">
		<div class="col-xs-9 col-xs-offset-3 form-buttons">
			<mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.save"/></mh:button>
		</div>
	</div>
</sf:form>
<script type="text/javascript">
$(document).ready(function() {
	var assetTypeForm = $("#assetType"),
		baseImgUrl = '<mh:theme code="asset.imgPath"/>';
	//验证表单
	assetTypeForm.validate({
		rules : {
			name : {
				required : true,
				maxlength:[50]
			},
			code : {
				required : true,
				typeCode : true
			},
			remarks: {
				maxlength:[500]
			},
			order :{
				digits:true
			}
		}
	});
	//注册按钮事件,保存成功则添加节点到树中
	assetTypeForm.find("#btnSave").on("click", function(event) {
		var datas = assetTypeForm.serialize(),
			url = assetTypeForm.attr("action");
		//动态加载的页面必须手动验证一次
		if(assetTypeForm.valid()) {
			$.post(url, datas, function(response) {
				var domain = response.domain,
					parentId = domain.parentId;
				//新建操作
				if(response.actionType == 0) {
					assetTypeTree.create(parentId, domain, "last", function() {
						assetTypeTree.setSelected(parentId, false);
						assetTypeTree.setSelected(domain.id, true);
					});
				} else {
					//修改操作,将数据更新到节点
					assetTypeTree.update(domain.id, domain);
				}
				//输出提示信息
				assetTypeTree.sort(domain.parentId);
				Logger.success(response.message);
			});
		}
		event.preventDefault();
	});
	
	
	var thisSrc = $(imgName).val();
	thisSrc = $.trim(thisSrc);
	//默认图片
	if(thisSrc<4){
		$("#imgNamevi").attr('src',baseImgUrl+"default.png");
	}else{
		$("#imgNamevi").attr('src',baseImgUrl+thisSrc);
	}
	
	//失去焦点事件
	$("#imgName").on("blur",function(){
		var thisSrc = $(imgName).val();
		thisSrc = $.trim(thisSrc);
		$.ajax({
			url:baseImgUrl+thisSrc,
			error:function(){
				Dialog.warn("<fmt:message key='asset.type.Imgerror.hint' />");
			}
		});
		//如果路径输入的小于4个长度 
		if(thisSrc.length<4){
			$("#imgNamevi").attr('src',baseImgUrl+"default.png");
		}else{
			$("#imgNamevi").attr('src',baseImgUrl+thisSrc);
		} 
	}); 
});
</script>