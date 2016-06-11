'use strict';

angular.module('corePortalApp').service('CPCardAssignementService',
    function ItemService($resource) {
        return $resource(
            'rest/cardAssignements/:id',
            { id: '@_id' },
            {
            	getCardAssignementList: {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                addCardAssignement: {
                    method: 'POST',
                    url: 'rest/cardAssignements/new',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                deleteCardAssignement: {
                    method: 'POST',
                    url: 'rest/cardAssignements/delete',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                }
            });
    });
