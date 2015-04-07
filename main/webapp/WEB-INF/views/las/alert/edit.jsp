<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="alertRule" role="form" cssClass="form-horizontal">
		
		<sf:hidden path="id"/>
		<sf:hidden path="ruleId"/>
		<sf:hidden path="name"/>
			
		<div class="form-group">
			<sf:label path="name" cssClass="col-xs-3 control-label">
				<fmt:message key="rule.name" />
			</sf:label>
			<div class="col-xs-9 form-field" style="padding-top:8px">
				<mh:value path="name"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-xs-3 control-label" for="type"> 可配字段 </label>
			<div class="col-xs-9 form-field">
				<p>
					<a class="btn btn-default" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
					  查看
					</a>
				</p>
				<div class="collapse" id="collapseExample">
					  <div class="well" style="border:none;padding:0px;marin-bottom:0px">
						    <table class="table table-bordered" style="margin-bottom:0px">
								  <tbody>
									  	<tr>
									  		<td>
									  		1
									  		</td>
									  		<td>
									  		1
									  		</td>
									  		<td>
									  		1
									  		</td>
									  		
									  	</tr>
									  	<tr>
									  		<td>
									  		1
									  		</td>
									  		<td>
									  		1
									  		</td>
									  		<td>
									  		1
									  		</td>
									  		
									  	</tr>
									  	<tr>
									  		<td>
									  		1
									  		</td>
									  		<td>
									  		1
									  		</td>
									  		<td>
									  		1
									  		</td>
									  		
									  	</tr>
									  	<tr>
									  		<td>
									  		1
									  		</td>
									  		<td>
									  		1
									  		</td>
									  		<td>
									  		1
									  		</td>
									  		
									  	</tr>
								  </tbody>
							</table>
					  </div>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="type" cssClass="col-xs-3 control-label">
				<fmt:message key="las.filter.action" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<mh:dictionary var="actionType" key="las.filter.action"/>
				<sf:checkboxes path="type" items="${actionType}"/>
			</div>
		</div>
		
		<div id="message" style="display: none;">
			<div class="form-group">
				<sf:label path="receive" cssClass="col-xs-3 control-label">
					<fmt:message key="las.filter.receive" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<sf:input path="receive" cssClass="form-control"/>
					<label class="form-hint"><fmt:message key="las.rule.receiveHint" /></label>
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="note" cssClass="col-xs-3 control-label">
					<fmt:message key="las.filter.note" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<sf:textarea path="note" cssClass="form-control" rows="5"/>
				</div>
			</div>
		</div>
		
		<div id="mail" style="display: none;">
			<div class="form-group">
				<sf:label path="subject" cssClass="col-xs-3 control-label">
					<fmt:message key="las.filter.subject" />
				</sf:label>
				<div class="col-xs-9 form-field" style="padding-top:8px">
					<sf:input path="subject" cssClass="form-control"/>
				</div>
			</div> 
			
			<div class="form-group">
				<sf:label path="address" cssClass="col-xs-3 control-label">
					<fmt:message key="las.filter.address" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<sf:input path="address" cssClass="form-control"/>
					<label class="form-hint"><fmt:message key="las.rule.addressHint" /></label>
				</div>
			</div>
			
			<div class="form-group">
				<sf:label path="content" cssClass="col-xs-3 control-label">
					<fmt:message key="las.filter.content" />
				</sf:label>
				<div class="col-xs-9 form-field">
					<sf:textarea path="content" cssClass="form-control" rows="5"/>
				</div>
			</div>
		</div>
		
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.save" /></mh:button>
				<a class="btn btn-default" href="<c:url value='/las/sim/alert/list'/>" ><fmt:message key="button.back" /></a>
			</div>
		</div>
		
	</sf:form>
</div>

<script type="text/javascript" >

	$(document).ready(function() {
		
		function content() {
			if($("#type1").prop("checked") == true) {
				$("#message").slideDown("normal",function(){
				 });
			} else {
				$("#message").slideUp("normal",function(){
					   $("#receive").val("");
					   $("#note").val("");
				 });
			}
			if($("#type2").prop("checked") == true) {
				$("#mail").slideDown("normal",function(){
				 });
			} else {
				$("#mail").slideUp("normal",function(){
					   $("#subject").val("");
					   $("#address").val("");
					   $("#content").val("");
				 });
			}
		}
		content();
		$("[name=type]").on("click", function() {
			content();
		});
		$("#btnSave").click(function() {
			$("#alertRule").submit();
		});
	})

</script>
