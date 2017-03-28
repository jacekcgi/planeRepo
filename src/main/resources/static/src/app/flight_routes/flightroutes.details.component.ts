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
    tmp : any

    searchRequest: SearchRequest = {
        pageRequest: { sort: { orders: [] }, page: 0, size: 25 },
        filter: { name: "" }
    };

    getData(keyword: string) {
        this.tmp = this.planeService.findPlanes(this.searchRequest).then((response) => { 
            return response.entities
            // return Observable.from(response.entities)//response.entities
        });
        console.log("tmp", this.tmp)
        let filteredList = this.continents.filter(el => el.name.includes(keyword))
        console.log("list", filteredList)
        let tmp2 = Observable.from(this.tmp);
        console.log("tmp2", tmp2)
        let tmp3 = Observable.of(filteredList);
        return tmp3;
        // return tmp;
    }

    public continents = [{
        id: 1,
        name: 'Asia',
        population: '4,157,300,000'
    }, {
        id: 2,
        name: 'Africa',
        population: '1,030,400,000'
    }, {
        id: 3,
        name: 'Europe',
        population: '738,600, 000'
    }, {
        id: 4,
        name: 'North America',
        population: '461,114,000'
    }, {
        id: 5,
        name: 'South America',
        population: '390,700,000'
    }, {
        id: 6,
        name: 'Australia',
        population: '36,700,000'
    }, {
        id: 7,
        name: 'Antartica',
        population: 0
    }
    ];

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
            continent: ""
        });
    }

    autocompleListFormatter = (data: any): SafeHtml => {
        console.log(data)
        let html = `<span>${data.name}</span>`;
        return this._sanitizer.bypassSecurityTrustHtml(html);
    }

    onSubmit() {
        alert('dojpa')
    }

}