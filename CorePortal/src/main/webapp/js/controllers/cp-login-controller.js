'use strict';

angular.module('corePortalApp').controller(
    'CPLoginCtrl',
    function ($scope, CPLoginService, $location) {

        $scope.login = function () {
        	CPLoginService.login(
                {username: $scope.username, password: $scope.password},
                function success() {
                    console.log('Login succeeded.');
                    $location.path("/main");
                },
                function err() {
                    alert('Username or password incorect. Login faild!');
                });
        };
    });
