import { Injectable, Inject } from '@angular/core';
import { ActionService } from 'app/services/action.service';

@Injectable()
export class AirportService {
    constructor( @Inject(ActionService) private actions: ActionService) {
    }

    findAirports(request: any) {
        return this.actions.post("/find/airports", request);
    }

}