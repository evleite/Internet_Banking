'use strict';

angular.module('corePortalApp').controller(
    'CPMainCtrl',
    function ($rootScope, $scope, CPMainService, $location, $httpParamSerializer) {
    	$(".loged-in-user > h3").html($rootScope.session.username);
    	
    	$scope.adminNav = [];
    	$scope.adminNav.push.apply($scope.adminNav, [
    			{key:"account", value:"Accounts"},
    			{key:"accountAssignement", value:"Account Assignements"},
    			{key:"card", value:"Cards"},
    			{key:"cardAssignement", value:"Card Assignements"},
    			{key:"commision", value:"Commisions"},
    			{key:"exchangeRate", value:"Exchange Rates"},
    			{key:"rate", value:"Rates"},
    			{key:"token", value:"Tokens"},
    			{key:"tokenAssignement", value:"Token Assignements"},
    			{key:"userCP", value:"Core Portal Users"},
    			{key:"userHB", value:"Home Bank Users"}
    	]);
    	
    	$scope.selectedNode = "account";

        $scope.main = function (item) {
        	switch (item) {
            	case "account":
            		$scope.selectedNode = item;
                	break;
            	case "accountAssignement":
            		$scope.selectedNode = item;
            		break;
            	case "card":
            		$scope.selectedNode = item;
                	break;
            	case "cardAssignement":
            		$scope.selectedNode = item;
                	break;
            	case "commision":
            		$scope.selectedNode = item;
                	break;
            	case "exchangeRate":
            		$scope.selectedNode = item;
                	break;
            	case "rate":
            		$scope.selectedNode = item;
                	break;
            	case "token":
            		$scope.selectedNode = item;
                	break;
            	case "tokenAssignement":
            		$scope.selectedNode = item;
                	break;
            	case "userCP":
            		$scope.selectedNode = item;
                	break;
            	case "userHB":
            		$scope.selectedNode = item;
                	break;
            	default:
        	}
        	
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
