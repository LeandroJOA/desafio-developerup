import { Equipe } from './../Objetos/equipe';
import { Component, OnInit } from '@angular/core';
import { CadastroEquipeService } from '../service/cadastro-equipe.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lista-equipe',
  templateUrl: './lista-equipe.component.html',
  styleUrls: ['./lista-equipe.component.css']
})
export class ListaEquipeComponent implements OnInit {

  mensagemError: string = ""
  error: boolean = false
  prod: any
  equipes: Array<Equipe> = []
  carregarLoading: boolean = false
  constructor(private cadastroEquipeService: CadastroEquipeService, private router: Router) { }

  ngOnInit(): void {

    this.cadastroEquipeService.listar().subscribe(
      e => {
        setTimeout(() =>{
          this.carregarLoading  = true
          this.equipes = e
        },2000)
      },
      error => {
        this.mensagemError = error.error
        this.error = true
      }
    ) 
  }

  excluirItem = (id: any) =>{
    if(window.confirm('Deseja excluir esta equipe?')){
      this.cadastroEquipeService.excluirItem(id).subscribe(
          success => {
            this.ngOnInit()
          },
          error => {
            this.mensagemError = error.error
            this.error = true
          },
          () => console.log('Requisição completa')
        )
      //this.ngOnInit();
    }
  }

  editar = (id:any) =>{
    this.router.navigate(['cadastroEquipe', id])
  }

  navegar = () =>{
    this.router.navigate(['cadastroEquipe'])
  }
}
