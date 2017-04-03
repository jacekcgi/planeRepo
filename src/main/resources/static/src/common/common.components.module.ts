import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule, TranslateLoader } from 'ng2-translate';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CustomLoader } from 'app/translate/translate.loader';

import { InputComponent } from 'common/input';
import { Table, PageableTable, Pagination, FilterToolbar, CellComponent, DefaultCellComponent, DateCellComponent } from 'common/table';
import { ErrorMessagesComponent } from 'common/validations';
import { LanguageComponent } from 'common/languages';
import { Modal } from 'common/modal/modal.window.component';

import { NotificationService, FlightRoutesService, PlaneService } from 'app/services';
import { AutocompleteComponent } from "common/autocomplete";
import { Ng2AutoCompleteModule } from 'ng2-auto-complete';
import { DateTimepickerComponent, MomentPipe } from "common/datetimepicker";
import { DatepickerModule, TimepickerModule  } from 'ng2-bootstrap';


import { FormatTimePipe } from 'common/pipe';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        Ng2AutoCompleteModule,
        DatepickerModule.forRoot(),
        TimepickerModule.forRoot(),
        ReactiveFormsModule,
        TranslateModule.forRoot({
            provide: TranslateLoader,
            useClass: CustomLoader
        })
    ],
    declarations: [
        InputComponent,
        AutocompleteComponent,
        DateTimepickerComponent,
        ErrorMessagesComponent,
        Table,
        PageableTable,
        Pagination,
        FilterToolbar,
        CellComponent,
        DefaultCellComponent,
        DateCellComponent,
        LanguageComponent,
        MomentPipe,
        Modal
        LanguageComponent,
        FormatTimePipe
    ],
    entryComponents: [DefaultCellComponent, DateCellComponent],
    providers: [

    ],
    exports: [
        InputComponent,
        AutocompleteComponent,
        DateTimepickerComponent,
        ErrorMessagesComponent,
        Table,
        PageableTable,
        Pagination,
        FilterToolbar,
        CellComponent,
        DefaultCellComponent,
        DateCellComponent,
        LanguageComponent,
        FormatTimePipe
        LanguageComponent,
        MomentPipe,
        Modal
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
