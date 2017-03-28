import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { PlaneService } from 'app/services';
import { NotificationService } from 'app/services';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
    selector: 'page-flight-route-details',
    templateUrl: './flightroutes.details.component.html',
})
export class FlightRouteDetailsComponent implements OnInit {

    sid: string = null;
    flightRouteForm: FormGroup;

    constructor(private fb: FormBuilder, private planeService: PlaneService, private ns: NotificationService, private route: ActivatedRoute) {
        route.queryParams.subscribe((params: Params) => {
            this.sid = params['sid'];
        });
    }

    ngOnInit() {
        if (this.sid) {

        }
        this.createForm();
    }

    createForm() {
        this.flightRouteForm = this.fb.group({
            sid: ['']
        });
    }

    onSubmit() {
        alert('dojpa')
    }

}