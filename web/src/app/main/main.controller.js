angular
    .module('app')
    .controller('MainController', MainController);

function MainController() {
    var vm = this;
    vm.name = "Gradle-Spring-Boot";
}
