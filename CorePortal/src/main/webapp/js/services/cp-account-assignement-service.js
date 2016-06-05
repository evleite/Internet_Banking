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
                }
            });
    });
