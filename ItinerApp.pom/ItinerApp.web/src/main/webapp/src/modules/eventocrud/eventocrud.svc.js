/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){

    var mod = ng.module("eventocrudModule");

     mod.service("eventocrudService", ["$http", "eventocrudContext", function ($http, context) {

     	this.eventos = [ ];

        this.fetchRecords = function () {
            $http.get('api/eventos').success(function (data) {
            data.forEach(function (evento) {
                console.log(evento);
                (this.eventos).push(evento);
                console.log(this.evento);
            });
        });
        };

        this.fetchRecords = function () {
            $http.get('api/eventos'+id);
        }

        this.deleteRecord = function (id) {
        	return $http.delete("api/eventos" + id);
        };

        this.saveRecord = function (record) {
            if (record.id) {
                    return $http.put("api/evento/" + currentRecord.id, currentRecord);
                } else {
                    return $http.post("api/evento", currentRecord);
                }
        };
    }]);


})(window.angular);

