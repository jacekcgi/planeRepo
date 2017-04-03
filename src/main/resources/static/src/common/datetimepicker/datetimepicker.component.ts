import { Component, Input, OnInit, forwardRef } from '@angular/core';
import { FormGroup, FormControl, AbstractControl, ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { ErrorMessagesComponent } from 'common/validations';
import { TranslateService } from 'ng2-translate';
import { DatePipe } from "@angular/common";

import * as moment from "moment";

@Component({
    selector: 'ap-datetimepicker',
    templateUrl: './datetimepicker.component.html',
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => DateTimepickerComponent),
            multi: true
        }
    ]
})
export class DateTimepickerComponent implements ControlValueAccessor, OnInit {
    @Input() size: number = 12;
    @Input() label: string;
    @Input() formGroup: FormGroup;
    @Input('value') _value: string = "";
    @Input('formControlName') property: string;
    @Input() placeholder: string;

    public clicked: boolean = false;

    internalDate: Date;
    internalTime: Date;
    internalDateTime: Date = new Date();

    onChange: any = () => { };
    onTouched: any = () => { };

    control: AbstractControl;

    constructor(private translateService: TranslateService, private datePipe: DatePipe) {
        moment.locale("pl")
    }

    public toggleDP(): boolean {
        this.clicked = !this.clicked;
        return !this.clicked;
    }

    ngModelChange(event: any) {
        console.log("model change", event)
    }

    registerOnChange(fn: any) {
        this.onChange = fn;
    }

    registerOnTouched(fn: any) {
        this.onTouched = fn;
    }

    writeValue(obj: any): void {

        if (obj === this.internalDateTime) {
            return;
        }
        if (obj && obj instanceof Date) {
            this.internalDateTime = obj;
            return;
        }
        this.internalDateTime = obj ? new Date(obj) : void 0;
    }

    // execute if our input field has been changed
    onInputChange(val: string) {
        this.value = val;
    }

    // mutators for value
    get value() {
        return this._value;
    }

    set value(val) {
        this._value = val;
        this.onChange(val);
        this.onTouched();
    }

    ngOnInit() {
        this.control = this.formGroup.get(this.property)

        this.internalDate = this.internalDateTime;
        this.internalTime = this.internalDateTime;
    }

    getPlaceholder() {
        let x = "";
        if (this.placeholder) {
            this.translateService.get(this.placeholder).subscribe((value: string) => x = value);
        }
        return x;
    }

    updateDateTime() {

        let newDate: Date = new Date();
        newDate.setTime(this.internalDate.getTime());
        newDate.setHours(this.internalTime.getHours());
        newDate.setMinutes(this.internalTime.getMinutes());
        newDate.setSeconds(this.internalTime.getSeconds());
        newDate.setMilliseconds(this.internalTime.getMilliseconds());
        this.internalDateTime = newDate;
        // if (this.milliseconds) {
        //     this.cd.viewToModelUpdate(newDate.getTime());
        // }
        // else {
        //     this.cd.viewToModelUpdate(newDate);
        // }
    }


    get date(): Date {

        return this.internalDate;
    }

    set date(date: Date) {

        this.internalDate = date;
        this.updateDateTime();
    }


    get time(): Date {

        return this.internalTime;
    }

    set time(time: Date) {

        this.internalTime = time;
        this.updateDateTime();
    }

    
}