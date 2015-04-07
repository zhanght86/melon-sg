<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<%--首次登录时显示--%>
<link rel="stylesheet" href="<mh:theme code='jquery.jWizard.css'/>" media="all" />
<script type="text/javascript" src="<mh:theme code='jquery.jWizard.js'/>"></script>

<div class="page-header">
  <h1>欢迎使用税务综合安全管理平台</h1>
</div>
<div id="jwizard">
	<div data-jwizard-title="业务概览">
		阐述综合安全管理平台的功能分布,
		综合安全管理平台的业务架构(图片)
	</div>
	<div data-jwizard-title="菜单导航">
		阐述菜单的级联布局
		以及菜单使用方式与导航区(图片)
	</div>
	<div data-jwizard-title="业务办理">
		发起任务
		处理任务
		任务跟踪
		任务考核
		(图片)
	</div>
	<div data-jwizard-title="个人助理">
		我的关注
		快速批注
		密钥管理
		个人配置(图片)
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		$("#jwizard").jWizard({
			title : true,
			menu : false,
			progress: {
				label: "count",//"percentage"
				append: "已完成"
			} ,
			buttons : {
				cancel : {
				  text: "跳过",
				  disabled : true,
				  click : function(event) {
					  window.location.href = "<c:url value='/home/main'/>";
				  }
				},
				
				prev : {
				  text: "上一布"
				},
				
				next : {
				  text: "下一步"
				},
				
				finish : {
				  text: "进入系统",
				  click : function(event) {
					  window.location.href = "<c:url value='/home/main'/>";
				  }
				}
			},
			
			effects: {
			  steps: {
			    hide: {
			      effect:    "blind",
			      direction: "left",
			      duration:  250
			    },
			    show: {
			      effect:    "blind",
			      direction: "left",
			      duration:  250
			    }
			  }
			}
		});
	});
</script>
