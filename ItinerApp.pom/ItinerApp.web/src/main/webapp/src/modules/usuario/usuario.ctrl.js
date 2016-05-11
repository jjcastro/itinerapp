(function (ng) {

    var mod = ng.module('usuarioModule');

    mod.controller('usuarioCtrl', ['$scope', 'usuarioSvc', function ($scope, svc) {
            $scope.usuarioActual = {};
            $scope.usuarios = [];

            $scope.usr =
                    {
                        id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                        nombre: '' /*Tipo String*/,
                        apellido: '',
                        username: '' /*Tipo String*/,
                        email: '' /*Tipo String*/,
                        cedula: '' /*Tipo String*/
                    };

            $scope.usrLogin = {
                username : '',
                pass : ''
            };

            $scope.id = -1;
            $scope.modoEdicion = false;
            $scope.registrado = false;

            this.crearUsuario = function () {
                svc.crearUsuario($scope.usr);
                $scope.registrado = true;
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
                svc.iniciarSesion($scope.usrLogin);
            };

            this.estaAutenticado = function(){
                return svc.autenticado;
            }

            this.cerrarSesion = function () {
                svc.logout();
            };
        }]);
})(window.angular);