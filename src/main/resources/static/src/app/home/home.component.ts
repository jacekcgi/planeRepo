import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { SERVER_ERROR } from 'common/validations';

@Component({
  selector: 'page-home',
  templateUrl: './home.component.html',
})
export class HomeComponent {

  userForm: FormGroup;

  private serverResponse: any =
  [{
    field: 'password',
    message: 'Dupa jasiu'
  },
  {
    field: 'password',
    message: 'Olaboga'
  },
  {
    field: 'address.street',
    message: 'Addres street is invalid'
  }]


  constructor(private fb: FormBuilder) {
    this.userForm = this.fb.group({
      name: new FormControl('', [Validators.required, Validators.minLength(10)]),
      password: ['', Validators.required],
      address: fb.group({
        street: ['', Validators.required]
      })
    })
  }

  onSubmit() {
    setTimeout(() => {
      this.applyErrors(this.userForm, this.serverResponse);
    }, 2000)
  }

  // move to action service later
  applyErrors(form: FormGroup, data: any): void {
    // prepare errors object
    if (data && data.length > 0) {
      let errors: any = [];
      _.map(data, (e: any) => {
        let fieldErrors = _.find(errors, { field: e.field });
        if (fieldErrors) {
          fieldErrors.errors.push(e.message)
        } else {
          errors.push({ field: e.field, errors: [e.message] });
        }
      })
      // iterate through all errors fields reponsed from server
      errors.map((fieldError: any) => {
        // get nested form control
        let formControl: AbstractControl = form.get(fieldError.field);
        // prepare object to set errors
        let fieldErrors: any = {};
        // put key as server error and value as messages errors
        fieldErrors[SERVER_ERROR] = fieldError.errors;
        // set it into form control
        formControl.setErrors(fieldErrors);
      });
    }
  }
}
