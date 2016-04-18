/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global i */

(function(ng){
    
    var mod = ng.module("itinerarioModule");
    
        mod.service("itinerarioService", function () {
        
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
    };

    //simply returns the contacts list
    this.list = function () {
        return itinerarios;
    };
});
    
    
})(window.angular);


