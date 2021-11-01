import { PorukaModalComponent } from './poruka-modal/poruka-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PorukeService } from './../../services/poruke.service';
import { Poruka } from './../../model/poruka.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-poruke',
  templateUrl: './poruke.component.html',
  styleUrls: ['./poruke.component.css']
})
export class PorukeComponent implements OnInit {

  private poruke: Poruka[] = [];
  filtered: Poruka[] = [];
  selected: string = "Neprocitana";
  selectedTitle: string = "Neprocitane poruke";

  constructor(private porukeService: PorukeService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.porukeService.get().subscribe(response => {
      if (response) {
        this.poruke = response;
        this.filter();
      }
    })
  }

  public filter() {
    if (this.selected.length > 0)
      this.filtered = this.poruke.filter(r => r.status == this.selected);
    else
      this.filtered = this.poruke;
  }


  public openModal(poruka: Poruka) {
    const modalRef = this.modalService.open(PorukaModalComponent);
    modalRef.componentInstance.poruka = poruka;
    this.porukeService.update(poruka).subscribe(response => {
      console.log(response);
      if (response) {
        this.poruke = this.poruke.filter(p => p.id != poruka.id);
        this.poruke.push(response);
        this.filter();
      }
    });
  }

}
