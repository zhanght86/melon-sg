<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mh" uri="http://www.legendsec.com/melon/html"%>
<style type="text/css">
	#portals>list-group-item>a {
		display : block;
	}
	/*向右排*/
	.portal-align-left .melon-portal {
		float : left;
	}
	
	/*向左排*/
	.portal-align-right .melon-portal {
		float : right;
	}
	
	#portal-container .panel-heading {
		text-align : right;
	}
	#portal-container .melon-portal{
		font-size : 12px;
		margin : 5px;
	}
	#portal-container .melon-portal .panel-heading {
		text-align : right;
		padding : 5px;
	}
	#portal-container .melon-portal .panel-footer {
		text-align : right;
		padding : 5px;
	}
	#portals-container {
		padding : 5px;
		padding-bottom : 30px;
	}
	
	.layout-action-edit, .layout-action-delete{
		height:16px;
		width:16px;
		display:inline-block;	
		background-image : url('<c:url value="/statics/themes/default/melon/images/melon-commons-icon.png" />');
	}
	.layout-action-edit{
		background-position: -62px -124px;
	}
	.layout-action-edit:hover{
		background-position: -88px -124px;
	}
	.layout-action-delete{
		float:right;
		background-position: -113px -124px;
	}
	.layout-action-delete:hover{
		background-position: -139px -124px;
	}
</style>

<div class="row" id="portal-container">
	<div class="col-xs-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<button class="btn btn-primary" id="btnEdit"><fmt:message key="button.edit"/></button>
				<button class="btn btn-default" id="btnCustom" style="display:none;"><fmt:message key="button.cusPortal"/></button>
				<button class="btn btn-primary" id="btnDone" style="display:none;"><fmt:message key="button.finish"/></button>
			</div>
			<div class="panel-body text-info">
				<fmt:message key="commons.portlet.layout.hint" />
			</div>
			<ul class="list-group" id="portals" style="list-style : none;">
			</ul>
		</div>
	</div>
	<div class="col-xs-8" style="padding-left:0px;">
		<div class="panel panel-default">
			<div class="panel-heading">
				<button class="btn btn-primary" id="btnCreate"><fmt:message key="button.create"/></button>
				<button class="btn btn-default" id="btnPreview"><fmt:message key="button.preview"/></button>
				<button class="btn btn-default" id="btn"><fmt:message key="button.leftAlign" /></button>
			</div>
			<ul class="panel-body portal-align-left" id="portalLayout" style="list-style : none; min-height: 250px">
			</ul>
		</div>
	</div>
</div>

<!-- view对话框 -->
<div id="viewDialog" class="melon-dialog" >
	<sf:form modelAttribute="view" id="viewForm" cssClass="form-horizontal">
		
		<sf:hidden path="id"/>
		<sf:hidden path="xpos"/>
		
		<div class="form-group" style="margin:10px 0 10px 0">
			<sf:label path="width" cssClass="col-xs-3 control-label">
				<fmt:message key="system.home.view.width" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<mh:dictionary var="length" key="length" />
				<sf:select path="width" cssClass="form-control" items="${length}" />
			</div>
		</div>
		
		
		<div class="form-group" style="margin:10px 0 10px 0">
			<sf:label path="height" cssClass="col-xs-3 control-label">
				<fmt:message key="system.home.view.height" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<sf:select path="height" cssClass="form-control" items="${length}" />
			</div>
		</div>
		
	</sf:form>
</div>

<!-- layout对话框 -->
<div id="layoutDialog" class="melon-dialog" >
	<sf:form modelAttribute="layout" id="layoutForm" cssClass="form-horizontal">
		
		<sf:hidden path="id"/>
		
		<div class="form-group" style="margin:10px 0 10px 0">
			<sf:label path="typeName" cssClass="col-xs-3 control-label">
				<fmt:message key="system.home.layout.typeName" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<sf:input path="typeName" cssClass="form-control" maxlength="50" />
			</div>
		</div>
		
		
		<div class="form-group" style="margin:10px 0 10px 0">
			<sf:label path="remarks" cssClass="col-xs-3 control-label">
				<fmt:message key="system.home.layout.remarks" />
			</sf:label>
	
			<div class="col-xs-9 form-field">
				<sf:textarea path="remarks"  cssClass="form-control" rows="3" maxlength="256" />
			</div>
		</div>
		
	</sf:form>
</div>

<!-- view模板 -->
<li class="panel panel-default melon-portal viewTemp" style="width : 100px;display:none">
	<div class="panel-heading">
		
		<button class="btn btn-xs btn-default viewEdit">
			<span class="glyphicon glyphicon-pencil" ></span>
		</button>
		<button class="btn btn-xs btn-default viewRemove">
			<span class="glyphicon glyphicon-remove"></span>
		</button>
	</div>
	<div class="panel-body">
	</div>
</li>

<!-- layout模板 -->
<li class="list-group-item layoutTemp" style="display:none">
	<a href="#"></a>
	<a class="layout-action-delete btnRemove" style="display:none" itemId="" href="#"></a>
</li>

<script type="text/javascript">

	function PortalManage(layout, viewPortal) {
		this.layout = $(layout);//菜单区域
		this.viewPortal = $(viewPortal);//视图区域
		this.items = [];//菜单
		this.itemId;//菜单id
		//对户框默认配置
		this.defDialog = {
				dialogClass : "dialog-default",
				modal : true,
				draggable : false,
				resizable : false,
				autoOpen : false,
				height : 300,
				width : 450
		};
		//视图表单
		this.viewModel = $("#viewDialog").dialog(this.defDialog);
		//布局表单
		this.layoutModel = $("#layoutDialog").dialog(this.defDialog);
		//初始化数据
		this.init();
	}
	
	$.extend(PortalManage.prototype, {
		
		init : function() {
			var _this = this,
				layouts = <mh:toJson value = "${layouts}"/>;
				
			//菜单项模板事件绑定
			$(".layoutTemp").on("click",function(event){
			    //背景切换
			    _this.layout.children(".active").removeClass("active");
			    $(this).addClass("active");
				_this.itemId = $(this).attr("id");
				//查询视图
				_this.listView($(this).attr("id"));
				event.preventDefault();
			});
			
			//视图编辑事件绑定
			$(".viewEdit").click(function() {
				var view = $(this).parents(".melon-portal"),
					id = view.data("model").id,
					width = view.data("model").width,
					height = view.data("model").height;
					xpos = view.data("model").xpos;
				$("#width").find("option[value="+width+"]").prop("selected",true);
				$("#height").find("option[value="+height+"]").prop("selected",true);
				$("#viewForm>#id").val(id);
				$("#viewForm>#xpos").val(xpos);
				_this.viewModel.dialog("option", "buttons", 
						[{
							"text" : "close",
							click : function() {
								_this.viewModel.dialog("close");
							}
						},{
							"text" : "save",
							click : function() {
								data = $("#viewForm");
								_this.updateView(data, view);
								_this.viewModel.dialog("close");
							}
						}]);
				_this.viewModel.dialog("open");
			});
			
			//视图删除事件绑定
			$(".viewRemove").click(function() {
				
				var view = $(this).parents(".melon-portal"),
					id = view.data("model").id,
					removeView = "<c:url value='/home/portalView/remove/' />"+id;
				$.post(removeView, function(data) {
					view.remove();
				});
				
			});
				
			//初始化菜单项
			$.each(layouts, function(key, item) {
				var cus = $(".layoutTemp").clone(true).removeClass("layoutTemp").prop("id", item.id).show();
				cus.find("a:eq(0)").text(item.typeName);
				cus.find("a:eq(1)").prop("itemId", item.id);
				_this.layout.append(cus);
				_this.items.push(cus.get(0));
			});
			
			this.register();
		},
		
		//按钮注册
		register : function() {
			
			//编辑页面
			$("#btnEdit").click(function() {
				$("#btnCustom").show();
				$("#btnDone").show();
				$(".btnRemove").show();
				$(this).hide();
				
			});
			
			//完成按钮
			$("#btnDone").click(function() {
				$("#btnEdit").show();
				$("#btnCustom").hide();
				$(".btnRemove").hide();
				$(this).hide();
				
			});		
		},
		
		//菜单排序
		sortLayout : function(ids) {
			var sortLayout = "<c:url value='/home/portal/sortLayout' />";
				$.post(sortLayout, {ids:ids.join(",")}, function(data) {
					
			});
		},
		
		//视图排序
		sortView : function(ids) {
			var sortView = "<c:url value='/home/portalView/sortView' />";
				$.post(sortView, {ids:ids.join(",")}, function(data) {
					
			});
		},
		
		//删除菜单
		removeLayout : function(id) {
			var _this = this,
				removeUrl = "<c:url value='/home/portal/remove' />";
			if(id!=null) {
				$.post(removeUrl, {id:id}, function(data) {
					_this.layout.children("#"+id).remove();
					_this.viewPortal.empty();
				});
			}else {
				Dialog.info("请选择菜单项！");
			}
		},
		
		//显示视图
		listView : function(id) {
			var _this = this,
				listUrl = "<c:url value='/home/portalView/listView' />";
				this.viewPortal.empty();//清空视图区域
			$.post(listUrl, {id:id}, function(views) {
				$.each(views, function(key, item) {
					var view = $(".viewTemp").clone(true).removeClass("viewTemp").prop("id", item.id).show(),
						parWidth = _this.viewPortal.width(),
						unitW = (parWidth-48)/4,
						unitH = 135,
						countH = item.height,
						countW = item.width;
					view.data("model", item);
					view.width(unitW*countW+(countW-1)*12);
					view.height(unitH*countH+(countH-1)*12);
					_this.viewPortal.append(view);
				});
				
			});
		},
		
		/*
		 *新建视图
		 *view 视图对象
		*/
		createView : function(view) {
			var _this = this,
				createVUrl = "<c:url value='/home/portalView/addView/' />"+this.itemId;
			if(this.itemId!=null) {
				$.post(createVUrl, view.serialize(), function(data) {
					var view = $(".viewTemp").clone(true).removeClass("viewTemp").prop("id", data.id).show(),
						parWidth = _this.viewPortal.width(),
						unitW = (parWidth-52)/4,
						unitH = 135,
						countH = data.height,
						countW = data.width;
					view.data("model", data);
					view.width(unitW*countW+(countW-1)*12);
					view.height(unitH*countH+(countH-1)*12);
					_this.viewPortal.append(view);
				});
			}else {
				Dialog.info("请选择菜单项！");
			}
			
		},
		
		/*
		 *更新视图
		 *view 视图对象
		*/
		updateView : function(viewForm, viewModel) {
			var _this = this,
			updateUrl = "<c:url value='/home/portalView/updateView/' />"+this.itemId;
			$.post(updateUrl, viewForm.serialize(), function(data) {
				var parWidth = _this.viewPortal.width(),
					unitW = (parWidth-52)/4,
					unitH = 135,
					countH = data.height,
					countW = data.width;
				viewModel.data("model", data);
				viewModel.width(unitW*countW+(countW-1)*12);
				viewModel.height(unitH*countH+(countH-1)*12);
			});
		},
		
		/*
		 *新建菜单
		 *layout 菜单对象
		*/
		createLayout : function(layout) {
			var _this = this,
				createLUrl = "<c:url value='/home/portal/addLayout' />";
			$.post(createLUrl, layout.serialize(), function(data) {
					var cus = $(".layoutTemp").clone(true).removeClass("layoutTemp").prop("id", data.id).show();
					cus.find("a:eq(0)").text(data.typeName);
					cus.find("a:eq(1)").prop("itemId", data.id);
					_this.layout.append(cus);
					_this.items.push(cus.get(0));
				});
		}
		
	});
	
	$(document).ready(function() {
		var portals = $("#portals"),
			viewPortal = $("#portalLayout"),
			manage = new PortalManage("#portals", "#portalLayout"),
			viewModal = manage.viewModel,
			layoutModal = manage.layoutModel;
			//注册viewdialog按钮
			viewModal.dialog("option", "buttons", 
			[{
				"text" : "close",
				click : function() {
					viewModal.dialog("close");
				}
			},{
				"text" : "save",
				click : function() {
					data = $("#viewForm");
					manage.createView(data);
					viewModal.dialog("close");
				}
			}]);
			//注册layoutdilog按钮
			layoutModal.dialog("option", "buttons", 
					[{
						"text" : "close",
						click : function() {
							layoutModal.dialog("close");
						}
					},{
						"text" : "save",
						click : function() {
							data = $("#layoutForm");
							manage.createLayout(data);
							layoutModal.dialog("close");
						}
					}]);
		
		//菜单排序
		portals.sortable({
			appendTo : "parent",
			axis: "y",
			forceHelperSize: true,
			cursor: "move",
			helper : function(event, ui) {
				return $(ui).clone(true).addClass("list-group-item-success");
			},
			stop : function(event, ui) {
				//重新计算所有的顺序;
				manage.sortLayout(portals.sortable('toArray'));
				
			}
		});
		
		//视图排序
		viewPortal.sortable({
			appendTo : "parent",
			forceHelperSize: true,
			cursor: "move",
			helper : function(event, ui) {
				return $(ui).clone(true).addClass("list-group-item-success");
			},
			stop : function(event, ui) {
				//重新计算所有的顺序;
				manage.sortView(viewPortal.sortable('toArray'));
				
			}
		});
		
		//删除按钮
		$(".btnRemove").click(function(event) {
			manage.removeLayout($(this).attr("itemId"));
			event.stopPropagation();
		});
		
		//新建按钮
		$("#btnCreate").click(function() {
			$("#btnEdit").show();
			$("#btnCustom").hide();
			$(".btnRemove").hide();
			$("#btnDone").hide();
			$("#viewForm>#id").val("");
			var ids = viewPortal.sortable('toArray');
			$("#viewForm>#xpos").val(ids.length+1);
			if(manage.itemId!=null) {
				viewModal.dialog("option", "buttons", 
						[{
							"text" : "close",
							click : function() {
								viewModal.dialog("close");
							}
						},{
							"text" : "save",
							click : function() {
								data = $("#viewForm");
								manage.createView(data);
								viewModal.dialog("close");
							}
						}]);
				viewModal.dialog("open");
			}else {
				Dialog.info("请选择菜单项！");
			}
		});

		
		//自定义按钮
		$("#btnCustom").click(function() {
			layoutModal.dialog("open");
		});
		
		//预览
		$("#btnPreview").click(function() {
			
		});
		
		//对齐方式
		$("#btnCreate").click(function() {
			
		});
		
		

	});
</script>
