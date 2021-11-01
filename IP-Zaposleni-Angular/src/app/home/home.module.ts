import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home/home.component';
import { LetoviComponent } from './letovi/letovi.component';
import { RezervacijeComponent } from './rezervacije/rezervacije.component';
import { PorukeComponent } from './poruke/poruke.component';
import { LetAddTerminComponent } from './letovi/let-add-termin/let-add-termin.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TerminiComponent } from './letovi/termini/termini.component';
import { NgbAlertModule, NgbDatepickerModule, NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { RazlogModalComponent } from './rezervacije/razlog-modal/razlog-modal.component';
import { PorukaModalComponent } from './poruke/poruka-modal/poruka-modal.component';
import { LetModalComponent } from './letovi/let-modal/let-modal.component';


@NgModule({
  declarations: [
    HomeComponent,
    LetoviComponent,
    RezervacijeComponent,
    PorukeComponent,
    LetAddTerminComponent,
    TerminiComponent,
    RazlogModalComponent,
    PorukaModalComponent,
    LetModalComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgbDatepickerModule,
    NgbAlertModule,
    NgbDropdownModule
  ]
})
export class HomeModule { }
