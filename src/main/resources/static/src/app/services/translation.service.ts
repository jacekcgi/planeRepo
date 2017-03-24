import { Injectable } from '@angular/core';
import { TranslateService } from 'ng2-translate';

@Injectable()
export class TranslationService {

    constructor(private translateService : TranslateService) {}

    get(key: string) {
        let value = key;
        this.translateService.get(key).subscribe((translation: string) => { value = translation });
        return value;
    }
}