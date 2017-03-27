import { Component, Input, Output, EventEmitter, ContentChildren, QueryList } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { FormInput } from './form.input.component'



@Component({
    selector: 'ap-form',
    template: `
    <form [formGroup]="formGroup" (ngSubmit)="submit()">
        <ng-content></ng-content>
    </form>
    `
})
export class Form {
    @ContentChildren(FormInput) inputs : QueryList<FormInput>; 
    formGroup: FormGroup;

    @Output() onSubmit: EventEmitter<any> = new EventEmitter();
    
    constructor(private fb: FormBuilder) {

    }

    ngAfterContentInit() {
        let controlsConfig = {};
        this.inputs.map((item: FormInput, index, array) => {
            controlsConfig[item.property] = item.validators;
        });
        console.log(controlsConfig);
        this.formGroup = this.fb.group(controlsConfig);
        this.inputs.map((item: FormInput, index, array) => {
            item.setControl(this.formGroup.get(item.property));
        });

        console.log(this.inputs);
        console.log(this.formGroup);
    }

    submit() {
        this.onSubmit.emit(this.formGroup);
    }
}