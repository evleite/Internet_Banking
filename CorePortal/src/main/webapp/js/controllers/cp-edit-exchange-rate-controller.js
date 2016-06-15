'use strict';

angular.module('corePortalApp').controller(
	    'CPEditExchangeRateCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, $location,
	    		CPModalFactory, CPExchangeRateService, 
	    		exchangeRate) {
	    	$scope.exchangeRate = exchangeRate;

	    	$scope.save = function() {
	    		CPExchangeRateService.editExchangeRate(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token,
                				id_exchangeRate: exchangeRate.id,
                				buy: $scope.buy,
                				sell: $scope.sell
                			}
                	),
                    function success(data) {
                		console.log('ExchangeRate succesfully edited:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to edit exchangeRate:', err);
                        	
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