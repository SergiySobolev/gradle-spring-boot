angular
    .module('app')
    .controller('LineController', LineController);

LineController.$inject = ['ValuesService'];

function LineController(ValuesService) {
    var vm = this;
    vm.sampleCount = 10;
    vm.labels = [];
    vm.series = ['Series A', 'Series B'];
    vm.data = [
        [],
        []
    ];

    var clearData = function() {
        vm.labels.splice(0, vm.labels.length);
        vm.data[0].splice(0, vm.data[0].length);
        vm.data[1].splice(0, vm.data[1].length);
        vm.data[0].push(0);
        vm.data[1].push(0);
        vm.labels.push(0);
    };

    vm.addSample = function (sample) {
        if (vm.data[0].length !== vm.labels.length) {
            console.log('Different count of labels and values');
        }
        if (vm.sampleCount <= vm.labels.length) {
            vm.labels.splice(0, 1);
            vm.data[0].splice(0, 1);
            vm.data[1].splice(0, 1);
        }
        vm.data[0].push(sample.values[0]);
        vm.data[1].push(sample.values[1]);
        vm.labels.push(sample.dateLabel);
    };

    var resolve = function (resolve) {
        console.log('Receiving values stopped due to: ' + resolve);
    };

    var reject = function (reject) {
        console.log('Receiving values stopped due to: ' + reject);
    };

    var progress = function (data) {
        var sample = JSON.parse(data.body);
        vm.addSample(sample);
    };

    vm.connect = function () {
        ValuesService.connectToValuesClient();
        ValuesService.receiveValueSequence().then(resolve, reject, progress);
        ValuesService.receiveSingleValue().then(resolve, reject, progress);
    };

    vm.disconnect = function () {
        ValuesService.disconnect();
    };

    vm.complete = function () {
        ValuesService.complete();
        clearData();
    };

    vm.getSingleValue = function  () {
        ValuesService.getSingleValue();
    };

    return this;
}