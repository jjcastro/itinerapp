// src/modules/person/person.mod.js
// Definición del modulo de personas

(function(ng){

  // define el módulo "personModule" con dependencia a ui.bootstrap
  var mod = ng.module("eventocrudModule", ["ui.bootstrap"]);

  // define una constante usada por el servicio y el mock del servicio
  mod.constant("eventocrudContext", "api/eventoscrud");

})(window.angular);