import { Component, Input, OnInit, forwardRef } from '@angular/core';
import { FormGroup, FormControl, AbstractControl, ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { ErrorMessagesComponent } from 'common/validations';
import { TranslateService } from 'ng2-translate';
import { DatePipe } from "@angular/common";

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

    onChange: any = () => { };
    onTouched: any = () => { };

    control: AbstractControl;

    constructor(private translateService: TranslateService, private datePipe: DatePipe) { }

    ngModelChange(event: any){
        console.log("model change", event)
        // this.control.patchValue(this.datePipe.transform(event, 'yyyy-MM-ddThh:mm:ss'));
    }

    registerOnChange(fn: any) {
        this.onChange = fn;
    }

    registerOnTouched(fn: any) {
        this.onTouched = fn;
    }

    writeValue(value: any) {
        this._value = value;
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
    }

    getPlaceholder() {
        let x = "";
        if (this.placeholder) {
            this.translateService.get(this.placeholder).subscribe((value: string) => x = value);
        }
        return x;
    }
}