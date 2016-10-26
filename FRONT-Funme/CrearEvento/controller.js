/**
 * 
 */
'use strict';
var app = angular.module('app', [])
app.controller('controlador', ['$scope','$http', function($scope, $http) {
			$scope.fechaActual = new date();
			$scope.fechaActual = $scope.fechaActual.getFullYear() + "-" + ("0" + ($scope.fechaActual.getMonth()+1)).slice(-2) + "-" + ("0" + $scope.fechaActual.getDate()).slice(-2); 
			console.log($scope.fechaActual);
	
			$scope.cancelar = function() {
				location.href="../index.html";
			}
			$scope.insertEvent = function() {
				if($scope.crearEvento.$valid){
					var insertarEv = {
							lugar : $scope.lugar,
							nombre : $scope.nombre,
							interes : $scope.inte,
							descripcion : $scope.desc,
							aforo : $scope.aforo
						};
					var email;
					insertarEv.dia = ("0" + $scope.dia.getDate()).slice(-2) + "/" + ("0" + ($scope.dia.getMonth()+1)).slice(-2)+ "/" + $scope.dia.getFullYear(); 
					insertarEv.hora = $scope.hora.getHours() + ":" + $scope.hora.getMinutes();
					console.log(insertarEv.dia);
					$http.post('http://localhost:8080/funme/crearEvento?dia='+insertarEv.dia+'&hora='+insertarEv.hora+'&lugar='+insertarEv.lugar+'&nombre='+insertarEv.nombre+'&interes='+insertarEv.interes+'&descripcion='+insertarEv.descripcion+'&aforo='+insertarEv.aforo+'&email='+insertarEv.email)
					.success(function(data) {
						$("#ok").modal();
					}).error(function(data) {
						$("#err").modal();
					});
				}
			}
} ]);