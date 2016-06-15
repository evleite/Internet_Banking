'use strict';

angular.module('corePortalApp').controller(
	    'CPAddRateCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, $location,
	    		CPModalFactory, CPRateService, 
	    		rateTypeList) {
	    	$scope.rateTypeList = rateTypeList;
	    	
	    	$scope.save = function() {
	    		CPRateService.addRate(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token, 
                				type: $scope.type, 
                				year_percentage: $scope.year_percentage,
                				details: $scope.details
                			}
                	),
                    function success(data) {
                		console.log('New rate succesfully created:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to add new rate:', err);
                        	
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