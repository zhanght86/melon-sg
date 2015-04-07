<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<style type="text/css">
	.00 {
		cursor: pointer;
	    height: 31px;
	    left: 0;
	    opacity: 0;
	    overflow: hidden;
	    position: absolute;
	    top: 4px;
	    width: 60px;
	}
	input.addfile {
		cursor: pointer;
	    font-size: 30px;
	    opacity: 0;
	    position: absolute;
	    right: -5px;
	    top: -5px;
		filter:alpha(opacity=0)
	}
	


</style>
<sf:form cssClass="form-horizontal" modelAttribute="meFile" role="form" enctype="multipart/form-data">

	<sf:hidden path="id"/>
	
	<sf:hidden path="businessId"/>
	<sf:hidden path="extension"/>
	<sf:hidden path="fileName"/>
	<sf:hidden path="secured"/>
	<sf:hidden path="userId"/>
	<sf:hidden path="username"/>
	<sf:hidden path="organName"/>
	<sf:hidden path="uploadTime"/>
	
	<div style="position:relative;">
		<a class="cb0 nui-txt-link" id="1400049187198_attachAdd" href="javascript:void(0)">
			<img src="<c:url value='/statics/themes/commons/images/fileupload.png'/>" alt="" width="24" height="24"/>
			添加附件
		</a>
		<div class="00">
			<sf:input path="file" type="file" cssClass="form-control addfile"/>
		</div>
		
	</div>
	<c:forEach items="${files}" var="file">
		<div style="width:200px;float:left;">
			<a href="<c:url value="/system/file/download/${file.id}"/>">${file.fileName}</a>
			<a href="javascript:void(0)"  fileId="${file.id }">删除</a>
		</div>
	</c:forEach>
	<div class="attachArea"></div>
</sf:form>

<script type="text/javascript" src="<mh:theme code='jquery.vaildate.js'/>"></script>
<script type="text/javascript">
	var MelonFile = {
		init : function(){
			this.bindEvent();			
		},
		addFile : function(file){
			//var fileName = file.val(),fileWrap=$("<div/>").html(fileName);
			//$(".attachArea").empty().append(fileWrap);
			$("form").submit();
		},
		bindEvent : function(){
			var _this = this;
			$("a[fileId]").on("click",function(){
				var fileId=$(this).attr("fileId"),url="<c:url value='/system/file/remove/'/>"+fileId;
				melon.JsonHelper.post(url, function(data) {
					if(data){
						$("a[fileId="+fileId+"]").parent().remove();
					}
				});
			});
			$("#file").on("change",function() {
				_this.addFile($(this));
			});
			$("#btnSave").on("click",function(){
				if($(".attachArea").children().size()>0){
					$("form").submit();
				}
				return false;
			});
			$("#btnCancel").on("click",function(){
				$(".attachArea").empty();
				return false;
			});
		}
	}
	$(document).ready(function() {
		var mForm = $("#meFile");
		//melon.form.init(mForm);
		//$("html").height(300);
		MelonFile.init();
	});
	
</script>