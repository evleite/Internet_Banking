'use strict';

angular.module('corePortalApp').factory(
		'CPModalFactory', 
		function ($uibModal) {
			return {
				errorModal: function (errorMessage) {
					console.log(errorMessage);
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-error-modal.html',
					    controller: 'CPErrorModelCtrl',
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
				
				addAccount: function (accountTypeList, currenciesList, commisionList, rateList) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-add-account.html',
					    controller: 'CPAddAccountCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	accountTypeList: function () {
					    		return accountTypeList;
					    	},
					    	currenciesList: function () {
					    		return currenciesList;
					    	},
					    	rateList: function () {
					    		return rateList;
					    	},
					    	commisionList: function () {
					    		return commisionList;
					    	},
					    }
					});

					modalInstance.result.then(function () {

					}, function () {
						
					});	
				},
				
				editAccount: function (account, commisionList, rateList) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-edit-account.html',
					    controller: 'CPEditAccountCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	account: function () {
					    		return account;
					    	},					    	
					    	rateList: function () {
					    		return rateList;
					    	},
					    	commisionList: function () {
					    		return commisionList;
					    	},
					    }
					});

					modalInstance.result.then(function () {

					}, function () {
						
					});	
				},
				
				addAccountAssignement: function (accountList, userHBList) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-add-account-assignement.html',
					    controller: 'CPAddAccountAssignementCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	accountList: function () {
					    		return accountList;
					    	},
					    	userHBList: function () {
					    		return userHBList;
					    	}
					    }
					});

					modalInstance.result.then(function () {

					}, function () {
						
					});	
				},
				
				addCard: function (cardTypeList) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-add-card.html',
					    controller: 'CPAddCardCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	cardTypeList: function () {
					    		return cardTypeList;
					    	},
					    }
					});

					modalInstance.result.then(function () {

					}, function () {
						
					});	
				},
				
				editCard: function (card) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-edit-card.html',
					    controller: 'CPEditCardCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	card: function () {
					    		return card;
					    	},					    	
					    }
					});

					modalInstance.result.then(function () {

					}, function () {
						
					});	
				},
				
				addCardAssignement: function (accountList, userHBList, cardList) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-add-card-assignement.html',
					    controller: 'CPAddCardAssignementCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	accountList: function () {
					    		return accountList;
					    	},
					    	userHBList: function () {
					    		return userHBList;
					    	},
					    	cardList: function () {
					    		return cardList;
					    	}
					    }
					});

					modalInstance.result.then(function () {

					}, function () {
						
					});	
				},
				
				addCommision: function (commisionTypeList) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-add-commision.html',
					    controller: 'CPAddCommisionCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	commisionTypeList: function () {
					    		return commisionTypeList;
					    	}
					    }
					});

					modalInstance.result.then(function () {

					}, function () {
						
					});	
				},
				
				editCommision: function (commision) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-edit-commision.html',
					    controller: 'CPEditCommisionCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	commision: function () {
					    		return commision;
					    	},					    	
					    }
					});

					modalInstance.result.then(function () {

					}, function () {
						
					});	
				},
			};
		}		
);