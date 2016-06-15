'use strict';

angular.module('corePortalApp').controller(
	    'CPAddCardCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, $location,
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