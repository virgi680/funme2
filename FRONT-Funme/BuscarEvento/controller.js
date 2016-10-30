
  'use strict';
var app = angular.module('app', [])
app.controller('controlador', ['$scope','$http', function($scope, $http) {
	var user = getParameterByName('email');
	
	$(document).ready(function(){
		
	    // Defining the local dataset
	    var provincias = ['Alava','Albacete','Alicante','Almería','Asturias','Avila','Badajoz','Barcelona','Burgos','Cáceres',
	    	'Cádiz','Cantabria','Castellón','Ciudad Real','Córdoba','La Coruña','Cuenca','Gerona','Granada','Guadalajara',
	    	'Guipúzcoa','Huelva','Huesca','Islas Baleares','Jaén','León','Lérida','Lugo','Madrid','Málaga','Murcia','Navarra',
	    	'Orense','Palencia','Las Palmas','Pontevedra','La Rioja','Salamanca','Segovia','Sevilla','Soria','Tarragona',
	    	'Santa Cruz de Tenerife','Teruel','Toledo','Valencia','Valladolid','Vizcaya','Zamora','Zaragoza'];
	    
	    // Constructing the suggestion engine
	    var provincias = new Bloodhound({
	        datumTokenizer: Bloodhound.tokenizers.whitespace,
	        queryTokenizer: Bloodhound.tokenizers.whitespace,
	        local: provincias
	    });
	    
	    // Initializing the typeahead
	    $('.typeahead').typeahead({
	        hint: true,
	        highlight: true, /* Enable substring highlighting */
	        minLength: 1 /* Specify minimum characters required for showing result */
	    },
	    {
	        name: 'provincias',
	        source: provincias
	    }).on('typeahead:selected', function(event, selection) {
	    
			  // the second argument has the info you want
			  $scope.lugar = selection;
			  // clearing the selection requires a typeahead method
			  $(this).typeahead('close');
			
			
			});
	});
	function getParameterByName(name) {
		   name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		   var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		   results = regex.exec(location.search);
		   return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	$scope.buscarEvent = function() {
		var data = [{"title":"mi evento creado","year":"2016","month":"09","day":"26","hour":"17","minute":"0"}];
		var buscarEv = {
				interes : "Deporte",
				lugar : "Barcelona"
			};
		if($scope.buscarEvento.$valid){
			location.href="../Calendario/calendario.html?categoria="+$scope.interes+"&lugar="+$scope.lugar+"&email="+user;
		}	
	}
	$scope.misEventos = function(){
    	location.href="../MisEventos/MisEventos.html?email="+user;
    }
}]);