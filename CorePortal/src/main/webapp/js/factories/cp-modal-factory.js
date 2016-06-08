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
			};
		}		
);

angular.module('corePortalApp').controller(
	    'CPErrorModelCtrl',
	    function ($scope, $uibModalInstance, errorMessage) {
	    	$scope.errorMessage = errorMessage;
	    	

	    	$scope.ok = function() {
				$uibModalInstance.close();
			};

			/*$scope.cancel = function() {
				$uibModalInstance.dismiss('cancel');
			};*/
	    }
);

angular.module('corePortalApp').controller(
	    'CPAddAccountCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, 
	    		CPModalFactory, CPAccountService, 
	    		accountTypeList, currenciesList, rateList, commisionList) {
	    	$scope.accountTypeList = accountTypeList;
	    	$scope.currenciesList = currenciesList;
	    	$scope.rateList = rateList;
	    	$scope.commisionList = commisionList;
	    	

	    	$scope.save = function() {
	    		CPAccountService.addAccount(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token, 
                				type: $scope.type, balance: $scope.balance,
                				id_comm: $scope.commision,
                				id_rate: $scope.rate,
                				currency: $scope.currencies
                			}
                	),
                    function success(data) {
                		console.log('New account succesfully created:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to add new account:', err);
                        	
                      	if (err.data.errorCode == 666){
                      		window.sessionStorage.clear();
                       		CPModalFactory.errorModal("Your session has expired. Please login again.");
                       		$location.path("/login");
                       	} else {                            	
                       		CPModalFactory.errorModal("Backend error");
                       	}
                    }
                );
			};

			$scope.cancel = function() {
				$uibModalInstance.dismiss();
			};
	    }
);

angular.module('corePortalApp').controller(
	    'CPEditAccountCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, 
	    		CPModalFactory, CPAccountService, 
	    		account, rateList, commisionList) {
	    	$scope.account = account;
	    	$scope.rateList = rateList;
	    	$scope.commisionList = commisionList;
	    	

	    	$scope.save = function() {
	    		CPAccountService.editAccount(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token,
                				id_account: account.id,
                				id_comm: $scope.commision,
                				id_rate: $scope.rate
                			}
                	),
                    function success(data) {
                		console.log('Account succesfully edited:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to edit account:', err);
                        	
                      	if (err.data.errorCode == 666){
                      		window.sessionStorage.clear();
                       		CPModalFactory.errorModal("Your session has expired. Please login again.");
                       		$location.path("/login");
                       	} else {                            	
                       		CPModalFactory.errorModal("Backend error");
                       	}
                    }
                );
			};

			$scope.cancel = function() {
				$uibModalInstance.dismiss();
			};
	    }
);

angular.module('corePortalApp').controller(
	    'CPAddAccountAssignementCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, 
	    		CPModalFactory, CPAccountAssignementService, 
	    		accountList, userHBList) {
	    	$scope.accountList = accountList;
	    	$scope.userHBList = userHBList;

	    	$scope.save = function() {
	    		CPAccountAssignementService.addAccountAssignement(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token, 
                				id_account: $scope.account,
                				id_user: $scope.user,
                			}
                	),
                    function success(data) {
                		console.log('New account assignement succesfully created:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to add new account assignement:', err);
                        	
                      	if (err.data.errorCode == 666){
                      		window.sessionStorage.clear();
                       		CPModalFactory.errorModal("Your session has expired. Please login again.");
                       		$location.path("/login");
                       	} else {                            	
                       		CPModalFactory.errorModal("Backend error");
                       	}
                    }
                );
			};

			$scope.cancel = function() {
				$uibModalInstance.dismiss();
			};
	    }
);

angular.module('corePortalApp').controller(
	    'CPAddCardCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, 
	    		CPModalFactory, CPCardService, 
	    		cardTypeList) {
	    	$scope.cardTypeList = cardTypeList;
	    	
	    	$scope.save = function() {
	    		CPCardService.addCard(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token, 
                				type: $scope.type,
                				daily_limit: $scope.daily_limit,
                				validity: $scope.card_validity
                			}
                	),
                    function success(data) {
                		console.log('New card succesfully created:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to add new card:', err);
                        	
                      	if (err.data.errorCode == 666){
                      		window.sessionStorage.clear();
                       		CPModalFactory.errorModal("Your session has expired. Please login again.");
                       		$location.path("/login");
                       	} else {                            	
                       		CPModalFactory.errorModal("Backend error");
                       	}
                    }
                );
			};

			$scope.cancel = function() {
				$uibModalInstance.dismiss();
			};
	    }
);

angular.module('corePortalApp').controller(
	    'CPEditCardCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, 
	    		CPModalFactory, CPCardService, 
	    		card) {
	    	$scope.card = card;
	    	
	    	$scope.save = function() {
	    		CPCardService.editCard(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token,
                				id_card: card.id,
                				validity: $scope.card_validity,
                				daily_limit: $scope.daily_limit
                			}
                	),
                    function success(data) {
                		console.log('Card succesfully edited:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to edit card:', err);
                        	
                      	if (err.data.errorCode == 666){
                      		window.sessionStorage.clear();
                       		CPModalFactory.errorModal("Your session has expired. Please login again.");
                       		$location.path("/login");
                       	} else {                            	
                       		CPModalFactory.errorModal("Backend error");
                       	}
                    }
                );
			};

			$scope.cancel = function() {
				$uibModalInstance.dismiss();
			};
	    }
);