'use strict';

angular.module('homeBankApp').controller(
    'HBMainCtrl',
    function ($rootScope, $scope, $location, $httpParamSerializer, $q, HBModalFactory, HBProductService, HBUserHBService) {
    	if (window.sessionStorage.login == "true"){
    		$(".loged-in-user > .username").html(window.sessionStorage.username);
    	} else {
    		window.sessionStorage.clear();
    		$location.path("/login");
    	}
    	$scope.username = window.sessionStorage.username;
    	
    	/* Get user products */
    	$scope.getProducts = function(){
    		$scope.creditCards = [];
    		$scope.currentAccounts = []; 
    		$scope.savingsAccounts = [];
    		
    		HBProductService.getAllProducts(
    				$httpParamSerializer({token: window.sessionStorage.token}),
                    function success(data) {
                        console.log('Product list:', data);
                        $scope.allProducts = data.productList;
                        
                        var i;
                        for (i=0; i<data.productList.length; i++){
                        	var product = data.productList[i];
                        	if (product.account.acc_type == "CREDIT_ACCOUNT"){
                        		$scope.creditCards.push(product);
                        	} else if (product.account.acc_type == "CURRENT_ACOUNT"){
                        		$scope.currentAccounts.push(product);
                        	} else if (product.account.acc_type == "SAVING_ACCOUNT"){
                        		$scope.savingsAccounts.push(product);
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
    	
    	$scope.getProducts();
    	
    	/* Nav bar */    	
    	$scope.homePage = function (){
    		window.location.reload();
    	}
    	$scope.borrowingPage = function (selectedProduct){
    		$rootScope.selectedProduct = selectedProduct;
    		$location.path("/borrowing");
    	}
    	$scope.bankingPage = function (selectedProduct){
    		$rootScope.selectedProduct = selectedProduct;
    		$location.path("/banking");
    	}
    	$scope.savingsPage = function (selectedProduct){
    		$rootScope.selectedProduct = selectedProduct;
    		$location.path("/savings");
    	}
    	$scope.settings = function () {
    		HBUserHBService.getUser(
                   	$httpParamSerializer(
                   			{
                   				token: window.sessionStorage.token
                   			}
                   	),
                    function success(data) {
                   		console.log('User succesfully loaded:', data);
                   		HBModalFactory.settings(data.user);
                    },
                    function err(err) {
                       	console.log('Failed to get user:', err);
                            	
                       	if (err.data.errorCode == 666){
                       		window.sessionStorage.clear();
                       		HBModalFactory.errorModal("Your session has expired. Please login again.");
                       		$location.path("/login");
                       	} else if (err.data.errorCode == 600) {
                       		HBModalFactory.errorModal(err.data.error);
                       		
                       	} else {                            	
                       		HBModalFactory.errorModal("Backend error");
                       	}
                    }
                );
    	}
    	$scope.logOut = function(){
    		window.sessionStorage.clear();
    		$location.path("/login");
    	}
    	
    	/* Payments */
    	$scope.internalPayment = function() {
    		HBModalFactory.internalPayment($scope.allProducts);
    	}
    	$scope.currentToSavingPayment = function() {
    		HBModalFactory.currentToSavingPayment($scope.currentAccounts, $scope.savingsAccounts);
    	}
    	$scope.savingToCurrentPayment = function() {
    		HBModalFactory.savingToCurrentPayment($scope.savingsAccounts, $scope.currentAccounts);
    	}
    	$scope.currentToCreditPayment = function() {
    		HBModalFactory.currentToCreditPayment($scope.currentAccounts, $scope.creditCards );
    	}
    	$scope.creditToCurrentPayment = function() {
    		HBModalFactory.creditToCurrentPayment($scope.creditCards, $scope.currentAccounts);
    	}
});
