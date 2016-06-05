'use strict';

angular.module('corePortalApp').service('CPUserCPService',
    function ItemService($resource) {
        return $resource(
            'rest/usersCP/:id',
            { id: '@_id' },
            {
                getUserList: {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                }
            });
    });
