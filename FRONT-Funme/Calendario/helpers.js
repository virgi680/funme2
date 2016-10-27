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
		      		/*$http.get(url+'unirseAEvento?nombre='+vm.event.nombre+'&lugar='+vm.event.lugar+'&email='+vm.event.emailLogin)
		      		.success(function(data) {
		      			console.log(TE HAS UNIDO);
		      			//data.emailLogin = email;
		      			//alert.show('Edited', data);
		      		}).error(function(data) {
		      			console.log(NO TE HAS UNIDO);
		      			//$("#err").modal();
		      		});*/
	          }
        },
        controllerAs: 'vm'
      });
    }

    return {
      show: show
    };

  });
