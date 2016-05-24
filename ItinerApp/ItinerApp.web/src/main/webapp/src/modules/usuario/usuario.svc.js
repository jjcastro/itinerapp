(function(){
    var mod = angular.module('usuarioModule');

    mod.service('usuarioSvc', ['$http', function($http){

        this.usuarioLog = {
                id: '' /*Tipo Long. El valor se asigna en el backend*/,
                lugarNacimiento: '' /*Tipo String*/,
                nombre: '' /*Tipo String*/,
                correo: '' /*Tipo String*/,
                password: '' /*Tipo String*/,
                fechaNacimiento: '' /*Tipo String*/,
                foto: '' /*Tipo String*/,
                administrador: 0 /*Tipo int.*/,
                itinerarios: {} /*Lista de itinerarios vacía*/,
                recuerdos: {} /*Lista de recuerdos vacía*/
        };
        this.autenticado = false;
        var idLogueado = -1;
        
        this.getLogueado = function()
        {
            return idLogueado;
        };
        this.getLogueadoFull= function()
        {
            return usuarioLog;
        };
        this.actualizarLogueado = function(logueado)
        {
            idLogueado = logueado.id;
            usuarioLog = logueado;
            return idLogueado;
        };
        
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

        };

        this.iniciarSesion = function(usrLogin){
            return $http.post('api/usuarios/login', usrLogin);
        };

        this.cerrarSesion = function(){
        };
    }]);
})(window.angular);