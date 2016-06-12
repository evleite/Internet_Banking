'use strict';

angular.module('corePortalApp').factory(
		'CPModalFactory', 
		function ($uibModal, $rootScope) {
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
				
				infoModal: function (infoMessage) {
					console.log(infoMessage);
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-info-modal.html',
					    controller: 'CPInfoModelCtrl',
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
						$rootScope.main("account");
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
						$rootScope.main("account");
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
						$rootScope.main("accountAssignement");
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
						$rootScope.main("card");
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
						$rootScope.main("card");
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
						$rootScope.main("cardAssignement");
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
						$rootScope.main("commision");
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
						$rootScope.main("commision");
					}, function () {
						
					});	
				},
				
				addExchangeRate: function (currenciesList) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-add-exchange-rate.html',
					    controller: 'CPAddExchangeRateCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	currenciesList: function () {
					    		return currenciesList;
					    	}
					    }
					});

					modalInstance.result.then(function () {
						$rootScope.main("exchangeRate");
					}, function () {
						
					});	
				},
				
				editExchangeRate: function (exchangeRate) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-edit-exchange-rate.html',
					    controller: 'CPEditExchangeRateCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	exchangeRate: function () {
					    		return exchangeRate;
					    	},					    	
					    }
					});

					modalInstance.result.then(function () {
						$rootScope.main("exchangeRate");
					}, function () {
						
					});	
				},
				
				addRate: function (rateTypeList) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-add-rate.html',
					    controller: 'CPAddRateCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	rateTypeList: function () {
					    		return rateTypeList;
					    	}
					    }
					});

					modalInstance.result.then(function () {
						$rootScope.main("rate");
					}, function () {
						
					});	
				},
				
				editRate: function (rate) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-edit-rate.html',
					    controller: 'CPEditRateCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	rate: function () {
					    		return rate;
					    	},					    	
					    }
					});

					modalInstance.result.then(function () {
						$rootScope.main("rate");
					}, function () {
						
					});	
				},
				
				addCPUser: function () {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-add-cp-user.html',
					    controller: 'CPAddCPUserCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	
					    }
					});

					modalInstance.result.then(function () {
						$rootScope.main("userCP");
					}, function () {
						
					});	
				},
				
				editCPUser: function (user) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-edit-cp-user.html',
					    controller: 'CPEditCPUserCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	user: function () {
					    		return user;
					    	},					    	
					    }
					});

					modalInstance.result.then(function () {
						$rootScope.main("userCP");
					}, function () {
						
					});	
				},
				
				changePassword: function (user) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-change-password.html',
					    controller: 'CPChangePasswordCtrl',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	user: function () {
					    		return user;
					    	},					    	
					    }
					});

					modalInstance.result.then(function () {
						
					}, function () {
						
					});	
				},
				
				addHBUser: function (authenticationTypeList) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-add-hb-user.html',
					    controller: 'CPAddHBUserCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	authenticationTypeList: function () {
					    		return authenticationTypeList;
					    	}
					    }
					});

					modalInstance.result.then(function () {
						$rootScope.main("userHB");
					}, function () {
						
					});	
				},
				
				editHBUser: function (user, authenticationTypeList) {
					
					var modalInstance = $uibModal.open({
						animation: false,
					    templateUrl: 'views/cp-edit-hb-user.html',
					    controller: 'CPEditHBUserCtrl',
					    size: 'lg',
					    backdrop  : 'static',
					    keyboard  : false,
					    resolve: { /* Parrameters passed as locals to controller*/
					    	user: function () {
					    		return user;
					    	},
					    	authenticationTypeList: function () {
					    		return authenticationTypeList;
					    	}
					    }
					});

					modalInstance.result.then(function () {
						$rootScope.main("userHB");
					}, function () {
						
					});	
				},
			};
		}		
);