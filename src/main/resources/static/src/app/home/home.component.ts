import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { PlaneService } from 'app/services';

@Component({
  selector: 'page-home',
  templateUrl: './home.component.html',
})
export class HomeComponent  {

  userForm: FormGroup;

  constructor(private fb: FormBuilder, private planeService: PlaneService) {
    this.userForm = this.fb.group({
      registration: ['', Validators.required],
      password: ['', Validators.required],
      address: fb.group({
        street: ['', Validators.required]
      })
    })
  }

  onSubmit() {
    console.log(this.userForm);
    // this.planeService.save2(this.userForm, this.userForm.value);
  }
}
