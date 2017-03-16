import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { SERVER_ERROR } from 'common/validations';
import { PlaneService } from 'app/services/plane.service';

@Component({
  selector: 'page-home',
  templateUrl: './home.component.html',
})
export class HomeComponent {

  userForm: FormGroup;

  constructor(private fb: FormBuilder, private planeService: PlaneService) {
    this.userForm = this.fb.group({
      registration: new FormControl(''),
      password: ['', Validators.required],
      address: fb.group({
        street: ['', Validators.required]
      })
    })
  }

  onSubmit() {
    console.log(this.userForm.value);
    this.planeService.save2(this.userForm, this.userForm.value);
  }
}
