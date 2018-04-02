// TODO use service to share data between controllers

var demo = angular.module('demo', []);

demo.factory('myService', function ($http) {
    var allItems = {};

    return {
        getData: function () {
            $http.get('http://localhost:8080/gradle_myflix/rest/items/all').
                     then(function(response) {
                        allItems = response.data;
                        return allItems;
                    });
        },
        setData: function (newItem) {
            //You could also set specific attribute of the form data instead
            $http.post('http://localhost:8080/gradle_myflix/rest/items/add', newItem).
                                  success(function(data, status, headers, config) {
                                          // this callback will be called asynchronously
                                          // when the response is available
                                          console.log(data);
                                          allItems = this.getData();
                                        }).
                                        error(function(data, status, headers, config) {
                                          // called asynchronously if an error occurs
                                          // or server returns response with an error status.
                                        });
        },
        resetData: function () {
            //To be called when the data stored needs to be discarded
            allItems = {};
        }
    };
});

demo.controller('ShowItem', function($scope, $http, myService) {
    $http.get('http://localhost:8080/gradle_myflix/rest/items/item/1').
        then(function(response) {
            $scope.item = response.data;
        });

    $scope.items = myService.getData();


//    $scope.$watch(function () { return myService.getData(); }, function (value) {
//            $scope.items = value;
//        });
});



demo.controller('AddItem', ['$scope', '$http', function($scope, $http, myService) {

       $scope.add = function(newItem) {
               //$scope.newItem = angular.copy(newItem);

               myService.setData(newItem);

/*               $http.post('http://localhost:8080/gradle_myflix/rest/items/add', newItem).
                      success(function(data, status, headers, config) {
                              // this callback will be called asynchronously
                              // when the response is available
                              console.log(data);
                            }).
                            error(function(data, status, headers, config) {
                              // called asynchronously if an error occurs
                              // or server returns response with an error status.
                            });*/
             };

}]);