angular
    .module('notification')
    .service('NotificationBarService', NotificationBarService);

NotificationBarService.$inject = ['$timeout, $compile, $rootScope'];

function NotificationBarService() {
    var domElement;

    this.PostMessage = function(message, timeToLinger, cssToApply) {
        var template = angular.element("<div class=\"notification-message " + (cssToApply || "") +  "\" time=\""+timeToLinger+"\">"+message+"</div>");
        var newScope = $rootScope.$new();
        domElement.append($compile(template)(newScope));
    };

    this.RegisterDOM = function(element) {
        domElement = element;
    };
}