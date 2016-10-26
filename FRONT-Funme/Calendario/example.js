angular.module('mwl.calendar.docs', ['mwl.calendar', 'ngAnimate', 'ui.bootstrap', 'colorpicker.module']);
angular
  .module('mwl.calendar.docs') //you will need to declare your module with the dependencies ['mwl.calendar', 'ui.bootstrap', 'ngAnimate']
  .controller('KitchenSinkCtrl', function(moment, alert, calendarConfig, $http) {

    var vm = this;

    //These variables MUST be set as a minimum for the calendar to work
    vm.calendarView = 'month';
    vm.viewDate = new Date();
    var actions = [{
      label: '<i class=\'glyphicon glyphicon-pencil\'></i>',
      onClick: function(args) {
        alert.show('Edited', args.calendarEvent);
      }
    }, {
      label: '<i class=\'glyphicon glyphicon-remove\'></i>',
      onClick: function(args) {
        alert.show('Deleted', args.calendarEvent);
      }
    }];
    var inter = "deportes";
    var lug = "barcelona";
    $http.get('http://192.168.1.65:8080/funme/buscarEventos?interes='+inter+'&lugar='+lug)
	.success(function(data) {
		vm.events = [];
		for(var i in data){
			var event = {
					title : data[i].title,
					startsAt : new Date(data[i].year, data[i].month, data[i].day, data[i].hour, data[i].minute),
					actions : actions
			};
			vm.events.push(event);
		}
		console.log(vm.events);
		//vm.events = data;
	}).error(function(data) {
		alert(error);
	});
   /*vm.events = [ 
        {
          title: 'An event',
 //         color: calendarConfig.colorTypes.warning,
          startsAt: new Date(2016,9,25,15,0)
//          endsAt: moment().startOf('week').add(1, 'week').add(9, 'hours').toDate(),
//          draggable: true,
//          resizable: true,
//          actions: actions
        }
        //, {
//          title: '<i class="glyphicon glyphicon-asterisk"></i> <span class="text-primary">Another event</span>, with a <i>html</i> title',
//          color: calendarConfig.colorTypes.info,
//          startsAt: moment().subtract(1, 'day').toDate(),
//          endsAt: moment().add(5, 'days').toDate(),
//          draggable: true,
//          resizable: true,
//          actions: actions
//        }, {
//          title: 'This is a really long event title that occurs on every year',
//          color: calendarConfig.colorTypes.important,
//          startsAt: moment().startOf('day').add(7, 'hours').toDate(),
//          endsAt: moment().startOf('day').add(19, 'hours').toDate(),
//          recursOn: 'year',
//          draggable: true,
//          resizable: true,
//          actions: actions
//        }
  ];*/

    vm.cellIsOpen = true;

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
    	alert("prueba");
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

  });
