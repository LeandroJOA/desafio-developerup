import { TestBed } from '@angular/core/testing';

import { CadastroEquipeService } from './cadastro-equipe.service';

describe('CadastroEquipeService', () => {
  let service: CadastroEquipeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CadastroEquipeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
