/**
 * @class Ajax Grid 
 * @param ${Object}, 配置信息
 * @param extends,附加配置信息
 * hasPages :　是否使用分页
 * complete : 表格加载完成后调用的方法
 * type :　必须是POST 或　GET
 * query : 查询使用的方法，返回查询条件
 * dataType : 枚举类型，可以是Object 或　Array数组（采用此方式时,id必须是数组第一个元素）
 * multiSelect : 是否启用多选,默认不启用
 * } 
 */
function AjaxGrid(configs) {
	//设置视图
	this.view = $("#" + configs.el);
	Helper.call(this, configs);
}

$.extend(AjaxGrid.prototype, Helper.prototype, {
	
	/**
	 * 设置默认的配置信息 
	 */
	setDefaults : function() {
		this.debug = false;//是否支持调试
		this.actions = {
			editable : false,//是否显示编辑按钮
			deletable : false//是否显示删除按钮
		};
		//默认的配置信息
		this.defaults = {
			url : this.configs.queryUrl,
			autowidth : true,//自动适应宽度
			shrinkToFit : true,//按比例计算宽度
		    height : "auto",
			cellLayout : 5,
			viewsortcols : [true,'vertical',true],
			cellEdit : false,
			rownumbers : true,//显示编号
			deepempty : true, //此属性可能会影响性能,
			altRows : false, //是否间隔显示记录
			hoverrows : true,//是否激活Hover事件
			emptyrecords : "还没有数据",//没有数据时的提示信息
			footerrow : false,//留出多余的一行
			//默认的排序方式与字段
			sortname: 'id',
			sortorder: 'desc',
			sortable : true
		};
	},
	
	/**
	 * 
 	 * @param {Object} configs
	 */
	initConfigs : function() {
		var configs = this.configs,
			pager = "#" + configs.pager;
		//是否启用分页
		if(configs.hasPages) {
			this.setPager(pager);
		}
		//设置查询方法
		this.setQueryFn(configs.query);
		//设置Ajax请求配置
		this.setAjax(configs.type);
		this.setJsonReader(configs.dataType);
		this.setColModel(configs.colModel);
		this.setComplete(configs.complete);
		this.setSortable(configs.sortable);
		this.setMultiSelect(configs.multiSelect);//设置多选
		this.setActionFormat(configs.editUrl, configs.deleteUrl);
		this.setDeleteAction(configs.deleteAction);
		this.setShowAction(configs.showUrl);
		//初始化按钮
		this.initButtons();
		this.setOnSelectRow(configs.onSelectRow);
	},
	
	/**
	 * 初始化按钮区
	 */
	initButtons : function() {
		var queryForm = this.view.parents(".page-list-body").find("form.form-query"),
			actionButtons = queryForm.find(".action-buttons").find("a.should-selected"),
			_this = this;
		this.actionButtons = actionButtons;//设置操作按钮
		actionButtons.each(function(index, item) {
			//验证地址的有效性
			var actionLink = $(item),
				href = $.trim($(item).attr("href"));
			if(href !== "") {
				actionLink.attr("disabled", true);//
				actionLink.on("click", function(event) {
					var selectedId = _this.getSelected(),
						href = $.trim($(item).attr("href"));
					if(selectedId === null) {
						Dialog.info("请先选择一行数据,然后进行操作!", "提示信息");
					} 
					else {
						href = href + "?id=" + selectedId;
						if(this.target == "_blank") {
							window.open(href);
						} else {
							location.href = href;
						}
						//actionLink.attr("href", href);
						/*location.href = href;
						window.open(href,"ddd");*/
					}
					event.preventDefault();
					//
				});
			}
		})
	},
	
	/**
	 * 
	 */
	setOnSelectRow : function(fn) {
		var _this = this;
		this.defaults.onSelectRow = function(rowid, status, event) {
			if($.isFunction(fn)) {
				fn.call(_this, rowid, status, event);
			}
			_this.actionButtons.removeAttr("disabled");
		};
	},
	
	/**
	 * @function 设置删除需要执行的操作
	 *  
	 */	
	setDeleteAction : function(fn) {
		var column = this._findFirstColumn();
		if(!fn) {
			fn = function(row) {
				var hint = column.label + "为<strong>" + row[column.name] + "</strong>的数据";
				return hint;
			};
		}
		this.deleteAction = fn;
	},
	
	/**
	 * @function 设置查询需要执行的方法
	 */
	setQueryFn : function(queryFn) {
		var queryButton = $("#BUTTON_QUERY"),
			_this = this,
			queryForm = queryButton.parents("form"),
			adQueryButton = $("#AD_BUTTON_QUERY");
		//设置查询地址
		if(this.configs.queryUrl) {
			queryForm.attr("action", this.configs.queryUrl);
		}
		//设置默认的提交动作
		this.queryFn = function() {
			return formToObject(queryForm.get(0));
		};
		//注册提交事件
		queryForm.on("submit", function(event) {
			_this.query();
			event.preventDefault();
		});
		//提交查询动作
		queryButton.on("click", function() {
			_this.query();
		});
		//高级查询执行的动作
		/****/
		adQueryButton.on("click", function(event) {
			var queryBottons = $(this).parents(".query-buttons"),
				formGroups = queryBottons.parents(".form-query").find(".form-group"),
				firstGroup = formGroups.filter(":first"),
				lastGroup = formGroups.filter(":last"),
				varyGroups = formGroups.filter(":gt(0)");
			if(!this.opened) {
				varyGroups.slideDown(400, function() {
					//queryBottons.appendTo(lastGroup);//移动到最后一组
					$(".advance-search").css("color","#1970b2");
					$(".advance-search .advance-open").addClass("advance-open-up");
				});
				this.opened = true;
			} else {
				//移动到第一组
				varyGroups.slideUp(400, function() {
					//queryBottons.appendTo(firstGroup);
				});
				this.opened = false;
			}
			firstGroup.find("input:first").focus();//添加焦点
		});
		
		//当高级查询没有选择时，隐藏高级查询按钮
		var adQuerySize = adQueryButton.parent().parent().next().length;
		if(adQuerySize == 0 ){
			adQueryButton.hide();
		}
		
	},
	
	_getRowId : function(row) {
		if(this.dataType === "ARRAY") {
			return row[0];
		} else {
			return row["id"];
		}
	},
	
	_findFirstColumn  : function() {
		for(var i = 0; i  < this.defaults.colModel.length; i++ ) {
			if(!this.defaults.colModel[i].hidden) {
				return this.defaults.colModel[i];
			}
		}
		return null;
	},
	
	setShowAction : function(url) {
		if(!!url) {
			var colModel = this._findFirstColumn(),
				_this = this;
			colModel.formatter = function(value, options, row) {
				var showUrl = url + "/" + _this._getRowId(row);
				return '<a class="jqrow-action jqrow-action-show" href="' + showUrl + '">' + value +'</a>';
			};
		}
		return null;
	},
	
	/**
	 * 
	 */
	setColModel : function(colModel) {
		$.extend(this.defaults, {
			cmTemplate : {
		    	resizable : false,
		    	title : false,
		    	fixed : false,
		    	editable : false,//是否可编辑
		    	sortable : true,//是否支持排序
		    	align : "left"
		    },//colModel的公共属性
			colModel : colModel
		});
	},
	
	/**
	 * @function 是否启用调试 
	 */
	setDebug : function(debug) {
		this.debug = debug;
	},
	
	/**
	 * @function 是否启用多选 
	 */
	setMultiSelect : function(multiSelect) {
		if(multiSelect) {
			$.extend(this.defaults, {
				multiselect : true,
				multiboxonly : false//设置为true时,只有点击复选框时才能选中,并且在点击任何其它列时,都会导致所有的选择项被清空
			});
		}
	},
	
	/**
	 * 是否启用排序
	 */
	setSortable : function(value) {
		this.defaults.sortable = !!value;
	},
	
	/**
	 * @function 设置JsonReader,与服务器交换数据的方式
	 */
	setJsonReader : function(dataType) {
		this.dataType = dataType || "OBJECT";
		$.extend(this.defaults, {
			prmNames : {
				page : "currPage",
				rows : "pageSize",
				sort : "sortColumn",
				order : "order"
			},//数据参数信息
			jsonReader : {
				root : "results",
				page : "currPage",
				total : "pages",
				records : "count",
				id : "id",
				repeatitems : true//设置此元素为true,将会容许出现重复元素,但是重复的元素无法同时选中
			},
		});
		//如果采用数组的方式
		if(dataType === "ARRAY") {
			$.extend(this.defaults.jsonReader, {
				//通过设置以下两个元素,可以以数组方式加载服务器数据
				id : "0",//id是数组的第一个元素 
				cell : ""
			});
		}
	},
	
	/**
	 * @function 启用Ajax
	 *  
	 */
	setAjax : function(type) {
		var _this = this;
		if(type !== "GET") {
			type = "POST";
		};
		$.extend(this.defaults, {
			autoencode : false,//转义服务器传送的数据，将HTML片段转义为普通字符串
			datatype: 'json',
			mtype: type,//数据的请求方式
			serializeGridData : function(postData) {
				var queryObj = _this.queryFn.call();//获取查询条件的数据;
				$.each(queryObj, function(key, value) {
					if($.isArray(value)) {
						queryObj[key] = value.join();
					}
				});
				return $.extend(postData, queryObj);
			}
		});
	},
	
	/**
	 * @function 表格加载完成后的回调方法
	 */
	setComplete : function(complete) {
		var cp = complete || $.noop;
		this.complete = cp;
		//cp.call(this);//
	},
	
	/**
	 * @function 是否使用分页
	 */
	setPager : function(pageEl) {
		$.extend(this.defaults, {
			 pager: pageEl,
			 pgbuttons : true,//是否显示
			 rowNum:15,//每页的数量
			 rownumWidth : 30,
			 pginput : true,//是否可以输入页码
			 viewrecords: true,//是否显示记录条数
			 rowList:[10, 15, 30, 50, 100, 200],
			 toppager : false//是否在表格顶部也显示分页构件
		});
	},
	
	/**
	 * @function 获取当前选中行,只有启用了multiSelect时才有效,返回所有选中行的id
	 * @return 获取所有选中行的id,以数组形式
	 */
	getSelected : function() {
		if(this.defaults.multiselect) {
			return this.view.jqGrid('getGridParam', "selarrrow");
		} else {
			return this.getFocus();
		}
	},
	
	/**
	 * @function 获取当前焦点所在的行的id 
	 */
	getFocus : function() {
		return this.view.jqGrid('getGridParam', "selrow");
	},
	
	/**
	 * 重新提交查询,刷新当前列表
	 * @function 重新提交查询,刷新当前列表
	 */
	query  : function () {
		this.view.trigger("reloadGrid");
	},
	
	/**
	 * @function 根据id获取当前行数据
	 * @param id 当前所在行的id
	 * @return 以数组方式返回id所代表行的数据
	 */
	getRowData : function (id) {
		return  $(this.view).jqGrid('getRowData',id);
	},
	
	/**
	 * @function 解析并渲染表格 
	 */
	render : function() {
		var _this = this;
		
		$.extend(this.defaults, {
			
			//当数据过少时,设置最低的高度
			loadComplete : function(data) {
				if (!data || !data.results || data.results.length < 5) {
					$(this).jqGrid("setGridHeight", 200);
				} else {
					$(this).jqGrid("setGridHeight", "auto");
				}
			},
			
			//数据载入出错时
			loadError : function(xhr, status, error) {
				$(this).jqGrid("setGridHeight", 200);
				if(_this.debug) {
					throw new Error(error);
				}
			},
			
			/**
			 * 表格加载完成 
			 */
			gridComplete : function() {
				//处理删除按钮
				if(_this.actions.deletable) {
					_this.view.find("a.jqrow-action-delete").on("click", function(event) {
						var id = $(this).attr("data-row-id"),
							row = _this.getRowData(id),
							url = $(this).attr("href"),
							hint = _this.deleteAction.call(this, row);
						hintFull = "您将删除" + hint + ",请确认是否继续!";
						if($(this).prop("disabled")) {
							return false;
						}
						Dialog.confirm(function() {
							$.ajax(url, {
								type : "POST",
								success : function(data) {
									if(data === "SUCCESS") {
										Logger.success("您已经成功删除了"+ hint+"!");
									} else {
										Logger.success("删除"+ hint+"失败!");
									}
									_this.query();//刷新列表
								}
							});
						}, hintFull);
						//添加确认过程
						event.preventDefault();
					});
				}
				//调用完成事件
				_this.complete.call(_this);
			}

		});
		this.view.jqGrid(this.defaults);
	},
	
	/**
	 * @param url:展开子表时需要请求的地址,数据只支持数组,不支持Object Map
	 * @param colModels 子表的列配置信息,数组包含对象,对象只能包含name,width,align,param
	 * 格式为 {
	 * 	name : ["设备类型","设备数量"],
	 	width : [350, 350],
	 	align : ["center", "center"],
	 	params : ["title"]//从父表中抓取一个属性作为参数传递到后台
	 * }
	 */
	setSubGrid : function(url, colModels) {
		this._setSubGridBase();
		//设置子表AJAX基本属性
		$.extend(this.defaults, {
			subGridUrl : url,
			subGridModel : [colModels]
		});
		//设置子表JsonReader属性
		this.defaults.jsonReader.subgrid = {
			root : "results",
			id : "0", 
			cell : ""
		};
	},
	
	_setSubGridBase : function() {
		$.extend(this.defaults, {
			subGrid : true,
			subGridWidth : 20,
			subGridOptions : {
				plusicon : "ui-icon-plus",
				minusicon : "ui-icon-minus",
				openicon : "ui-icon-carat-1-sw",
				delayOnLoad : 50,
				expandOnLoad: false, //在加载父表时就展开子表,并录入子表的数据
				selectOnExpand : false, //选中就会载载入数据
				reloadOnExpand : false //每次展开都会重新加载数据
			}
		});
	},
	
	/**
	 * 
 	 * @param {Object} expand 展开子表时需要执行的方法
 	 * @param {Object} collapse 关闭子表时需要执行的方法
	 */
	setCustomGrid : function(expand, collapse) {
		this._setSubGridBase();
		$.extend(this.defaults, {
			subGridRowExpanded : expand,
			subGridRowColapsed : collapse
		});
	},
	
	setActionFormat : function(editUrl, deleteUrl) {
		var _this = this;
		this.actions.editable = !!editUrl;
		this.actions.deletable = !!deleteUrl;
		var actionFormat = function(value, option, row) {
			var result = "",
				id = _this._getRowId(row);
			if(_this.actions.editable) {
				var eUrl = editUrl + "/" + id;
				result = result + '<a class="jqrow-action jqrow-action-edit" title="编辑" data-row-id="'+  id +'" href="' + eUrl + '"></a>';
			}
			if(_this.actions.deletable) {
				var dUrl = deleteUrl + "/" + id;
				result = result + '<a class="jqrow-action jqrow-action-delete"  title="删除" data-row-id="'+ id +'" href="' + dUrl + '"></a>';
			}
			return result;
		};
		if(this.actions.editable || this.actions.deletable) {
			this.defaults.colModel.push({name:"actions", label:"操作", width:7, formatter : actionFormat, sortable : false});
		}
	}
	
});