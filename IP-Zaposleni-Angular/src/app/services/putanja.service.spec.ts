import { TestBed } from '@angular/core/testing';

import { PutanjaService } from './putanja.service';

describe('PutanjaService', () => {
  let service: PutanjaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PutanjaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
