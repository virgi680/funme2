
'use strict';
var app = angular.module('app', [])
app.controller('controlador', ['$scope','$http', function($scope, $http) {
	var url = 'http://192.168.1.65:8080/funme/';
	
	$scope.login = function() {
		if($scope.login2.$valid){
			$http.get(url+'login?email='+$scope.email+'&password='+$scope.password)
			.success(function(data) {
				location.href="BuscarEvento/buscarEvento.html?email="+$scope.email;
			}).error(function(data) {
				$("#err").modal();
			});
		}
	}
}  ]);