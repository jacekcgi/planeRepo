import { Component } from '@angular/core';
import { TranslateService } from 'ng2-translate';
import { LanguageService } from 'app/services'
import { Cookie } from 'ng2-cookies';
import { AppConfig } from "config";

@Component({
    selector: 'ap-languages',
    templateUrl: './language.component.html'
})
export class LanguageComponent {
    languages: any[];

    constructor(private translate: TranslateService, private languageService: LanguageService){
        this.languageService.findLanguages().then((value: any) => {
            this.languages = value;
        });
    }

    onLanguageChange(language: any) {
        this.translate.use(language.locale);
        Cookie.set(AppConfig.languageCookieName, language.locale, null, "/");
    }
}
