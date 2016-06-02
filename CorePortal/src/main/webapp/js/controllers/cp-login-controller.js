'use strict';

angular.module('corePortalApp').controller(
    'CPLoginCtrl',
    function ($scope, CPLoginService, $location, $httpParamSerializer) {

        $scope.login = function () {
        	CPLoginService.login(
        		$httpParamSerializer({username: $scope.username, password: $scope.password}),
                function success(data) {
                    console.log('Login succeeded.', data);
                    $location.path("/main");
                },
                function err(data) {
                	console.log('Login failed!', data);
                    alert('Username or password incorect. Login failed!');
                });
        };
    });
