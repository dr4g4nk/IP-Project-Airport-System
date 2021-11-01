import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LetAddTerminComponent } from './let-add-termin.component';

describe('LetAddTerminComponent', () => {
  let component: LetAddTerminComponent;
  let fixture: ComponentFixture<LetAddTerminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LetAddTerminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LetAddTerminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
