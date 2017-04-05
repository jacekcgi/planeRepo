import { airplane } from './en_EN/airplane'
import { airport } from './en_EN/airport'
import { flightDetails } from './en_EN/flightDetails'
import { validations } from './en_EN/validations'
import { flightroute } from "./en_EN/flightroute";
import { datepicker } from "./en_EN/datepicker";

export const LANG_EN_NAME = 'en_EN';

export const LANG_EN_TRANS = {
    'hello': 'Smart {{value}}',
    airplane,
    flightroute,
    validations,
    airport,
    flightDetails,
    datepicker,

    // main keys
    saveButton: 'Save',
    searchButton: 'Search',
    closeButton: 'Close',
    // notification titles
    successTitle: 'Success',
    infoTitle: 'Info',
    warnTitle: 'Warning',
    errorTitle: 'Error',
    actions: 'Actions',
    languages: 'Languages',
    en_EN: 'English',
    pl_PL: 'Polish'
};
