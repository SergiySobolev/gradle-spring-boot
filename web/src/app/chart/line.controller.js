angular
    .module('app')
    .controller('LineController', LineController);

LineController.$inject = ['ValuesService'];

function LineController(ValuesService) {
    var vm = this;
    vm.sampleCount = 10;
    vm.labels = [];
    vm.series = ['Series A', 'Series B'];
    vm.colors = ['Green', 'Dark Grey', 'Grey'];
    vm.data = [
        [],
        []
    ];

    vm.onClick = function (points, evt) {
        console.log(points, evt);
    };

    vm.addSample = function (sample) {
        if (vm.data[0].length !== vm.labels.length) {
            console.log('Different count of labels and values');
        }
        if (vm.sampleCount <= vm.labels.length) {
            vm.labels.splice(0,1);
            vm.data[0].splice(0,1);
            vm.data[1].splice(0,1);
        }
        vm.data[0].push(sample.values[0]);
        vm.data[1].push(sample.values[1]);
        vm.labels.push(sample.dateLabel);
    };

    ValuesService.connect();

    ValuesService.receiveValue().then(null, null, function (data) {
        var sample = JSON.parse(data.body);
        vm.addSample(sample);
    });

    return this;
}