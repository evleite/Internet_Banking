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
                }
            });
    });
