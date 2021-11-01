import { Putanja } from './putanja.model';
import { Termin } from './termin.model';
export class Let {
    public id: number = 0;
    public tip: string = '';
    public putanja: Putanja = new Putanja();
    public termini: Termin[] = [];

    constructor() { }
}