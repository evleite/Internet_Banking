'use strict';

angular.module('homeBankApp').service('HBUserHBService',
    function ItemService($resource) {
        return $resource(
            'rest/usersHB/:id',
            { id: '@_id' },
            {
            	getUser: {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                editUser: {
                    method: 'POST',
                    url: 'rest/usersHB/edit',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                changePassword: {
                    method: 'POST',
                    url: 'rest/usersHB/changePassword',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
            });
    });
