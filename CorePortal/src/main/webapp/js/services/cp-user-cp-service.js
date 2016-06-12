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
                },
                
                addCPUser: {
                    method: 'POST',
                    url: 'rest/usersCP/new',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                deleteCPUser: {
                    method: 'POST',
                    url: 'rest/usersCP/delete',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                editCPUser: {
                    method: 'POST',
                    url: 'rest/usersCP/edit',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                changePassword: {
                    method: 'POST',
                    url: 'rest/usersCP/changePassword',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
            });
    });
