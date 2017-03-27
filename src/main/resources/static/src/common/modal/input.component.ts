import { Component, Input, OnInit, forwardRef } from '@angular/core';
import { FormGroup, FormControl, AbstractControl, ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { ErrorMessagesComponent } from 'common/validations';
import { TranslateService } from 'ng2-translate';
import { FormInput } from 'common/modal/form.input.component'

@Component({
    selector: 'ap-input2',
    template: `
    <ap-form-input [size]="size" [label]="label" [property]="property" [validators]="validators">
        <input [ngClass]="{'invalid': control && !control.valid && control.touched}" type="text" (input)="onInputChange($event.target.value)" [value]="value"
            class="form-control" [name]="property" [placeholder]="getPlaceholder()">
    </ap-form-input>
    `,
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => InputComponent2),
            multi: true
        }
    ]
})
export class InputComponent2 extends FormInput implements ControlValueAccessor {

    @Input('value') _value: string = "";
    @Input() placeholder: string;

    onChange: any = () => { };
    onTouched: any = () => { };

    constructor(private translateService: TranslateService ) {
        super();
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

    getPlaceholder() {
        let x = "";
        if (this.placeholder) {
            this.translateService.get(this.placeholder).subscribe((value: string) => x = value);
        }
        return x;
    }
}