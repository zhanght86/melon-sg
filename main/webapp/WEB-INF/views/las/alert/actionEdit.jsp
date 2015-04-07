<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

	<sf:hidden path="alertRule.id"/>
	<sf:hidden path="alertRule.ruleId"/>
	<sf:hidden path="alertRule.rule"/>
	<sf:hidden path="alertRule.name"/>
	
	<div class="form-group">
		<label class="col-xs-3 control-label" for="type"><fmt:message key="las.rule.actionField" /></label>
		<div class="col-xs-9 form-field">
			<p>
				<a class="btn btn-default" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
				  <fmt:message key="button.view"/>
				</a>
			</p>
			<div class="collapse" id="collapseExample">
				  <div class="well" style="border:none;padding:0px;marin-bottom:0px">
					    <table class="table table-bordered" style="margin-bottom:0px">
							  <tbody>
								  	<tr>
								  		<td>
								  		id
								  		</td>
								  		<td>
								  		name
								  		</td>
								  		<td>
								  		type
								  		</td>
								  		
								  	</tr>
								  	<tr>
								  		<td>
								  		creator
								  		</td>
								  		<td>
								  		createTime
								  		</td>
								  		<td>
								  		content
								  		</td>
								  		
								  	</tr>
								  	<tr>
								  		<td>
								  		state
								  		</td>
								  		<td>
								  		addr
								  		</td>
								  		<td>
								  		remark
								  		</td>
								  		
								  	</tr>
							  </tbody>
						</table>
				  </div>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<sf:label path="alertRule.type" cssClass="col-xs-3 control-label">
			<fmt:message key="las.filter.action" />
		</sf:label>
		<div class="col-xs-9 form-field">
			<mh:dictionary var="actionType" key="las.filter.action"/>
			<sf:checkboxes cssClass="actionType" path="alertRule.type" items="${actionType}"/>
		</div>
	</div>
	
	<div id="message" style="display: none;">
		<div class="form-group">
			<sf:label path="alertRule.receive" cssClass="col-xs-3 control-label">
				<fmt:message key="las.filter.receive" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:input path="alertRule.receive" cssClass="form-control receive"/>
				<label class="form-hint"><fmt:message key="las.rule.receiveHint" /></label>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="alertRule.note" cssClass="col-xs-3 control-label">
				<fmt:message key="las.filter.note" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:textarea path="alertRule.note" cssClass="form-control note" rows="5" maxlength="500"/>
			</div>
		</div>
	</div>
	
	<div id="mail" style="display: none;">
		<div class="form-group">
			<sf:label path="alertRule.subject" cssClass="col-xs-3 control-label">
				<fmt:message key="las.filter.subject" />
			</sf:label>
			<div class="col-xs-9 form-field" style="padding-top:8px">
				<sf:input path="alertRule.subject" cssClass="form-control subject"/>
			</div>
		</div> 
		
		<div class="form-group">
			<sf:label path="alertRule.address" cssClass="col-xs-3 control-label">
				<fmt:message key="las.filter.address" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:input path="alertRule.address" cssClass="form-control address"/>
				<label class="form-hint"><fmt:message key="las.rule.addressHint" /></label>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="alertRule.content" cssClass="col-xs-3 control-label">
				<fmt:message key="las.filter.content" />
			</sf:label>
			<div class="col-xs-9 form-field">
				<sf:textarea path="alertRule.content" cssClass="form-control content" rows="5" maxlength="500"/>
			</div>
		</div>
	</div>

