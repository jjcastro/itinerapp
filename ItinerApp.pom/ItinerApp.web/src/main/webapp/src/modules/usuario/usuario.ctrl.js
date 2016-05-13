(function (ng) {

    var mod = ng.module('usuarioModule');

    mod.controller('usuarioCtrl', ['$scope', 'usuarioSvc', function ($scope, svc) {
            $scope.usuarioActual = {};
            $scope.usuarios = [];
            $scope.Logueado ={
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
            
            $scope.usr = {
                id: undefined /*Tipo Long. El valor se asigna en el backend*/,
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
                    
            $scope.repPassword = '';

            $scope.id = -1;
            $scope.modoEdicion = false;
            $scope.registrado = false;

            this.crearUsuario = function () {
                svc.crearUsuario($scope.usr);
                $scope.registrado = true;
                this.limpiarUsuario();
            };

            this.eliminarUsuario = function (id) {
                svc.eliminarUsuario(id);
            };

            this.editarUsuario = function (id) {
                this.cargarUsuario(id);
                $scope.modoEdicion = true;
            };

            this.modificarUsuario = function () {
                svc.modificarUsuario($scope.usr);
                $scope.modoEdicion = false;
            };

            this.cargarUsuario = function (id) {
                $scope.usr = svc.cargarUsuario(id)
            };

            this.cargarUsuarios = function () {
                $scope.usuarios = svc.cargarUsuarios();
            };

            this.iniciarSesion = function () {
                return svc.iniciarSesion($scope.usr).then(function(response)
                {
                    $scope.Logueado = response.data;
                    svc.actualizarLogueado($scope.Logueado);
                    return response;
                });
                
            };

            this.estaAutenticado = function(){
                return svc.autenticado;
            };

            this.cerrarSesion = function () {
                svc.logout();
            };
            
            this.limpiarUsuario = function(){
                $scope.usr =
                    {
                        id: undefined /*Tipo Long. El valor se asigna en el backend*/,
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
            }
        }]);
})(window.angular);