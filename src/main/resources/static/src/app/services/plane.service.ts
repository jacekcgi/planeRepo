import { ActionService } from 'app/services/action.service';
import { Injectable, Inject } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AxiosResponse } from "axios";
import { FlightDetailsDto } from "app/domain";

declare var google: any;

const MILISECONDS_IN_ONE_HOUR = 3600000;

@Injectable()
export class PlaneService {
    constructor( @Inject(ActionService) private actions: ActionService) {
    }

    getPlane(sid: string) {
        return this.actions.get("/get/plane/" + sid);
    }

    findPlanes(request: any) {
        return this.actions.post("/find/planes", request);
    }

    findPlanesIDs() {
        return this.actions.get("/planeIdList");
    }

    findAllPlanesLocation() {
        return this.actions.get("/allPlanesLocation/");
    }

    getFlightDetails(sid:string) {
         return this.actions.get("/get/flightDetails/" + sid);
    }

    findFlightTrail(sid:string) {
         return this.actions.get("/find/flightTrail/" + sid);
    }

    save(form: FormGroup, data: object) {
        return this.actions.postForm("/plane", data, form);
    }

    calculateDistance(lastUpdate: Date, velocity: number, timeElapsed: number) {
        var elapsedTime = ((lastUpdate ? (new Date().getTime() - lastUpdate.getTime()) : 0) + timeElapsed) / MILISECONDS_IN_ONE_HOUR; // in hours
        return velocity * elapsedTime * 1000; //im meters
    }

    calculateDestinationPoint(flightData: FlightDetailsDto, distance: number) {

        var from = new google.maps.LatLng(flightData.currentLatitude, flightData.currentLongitude);
        var dest = new google.maps.LatLng(flightData.destinationLatitude, flightData.destinationLongitude);

        var finalBearing = google.maps.geometry.spherical.computeHeading(from, dest);
        var latlng = google.maps.geometry.spherical.computeOffset(from, distance, finalBearing);
        var point = { latlng: latlng, course: finalBearing };

        return point;
    }

     calculateMaxDistance(remainingFuel:number,averageFuelConsumption:number) {
          return  remainingFuel/averageFuelConsumption*0.9;
    }

}
