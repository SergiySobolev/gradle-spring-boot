angular
    .module('app')
    .service('ValuesService', ValuesService);

ValuesService.$inject = ['$q'];

function ValuesService($q) {
    var stompClient = null;
    var valuesSequenceListener = $q.defer();
    var singleValueStompClient = {
        client: null,
        stomp: null
    };
    var singleValueListener = $q.defer();
    this.connect = connect;
    this.disconnect = disconnect;
    this.complete = complete;
    this.receiveValueSequence = receiveValueSequence;
    this.getSingleValue = getSingleValue;

    function startListeners() {
        stompClient.subscribe('/topic/sequencevalue', function (value) {
            valuesSequenceListener.notify(value);
        });
    }

    function startSingleValueListener() {
        //singleValueStompClient.stomp.subscribe('/topic/singlevalue', function (value) {
        //    singleValueListener.notify(value);
        //});
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

    function connectIfNotConnected() {
        if(stompClient == null){
            connect();
        }
    }

    function getSingleValue() {
        singleValueStompClient.client = new SockJS("/singlevalue");
        singleValueStompClient.stomp = Stomp.over(singleValueStompClient.client);
        singleValueStompClient.stomp.connect({}, startSingleValueListener, null);
        singleValueStompClient.stomp.send("/app/singlevalue", {
            priority: 9
        }, JSON.stringify({
            message: "mymessage",
            id: 1,
            user: "myprincipal"
        }));
    }

    function receiveValueSequence() {
        return valuesSequenceListener.promise;
    }

}