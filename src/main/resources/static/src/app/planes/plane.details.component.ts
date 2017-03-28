import { Component } from '@angular/core';
import { AbstractControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { PlaneService } from 'app/services';
import { NotificationService } from 'app/services';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'page-plane-details',
  templateUrl: './plane.detail.component.html',
})
export class PlaneDetailsComponent {
  sid: string = null;
  planeForm: FormGroup;

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
      description: ['']
    });
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