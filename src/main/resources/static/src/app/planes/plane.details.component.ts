import { Component } from '@angular/core';
import { AbstractControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { PlaneService } from 'app/services';
import { NotificationService } from 'app/services';
import { ActivatedRoute, Params } from '@angular/router';
import * as moment from "moment";

@Component({
  selector: 'page-plane-details',
  templateUrl: './plane.detail.component.html',
})
export class PlaneDetailsComponent {
  sid: string = null;
  planeForm: FormGroup;

  momentValue: any = moment();

  constructor(private fb: FormBuilder, private planeService: PlaneService, private ns: NotificationService, private route: ActivatedRoute) {
    route.queryParams.subscribe((params: Params) => {
        this.sid = params['sid'];
      });
   }

  ngOnInit() {
    if (this.sid) {
      this.planeService.getPlane(this.sid).then((plane) => {
        this.planeForm.patchValue(plane);
      });
    }
    this.createForm();
  }

  createForm() {
    this.planeForm = this.fb.group({
      sid: [''],
      name: ['', Validators.required],
      registration: ['', Validators.required],
      description: [''],
      datetime: [null, Validators.required]
    });
  }

  public setMoment(moment: any): any {
    this.momentValue = moment;
    // Do whatever you want to the return object 'moment'
}

  onSubmit() {
    this.planeService.save(this.planeForm, this.planeForm.value).then((response) => {
      this.ns.success('airplane.successCreated');
      this.sid = response["sid"];
      this.planeForm.patchValue(response);
      // this.planeForm.reset(); now no need
    });
  }

}