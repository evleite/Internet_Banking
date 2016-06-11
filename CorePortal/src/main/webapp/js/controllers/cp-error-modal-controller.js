'use strict';

angular.module('corePortalApp').controller(
	    'CPErrorModelCtrl',
	    function ($scope, $uibModalInstance, errorMessage) {
	    	$scope.errorMessage = errorMessage;
	    	

	    	$scope.ok = function() {
				$uibModalInstance.close();
			};

			/*$scope.cancel = function() {
				$uibModalInstance.dismiss('cancel');
			};*/
	    }
);