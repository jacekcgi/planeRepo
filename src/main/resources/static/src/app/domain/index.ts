
export interface FlightDetailsDto {
    currentLatitude: number;

    currentLongitude: number;

    destinationLatitude: number;

    destinationLongitude: number;

    velocity: number;

    flightRouteSid: string;

    timeElapsed: number; //in ms
}

 export interface FlightDetails {
    planeSid: string,
    planeName?: string,
    planeRegistration?:string,
    course?:number,
    latitude?:number,
    longitude?:number,
    flightTime?:number,
    velocity?:number,
    averageFuelConsumption?:number
}
