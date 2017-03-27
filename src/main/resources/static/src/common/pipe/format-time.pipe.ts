import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
 name: 'formatTime'
})
 
export class FormatTimePipe implements PipeTransform {
    transform(value: number): string {

        if(value) {
            console.log(value);
            let totalSeconds:number = value / 1000;
            let totalMinutes:number = totalSeconds / 60;
            let totalHours:number = totalMinutes / 60;

            var seconds:number= Math.floor(totalSeconds) % 60;
            var minutes:number = Math.floor(totalMinutes) % 60;
            var hours:number = Math.floor(totalHours);
                   
            return this.numberToString(hours)+':'+this.numberToString(minutes)+":"+this.numberToString(seconds);
        } else {
            return "00:00:00";
        }

    
    }

    numberToString(number:number):string {
        var numberString:string;
        if (number < 10) {
                numberString = '0'+number.toString();
        } else {
                numberString = number.toString();
        }

        return numberString;
    }
}
