'use strict';

angular.module('corePortalApp').controller(
	    'CPEditRateCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, $location,
	    		CPModalFactory, CPRateService, 
	    		rate) {
	    	$scope.rate = rate;

	    	$scope.save = function() {
	    		CPRateService.editRate(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token,
                				id_rate: rate.id,
                				year_percentage: $scope.year_percentage,
                				details: $scope.details
                			}
                	),
                    function success(data) {
                		console.log('Rate succesfully edited:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to edit rate:', err);
                        	
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