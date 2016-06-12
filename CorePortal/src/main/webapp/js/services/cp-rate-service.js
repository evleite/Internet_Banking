'use strict';

angular.module('corePortalApp').service('CPRateService',
    function ItemService($resource) {
        return $resource(
            'rest/rates/:id',
            { id: '@_id' },
            {
                getRateList: {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                addRate: {
                    method: 'POST',
                    url: 'rest/rates/new',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                deleteRate: {
                    method: 'POST',
                    url: 'rest/rates/delete',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                editRate: {
                    method: 'POST',
                    url: 'rest/rates/edit',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
            });
    });
