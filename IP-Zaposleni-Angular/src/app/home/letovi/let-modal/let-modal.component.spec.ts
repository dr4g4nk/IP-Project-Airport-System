import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LetModalComponent } from './let-modal.component';

describe('LetModalComponent', () => {
  let component: LetModalComponent;
  let fixture: ComponentFixture<LetModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LetModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LetModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
