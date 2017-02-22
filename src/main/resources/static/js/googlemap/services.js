map.service('lazyLoadApi', ['$window', '$q',function lazyLoadApi($window, $q) {
  function loadScript() {
    var s = document.createElement('script')
    s.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyBMM5SVaQN_uhe5GVZj41g5s0db1OBNtFE&language=en&callback=initMap'
    document.body.appendChild(s)
  }
  var deferred = $q.defer()

  $window.initMap = function() {
    deferred.resolve()
  }

  if ($window.attachEvent) {
    $window.attachEvent('onload', loadScript)
  } else {
    $window.addEventListener('load', loadScript, false)
  }

  return deferred.promise
}]);

map.service('locationService',['$http',function ($http) {
    this.currentPosition = getCurrentPosition;
    function getCurrentPosition(planeId,lastUpdate,callback) {
    var url = '/planeLocation';
    if(planeId){
        url=url+'/'+planeId
    }
    $http.get("/getCurrentTime").then(function successCallback(response) {
            var currentTime = response.data;
            var loadFromServer = true;
            if(lastUpdate) {
                if(currentTime-lastUpdate < 30000) {
                    loadFromServer = false;
                }
            }

            if(loadFromServer ) {
              $http.get(url).
                      then(function successCallback(response) {
                      var data;
                      angular.forEach(response.data, function(value, key){
                            data = value;
                            lastUpdate = key;
                      });

                      callback(lastUpdate,data);
                       }, function errorCallback(response) {
                       });
             } else {
                callback(currentTime,"");
             }
     },function errorCallback(response) {
       //             callback(response.data);
     });

     }

}]);

