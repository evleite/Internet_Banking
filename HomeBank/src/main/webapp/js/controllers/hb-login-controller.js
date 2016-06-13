'use strict';

angular.module('homeBankApp').controller(
    'HBLoginCtrl',
    function ($rootScope, $scope, HBLoginService, $location, $httpParamSerializer) {
    	window.sessionStorage.clear();
    	$(".loged-in-user > .username").html("");
    	
        $scope.login = function () {
        	$scope.user = $scope.username;
        	$scope.pass = $scope.password;
        	$scope.username = "";
        	$scope.password = "";
        	$scope.loginFailed = false;
            $scope.errorMessage = "";
            $rootScope.session = {}
        	HBLoginService.login(
        		$httpParamSerializer({username: $scope.user, password: $scope.pass}),
                function success(data) {
                    console.log('Login succeeded:', data);
                    
                    window.sessionStorage.login = data.success;
                    window.sessionStorage.username = data.username;
                    window.sessionStorage.token = data.token;
                    
                    console.log('Seesion:', window.sessionStorage);
                    
                    $location.path("/main");
                },
                function err(err) {
                	console.log('Login failed:', err.data.error);
                    
                	window.sessionStorage.login = err.data.success;
                	
                	$scope.loginFailed = true;
                    $scope.loginError = err.data.error;
                });
        };
    });
