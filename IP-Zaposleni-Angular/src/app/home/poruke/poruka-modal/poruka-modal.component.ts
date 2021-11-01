import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit } from '@angular/core';
import { Poruka } from 'src/app/model/poruka.model';

@Component({
  selector: 'app-poruka-modal',
  templateUrl: './poruka-modal.component.html',
  styleUrls: ['./poruka-modal.component.css']
})
export class PorukaModalComponent implements OnInit {

  poruka: Poruka = new Poruka;

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

}
