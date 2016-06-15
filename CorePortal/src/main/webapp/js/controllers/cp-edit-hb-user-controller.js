'use strict';

angular.module('corePortalApp').controller(
	    'CPEditHBUserCtrl',
	    function (
	    		$scope, $uibModalInstance, $httpParamSerializer, $location,
	    		CPModalFactory, CPUserHBService,
	    		user, authenticationTypeList) {
	    	$scope.user = user;
	    	$scope.authenticationTypeList = authenticationTypeList;
	    	
	    	$scope.save = function() {
	    		CPUserHBService.editHBUser(
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
                				auth_type: $scope.auth_type
                			}
                	),
                    function success(data) {
                		console.log('HB user succesfully edited:', data);
                		
                		$uibModalInstance.close();
                    },
                    function err(err) {
                      	console.log('Failed to edit HB user:', err);
                        	
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
			
			$scope.generateNewPassword = function() {
				CPUserHBService.generateNewPassword(
	                	$httpParamSerializer(
	                			{
	                				token: window.sessionStorage.token,
	                				id_user: user.id
	                			}
	                	),
	                    function success(data) {
	                		console.log('Password succesfully generated');
	                		
	                		CPModalFactory.infoModal("New generated password: " + data.password);
	                    },
	                    function err(err) {
	                      	console.log('Failed to generate new password:', err);
	                        	
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
			}
	    }
);