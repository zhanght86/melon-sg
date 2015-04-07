<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>

<style type="text/css">
	#portal-manager .panel-heading {
		text-align : right;
	}
	
	#portal-manager .col-xs-8 .panel-body {
		min-height : 300px;
	}
	
	#layouts >li >a {
		display : block;
	}
	
	#layouts >li.active > a {
		color : #FFF;
	}
</style>

<div class="row" id="portal-manager">
	<div class="col-xs-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<button class="btn btn-primary" id="createLayout"><fmt:message key="button.createLayout"/></button>
				<button class="btn btn-default" id="updateLayout"><fmt:message key="button.update"/></button>
				<button class="btn btn-default" id="removeLayout"><fmt:message key="button.remove"/></button>
			</div>
			<div class="panel-body text-info">
				<fmt:message key="commons.portlet.layout.hint" />
			</div>
			<ul class="list-group" id="layouts">
				<c:forEach items="${layouts}" var="layout" varStatus="status">
					<li class="list-group-item" data-id="${layout.id}" data-ltr="${layout.leftToRight}">
						<a href="javascript:void(0);">${layout.typeName}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="col-xs-8" style="padding-left:0px;">
		<div class="panel panel-default">
			<div class="panel-heading" id="layoutButtons">
				<button class="btn btn-primary" id="btnPreview" disabled><fmt:message key="button.preview"/></button>
				<button class="btn btn-default" id="copyView" disabled><fmt:message key="button.copyView"/></button>
				<button class="btn btn-default" id="createView" disabled><fmt:message key="button.createView"/></button>
			</div>
			<div class="panel-body" id="views">
			</div>
		</div>
	</div>	
</div>

<!-- 对话框 -->
<div class="modal fade" id="myModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span>
					<span class="sr-only"><fmt:message key="button.close"/></span>
				</button>
				<h4 class="modal-title">
					<fmt:message key="button.createLayout"/>
				</h4>
			</div>
			<div class="modal-body">
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">
					<fmt:message key="button.close"/>
				</button>
				<button type="button" id="btnSave" class="btn btn-primary">
					<fmt:message key="button.save"/>
				</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var crLayoutUrl = '<c:url value="/home/portal/createLayout"/>',//创建视图地址
		crViewUrl = '<c:url value="/home/portal/createView/"/>',//创建组件地址
		listViewUrl = '<c:url value="/home/portal/listView/"/>',//视图摆放地址
		sortLayoutUrl = '<c:url value="/home/portal/sortLayout"/>',//视图排序地址
		sortViewUrl = '<c:url value="/home/portal/sortView"/>',//视图排序地址
		rmLayoutUrl = '<c:url value="/home/portal/removeLayout/"/>',//更新视图地址
		rmViewUrl = '<c:url value="/home/portal/removeView/"/>',//更新组件地址
		upLayoutUrl = '<c:url value="/home/portal/updateLayout/"/>',//更新视图地址
		upViewUrl = '<c:url value="/home/portal/updateView/"/>',//更新组件地址
		copyViewUrl = '<c:url value="/home/portal/copyView/" />',//复制组件地址
		previewUrl = '<c:url value="/home/portal/preview/"/>';//预览视图地址
		
	
	var Portals = {
			//0:创建视图,1:创建组件,2:修改视图,3:修改组件,4:复制视图
			action : 0,
			
			currLayout : null,
			
			viewSorted : false,
			
			/**/
			addLayout : function(layout) {
				var item = $('<li class="list-group-item"></li>').attr("data-id", layout.id)
																 .attr("data-ltr", layout.leftToRight)
															     .text(layout.typeName)
															     .appendTo(this.layouts);
				this.registerLayout(item);
				this.layouts.sortable("refresh");
				this.setCurrLayout(item);
			},
			
			/*激活视图布局的相关按钮*/
			enableButtons : function() {
				this.layoutButtons.prop("disabled", false);
			},
			
			//初始化
			init : function() {
				var _this = this;
				this.myModal = $('#myModal');
				this.modalBody = this.myModal.find(".modal-body");
				this.views = $("#views");
				this.layoutButtons = $("#layoutButtons>button");
				//
				this.initLayout();	
				//初始化视图操作注册
				this.initCreateLayout();
				this.initUpdateLayout();
				this.initRemoveLayout();
				//初始化Portal操作注册
				this.initCreatePortal();
				this.initSortable();
				this.initCopyView();
				this.initPreviewPortal();
				//注册通用的保存按钮
				this.initSave();
			},
			
			//初始化视图创建器
			initCreateLayout : function() {
				var _this = this;
				//注册创建视图按钮
				$("#createLayout").on("click", function(event) {
					//设置状态
					_this.action = 0;
					//载入内容
					_this.modalBody.load(crLayoutUrl , function() {
						_this.myModal.modal('show');
					});
					event.preventDefault();
				});
			},
			
			//修改视图
			/**/
			initUpdateLayout : function() {
				var _this = this;
				$("#updateLayout").on("click", function(event) {
					_this.action = 2;
					//载入内容
					_this.modalBody.load(upLayoutUrl + _this.currLayout.attr("data-id"), function() {
						_this.myModal.modal('show');
					});
					event.preventDefault();
				});
			},
			
			//创建仪表盘按钮
			initCreatePortal : function() {
				var _this = this;
				//注册创建仪表盘按钮
				$("#createView").on("click", function(event) {
					//设置状态
					_this.action = 1;
					//载入内容
					_this.modalBody.load(crViewUrl + _this.currLayout.attr("data-id"), function() {
						_this.myModal.modal('show');
					});
				});
			},
			
			//复制视图按钮
			initCopyView : function() {
				var _this = this;
				//注册创建仪表盘按钮
				$("#copyView").on("click", function(event) {
					//设置状态
					_this.action = 4;
					//载入内容
					_this.modalBody.load(copyViewUrl + _this.currLayout.attr("data-id"), function() {
						_this.myModal.modal('show');
					});
				});
			},
			
			//预览portal
			initPreviewPortal : function() {
				
				var _this = this;
				//注册创建仪表盘按钮
				$("#btnPreview").on("click", function(event) {
					//预览内容
					window.location = previewUrl + _this.currLayout.attr("data-id") + "?ltr=" + _this.currLayout.attr("data-ltr");
				});
				
			},
			
			//初始化更新仪表盘,载入视图列表页面后执行,见list.jsp
			initUpdatePortal : function() {
				var _this = this;
				this.views.find("button.view-update").on("click", function(event) {
					//设置状态
					_this.action = 3;
					//载入内容
					_this.modalBody.load(upViewUrl + $(this).attr("data-id"), function() {
						_this.myModal.modal('show');
					});
					event.preventDefault();
				});
			},
			
			/*
			 * 初始化删除仪表盘
			 * 载入视图列表页面后执行,见list.jsp
			 */
			initRemovePortal : function() {
				var _this = this;
				this.views.find("button.view-remove").on("click", function(event) {
					var button = $(this),
						url = rmViewUrl + button.attr("data-id"),
						portal = button.parents(".home-portal"),
						text = portal.find("h4").text();
					//确认删除过程
					Dialog.confirm(function() {
						$.post(url, function() {
							//如果删除成功,删除DOM节点并重新刷新
							portal.remove();
							_this.views.sortable("refresh");
							Logger.success("<fmt:message key='hint.success'/>");
						});
						//提交删除仪表盘请求
					}, Melon.format("<fmt:message key='home.portal.removeView.hint'/>", text));
					event.preventDefault();
				});
			},
			
			//初始化视图布局
			initLayout : function() {
				var items,
					_this = this;
				this.layouts = $("#layouts");
				items = this.layouts.find(">li");
				//注册点击事件
				this.registerLayout(items);
				if(items.size() > 0) {
					this.setCurrLayout(items.get(0));
				}
			},
			
			//初始化视图的排序
			initSortable : function() {
				
				var _this = this;
				//视图排序
				this.layouts.sortable({
					appendTo : "parent",
					forceHelperSize: true,
					cursor: "move",
					helper : function(event, ui) {
						return $(ui).clone(true).addClass("list-group-item-success");
					},
					stop : function(event, ui) {
						var ids = [];
						_this.layouts.find('li.list-group-item').each(function(index, item) {
							ids.push($(item).attr("data-id"));
						});
						$.post(sortLayoutUrl, {ids:ids.join(",")}, function() {
							
						});
					}
				});
			},
			
			//注册保存按钮
			initSave : function() {
				var _this = this;
				$("#btnSave").on("click", function(event) {
					var	form = _this.modalBody.find("form"),
						url = form.attr("action"),
						datas = form.serialize();
					//判断是否添加layout
					if(form.attr("id") == "layout") {
						if(!layoutForm.valid()) {
							return false;
						}
					}
					//提交数据
					$.post(url, datas, function(domain) {
						switch(_this.action) {
						
							case 0://添加新视图
							_this.addLayout(domain);	
							break;
							
							//添加仪表盘
							case 1:
							_this.loadCurrent();
							break;
							
							//更新视图
							case 2:
							_this.loadCurrent(domain);
							break;
							
							//更新仪表盘
							case 3:
							_this.loadCurrent();
							break;
							
							//复制仪表盘
							case 4 :
							_this.loadCurrent(domain);
							break;
						}
						_this.myModal.modal('hide');
					});
				});
			},
			
			/**
			 * 载入组件视图
			 */
			listView : function(id, ltr) {
				var _this = this;
				this.views.load(listViewUrl + id + "?ltr=" + ltr, function() {
					//激活所有按钮
					_this.enableButtons();
					_this.refreshViews();
				});
			},
			
			/*注册视图点击事件*/
			registerLayout : function(layout) {
				var _this = this;
				layout.on("click", function(event) {
					_this.setCurrLayout(this);
					event.preventDefault();
				});
			},
			
			/*刷新仪表盘配置*/
			refreshViews : function() {
				var width = this.views.width() - 1,
					_this = this;//修复溢出问题,可能造成1px的偏差
				this.views.find(">.home-portal").each(function(index, item) {
					var view = $(item),
						ow = parseInt(view.attr("data-width")),
						oh = parseInt(view.attr("data-height")),
						lw = Math.floor(width * ow / 12),
						lh = Math.floor(width * oh / 12);
					view.css({
						width : lw,
						height : lh
					});
				});
				//
				if(this.viewSorted) {
					this.views.sortable("refresh");
				} else {
					this.views.sortable({
						appendTo : "parent",
						items : "div.home-portal",
						forceHelperSize: true,
						cancel : "button",
						cursor: "move",
						helper : function(event, ui) {
							return $(ui).clone(true).addClass("alert alert-success");
						},
						stop : function(event, ui) {
							var ids = [];
							_this.views.find('div.home-portal').each(function(index, item) {
								ids.push($(item).attr("data-id"));
							});
							$.post(sortViewUrl, {ids:ids.join(",")}, function() {
								
							});
						}
					});
					this.viewSorted = true;
				}
				
			},
			
			/**
			 * 初始化删除布局
			 */
			initRemoveLayout : function() {
				var _this = this;
				$("#removeLayout").on("click", function(event) {
					//收集基本信息
					var url = rmLayoutUrl + _this.currLayout.attr("data-id"),
						text =  _this.currLayout.text();
					//确认删除过程
					Dialog.confirm(function() {
						$.post(url, function() {
							//如果删除成功
							_this.removeCurrent();
							Logger.success("<fmt:message key='hint.success'/>");
						});
					}, Melon.format("<fmt:message key='home.portal.removeLayout.hint'/>", text));
					//)
					event.preventDefault();
				});
			},
			
			//设置当前的焦点
			setCurrLayout : function(layout) {
				if(this.currLayout != null) {
					this.currLayout.removeClass("active");
				}
				this.currLayout = $(layout);
				this.currLayout.addClass("active");
				this.loadCurrent();
			},
			
			/**
			 * 加载当前的PortalView视图
			 */
			loadCurrent : function(domain) {
				if(!!domain) {
					this.currLayout.attr("data-ltr", domain.leftToRight);
					this.currLayout.find(">a").text(domain.typeName);
				}
				this.listView(this.currLayout.attr("data-id"), this.currLayout.attr("data-ltr"));
			},
			
			/*
			 * 删除当前的Portal布局
			 */
			removeCurrent : function() {
				var prev = this.currLayout.prev(),
					next = this.currLayout.next();
				this.currLayout.remove();
				if(prev.size() > 0) {
					this.setCurrLayout(prev);
					return;
				}
				if(next.size() > 0) {
					this.setCurrLayout(next);
					return;
				}
				//如果都不存在
				location.href = location.href;//刷新一次
			}
	};
	
	$(document).ready(function(event) {
		var myModal = $("#myModal"),
			height = myModal.height(),
			dialog = myModal.find(".modal-dialog");
		//dialog位置
		dialog.css("marginTop", (height-150)/2);
		Portals.init();
	});
</script>