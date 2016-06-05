'use strict';

angular.module('corePortalApp').controller(
    'CPMainCtrl',
    function ($rootScope, $scope, 
    		CPAccountService, CPRateService, CPCommisionService, CPUserCPService, CPAccountAssignementService, CPCardService,
    		CPCardAssignementService, CPExchangeRateService, CPUserHBService,
    		$location, $httpParamSerializer) {
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
                            	
                            	$scope.hideAdminLists();
                            	
                            	if (err.data.errorCode == 666){
                            		window.sessionStorage.clear();
                            		alert("Your session has expired. Please login again.");
                            		$location.path("/login");
                            	}
                            	
                            	alert("Backend error");
                            });
            		
                	break;
            	case "accountAssignement":
            		$scope.selectedNode = item;
            		
            		CPAccountAssignementService.getAccountAssignementList(
                    		$httpParamSerializer({token: window.sessionStorage.token}),
                            function success(data) {
                                console.log('AccountAssignement list:', data);
                                
                                $scope.accountAssignementList = data.accountAssignementList;
                                
                                $scope.hideAdminLists();
                                $scope.showAccountAssignementList = true;
                            },
                            function err(err) {
                            	console.log('Get accountassignementlist failed:', err);
                            	
                            	$scope.hideAdminLists();
                            	
                            	if (err.data.errorCode == 666){
                            		window.sessionStorage.clear();
                            		alert("Your session has expired. Please login again.");
                            		$location.path("/login");
                            	}
                            	
                            	alert("Backend error");
                            });
            		
            		break;
            	case "card":
            		$scope.selectedNode = item;
            		
            		CPCardService.getCardList(
                    		$httpParamSerializer({token: window.sessionStorage.token}),
                            function success(data) {
                                console.log('Card list:', data);
                                
                                $scope.cardList = data.cardList;
                                
                                $scope.hideAdminLists();
                                $scope.showCardList = true;
                            },
                            function err(err) {
                            	console.log('Get cardList failed:', err);
                            	
                            	$scope.hideAdminLists();
                            	
                            	if (err.data.errorCode == 666){
                            		window.sessionStorage.clear();
                            		alert("Your session has expired. Please login again.");
                            		$location.path("/login");
                            	}
                            	
                            	alert("Backend error");
                            });
            		
                	break;
            	case "cardAssignement":
            		$scope.selectedNode = item;
            		
            		CPCardAssignementService.getCardAssignementList(
                    		$httpParamSerializer({token: window.sessionStorage.token}),
                            function success(data) {
                                console.log('CardAssignement list:', data);
                                
                                $scope.cardAssignementList = data.cardAssignementList;
                                
                                $scope.hideAdminLists();
                                $scope.showCardAssignementList = true;
                            },
                            function err(err) {
                            	console.log('Get cardAssignementList failed:', err);
                            	
                            	$scope.hideAdminLists();
                            	
                            	if (err.data.errorCode == 666){
                            		window.sessionStorage.clear();
                            		alert("Your session has expired. Please login again.");
                            		$location.path("/login");
                            	}
                            	
                            	alert("Backend error");
                            });
            		
                	break;
            	case "commision":
            		$scope.selectedNode = item;
            		
            		CPCommisionService.getCommisionList(
                    		$httpParamSerializer({token: window.sessionStorage.token}),
                            function success(data) {
                                console.log('Commision list:', data);
                                
                                $scope.commisionList = data.commisionList;
                                
                                $scope.hideAdminLists();
                                $scope.showCommisionList = true;
                            },
                            function err(err) {
                            	console.log('Get commisionlist failed:', err);
                            	
                            	$scope.hideAdminLists();
                            	
                            	if (err.data.errorCode == 666){
                            		window.sessionStorage.clear();
                            		alert("Your session has expired. Please login again.");
                            		$location.path("/login");
                            	}
                            	
                            	alert("Backend error");
                            });
            		
                	break;
            	case "exchangeRate":
            		$scope.selectedNode = item;
            		
            		CPExchangeRateService.getExchangeRateList(
                    		$httpParamSerializer({token: window.sessionStorage.token}),
                            function success(data) {
                                console.log('ExchangeRate list:', data);
                                
                                $scope.exchangeRateList = data.exchangeRateList;
                                
                                $scope.hideAdminLists();
                                $scope.showExchangeRateList = true;
                            },
                            function err(err) {
                            	console.log('Get exchangeratelist failed:', err);
                            	
                            	$scope.hideAdminLists();
                            	
                            	if (err.data.errorCode == 666){
                            		window.sessionStorage.clear();
                            		alert("Your session has expired. Please login again.");
                            		$location.path("/login");
                            	}
                            	
                            	alert("Backend error");
                            });
            		
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
                            	
                            	$scope.hideAdminLists();
                            	
                            	if (err.data.errorCode == 666){
                            		window.sessionStorage.clear();
                            		alert("Your session has expired. Please login again.");
                            		$location.path("/login");
                            	}
                            	
                            	alert("Backend error");
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
            		
            		CPUserCPService.getUserList(
                    		$httpParamSerializer({token: window.sessionStorage.token}),
                            function success(data) {
                                console.log('CP user list:', data);
                                
                                $scope.userCPList = data.userCPList;
                                
                                $scope.hideAdminLists();
                                $scope.showUserCPList = true;
                            },
                            function err(err) {
                            	console.log('Get CP userlist failed:', err);
                            	
                            	$scope.hideAdminLists();
                            	
                            	if (err.data.errorCode == 666){
                            		window.sessionStorage.clear();
                            		alert("Your session has expired. Please login again.");
                            		$location.path("/login");
                            	}
                            	
                            	alert("Backend error");
                            });
            		
                	break;
            	case "userHB":
            		$scope.selectedNode = item;
            		
            		CPUserHBService.getUserList(
                    		$httpParamSerializer({token: window.sessionStorage.token}),
                            function success(data) {
                                console.log('HB user list:', data);
                                
                                $scope.userHBList = data.userHBList;
                                
                                $scope.hideAdminLists();
                                $scope.showUserHBList = true;
                            },
                            function err(err) {
                            	console.log('Get HB userlist failed:', err);
                            	
                            	$scope.hideAdminLists();
                            	
                            	if (err.data.errorCode == 666){
                            		window.sessionStorage.clear();
                            		alert("Your session has expired. Please login again.");
                            		$location.path("/login");
                            	}
                            	
                            	alert("Backend error");
                            });
            		
                	break;
            	default:
        	}
        };
        
        $scope.main("account");
    });
