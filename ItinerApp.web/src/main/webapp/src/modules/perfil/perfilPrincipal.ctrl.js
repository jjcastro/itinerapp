/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module("perfilModule");

    mod.controller("perfilCtrl", ["$scope", "perfilService", function ($scope, svc) {
           
        
        var self = this;
        $scope.read = true;
        $scope.edit = false;
        $scope.delete = false;
        $scope.currentRecord = {};
        $scope.records = [];
        $scope.user = {};
        $scope.master ={};
        $scope.prueba ='prueba';
        
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
            /*
            this.changeCurrent = function()
            {
                $scope.currentRecord.nombre = $scope.user.nombre;
                $scope.currentRecord.lugNac = $scope.user.LugNac;
                $scope.currentRecord.fecNac = $scope.user.FecNac;
                $scope.currentRecord.ciuFav = $scope.user.CiuFav;
                $scope.currentRecord.email = $scope.user.Email;
                $scope.currentRecord.telCon = $scope.user.TelCon;
            };*/
            this.createRecord = function () {
               
               /*this.changeCurrent();
               if(svc.existeUsuario($scope.currentRecord.nombre) === 0){
                svc.crearUsuario($scope.currentRecord);
            }
            else
            {
                svc.editarUsuario($scope.currentRecord);
            }
                this.fetchRecords();*/
  
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
                /*
            $scope.master = angular.copy(user);
            
            svc.crearRecuerdo($scope.master);
            
            this.fetchRecordsRec();
            $scope.edit = false;
            $scope.delete = false;
            $scope.read=true;  
                */
            $scope.master = angular.copy($scope.user);
            
            svc.crearRecuerdo($scope.master).then(function ()
            {
                self.fetchRecordsRec();
                $scope.edit = false;
                $scope.delete = false;
                $scope.read=true; 
            });
            
            
            
            };
            
            this.agregarRecuerdo = function()
            {
                
            };
            this.fetchRecordsRec = function () {
                
                //NOTA:
                /*Se esta realizando de esta manera porque se esta considerando
                 * que el usuario ha hecho log in por lo tanto el record siempre
                 * sera el mismo. Mas adelante el current record lo definira el 
                 * login del usuario.              
                 * */
                /*    $scope.records = svc.fetchRecordsRec();
                    $scope.master = $scope.records[0];
                    $scope.edit = false;
                    $scope.delete = false;
                    $scope.read=true;
                    this.reset();
                    return $scope.records;
                */
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