import { LetAddTerminComponent } from './../let-add-termin/let-add-termin.component';
import { TerminService } from './../../../services/termin.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Let } from './../../../model/let.model';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-termini',
  templateUrl: './termini.component.html',
  styleUrls: ['./termini.component.css']
})
export class TerminiComponent implements OnInit {

  constructor(public activeModal: NgbActiveModal, private terminService: TerminService, private modalService: NgbModal) { }

  @Input() Let: Let = new Let();

  ngOnInit(): void {
  }

  public openModal() {
    const modalRef = this.modalService.open(LetAddTerminComponent);
    modalRef.componentInstance.Let = this.Let;
    modalRef.result.then((result) => { if (result) { console.log(result); } });
  }


  public delete(id: number) {
    this.Let.termini = this.Let.termini.filter(termin => termin.id != id);
    this.terminService.delete(id).subscribe(response => {
      console.log("Obrisano");
    })
  }

}
