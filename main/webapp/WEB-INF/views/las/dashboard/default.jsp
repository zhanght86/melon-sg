<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>
		<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><fmt:message key="melon.title"/></title>
		<link rel="stylesheet" href="<c:url value='/statics/themes/default/lib/jquery.ui-1.11.1.css'/>" media="all" />
		<link rel="stylesheet" href="<c:url value='/statics/kibana/css/bootstrap.light.min.css'/>" media="all" />
    	<link rel="stylesheet" href="<c:url value='/statics/kibana/css/timepicker.css'/>" media="all">
    	<link rel="stylesheet" href="<c:url value='/statics/kibana/css/animate.min.css'/>" media="all">
   		<link rel="stylesheet" href="<c:url value='/statics/kibana/css/normalize.min.css'/>" media="all">
   		<link rel="stylesheet" href="<c:url value='/statics/kibana/css/bootstrap-responsive.min.css'/>" media="all">
   		<link rel="stylesheet" href="<c:url value='/statics/kibana/css/font-awesome.min.css'/>" media="all">
   		<link rel="stylesheet" href="<c:url value='/statics/themes/default/melon/melon-theme-1.1.0.css'/>" media="all">
   		<style type="text/css">
   			.melon-footer-search .input-append {
   				display : block;
   			}
   			input.search-query {
   				border-radius: 2px;
   				margin-left : 0px;
   			}
   			.melon-menu-item .container{
   			}
   			.breadcrumb > li {
   				line-height : 16px;
   			}
   			.melon-user-info .dropdown-menu {
   				text-align : left;
   			}
   			
   			.melon-path-container {
			    height:36px;
			    padding:20px 0px 0px 0px;
			}
   		</style>
	    <script type="text/javascript">
	    	//以下地址需要配置成绝对地址
	    	var Kibana = {
	    		baseUrl : "<c:url value='/statics/kibana/'/>",
	    		appUrl : "<c:url value='/statics/kibana/app'/>",
	    		loadImg : "<c:url value='/statics/kibana/img/load.gif'/>",
	    		esUrl : "http://10.70.1.10:9200"//服务器地址
	    	};
	    </script>
	    <script type="text/javascript" src="<c:url value='/statics/kibana/vendor/require/require.js'/>"></script>
	    <script type="text/javascript" src="<c:url value='/statics/kibana/app/components/require.config.js'/>"></script>
	    <script type="text/javascript">
	    	//启动主程序
	    	require(['app'], function(){
	 			   		
	    	});
	    </script>
	</head>
	
	<body>
	
		<div class="modal fade" id="myModal" style="position:absolute;top:30px;left:0px;display:none;">
	        <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	          <h3>告警的关联事件查询</h3>
	        </div>
	        <div class="modal-body">
	           <iframe id="eventFrame" src="" frameborder="0" width="100%" height="400px">
	           </iframe>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-success" data-dismiss="modal">关闭</button>
	        </div>
	    </div>
	
		<noscript>
	      <div class="container">
	        <center><h3>You must enable javascript to use Kibana</h3></center>
	      </div>
	    </noscript>
	    
	    <div ng-cloak ng-repeat='alert in dashAlerts.list' class="alert-{{alert.severity}} dashboard-notice" ng-show="$last">
      		<button type="button" class="close" ng-click="dashAlerts.clear(alert)" style="padding-right:50px">&times;</button>
      		<strong>{{alert.title}}</strong> 
      		<span ng-bind-html='alert.text'></span>
	      	<div style="padding-right:10px" class='pull-right small'> {{$index + 1}} alert(s) </div>
	    </div>
    	
    	<!-- 顶部导航区 -->
		<jsp:include page="/META-INF/tiles-config/templates/include/desktop-header.jsp"/>
		
		<div class="container" style="background-color : #fff;border:1px solid #e1e4e5;	min-height:400px;">
		    <div ng-repeat="pulldown in dashboard.current.nav" ng-controller="PulldownCtrl" ng-show="pulldown.enable">
			    <kibana-simple-panel type="pulldown.type" ng-cloak>
			    </kibana-simple-panel>
		    </div>
			<!-- 内容摆放区 -->
    		<div ng-cloak ng-view></div>
		</div>
		
		<jsp:include page="/META-INF/tiles-config/templates/include/desktop-copyright.jsp"/>
	</body>
</html>