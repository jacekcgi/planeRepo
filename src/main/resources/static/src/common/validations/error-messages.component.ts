import { Component, Input } from '@angular/core';
import { FormGroup, FormControl, AbstractControl } from '@angular/forms';
import { TranslateService } from 'ng2-translate';

export const SERVER_ERROR = 'serverError';

@Component({
  selector: 'error-messages',
  template: `
    <div *ngIf="errors.length > 0">
      <span style="color: red" *ngFor="let e of errors"> {{ e }} <br/></span>
    </div>
  `
})
export class ErrorMessagesComponent {

  // prefix to find translations
  @Input() prefix: string = 'validations';
  // given form control object
  @Input() control: AbstractControl;

  constructor(private translateService: TranslateService) {
  }

  get errors() {
    let fieldErrors: string[] = [];
    if (this.control) {
      for (let propertyName in this.control.errors) {
        if (this.control.errors.hasOwnProperty(propertyName)) {
          if (propertyName === SERVER_ERROR) { // server errors
            let serverErrors = this.control.errors[propertyName];
            serverErrors.map((e: string) => {
              fieldErrors.push(e); // because server response translated errors
            })
          } else if (this.control.touched) { // gui errors
            let params = this.control.errors[propertyName];
            this.translateService.get(this.prefix + '.' + propertyName, params).subscribe((value: string) => {
              fieldErrors.push(value);
            });
          }
        }
      }
    }
    return fieldErrors;
  }
}