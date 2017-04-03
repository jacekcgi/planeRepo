import { Component, OnInit, Input } from '@angular/core';
import { AbstractControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { PlaneService } from 'app/services';
import { NotificationService, AiportService, FlightRoutesService } from 'app/services';
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

    planesSearchRequest: SearchRequest = {
        pageRequest: { sort: { orders: [] }, page: 0, size: 25 },
        filter: { name: "" }
    };

    airportsSearchRequest: SearchRequest = {
        pageRequest: { sort: { orders: [] }, page: 0, size: 25 },
        filter: { name: "" }
    };

    getPlanes(keyword: string) {
        this.planesSearchRequest.filter = { name: keyword };
        let tmp: any = this.planeService.findPlanes(this.planesSearchRequest).then((response) => {
            return response.entities
        });

        return Observable.fromPromise(tmp);
    }

    getAirports(keyword: string) {
        this.airportsSearchRequest.filter = { name: keyword };
        let tmp: any = this.airportService.findAiports(this.airportsSearchRequest).then((response) => {
            return response.entities
        });

        return Observable.fromPromise(tmp);
    }

    constructor(private fb: FormBuilder, private planeService: PlaneService, private ns: NotificationService, private route: ActivatedRoute, private _sanitizer: DomSanitizer,
        private airportService: AiportService, private flightRouteService: FlightRoutesService) {
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
            plane: ['', Validators.required],
            source: ['', Validators.required],
            destination: ['', Validators.required],
            startDate: '',
            incomingDate: ''
            // incomingDate: ['', Validators.required]
            // destination: ""
        });
        // this.flightRouteForm.patchValue({
        //     plane: {
        //         sid: "8f9572ad984046b489f90589f1580925",
        //         name: "Fajny samolot"
        //     }
        // });
    }

    autocompleListFormatter = (data: any): SafeHtml => {
        let html = `<span>${data.name} - ${data.registration}</span>`;
        return this._sanitizer.bypassSecurityTrustHtml(html);
    }

    autocompleValueListFormatter = (data: any): SafeHtml => {
        console.log("data",data);
        let html = `<span>${data.name} - ${data.registration}</span>`;
        return this._sanitizer.bypassSecurityTrustHtml(html);
    }

    onSubmit() {
        this.flightRouteService.save(this.flightRouteForm, this.flightRouteForm.value).then((response) => {
            this.ns.success('airplane.successCreated');
            this.sid = response["sid"];
            this.flightRouteForm.patchValue(response);
            // this.planeForm.reset(); now no need
        });
    }

}