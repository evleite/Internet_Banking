'use strict';

angular.module('corePortalApp').controller(
    'CPLoginCtrl',
    function ($rootScope, $scope, CPLoginService, $location, $httpParamSerializer) {

        $scope.login = function () {
        	$(".header").removeClass("hidden");
        	
        	$scope.user = $scope.username;
        	$scope.pass = $scope.password;
        	$scope.username = "";
        	$scope.password = "";
        	$scope.loginFailed = false;
            $scope.errorMessage = "";
            $rootScope.session = {}
        	CPLoginService.login(
        		$httpParamSerializer({username: $scope.user, password: $scope.pass}),
                function success(data) {
                    console.log('Login succeeded:', data);
                    
                    $rootScope.session.login = data.success;
                    $rootScope.session.username = data.username;
                    $rootScope.session.token = data.token;
                    
                    console.log('Seesion:', $rootScope.session);
                    
                    $(".header").addClass("hidden");
                    
                    $location.path("/main");
                },
                function err(err) {
                	console.log('Login failed:', err.data.error);
                    
                	$rootScope.seesion.login = data.success;
                	
                	console.log('Seesion:', $rootScope.session);
                	
                	$scope.loginFailed = true;
                    $scope.loginError = err.data.error;
                });
        };
    });
