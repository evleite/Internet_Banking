'use strict';

angular.module('corePortalApp').controller(
	    'CPInfoModelCtrl',
	    function ($scope, $uibModalInstance, infoMessage) {
	    	$scope.infoMessage = infoMessage;
	    	

	    	$scope.ok = function() {
				$uibModalInstance.close();
			};

	    }
);