(function (ng) {

    var mod = ng.module("mainApp", ["ui.router"]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/evento");
            $stateProvider
                    .state('evento', {
                        url: '/evento',
                        templateUrl: "src/modules/evento/evento.tpl.html"
                    })
                    .state('perfil',{
                        url:'/perfil',
                        templateUrl:"src/modules/perfil/perfil.tpl.html"
                    })
                            .state('mapa',{
                                url:'/mapa',
                        templateUrl:"src/modules/mapa/mapa.tpl.html"
                    })
        }]);
})(window.angular);
