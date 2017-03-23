import { Component } from '@angular/core';
import { TranslateService } from 'ng2-translate';
import { NotificationService } from 'app/services';
import { Cookie } from "ng2-cookies";
import { AppConfig } from "config";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    notificationOptions = {};

    constructor(translate: TranslateService,
        ns: NotificationService) {
        // this language will be used as a fallback when a translation isn't found in the current language
        let language = Cookie.get(AppConfig.languageCookieName);
        translate.setDefaultLang(language ? language : AppConfig.deafultLanguage);

        this.notificationOptions = ns.getOptions();
    }
}
