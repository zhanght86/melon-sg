define([
  'angular',
  'jquery',
  'kbn',
  'lodash',
  'config',
  'moment',
  'modernizr',
  'filesaver',
  'blob'
],
function (angular, $, kbn, _, config, moment, Modernizr) {
  'use strict';

  var module = angular.module('kibana.services');

  module.service('dashboard', function(
    $routeParams, $http, $rootScope, $injector, $location, $timeout,
    ejsResource, timer, kbnIndex, alertSrv, esVersion, esMinVersion
  ) {
    // A hash of defaults to use when loading a dashboard

    var _dash = {
      title: "",
      style: "dark",
      editable: true,
      failover: false,
      panel_hints: true,
      rows: [],
      pulldowns: [
        {
          type: 'query',
        },
        {
          type: 'filtering'
        }
      ],
      nav: [
        {
          type: 'timepicker'
        }
      ],
      services: {},
      loader: {
        save_gist: false,
        save_elasticsearch: true,
        save_local: true,
        save_default: true,
        save_temp: true,
        save_temp_ttl_enable: true,
        save_temp_ttl: '30d',
        load_gist: false,
        load_elasticsearch: true,
        load_elasticsearch_size: 20,
        load_local: false,
        hide: false
      },
      index: {
        interval: 'none',
        pattern: '_all',
        default: 'INDEX_MISSING',
        warm_fields: true
      },
      refresh: false
    };

    // An elasticJS client to use
    var ejs = ejsResource(config.elasticsearch);

    var gist_pattern = /(^\d{5,}$)|(^[a-z0-9]{10,}$)|(gist.github.com(\/*.*)\/[a-z0-9]{5,}\/*$)/;

    // Store a reference to this
    var self = this;
    var filterSrv,querySrv;

    this.current = _.clone(_dash);
    this.last = {};
    this.availablePanels = [];

    $rootScope.$on('$routeChangeSuccess', function () {
      // Clear the current dashboard to prevent reloading
      if ($location.path() === '/connectionFailed') { return; }
      self.current = {};
      self.indices = [];
      esVersion.isMinimum().then(function(isMinimum) {
        if(_.isUndefined(isMinimum)) {
          return;
        }
        if(isMinimum) {
          route();
        } else {
          alertSrv.set('升级要求',"你的elasticsearch版本太旧. Kibana 需要" +
            " Elasticsearch " + esMinVersion + " 或者以上版本.", "失败");
        }
      });
    });

    var route = function() {
      // Is there a dashboard type and id in the URL?
      if(!(_.isUndefined($routeParams.kbnType)) && !(_.isUndefined($routeParams.kbnId))) {
        var _type = $routeParams.kbnType;
        var _id = $routeParams.kbnId;
        //
        switch(_type) {
        case ('elasticsearch'):
          self.elasticsearch_load('dashboard',_id);
          break;
        case ('temp'):
          self.elasticsearch_load('temp',_id);
          break;
        case ('file'):
          self.file_load(_id);
          break;
        case('script'):
          self.script_load(_id);
          break;
        case('local'):
          self.local_load();
          break;
        default:
          $location.path(config.default_route);
        }
      // No dashboard in the URL
      } else {
        // Check if browser supports localstorage, and if there's an old dashboard. If there is,
        // inform the user that they should save their dashboard to Elasticsearch and then set that
        // as their default
        if (Modernizr.localstorage) {
          if(!(_.isUndefined(window.localStorage['dashboard'])) && window.localStorage['dashboard'] !== '') {
            $location.path(config.default_route);
            alertSrv.set('已替换并保存到浏览器',' 用来储蓄elasticsearch.'+
              ' 单击 <a href="#/dashboard/local/deprecated">这里</a> 将加载你原来旧的面板.');
          } else if(!(_.isUndefined(window.localStorage.kibanaDashboardDefault))) {
            $location.path(window.localStorage.kibanaDashboardDefault);
          } else {
            $location.path(config.default_route);
          }
        // No? Ok, grab the default route, its all we have now
        } else {
          $location.path(config.default_route);
        }
      }
    };

    // Since the dashboard is responsible for index computation, we can compute and assign the indices
    // here before telling the panels to refresh
    this.refresh = function() {
      if(self.current.index.interval !== 'none') {
        if(_.isUndefined(filterSrv)) {
          return;
        }
        if(filterSrv.idsByType('time').length > 0) {
          var _range = filterSrv.timeRange('last');
          kbnIndex.indices(_range.from,_range.to,
            self.current.index.pattern,self.current.index.interval
          ).then(function (p) {
            if(p.length > 0) {
              self.indices = p;
            } else {
              // Option to not failover
              if(self.current.failover) {
                self.indices = [self.current.index.default];
              } else {
                // Do not issue refresh if no indices match. This should be removed when panels
                // properly understand when no indices are present
                alertSrv.set('没有结果','没有结果，因为没有发现符合您选择的时间跨度数据'
                  ,'信息',5000);
                return false;
              }
            }
            // Don't resolve queries until indices are updated
            querySrv.resolve().then(function(){$rootScope.$broadcast('refresh');});
          });
        } else {
          if(self.current.failover) {
            self.indices = [self.current.index.default];
            querySrv.resolve().then(function(){$rootScope.$broadcast('refresh');});
          } else {
            alertSrv.set("没有时间过滤器",
              '时间戳的指标没有设置跨度. 等待加载时间滤波器.',
              'info',5000);
          }
        }
      } else {
        self.indices = [self.current.index.default];
        querySrv.resolve().then(function(){$rootScope.$broadcast('refresh');});
      }
    };

    var dash_defaults = function(dashboard) {
      _.defaults(dashboard,_dash);
      _.defaults(dashboard.index,_dash.index);
      _.defaults(dashboard.loader,_dash.loader);
      return _.cloneDeep(dashboard);
    };

    this.dash_load = function(dashboard) {
      //By Gan
      //
      if(!dashboard) {
      	return false;
      }
      // Cancel all timers
      timer.cancel_all();

      // Make sure the dashboard being loaded has everything required
      dashboard = dash_defaults(dashboard);

      // If not using time based indices, use the default index
      if(dashboard.index.interval === 'none') {
        self.indices = [dashboard.index.default];
      }

      // Set the current dashboard
      self.current = _.clone(dashboard);

      // Delay this until we're sure that querySrv and filterSrv are ready
      $timeout(function() {
        // Ok, now that we've setup the current dashboard, we can inject our services
        if(!_.isUndefined(self.current.services.query)) {
          querySrv = $injector.get('querySrv');
          querySrv.init();
        }
        if(!_.isUndefined(self.current.services.filter)) {
          filterSrv = $injector.get('filterSrv');
          filterSrv.init();
        }
      },0).then(function() {
        // Call refresh to calculate the indices and notify the panels that we're ready to roll
        self.refresh();
      });

      if(dashboard.refresh) {
        self.set_interval(dashboard.refresh);
      }

      // Set the available panels for the "Add Panel" drop down
      self.availablePanels = _.difference(config.panel_names,
        _.pluck(_.union(self.current.nav,self.current.pulldowns),'type'));

      // Take out any that we're not allowed to add from the gui.
      self.availablePanels = _.difference(self.availablePanels,config.hidden_panels);

      return true;
    };

    this.gist_id = function(string) {
      if(self.is_gist(string)) {
        return string.match(gist_pattern)[0].replace(/.*\//, '');
      }
    };

    this.is_gist = function(string) {
      if(!_.isUndefined(string) && string !== '' && !_.isNull(string.match(gist_pattern))) {
        return string.match(gist_pattern).length > 0 ? true : false;
      } else {
        return false;
      }
    };

    this.to_file = function() {
      var blob = new Blob([angular.toJson(self.current,true)], {type: "application/json;charset=utf-8"});
      // from filesaver.js
      window.saveAs(blob, self.current.title+"-"+new Date().getTime());
      return true;
    };

    this.set_default = function(route) {
      if (Modernizr.localstorage) {
        // Purge any old dashboards
        if(!_.isUndefined(window.localStorage['dashboard'])) {
          delete window.localStorage['dashboard'];
        }
        window.localStorage.kibanaDashboardDefault = route;
        return true;
      } else {
        return false;
      }
    };

    this.purge_default = function() {
      if (Modernizr.localstorage) {
        // Purge any old dashboards
        if(!_.isUndefined(window.localStorage['dashboard'])) {

          delete window.localStorage['dashboard'];
        }
        delete window.localStorage.kibanaDashboardDefault;
        return true;
      } else {
        return false;
      }
    };

    // TOFIX: Pretty sure this breaks when you're on a saved dashboard already
    this.share_link = function(title,type,id) {
      return {
        location  : window.location.href.substr(0, window.location.href.indexOf('#')),
        type      : type,
        id        : id,
        link      : window.location.href.substr(0, window.location.href.indexOf('#'))+"#dashboard/"+type+"/"+encodeURIComponent(id),
        title     : title
      };
    };

    var renderTemplate = function(json,params) {
      var _r;
      _.templateSettings = {interpolate : /\{\{(.+?)\}\}/g};
      var template = _.template(json);
      var rendered = template({ARGS:params});
      try {
        _r = angular.fromJson(rendered);
      } catch(e) {
        _r = false;
      }
      return _r;
    };

    this.local_load = function() {
      var dashboard = JSON.parse(window.localStorage['dashboard']);
      dashboard.rows.unshift({
        height: "30",
        title: "Deprecation Notice",
        panels: [
          {
            title: 'WARNING: Legacy dashboard',
            type: 'text',
            span: 12,
            mode: 'html',
            content: '该仪表已从本地浏览器缓存中加载.'+
            ' 如果你使用另一个浏览器或计算机将无法访问它! '+
            '\n\n  <h4>好消息!</h4> '+
            ' Kibana 现在可以储存在Elasticsearch的面板中. 点击这个 <i class="icon-save"></i> '+
            '左上方的按钮来保存该仪表板. 然后选择 "设置主页" 从'+
            '  子菜单的"高级选项"中自动使用存储的面板作为kibana 登录后的页面'+
            '<br><br><strong>提示:</strong> 你可以在保存前删除该行!'
          }
        ]
      });
      self.dash_load(dashboard);
    };

    this.file_load = function(file) {
   		//By Gan 	
   		//添加路径转向
   		var url = "app/dashboards/"+file.replace(/\.(?!json)/,"/")+'?' + new Date().getTime();
   		url = Kibana.baseUrl + url;
      return $http({
        url: url,
        method: "GET",
        transformResponse: function(response) {
          return renderTemplate(response,$routeParams);
        }
      }).then(function(result) {
        if(!result) {
          return false;
        }
        self.dash_load(dash_defaults(result.data));
        return true;
      },function() {
        alertSrv.set('错误',"没有加载 <i>dashboards/"+file+"</i>. 请确认它是否存在" ,'错误');
        return false;
      });
    };

    this.elasticsearch_load = function(type,id) {
      var successcb = function(data) {
        var response = renderTemplate(angular.fromJson(data)._source.dashboard, $routeParams);
        self.dash_load(response);
      };
      var errorcb = function(data, status) {
        if(status === 0) {
          alertSrv.set('错误',"无法联系elasticsearch,在 "+ejs.config.server+
            ".请确保你的系统可用elasticsearch." ,'错误');
        } else {
          alertSrv.set('错误',"没有发现 "+id+". 如果你使用了代理，请确保它的配置正确",'失败');
        }
        return false;
      };

      ejs.client.get(
        "/" + config.kibana_index + "/"+type+"/"+id+'?' + new Date().getTime(),
        null, successcb, errorcb);

    };

    this.script_load = function(file) {
      return $http({
        url: "app/dashboards/"+file.replace(/\.(?!js)/,"/"),
        method: "GET",
        transformResponse: function(response) {
          /*jshint -W054 */
          var _f = new Function('ARGS','kbn','_','moment','window','document','angular','require','define','$','jQuery',response);
          return _f($routeParams,kbn,_,moment);
        }
      }).then(function(result) {
        if(!result) {
          return false;
        }
        self.dash_load(dash_defaults(result.data));
        return true;
      },function() {
        alertSrv.set('错误',
          "没有加载 <i>scripts/"+file+"</i>. 请确认是否存在，并返回一个有效的面板" ,
          '失败');
        return false;
      });
    };

    this.elasticsearch_save = function(type,title,ttl) {
      // Clone object so we can modify it without influencing the existing obejct
      var save = _.clone(self.current);
      var id;

      // Change title on object clone
      if (type === 'dashboard') {
        id = save.title = _.isUndefined(title) ? self.current.title : title;
      }

      // Create request with id as title. Rethink this.
      var request = ejs.Document(config.kibana_index,type,id).source({
        user: 'guest',
        group: 'guest',
        title: save.title,
        dashboard: angular.toJson(save)
      });

      request = type === 'temp' && ttl ? request.ttl(ttl) : request;

      return request.doIndex(
        // Success
        function(result) {
          if(type === 'dashboard') {
            $location.path('/dashboard/elasticsearch/'+title);
          }
          return result;
        },
        // Failure
        function() {
          return false;
        }
      );
    };

    this.elasticsearch_delete = function(id) {
      return ejs.Document(config.kibana_index,'dashboard',id).doDelete(
        // Success
        function(result) {
          return result;
        },
        // Failure
        function() {
          return false;
        }
      );
    };

    this.elasticsearch_list = function(query,count) {
      var request = ejs.Request().indices(config.kibana_index).types('dashboard');
      return request.query(
        ejs.QueryStringQuery(query || '*')
        ).size(count).doSearch(
          // Success
          function(result) {
            return result;
          },
          // Failure
          function() {
            return false;
          }
        );
    };

    this.save_gist = function(title,dashboard) {
      var save = _.clone(dashboard || self.current);
      save.title = title || self.current.title;
      return $http({
        url: "https://api.github.com/gists",
        method: "POST",
        data: {
          "description": save.title,
          "public": false,
          "files": {
            "kibana-dashboard.json": {
              "content": angular.toJson(save,true)
            }
          }
        }
      }).then(function(data) {
        return data.data.html_url;
      }, function() {
        return false;
      });
    };

    this.gist_list = function(id) {
      return $http.jsonp("https://api.github.com/gists/"+id+"?callback=JSON_CALLBACK"
      ).then(function(response) {
        var files = [];
        _.each(response.data.data.files,function(v) {
          try {
            var file = JSON.parse(v.content);
            files.push(file);
          } catch(e) {
            return false;
          }
        });
        return files;
      }, function() {
        return false;
      });
    };

    this.start_scheduled_refresh = function (after_ms) {
      timer.cancel(self.refresh_timer);
      self.refresh_timer = timer.register($timeout(function () {
        self.start_scheduled_refresh(after_ms);
        self.refresh();
      }, after_ms));
    };

    this.cancel_scheduled_refresh = function () {
      timer.cancel(self.refresh_timer);
    };

    this.set_interval = function (interval) {
      self.current.refresh = interval;
      if (interval) {
        var _i = kbn.interval_to_ms(interval);
        this.start_scheduled_refresh(_i);
      } else {
        this.cancel_scheduled_refresh();
      }
    };


  });

});
