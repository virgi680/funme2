
  'use strict';
  angular.module('navBarDemoBasicUsage', ['ngMaterial', 'ngMessages'])
      .controller('AppCtrl', AppCtrl);

  function AppCtrl($scope) {
    $scope.currentNavItem = 'page1';
  }
;