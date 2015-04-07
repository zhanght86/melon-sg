<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<link rel="stylesheet" href="<mh:theme code='jquery.fullcalendar.css'/>" media="all" />
<script src="<mh:theme code='jquery.fullcalendar.js'/>" type="text/javascript"></script>
<style type="text/css">
	#calendar .form-group {
		margin : 10px 0px;
	}
	.fc-widget-header{
		padding:5px 0px;
	}
	.fc-event {
		background : none;
		border : none;
		color : #ddd;
		font-size : 14px;
	}
	
	.fc-event:hover {
		color : #ddd;
	}
	
	.fc-other-month{
		color:#dbdbdb;
	}
	
	.fc-day-grid .fc-day-number{
		padding:10px 8px;
	}
	
	.fc-work-day {
		background-color : #ffffff;
		color:#808080;
		font-size:14px;
	}
	.fc-holi-day {
		background-color : #f0f0f0;
		color:#b2b2b2;
	}
	.fc-view .fc-event-container .fc-content{
		background-color: #beecfa;
		border:1px solid #88d6f2;
		border-radius:2px;
		color:#3c3c3c;
		font-size:12px;
		padding:2px 6px;
	}
	.fc-day-grid .fc-state-highlight{
		background-color: #beecfa;
	}
	.fc-content-skeleton .fc-state-highlight{
		color:#2e9bd1;
	}
	.fc-toolbar .fc-center h2{
		font-size:20px;
	}
	/*   弹框      */
	.ui-dialog .ui-dialog-buttonset .ui-state-default{
		
	}
</style>
<div id='calendar'></div>
<div id="calendar-form" class="melon-dialog">
	<sf:form modelAttribute="calendar" >
		
		<input type="hidden" name="startTime"/>
		
		<input type="hidden" name="endTime"/>
		
		<sf:hidden path="id"/>
		
		<div class="form-group">
			<div class="alert alert-info col-xs-12" id="action-hint">
			</div>
			<span class="control-label col-xs-12">
			</span>
		</div>
		
		<div class="form-group" style="margin:10px 0 10px 0">
			<sf:label path="remarks" cssClass="col-xs-3 control-label">
				<fmt:message key="organ.organization.remark" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<sf:textarea path="remarks" cssClass="form-control"  rows="3" maxlength="500" />
			</div>
		</div>
		
	</sf:form>
	
</div>
<script type="text/javascript">
var calendar = {
		
	startTime : null,
	
	endTime : null,
	
	options : {
		
		fixedWeekCount : false,
		
		//设置当前时间
		now : '${now}',
		
		header: {
			left: 'prev, next',
			center: 'title'
		},
		
		//解析事件
	    eventRender: function(event, element) {
	    	//阻止默认的事件提交
	    	return !!event.dateFlag;
	    },
	    
	    //初始化事件
	    events: {
			url: "<c:url value='/system/calendar/find'/>",
			success : function(response) {
				var datas = response.events,
					start = response.start,
					end = response.end;
				//设置初始时间
				calendar.startTime = start;
				calendar.endTime = end;
				//将此数据设入到URL中
				$.each(datas, function(index, data) {
					var dayView = calendar.getDayView(data.date);
					dayView.data("event", data);
					calendar.refreshDayView(dayView);//更新样式
				});
				return datas;
			}
	    },
	    
	    //日期解析
		dayRender : function(date, cell) {
			if($(cell).hasClass("fc-today")) {
				$(cell).removeClass("fc-today")
			}
		},
	    
	    /**
	    */
	    dayClick: function(date, jsEvent, view) {
	    	var _this = $(this),
				event = _this.data("event"),
				actionHint = $("#action-hint"),
				modal = calendar.modal;
	    	modal.dialog("option", "title", "<fmt:message key='system.calendar.title' />");
	    	//替换提示文字
	    	actionHint.text(calendar.getMessage(event));
	    	//如果是标记过的数据,则更新与删除数据
	    	if(event.dateFlag){
	    		modal.dialog("option", "buttons", [{
					"text" : "<fmt:message key='button.save'/>",
					click : function() {
						//提交数据
						var url = "<c:url value='/system/calendar/update'/>";
						$.post(url, {id:event.id, date:event.date, remarks:$("#remarks").val()}, function(result) {
							//更新数据
							_this.data("event",result);
							calendar.refreshDayView(_this);
							//刷新页面
							window.location = "<c:url value='/system/calendar/show?defaultDate=' />" + event.date;
							modal.dialog("close");
						});
					}
				}, {
					"text" : "<fmt:message key='button.remove' />",
					click : function() {
						var url = "<c:url value='/system/calendar/remove/' />"+event.id;
						$.post(url, function(id) {
							event.dateFlag = false;
							event.workDay = !event.workDay;
							_this.data("event", event);//更新数据
							calendar.removeEvent(_this, event);
							modal.dialog("close");
						});
						modal.dialog("close");
					}
				},  {
					"text" : "<fmt:message key='button.cancel' />",
					click : function() {
						modal.dialog("close");
					}
				}]);
	    		//打开对话框
	    		$("#remarks").val(event.remarks);
	    		modal.dialog("open");
	    	} else {
	    		//否则添加数据,每次添加数据后,都刷新页面
	    		modal.dialog("option", "buttons", [{
					"text" : "<fmt:message key='button.add' />",
					click : function() {
						var url = "<c:url value='/system/calendar/create' />";
						$.post(url, {
								date:event.date,
								remarks:$("#remarks").val()
							}, function(result) {
							//
							calendar.addEvent(result);
							model.dialog("close");
						});
					}
				}, {
					"text" : "<fmt:message key='button.cancel' />",
					click : function() {
						modal.dialog("close");
					}
				}]);
	    		modal.dialog("open");
	    	}
	    }
	},
	
	/**
	*/
	addEvent : function(event) {
		var dayView = calendar.getDayView(event.date);
		//绑定数据信息
		dayView.data("event", event);
		//添加事件源 (method)
		this.view.fullCalendar('renderEvent', event);
		//刷新页面
		window.location = "<c:url value='/system/calendar/show?defaultDate=' />" + event.date;
	},
	
	removeEvent : function(dayView, event) {
		this.view.fullCalendar('removeEvents', [event.id]);
		this.refreshDayView(dayView);
	},
	
	/**/
	getMessage : function(event) {
		if(event.weekend) {
			return Melon.format('<fmt:message key="system.calendar.work.hint"/>', event.date);
		} 
		return Melon.format('<fmt:message key="system.calendar.rest.hint"/>', event.date);
	},
	
	/*
     * 获取日历天的视图
     */
    getDayView : function(date) {
    	return this.view.find("td[data-date=" + date + "]");
    },
    
    //初始化
	init : function(el) {
		this.view = $(el).fullCalendar(this.options);
		this.modal = $("#calendar-form").dialog({
			dialogClass : "dialog-default",
			modal : true,
			draggable : false,
			resizable : false,
			autoOpen : false,
			height : 400,
			width : 450
		});
	},
    
    //刷新日期视图
    refreshDayView : function(dayView) {
    	var event = dayView.data("event");
    	if(event.workDay) {
    		dayView.addClass("fc-work-day");
    		dayView.removeClass("fc-holi-day");
    	} else {
    		dayView.addClass("fc-holi-day");
    		dayView.removeClass("fc-work-day");
    	}
    }
	
};

<c:if test="${!empty defaultDate}">
$.extend(calendar.options, {
	defaultDate : '${defaultDate}',
});
</c:if>
$(document).ready(function() {
	calendar.init("#calendar");	
});
</script>