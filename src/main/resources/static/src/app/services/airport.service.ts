import { ActionService } from 'app/services/action.service';
import { Injectable, Inject } from '@angular/core';


declare var google: any;

@Injectable()
export class AirportService {
    constructor( @Inject(ActionService) private actions: ActionService) {
    }

    findAirports(request: any) {
        return this.actions.post("/find/airports", request);
    }

    findAllAirports() {
        return this.actions.get("/find/airports");
    }

    findAirportsOnZoomLvl(zoom: number) {
        return this.actions.get("/findAirportsOnCurrZoom/"+zoom);
    }
}
