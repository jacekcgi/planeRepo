import { Component, Input, OnInit } from '@angular/core';
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
export class ErrorMessagesComponent implements OnInit {

  // given form model
  @Input() form: FormGroup;
  // given property to form control
  @Input() property: string;
  // prefix to find translations
  @Input() prefix: string = 'validations';

  private formControl: AbstractControl;

  constructor(private translateService: TranslateService) {
  }

  ngOnInit() {
    this.formControl = this.form.get(this.property);
    if (this.formControl == null) {
      console.error("cannot find abstract control by property " + this.property + " in: ", this.formControl);
    }
  }

  get errors() {
    let fieldErrors: string[] = [];
    if (this.formControl) {
      for (let propertyName in this.formControl.errors) {
        if (this.formControl.errors.hasOwnProperty(propertyName)) {
          if (propertyName === SERVER_ERROR) { // server errors
            let serverErrors = this.formControl.errors[propertyName];
            serverErrors.map((e: string) => {
              fieldErrors.push(e); // because server response translated errors
            })
          } else if (this.formControl.touched) { // gui errors
            let params = this.formControl.errors[propertyName];
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