import { Component, Input, OnInit, forwardRef } from '@angular/core';
import { FormControl, AbstractControl, ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
    selector: 'ap-form-input',
    template: `
    <div ngClass="form-group col-sm-{{size}}">
        <label [for]="property">{{ label | translate }}</label>
        <ng-content></ng-content>
        <error-messages [control]="control"></error-messages>
    </div>
    `
})
export class FormInput {
    @Input() size: number = 12;
    @Input() label: string;
    @Input() property: string;
    @Input() validators: Array<string>;

    control: AbstractControl;

    setControl(control: AbstractControl){
        this.control = control;
    }

    getControl(){
        return this.control;
    }
}