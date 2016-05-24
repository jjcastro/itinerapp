/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global i */

(function(ng){
    
    var mod = ng.module("itinerarioModule");
    
        mod.service("itinerarioService", ["$http", function ($http) {
        
        var uid = 1;
    
        //contacts array to hold list of all contacts
        var itinerarios = [{
             id: 0,
            'name': 'Viral',
            'image': 'hello@gmail.com',
            'dateOut': '12/12/16'
            
    }];
    
    //save method create a new contact if not already exists
    //else update the existing object
    this.save = function (itinerario) {
        console.log("entra a save");
        console.log(typeof itinerario.id);
        if (typeof itinerario.id == 'undefined') {
            //if this is new contact, add it in contacts array
            console.log(1);
            itinerario.id = uid++;
            itinerarios.push(itinerario);
            console.log(itinerarios);
        } else {
            console.log("itinerario existente");
            console.log(itinerario);
            //for existing contact, find this contact using id
            //and update it.
            for (i in itinerarios) {
                if (itinerarios[i].id === itinerario.id) {
                    itinerarios[i] = itinerario;
                }
            }
        }

    };

    //simply search contacts list for given id
    //and returns the contact object if found
    this.get = function (id) {
        for (i in itinerarios) {
            if (itinerarios[i].id === id) {
                return itinerarios[i];
            }
        }

    };
    
    //iterate through contacts list and delete 
    //contact if found
    this.delete = function (id) {
        for (i in itinerarios) {
            if (itinerarios[i].id === id) {
                itinerarios.splice(i, 1);
            }
        }
        return $http.get('api/itinerarios/'+actual);
    };

    //simply returns the contacts list
    this.list = function () {
        return itinerarios;
    };
    
    this.actualizarItinerarioSeleccionado= function(record)
        {
            actual = record.id;
            return actual;
        };
        
        this.itinerarioSeleccionado = function()
        {            
            return $http.get('api/itinerarios/'+actual);  
        };
        this.crearItinerario = function(currentRecord)
        {
            /*
            usuario.push({nombre: currentRecord.nombre, 
                lugNac: currentRecord.lugNac, 
                fecNac:currentRecord.fecNac,
                ciuFav:currentRecord.ciuFav,
                email:currentRecord.email,
                telCon: currentRecord.telCon});*/
            itinerario = angular.copy(currentRecord);
            
        };
        this.editarIitnerario = function(nombre,master)
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
            itinerario = angular.copy(master);
           
            
        };
        this.crearItinerario = function(master)
        {
            //recuerdos.push(master);
            return $http.post('api/itinerario',master);
        };
        
        
        
        this.fetchRecords = function()
        {
           
            return itinerario;
        };
        
        this.fetchRecordsRec = function()
        {
            //return recuerdos;
            return $http.get('api/itinerarios');
        };
    
}]);
    
    
})(window.angular);


