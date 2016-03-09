// src/modules/person/person.ctrl.js
// Controlador para el módulo de personas

(function (ng) {

  // es parte del módulo "personModule"
  var mod = ng.module("eventocrudModule");

  // crea el controlador con dependencias a $scope y a personService
  mod.controller("eventocrudCtrl", ["$scope", "$state", "$stateParams", "eventocrudService", function ($scope, $state, $stateParams, svc) {

    // TODO: define los atributos en el scope

    $scope.listaEventos = [];

    $scope.currentRecord = {};

    this.editMode = false;

    $scope.fetchRecords = function () {
        $scope.listaEventos = svc.fetchRecords();
        $scope.currentRecord = {};
        this.editMode = false;
    };

    $scope.editRecord = function () {
    	this.editMode = true;
    	$scope.currentRecord = $scope.eventos;
    }
    $scope.updateRecord = function (city, id) {
    	this.editMode = false;
    	svc.updateRecord(city, id);
    }

    $scope.deleteRecord = function (id) {
    	svc.deleteRecord(id);
    	$state.go('listacrudeventos');
    }

    $scope.createRecord = function () {
        this.editMode = true;
        $scope.currentRecord = {};
    };

    $scope.submitNewRecord = function (newRecord) {
    	this.editMode = false;
    	$scope.currentRecord = angular.copy(newRecord);
    	svc.newRecord(newRecord);
    	$scope.newCity = angular.copy({});
    };

    function responseError(response) {
        self.showError(response.data);
    };

    $scope.fetchRecords();

    if($stateParams.id)
    {
    	for (var i = 0; i < $scope.listaEventos.length; i++) {
			if ($scope.listaEventos[i].id == $stateParams.id) {
			  $scope.eventos = $scope.listaEventos[i];
			}
		}
    }
    else
    {
       $scope.eventos = $scope.listaEventos;
    }

    // TODO: define funciones que son invocadas desde la pantalla
    // y que usan funciones definidas en el servicio


  }]);

})(window.angular);