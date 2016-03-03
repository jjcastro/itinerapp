/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {

    var mod = ng.module("perfilModule");

    mod.controller("perfilDatosCtrl", ["$scope", "perfilService", function ($scope, svc) {
           
        $scope.read = true;
        $scope.edit = false;
        var self = this;
       
        $scope.user = {};
        $scope.master = {};
        $scope.currentRecord = {};
        $scope.records = {};
        //$scope.records = [];
        
        
             
            this.editTrue = function()
            {
                $scope.edit = true;
                $scope.read=false;
            };
            this.reset = function()
            {
                
                $scope.user = angular.copy($scope.master);
                $scope.edit = false;
                $scope.read=true;  
            };
            this.deleteInfo = function()
            {
                /*
                if(svc.existeUsuario($scope.master.nombre) === 1)
                {
                    svc.deleteInfo($scope.master.nombre);
                }
                else
                {}
                this.fetchRecords();
                */
               svc.deleteInfo($scope.master.nombre);
            };
          
            
           
            
            this.update = function(user) {
                
            $scope.master = angular.copy(user);
            /*if(svc.existeUsuario($scope.master.nombre) === 0){
                svc.crearUsuario($scope.master);
            }
            else
            {
                svc.editarUsuario($scope.master.nombre,$scope.master);
            }*/
            svc.crearUsuario($scope.master);
            this.fetchRecords();
            $scope.edit = false;
            $scope.read=true;  
            
            };
            
            
            this.fetchRecords = function () {
                
                //NOTA:
                /*Se esta realizando de esta manera porque se esta considerando
                 * que el usuario ha hecho log in por lo tanto siempre
                 * sera el mismo. Mas adelante el current record lo definira el 
                 * login del usuario.              
                 * */
                   /* $scope.records = svc.fetchRecords();
                    $scope.master = $scope.records[0];*/
                    $scope.records = svc.fetchRecords()
                    $scope.master = $scope.records;
                    $scope.edit = false;
                    $scope.read=true;
                    this.reset();
                    return $scope.records;
                
            };

          
            

            
          

            this.reset();
            this.fetchRecords();

        }]);

})(window.angular);