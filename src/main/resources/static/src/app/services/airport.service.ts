import { ActionService } from 'app/services/action.service';
import { Injectable, Inject } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AxiosResponse } from "axios";

declare var google: any;

@Injectable()
export class AiportService {
    constructor( @Inject(ActionService) private actions: ActionService) {
    }

    findAiports(request: any) {
        return this.actions.post("/find/airports", request);
    }

    findAirportsOnZoomLvl(zoom: number) {
        return this.actions.get("/findAirportsOnCurrZoom/"+zoom);
    }
}
