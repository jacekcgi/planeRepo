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
import { NguiDatetimePickerModule } from '@ngui/datetime-picker';
import { DateTimepickerComponent } from "common/datetimepicker";
import { DateTimePickerModule } from 'ng2-date-time-picker';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        Ng2AutoCompleteModule,
        NguiDatetimePickerModule,
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
        DateTimePickerModule,
        ErrorMessagesComponent,
        Table,
        PageableTable,
        Pagination,
        FilterToolbar,
        CellComponent,
        DefaultCellComponent,
        DateCellComponent,
        LanguageComponent,
        Modal
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
