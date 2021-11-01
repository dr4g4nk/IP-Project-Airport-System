import { Zaposleni } from './../model/zaposleni.model';
import { LoginService } from './../services/login.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  private user: Zaposleni = new Zaposleni("", "");


  public get User() {
    return this.user;
  }

  constructor(private loginService: LoginService, private router: Router) {

  }

  public login(user: Zaposleni) {
    this.user = user;
    window.sessionStorage.setItem("user", JSON.stringify(user));
  }

  public logout() {
    this.user.loggedIn = false;
    window.sessionStorage.clear();
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    let exists = window.sessionStorage.getItem("user");
    if (exists) {
      this.user = JSON.parse(exists);

      if (this.user.loggedIn)
        return true;

    }
    this.router.navigate(['login']);
    return false;
  }

}
