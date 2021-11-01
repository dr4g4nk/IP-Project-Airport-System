import { AuthGuard } from './../login/auth.guard';
import { Rezervacija } from './../model/rezervacija.model';
import { Zaposleni } from './../model/zaposleni.model';
import { LoginService } from './login.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RezervacijaService {
  private httpOptions: {} = {}

  constructor(private http: HttpClient, private auth: AuthGuard) {
    const user: Zaposleni = this.auth.User;
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + btoa(user.korisnickoIme + ":" + user.lozinka)
      })
    };
  }

  private endpoint: string = "http://localhost:8080/api/v1/rezervacije";

  public get() {
    return this.http.get<Rezervacija[]>(this.endpoint, this.httpOptions);
  }

  public update(data: Rezervacija) {
    console.log(data.datumKreiranja);
    return this.http.put<Rezervacija>(this.endpoint, data, this.httpOptions);
  }
}
