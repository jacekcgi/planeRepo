import { OnInit, OnChanges, SimpleChanges, Component, ElementRef, ViewChild, AfterViewInit } from "@angular/core";
import { PlaneService } from "app/services";
import { Observable } from "rxjs/Observable";
declare var google: any;

let $ = require('jquery');

@Component({
    selector: 'map',
    templateUrl: './map.component.html'
})

export class MapComponent implements AfterViewInit {

    @ViewChild('map') mapDiv: any;

    private map: any;

    lastUpdate: any = null;

    storedLocationData: any;

    markers = {};

    plane = "M482,363.333c2.667,0.666,5,0.332,7-1c2-1.334,3-3.334,3-6v-36c0-6.668-3-12-9-16l-187-111    c-6-3.334-9-8.668-9-16v-125c0-6.667-1.333-13-4-19c-7.333-18.667-17.667-29-31-31c-2-0.667-4-1-6-1s-4.333,0.333-7,1    c-0.667,0-1.5,0.167-2.5,0.5s-2.167,0.833-3.5,1.5c-4.667,1.333-9,4.333-13,9s-7,9-9,13l-3,7c-2,6-3,12.333-3,19v125    c0,7.333-3,12.667-9,16l-187,111c-6,4-9,9.332-9,16v36c0,2.666,1,4.666,3,6c2,1.332,4.333,1.666,7,1l185-60    c2.667-1.334,5-1.168,7,0.5c2,1.666,3,3.832,3,6.5v97c0,7.334-2.667,13-8,17l-25,18c-5.333,4-8,9.666-8,17v24c0,2.666,1,4.666,3,6    s4.333,1.666,7,1l62-18c6.667-2,13.333-2,20,0l62,18c2.667,0.666,5,0.334,7-1s3-3.334,3-6v-24c0-7.334-2.667-13-8-17l-25-18    c-5.333-4-8-9.666-8-17v-97c0-3.334,1-5.668,3-7c2-1.334,4.333-1.334,7,0L482,363.333z"

    icon = {
        path: this.plane,
        fillOpacity: 1,
        fillColor: '#ffda44',
        strokeWeight: 1,
        scale: 0.05,
        anchor: new google.maps.Point(256, 256)
    }

    constructor(private planeService: PlaneService) {

    }

    ngAfterViewInit(): void {
        this.initMap();
    }

    initMap() {
        var uluru = { lat: 54, lng: 17 };
        var map = new google.maps.Map(this.mapDiv.nativeElement, {
            zoom: 4,
            center: uluru
        });
        this.map = map;

        this.loadAndUpdatePlanes();
        setInterval(() => { this.loadAndUpdatePlanes() }, 5000);
        this.loadAirports(map);
    }

    loadAndUpdatePlanes() {
        this.planeService.findAllPlanesLocation(this.lastUpdate).then((data) => {
            this.updatePositions(data.updateTime, data.flightDetails)
        })
    }

    updatePositions(serverTime: any, data: any) {
        if (data) {
            this.lastUpdate = serverTime
            this.storedLocationData = data;
        } else {
            data = this.storedLocationData;
        }

        var tmpMarkers = {};

        for (let value of data) {
            var distance = this.planeService.calculateDistance(serverTime, value.incomingTime, value.velocity);
            var destPoint = this.planeService.calculateDestinationPoint(value.gpsLatitude, value.gpsLongitude, value.course, distance);
            var planeSid = value.plane.sid;
            var latlng = new google.maps.LatLng(destPoint.latitude.toString(), destPoint.longitude.toString());
            this.icon["rotation"] = destPoint.course;
            
            if (this.markers[planeSid]) {
                var marker = this.markers[planeSid];
                marker.setPosition(latlng);
                marker.setIcon(this.icon);
                tmpMarkers[planeSid] = marker;
                this.markers[planeSid] = undefined;
                delete this.markers[planeSid];

            } else {
                tmpMarkers[planeSid] = this.createMarker(latlng, value, planeSid);
            }
        }

        for (var m in this.markers) {
            if (this.markers.hasOwnProperty(m)) {
                this.markers[m].setMap(null);
            }
        }
        this.markers = tmpMarkers;
    }

    createMarker(latlng: any, value: any, planeSid: string) {
        var marker = new google.maps.Marker({
            position: latlng,
            title: value.course.toString(),
            icon: this.icon,
            map: this.map
        });
        marker.set('planeSid', planeSid)
        marker.set('mapComponent', this)
        marker.addListener('click', function () {
            //this in here is marker
            this.get('mapComponent').onMarkerClick(this.get('planeSid'))
        });
        return marker;
    }

    onMarkerClick(planeSid: any) {
        console.log('clicked!')
        console.log(planeSid)
    }

    loadAirportsOnMap(map: any, data: any) {
           var markers = new Array;
           let i = 0;
            var z = map.getZoom();
           for(let value of data) {
           var marker = new google.maps.Marker({
            position: new google.maps.LatLng(value.latitude, value.longitude),
            // animation: google.maps.Animation.DROP,
            map: null,
            zoomlvl: value.zoomlvl
              });
              markers[i++] = marker;
                if ( z >= marker.zoomlvl) { 
                marker.setMap(map);
            }
           }
            google.maps.event.addListener(map, 'zoom_changed', function() {
            //  if (map.getBounds().contains(marker.getPosition())) {
            var z = map.getZoom();
            console.log(z);
            for (let mkr of markers) {
                  if ( z >= mkr.zoomlvl) { 
                mkr.setMap(map);
            }
            else if (!map.getBounds().contains(marker.getPosition()) || z < mkr.zoomlvl){
                // mkr.setAnimation(google.maps.Animation.DROP);
                mkr.setMap(null);
            } 
            }
        //  }
                });

                 google.maps.event.addListener(map, 'dragend', function() {
            //  if (map.getBounds().contains(marker.getPosition())) {
            var z = map.getZoom();
            
            for (let mkr of markers) {
         if (map.getBounds().contains(mkr.getPosition())&& z>=mkr.zoomlvl ){
                mkr.setAnimation(google.maps.Animation.DROP);
                mkr.setMap(map);
            } 
            else{
                mkr.setMap(null);
            }
            }
        //  }
                });
    }

    loadAirports(map: any) {
         this.planeService.findAirports().then((data) => {
            this.loadAirportsOnMap(map, data);
        })
      
    }

}