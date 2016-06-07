'use strict';

angular.module('corePortalApp').controller(
    'CPMainCtrl',
    function ($rootScope, $scope, 
    		CPAccountService, CPRateService, CPCommisionService, CPUserCPService, CPAccountAssignementService,
    		CPCardService, CPCardAssignementService, CPExchangeRateService, CPUserHBService,
    		CPModalFactory,
    		$location, $httpParamSerializer, $q) {
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
    	
    	$scope.getAccountList = function(){
    		CPAccountService.getAccountList(
    				$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('Account list:', data);
                        
                        $scope.accountList = data.accountList;
                        $scope.accountTypeList = data.accountTypeList;
                        $scope.currenciesList = data.currenciesList;
                        
                    },
                    function err(err) {
                    	console.log('Get accountlist failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
    	}
    	
    	$scope.getAccountAssignementList = function(){
    		CPAccountAssignementService.getAccountAssignementList(
            		$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('AccountAssignement list:', data);
                        
                        $scope.accountAssignementList = data.accountAssignementList;
                        
                    },
                    function err(err) {
                    	console.log('Get accountassignementlist failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
    	}
    	
    	$scope.getCardList = function(){
    		CPCardService.getCardList(
            		$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('Card list:', data);
                        
                        $scope.cardList = data.cardList;
                        
                    },
                    function err(err) {
                    	console.log('Get cardList failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
    	}
    	
    	$scope.getCardAssignementList = function(){
    		CPCardAssignementService.getCardAssignementList(
            		$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('CardAssignement list:', data);
                        
                        $scope.cardAssignementList = data.cardAssignementList;
                        
                    },
                    function err(err) {
                    	console.log('Get cardAssignementList failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
    	}
    	
    	$scope.getCommisionList = function(){
    		CPCommisionService.getCommisionList(
            		$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('Commision list:', data);
                        
                        $scope.commisionList = data.commisionList;
                        
                    },
                    function err(err) {
                    	console.log('Get commisionlist failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    	
                    });
    	}
    	
    	$scope.getExchangeRatesList = function(){
    		CPExchangeRateService.getExchangeRateList(
            		$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('ExchangeRate list:', data);
                        
                        $scope.exchangeRateList = data.exchangeRateList;
                        
                    },
                    function err(err) {
                    	console.log('Get exchangeratelist failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
    	}
    	
    	$scope.getRateList = function(){
    		CPRateService.getRateList(
            		$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('Rate list:', data);
                        
                        $scope.rateList = data.rateList;
                        
                    },
                    function err(err) {
                    	console.log('Get ratelist failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
    	}
    	
    	$scope.getCPUserList = function(){
    		CPUserCPService.getUserList(
            		$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('CP user list:', data);
                        
                        $scope.userCPList = data.userCPList;
                        
                    },
                    function err(err) {
                    	console.log('Get CP userlist failed:', err);
                    	
                    	$scope.hideAdminLists();
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
    	}
    	
    	$scope.getHBUserList = function(){
    		CPUserHBService.getUserList(
            		$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('HB user list:', data);
                        
                        $scope.userHBList = data.userHBList;
                        
                    },
                    function err(err) {
                    	console.log('Get HB userlist failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
    	}
    	
        $scope.main = function (item) {
        	switch (item) {
            	case "account":
            		$scope.selectedNode = item;
            		
            		$scope.getAccountList();
            		$scope.getCommisionList();
                	$scope.getRateList();
            		
            		$scope.hideAdminLists();
                    $scope.showAccountList = true;
            		
                	break;
            	case "accountAssignement":
            		$scope.selectedNode = item;
            		
            		$scope.getAccountAssignementList();
            		
            		$scope.hideAdminLists();
                    $scope.showAccountAssignementList = true;
            		
            		break;
            	case "card":
            		$scope.selectedNode = item;
            		
            		$scope.getCardList();
            		
            		$scope.hideAdminLists();
                    $scope.showCardList = true;
            		
                	break;
            	case "cardAssignement":
            		$scope.selectedNode = item;
            		
            		$scope.getCardAssignementList();
            		
            		$scope.hideAdminLists();
                    $scope.showCardAssignementList = true;
            		
                	break;
            	case "commision":
            		$scope.selectedNode = item;
            		
            		$scope.getCommisionList();
            		
            		$scope.hideAdminLists();
                    $scope.showCommisionList = true;
            		
                	break;
            	case "exchangeRate":
            		$scope.selectedNode = item;
            		
            		$scope.getExchangeRatesList();
            		
            		$scope.hideAdminLists();
                    $scope.showExchangeRateList = true;
            		
                	break;
            	case "rate":
            		$scope.selectedNode = item;
            		
            		$scope.getRateList();
            		
            		$scope.hideAdminLists();
                    $scope.showRateList = true;
            		
                	break;
            	case "token":
            		$scope.selectedNode = item;
            		
            		$scope.hideAdminLists();
            		
                	break;
            	case "tokenAssignement":
            		$scope.selectedNode = item;
            		
            		$scope.hideAdminLists();
            		
                	break;
            	case "userCP":
            		$scope.selectedNode = item;
            		
            		$scope.getCPUserList();
            		
            		$scope.hideAdminLists();
                    $scope.showUserCPList = true;
            		
                	break;
            	case "userHB":
            		$scope.selectedNode = item;
            		
            		$scope.getHBUserList();
            		
            		$scope.hideAdminLists();
                    $scope.showUserHBList = true;
            		
                	break;
            	default:
        	}
        };
        
        $scope.addAccount = function (){
        	
        	CPModalFactory.addAccount($scope.accountTypeList, $scope.currenciesList, $scope.commisionList, $scope.rateList);
        	        	
        }
        
        $scope.deleteAccount = function(id, index){
        	CPAccountService.deleteAccount(
    				$httpParamSerializer({token: window.sessionStorage.token, id: id}),
                    function success(data) {
                        console.log('Account deleted succesfully:', data);
                        
                        $scope.accountList.splice(index, 1);
                        
                    },
                    function err(err) {
                    	console.log('Delete account failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
        	
        }
        
        $scope.editAccount = function(account, index){  	
        	
        	CPModalFactory.editAccount(account, $scope.commisionList, $scope.rateList);
        	        	
        }
        
        $scope.main("account");
    });
