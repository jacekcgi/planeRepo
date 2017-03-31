import { Component, Input, OnInit, forwardRef } from '@angular/core';
import { FormGroup, FormControl, AbstractControl, ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';


@Component({
    selector: 'ap-checkbox',
    template: `
    <div ngClass="form-group col-sm-{{size}}">
        <label class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" (change)="onCheckboxChange($event.target.checked)" [checked]=" _value">
            <span class="custom-control-indicator"></span>
            <span class="custom-control-description">{{label | translate}}</span>
        </label>
        <error-messages [control]="control"></error-messages>
    </div>
    `,
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => CheckboxComponent),
            multi: true
        }
    ]
})
export class CheckboxComponent implements ControlValueAccessor, OnInit {
    @Input() size: number = 12;
    @Input() label : string;
    @Input() formGroup: FormGroup;
    @Input('value') _value: boolean = false;
    @Input('formControlName') property: string;

    constructor() { }

    onChange: any = () => { };
    onTouched: any = () => { };

    control: AbstractControl;

    registerOnChange(fn: any) {
       this.onChange = fn;
    }

    registerOnTouched(fn: any) {
        this.onTouched = fn;
    }

    writeValue(value: any) {
        this._value = value;
    }

    ngOnInit() {
        this.control = this.formGroup.get(this.property);
    }

    onCheckboxChange(val: boolean){
        this.value = val;
        this.onChange(val);
        this.onTouched();
        console.log(this.value);
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
}