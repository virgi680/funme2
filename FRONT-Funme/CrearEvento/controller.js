/**
 * 
 */
'use strict';
var app = angular.module('app', [])
app.controller('controlador', ['$scope','$http', function($scope, $http) {
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
							var mes = $scope.dia.getMonth()+1;
							insertarEv.dia = $scope.dia.getDate() + "/" + mes + "/" + $scope.dia.getFullYear(); 
							insertarEv.hora = $scope.hora.getHours() + ":" + $scope.hora.getMinutes();
							
							$http.post('http://localhost:8080/funme/crearEvento?dia='+insertarEv.dia+'&hora='+insertarEv.hora+'&lugar='+insertarEv.lugar+'&nombre='+insertarEv.nombre+'&interes='+insertarEv.interes+'&descripcion='+insertarEv.descripcion+'&aforo='+insertarEv.aforo+'&email='+insertarEv.email)
							.success(function(data) {
								$("#ok").modal();
							}).error(function(data) {
								$("#err").modal();
							});
						}
					}
				} ]);