'use strict';

angular.module('homeBankApp').controller(
	    'HBCreditCurrentPaymentCtrl',
	    function ($scope, $location, $uibModalInstance, $httpParamSerializer, HBPaymentService, HBModalFactory,
	    		creditCards, currentAccounts) {
	    	$scope.currentAccounts = currentAccounts;
	    	$scope.creditCards = creditCards;
	    	
	    	$scope.save = function() {
	    		var i;
	    		for (i=0;i<$scope.creditCards.length;i++){
	    			var product = $scope.creditCards[i];
	    			if (product.account.IBAN == $scope.payer_IBAN){
	    				$scope.card_no = product.card.card_no;
	    			}
	    		}
	    		
	    		HBPaymentService.creditToCurrentPayment(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token,
                				card_no: $scope.card_no,
                				payer_IBAN: $scope.payer_IBAN,
                				beneficiary_IBAN: $scope.beneficiary_IBAN,
                				amount: $scope.amount,
                				details: $scope.details,
                			}
                	),
                    function success(data) {
                		console.log('Payment is proccessed:', data);
                		
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