'use strict';

angular.module('corePortalApp').service('CPAccountAssignementService',
    function ItemService($resource) {
        return $resource(
            'rest/accountAssignements/:id',
            { id: '@_id' },
            {
            	getAccountAssignementList: {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                addAccountAssignement: {
                    method: 'POST',
                    url: 'rest/accountAssignements/new',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                deleteAccountAssignement: {
                    method: 'POST',
                    url: 'rest/accountAssignements/delete',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                }
            });
    });
