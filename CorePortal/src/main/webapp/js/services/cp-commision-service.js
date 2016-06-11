'use strict';

angular.module('corePortalApp').service('CPCommisionService',
    function ItemService($resource) {
        return $resource(
            'rest/commisions/:id',
            { id: '@_id' },
            {
                getCommisionList: {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                addCommision: {
                    method: 'POST',
                    url: 'rest/commisions/new',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                deleteCommision: {
                    method: 'POST',
                    url: 'rest/commisions/delete',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                editCommision: {
                    method: 'POST',
                    url: 'rest/commisions/edit',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
            });
    });
