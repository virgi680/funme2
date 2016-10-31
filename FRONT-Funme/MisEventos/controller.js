
  'use strict';
var app = angular.module('app', [])
app.controller('controlador', ['$scope','$http', function($scope, $http) {
	var url = 'http://192.168.1.65:8080/funme/';
	$scope.user = getParameterByName('email');
	$scope.eventos = [];
	$http.get(url+'misEventos?email='+$scope.user)
	.success(function(data) {
		$scope.eventosTodos = data;
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
	$scope.eliminarEv = function(evento){
  	  $http.post(url+'borrarEvento?email='+$scope.user+'&hora='+evento.hora+'&dia='+evento.dia+'&nombre='+evento.nombre+'&lugar='+evento.lugar)
			.success(function(data) {
				location.href="../MisEventos/MisEventos.html?email="+$scope.user;
				//$("#ok").modal();
			}).error(function(data) {
				console.log("NO TE HAS UNIDO");
				//$("#err").modal();
			});
    }
	$scope.events = function(evento){
		$scope.eventos = [];
	  	  if(evento == "creados"){
	  		  for(var i in $scope.eventosTodos){
	  			  if($scope.eventosTodos[i].creador == $scope.user){
	  				$scope.eventos.push($scope.eventosTodos[i]);
	  			  }
	  		  }
	  	  }
	  	  else{
	  		 if(evento == "unidos"){
	  			for(var i in $scope.eventosTodos){
		  			  if($scope.eventosTodos[i].creador != $scope.user){
		  				$scope.eventos.push($scope.eventosTodos[i]);
		  			  }
		  		  }
	  		 }
	  		 else{
	  			$scope.eventos = $scope.eventosTodos;
	  		 }
	  		
	  	  }
	}
}]);