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
                },
                
                addHBUser: {
                    method: 'POST',
                    url: 'rest/usersHB/new',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                deleteHBUser: {
                    method: 'POST',
                    url: 'rest/usersHB/delete',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                editHBUser: {
                    method: 'POST',
                    url: 'rest/usersHB/edit',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                generateNewPassword: {
                    method: 'POST',
                    url: 'rest/usersHB/generateNewPassword',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
            });
    });
