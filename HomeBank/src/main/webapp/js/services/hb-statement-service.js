'use strict';

angular.module('homeBankApp').service('HBStatementService',
    function ItemService($resource) {
        return $resource(
            'rest/statement/:id',
            { id: '@_id' },
            {
            	printStatement: {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    responseType : 'arraybuffer'
                }
            
            });
    });
