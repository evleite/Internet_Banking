'use strict';

angular.module('homeBankApp').controller(
	    'HBTransactionDetailsCtrl',
	    function ($scope, $uibModalInstance, transaction) {
	    	$scope.transaction = transaction;
	    	
	    	$scope.ok = function() {
	    		$uibModalInstance.close();
	    	};
	    }
);