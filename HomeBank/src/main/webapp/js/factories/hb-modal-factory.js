'use strict';

angular.module('homeBankApp').factory(
		'HBModalFactory', 
		function ($uibModal, $rootScope) {
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
					    resolve: { /* Parrameters passed as locals to controller*/
					    	errorMessage: function () {
					    		return errorMessage;
					    	} 
					    }
					});

					modalInstance.result.then(function (/* parametrii de aici seprimesc de la $uibModalInstance.close() */) {
						/* When $uibModalInstance.close()*/
					}, function () {
						/* When $uibModalInstance.dismiss()*/
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
					    resolve: { /* Parrameters passed as locals to controller*/
					    	infoMessage: function () {
					    		return infoMessage;
					    	} 
					    }
					});

					modalInstance.result.then(function () {
						
					}, function () {
						
					});	
				},
				
			};
		}		
);