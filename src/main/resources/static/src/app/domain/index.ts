export interface FlightDetails {
    planeSid: string,
    planeName?: string,
    planeRegistration?:string,
    latitude?:number,
    longitude?:number,
    flightTime?:number,
    velocity?:number,
    averageFuelConsumption?:number
}