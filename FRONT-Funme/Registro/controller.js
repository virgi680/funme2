/**
 * 
 */
'use strict';
var app = angular.module('app', [])
app.controller('controlador', ['$scope','$http', function($scope, $http) {
					$scope.cancelar = function() {
						open('login.html');
					}
					$scope.close = function () {
						window.close();
					};
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
								var posicion_x; 
								var posicion_y; 
								posicion_x=(screen.width/2)-(400/2); 
								posicion_y=(screen.height/2)-(300/2); 

								$http.post('http://localhost:8080/funme/guardarRegistro?email='+registro.email+'&password='+registro.password+'&nombre='+registro.nombre+'&apellidos='+registro.apellidos+'&fecha='+registro.fecha+'&genero='+registro.genero)
								.success(function(data) {
									window.open('popup-exito.html', data.target, 'width=400,height=300,left='+posicion_x+',top='+posicion_y+'')
								}).error(function(data) {
									window.open('popup-error.html', data.target, 'width=400,height=300,left='+posicion_x+',top='+posicion_y+'')
								});
							}
						}

					}
					
				} ]);



