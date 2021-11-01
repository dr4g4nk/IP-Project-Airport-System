import { AuthGuard } from './../login/auth.guard';
import { Zaposleni } from './../model/zaposleni.model';
import { LoginService } from './login.service';
import { Let } from './../model/let.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LetService {

  private url: string = "http://localhost:8080/api/v1/letovi";

  constructor(private http: HttpClient, private auth: AuthGuard) { }

  public getAllLetovi() {
    const user: Zaposleni = this.auth.User;
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + btoa(user.korisnickoIme + ":" + user.lozinka)
      })
    };
    return this.http.get<Let[]>(this.url, httpOptions);
  }

  public addLet(l: Let) {
    const user: Zaposleni = this.auth.User;
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + btoa(user.korisnickoIme + ":" + user.lozinka)
      })
    };
    return this.http.post<Let>(this.url, l, httpOptions);
  }

  public deleteLet(l: Let) {
    const user: Zaposleni = this.auth.User;
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + btoa(user.korisnickoIme + ":" + user.lozinka)
      })
    };
    return this.http.delete(this.url + "?id=" + l.id, httpOptions);
  }
}
