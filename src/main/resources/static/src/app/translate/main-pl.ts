import { airplane } from './pl_PL/airplane'
import { flightroute } from "./pl_PL/flightroute";
import { datepicker } from "./pl_PL/datepicker";
import { validations } from "./pl_PL/validations";
import { airport } from "./pl_PL/airport";
import { flightDetails } from "./pl_PL/flightDetails";

export const LANG_PL_NAME = 'pl_PL';

export const LANG_PL_TRANS = {
    'hello': 'Witaj świecie',
    airplane,
    flightroute,
    validations,
    airport,
    flightDetails,
    datepicker,

    languages: 'Języki',
    pl_PL: 'Polski',
    en_EN: 'Angielski',

    // notification titles
    successTitle: 'Sukces',
    infoTitle: 'Info',
    warnTitle: 'Ostrzeżenie',
    errorTitle: 'Błąd',

    saveButton: 'Zapisz',
    searchButton: 'Szukaj',
    closeButton: 'Zamknij',
    actions: 'Akcje',
};
