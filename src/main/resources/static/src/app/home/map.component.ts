import { OnInit, OnChanges, SimpleChanges, Component, ElementRef, ViewChild, AfterViewInit, OnDestroy } from "@angular/core";
import { PlaneService } from "app/services";
import { FlightDetailsDto } from "app/domain";
import { Observable, Subscription } from 'rxjs/Rx';
declare var google: any;

var PLANES_REFRESH_INTERVAL : number = 1000; // 1s

var SERVER_POST_FOR_PLANES_INTERVAL: number = 31; //call every N execution of planes refresh

let $ = require('jquery');

@Component({
    selector: 'map',
    templateUrl: './map.component.html'
})

export class MapComponent implements OnInit, AfterViewInit, OnDestroy {

    toggled:string="";

    selectedFlightDetailsDto: FlightDetailsDto;

    @ViewChild('map') mapDiv: any;

    private timer: Observable<number>;

    private sub: Subscription;

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
        this.timer = Observable.interval(PLANES_REFRESH_INTERVAL);
    }

    ngOnInit() {
        this.initMap();
        
    }

    ngAfterViewInit(): void {
        this.sub = this.timer.subscribe((i) => {
            if (i % SERVER_POST_FOR_PLANES_INTERVAL == 0) {
                this.loadAndUpdatePlanes();
            }
            else {
                this.updatePositions(false)
            }
        });
    }

    ngOnDestroy() {
        this.sub.unsubscribe();
    }

    initMap() {
        var uluru = { lat: 54, lng: 17 };
        var map = new google.maps.Map(this.mapDiv.nativeElement, {
            zoom: 4,
            center: uluru
        });
        this.map = map;

        this.loadAndUpdatePlanes();
    }

    loadAndUpdatePlanes() {
        this.planeService.findAllPlanesLocation().then((data: Array<FlightDetailsDto>) => {
            this.updateData(data);
            this.updatePositions(true);
        })
    }

    updateData(data: Array<FlightDetailsDto>) {
        this.storedLocationData = data;
        this.lastUpdate = new Date();
    }

    updatePositions(oryginal: boolean) {
        let data: Array<FlightDetailsDto> = this.storedLocationData;
        let tmpMarkers = {};

        for (let value of data) {
            let distance = this.planeService.calculateDistance(this.lastUpdate, value.velocity, value.timeElapsed);
            let toTravel = value.flightDistance - (value.distanceTraveled + (distance / 1000));
            let destPoint;
            if (toTravel > 0) {
                // console.log(value.flightDistance + " >= " + value.distanceTraveled + " + " + (distance / 1000));
                destPoint = this.planeService.calculateDestinationPoint(value, distance);
            } else {
                //set airport latlng
                destPoint = { latlng: {lat: value.destinationLatitude, lng: value.destinationLongitude}, course: null};
            }
            let flightRouteSid = value.flightRouteSid;
            let latlng = destPoint.latlng;
           
            if (destPoint.course) this.icon["rotation"] = destPoint.course;

            if (this.markers[flightRouteSid]) {
                var marker = this.markers[flightRouteSid];
                marker.setPosition(latlng);
                marker.velocity = value.velocity;
                marker.timeElapsed=value.timeElapsed;
                marker.averageFuelConsumption = value.averageFuelConsumption;
                marker.flightDistance = value.flightDistance;
                marker.distanceTraveled = value.distanceTraveled;
                marker.destinationCity = value.destinationCity,
                marker.setIcon(this.icon);
                tmpMarkers[flightRouteSid] = marker;

                this.markers[flightRouteSid] = undefined;
                delete this.markers[flightRouteSid];
            } else {
                if (oryginal) {
                    tmpMarkers[flightRouteSid] = this.createMarker(latlng, value, flightRouteSid);
                }
            }
        }

        for (let m in this.markers) {
            if (this.markers.hasOwnProperty(m)) {
                this.markers[m].setMap(null);
            }
        }
        this.markers = tmpMarkers;
    }

    createMarker(latlng: any, value: any, flightRouteSid: string) {
        var marker = new google.maps.Marker({
            position: latlng,
            title: value.course,
            icon: this.icon,
            map: this.map,
            flightRouteSid:flightRouteSid,
            planeName:value.planeName,
            planeRegistration:value.planeRegistration,
            velocity:value.velocity,
            averageFuelConsumption:value.averageFuelConsumption,
            timeElapsed:value.timeElapsed,
            sourceCity:value.sourceCity,
            destinationCity:value.destinationCity,
            flightDistance:value.flightDistance,
            distanceTraveled:value.distanceTraveled
        });
        marker.set('mapComponent', this)
        marker.addListener('click', function() {
            var that = this.get('mapComponent');


            if(!that.selectedFlightDetailsDto) {
                that.selectedFlightDetailsDto = {flightRouteSid:""};
            }
           if(that.toggled === "toggled") {
                if(that.selectedFlightDetailsDto.flightRouteSid === flightRouteSid) {
                    that.toggled = "";
                    that.selectedFlightDetailsDto = {flightRouteSid:""};
                } else {
                    that.createFligtDetails(that.selectedFlightDetailsDto,this);
                }
           } else {
                that.toggled = "toggled";
                that.createFligtDetails(that.selectedFlightDetailsDto,this);
           }

        });
        return marker;
    }

    createFligtDetails (flightDetailsDto:any,marker:any) {
        flightDetailsDto['flightRouteSid']=marker.flightRouteSid;
        flightDetailsDto['planeName']=marker.planeName;
        flightDetailsDto['planeRegistration']=marker.planeRegistration;
        flightDetailsDto['latitude']=marker.position.lat();
        flightDetailsDto['longitude']=marker.position.lng();
        flightDetailsDto['velocity']=marker.velocity;
        flightDetailsDto['timeElapsed']=marker.timeElapsed;
        flightDetailsDto['averageFuelConsumption']=marker.averageFuelConsumption;
        flightDetailsDto['course']=(marker.icon.rotation+360)%360;
        flightDetailsDto['sourceCity']=marker.sourceCity;
        flightDetailsDto['destinationCity']=marker.destinationCity;
        flightDetailsDto['flightDistance']=marker.flightDistance;
        flightDetailsDto['distanceTraveled']=marker.distanceTraveled;
    }

}
