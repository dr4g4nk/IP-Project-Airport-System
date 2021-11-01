import { AuthGuard } from './../login/auth.guard';
import { Poruka } from './../model/poruka.model';
import { Zaposleni } from './../model/zaposleni.model';
import { LoginService } from './login.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PorukeService {
  private httpOptions: {} = {}

  constructor(private http: HttpClient, private auth: AuthGuard) {
    const user: Zaposleni = this.auth.User;
    this.httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + btoa(user.korisnickoIme + ":" + user.lozinka)
      })
    };
  }

  private endpoint: string = "http://localhost:8080/api/v1/poruke";

  public get() {
    return this.http.get<Poruka[]>(this.endpoint, this.httpOptions);
  }

  public update(data: Poruka) {
    return this.http.put<Poruka>(this.endpoint, data, this.httpOptions);
  }
}
