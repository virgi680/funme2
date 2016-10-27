angular.module('mwl.calendar.docs', ['mwl.calendar', 'ngAnimate', 'ui.bootstrap', 'colorpicker.module']);
angular
  .module('mwl.calendar.docs') //you will need to declare your module with the dependencies ['mwl.calendar', 'ui.bootstrap', 'ngAnimate']
  .controller('KitchenSinkCtrl', function(moment, alert, calendarConfig, $http, $scope) {
	  var url = 'http://192.168.1.65:8080/funme/';
		var inter = getParameterByName('categoria');
		var lug = getParameterByName('lugar');
		$scope.user = getParameterByName('email');
			$http.get(url+'buscarEventos?categoria='+inter+'&lugar='+lug+'&email='+$scope.user)
			.success(function(data) {
				vm.events = [];
				for(var i in data){
					var event = {
							title : data[i].title,
							startsAt : new Date(data[i].year, (data[i].month-1), data[i].day, data[i].hour, data[i].minute),
							actions : actions
					};
					vm.events.push(event);
				}
			}).error(function(data) {
				$("#err").modal();
			});
    var vm = this;

    //These variables MUST be set as a minimum for the calendar to work
    vm.calendarView = 'month';
    vm.viewDate = new Date();
    var actions = [{
      label: '<i class=\'glyphicon glyphicon-search\'></i>',
      onClick: function(args) {
    	  $http.get(url+'informacionEvento?nombre='+args.calendarEvent.title+'&lugar='+lug)
			.success(function(data) {
				data.emailLogin = $scope.user;
				alert.show('Edited', data);
			}).error(function(data) {
				$("#err").modal();
			});
        
      }
    }];

    function getParameterByName(name) {
		   name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		   var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		   results = regex.exec(location.search);
		   return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
		}
    vm.addEvent = function() {
    	
      vm.events.push({
        title: 'New event',
        startsAt: moment().startOf('day').toDate(),
        endsAt: moment().endOf('day').toDate(),
        color: calendarConfig.colorTypes.important,
        draggable: true,
        resizable: true
      });
    };

    vm.eventClicked = function(event) {
      alert.show('Clicked', event);
    };

    vm.eventEdited = function(event) {
      alert.show('Edited', event);
    };

    vm.eventDeleted = function(event) {
      alert.show('Deleted', event);
    };

    vm.eventTimesChanged = function(event) {
      alert.show('Dropped or resized', event);
    };

    vm.toggle = function($event, field, event) {
      $event.preventDefault();
      $event.stopPropagation();
      event[field] = !event[field];
    };

    vm.timespanClicked = function(date, cell) {

      if (vm.calendarView === 'month') {
    	  
        if ((vm.cellIsOpen && moment(date).startOf('day').isSame(moment(vm.viewDate).startOf('day'))) || cell.events.length === 0 || !cell.inMonth) {
        	
        	vm.cellIsOpen = false;
        } else {
          vm.cellIsOpen = true;
          vm.viewDate = date;
        }
      } else if (vm.calendarView === 'year') {
        if ((vm.cellIsOpen && moment(date).startOf('month').isSame(moment(vm.viewDate).startOf('month'))) || cell.events.length === 0) {
          vm.cellIsOpen = false;
        } else {
          vm.cellIsOpen = true;
          vm.viewDate = date;
        }
      }

    };
    $scope.crearEvento = function(){
    	location.href="../CrearEvento/crearEvento.html?categoria="+inter+"&lugar="+lug+"&email="+$scope.user;
    }
    $scope.buscarEvento = function(){
    	location.href="../BuscarEvento/buscarEvento.html?email="+$scope.user;
    }
    $scope.misEventos = function(){
    	location.href="../MisEventos/MisEventos.html?email="+$scope.user;
    }

  });
