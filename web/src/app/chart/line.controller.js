angular
    .module('app')
    .controller('LineController', LineController);

LineController.$inject = ['ValuesService'];

function LineController(ValuesService){
    var vm = this;
    vm.labels = ["January", "February", "March", "April", "May", "June", "July", "January", "February", "March", "April", "May", "June", "July"];
    vm.series = ['Series A', 'Series B'];
    vm.colors = ['Green', 'Dark Grey', 'Grey'];
    vm.data = [
        [65, 59, 80, 81, 56, 55, 40],
        [28, 48, 40, 19, 86, 27, 90]
    ];
    vm.onClick = function (points, evt) {
        console.log(points, evt);
    };
    ValuesService.connect();
    ValuesService.receiveValue().then(null, null, function(value) {
        vm.data[0].push(value.body/1);
        vm.data[1].push(value.body/2);
    });
    return this;
}