'use strict';

angular.module('corePortalApp').controller(
    'CPMainCtrl',
    function ($rootScope, $scope, CPAccountService, CPRateService, $location, $httpParamSerializer) {
    	if (window.sessionStorage.login == "true"){
    		$(".loged-in-user > h3").html(window.sessionStorage.username);
    	} else {
    		window.sessionStorage.clear();
    		$location.path("/login");
    	}
    	
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
    	
    	$scope.hideAdminLists = function (){
    		$scope.showAccountList = false;
    		$scope.showAccountAssignementList = false;
    		$scope.showCardList = false;
    		$scope.showCardAssignementList = false;
    		$scope.showCommisionList = false;
    		$scope.showExchangeRateList = false;
    		$scope.showRateList = false;
    		$scope.showTokenList = false;
    		$scope.showTokenAssignementList = false;
    		$scope.showUserCPList = false;
    		$scope.showUserHBList = false;
    	}
    	
        $scope.main = function (item) {
        	switch (item) {
            	case "account":
            		$scope.selectedNode = item;
            		
            		CPAccountService.getAccountList(
                    		$httpParamSerializer({token: window.sessionStorage.token}),
                            function success(data) {
                                console.log('Account list:', data);
                                
                                $scope.accountList = data.accountList;
                                
                                $scope.hideAdminLists();
                                $scope.showAccountList = true;
                            },
                            function err(err) {
                            	console.log('Get accountlist failed:', err);
                            });
            		
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
            		
            		CPRateService.getRateList(
                    		$httpParamSerializer({token: window.sessionStorage.token}),
                            function success(data) {
                                console.log('Rate list:', data);
                                
                                $scope.rateList = data.rateList;
                                
                                $scope.hideAdminLists();
                                $scope.showRateList = true;
                            },
                            function err(err) {
                            	console.log('Get ratelist failed:', err);
                            });
            		
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
        };
    });
