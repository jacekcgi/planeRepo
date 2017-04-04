import { ActionService } from 'app/services/action.service';
import { Injectable, Inject } from '@angular/core';
import { Cookie } from "ng2-cookies";
import { AppConfig } from "config";
import { Subject } from "rxjs/Subject";

@Injectable()
export class LanguageService {

    private languageChange = new Subject<string>();

    languageChanged$ = this.languageChange.asObservable();

    constructor( @Inject(ActionService) private actions: ActionService) {
    }

    findLanguages() {
        return this.actions.get("/laguages");
    }

    getCurrentLanguage(): string {
        let language = Cookie.get(AppConfig.languageCookieName);
        return language ? language : AppConfig.deafultLanguage;
    }

    onLanguageSet(language: string) {
        this.languageChange.next(language);
    }
}
