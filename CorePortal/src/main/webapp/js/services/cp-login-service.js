'use strict';

angular.module('corePortalApp').service('CPLoginService',
    function ItemService($resource) {
        return $resource(
            'rest/login/:loginId',
            {loginId: '@id'},
            {
                login: {
                    method: 'POST',
                    isArray: true,
                    data: {name: 'default name', flag: false}
                }
            });
    });
