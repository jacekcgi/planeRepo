import { ActionService } from 'app/services/action.service';
import { Injectable, Inject } from '@angular/core';

@Injectable()
export class LanguageService {
    constructor( @Inject(ActionService) private actions: ActionService) {
    }

    findLanguages() {
        return this.actions.get("/laguages");
    }

}