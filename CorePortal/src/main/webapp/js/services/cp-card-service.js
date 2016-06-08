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
                },
                
                addCard: {
                    method: 'POST',
                    url: 'rest/cards/new',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                deleteCard: {
                    method: 'POST',
                    url: 'rest/cards/delete',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
                
                editCard: {
                    method: 'POST',
                    url: 'rest/cards/edit',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                },
            });
    });
