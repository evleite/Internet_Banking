'use strict';

angular.module('corePortalApp').controller(
	    'CPAddCPUserCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, 
	    		CPModalFactory, CPUserCPService) {
	    	
	    	$scope.save = function() {
	    		CPUserCPService.addCPUser(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token, 
                				username: $scope.username,
                				firstname: $scope.firstname,
                				lastname: $scope.lastname,
                				CNP: $scope.CNP,
                				email: $scope.email,
                				address: $scope.address,
                				telephone: $scope.telephone,
                			}
                	),
                    function success(data) {
                		console.log('New CP user succesfully created:');
                		
                		CPModalFactory.infoModal("User succesfully created with password: " + data.password);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to add new CP user:', err);
                        	
                      	if (err.data.errorCode == 666){
                      		window.sessionStorage.clear();
                       		CPModalFactory.errorModal("Your session has expired. Please login again.");
                       		$location.path("/login");
                       	} else if (err.data.errorCode == 600) {
                       		CPModalFactory.errorModal(err.data.error);
                       		
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