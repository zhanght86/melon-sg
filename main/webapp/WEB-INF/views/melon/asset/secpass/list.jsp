<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="hh" uri="http://www.legendsec.com/melon/helper" %>

<div class="modal fade" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span>
					<span class="sr-only"><fmt:message key="button.close"/></span>
				</button>
				<h4 class="modal-title">
					<fmt:message key="security.account.secPass.title"/>
				</h4>
			</div>
			<div class="modal-body">
				<sf:form modelAttribute="passwordForm" role="form" cssClass="form-horizontal" style="width:95%;margin:0 auto">
					<sf:hidden path="id"/>
					
					<div class="form-group" id=password>
						<sf:label path="password" cssClass="col-xs-2 control-label">
							<fmt:message key="security.account.secPassWord" />
						</sf:label>
						
						<div class="col-xs-8 form-field">
						    <sf:input path="password" cssClass="form-control" maxlength="128"/>
							<sf:errors path="password"/>
						</div>
					</div>
				</sf:form>
				
			</div>
			<div class="modal-footer">
				<button type="button" id="btnSave" class="btn btn-primary">
					<fmt:message key="button.sure"/>
				</button>
			</div>
		</div>
	</div>
</div>
<div id="secPassList">

</div>

<script type="text/javascript">
$(document).ready(function() {
	var myModal = $("#myModal"),
		form = $("#passwordForm"),
		height = myModal.height(),
		inline = "${inline}",
		dialog = myModal.find(".modal-dialog");
	//dialog位置
	dialog.css("marginTop", (height-204)/2);
	if(inline == "1"){
		//内部请求
		$("#secPassList").load('<c:url value="/asset/secpass/load"/>');
	} else {
		//菜单请求
		myModal.modal('show');
		$("#btnSave").click(function(event) {
			var data = form.serialize();
			$.post('<c:url value="/asset/secpass/verify"/>', data, function(response) {
				//判断是否为正确验证口令
				if(response) {
					myModal.modal("hide");
					//window.location = '<c:url value="/asset/secpass/list"/>';
					$("#secPassList").load('<c:url value="/asset/secpass/load"/>');
				} else {
					if($("#password-error").length == 0){
						$('<label id="password-error" class="col-xs-2 control-label" style="color: #f55549; font-size : 12px; padding :10px 0 5px 0" for="password" ></label>').appendTo('#password').text('<fmt:message key="security.account.secPass.hint"/>');
					}
				}
				
			});
			event.preventDefault();
			return false;
		});
	}
	//去除enter事件
	$('#myModal').keydown(function(e) {
		e = e || window.event; 
		var keycode = e.which ? e.which : e.keyCode; 
		//如果按下ENTER键 
		if(keycode == 13 || keycode == 108){ 
			return false;
		} 

	});
});
</script>

