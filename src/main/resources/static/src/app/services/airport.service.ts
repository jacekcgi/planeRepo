import { ActionService } from 'app/services/action.service';
import { Injectable, Inject } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AxiosResponse } from "axios";
import { SearchRequest } from "common/table";

@Injectable()
export class AirportService {
    constructor( @Inject(ActionService) private actions: ActionService) {
    }

    findAirports() {
        return this.actions.get("/findAirports");
    }

    findAirportsOnZoomLvl(zoom: number) {
        return this.actions.get("/getAirports/"+zoom);
    }

}