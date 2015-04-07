<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<style type="text/css">
	.tableStyles{
	}
	.tableStyles td {
		text-align: center;
		border:1px solid #DDDDDD; 
		line-height:35px !important;
	}
	.melon-form-label , .melon-form-field {
		height:35px !important;
	}
</style>

		<table id="editTable" cellspacing=0 cellpadding=0 style="display:none;">
     		<tr>
     			<td>
     				<div id="addLogicDiv" style="display:none;">
     					<table id="logicTable" cellspacing=0 cellpadding=0 class="melon-form tableStyles">
     						<tr>
			        			<th colspan="2" class="melon-table-td-left melon-table-td-right melon-table-td-top melon-table-header" nowrap="nowrap" style="border:1px solid #DDDDDD;"><fmt:message key="system.rule.logicOperProp"/></th>
			        		</tr>
			        		<tr>
			        			<td class="melon-form-label" style="width:200px;text-align:center;" nowrap="nowrap">
			        				<label><fmt:message key="system.rule.logicOper"/></label>
			        			</td>
			        			<td class="melon-form-field">
			        				<mh:getDicts var="logicOpers" key="logicOpers"/>
					                <sf:select path="joinType" style="border:1px solid #dddddd;">
					                    <sf:options  items="${logicOpers}" itemLabel="label" itemValue="value"/>
					                </sf:select>
			        			</td>
			        		</tr>
			        		<tr>
								<td colspan="2" class="melon-table-button-td" style="border:none;"> <button class="melon-button melon-button-addlogic" style="float:none;"><fmt:message key="system.rule.button.add"/></button></td>
							</tr>
			        	</table>
     				</div>
     				<div id="addOpDiv" style="display:none;">
     					<div>
     						<table cellspacing=0 cellpadding=0 class="melon-form tableStyles">
     							<tr>
     								<th colspan="2" class="melon-table-td-left melon-table-td-right melon-table-td-top melon-table-header" nowrap="nowrap" style="border:1px solid #DDDDDD;"><fmt:message key="system.rule.conditions"/></th>
     							</tr>
     							<tr>
     								<td class="melon-form-label" nowrap="nowrap"><fmt:message key="system.rule.class"/></td>
     								<td class="melon-form-field">
     									<c:out value="${filterEntity }"/>
     								</td>
     							</tr>
     							<tr>
     								<td class="melon-form-label" nowrap="nowrap"><fmt:message key="system.rule.field"/></td>
     								<td class="melon-form-field">
     									 <sf:select path="field">
     									 	<sf:option value=""><fmt:message key="system.rule.choice"/></sf:option>
     									 </sf:select>
     								</td>
     							</tr>
     							<tr>
     								<td class="melon-form-label" nowrap="nowrap"><fmt:message key="system.rule.operator"/></td>
     								<td class="melon-form-field">
     									 <sf:select path="operator">
     									 	<sf:option value=""><fmt:message key="system.rule.choice"/></sf:option>
     									 </sf:select>
     								</td>
     							</tr>
     							<tr>
     								<td class="melon-form-label" nowrap="nowrap"><fmt:message key="system.rule.value"/></td>
     								<td class="melon-form-field">
     									<sf:input path="value" style="border:1px solid #dddddd;height:30px;"/>
     								</td>
     							</tr>
     							<tr>
     								<td colspan="2" class="melon-table-button-td" style="border:none;"><button class="melon-button melon-button-addcondition" style="float:none;" ><fmt:message key="system.rule.button.add"/></button></td>
     							</tr>
     						</table>
     					</div>
     				</div>
     			</td>
     		</tr>
     	</table>
       	<table id="logicDetail" cellspacing=0 cellpadding=0  class="melon-form tableStyles">
       		<tr>
       			<th colspan="2" class="melon-table-td-left melon-table-td-right melon-table-td-top melon-table-header" nowrap="nowrap" style="border:1px solid #DDDDDD;"><fmt:message key="system.rule.logicOperProp"/></th>
       		</tr>
       		<tr>
       			<td class="melon-form-label" nowrap="nowrap" width="50%">
       				<div><fmt:message key="system.rule.logicOper"/></div>
       			</td>
       			<td class="melon-form-field" width="50%">
       				<div id="logicValue"></div>
       			</td>
       		</tr>
       		<tr>
				<td class="melon-form-label nowrap="nowrap" >条件表达式</td>
				<td class="melon-form-field">
					<div id="ruleExp"></div>
				</td>
			</tr>
       	</table>
       	<table id="conditionDetail" cellspacing=0 cellpadding=0 class="tableStyles" style="display:none;">
       		<tr>
				<th colspan="2" class="melon-table-td-left melon-table-td-right melon-table-td-top melon-table-header" nowrap="nowrap" style="border:1px solid #DDDDDD;"><fmt:message key="system.rule.conditions"/></th>
			</tr>
			<tr>
				<td class="melon-form-label" nowrap="nowrap"><fmt:message key="system.rule.class"/>
				</td>
				<td class="melon-form-field">
					 <div id="filterEntityValue"></div>
				</td>
			</tr>
			<tr>
				<td class="melon-form-label" nowrap="nowrap"><fmt:message key="system.rule.field"/></td>
				<td class="melon-form-field">
					<div id="filedValue"></div>
				</td>
			</tr>
			<tr>
				<td class="melon-form-label" nowrap="nowrap"><fmt:message key="system.rule.operator"/></td>
				<td class="melon-form-field">
					 <div id="operatorValue"></div>
				</td>
			</tr>
			<tr>
				<td class="melon-form-label" class="melon-table-td-bottom" nowrap="nowrap"><fmt:message key="system.rule.value"/></td>
				<td class="melon-form-field">
					<div id="valueDetail"></div>
				</td>
			</tr>
			<tr>
				<td class="melon-form-label" class="melon-table-td-bottom" nowrap="nowrap">条件表达式</td>
				<td class="melon-form-field">
					<div id="ruleExp"></div>
				</td>
			</tr>
       	</table>