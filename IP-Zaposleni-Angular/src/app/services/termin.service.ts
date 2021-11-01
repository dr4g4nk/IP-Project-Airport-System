import { AuthGuard } from './../login/auth.guard';
import { LoginService } from './login.service';
import { Zaposleni } from './../model/zaposleni.model';
import { Termin } from './../model/termin.model';
import { HttpClient, HttpHeaders, HttpParamsOptions } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TerminService {

  private httpOptions: {} = {}

  constructor(private http: HttpClient, private auth: AuthGuard) {
    const user: Zaposleni = this.auth.User;
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + btoa(user.korisnickoIme + ":" + user.lozinka)
      })
    };
  }

  private endpoint: string = "http://localhost:8080/api/v1/termini";

  public add(termin: Termin) {
    console.log(termin);

    return this.http.post<Termin>(this.endpoint, termin, this.httpOptions);
  }

  public getTerminiByLet(id: number) {
    return this.http.get<Termin[]>(this.endpoint + "?id=" + id, this.httpOptions);
  }

  public delete(id: number) {
    return this.http.delete(this.endpoint + "?id=" + id, this.httpOptions);
  }
}
