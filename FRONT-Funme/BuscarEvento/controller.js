
  'use strict';
var app = angular.module('app', [])
app.controller('controlador', ['$scope','$http', function($scope, $http) {
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
	    });
	});
	$scope.buscarEvent = function() {
			var buscarEv = {
					interes : $scope.interes,
					lugar : $scope.lugar
				};

			//alert(lugar);
			$http.post('http://localhost:8080/funme/buscarEventos?interes='+buscarEv.interes+'&lugar='+buscarEv.lugar)
			.success(function(data) {
				location.href="../eventos.html";
			}).error(function(data) {
				$("#err").modal();
			});

	}
	} ]);