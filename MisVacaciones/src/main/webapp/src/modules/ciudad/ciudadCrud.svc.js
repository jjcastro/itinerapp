/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){
    
    var mod = ng.module("ciudadCrudModule");
    
     mod.service("ciudadCrudService", ["$http", "ciudadCrudContext", function ($http, context) {
        
     	this.ciudades = [
     		{
     			"id": 1,
     			"nombre": "Rio de Janeiro",
     			"pais": "Brasil",
     			"descripcion": "Río de Janeiro es una ciudad con mucho encanto, el encanto de sus playas y su mar, el encanto de sus islas, bosques y montañas, el encanto del pueblo bronceado por el sol que brilla durante todo el año. Río es así, una ciudad alegre y extrovertida , fácil de amar. Será un amor a primera vista, como casi siempre pasa. En esta web podrás encontrar informacion turística de Río de Janeiro para ayudarte con los preparativos.",
     			"subtext": "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit, itaque, deserunt corporis vero ipsum nisi eius odio natus ullam provident pariatur temporibus.",

     			"fotoBig": "Rio de Janeiro",
     			"fotoSmall": "rio-square.jpg",
     			"fechaI": "Jan 24",
     			"fechaF": "Jan 30"
     		},
     		{
     			"id": 2,
     			"nombre": "Buenos Aires",
     			"pais": "Argentina",
     			"descripcion": "Río de Janeiro es una ciudad con mucho encanto, el encanto de sus playas y su mar, el encanto de sus islas, bosques y montañas, el encanto del pueblo bronceado por el sol que brilla durante todo el año. Río es así, una ciudad alegre y extrovertida , fácil de amar. Será un amor a primera vista, como casi siempre pasa. En esta web podrás encontrar informacion turística de Río de Janeiro para ayudarte con los preparativos.",
     			"subtext": "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit, itaque, deserunt corporis vero ipsum nisi eius odio natus ullam provident pariatur temporibus.",
     			"fotoBig": "Rio de Janeiro",
     			"fotoSmall": "argentina-square.jpg",
     			"fechaI": "Jan 30",
     			"fechaF": "Feb 14"
     		},
     		{
     			"id": 3,
     			"nombre": "Santiago de Chile",
     			"pais": "Chile",
     			"descripcion": "Río de Janeiro es una ciudad con mucho encanto, el encanto de sus playas y su mar, el encanto de sus islas, bosques y montañas, el encanto del pueblo bronceado por el sol que brilla durante todo el año. Río es así, una ciudad alegre y extrovertida , fácil de amar. Será un amor a primera vista, como casi siempre pasa. En esta web podrás encontrar informacion turística de Río de Janeiro para ayudarte con los preparativos.",
     			"subtext": "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit, itaque, deserunt corporis vero ipsum nisi eius odio natus ullam provident pariatur temporibus.",
     			"fotoBig": "Rio de Janeiro",
     			"fotoSmall": "chile-square.jpg",
     			"fechaI": "Feb 15",
     			"fechaF": "Feb 20"
     		},
     		{
     			"id": 4,
     			"nombre": "New York",
     			"pais": "USA",
     			"descripcion": "Río de Janeiro es una ciudad con mucho encanto, el encanto de sus playas y su mar, el encanto de sus islas, bosques y montañas, el encanto del pueblo bronceado por el sol que brilla durante todo el año. Río es así, una ciudad alegre y extrovertida , fácil de amar. Será un amor a primera vista, como casi siempre pasa. En esta web podrás encontrar informacion turística de Río de Janeiro para ayudarte con los preparativos.",
     			"subtext": "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit, itaque, deserunt corporis vero ipsum nisi eius odio natus ullam provident pariatur temporibus.",
     			"fotoBig": "Rio de Janeiro",
     			"fotoSmall": "usa-square.jpg",
     			"fechaI": "Feb 21",
     			"fechaF": "Feb 29"
     		},
     	];

        this.fetchRecords = function () {
            return this.ciudades;
        };

        this.deleteRecord = function (id) {
        	for (var i = 0; i < this.ciudades.length; i++) {
				if (this.ciudades[i].id == id) {
				  this.ciudades.splice(i,1);
				}
			}
        };

        this.newRecord = function (record) {
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

        this.updateRecord = function (record, id) {
        	this.deleteRecord(id);
        	this.newRecord(record);
        };
    }]);
    
    
})(window.angular);

