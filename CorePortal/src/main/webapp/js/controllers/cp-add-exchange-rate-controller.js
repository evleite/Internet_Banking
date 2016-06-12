'use strict';

angular.module('corePortalApp').controller(
	    'CPAddExchangeRateCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, 
	    		CPModalFactory, CPExchangeRateService, 
	    		currenciesList) {
	    	$scope.currenciesList = currenciesList;
	    	
	    	$scope.save = function() {
	    		CPExchangeRateService.addExchangeRate(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token, 
                				currency: $scope.currency, 
                				buy: $scope.buy,
                				sell: $scope.sell
                			}
                	),
                    function success(data) {
                		console.log('New exchangeRate succesfully created:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to add new exchangeRate:', err);
                        	
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