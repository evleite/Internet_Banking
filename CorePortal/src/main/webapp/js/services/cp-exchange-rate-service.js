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
                },
                
                addExchangeRate: {
                    method: 'POST',
                    url: 'rest/exchangeRates/new',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                deleteExchangeRate: {
                    method: 'POST',
                    url: 'rest/exchangeRates/delete',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                editExchangeRate: {
                    method: 'POST',
                    url: 'rest/exchangeRates/edit',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
            });
    });
