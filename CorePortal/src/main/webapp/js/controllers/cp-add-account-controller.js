'use strict';

angular.module('corePortalApp').controller(
	    'CPAddAccountCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, $location,
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