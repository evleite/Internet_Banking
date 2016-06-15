'use strict';

angular.module('corePortalApp').controller(
	    'CPEditCPUserCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, $location,
	    		CPModalFactory, CPUserCPService,
	    		user) {
	    	$scope.user = user;
	    	
	    	$scope.save = function() {
	    		CPUserCPService.editCPUser(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token,
                				id_user: user.id,
                				firstname: $scope.firstname,
                				lastname: $scope.lastname,
                				CNP: $scope.CNP,
                				email: $scope.email,
                				address: $scope.address,
                				telephone: $scope.telephone,
                			}
                	),
                    function success(data) {
                		console.log('CP user succesfully edited:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to edit CP user:', err);
                        	
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
			
			$scope.changePassword = function() {
				CPModalFactory.changePassword(user);
			}
	    }
);