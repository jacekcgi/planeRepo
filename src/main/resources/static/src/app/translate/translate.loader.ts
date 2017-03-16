import { Injectable } from "@angular/core";

import { TranslateLoader } from 'ng2-translate';
import { Observable } from 'rxjs/Observable';
import { TRANSLATIONS } from './translations';

@Injectable()
export class CustomLoader implements TranslateLoader {
    getTranslation(lang: string): Observable<any> {
        return Observable.of(TRANSLATIONS[lang]);
    }
}