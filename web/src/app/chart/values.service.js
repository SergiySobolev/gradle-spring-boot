angular
    .module('app')
    .service('ValuesService', ValuesService);

ValuesService.$inject = ['$q'];

function ValuesService($q) {
    var valueStompClient = {
        client: null,
        stomp: null
    };
    var singleValueListener = $q.defer();
    var valuesSequenceListener = $q.defer();
    this.connectToValuesClient = connectToValuesClient;
    this.disconnect = disconnect;
    this.complete = complete;
    this.receiveValueSequence = receiveValueSequence;
    this.receiveSingleValue = receiveSingleValue;
    this.getSingleValue = getSingleValue;

    function startSingleValueListeners() {
        valueStompClient.client.subscribe('/topic/singlevalue', function (value) {
            singleValueListener.notify(value);
        });
        valueStompClient.client.subscribe('/topic/sequencevalue', function (value) {
            valuesSequenceListener.notify(value);
        });
    }

    var connectCallback = function() {
        console.log("Connection to WS successful");
        startSingleValueListeners();
    };

    var errorCallback = function(error) {
        console.log(error.headers.message);
    };

    function connectToValuesClient() {
        valueStompClient.stomp = new SockJS('ws');
        valueStompClient.client = Stomp.over(valueStompClient.stomp);
        valueStompClient.client.connect("guest", "guest", connectCallback, errorCallback);
    }

    function disconnect() {
        if (valueStompClient.client != null) {
            valueStompClient.client.disconnect();
            valuesSequenceListener.reject("Disconnected from values source");
            valuesSequenceListener = $q.defer();
        }
    }

    function complete() {
        if (valueStompClient.client != null) {
            valueStompClient.client.disconnect();
            valuesSequenceListener.resolve("All values received");
            valuesSequenceListener = $q.defer();
        }
    }

    function getSingleValue() {
        //var jsonStr = JSON.stringify({
        //    message: "mymessage",
        //    id: 1,
        //    user: "myprincipal"
        //});
        valueStompClient.client.send("/app/singlevalue");
    }

    function receiveValueSequence() {
        return valuesSequenceListener.promise;
    }

    function receiveSingleValue() {
        return singleValueListener.promise;
    }



}