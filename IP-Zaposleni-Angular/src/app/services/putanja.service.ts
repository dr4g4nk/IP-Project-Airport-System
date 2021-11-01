import { Putanja } from './../model/putanja.model';
import { Zaposleni } from './../model/zaposleni.model';
import { AuthGuard } from './../login/auth.guard';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PutanjaService {
  private url: string = "http://localhost:8080/api/v1/putanja";

  constructor(private http: HttpClient, private auth: AuthGuard) { }

  public getAll() {
    const user: Zaposleni = this.auth.User;
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + btoa(user.korisnickoIme + ":" + user.lozinka)
      })
    };
    return this.http.get<Putanja[]>(this.url, httpOptions);
  }
}
