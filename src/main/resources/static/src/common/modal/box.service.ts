import { Injectable } from '@angular/core';

@Injectable()
export class BoxService {
   prompt: (message: string, accept?:string, close?:string) => Promise<boolean>;
}