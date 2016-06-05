'use strict';

angular.module('corePortalApp').service('CPExchangeRateService',
    function ItemService($resource) {
        return $resource(
            'rest/exchangeRates/:id',
            { id: '@_id' },
            {
            	getExchangeRateList: {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                }
            });
    });
