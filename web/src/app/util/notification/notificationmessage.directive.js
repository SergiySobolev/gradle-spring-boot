angular
    .module('notification')
    .directive('notificationMessage', NotificationMessage);

function NotificationMessage($timeout, NotificationBarService) {
    return {
        restrict:"C",
        transclude:true,
        template: "<a href=\"javascript:void(0)\" ng-click=\"close()\">x</a><div ng-transclude></div>",
        link: function(scope, el, attr) {
            //var promiseToEnd,
            //    promiseToDestroy;
            ////ugly hack to get css styling to be interpreted correctly by browser.  Blech!
            //$timeout(function() {
            //    el.addClass("show");
            //}, 1);
            //scope.close = function() {
            //    el.remove();
            //    scope.$destroy();
            //};
            //
            //function cancelTimeouts() {
            //    if (promiseToDestroy) {
            //        $timeout.cancel(promiseToDestroy);
            //        promiseToDestroy = undefined;
            //    }
            //    $timeout.cancel(promiseToEnd);
            //    el.addClass("show");
            //}
            //
            //function startTimeouts() {
            //    promiseToEnd = $timeout(function() {
            //        el.removeClass("show");
            //        promiseToDestroy = $timeout(scope.close, 1010);
            //    }, attr.time);
            //}
            //
            //el.bind("mouseenter", cancelTimeouts);
            //el.bind("mouseleave", startTimeouts);
            //
            //startTimeouts();
        }
    };
}