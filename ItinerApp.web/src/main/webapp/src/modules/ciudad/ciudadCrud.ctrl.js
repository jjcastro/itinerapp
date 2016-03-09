// src/modules/person/person.ctrl.js
// Controlador para el módulo de personas

(function (ng) {

  // es parte del módulo "personModule"
  var mod = ng.module("ciudadCrudModule");

  // crea el controlador con dependencias a $scope y a personService
  mod.controller("ciudadCrudCtrl", ["$scope", "$state", "$stateParams", "ciudadCrudService", function ($scope, $state, $stateParams, svc) {

    // TODO: define los atributos en el scope   

    $scope.listaCiudades = [];

    $scope.currentRecord = {};

    this.editMode = false;

    $scope.fetchRecords = function () {
        $scope.listaCiudades = svc.fetchRecords();
        $scope.currentRecord = {};
        this.editMode = false;
    };

    $scope.editRecord = function () {
    	this.editMode = true;
    	$scope.currentRecord = $scope.ciudades;
    }
    $scope.updateRecord = function (city, id) {
    	this.editMode = false;
    	svc.updateRecord(city, id);
    }

    $scope.deleteRecord = function (id) {
    	svc.deleteRecord(id);
    	$state.go('listacrudciudad');
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
    	for (var i = 0; i < $scope.listaCiudades.length; i++) {
			if ($scope.listaCiudades[i].id == $stateParams.id) {
			  $scope.ciudades = $scope.listaCiudades[i];
			}
		}
    }
    else
    {
       $scope.ciudades = $scope.listaCiudades;
    }     

    // TODO: define funciones que son invocadas desde la pantalla
    // y que usan funciones definidas en el servicio


  }]);

})(window.angular);