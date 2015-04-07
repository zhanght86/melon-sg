<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<link rel="stylesheet" href="<mh:theme code='jquery.fileupload.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.fileupload.js'/>"></script>

<%--
<!-- 明细弹框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">设备明细</h4>
      </div>
      <div class="modal-body" id="deviceShowView">
      		<table class="">
      			
      		</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save changes</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- 更新弹框 -->
<div class="modal fade" id="updateDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">设备明细</h4>
      </div>
      <div class="modal-body">
        		更新内容
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save changes</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
 --%>

<span class="btn fileinput-button btn-success btn-block">
    <i class="glyphicon glyphicon-plus"></i>
    <span>添加附件</span>
    <input id="fileupload" type="file" name="files[]" multiple>
</span>

<form action="<c:url value="/asset/vuln/importSecondStep"/>" method="post">
导入数据预览
<table id="tabDb" class="table table-bordered">
	 <thead>
          <tr>
            <th style="display:none"></th>
            <th style="display:none"></th>
            <th class="select"><input type="checkbox" id="selectAll"/></th>
            <th width="120">IP地址</th>
            <th>关联资产</th>
          </tr>
        </thead>
        <tbody>
        <c:if test="${vulnimportlist != null}">
          <c:forEach var="vuln" items="${vulnimportlist}" varStatus="status">
            <tr	readonly="true">
              <td style="display:none">
                <input type="text" class="thintext" value='<c:out value="${vuln[1]}"/>' readonly="readonly" name="ip[<c:out value="${status.index}"/>]" id="ip_<c:out value="${status.index}"/>"/>
              </td>
              <td style="display:none">
                <input type="text" class="thintext" id="deviceId_<c:out value="${status.index}"/>" value="<c:out value="${vuln[0] }"/>" readonly="readonly" name="deviceId[<c:out value="${status.index}"/>]" />
              </td>
              <td class="select"><input type="checkbox" id="checkbox" name="<c:out value="${status.index}"/>" value='<c:out value="${vuln[0]}"/>'/></td>
              <td id="tds_<c:out value="${status.index}"/>"><c:out value="${vuln[1]}"/></td>
              <td><input type="text" id="id_<c:out value="${status.index}"/>" class="thintext" value='<c:out value="${vuln[2]}"/>' readonly="readonly" name="name[<c:out value="${status.index}"/>]" />&nbsp;
              	<input type="button" id="button_<c:out value="${status.index}"/>" value="..." onclick="moveHandler(<c:out value="${status.index}"/>)" disabled="true" name="assetTree"/>
              	<input type="hidden" id="hidden_<c:out value="${status.index}"/>" name="objs" value="">
              	</td>
            </tr>
          </c:forEach>
        </c:if>
        </tbody>
</table>

<a id="btnBack" class="btn btn-default" href="javascript:void(0);" style="float:right;">
	<fmt:message key="button.cancel"/>
</a>
<a id="btnBack" class="btn btn-default" href="javascript:checkForm(0);" style="float:right;">
	<fmt:message key="button.sure"/>
</a>
</form>
<div id="loadings">资产列表
	<table id="tabDb" class="table table-bordered">
	 <thead>
          <tr>
            <th class="select"><input type="checkbox" id="selectAll" value="10"/></th>
            <th width="120">设备类型</th>
            <th>资产名称</th>
          </tr>
        </thead>
        <tbody>
        <c:if test="${devList != null}">
        	<c:forEach var="dev" items="${devList}" varStatus="status">
	        	<tr>
	        		<td><input type="checkbox" id="assetcheckbox" name="assetcheckbox" value="<c:out value="${dev.id}"/>_<c:out value="${dev.name}"/>"></td>
       				 <td><c:out value="${dev.name}"/></td>
       				 <td><c:out value="${dev.chargeName}"/></td>
	        	</tr>
        	</c:forEach>
        </c:if>
        
        </tbody>
</table>
<input type="button" value="确定" onclick="subValue()">
<input type="button" value="取消" onclick="cacle()">
</div>

<script type="text/javascript">
	var assetIds ="";//存放每个ip选择的资产
	var deviceid ="";//保存选择的弱点
$(document).ready(function() {
	var importUrl = '<c:url value="/asset/vuln/importModel"/>',
		url = '<c:url value="/asset/vuln/importModelPage"/>',
		res = null,
		impRes = null;
		
	//上传附件
	$('#fileupload').fileupload({
        url: importUrl,
        dataType: 'json',
        done: function (e, data) {
        	res = data.result;
        	if(res.result) {
        		Dialog.warn("<fmt:message key='asset.imp.errorDb'/>");
        	}else{
        		//impRes = res.impRes;
        		//Dialog.warn("导入数据成功"+impRes.correctNumber+"条,失败"+impRes.errorNumber+"条");
        		window.location.href = url;
        	}
        }
    }).prop('disabled', !$.support.fileInput)
	.parent().addClass($.support.fileInput ? undefined : 'disabled');
	
	$(':checkbox[id=checkbox]').on('click',function(){
		   var _this=$(this);
		   var indexs = this.name;//id[0]
		   //indexs = indexs.substring(3,indexs.length-1);
		   
		   if(_this.is(":checked")){
		   	$("#button_"+indexs).removeAttr("disabled");
		   	$("#hiden_"+indexs).val('');
		   } else {
		   	$("#button_"+indexs).attr("disabled",'true');
		   	$("#id_"+indexs).val('');
		  }
			
				
		});
	
	$(':checkbox[name=assetcheckbox]').on('click',function(){
			
		   var _this=$(this);
		  	if(_this.is(":checked")){
		  		
		  		assetIds +=_this.val()+",";
		  	}else{
		  			assetIds.replace(_this.val(),"");
		  		}
		  		
		});
	$("#loadings").hide();
	//$("#loadings").dialog("close");

});
function moveHandler(id){
	
	deviceid = id;
	$("#loadings").dialog({
			title:"资产列表",
			modal : true,
			draggable : false,
			resizable : false,
			autoOpen : true,
			height : 450,
			width : 700
	});
	var values = $("#id_"+id).val();
	if( values && values!=""){
		checkasset(values);	
	}
}
function cacle(){
		assetIds = "";
		cleanCheck();
		$("#loadings").hide();
		$("#loadings").dialog("close");
	}
function subValue(){
	
		$("#id_"+deviceid).val(assetIds);
		var ip = $("#ip_"+deviceid).val();
		
		//var res =deviceid+","+$("#id_"+deviceid).val();
		$("#hidden_"+deviceid).val(ip+','+assetIds);
		
		assetIds = "";
		cleanCheck();
		$("#loadings").hide();
		$("#loadings").dialog("close");
	}
	
function cleanCheck(){
		$(':checkbox[name=assetcheckbox]').each(function(){
				var _this=$(this);
				if(_this.is(":checked")){
						_this.prop("checked", false);
				}
			});
	}
function checkasset(ids){
		$(':checkbox[name=assetcheckbox]').each(function(){
				var _this=$(this);
				if(ids.indexOf(_this.val())!=-1){
						_this.prop("checked", true);
				}
			});
	}	
	function checkForm(){
			var form = document.forms[0];
			form.submit();
		}
</script>