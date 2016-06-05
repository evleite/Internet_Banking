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
                }
            });
    });
