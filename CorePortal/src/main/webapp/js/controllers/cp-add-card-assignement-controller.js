'use strict';

angular.module('corePortalApp').controller(
	    'CPAddCardAssignementCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, $location,
	    		CPModalFactory, CPCardAssignementService, 
	    		accountList, userHBList, cardList) {
	    	$scope.accountList = accountList;
	    	$scope.userHBList = userHBList;
	    	$scope.cardList = cardList;

	    	$scope.save = function() {
	    		CPCardAssignementService.addCardAssignement(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token, 
                				id_account: $scope.account,
                				id_user: $scope.user,
                				id_card: $scope.card
                			}
                	),
                    function success(data) {
                		console.log('New card assignement succesfully created:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to add new card assignement:', err);
                        	
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