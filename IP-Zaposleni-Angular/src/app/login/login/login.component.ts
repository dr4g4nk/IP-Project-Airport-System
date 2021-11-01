import { AuthGuard } from './../auth.guard';
import { Zaposleni } from './../../model/zaposleni.model';
import { LoginService } from './../../services/login.service';

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: any;
  public isOpen: boolean = false;
  public form: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder,
    private loginService: LoginService, private auth: AuthGuard,
    private router: Router) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    });
  }

  public login(form: any) {
    let zaposleni = new Zaposleni(form.value.username, form.value.password);
    console.log(zaposleni);
    this.loginService.login(zaposleni).subscribe(data => {
      if (data.loggedIn) {
        console.log("Prijavljen");
        zaposleni.loggedIn = data.loggedIn;
        this.auth.login(zaposleni);
        this.router.navigate(['home']);
      }
      else this.isOpen = true;
    })
  }
}