/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){

    var mod = ng.module("ciudadCrudModule");

     mod.service("ciudadCrudService", ["$http", "ciudadCrudContext", function ($http, context) {

        this.ciudades = [];
        var global = this;

        this.fetchRecords = function() {
            $http.get('api/ciudad').success(function (data) {
            data.forEach(function (ciudad) {
                console.log(ciudad);
                (global.ciudades).push(ciudad);
                console.log(this.ciudades);
            });
        });
        };
        

        function deleteRecord(id) {
        	for (var i = 0; i < this.ciudades.length; i++) {
				if (this.ciudades[i].id == id) {
				  this.ciudades.splice(i,1);
				}
			}
        };

        function newRecord(record) {
            record.subtext = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit, itaque, deserunt corporis vero ipsum nisi eius odio natus ullam provident pariatur temporibus.";
 			record.fotoBig = "Rio de Janeiro";
 			record.fotoSmall = "usa-square.jpg";

 			var max = 0;
 			for (var i = 0; i < this.ciudades.length; i++) {
				if (this.ciudades[i].id > max) {
				  max = this.ciudades[i].id;
				}
			}

			record.id = max+1;
 			this.ciudades.push(record);
        };

        function updateRecord(record, id) {
        	this.deleteRecord(id);
        	this.newRecord(record);
        };


    }]);


})(window.angular);

