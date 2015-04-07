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
	    <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	    <meta name="viewport" content="width=device-width">
	    <title><fmt:message key="melon.title"/></title>
	    <link rel="stylesheet" href="<c:url value='/statics/kibana/css/bootstrap.light.min.css'/>" media="all" />
	    <link rel="stylesheet" href="<c:url value='/statics/kibana/css/timepicker.css'/>" media="all" />
	    <link rel="stylesheet" href="<c:url value='/statics/kibana/css/animate.min.css'/>" media="all" />
	    <link rel="stylesheet" href="<c:url value='/statics/kibana/css/normalize.min.css'/>" media="all" />
	    <link rel="stylesheet" href="<c:url value='/statics/kibana/css/bootstrap-responsive.min.css'/>" media="all" />
	    <link rel="stylesheet" href="<c:url value='/statics/kibana/css/font-awesome.min.css'/>" media="all" />
	    <style type="text/css">
	    	/*设置全局字体*/
			body {
				font-family :"Arial","微软雅黑","宋体","Helvetica Neue",Helvetica,Arial,sans-serif;
			}
	    </style>
	    <!-- load the root require context -->
	    <script type="text/javascript">
			//以下地址需要配置成绝对地址
			var Kibana = {
				rootUrl : "<c:url value='/'/>",
				baseUrl : "<c:url value='/statics/kibana/'/>",
				appUrl : "<c:url value='/statics/kibana/app'/>",
				loadImg : "<c:url value='/statics/kibana/img/load.gif'/>",
				esUrl : "http://10.70.1.10:9200"//服务器地址
			};
	    </script>
	    <script type="text/javascript" src="<c:url value='/statics/kibana/vendor/require/require.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/statics/kibana/app/components/require.config.js'/>"></script>
	    <script type="text/javascript">
	    	require(['app'], function () {})
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
    
    <div ng-cloak ng-repeat='alert in dashAlerts.list' class="alert-{{alert.severity}} dashboard-notice" ng-show="$last">
      <button type="button" class="close" ng-click="dashAlerts.clear(alert)" style="padding-right:50px">&times;</button>
      <strong>{{alert.title}}</strong> 
      <span ng-bind-html='alert.text'></span>
      <div style="padding-right:10px" class='pull-right small'> {{$index + 1}} alert(s) </div>
    </div>
    
	<div ng-cloak class="navbar navbar-static-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<!-- 头部版权信息 -->
				<span class="brand">
					<img src="<c:url value='/statics/kibana/img/small.png'/>" bs-tooltip="'下一代日志审计系统'" data-placement="bottom">
					{{dashboard.current.title}}
				</span>
				<ul class="nav pull-right" ng-controller='dashLoader' ng-init="init()" ng-include="'<c:url value='/statics/kibana/app/partials/dashLoader.html'/>'">
				</ul>
			</div>
		</div>
	</div>

	<!-- 具体内容信息 -->
    <div ng-cloak ng-view></div>

  </body>
</html>
