angular.module('mwl.calendar.docs', ['mwl.calendar', 'ngAnimate', 'ui.bootstrap', 'colorpicker.module']);
angular
  .module('mwl.calendar.docs') //you will need to declare your module with the dependencies ['mwl.calendar', 'ui.bootstrap', 'ngAnimate']
  .controller('KitchenSinkCtrl', function(moment, alert, calendarConfig, $http, $scope) {
	  calendarConfig.allDateFormats.angular.title.week = 'Semana {week} de {year}';
	  calendarConfig.i18nStrings.weekNumber = 'Semana {week}';
	  var url = 'http://192.168.1.65:8080/funme/';
		$scope.inter = getParameterByName('categoria');
		$scope.lug = getParameterByName('lugar');
		$scope.user = getParameterByName('email');
		
			$http.get(url+'buscarEventos?categoria='+$scope.inter+'&lugar='+$scope.lug)
			.success(function(data) {
				vm.events = [];
					for(var i in data){
						var colorsito = calendarConfig.colorTypes.warning;
						console.log(colorsito);
						switch(data[i].categoria) {
					    case "deportes":
					        colorsito = {
					    		primary : '#5cd65c',
					    		secondary : '#5cd65c'
					    	}
					        break;
					    case "idiomas":
					        colorsito = {
						    		primary : '#66c2ff',
						    		secondary : '#66c2ff'
						    }
					        break;
					    case "cultura":
					        colorsito = {
						    		primary : '#ffc266',
						    		secondary : '#ffc266'
						    }
					        break;
					    default:
					         colorsito = calendarConfig.colorTypes.warning;
					      break;
					}
					var event = {
							title : data[i].title,
							startsAt : new Date(data[i].year, (data[i].month-1), data[i].day, data[i].hour, data[i].minute),
							color: colorsito,
							actions : actions
					};
					vm.events.push(event);
				}
			}).error(function(data) {
				$("#err").modal();
			});
console.log(calendarConfig.colorTypes);
    var vm = this;

    //These variables MUST be set as a minimum for the calendar to work
    vm.calendarView = 'month';
    vm.viewDate = new Date();
    var actions = [];

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
    	var dia = ("0" + event.startsAt.getDate()).slice(-2) + "/" + ("0" + (event.startsAt.getMonth()+1)).slice(-2)+ "/" + event.startsAt.getFullYear(); 
		var hora = ("0" + event.startsAt.getHours()).slice(-2) + ":" + ("0" + event.startsAt.getMinutes()).slice(-2);

    	 $http.get(url+'informacionEvento?nombre='+event.title+'&lugar='+$scope.lug+'&dia='+dia+'&hora='+hora+'&email='+$scope.user)
			.success(function(data) {
				data.emailLogin = $scope.user;
				alert.show('Clicked', data);
			}).error(function(data) {
				console.log("modal err");
				$("#err2").modal();
			});
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
    	location.href="../CrearEvento/crearEvento.html?categoria="+$scope.inter+"&lugar="+$scope.lug+"&email="+$scope.user;
    }
    $scope.buscarEvento = function(){
    	location.href="../BuscarEvento/buscarEvento.html?email="+$scope.user;
    }
    $scope.misEventos = function(){
    	location.href="../MisEventos/MisEventos.html?email="+$scope.user;
    }
    $scope.reset = function(){
    	$scope.evento = "";
    }
    $scope.busquedaAvanzada = function(){
        $http.get(url+'busquedaAvanzada?categoria='+$scope.inter+'&nombre='+$scope.evento+'&lugar='+$scope.lug)       
            .success(function(data) {
              console.log("ha entrado");
                vm.events = [];
                for(var i in data){
					var colorsito = calendarConfig.colorTypes.warning;
					switch(data[i].categoria) {
				    case "deportes":
				        colorsito = {
					    		primary : '#5cd65c',
					    		secondary : '#5cd65c'
					    	}
				        break;
				    case "idiomas":
				        colorsito = {
					    		primary : '#66c2ff',
					    		secondary : '#66c2ff'
					    }
				        break;
				    case "cultura":
				        colorsito = {
					    		primary : '#ffc266',
					    		secondary : '#ffc266'
					    }
				        break;
				    default:
				         colorsito = calendarConfig.colorTypes.warning;
				      break;
				}
				var event = {
						title : data[i].title,
						startsAt : new Date(data[i].year, (data[i].month-1), data[i].day, data[i].hour, data[i].minute),
						color: colorsito,
						actions : actions
				};
				vm.events.push(event);
			}
            }).error(function(data) {
              $("#err").modal();
            });
        }
  });
