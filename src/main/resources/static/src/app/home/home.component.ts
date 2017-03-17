import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { SERVER_ERROR } from 'common/validations';
import { PlaneService } from 'app/services/plane.service';
import { NotificationsService } from 'angular2-notifications';

@Component({
  selector: 'page-home',
  templateUrl: './home.component.html',
})
export class HomeComponent {

  userForm: FormGroup;

  constructor(private fb: FormBuilder, private planeService: PlaneService, private notify:  NotificationsService) {
    this.userForm = this.fb.group({
      registration: new FormControl(''),
      password: ['', Validators.required],
      address: fb.group({
        street: ['', Validators.required]
      })
    })
  }

  onSubmit() {
    this.notify.success("test", "test 23");
    this.notify.error("test", "test 23");
    // this.notify.alert("test", "test 23");
    // this.notify.info("test", "test 23");
    // console.log(this.userForm.value);
    this.planeService.save2(this.userForm, this.userForm.value);
  }
}
