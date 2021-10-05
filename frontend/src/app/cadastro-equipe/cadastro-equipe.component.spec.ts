import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroEquipeComponent } from './cadastro-equipe.component';

describe('CadastroEquipeComponent', () => {
  let component: CadastroEquipeComponent;
  let fixture: ComponentFixture<CadastroEquipeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastroEquipeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroEquipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
