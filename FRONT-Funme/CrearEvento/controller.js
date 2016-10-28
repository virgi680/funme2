/**
 * 
 */
'use strict';
var app = angular.module('app', [])
app.controller('controlador', ['$scope','$http', function($scope, $http) {
			var url = 'http://192.168.1.65:8080/funme/';
			$scope.inter = getParameterByName('categoria');
			$scope.lug = getParameterByName('lugar');
			var user = getParameterByName('email');
			$scope.fechaActual = new Date();
			$scope.fechaActual = $scope.fechaActual.getFullYear() + "-" + ("0" + ($scope.fechaActual.getMonth()+1)).slice(-2) + "-" + ("0" + $scope.fechaActual.getDate()).slice(-2); 
			console.log($scope.fechaActual);
			function getParameterByName(name) {
				   name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
				   var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
				   results = regex.exec(location.search);
				   return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
			}
			$scope.cancelar = function() {
				location.href="../Calendario/calendario.html?categoria="+$scope.inter+"&lugar="+$scope.lug+"&email="+user;
			}
			$scope.insertEvent = function() {
				if($scope.crearEvento.$valid){
					var insertarEv = {
							lugar : $scope.lugar,
							nombre : $scope.nombre,
							interes : $scope.interes,
							descripcion : $scope.desc,
							aforo : $scope.aforo
						};
					console.log($scope.dia);
					insertarEv.dia = ("0" + $scope.dia.getDate()).slice(-2) + "/" + ("0" + ($scope.dia.getMonth()+1)).slice(-2)+ "/" + $scope.dia.getFullYear(); 
					insertarEv.hora = $scope.hora.getHours() + ":" + $scope.hora.getMinutes();
					console.log(insertarEv.dia);
					$http.post(url+'crearEvento?dia='+insertarEv.dia+'&hora='+insertarEv.hora+'&lugar='+$scope.lugar+'&nombre='+insertarEv.nombre+'&interes='+$scope.interes+'&descripcion='+insertarEv.descripcion+'&aforo='+insertarEv.aforo+'&email='+user)
					.success(function(data) {
						$("#ok").modal();
					}).error(function(data) {
						$("#err").modal();
					});
				}
			}
			$scope.buscarEvento = function(){
		    	location.href="../BuscarEvento/buscarEvento.html?email="+user;
		    }
			$scope.misEventos = function(){
		    	location.href="../MisEventos/MisEventos.html?email="+user;
		    }
} ]);