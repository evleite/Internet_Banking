'use strict';

angular.module('corePortalApp').controller(
    'CPMainCtrl',
    function ($rootScope, $scope, 
    		CPAccountService, CPRateService, CPCommisionService, CPUserCPService, CPAccountAssignementService,
    		CPCardService, CPCardAssignementService, CPExchangeRateService, CPUserHBService,
    		CPModalFactory,
    		$location, $httpParamSerializer, $q) {
    	if (window.sessionStorage.login == "true"){
    		$(".loged-in-user > .username").html(window.sessionStorage.username);
    	} else {
    		window.sessionStorage.clear();
    		$location.path("/login");
    	}
    	
    	$scope.logOut = function(){
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
  	    	
        /* Account model */
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
    	
        $scope.addAccount = function (){
        	$rootScope.main = $scope.main;
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
        	$rootScope.main = $scope.main;
        	CPModalFactory.editAccount(account, $scope.commisionList, $scope.rateList);
        	        	
        }
        
        /* AccountAssignement model */
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
        
        $scope.addAccountAssignement = function(){
        	$rootScope.main = $scope.main;
        	CPModalFactory.addAccountAssignement($scope.accountList, $scope.userHBList);

        }
        
        $scope.deleteAccountAssignement = function(id, index){
        	CPAccountAssignementService.deleteAccountAssignement(
    				$httpParamSerializer({token: window.sessionStorage.token, id: id}),
                    function success(data) {
                        console.log('AccountAssignement deleted succesfully:', data);
                        
                        $scope.accountAssignementList.splice(index, 1);
                        
                    },
                    function err(err) {
                    	console.log('Delete accountAssignement failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
        	
        }
        
        /* Card model */
        $scope.getCardList = function(){
    		CPCardService.getCardList(
            		$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('Card list:', data);
                        
                        $scope.cardList = data.cardList;
                        $scope.cardTypeList = data.cardTypeList;
                        
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
        
        $scope.addCard = function (){
        	$rootScope.main = $scope.main;
        	CPModalFactory.addCard($scope.cardTypeList);
        	        	
        }
        
        $scope.deleteCard = function(id, index){
        	CPCardService.deleteCard(
    				$httpParamSerializer({token: window.sessionStorage.token, id: id}),
                    function success(data) {
                        console.log('Card deleted succesfully:', data);
                        
                        $scope.cardList.splice(index, 1);
                        
                    },
                    function err(err) {
                    	console.log('Delete card failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
        	
        }
        
        $scope.editCard = function(card, index){  	
        	$rootScope.main = $scope.main;
        	CPModalFactory.editCard(card);
        	        	
        }
        
        /* CardAssignement model */
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
        
        $scope.addCardAssignement = function(){
        	$rootScope.main = $scope.main;
        	CPModalFactory.addCardAssignement($scope.accountList, $scope.userHBList, $scope.cardList);

        }
        
        $scope.deleteCardAssignement = function(id, index){
        	CPCardAssignementService.deleteCardAssignement(
    				$httpParamSerializer({token: window.sessionStorage.token, id: id}),
                    function success(data) {
                        console.log('CardAssignement deleted succesfully:', data);
                        
                        $scope.cardAssignementList.splice(index, 1);
                        
                    },
                    function err(err) {
                    	console.log('Delete cardAssignement failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
        	
        }
        
        /* Commision model */
        $scope.getCommisionList = function(){
    		CPCommisionService.getCommisionList(
            		$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('Commision list:', data);
                        
                        $scope.commisionList = data.commisionList;
                        $scope.commisionTypeList = data.commisionTypeList;
                        
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
        
        $scope.addCommision = function (){
        	$rootScope.main = $scope.main;
        	CPModalFactory.addCommision($scope.commisionTypeList);
        	        	
        }
        
        $scope.deleteCommision = function(id, index){
        	CPCommisionService.deleteCommision(
    				$httpParamSerializer({token: window.sessionStorage.token, id: id}),
                    function success(data) {
                        console.log('Commision deleted succesfully:', data);
                        
                        $scope.commisionList.splice(index, 1);
                        
                    },
                    function err(err) {
                    	console.log('Delete commision failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
        	
        }
        
        $scope.editCommision = function(commision, index){  	
        	$rootScope.main = $scope.main;
        	CPModalFactory.editCommision(commision);
        	        	
        }
        
        /* ExchangeReates model */
        $scope.getExchangeRatesList = function(){
    		CPExchangeRateService.getExchangeRateList(
            		$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('ExchangeRate list:', data);
                        
                        $scope.exchangeRateList = data.exchangeRateList;
                        $scope.currenciesList = data.currenciesList;
                        
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
        $scope.addExchangeRate = function (){
        	$rootScope.main = $scope.main;
        	CPModalFactory.addExchangeRate($scope.currenciesList);
        	        	
        }
        
        $scope.deleteExchangeRate = function(id, index){
        	CPExchangeRateService.deleteExchangeRate(
    				$httpParamSerializer({token: window.sessionStorage.token, id: id}),
                    function success(data) {
                        console.log('ExchangeRate deleted succesfully:', data);
                        
                        $scope.exchangeRateList.splice(index, 1);
                        
                    },
                    function err(err) {
                    	console.log('Delete exchangeRate failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
        	
        }
        
        $scope.editExchangeRate = function(exchangeRate, index){  	
        	$rootScope.main = $scope.main;
        	CPModalFactory.editExchangeRate(exchangeRate);
        	        	
        }
        
        /* Rate model */
        $scope.getRateList = function(){
    		CPRateService.getRateList(
            		$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('Rate list:', data);
                        
                        $scope.rateList = data.rateList;
                        $scope.rateTypeList = data.rateTypeList;
                        
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
        $scope.addRate = function (){
        	$rootScope.main = $scope.main;
        	CPModalFactory.addRate($scope.rateTypeList);
        	        	
        }
        
        $scope.deleteRate = function(id, index){
        	CPRateService.deleteRate(
    				$httpParamSerializer({token: window.sessionStorage.token, id: id}),
                    function success(data) {
                        console.log('Rate deleted succesfully:', data);
                        
                        $scope.rateList.splice(index, 1);
                        
                    },
                    function err(err) {
                    	console.log('Delete rate failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
        	
        }
        
        $scope.editRate = function(rate, index){  	
        	$rootScope.main = $scope.main;
        	CPModalFactory.editRate(rate);
        	        	
        }
        
        /* CPUser model */
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
        $scope.addCPUser = function (){
        	$rootScope.main = $scope.main;
        	CPModalFactory.addCPUser();
        	        	
        }
        
        $scope.deleteCPUser = function(id, index){
        	CPUserCPService.deleteCPUser(
    				$httpParamSerializer({token: window.sessionStorage.token, id: id}),
                    function success(data) {
                        console.log('CP User deleted succesfully:', data);
                        
                        $scope.userCPList.splice(index, 1);
                        
                    },
                    function err(err) {
                    	console.log('Delete CP user failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
        	
        }
        
        $scope.editCPUser = function(user, index){  	
        	$rootScope.main = $scope.main;
        	CPModalFactory.editCPUser(user);
        	        	
        }
        
        /* HBUser model */
        $scope.getHBUserList = function(){
    		CPUserHBService.getUserList(
            		$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('HB user list:', data);
                        
                        $scope.userHBList = data.userHBList;
                        $scope.authenticationTypeList = data.authenticationTypeList;
                        
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
        $scope.addHBUser = function (){
        	$rootScope.main = $scope.main;
        	CPModalFactory.addHBUser($scope.authenticationTypeList);
        	        	
        }
        
        $scope.deleteHBUser = function(id, index){
        	CPUserHBService.deleteHBUser(
    				$httpParamSerializer({token: window.sessionStorage.token, id: id}),
                    function success(data) {
                        console.log('HB User deleted succesfully:', data);
                        
                        $scope.userHBList.splice(index, 1);
                        
                    },
                    function err(err) {
                    	console.log('Delete HB user failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		CPModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		CPModalFactory.errorModal("Backend error");
                    	}
                    });
        	
        }
        
        $scope.editHBUser = function(user, index){  	
        	$rootScope.main = $scope.main;
        	CPModalFactory.editHBUser(user, $scope.authenticationTypeList);
        	        	
        }
        
        /* Main */
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
            		$scope.getAccountList();
            		$scope.getHBUserList();
            		
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
            		$scope.getAccountList();
            		$scope.getHBUserList();
            		$scope.getCardList();
            		
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
        
        $scope.main("account");
    });
