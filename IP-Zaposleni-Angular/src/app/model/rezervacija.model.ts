import { Korisnik } from './korisnik.model';
import { Let } from './let.model';
export class Rezervacija {
    id: number = 0;
    status: string = "";
    datumKreiranja: string = "";
    razlog: string = "";
    l: Let = new Let();
    korisnik: Korisnik = new Korisnik();
}