'use strict';

angular.module('corePortalApp').controller(
	    'CPEditCardCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, $location,
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