define(["echarts", 'echarts/config'], function(echarts, ecConfig) {
	
	/*
	 * 通用图形
	 *
	 */
	var Chart = function(options) {
		//设置默认的配置
		this.setDefaultSeries();
	};
	
	//继承基本属性
	$.extend(Chart.prototype, {
		
		/**
		 * 获取默认的形状配置信息
		 */
		getDefaultSeries : function(type) {
			return this.defaultSeries[type];
		},
		
		/**
		 * 载入JS Chart库
		 */
		loadJs : function(libs, options) {
			var _this = this;
			if(!libs) {
				throw new Error("MUST LOAD LIBS!");
			}
			//载入库文件
			require(libs, function() {
				//初始化配置
				_this._init(options);
				//渲染配置
				_this.render(options);
			});
		},
		
		/**
		 * 
		 */
		_init : function(options) {
			//配置视图及初始化图形
			this.view = $(options.el);
			this.chart = echarts.init(this.view.get(0));
			//添加默认的配置信息
			this.setDefaults();
			this.setTheme(options);//设置主体
			this.setDataZoom(options);//设置缩放
			this.setTitle(options);//设置标题
			this.setToolbox(options);//设置工具箱
			this.setBegin(options);//设置初始扩展函数
		},
		
		/**
		 * 注册点击事件
		 */
		onClick : function(fn) {
			if($.isFunction(fn)) {
				this.chart.on(ecConfig.EVENT.CLICK, fn);
			}
		},
		
		/**
		 * 渲染图形
		 */
		render : function(options) {
			var data = options.data,
				_this = this,
				dataType = $.type(data);
			
			switch(dataType) {
				
				//ajax请求地址
				case "string" : 
				// 为echarts对象加载ajax数据
				$.getJSON(data, function(datas) {
					_this.load(datas, options);
				});
				break;
				
				//直接的数据
				case "array":
				_this.load(data, options);
				break;
				
				//回调函数
				case "function":
				_this.load(data.call(), options);
				break;
				
				//回调函数
				case "object":
				_this.load(data, options);
				break;
				
				//没有数据，进入自定义操作	
				default :
					//完成函数
					this.setReady(options);
					this.chart.setOption(this.defaults); 
				break;
			}
			
		},
		
		/**
		 * 插入值 
		 */
		insertValue : function(item, seriesConfig, valueField, categoryField, options) {
			//放入所有数据
			var value = item[valueField];
			if($.isArray(item)) {
				seriesConfig.data.push(value);
			} else {
				seriesConfig.data.push($.extend(true, {}, item, {value:value}));
			}
		},
		
		/**
		 * 载入数据 
		 */
		load : function(datas, options) {
			var legend = [], //标签数据
				axisData = [], //坐标数据
				seriesConfigs = [],//所有的图形配置
				_this = this;
			//解析数据
			$.each(datas, function(index, item) {
				
				for(var i=0; i < options.seriesConfig.length; i++) {
					//
					var config = options.seriesConfig[i],
						valueField = config["value"] || "value",//得到每项的配置
						categoryField = config["category"] || "category";
					//解析配置信息
					if(!seriesConfigs[i]) {
						var seriesType = config.type || "line",
							defaultConfig =  _this.getDefaultSeries(seriesType);
						seriesConfigs[i] = $.extend(true, {}, config,  defaultConfig);//单项的具体配置
						seriesConfigs[i].data = [];//初始化图形数据
					}
					//只灌入一次
					if(index == 0) {
						legend.push(seriesConfigs[i]["name"]);
					}
					//灌入坐标
					if(i == 0) {
						axisData.push(item[categoryField]);
					}
					_this.insertValue(item, seriesConfigs[i], valueField, categoryField, options);
				}
			});
			//设置坐标轴
			this.setAxis(axisData, options);
			this.setLegend(legend);
			this.setSeries(seriesConfigs);
			//完成函数
			this.setReady(options);
			this.chart.setOption(this.defaults); 
		},
		
		/**
		 * 
		 */
		setAxis : function(axisData, options) {
			$.extend(this.defaults, {
				//X轴
				xAxis : [{
					type : 'category',
					data : axisData,
					axisLabel : {
						rotate : 0//旋转度数
					},
					boundaryGap : true//是否留出边距
				}],
				//Y轴
				yAxis : [{
					type : 'value',
					splitArea : {
						show : true
					}
				}],
			});
			if(options.rotate) {
				var xAxis = this.defaults.xAxis;
				this.defaults.xAxis = this.defaults.yAxis ;
				this.defaults.yAxis = xAxis;
			}
		},
		
		/**
		 * 
		 */
		setBegin : function(options) {
			var begin = options.begin;
			//设置扩展信息
			if($.isFunction(begin)) {
				begin.call(this);//调用扩展配置函数
			}
		},
		
		/**
		 * 设置数据缩放配置
		 */
		setDataZoom : function(options) {
			if(options.dataZoom) {
				this.defaults.dataZoom = {
					show : true,
					realTime : false,
					start : 0,
					end : 100,
					orient : 'horizontal'//'horizontal' | 'vertical',
				};
			}
		},
		
		/**
		 * 
		 */
		setDefaults : function() {
			this.defaults = {};
		},
		
		/**
		 * 设置数据标注
		 */
		setLegend : function(legend) {
			$.extend(this.defaults, {
				legend : {
					data : legend,
					selectedMode : true,
					orient :'horizontal', //'horizontal' | 'vertical',
					y : 'top'
				}
			});
		},
		
		/**
		 * 设置图形配置
		 */
		setSeries : function(series) {
			this.defaults.series = series;
		}, 
		
		/**
		 * 设置完成函数
		 * 设置解析前的扩展配置
		 */
		setReady : function(options) {
			var ready = options.ready;
			//设置扩展信息
			if($.isFunction(ready)) {
				ready.call(this);//调用扩展配置函数
			}
		},
		
		/**
		 * 设置默认的配置信息
		 */
		setDefaultSeries : function() {
			this.defaultSeries = {
				
				//设置形状配置信息
				"line" : {
					clickable : true,
				},
				
				//设置柱形图
				"bar" : {
					
				},
				
				//块状
				"area" : {
					type : "line",
					smooth:true,
            		itemStyle: {
            			normal: {
            				areaStyle: {
            					type: 'default'
            				}
            			}
            		}
				}

			};
		},
		
		/**
		 * 设置主题
		 */
		setTheme : function(options) {
			var theme = options.theme || "blue",
				_this = this;
			require(['echarts/theme/' + theme], function(theme) {
				_this.chart.setTheme(theme);
			}); 
		},
		
		/**
		 * 设置标题
		 */
		setTitle : function(options) {
			var title = options.title;
			//设置标题
			if(!!title) {
				this.defaults.title = {
					text : title,
					textStyle : {
						fontFamily : "微软雅黑",
						fontSize : 18,
						color : "#333"
					}
				};
			}
		},
		
		/**
		 * 设置工具箱
		 */
		setToolbox : function(options) {
			this.defaults.toolbox = {
				show : true,
				feature : {
					mark : {
						show : false
					},
					dataView : {
						show : false,
						readOnly : false
					},
					magicType : {
						show : true,
						type : ['line', 'bar']
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}, 
			};
			//判断是否加上额外的工具
			if(options.seriesConfig && options.seriesConfig.length > 1) {
				this.defaults.toolbox.feature.magicType.type.push('stack');
				this.defaults.toolbox.feature.magicType.type.push('tiled');
			} 
		}
		
	});
	
	/**
	 * 
	 */
	var Column = function(options) {
		Chart.call(this, options);
		//创建Column图形
		this.loadJs(["echarts/chart/line", "echarts/chart/bar"], options);
	};
	
	/**
	 * 添加信息
	 */
	$.extend(Column.prototype, Chart.prototype, {
		
		/**
		 * 设置默认配置
		 */
		setDefaults : function() {
			this.defaults = {
				//提示信息
				tooltip : {
					trigger : 'axis',
					axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
				},
				//拖拽重计算,默认禁用
				calculable : false,
			};
		},
		
	});
	
	/**
	 * 饼状图
	 */
	var Pie = function(options) {
		Chart.call(this, options);
		//创建Column图形
		this.loadJs(["echarts/chart/pie"], options);
	};
	
	$.extend(Pie.prototype, Chart.prototype, {
		
		setAxis : $.noop,
		
		/**
		 * 插入值 
		 */
		insertValue : function(item, seriesConfig, valueField, categoryField, options) {
			//放入所有数据
			var value = item[valueField],
				name = item[categoryField] || seriesConfig["name"];
			seriesConfig.data.push({
				
				value : value,
				
				name : name
			});
		},
		
		setToolbox : function(options) {
			Chart.prototype.setToolbox.call(this, options);
			this.defaults.toolbox.feature.magicType.show = false;
		}
		
	});
	
	/**
	 * 仪表图
	 */
	var Gauge = function(options) {
		Chart.call(this, options);
		//创建 仪表图形
		this.loadJs(["echarts/chart/gauge"], options);
	};
	
	$.extend(Gauge.prototype, Pie.prototype, {
		
		/**
		 * 设置默认的配置信息
		 */
		setDefaultSeries : function() {
			this.defaultSeries = {
				"half-gauge" : {
					type:'gauge',
					startAngle: 180,
           			endAngle: 0,
           			detail : {
		                show : true,
		                backgroundColor: 'rgba(0,0,0,0)',
		                borderWidth: 0,
		                borderColor: '#ccc',
		                height: 40,
		                offsetCenter: [0, -40],       // x, y，单位px
		                textStyle: {       
		                	// 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                    fontSize : 30
		                }
		            }
				} 
			};
		}
		
	});
	
	/**
	 * 连接关系图 
	 */
	var Force = function(options) {
		Chart.call(this, options);
		//创建 仪表图形
		this.loadJs(["echarts/chart/force"], options);
	};
	
	$.extend(Force.prototype, Chart.prototype, {
		
		/**
		 * 设置默认的配置信息
		 */
		setDefaultSeries : function() {
			this.defaultSeries = {
				"force" : {
					useWorker: false,
		            minRadius : 15,
		            maxRadius : 25,
		            gravity: 1.1,
		            scaling: 1.1,
		            linkSymbol: 'arrow',
		            itemStyle: {
		                normal: {
		                    label: {
		                        show: true,
		                        textStyle: {
		                            color: '#333'
		                        }
		                    },
		                    nodeStyle : {
		                        brushType : 'both',
		                        strokeColor : 'rgba(255,215,0,0.4)',
		                        lineWidth : 1
		                    }
		                },
		                emphasis: {
		                    label: {
		                        show: false
		                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
		                    },
		                    nodeStyle : {
		                        //r: 30
		                    },
		                    linkStyle : {}
		                }
		            }
				} 
			};
		},
		
		/**
		 * 
		 */
		setLegend : function(legend) {
			Chart.prototype.setLegend.call(this, legend);
			$.extend(this.defaults.legend, {
				x : "left"
			});
		},
		
		/**
		 * 
		 */
		setTitle : function(options) {
			Chart.prototype.setTitle.call(this, options);
			$.extend(this.defaults.title, {
				 x:'right',
				 y:'bottom'
			});
		},
		
		/**
		 * 
		 */
		load : function(datas, options) {
			var legends = options.legends,
				//categories = options.categories,
				seriesConfig = options.seriesConfig[0],
				type = seriesConfig["type"] || "force";
			//添加配置信息
			$.extend(true, seriesConfig, this.getDefaultSeries(type));
			//设置链接方式
			seriesConfig.nodes = datas["nodes"];
			seriesConfig.links =  datas["links"];
			//
			this.setLegend(legends);
			this.setSeries([seriesConfig]);
			//完成函数
			this.setReady(options);
			this.chart.setOption(this.defaults); 
		}
		
	});
	
	/**
	 * 漏斗图
	 */
	var Funnel = function(options) {
		Chart.call(this, options);
		//创建Column图形
		this.loadJs(["echarts/chart/funnel"], options);
	};
	
	/*
	 * 
	 */
	$.extend(Funnel.prototype, Chart.prototype,{
		
		/**
		 * 插入标签 
		 */
		insertLegend : function(index, legend, seriesConfigs, i, item) {
			//只灌入一次
			legend.push(item["name"]);
		},
		
		setAxis : $.noop
		
	});
	

	/**
	 * 混搭 
	 */
	var Complex = function(options) {
		Chart.call(this, options);
		//创建Column图形
		this.loadJs(["echarts/chart/line", "echarts/chart/bar", "echarts/chart/pie"], options);
	};
	
	$.extend(Complex.prototype, Chart.prototype, {
		
		/**
		 * 载入数据 
		 */
		load : function(datas, options) {
			var legend = [], //标签数据
				axisData = [], //坐标数据
				seriesConfigs = [],//所有的图形配置
				_this = this;
				
			//解析数据
			$.each(datas, function(index, item) {
				
				for(var i=0; i < options.seriesConfig.length; i++) {
					//
					var config = options.seriesConfig[i],
						valueField = config["value"] || "value",//得到每项的配置
						seriesType = config.type || "line",
						defaultConfig =  _this.getDefaultSeries(seriesType),
						categoryField = config["category"] || "category";
					//解析配置信息
					if(!seriesConfigs[i]) {
						seriesConfigs[i] = $.extend(true, {}, config,  defaultConfig);//单项的具体配置
						seriesConfigs[i].data = [];//初始化图形数据
					}
					_this.insertLegend(index, legend, seriesConfigs, i, item);
					//灌入坐标
					if(i == 0) {
						axisData.push(item[categoryField]);
					}
					switch(seriesType) {
						case "pie" :
						_this.insertPieValue(item, seriesConfigs[i], valueField, categoryField, options);
						break;
						
						default:
						_this.insertValue(item, seriesConfigs[i], valueField, categoryField, options);
						break;
					}
				}
			});
			//设置坐标轴
			this.setAxis(axisData, options);
			this.setLegend(legend);
			this.setSeries(seriesConfigs);
			//完成函数
			this.setReady(options);
			this.chart.setOption(this.defaults); 
		},
		
		/**
		 * 插入值 
		 */
		insertPieValue : function(item, seriesConfig, valueField, categoryField, options) {
			//放入所有数据
			var value = item[valueField],
				name = item[categoryField] || seriesConfig["name"];
			seriesConfig.data.push({
				
				value : value,
				
				name : name
			});
		},
	});
	
	
	
	/**
	 * 
	 */
	var Map = function(options) {
		Chart.call(this, options);
		//创建Column图形
		this.loadJs(["echarts/chart/map"], options);
	};
	
	/*
	 * 
	 */
	$.extend(Map.prototype, Chart.prototype, {
		
		setAxis : $.noop
		
	});
	/**
	 * 
	 */
	var Scatter = function(options) {
		Chart.call(this, options);
		//创建Column图形
		this.loadJs(["echarts/chart/scatter"], options);
	};
	
	$.extend(Scatter.prototype, Chart.prototype, {
		
		setAxis : function(axisData, options) {
			$.extend(this.defaults, {
				xAxis : [{
					//type : 'value',
		            power: 1,
		            //precision: 2,
		            scale:true
				}],
				
				yAxis : [{
					type : 'value',
		            power: 1,
		            //precision: 2,
		            scale:true
				}]
			})
		},
		
		insertValue : function(item, seriesConfig, valueField, categoryField, options) {
			//放入所有数据
			//var value = item[valueField];
			seriesConfig.data = item;
		}
	});
		
	/**/
	return {
		Column : Column,
		
		Complex : Complex,
		
		Gauge : Gauge,
		
		Force : Force,
		
		Funnel : Funnel,
		
		Map : Map,
		
		Pie : Pie,
		
		Scatter  : Scatter,
		
		Echarts : echarts
	};
	
});
