angular
    .module('notification')
    .directive('notificationBarService', NotificationBarService);

NotificationBarService.$inject = ['NotificationBarService'];

function NotificationBarService() {
    return {
        restrict:"C",
        link: function(sc, el) {
            NotificationBarService.RegisterDOM(el);
        }
    }
}