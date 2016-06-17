'use strict';

angular.module('homeBankApp').factory(
		'HBModalFactory', 
		function ($uibModal, $rootScope, HBUserHBService) {
			return {
				errorModal: function (errorMessage) {
					console.log(errorMessage);
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/hb-error-modal.html',
					    controller: 'HBErrorModelCtrl',
					    /*size: size,*/
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { 
					    	errorMessage: function () {
					    		return errorMessage;
					    	} 
					    }
					});

					modalInstance.result.then(function () {

					}, function () {

					});	
				},
				
				infoModal: function (infoMessage) {
					console.log(infoMessage);
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/hb-info-modal.html',
					    controller: 'HBInfoModelCtrl',
					    /*size: size,*/
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { 
					    	infoMessage: function () {
					    		return infoMessage;
					    	} 
					    }
					});

					modalInstance.result.then(function () {
						
					}, function () {
						
					});	
				},
				
				settings: function (user) {
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/hb-settings-modal.html',
					    controller: 'HBSettingsModelCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { 
					    	user: function () {
					    		return user;
					    	} 
					    }
					});

					modalInstance.result.then(function (user) {
						
					}, function () {

					});			               
				},
				
				changePassword: function (user) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/hb-change-password.html',
					    controller: 'HBChangePasswordCtrl',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: {
					    	user: function () {
					    		return user;
					    	},					    	
					    }
					});

					modalInstance.result.then(function () {
						
					}, function () {
						
					});	
				},
				
				transactionDetails: function (transaction) {
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/hb-transaction-details-modal.html',
					    controller: 'HBTransactionDetailsCtrl',
					    /*size: size,*/
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { 
					    	transaction: function () {
					    		return transaction;
					    	} 
					    }
					});

					modalInstance.result.then(function () {

					}, function () {

					});	
				},
				
				internalPayment: function (products) {
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/hb-internal-payment.html',
					    controller: 'HBInternalPaymentCtrl',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { 
					    	products: function () {
					    		return products;
					    	} 
					    }
					});

					modalInstance.result.then(function (user) {
						
					}, function () {

					});			               
				},
				
				currentToSavingPayment: function (currentAccounts, savingAccounts) {
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/hb-current-saving-payment.html',
					    controller: 'HBCurrentSavingPaymentCtrl',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { 
					    	currentAccounts: function () {
					    		return currentAccounts;
					    	},
					    	savingAccounts: function () {
					    		return savingAccounts;
					    	}
					    }
					});

					modalInstance.result.then(function (user) {
						
					}, function () {

					});			               
				},
				
				savingToCurrentPayment: function (savingAccounts, currentAccounts) {
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/hb-saving-current-payment.html',
					    controller: 'HBSavingCurrentPaymentCtrl',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { 
					    	savingAccounts: function () {
					    		return savingAccounts;
					    	},
					    	currentAccounts: function () {
					    		return currentAccounts;
					    	}
					    }
					});

					modalInstance.result.then(function (user) {
						
					}, function () {

					});			               
				},
				
				currentToCreditPayment: function (currentAccounts, creditCards) {
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/hb-current-credit-payment.html',
					    controller: 'HBCurrentCreditPaymentCtrl',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { 
					    	currentAccounts: function () {
					    		return currentAccounts;
					    	},
					    	creditCards: function () {
					    		return creditCards;
					    	}
					    }
					});

					modalInstance.result.then(function (user) {
						
					}, function () {

					});			               
				},
				
				creditToCurrentPayment: function (creditCards, currentAccounts) {
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/hb-credit-current-payment.html',
					    controller: 'HBCreditCurrentPaymentCtrl',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { 
					    	creditCards: function () {
					    		return creditCards;
					    	},
					    	currentAccounts: function () {
					    		return currentAccounts;
					    	}
					    }
					});

					modalInstance.result.then(function (user) {
						
					}, function () {

					});			               
				},
				
			};
		}		
);