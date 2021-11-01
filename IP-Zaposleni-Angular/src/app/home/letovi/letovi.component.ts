import { LetModalComponent } from './let-modal/let-modal.component';
import { TerminService } from './../../services/termin.service';
import { TerminiComponent } from './termini/termini.component';
import { Let } from './../../model/let.model';
import { LetService } from './../../services/let.service';
import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-letovi',
  templateUrl: './letovi.component.html',
  styleUrls: ['./letovi.component.css']
})
export class LetoviComponent implements OnInit {

  public letovi: Let[] = [];
  constructor(private letService: LetService, private modalService: NgbModal, private terminService: TerminService) { }

  ngOnInit(): void {
    this.letService.getAllLetovi().subscribe(data => {
      this.letovi = data;
    })
  }
  public openModal(Let: Let) {
    console.log(Let)
    this.terminService.getTerminiByLet(Let.id).subscribe(response => {
      Let.termini = response;
      Let.termini.forEach(termin => {
        let parts = termin.dan.split("-");
        if (parts.length == 3)
          termin.dan = parts[2] + "." + parts[1] + "." + parts[0];
      })
      const modalRef = this.modalService.open(TerminiComponent);
      modalRef.componentInstance.Let = Let;
    });
  }

  public letModal() {
    const modalRef = this.modalService.open(LetModalComponent);
    modalRef.result.then(result => {
      if (result) {
        this.letovi.push(result);
      }
    })
  }

  public delete(l: Let) {
    if (confirm("Da li ste sigurni da zelite obrisati let?")) {
      this.letService.deleteLet(l).subscribe(response => {
        console.log(response);
        this.letovi = this.letovi.filter(l1 => l1.id != l.id);
      })

    }
  }
}
