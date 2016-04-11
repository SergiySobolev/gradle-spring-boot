angular
    .module('notification')
    .service('OneSecondNotificationBarService', OneSecondNotificationBarService);

OneSecondNotificationBarService.$inject = ['NotificationBarService'];

function OneSecondNotificationBarService(NotificationBarService) {

    this.postMessage = PostMessage;

    function PostMessage() {
        NotificationBarService.postMessage("message", 2000, null);
    }
}