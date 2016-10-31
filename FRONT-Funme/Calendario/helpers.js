angular
  .module('mwl.calendar.docs')
  .factory('alert', function($uibModal) {

    function show(action, event) {
      return $uibModal.open({
        templateUrl: 'modalContent.html',
        controller: function($scope, $http) {
        	 var url = 'http://192.168.1.65:8080/funme/';
	          var vm = this;
	          vm.action = action;
	          vm.event = event;
	          $scope.unirse = function(){
	        	  $http.post(url+'unirseAEvento?email='+vm.event.emailLogin+'&descripcion='+vm.event.descripcion+'&categoria='+vm.event.categoria+'&hora='+vm.event.hora+'&dia='+vm.event.dia+'&aforo='+vm.event.aforo+'&nombre='+vm.event.nombre+'&lugar='+vm.event.lugar)
					.success(function(data) {
						console.log(data);
						location.href="../MisEventos/MisEventos.html?email="+vm.event.emailLogin;
						//$("#ok").modal();
					}).error(function(data) {
						console.log("NO TE HAS UNIDO");
						//$("#err").modal();
					});
	          }
	          $scope.eliminarEv = function(){
	        	//MODAL PARA PRGEUNTAR SI ES SEGURO QUE LO QUIERE ELIMINAR
	        	  $http.post(url+'borrarEvento?email='+vm.event.emailLogin+'&hora='+vm.event.hora+'&dia='+vm.event.dia+'&nombre='+vm.event.nombre+'&lugar='+vm.event.lugar)
					.success(function(data) {
						location.href="../MisEventos/MisEventos.html?email="+vm.event.emailLogin;
						//$("#ok").modal();
					}).error(function(data) {
						console.log("NO TE HAS UNIDO");
						//$("#err").modal();
					});
	          }
	          $scope.desunirse = function(){
	        	//MODAL PARA PRGEUNTAR SI ES SEGURO QUE LO QUIERE ELIMINAR
	        	  $http.post(url+'desunirseEvento?hora='+vm.event.hora+'&dia='+vm.event.dia+'&nombre='+vm.event.nombre+'&lugar='+vm.event.lugar+'&email='+vm.event.emailLogin)
					.success(function(data) {
						location.href="calendario.html?email="+vm.event.emailLogin+'&categoria='+vm.event.categoria+'&lugar='+vm.event.lugar;
						//$("#ok").modal();
					}).error(function(data) {
						console.log("NO TE HAS UNIDO");
						//$("#err").modal();
					});
	          }
        },
        controllerAs: 'vm'
      });
    }

    return {
      show: show
    };

  });
