'use strict';

angular.module('corePortalApp').controller(
	    'CPEditAccountCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, $location,
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
                				id_rate: $scope.rate,
                				balance: $scope.balance
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