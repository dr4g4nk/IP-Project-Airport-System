import { AuthGuard } from './../login/auth.guard';
import { PorukeComponent } from './poruke/poruke.component';
import { RezervacijeComponent } from './rezervacije/rezervacije.component';
import { LetoviComponent } from './letovi/letovi.component';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'letovi',
        component: LetoviComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'rezervacije',
        component: RezervacijeComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'poruke',
        component: PorukeComponent,
        canActivate: [AuthGuard]
      }
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})

export class HomeRoutingModule { }
