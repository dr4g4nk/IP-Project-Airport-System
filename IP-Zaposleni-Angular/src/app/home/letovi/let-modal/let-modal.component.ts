import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Let } from './../../../model/let.model';
import { FormGroup } from '@angular/forms';
import { Putanja } from './../../../model/putanja.model';
import { PutanjaService } from './../../../services/putanja.service';
import { LetService } from './../../../services/let.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-let-modal',
  templateUrl: './let-modal.component.html',
  styleUrls: ['./let-modal.component.css']
})
export class LetModalComponent implements OnInit {

  public l: Let = new Let();
  public putanje: Putanja[] = [];
  constructor(private letService: LetService, private putanjaService: PutanjaService, public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
    this.putanjaService.getAll().subscribe(response => {
      this.putanje = response;
      console.log(this.putanje);
      this.l.tip = "Putnicki";
      this.l.putanja = this.putanje.length > 0 ? this.putanje[0] : new Putanja();
    })
  }

  public save() {
    console.log(this.l);
    this.letService.addLet(this.l).subscribe(response => {
      if (response) {
        this.activeModal.close(response);
      }
    })
  }

}
