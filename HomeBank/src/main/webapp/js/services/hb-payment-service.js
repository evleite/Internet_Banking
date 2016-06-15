'use strict';

angular.module('homeBankApp').service('HBPaymentService',
    function ItemService($resource) {
        return $resource(
            'rest/payments/:id',
            { id: '@_id' },
            {
            	internalPayment: {
                    method: 'POST',
                    url: 'rest/payments/internalPayment',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
            });
    });
