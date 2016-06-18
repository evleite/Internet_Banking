'use strict';

angular.module('homeBankApp').controller(
    'HBBorrowingCtrl',
    function ($rootScope, $scope, $location, $httpParamSerializer, $q, $route, 
    		HBModalFactory, HBProductService, HBUserHBService, HBTransactionService, HBStatementService) {
    	
    	/* Get transaction for selected product */
    	$scope.getTransactions = function(IBAN){
    		HBTransactionService.getProductTransactions(
    				$httpParamSerializer({token: window.sessionStorage.token, 
    									   IBAN: IBAN}),
                    function success(data) {
                        console.log('Transaction list:', data);

                        $scope.transactionList = data.transactionList;

    				},
                    function err(err) {
                    	console.log('Get transactionlist failed:', err);
                    	
                    	if (err.data.errorCode == 666){
                    		window.sessionStorage.clear();
                    		HBModalFactory.errorModal("Your session has expired. Please login again.");
                    		$location.path("/login");
                    	} else {                            	
                    		HBModalFactory.errorModal("Backend error");
                    	}
                    });
    	}    	

    	$scope.getAccountCardNo = function(iban){
    		var i;
    		for (i=0;i<$scope.creditCards.length; i++){
    			var product = $scope.creditCards[i];
    			if (product.account.IBAN == iban){
    				return product.card.card_no;
    			}
    		}
    		return $scope.creditCards[i].card.card_no;
    	}
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
                        
                        if ($rootScope.selectedProduct){
                    		$scope.selectedProduct = $rootScope.selectedProduct;
                    	} else {
                    		$scope.selectedProduct = $scope.creditCards[0].account.IBAN;
                    	}
                        $scope.selectedCard = $scope.getAccountCardNo($scope.selectedProduct);
                        
                        $scope.getTransactions($scope.selectedProduct);
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
    	
    	/* Open transaction details */
    	$scope.transactionDetails = function(transaction){
    		HBModalFactory.transactionDetails(transaction);
    	}
    	
    	/* Nav bar */    	
    	$scope.homePage = function (){
    		$location.path("/main");
    	}
    	$scope.borrowingPage = function (selectedProduct){
    		$rootScope.selectedProduct = selectedProduct;
    		$route.reload();
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
    	
    	/* Print statement */
    	$scope.printStatement = function() {
    		HBStatementService.printStatement(
                   	$httpParamSerializer(
                   			{
                   				token: window.sessionStorage.token,
                   				iban: $scope.selectedProduct
                   			}
                   	),
                    function success(data) {
                   		console.log('Statement succesfully generated:', data);
                   		
                   		var file = new Blob([data], {type: 'application/pdf'});
                   	    var fileURL = URL.createObjectURL(file);
                   	    window.open(fileURL);
                   		
                    },
                    function err(err) {
                       	console.log('Failed to generate statement:', err);
                       	                            	
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
});
