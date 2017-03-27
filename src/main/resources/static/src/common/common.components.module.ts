import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule, TranslateLoader } from 'ng2-translate';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CustomLoader } from 'app/translate/translate.loader';

import { InputComponent } from 'common/input';
import { Table, PageableTable, Pagination, FilterToolbar, CellComponent, DefaultCellComponent } from 'common/table';
import { ErrorMessagesComponent } from 'common/validations';
import { LanguageComponent } from 'common/languages';
import { Modal } from 'common/modal/modal.window.component';
import {Form} from 'common/modal/form.component';
import {FormInput} from 'common/modal/form.input.component';
import {InputComponent2} from 'common/modal/input.component';

import { NotificationService } from 'app/services';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        TranslateModule.forRoot({
            provide: TranslateLoader,
            useClass: CustomLoader
        })
    ],
    declarations: [
        InputComponent,
        ErrorMessagesComponent,
        Table,
        PageableTable,
        Pagination,
        FilterToolbar,
        CellComponent,
        DefaultCellComponent,
        LanguageComponent,
        Modal,
        Form,
        FormInput,
        InputComponent2
    ],
    entryComponents: [DefaultCellComponent],
    providers: [

    ],
    exports: [
        InputComponent,
        ErrorMessagesComponent,
        Table,
        PageableTable,
        Pagination,
        FilterToolbar,
        CellComponent,
        DefaultCellComponent,
        LanguageComponent,
        Modal,
        Form,
        FormInput,
        InputComponent2
    ]
})
export class CommonComponentsModule {
    // public static forRoot(): ModuleWithProviders {
    //     return {
    //         ngModule: CommonComponentsModule,
    //         providers: [
    //             NotificationService
    //         ]
    //     };
    // }
}
