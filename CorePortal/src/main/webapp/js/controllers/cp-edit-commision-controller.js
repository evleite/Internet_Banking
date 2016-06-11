'use strict';

angular.module('corePortalApp').controller(
	    'CPEditCommisionCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, 
	    		CPModalFactory, CPCommisionService, 
	    		commision) {
	    	$scope.commision = commision;

	    	$scope.save = function() {
	    		CPCommisionService.editCommision(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token,
                				id_commision: commision.id,
                				amount: $scope.amount,
                				details: $scope.details
                			}
                	),
                    function success(data) {
                		console.log('Commision succesfully edited:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to edit commision:', err);
                        	
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