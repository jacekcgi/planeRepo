import { Component, Input, OnInit, forwardRef, ViewChild } from '@angular/core';
import { FormGroup, FormControl, AbstractControl, ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { ErrorMessagesComponent } from 'common/validations';
import { TranslateService } from 'ng2-translate';
import { NgbDatepickerI18n } from '@ng-bootstrap/ng-bootstrap';
import { CustomDatepickerI18n } from './custom.datepickerI18n';
//import * as moment from "moment";



@Component({
    selector: 'ap-datetimepicker2',
    templateUrl: './datetimepicker2.component.html',
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => DateTimepicker2Component),
            multi: true
        },
        { provide: NgbDatepickerI18n, useClass: CustomDatepickerI18n }
    ]
})
export class DateTimepicker2Component implements ControlValueAccessor, OnInit {
    @Input() size: number = 12;
    @Input() label: string;
    @Input() formGroup: FormGroup;
    @Input('formControlName') property: string;
    @Input() placeholder: string;

    @Input('dateFormat') dateFormat: string = 'LLLL';

    @ViewChild("dp") dpComponent: any;
    _dateModel: any;
    _timeModel: any;

    onChange: any = () => { };
    onTouched: any = () => { };

    control: AbstractControl;


    constructor(private translateService: TranslateService) {

    }

    registerOnChange(fn: any) {
        this.onChange = fn;
    }

    registerOnTouched(fn: any) {
        this.onTouched = fn;
    }

    writeValue(obj: any): void {
        const modelValue = this.control.value;
        if (modelValue && modelValue instanceof Date) {
            this.dateModel = { year: modelValue.getFullYear(), month: modelValue.getMonth() + 1, day: modelValue.getDate() };
        }

    }

    //get accessor
    get dateModel(): any {
        return this._dateModel;
    };

    get timeModel(): any {
        return this._timeModel;
    }

    //set accessor including call the onchange callback
    set dateModel(v: any) {
        if (v) {
            // console.log("setting date value", v);
            this._dateModel = v;
            if (this.timeModel) {
                const timeModel = this.timeModel;
                const vDate = new Date(v.year, v.month - 1, v.day, timeModel.hour, timeModel.minute);
                this.onChange(vDate);
            }
        } else {
            // wrong value - nullable model
            this.onChange(null);
        }
        this.onTouched();
    }

    set timeModel(v: any) {
        if (v) {
            // console.log("setting time value", v);
            this._timeModel = v;
            if (this.dateModel) {
                const dateModel = this.dateModel;
                const vDate = new Date(dateModel.year, dateModel.month - 1, dateModel.day, v.hour, v.minute);
                // console.log("setting time model", vDate);
                this.onChange(vDate);
            }
            this.onTouched();
        } else {
            // wrong value - nullable model
            this.onChange(null);
        }
    }

    // toggle component
    onClick() {
        console.log("model",this.formGroup.value);
        this.dpComponent.toggle();
    }

    ngOnInit() {
        this.control = this.formGroup.get(this.property);
    }

    getPlaceholder() {
        let x = "";
        if (this.placeholder) {
            this.translateService.get(this.placeholder).subscribe((value: string) => x = value);
        }
        return x;
    }
}
