angular
    .module('app')
    .service('ValuesService', ValuesService);

ValuesService.$inject = ['$q'];

function ValuesService($q) {
    var stompClient = null;
    var valuesSequenceListener = $q.defer();

    this.connect = connect;
    this.disconnect = disconnect;
    this.complete = complete;
    this.receiveValueSequence = receiveValueSequence;

    function startListeners() {
        stompClient.subscribe('/topic/sequencevalue', function(value) {
            valuesSequenceListener.notify(value);
        });
    }

    function connect() {
        var socket = new SockJS('/sequencevalue');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, startListeners);
    }

    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
            valuesSequenceListener.reject("Disconnected from values source");
            valuesSequenceListener = $q.defer();
        }
    }

    function complete() {
        if (stompClient != null) {
            stompClient.disconnect();
            valuesSequenceListener.resolve("All values received");
            valuesSequenceListener = $q.defer();
        }
    }

    function receiveValueSequence() {
        return valuesSequenceListener.promise;
    }

}