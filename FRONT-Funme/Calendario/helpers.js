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
	        	  console.log("UNIRSE");
	        	  $http.post(url+'unirseAEvento?email='+vm.event.emailLogin+'&descripcion='+vm.event.descripcion+'&categoria='+vm.event.categoria+'&hora='+vm.event.hora+'&dia='+vm.event.dia+'&aforo='+vm.event.aforo+'&nombre='+vm.event.nombre+'&lugar='+vm.event.lugar)
					.success(function(data) {
						console.log("TE HAS UNIDO");
						$("#ok").modal();
					}).error(function(data) {
						console.log("NO TE HAS UNIDO");
						//$("#err").modal();
					});
	          }
	          $scope.ok = function(){
	        	  location.href="../MisEventos/MisEventos.html?email="+vm.event.emailLogin;
	          }
        },
        controllerAs: 'vm'
      });
    }

    return {
      show: show
    };

  });
