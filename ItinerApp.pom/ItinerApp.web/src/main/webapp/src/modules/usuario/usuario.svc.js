(function(){
    var mod = angular.module('usuarioModule');

    mod.service('usuarioSvc', ['$http', function($http){

        this.usuarioActual = {};
        this.autenticado = false;

        this.crearUsuario = function(usr){
           $http.post('api/usuarios',usr);
        };

        this.eliminarUsuario = function(id){
           $http.delete('api/usuarios/' + id);
        };

        this.modificarUsuario = function(usr){
           $http.put('api/usuarios' + usr.id, usr);
        };

        this.cargarUsuario = function(id){
            return $http.get('api/usuarios/' + id);
        };

        this.cargarUsuarios = function(){
            $http.get('api/usuarios');
        };

        this.estaAutenticado = function(){

        }

        this.iniciarSesion = function(usrLogin){
            return $http.post('api/usuarios/login', usrLogin);
        };

        this.cerrarSesion = function(){
        };
    }]);
})(window.angular);