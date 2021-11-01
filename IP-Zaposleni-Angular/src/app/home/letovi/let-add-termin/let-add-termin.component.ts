import { TerminService } from './../../../services/termin.service';
import { Termin } from './../../../model/termin.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Let } from './../../../model/let.model';
import { Component, OnInit, Output } from '@angular/core';
import { NgbActiveModal, NgbDate } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-let-add-termin',
  templateUrl: './let-add-termin.component.html',
  styleUrls: ['./let-add-termin.component.css']
})
export class LetAddTerminComponent implements OnInit {

  public Let: Let = new Let();

  dates: NgbDate[] = [];
  startTime: string = "";
  endTime: string = "";

  showMsg: boolean = false;

  constructor(public activeModal: NgbActiveModal, private terminService: TerminService) { }

  ngOnInit(): void {
  }

  passBack() { this.activeModal.close(this.Let); }


  public save() {

    let termini: Termin[] = [];
    this.dates.forEach((element) => {
      let l: Let = new Let();
      l.id = this.Let.id;
      termini.push(new Termin(element.year + "-" + (element.month < 10 ? "0" + element.month : element.month) + "-" + (element.day < 10 ? "0" + element.day : element.day), this.startTime + ":00", this.endTime + ":00", l));
    });

    if (termini.length > 0 && this.startTime.length > 0 && this.endTime.length > 0) {
      termini.forEach(element => {
        console.log(element);
        this.terminService.add(element).subscribe(response => {
          let parts = response.dan.split("-");
          this.Let.termini.push(new Termin(parts[2] + "." + parts[1] + "." + parts[0], response.polazak, response.dolazak, response.l, response.id));
        });
      });
      this.activeModal.close(this.Let);
    }
    else this.showMsg = true;
  }

  public addDate(date: NgbDate) {
    if (this.dates.indexOf(date) == -1)
      this.dates.push(date);
  }

  public deleteDate(date: NgbDate) {
    this.dates = this.dates.filter(d => d != date)
  }
}
