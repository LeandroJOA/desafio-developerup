import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaEquipeComponent } from './lista-equipe.component';

describe('ListaEquipeComponent', () => {
  let component: ListaEquipeComponent;
  let fixture: ComponentFixture<ListaEquipeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaEquipeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaEquipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
