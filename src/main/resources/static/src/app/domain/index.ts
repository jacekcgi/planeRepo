
export interface FlightDetailsDto {
    currentLatitude: number;

    currentLongitude: number;

    destinationLatitude: number;

    destinationLongitude: number;

    velocity: number;

    flightRouteSid: string;

    timeElapsed: number; //in ms

    distanceTraveled: number;

    flightDistance: number;

    planeName: string,

    planeRegistration:string,

    averageFuelConsumption:number,

    sourceCity: string,

    destinationCity:string
}