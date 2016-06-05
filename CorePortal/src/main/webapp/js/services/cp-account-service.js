'use strict';

angular.module('corePortalApp').service('CPAccountService',
    function ItemService($resource) {
        return $resource(
            'rest/acounts/:id',
            { id: '@_id' },
            {
                getAccountList: {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                addAccount: {
                    method: 'POST',
                    url: 'rest/acounts/new',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                }
            });
    });
