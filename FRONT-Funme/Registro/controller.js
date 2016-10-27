/**
 * 
 */
'use strict';
var app = angular.module('app', [])
app.controller('controlador', ['$scope','$http', function($scope, $http) {
					var url = 'http://192.168.1.65:8080/funme/';
					$scope.cancelar = function() {
						location.href="../index.html";
					}
					$scope.guardarRegistro = function() {
						if($scope.password != $scope.rpassword){
							alert("Las passwords deben de coincidir");
						}
						else{
							if($scope.registro.$valid){
								var registro = {
										email : $scope.email,
										password : $scope.password,
										rpassword : $scope.rpassword,
										nombre : $scope.nombre,
										apellidos : $scope.apellidos,
										genero : $scope.genero
									};
								var mes = $scope.fecha.getMonth()+1;
								registro.fecha = $scope.fecha.getDate() + "/" + mes + "/" + $scope.fecha.getFullYear();

								$http.post(url+'guardarRegistro?email='+registro.email+'&password='+registro.password+'&nombre='+registro.nombre+'&apellidos='+registro.apellidos+'&fecha='+registro.fecha+'&genero='+registro.genero)
								.success(function(data) {
									$("#ok").modal();
								}).error(function(data) {
									$("#err").modal();
								});
							}
						}

					}
					
				} ]);



