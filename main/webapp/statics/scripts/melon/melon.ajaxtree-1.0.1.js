/**
 * @class AjaxTree Ajax树,主要体现在后台 
 * @param {Object} configs
 * @param {Object} exconfigs
 */
function AjaxTree(configs) {
	this.view = $("#" + configs.el);
	Helper.call(this, configs);
}

$.extend(AjaxTree.prototype, Helper.prototype, {
	
	/**
	 * 默认的根节点ID
	 * */
	ROOT_ID : 1,
	
	/**
	 * 
	 */
	setDefaults : function() {
		//默认启用的配置
		this.defaults = {
			core : {
				//主题配置
				"themes" : {
					name : "default",
					"variant" : "large", //是否是大图标方式显示
					"dots" : false, //是否显示图标之间的连接线
					icons : true, //是否显示图标
					stripes : false, //是否用颜色来区分间隔行
					'responsive' : false
				},
				animation : 200,
				multiple : false, //是否可以同时选中多个节点
				data : null,
				check_callback : true,//默认支持所有操作
				error : function(str) {
					if(_this.debug) {
						throw new Error(str);
					}
				},
				force_text : false//是否对内容进行转义（ESCAPE）
			},
			plugins : ["wholerow", "search", "sort"]
		};
	},
	
	/**
	 * 
 	 * @param {Object} configs
	 */
	initConfigs : function() {
		this.setDebug(this.configs.debug);//设置Debug模式
		this.setSort(this.configs.sortName, this.configs.sortOrder);//设置排序
		this.setMultiSelect(this.configs.multiSelect);//是否启用多选,复选框
		this.setAjax(this.configs.url, this.configs.rootId, this.configs.params);//设置ajax
		this.setAjaxConfig(this.configs.ajaxConfig);//设置ajax请求方法
		this.setData(this.configs.data);//设置树性节点数据
	},
	
	/**
	 * @function 创建一个节点
	 */
	create : function(parent, node, pos, callback, loaded) {
		var _this = this;
		return this._reference.create_node(parent, node, pos, callback, loaded);
	},
	
	/**
	 * @function 获取当前选中的节点
	 * @param full 是否返回节点的所有信息,默认返回id
	 */
	getChecked : function(full) {
		return this._reference.get_checked(full);
	},
	
	/**
	 * @function 获取树形控件的容器 
	 */
	getContainer : function() {
		return this._reference.get_container();
	},
	
	/**
	 * @function 能通过id直接获取节点
	 */
	getNode : function(id, asDom) {
		return this._reference.get_node(id, asDom);
	},
	
	/**
	 * @function 获取父节点
	 */
	getParent : function(node) {
		return this._reference.get_parent(node);
	},
	
	/**
	 * 获取选中的节点
	 * @param full 如果为真，则返回包含所有内容的节点，否则只返回ID，强烈建议返回ID，效果更好
	 */
	getSelected : function(full) {
		return this._reference.get_selected(full);
	},
	
	/**
	 * 新添加--获取节点全部状态
	 */
	getSelectAll : function(fn) {
		var _this = this;
		this.view.on("select_node.jstree", function (event, data) {
			if($.isFunction(fn)) {
				fn.call(_this, data.node);//
			}
		});
	},
	
	/**
	 * @function 判断当前节点是否被选中,禁止启用setCheckable(true)后生效
	 */
	isChecked : function(node) {
		return this._reference.is_checked(node);
	},
	
	/**
	 * 判断当前节点是否是子节点
	 */
	isLeaf : function(node) {
		return this._reference.is_leaf(node);
	},
	
	/**
	 * 判断当前节点是否是父节点
	 */
	isParent : function(node) {
		return this._reference.is_parent(node);
	},

	/**
	 * @function 判断是否是根节点
	 */
	isRoot : function(node) {
		return this._reference.get_parent(node) === "#";
	},

	/**
	 * @function 删除一个节点
	 * @param node 节点的id
	 *
	 */
	remove : function(node) {
		return this._reference.delete_node(node);
	},
	
	/**
	 * @function 解析树形构件 
	 */
	render : function() {
		this.view.jstree(this.defaults);
		this._reference = $.jstree.reference(this.view);
	},
	
	/**
	 * @function 刷新节点
	 */
	refresh : function(node) {
		this._reference.refresh_node(node);
	},
	
	/**
	 * @function 根据内容查找相应的节点
	 * @param　text 待查找的内容
	 */
	search : function(text) {
		this._reference.search(text);
	},
	
	/**
	 * 
	 */
	setAjax : function(url, rootId, params) {
		//根节点不能是0,否则出错
		rootId = rootId || this.ROOT_ID;
		this.defaults.core.data = {
			/**
			 * 获取url地址
			 */
			url : function(node) {
				return url;
			},
			
			/**
			 * 发送参数到服务器
			 */
			data : function (node) {
			 	var id = rootId;
				if(node.id !== "#") {
			 		id = node.id;
			 	}
			 	return {'id' :id};
			 } 
		};
		//彻底覆盖整个配置
		if($.isPlainObject(params)) {
			this.defaults.core.data.data = function(node) {
				return params;
			};
		}
	},
	
	/**
	 * @function　设置是否采用ajax
	 * @param ajax 可以是请求地址，也可以是js object对象(接受url,data两个属性，可以传递方法)
	 * url : function(node) {
		 if(node.id === "#") {
		 	return "root.json";
		 	} else {
		 		return "children.json";
		 	}
		 },
		 //提交到服务器的参数
		 'data' : function (node) {
		 	var id = 0;
			if(node.id !== "#") {
		 		id = node.id;
		 	}
		 	return {'id' :id};
		 } 
	 */
	setAjaxConfig : function(ajaxConfig) {
		if ($.isPlainObject(ajaxConfig)) {
			this.defaults.core.data = ajaxConfig;
		}
	},
	
	/**
	 *　设置树需要载入的数据，此方法与setAjax互相排斥，如果调用本方法后再调用setAjax，则本方法不起作用
	 * @function 设置树需要载入的数据
	 * @params datas JS Object数据
	 */
	setData : function(datas) {
		if($.isPlainObject(datas) || $.isArray(datas)) {
			this.defaults.core.data = datas;
		}
	},
	
	/**
	 * @function 设置是否启用Debug模式
 	 * @param {Boolean} debug
	 */
	setDebug : function(debug) {
		this.debug = !!debug;
	},
	
	/**
	 * 
	 */
	setDisabled : function(node) {
		this._reference.disable_node(node); 
	},
	
	/**
	 * @function 设置是否启用多选 
	 */
	setMultiSelect : function(multiSelect) {
		if(!!multiSelect) {
			this.defaults.checkbox = {
				"keep_selected_style" : false, //是否保持节点的选中状态
				"visible" : true, //是否显示复选框
				"whole_node" : true, //是否选中整个节点
				"three_state" : false, //是否级联选中根节点,
				tie_selection : false,
				cascade : ""//"down"选中子节点，"undetermined"：父节点处于待定状态, "up"选中祖先节点
			};
			this.defaults.plugins.push("checkbox");
		}
	},
	
	/**
	 * @function 设置是否只读，能显著提高性能
	 */
	setReadOnly : function(value) {
		this.defaults.core.check_callback = !value;
	},
	
	/**
	 * 
	 */
	setChecked : function(node) {
		this._reference.check_node(node);
	},
	
	/**
	 * 选中节点
	 * @param node 节点
	 * @param selected 是否被选中
	 */
	setSelected : function(node, selected) {
		if (selected) {
			this._reference.select_node(node, false, false);
		} else {
			this._reference.deselect_node(node, false);
		}
	},
	
	/**
	 * 设置排序
	 * @param {Object} sortName
	 * @param {Object} sortOrder
	 */
	setSort : function(sortName, sortOrder) {
		var _this = this;
		this.sortName = sortName || "id",
		this.sortOrder = sortOrder || "asc";
		this.defaults["sort"] = function(n1, n2) {
			var result = n1 - n2;
			if(_this.sortName !== "id") {
				result = _this.getNode(n1).original[_this.sortName] - _this.getNode(n2).original[_this.sortName];
			}
			result = result > -1 ? 1 : -1;
			if(_this.sortOrder.toUpperCase() === "DESC") {
				result = 0 - result;
			}
			return result;
		};
	},
	
	/**
	 * @function 根据节点的类型，来显示不同的图标
	 * @types 显示的配置　JS Object对象，如下所示
	 * "default" : {
	 "icon" : "glyphicon glyphicon-home"
	 },
	 "2" : {
	 "icon" : "glyphicon glyphicon-user"
	 }
	 */
	setTypes : function(types) {
		this.defaults.types = types;
		this.defaults.plugins.push["types"];
	},
	
	/**
	 * 此方法已废弃,请勿调用,树将自动排序
	 * @function 对节点进行排序
	 * @param 待排序的节点
	 */
	sort : function(node, deep) {
		return this._reference.sort(node, deep);
	},
	
	/**
	 * @function 对节点进行重命名
	 * @param node 节点id
	 * @param text 节点新名称
	 * @param data 节点的数据内容
	 */
	update : function(node, data) {
		this.getNode(node).original = data;
		return this._reference.rename_node(node, data.text);
		
	},
	
	/**
	 * 
	 */
	onActivate : function(fn) {
		var _this = this;
		this.view.on("activate_node.jstree", function (event, data) {
			if($.isFunction(fn)) {
				fn.call(_this, event, data.node);//
			}
		});
	},
	
	/**
	 * @function 注册节点选中事件
	 * @param fn
	 */
	onSelect : function(fn) {
		var _this = this;
		this.view.on("select_node.jstree", function (event, data) {
			if($.isFunction(fn)) {
				fn.call(_this, data.node.original);//
			}
		});
	},
	
	/**
	 * @function 绑定展开节点事件
	 */
	onOpen : function(fn) {
		this.view.on("open_node.jstree", function (event, data) {
			if($.isFunction(fn)) {
				fn.call(_this, data.node.original);//
			}
		});
	},
	
	/**
	 * @function 绑定树加载完成事件
	 */
	onReady : function(fn) {
		var _this = this;
		this.view.on("ready.jstree", function (event) {
			if($.isFunction(fn)) {
				fn.call(_this);//
			}
			_this.setSelected(_this.configs.rootId, true);//默认选中根节点
		});
	},
	
	/**
	 * 
	 */
	onCheck : function(fn) {
		var _this = this;
		this.view.on("check_node.jstree", function (node, selected, event) {
			if($.isFunction(fn)) {
				fn.call(_this, node, selected, event);//
			}
		});
	},
	
	/**
	 * 
	 */
	onUnCheck : function(fn) {
		var _this = this;
		this.view.on("uncheck_node.jstree", function (node, selected, event) {
			if($.isFunction(fn)) {
				fn.call(_this, node, selected, event);//
			}
		});
	},
	
	/**
	 * 
	 */
	onLoaded : function(fn) {
		var _this = this;
		this.view.on("loaded.jstree", function(event, data) {
			if($.isFunction(fn)) {
				fn.call(_this, event, data);
			}
		});
	}
	
});