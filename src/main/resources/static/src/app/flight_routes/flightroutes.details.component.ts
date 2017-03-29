import { Component, OnInit, Input } from '@angular/core';
import { AbstractControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { PlaneService } from 'app/services';
import { NotificationService } from 'app/services';
import { ActivatedRoute, Params } from '@angular/router';
import { SafeHtml, DomSanitizer } from "@angular/platform-browser";
import { Observable } from "rxjs/Observable";
import { SearchRequest } from "common/table";

@Component({
    selector: 'page-flight-route-details',
    templateUrl: './flightroutes.details.component.html',
})
export class FlightRouteDetailsComponent implements OnInit {

    sid: string = null;
    flightRouteForm: FormGroup;

    searchRequest: SearchRequest = {
        pageRequest: { sort: { orders: [] }, page: 0, size: 25 },
        filter: { name: "" }
    };

    getData(keyword: string) {
        let tmp : any = this.planeService.findPlanes(this.searchRequest).then((response) => { 
            return response.entities
        });

        return Observable.fromPromise(tmp);
    }


    constructor(private fb: FormBuilder, private planeService: PlaneService, private ns: NotificationService, private route: ActivatedRoute, private _sanitizer: DomSanitizer) {
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
            sid: [''],
            plane: ""
            // destination: ""
        });
    }

    autocompleListFormatter = (data: any): SafeHtml => {
        let html = `<span>${data.name} - ${data.registration}</span>`;
        return this._sanitizer.bypassSecurityTrustHtml(html);
    }

    onSubmit() {
        alert('dojpa')
    }

}