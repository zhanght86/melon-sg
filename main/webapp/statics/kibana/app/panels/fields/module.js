/*
  ## Fields (DEPRECATED)

define([
  'angular',
  'app',
  'lodash'
],
function (angular, app, _) {
  'use strict';

  var module = angular.module('kibana.panels.fields', []);
  app.useModule(module);

  module.controller('fields', function($scope) {

    $scope.panelMeta = {
      status  : "Deprecated",
      description : "You should not use this table, it does not work anymore. The table panel now"+
        "integrates a field selector. This module will soon be removed."
    };


    // Set and populate defaults
    var _d = {
      style   : {},
      arrange : 'vertical',
      micropanel_position : 'right',
    };
    _.defaults($scope.panel,_d);

    $scope.init = function() {
      // Place holder until I remove this
    };

  });
});*/
/*
  ## Fields (DEPRECATED)
*/
define([
  'angular',
  'app',
  'lodash'
],
function (angular, app, _) {
  'use strict';

  var module = angular.module('kibana.panels.fields', []);
  app.useModule(module);

  module.controller('fields', function($scope) {

    $scope.panelMeta = {
      status  : "Deprecated",
      description : "该面板已停止工作，由于表板集成了一个字段选择器，这个模块将很快被删除"
    };

    // Set and populate defaults
    var _d = {
      style   : {},
      arrange : 'vertical',
      micropanel_position : 'right',
    };
    _.defaults($scope.panel,_d);

    $scope.init = function() {
      // Place holder until I remove this
    };

  });
});
