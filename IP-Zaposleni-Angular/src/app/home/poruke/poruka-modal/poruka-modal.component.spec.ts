import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PorukaModalComponent } from './poruka-modal.component';

describe('PorukaModalComponent', () => {
  let component: PorukaModalComponent;
  let fixture: ComponentFixture<PorukaModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PorukaModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PorukaModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
