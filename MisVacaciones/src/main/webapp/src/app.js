(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/landing");
            $stateProvider
                    .state('dashboard.evento', {
                        url: '/evento',
                        templateUrl: "src/modules/evento/evento.tpl.html"
                    })
                    .state('dashboard.eventociudad', {
                        url: '/eventociudad',
                        templateUrl: "src/modules/eventociudad/eventociudad.tpl.html"
                    })
                    .state('dashboard.timeline', {
                        url: '/timeline',
                        templateUrl: "src/modules/timeline/timeline.tpl.html"
                    })
                    .state('dashboard.lista', {
                        url: '/lista',
                        templateUrl: "src/modules/lista/lista.tpl.html"
                    })
                    .state('dashboard.perfil',{
                        url:'/perfil',
                        templateUrl:"src/modules/perfil/perfil.tpl.html"
                    })
                    .state('dashboard.perfil.recuerdo',{   
                        url:'/recuerdo',
                        templateUrl: "src/modules/perfil/recuerdo.tpl.html" 
                    })
                    .state('dashboard.perfil.infogeneral',{   
                        url:'/recuerdo/infogeneral',
                        templateUrl: "src/modules/perfil/datosPersonales.tpl.html" 
                    })
                    .state('dashboard.mapa',{
                                url:'/mapa',
                        templateUrl:"src/modules/mapa/mapa.tpl.html"
                    })
                    .state('dashboard.login',{
                                url:'/login',
                        templateUrl:"src/modules/login/login.tpl.html"
                    })
                    .state('dashboard.registro',{
                                url:'/registro',
                        templateUrl:"src/modules/registro/registro.tpl.html"
                    })
                    .state('landing',{
                                url:'/landing',
                        templateUrl:"src/modules/landing/landing.tpl.html"
                    })
                    .state('dashboard.itinerario',{
                                url:'/itinerario',
                        templateUrl:"src/modules/itinerario/itinerario.tpl.html"
                    })
                    .state('dashboard',{
                                url:'/dashboard',
                        templateUrl:"src/modules/dashboard/dashboard.tpl.html"
                    })
        }]);
})(window.angular);
