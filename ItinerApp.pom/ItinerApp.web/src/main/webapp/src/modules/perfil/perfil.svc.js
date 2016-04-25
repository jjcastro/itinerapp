/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){
    
    var mod = ng.module("perfilModule");
    
     mod.service("perfilService", ["$http", function ($http) {
        var usuario = {};
        var recuerdos = [];
        var actual = -1;
        
        this.actualizarRecuerdoSeleccionado= function(record)
        {
            actual = record.id;
            return actual;
        };
        
        this.recuerdoSeleccionado = function()
        {            
            return $http.get('api/recuerdos/'+actual);  
        };
        this.crearUsuario = function(currentRecord)
        {
            
            usuario = angular.copy(currentRecord);
            
        };
        this.editarUsuario = function(nombre,master)
        {
            
            usuario = angular.copy(master);
           
            
        };
        this.crearRecuerdo = function(master)
        {
            //recuerdos.push(master);
            return $http.post('api/recuerdos',master);
        };
        
        
        
        this.fetchRecords = function()
        {
           
            return usuario;
        };
        
        this.fetchRecordsRec = function()
        {
            //return recuerdos;
            return $http.get('api/recuerdos');
        };
        
        this.existeUsuario = function(nombre)
        {
            //retorna 1 si existe cero en caso contrario.
             for(var i = 0; i < usuarios.length; i++)
            {
                
                if(usuarios[i].nombre == nombre)
                {
                    return 1;
                }
            }
            return 0;
        };
        
        this.buscarUsuario = function(nombre)
        {
            for(var i = 0; i < usuarios.length; i++)
            {
                
                if(usuarios[i].nombre == nombre)
                {
                    return usuarios[i];
                }
            }
            return null;
        };
        
        this.deleteInfo = function(nombre)
        {
           
            usuario = {};
            
        };
    }]);
    
    
})(window.angular);

