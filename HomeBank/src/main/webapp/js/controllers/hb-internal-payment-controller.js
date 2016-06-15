'use strict';

angular.module('homeBankApp').controller(
	    'HBInternalPaymentCtrl',
	    function ($scope, $location, $uibModalInstance, $httpParamSerializer, HBPaymentService, HBModalFactory, products) {
	    	$scope.products = products;
	    	
	    	$scope.save = function() {
	    		HBPaymentService.internalPayment(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token,
                				payer_IBAN: $scope.payer_IBAN,
                				beneficiary_IBAN: $scope.beneficiary_IBAN,
                				amount: $scope.amount,
                				details: $scope.details,
                			}
                	),
                    function success(data) {
                		console.log('Payment is pedding:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Payment failed:', err);
                        	
                      	if (err.data.errorCode == 666){
                      		window.sessionStorage.clear();
                       		HBModalFactory.errorModal("Your session has expired. Please login again.");
                       		$location.path("/login");
                       	} else if (err.data.errorCode == 600) {
                       		HBModalFactory.errorModal(err.data.error);
                       		
                       	} else {                            	
                       		HBModalFactory.errorModal("Backend error");
                       	}
                    }
                );
			};

			$scope.cancel = function() {
				$uibModalInstance.dismiss();
			};
	    }
);