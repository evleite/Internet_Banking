'use strict';

angular.module('corePortalApp').service('CPUserHBService',
    function ItemService($resource) {
        return $resource(
            'rest/usersHB/:id',
            { id: '@_id' },
            {
                getUserList: {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                }
            });
    });
