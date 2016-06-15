'use strict';

angular.module('homeBankApp').controller(
	    'HBSettingsModelCtrl',
	    function ($scope, $location, $uibModalInstance, $httpParamSerializer, HBUserHBService, HBModalFactory, user) {
	    	$scope.user = user;
	    	
	    	$scope.save = function() {
	    		HBUserHBService.editUser(
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
                		console.log('User succesfully edited:', data);
                		
                		$uibModalInstance.close(data.user);
                    },
                    function err(err) {
                      	console.log('Failed to edit user:', err);
                        	
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
			
			$scope.changePassword = function() {
				HBModalFactory.changePassword($scope.user);
			};
	    }
);