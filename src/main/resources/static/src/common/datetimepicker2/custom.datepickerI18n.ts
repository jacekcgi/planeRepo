import { Injectable, OnInit, OnDestroy } from '@angular/core';
import { NgbDatepickerI18n } from '@ng-bootstrap/ng-bootstrap';
import { Subscription } from "rxjs/Rx";
import * as moment from "moment";
import { LanguageService, TranslationService } from "app/services";

@Injectable()
export class CustomDatepickerI18n extends NgbDatepickerI18n {

  constructor(private translationService: TranslationService) {
    super();

  }

  getWeekdayShortName(weekday: number): string {
    return this.translationService.get('datepicker.weekdays.' + weekday);
  }
  getMonthShortName(month: number): string {
    return this.translationService.get('datepicker.months.' + month);
  }
  getMonthFullName(month: number): string {
    return this.getMonthShortName(month);
  }
}