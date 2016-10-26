
  'use strict';
var app = angular.module('app', [])
app.controller('controlador', ['$scope','$http', function($scope, $http) {
	$scope.equipo = [{
					title : "Futbol",
					fecha : "10/10/2016",
					nombre : "Luis"
				},{
					title : "Baloncesto",
					fecha : "15/10/2016",
					nombre : "pepe",
					lugar :  "  Madrid"
				}
				];
	/*$http.get('http://192.168.1.65:8080/funme/MisEventos?email='+enviarMail.email+')
	.success(function(data) {
		$scope.equipo = data;
		
	}).error(function(data) {
		$("#err").modal();
	});*/
	$scope.MisEventos = function() {
			if($scope.MisEventos.$valid){
				var enviarMail = {
						email: $scope.email

								};


			}
	}

	$scope.MisEventos = function() {
		var data = [{"title":"mi evento creado","year":"2016","month":"09","day":"26","hour":"17","minute":"0"}];
		var buscarEv = {
				interes : "Deporte",
				lugar : "Barcelona"
			};
		if($scope.buscarEvento.$valid){
			location.href="../Calendario/calendario.html?categoria="+$scope.interes+"&lugar="+$scope.lugar;
		}	
	}
	} 
]);