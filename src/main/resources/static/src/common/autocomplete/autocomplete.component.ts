import { Component, Input, OnInit, forwardRef } from '@angular/core';
import { FormGroup, FormControl, AbstractControl, ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { ErrorMessagesComponent } from 'common/validations';
import { TranslateService } from 'ng2-translate';
import { SearchRequest } from "common/table";
import { Observable } from "rxjs/Rx";
import { PlaneService } from "app/services";
import { SafeHtml, DomSanitizer } from "@angular/platform-browser";

@Component({
    selector: 'ap-autocomplete',
    templateUrl: './autocomplete.component.html',
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => AutocompleteComponent),
            multi: true
        }
    ]
})
export class AutocompleteComponent implements ControlValueAccessor, OnInit {

    defaultListFormatter = (data: any): string => {
        return `<span>${data.name}</span>`;
    }

    @Input() size: number = 12;
    @Input() label: string;
    @Input() formGroup: FormGroup;
    value: string = "";
    @Input('formControlName') property: string;
    @Input() modelProperty: string;
    @Input() placeholder: string;
    // autocomplete props
    @Input("source") source: any;
    @Input("min-chars") minChars: number = 0;
    @Input("list-formatter") listFormatter: (arg: any) => string = this.defaultListFormatter;
    @Input("display-property-name") displayPropertyName: string;


    onChange: any = () => { };
    onTouched: any = () => { };

    control: AbstractControl;

    constructor(private translateService: TranslateService, private _sanitizer: DomSanitizer) { }

    registerOnChange(fn: any) {
        this.onChange = fn;
    }

    registerOnTouched(fn: any) {
        this.onTouched = fn;
    }

    writeValue(val: any) {
        console.log("write value");
        this.value = _.get(val, this.displayPropertyName, null)
    }


    /**
     * autocomplete component has own control, to manage value and display properly,
     * and our form has seperated model to manage what has been choosen by user
     */
    onAutcompleteChange(val: any) {
        console.log("selected", val);
        if (val == null || val == undefined || val.length === 0) {
            console.log("nulluje");
            this.onChange(null);
        } else if (typeof val === 'object') {
            console.log("settuje");
            this.onChange(_.get(val, this.modelProperty, val));
        }
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