'use strict';

angular.module('homeBankApp').service('HBTransactionService',
    function ItemService($resource) {
        return $resource(
            'rest/transactions/:id',
            { id: '@_id' },
            {
            	getProductTransactions: {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
            });
    });
