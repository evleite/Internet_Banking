'use strict';

angular.module('corePortalApp').controller(
	    'CPChangePasswordCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, 
	    		CPModalFactory, CPUserCPService,
	    		user) {
	    	$scope.user = user;
	    	
	    	$scope.save = function() {
	    		$scope.changeFailed = false;
	    		CPUserCPService.changePassword(
                	$httpParamSerializer(
                			{
                				token: window.sessionStorage.token,
                				id_user: user.id,
                				old_password: $scope.old_password,
                				new_password: $scope.new_password,
                				confirm_password: $scope.confirm_password,
                			}
                	),
                    function success(data) {
                		console.log('Password succesfully changed:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to change password:', err);
                        	
                      	if (err.data.errorCode == 666){
                      		window.sessionStorage.clear();
                       		CPModalFactory.errorModal("Your session has expired. Please login again.");
                       		$location.path("/login");
                       	} else if (err.data.errorCode == 600) {
                       		$scope.changeFailed = true;
                       		$scope.error = err.data.error;
                       		
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