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

}
