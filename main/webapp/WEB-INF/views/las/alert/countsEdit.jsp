<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="col-xs-8 col-xs-offset-2 form-singlon">
	<sf:form modelAttribute="ruleCounts" role="form" cssClass="form-horizontal">
		<sf:hidden path="andAttr" id="andAttr"/>
			
		<div class="form-group">
			<sf:label path="counts" cssClass="col-xs-3 control-label">
				<fmt:message key="las.rule.counts" />
			</sf:label>
			<div class="col-xs-9 form-field" style="padding-top:8px">
				<sf:input path="counts" cssClass="form-control receive"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="times" cssClass="col-xs-3 control-label">
				<fmt:message key="las.rule.times" />
			</sf:label>
			<div class="col-xs-9 form-field" style="padding-top:8px">
				<sf:input path="times" cssClass="form-control receive"/>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="unit" cssClass="col-xs-3 control-label">
				<fmt:message key="las.rule.unit" />
			</sf:label>
			<div class="col-xs-9 form-field" style="padding-top:8px">
				<mh:dictionary var="unti" key="las.rule.unti"/>
	            <sf:select path="unit" cssClass="form-control">
	                <sf:options  items="${unti}" />
	            </sf:select>
			</div>
		</div>
		
		<div class="form-group">
			<sf:label path="conditions" cssClass="col-xs-3 control-label">
				
				<fmt:message key="las.rule.conditions" />
			</sf:label>
			<div class="col-xs-9 form-field" style="padding-top:8px">
			
			  <select id="conditions" name="conditions" class="form-control">
			  	<c:forEach var="obj" items="${ruleCounts.conditions}">
			  
	                <option value="<c:out value="${obj.id }"></c:out>"><c:out value="${obj.name }"></c:out> </option>
			  	</c:forEach>
	            </select>
			
			</div>
		</div>
		<div class="form-group">
			<sf:label path="columns" cssClass="col-xs-3 control-label">
				
				<fmt:message key="las.rule.columns" />
			</sf:label>
			<div class="col-xs-9 form-field" style="padding-top:8px">
			
			  <select id="columns" name=""columns"" class="form-control">
			  	<c:forEach var="obj" items="${ruleCounts.columns}">
			  
	                <option value="<c:out value="${obj }"></c:out>"><c:out value="${obj }"></c:out> </option>
			  	</c:forEach>
	            </select>
			<input type="button" onclick="addArrt()" value="添加">
			</div>
		</div>
		<div class="form-group">
			<sf:label path="andAttrs" cssClass="col-xs-3 control-label">
				
				<fmt:message key="las.rule.andAttrs" />
			</sf:label>
			<div class="col-xs-9 form-field" style="padding-top:8px">
				<div  id="andAttrs" name="andAttrs" style="border:1px solid #ccc; width: 100%; height :120px ">
				
				</div>
			
			  <!-- <select id="andAttrs" name="andAttrs" class="form-control"   multiple="true" size="5" >
			  	
	           </select> -->
			<input type="button" onclick="delArrt()" value="删除">
			</div>
		</div>
		<div class="form-group form-button-panel">
			<div class="col-xs-9 col-xs-offset-3 form-buttons">
				<mh:button class="btn btn-primary" id="btnSave"><fmt:message key="button.save" /></mh:button>
				<a class="btn btn-default" href="<c:url value='/las/sim/rule/list'/>" ><fmt:message key="button.back" /></a>
			</div>
		</div>
		
	</sf:form>
</div>

<script type="text/javascript" >

	$(document).ready(function() {
		
		
		$("#btnSave").click(function() {
			
			var options = $('#andAttrs option');
			
				
			var attr = "";	
			for(var i=0; i<options.length; i++)
			{
				attr +=options[i].value+";"
				
			}
			
			
			$("#andAttr").val(attr);
			
			$("#ruleCounts").submit();
		});
		
	})
function addArrt(){
		var conV = $("#conditions").val();
		var conT = $("#conditions").find("option:selected").text();
		
		var colV = $("#columns").val();
		var colT = $("#columns").find("option:selected").text();
		
		var resV = conV+":"+colV;
		var resT = conT+":"+colT;
		var obj = 	$("#andAttrs");
		var options = $('#andAttrs option');
		var b = false;
		
			
			
		for(var i=0; i<options.length; i++)
		{
		  if(options[i].value==resV)
		  {
		    b = true;
		    break;
		  }
		}
		if(!b)
		{
		  //add
		obj.append("<option value='"+resV+"'>"+resT+"</option>"); 
		  
		} 
	}
function delArrt(){
	$("#andAttrs").find("option:selected").remove();
}
</script>
