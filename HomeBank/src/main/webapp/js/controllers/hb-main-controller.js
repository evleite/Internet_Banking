'use strict';

angular.module('homeBankApp').controller(
    'HBMainCtrl',
    function ($rootScope, $scope, $location, $httpParamSerializer, $q, HBModalFactory, HBProductService) {
    	if (window.sessionStorage.login == "true"){
    		$(".loged-in-user > .username").html(window.sessionStorage.username);
    	} else {
    		window.sessionStorage.clear();
    		$location.path("/login");
    	}
    	$scope.username = window.sessionStorage.username;
    	
    	/* Nav bar */    	
    	/* TODO: Payments */
    	$scope.homePage = function (){
    		window.location.reload();
    	}
    	$scope.borrowingPage = function (){
    		$location.path("/borrowing");
    	}
    	$scope.bankingPage = function (){
    		$location.path("/banking");
    	}
    	$scope.savingsPage = function (){
    		$location.path("/savings");
    	}
    	/* TODO: Settings tab */
    	$scope.logOut = function(){
    		window.sessionStorage.clear();
    		$location.path("/login");
    	}
    	
    	/* Populate home page */
    	$scope.populate = function(){
    		$scope.credidCards = [];
    		$scope.currentAccounts = []; 
    		$scope.savingsAccount = [];
    		
    		HBProductService.getAllProducts(
    				$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('Product list:', data);
                        
                        var i;
                        for (i=0; i<data.productList.length; i++){
                        	var product = data.productList[i];
                        	if (product.account.acc_type == "CREDIT_ACCOUNT"){
                        		$scope.credidCards.push(product);
                        	} else if (product.account.acc_type == "CURRENT_ACOUNT"){
                        		$scope.currentAccounts.push(product);
                        	} else if (product.account.acc_type == "SAVING_ACCOUNT"){
                        		$scope.savingsAccount.push(product);
                        	}
                        }
                    },
                    function err(err) {
                    	console.log('Get productlist failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		HBModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		HBModalFactory.errorModal("Backend error");
                    	}
                    });
    		
    	}
    	
    	$scope.populate();
});
