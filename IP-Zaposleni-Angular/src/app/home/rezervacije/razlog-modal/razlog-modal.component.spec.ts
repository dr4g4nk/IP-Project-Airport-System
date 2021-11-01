import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RazlogModalComponent } from './razlog-modal.component';

describe('RazlogModalComponent', () => {
  let component: RazlogModalComponent;
  let fixture: ComponentFixture<RazlogModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RazlogModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RazlogModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
