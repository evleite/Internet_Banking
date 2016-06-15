'use strict';

angular.module('homeBankApp').controller(
	    'HBInfoModelCtrl',
	    function ($scope, $uibModalInstance, infoMessage) {
	    	$scope.infoMessage = infoMessage;
	    	

	    	$scope.ok = function() {
				$uibModalInstance.close();
			};

	    }
);