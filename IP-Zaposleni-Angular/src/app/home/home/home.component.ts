import { AuthGuard } from './../../login/auth.guard';
import { LoginService } from './../../services/login.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public show: boolean = true;

  constructor(private router: Router, private auth: AuthGuard) { }

  ngOnInit(): void {
  }

  public logout() {
    this.auth.logout();
    this.router.navigate(['/']);
  }

}
