angular
    .module('notification')
    .service('NotificationBarService', NotificationBarService);

NotificationBarService.$inject = ['$rootScope'];

function NotificationBarService($rootScope) {

    var domElement;

    this.postMessage = PostMessage;

    this.registerDOM = RegisterDOM;

    function PostMessage(message, timeToLinger, cssToApply) {
        var template = angular.element("<div class=\"notification-message " + (cssToApply || "") +  "\" time=\""+timeToLinger+"\">"+message+"</div>");
        var newScope = $rootScope.$new();
        domElement.append($compile(template)(newScope));
    }

    function RegisterDOM(element) {
        domElement = element;
    }
}