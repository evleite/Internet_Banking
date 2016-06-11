'use strict';

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