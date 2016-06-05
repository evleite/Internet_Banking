'use strict';

angular.module('corePortalApp').service('CPCardService',
    function ItemService($resource) {
        return $resource(
            'rest/cards/:id',
            { id: '@_id' },
            {
                getCardList: {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                }
            });
    });
