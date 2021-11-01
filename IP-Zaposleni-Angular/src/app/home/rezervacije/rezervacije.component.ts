import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { RazlogModalComponent } from './razlog-modal/razlog-modal.component';
import { RezervacijaService } from './../../services/rezervacija.service';
import { Rezervacija } from './../../model/rezervacija.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-rezervacije',
  templateUrl: './rezervacije.component.html',
  styleUrls: ['./rezervacije.component.css']
})
export class RezervacijeComponent implements OnInit {

  private rezervacije: Rezervacija[] = [];
  filtered: Rezervacija[] = [];
  selected: string = "Nova";
  selectedTitle: string = "Nove rezervacija";

  constructor(private rezervacijaService: RezervacijaService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.rezervacijaService.get().subscribe(response => {
      this.rezervacije = response;
      this.filter();
    });
  }

  public filter() {
    if (this.selected.length > 0)
      this.filtered = this.rezervacije.filter(r => r.status == this.selected);
    else
      this.filtered = this.rezervacije;
  }



  public openModal(rezervacija: Rezervacija) {
    const modalRef = this.modalService.open(RazlogModalComponent);
    modalRef.componentInstance.rezervacija = rezervacija;
    modalRef.result.then((result) => {
      if (result) {
        this.rezervacije = this.rezervacije.filter(r => r.id != rezervacija.id);
        this.rezervacije.push(result);
        this.filter();
      }
    });
  }

  public prihvati(rezervacija: Rezervacija) {
    let tmp = this.rezervacije.find(r => r.id == rezervacija.id);
    if (tmp) {
      tmp.status = "Prihvacena";
      this.rezervacijaService.update(tmp).subscribe(response => {
        if (response) {
          console.log("Prihvacena")
          this.filter();
        }
      });
    }
  }

}
