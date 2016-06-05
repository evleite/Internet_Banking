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
				addAccount: function (accountTypeList) {
					
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
					    	} 
					    }
					});

					modalInstance.result.then(function (data) {
						console.log("type: " + data[0] + " ; " + "balance: " + data[1]);
					}, function (data) {
						console.log(data);
					});	
				}
			};
		}		
);

angular.module('corePortalApp').controller(
	    'CPErrorModelCtrl',
	    function ($scope, $uibModalInstance, errorMessage) {
	    	$scope.errorMessage = errorMessage;
	    	

	    	$scope.ok = function() {
				$uibModalInstance.close('ok');
			};

			/*$scope.cancel = function() {
				$uibModalInstance.dismiss('cancel');
			};*/
	    }
);

angular.module('corePortalApp').controller(
	    'CPAddAccountCtrl',
	    function ($scope, $uibModalInstance, $httpParamSerializer, CPModalFactory, CPAccountService, accountTypeList) {
	    	$scope.accountTypeList = accountTypeList;
	    	

	    	$scope.save = function() {
	    		CPAccountService.addAccount(
                	$httpParamSerializer({token: window.sessionStorage.token, type: $scope.type, balance: $scope.balance}),
                    function success(data) {
                		console.log('New account succesfully created:', data);
                		
                		$uibModalInstance.close([$scope.type, $scope.balance]);
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
				$uibModalInstance.dismiss('cancel');
			};
	    }
);