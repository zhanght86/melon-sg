<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>


<div style=""> 
	<div class="page-header" style="border-bottom:0px;margin-bottom:0px;">
	  <h3 style="display: inline-block;">我的工作</h3>
	</div>
	<div class="row row-tab" style="margin-left: -20px; margin-right: 0px;">
		<ul class="nav nav-tabs" role="tablist" style="padding: 0px 20px 0px 0px; margin-right: 20px; margin-left: 20px;">
			<li class="active">
				<a href="#infoSystemTab" role="tab" data-toggle="tab">
					待完成<span>（10）</span>
				</a>
			</li>
			<li>
				<a href="#assetTab" id="assetAction" role="tab" data-toggle="tab">
					已下发<span>（23）</span>
				</a>
			</li>
		</ul>
		<div class="tab-content col-xs-12" style="padding:30px 20px;">
			<div class="tab-pane active" id="infoSystemTab" >
				<table class="table table-bordered">
					<tr>
						<th>工作名称</th> 
						<th>截止日期</th>
					</tr>
					<tr>
						<td>机房巡检</td>
						<td>2014-12-01</td>
					</tr>
					<tr>
						<td>路由器检查</td>
						<td>2014-11-21</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane" id="assetTab">
				<table class="table table-bordered">
					<tr>
						<th>工作名称</th> 
						<th>截止日期</th>
					</tr>
					<tr>
						<td>安全检查填报</td>
						<td>2014-12-13</td>
					</tr>
					<tr>
						<td>安全对象审核</td>
						<td>2014-11-30</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	
</div> 


