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