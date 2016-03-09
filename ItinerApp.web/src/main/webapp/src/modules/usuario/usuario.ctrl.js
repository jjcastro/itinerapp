(function(ng){

    var mod = ng.module('usuarioModule');

    mod.controller('usuarioCtrl', ['$scope', 'usuarioSvc', function($scope, svc){
           $scope.usuarioActual = {};
           $scope.usuarios = [];
           $scope.usr = {};
           $scope.id = -1;
           $scope.modoEdicion = false;
           $scope.registrado = false;

           // this.pass = '';
           // this.autenticado = '';

           this.crearUsuario = function(){
               this.cargarUsuarios();
               $scope.usr.id = $scope.usuarios.length;
               svc.crearUsuario($scope.usr);
               $scope.registrado = true;
           };

           this.eliminarUsuario = function(id){
               svc.eliminarUsuario(id);
           };

           this.editarUsuario = function(id){
               this.cargarUsuario(id);
               $scope.modoEdicion = true;
           };

           this.modificarUsuario= function(){
               svc.modificarUsuario($scope.usr);
               $scope.modoEdicion = false;
           };

           this.cargarUsuario = function(id){
               $scope.usr = svc.cargarUsuario(id)
           };

           this.cargarUsuarios = function(){
               $scope.usuarios = svc.cargarUsuarios();
           };

           this.iniciarSesion = function(){
                usuarioSrv.login(this.username, this.pass);
                if (usuarioSrv.autenticado){
                    this.autenticado = 'Autenticado!';
                } else {
                    this.autenticado = 'No autenticado';
                }
                return usuarioSrv.autenticado;
            };

            this.cerrarSesion = function(){
                usuarioSrv.logout();
            };
    }]);
})(window.angular);