import { Rezervacija } from './../../../model/rezervacija.model';
import { RezervacijaService } from './../../../services/rezervacija.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-razlog-modal',
  templateUrl: './razlog-modal.component.html',
  styleUrls: ['./razlog-modal.component.css']
})
export class RazlogModalComponent implements OnInit {

  rezervacija: Rezervacija = new Rezervacija();
  razlog: string = "";
  showMsg: boolean = false;
  constructor(public activeModal: NgbActiveModal, private rezervacijaService: RezervacijaService, private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  public update() {
    if (this.razlog.length > 0) {
      this.rezervacija.razlog = this.razlog;
      this.rezervacija.status = "Ponistena";
      this.rezervacijaService.update(this.rezervacija).subscribe(response => {
        this.activeModal.close(this.rezervacija);
      })
    }
    else this.showMsg = true;
  }

}
