
  'use strict';
var app = angular.module('app', [])
app.controller('controlador', ['$scope','$http', function($scope, $http) {
	var url = 'http://192.168.1.65:8080/funme/';
	$scope.user = getParameterByName('email');
	$scope.equipo = [];
	$http.get(url+'misEventos?email='+$scope.user)
	.success(function(data) {
		$scope.equipo = data;
	}).error(function(data) {
		$("#err").modal();
	});
	function getParameterByName(name) {
		   name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		   var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		   results = regex.exec(location.search);
		   return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	$scope.buscarEvento = function(){
    	location.href="../BuscarEvento/buscarEvento.html?email="+$scope.user;
    }
	$scope.misEventos = function(){
    	location.href="MisEventos.html?email="+$scope.user;
    }
}]);