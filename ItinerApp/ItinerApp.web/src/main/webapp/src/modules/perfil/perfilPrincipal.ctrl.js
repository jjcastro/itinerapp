/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module('perfilModule');

    mod.controller('perfilCtrl', ['$scope', 'perfilService', 'usuarioSvc', function ($scope, svc,uSvc) {
           
        
        var self = this;
        $scope.read = true;
        $scope.edit = false;
        $scope.delete = false;
        $scope.currentRecord = {};
        $scope.records = [];
        //$scope.user = {};
        $scope.master ={};
        $scope.prueba ='prueba';
        
         $scope.user = {
                id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                name: '' /*Tipo String*/,
                descripcion: '' /*Tipo String*/,
                imagen: '' /*Tipo String*/,
                usuario:{}
                
            };
            $scope.creado = {
                id: '11' /*Tipo Long. El valor se asigna en el backend*/,
                name: '' /*Tipo String*/,
                descripcion: '' /*Tipo String*/,
                imagen: '' /*Tipo String*/,
                usuario:{}
                
            };
        
          this.actualizarCurrent =function(record)
            {
                
                $scope.prueba = ' '+record.id;
                svc.actualizarRecuerdoSeleccionado(record);
                
            };
            this.deleteTrue = function()
            {
                $scope.delete = true;
                $scope.edit = false;
                $scope.read=false;
            };
            this.editTrue = function()
            {
                $scope.edit = true;
                $scope.read=false;
                $scope.delete = false;
            };
            this.reset = function()
            {
                
                $scope.user = angular.copy($scope.master);
                $scope.edit = false;
                $scope.delete = false;
                $scope.read=true;  
            };
            this.deleteInfo = function()
            {
                
                if(svc.existeUsuario($scope.master.nombre) === 1)
                {
                    svc.deleteInfo($scope.master.nombre);
                }
                else
                {}
                this.fetchRecordsRec();
            };
          
            this.createRecord = function () {
               
             
            };
            this.editRecord = function (record) {
                
                return svc.fetchRecord(record.id).then(function (response) {
                    $scope.currentRecord = response.data;
                    $scope.edit = true;
                    $scope.read=false;
                    $scope.delete = false;
                    return response;
                });
            };
            
            this.update = function(user) {
               
            $scope.user.usuario = uSvc.getLogueadoFull();
            $scope.master = angular.copy($scope.user);
            //$scope.creado = 
            svc.crearRecuerdo($scope.master).then(function (response)
            {
                $scope.creado = response.data;
                self.agregarRecuerdo();
                self.fetchRecordsRec();
                $scope.edit = false;
                $scope.delete = false;
                $scope.read=true; 
            });
              
            };
            
            this.agregarRecuerdo = function()
            {
                svc.asignar($scope.creado).then(function ()
                {
                    self.fetchRecordsRec();
                });
            };
            
            /*this.fetchRecordsRec = function () {
                
               
               return  svc.fetchRecordsRec().then(function(response)
               {
                    $scope.records = response.data;
                    //$scope.master = $scope.records[0];
                    $scope.edit = false;
                    $scope.delete = false;
                    $scope.read=true;
                    self.reset();
                    return response;
               });   
            };*/
            
                this.fetchRecordsRec = function () {
                
               idUsuarioLogueado = uSvc.getLogueado();
               return  svc.fetchRecordsRec(idUsuarioLogueado).then(function(response)
               {
                    $scope.records = response.data;
                    //$scope.master = $scope.records[0];
                    $scope.edit = false;
                    $scope.delete = false;
                    $scope.read=true;
                    self.reset();
                    return response;
               });
               
               
            };

          
            this.saveRecord = function () {
                    
                return svc.crearUsuario($scope.currentRecord).then(function () {
                        self.fetchRecordsRec();
                    });                
            };

            
            this.deleteRecord = function (record) {
                return svc.deleteRecord(record.id).then(function () {
                    self.fetchRecordsRec();
                });
            };

            this.reset();
            this.fetchRecordsRec();

        }]);

})(window.angular);