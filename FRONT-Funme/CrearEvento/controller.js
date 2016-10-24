/**
 * 
 */
'use strict';
var app = angular.module('app', [])
app.controller('controlador', ['$scope','$http', function($scope, $http) {
					$scope.cancelar = function() {
						open('Calendario.html');
					}
					$scope.close = function () {
						window.close();
					};
					$scope.insertEvent = function() {
						//alert(insertarEv.hora);							
						if($scope.crearEvento.$valid){
							var insertarEv = {
									lugar : $scope.lugar,
									nombre : $scope.nombre
								};
							var mes = $scope.dia.getMonth()+1;
							insertarEv.dia = $scope.dia.getDate() + "/" + mes + "/" + $scope.dia.getFullYear(); 
							insertarEv.hora = $scope.hora.getHours() + ":" + $scope.hora.getMinutes();
							var posicion_x; 
							var posicion_y; 
							posicion_x=(screen.width/2)-(400/2); 
							posicion_y=(screen.height/2)-(180/2);

							$http.post('http://localhost:8080/funme/crearEvento?dia='+insertarEv.dia+'&hora='+insertarEv.hora+'&lugar='+insertarEv.lugar+'&nombre='+insertarEv.nombre)
							.success(function(data) {
								window.open('popup-exito.html', this.target, 'width=400,height=343,left='+posicion_x+',top='+posicion_y+'')
							}).error(function(data) {
								window.open('popup-exito.html', this.target, 'width=400,height=343,left='+posicion_x+',top='+posicion_y+'')
							});
						}
					}
				} ]);