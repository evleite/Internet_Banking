'use strict';

angular.module('homeBankApp').controller(
	    'HBErrorModelCtrl',
	    function ($scope, $uibModalInstance, errorMessage) {
	    	$scope.errorMessage = errorMessage;
	    	

	    	$scope.ok = function() {
				$uibModalInstance.close();
			};

	    }
);