'use strict';

angular.module('corePortalApp').controller(
	    'CPAddCommisionCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, 
	    		CPModalFactory, CPCommisionService, 
	    		commisionTypeList) {
	    	$scope.commisionTypeList = commisionTypeList;
	    	
	    	$scope.save = function() {
	    		CPCommisionService.addCommision(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token, 
                				type: $scope.type, 
                				amount: $scope.amount,
                				details: $scope.details
                			}
                	),
                    function success(data) {
                		console.log('New commision succesfully created:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to add new commision:', err);
                        	
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