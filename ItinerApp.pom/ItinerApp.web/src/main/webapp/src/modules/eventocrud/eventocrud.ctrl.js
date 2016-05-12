// src/modules/person/person.ctrl.js
// Controlador para el módulo de personas

(function (ng) {

  // es parte del módulo "personModule"
  var mod = ng.module("eventocrudModule");

  // crea el controlador con dependencias a $scope y a personService
  mod.controller("eventocrudCtrl", ["$scope", "eventocrudService", function ($scope, svc) {

    // TODO: define los atributos en el scope
    $scope.alerts=[];

    $scope.currentRecord = {
        id: undefiend,
        name:'',
        descripcion:'',
        ciudad:'',
    };

    $scope.records = [];

    $scope.clear=function(){
        $scope.value = null;
    };

    $scope.open = function($event){
      $event.preventDefault();
      $event.stopPropagation();
      $scope.open=true;
    };

    this.closeAlert = function (index) {
        $scope.alerts.splice(index,1);
    };

    function showMessage(msg, type) {
        var types = ["info", "danger", "warning", "success"];
        if (types.some(function (rc) {
            return type === rc;
        })) {
            $scope.alerts.push({type: type, msg: msg});
        }
    }

    this.showError = function (msg) {
        showMessage(msg, "danger");
    };

    this.showSuccess = function (msg) {
        showMessage(msg, "success");
    };

    var self = this;
    function responseError(response) {
        self.showError(response.data);
    }


    this.editMode = false;
    this.readOnly = false;

    this.changeTab = function (tab) {
        $scope.tab = tab;
    };

    this.fetchRecords = function () {
        return svc.fetchRecords().then(function (response) {
            $scope.records = response.data;
            $scope.currentRecord = {};
            self.editMode = false;
            return response;
        }, sponseError);
    };

    this.editRecord = function (record) {
        return svc.fetchRecord(record.id).then(function (response) {
            $scope.currentRecord = response.data;
            self.editMode = true;
            $scope.$broadcast("post-edit", $scope.currentRecord);
            return response;
        }, responseError);
    };

    this.deleteRecord = function (record) {
        return svc.deleteRecord(record.id).then(function () {
            self.fetchRecords();
        }, responseError);
    };

    this.createRecord = function () {
        this.editMode = true;
        $scope.currentRecord = {};
        $scope.$broadcast("post-create", $scope.currentRecord);
    };

    this.saveRecord = function () {
        return svc.saveRecord($scope.currentRecord).then(function () {
            self.fetchRecords();
        }, responseError);
    };

    var self = this;
    function responseError(response) {
        self.showError(response.data);
    }

    function onCreateOrEdit(event, args) {
        var childName = "reviews";
        if (args[childName] === undefined) {
            args[childName] = [];
        }
        $scope.records = args[childName];
        $scope.refId = args.id;
    }
    $scope.$on("post-create", onCreateOrEdit);
    $scope.$on("post-edit", onCreateOrEdit);

    function indexOf(rc) {
        var field = rc.id !== undefined ? 'id' : 'cid';
        for (var i in $scope.records) {
            if ($scope.records.hasOwnProperty(i)) {
                var current = $scope.records[i];
                if (current[field] === rc[field]) {
                    return i;
                }
            }
        }
    }

    this.createRecord = function () {
        this.editMode = true;
        $scope.currentRecord = {};
    };

    var self = this;
    this.saveRecord = function () {
        var rc = $scope.currentRecord;
        if (rc.id || rc.cid) {
            var idx = indexOf(rc);
            $scope.records.splice(idx, 1, rc);
        } else {
            rc.cid = -Math.floor(Math.random() * 10000);
            rc[$scope.parent] = {id: $scope.refId};
            $scope.records.push(rc);
        }
        this.fetchRecords();
    };

    this.fetchRecords = function () {
        $scope.currentRecord = {};
        this.editMode = false;
    };

    this.editRecord = function (record) {
        $scope.currentRecord = ng.copy(record);
        this.editMode = true;
    };

    this.deleteRecord = function (record) {
        var idx = indexOf(record);
        $scope.records.splice(idx, 1);
    };
}]);
})(window.angular);