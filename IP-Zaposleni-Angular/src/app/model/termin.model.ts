import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { Let } from './let.model';
export class Termin {
    constructor(public dan: string, public polazak: string, public dolazak: string, public l: Let, public id: number = -1) { }
}