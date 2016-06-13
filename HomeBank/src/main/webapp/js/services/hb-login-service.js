'use strict';

angular.module('homeBankApp').service('HBLoginService',
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
