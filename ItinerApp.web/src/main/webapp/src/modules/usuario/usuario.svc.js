(function(){
    var mod = angular.module('usuarioModule');

    mod.service('usuarioSvc', ['$http', function($http){

        this.usuarios = users;
        this.usuarioActual = this.usuarios[0];
        this.autenticado = false;

        this.crearUsuario = function(usr){
            var req = {
                method: 'POST',
                url: 'http://localhost:27087/ItinerApp.web/api/usuarios',
                headers: {'Content-Type': undefined},
                data: {usuario: usr}
            };

            $http(req).then(function(){alert('Usuario creado.');}, function(){alert('No se creó el usuario.');});
            //this.usuarios.push(usr);
        };

        this.eliminarUsuario = function(id){
            /*for (i = 0; i < this.usuarios.length; i++){
                if (this.usuarios[i].id == id){
                    this.usuarios.splice(i, 1);
                    i = this.usuarios.length;
                }
            }*/

            var req = {
                method: 'DELETE',
                url: 'http://localhost:27087/ItinerApp.web/api/usuarioS/' + id,
                headers: {'Content-Type': undefined}
            };

            $http(req).then(function(){alert('Usuario eliminado.');}, function(){alert('No se eliminó el usuario.');});
        };

        this.modificarUsuario = function(usr){
            var req = {
                method: 'PUT',
                url: 'http://localhost:27087/ItinerApp.web/api/usuarios' + usr.id,
                headers: {'Content-Type': undefined},
                data: {usuario: usr}
            };

            $http(req).then(function(){alert('Usuario modificado.');}, function(){alert('No se modificó el usuario.');});
            this.usuarios[usr.id] = usr;
        };

        this.cargarUsuario = function(id){
            var usuar = {};

            var req = {
                method: 'GET',
                url: 'http://localhost:27087/ItinerApp.web/api/usuarios/' + id,
                headers: {'Content-Type': undefined}
            };

            $http(req).then(function(resp){usuar = resp.data;}, function(){alert('No se cargó el usuario.');});

            return usuar;
        };

        this.cargarUsuarios = function(){
            /*var usrs;
            
            var req = {
                method: 'GET',
                url: 'http://localhost:27087/ItinerApp.web/api/usuarios',
                headers: {'Content-Type': undefined}
            };

            $http(req).then(function(resp){usrs = resp.data;}, function(){alert('No se cargaron los usuarios.');});
            */
            return $http.get('api/usuarios');;
        };

        this.estaAutenticado = function(){
            return this.autenticado;
        }

        this.iniciarSesion = function(usr, pass){
            for (var i = 1; i < this.usuarios.length; i++){
                if (this.usuarios[i].username == usr && this.usuarios[i].password == pass){
                    this.usuarioActual = this.usuarios[i];
                    this.autenticado = true;
                    i = this.usuarios.length;
                }
            }
        };

        this.cerrarSesion = function(){
            this.usuarioActual = this.usuarios[0];
            this.autenticado = false;
        };
    }]);

    var users = [
            {
                id: 0,
                nombre: 'Invitado',
                apellido: '',
                username: '',
                email: '',
                cedula : 0,
                password: '',
                administrador: false
            },
            {
                id: 1,
                nombre: 'Juan Camilo',
                apellido: 'Marthá',
                username: 'jc.martha',
                email: 'jc.martha10@uniandes.edu.co',
                password: 'asdf',
                cedula : 0,
                administrador: true
            },
            {
                id: 2,
                nombre: 'Juan José',
                apellido: 'Castro',
                username: 'jj.castro',
                email: 'jj.castro10@uniandes.edu.co',
                password: '1234',
                cedula : 0,
                administrador: true
            },
            {
                id: 3,
                nombre: 'Santiago',
                apellido: 'Robayo',
                username: 's.robayo',
                email: 's.robayo222@uniandes.edu.co',
                password: 'srg',
                cedula : 0,
                administrador: true
            },
            {
                id: 4,
                nombre: 'Felipe',
                apellido: 'Martinez',
                username: 'f.martinez',
                email: 'f.martinez@uniandes.edu.co',
                password: '5678',
                cedula : 0,
                administrador: true
            },
            {
                id: 5,
                nombre: 'Juan Pablo',
                apellido: 'Otálora',
                username: 'jp.otalora',
                email: 'jp.otalora10@uniandes.edu.co',
                password: 'vbnm',
                cedula : 0,
                administrador: true
            }
        ];
})(window.angular);