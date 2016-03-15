angular
    .module('app')
    .service('ValuesService', ValuesService);

ValuesService.$inject = ['$q'];

function ValuesService($q) {
    var stompClient = null;
    var valuesListener = $q.defer();

    this.connect = connect;
    this.disconnect = disconnect;
    this.receiveValue = receiveValue;

    function startListeners() {
        stompClient.subscribe('/topic/value', function(value) {
            valuesListener.notify(value);
        });
    }

    function connect() {
        var socket = new SockJS('/value');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, startListeners);
    }

    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        console.log("Disconnected");
    }

    function receiveValue() {
        return valuesListener.promise;
    }

}