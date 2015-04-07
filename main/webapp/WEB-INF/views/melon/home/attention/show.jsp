<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
	
	<div class="attention" style="display: inline-block;">
  		(<a href="#" style="font-weight:bold;" id="attCount" data-toggle="modal" data-target="#myModal">
  			${attentionCount}<fmt:message key="asset.device.attention" />
		</a>
		
			<c:choose>
				<c:when test="${isAttentioned == 0}">
					<a href="#" class="btn-attention" id="atten" ></a>)
				</c:when>
				<c:otherwise>
					<a href="#" class="btn-cancelAttention" id="atten" ></a>)
				</c:otherwise>
			</c:choose>
  	</div>
