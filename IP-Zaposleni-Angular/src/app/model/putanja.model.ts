import { Lokacija } from './lokacija.model';
export class Putanja {
    public id: number = 0;
    public polaznaLokacija: Lokacija = new Lokacija();
    public odredisnaLokacija: Lokacija = new Lokacija();
}