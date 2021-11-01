import { Zaposleni } from './../model/zaposleni.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl: string = "http://localhost:8080/login";

  constructor(private http: HttpClient) { }

  public login(zaposleni: Zaposleni) {
    return this.http.post<Zaposleni>(this.loginUrl, zaposleni);
  }
}
