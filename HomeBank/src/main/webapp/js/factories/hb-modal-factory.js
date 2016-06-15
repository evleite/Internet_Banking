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
				
			};
		}		
);