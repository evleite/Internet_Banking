'use strict';

angular.module('corePortalApp').service('CPLoginService',
    function ItemService($resource) {
        return $resource(
            'rest/login',
            {},
            {
                login: {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                }
            });
    });
