<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

	<style type="text/css">
		.knowledge-show-container{
			width:589px;
			margin:0 auto;
			margin-top:62px;
		}
		.knowledge-info-header .melon-info-title{
			font-size:28px;
			color:#1a1d1f;
			font-family: "微软雅黑";
			text-align: center;
		}
		.knowledge-info-header .melon-info-issueDate{
			float:right;
			margin-right:20px;
			color:#808080;
			font-size:12px;
		}
		.show-body{
			margin-top:48px;
		}
		.show-body .show-content{
			font-family:"宋体";
			font-size:14px;
			color:#1e1e1e;
			text-indent: 2em;
			overflow:hidden;
		}
		.show-footer{
			float:right;
			margin:40px 20px;
			color:#808080;
		}
	</style>
	<div class="knowledge-show-container">
		<div class="knowledge-info-header"> 
			<p class="melon-info-title">
			${knowledge.title}
			</p>
			<p class="melon-info-issueDate">${knowledge.issueDate}</p>
		</div>
		<div class="show-body">
			<div class="show-content">
				${knowledge.content}
				<div class="show-footer">
					<p>${knowledge.issueOrgan}</p>
				</div>
			</div>
			
		</div>
	</div>
