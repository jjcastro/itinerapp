/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){

    var mod = ng.module("eventocrudModule");

     mod.service("eventocrudService", ["$http", "eventocrudContext", function ($http, context) {

     	this.eventos = [
     		{
     			"id": 1,
     			"nombre": "Estereo Picnic",
     			"ciudad": "Bogota",
     			"descripcion": "Festival de musica mas esperado del año",

     		},
     		{
     			"id": 2,
     			"nombre": "Rock al Parque",
                        "ciudad": "Bogota",
     			"descripcion": "Rock en Bogota :D",

     		},
     	];

        this.fetchRecords = function () {
            return this.eventos;
        };

        this.deleteRecord = function (id) {
        	for (var i = 0; i < this.eventos.length; i++) {
				if (this.eventos[i].id == id) {
				  this.eventos.splice(i,1);
				}
			}
        };

        this.newRecord = function (record) {
            record.subtext = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit, itaque, deserunt corporis vero ipsum nisi eius odio natus ullam provident pariatur temporibus.";
 			record.fotoBig = "Rio de Janeiro";
 			record.fotoSmall = "usa-square.jpg";

 			var max = 0;
 			for (var i = 0; i < this.eventos.length; i++) {
				if (this.eventos[i].id > max) {
				  max = this.eventos[i].id;
				}
			}

			record.id = max+1;
 			this.eventos.push(record);
        };

        this.updateRecord = function (record, id) {
        	this.deleteRecord(id);
        	this.newRecord(record);
        };
    }]);


})(window.angular);

