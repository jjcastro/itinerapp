/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){
    
    var mod = ng.module("perfilModule");
    
     mod.service("perfilService", ["$http", function ($http) {
        var usuario = {}
        var recuerdos = [];
        this.crearUsuario = function(currentRecord)
        {
            /*
            usuario.push({nombre: currentRecord.nombre, 
                lugNac: currentRecord.lugNac, 
                fecNac:currentRecord.fecNac,
                ciuFav:currentRecord.ciuFav,
                email:currentRecord.email,
                telCon: currentRecord.telCon});*/
            usuario = angular.copy(currentRecord);
            
        };
        this.editarUsuario = function(nombre,master)
        {
            /*
              for(var i = 0; i < usuarios.length; i++)
            {
                
                if(usuarios[i].nombre == nombre)
                {
                    usuarios[i] = angular.copy(master);
                    i=usuarios.length;
                }
            }*/
            usuario = angular.copy(master);
           
            
        };
        this.crearRecuerdo = function(master)
        {
            recuerdos.push(master);
        };
        
        
        
        this.fetchRecords = function()
        {
           
            return usuario;
        };
        
        this.fetchRecordsRec = function()
        {
            return recuerdos;
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
            /*
            for(var i = 0; i < usuarios.length; i++)
            {
                
                if(usuarios[i].nombre == nombre)
                {
                    usuarios.slice(i,1);
                    i = usuarios.length;
                    return "done";
                }
            }*/
            usuario = {};
            
        };
    }]);
    
    
})(window.angular);
