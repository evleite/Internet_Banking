'use strict';

angular.module('homeBankApp').service('HBProductService',
    function ItemService($resource) {
        return $resource(
            'rest/products/:id',
            { id: '@_id' },
            {
                getAllProducts: {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
            });
    });
