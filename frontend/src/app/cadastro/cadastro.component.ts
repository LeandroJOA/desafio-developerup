import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router'

import { DiasDaSemana } from './../dias-da-semana.enum';
import { Pessoa } from './../Objetos/pessoa';
import { CadastroService } from '../service/cadastro.service';
import { Equipe } from '../Objetos/equipe';
import { CadastroEquipeService } from '../service/cadastro-equipe.service';


@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  atualizando: boolean = false
  mensagemError: string = ""
  error: boolean = false
  id: any
  pessoa: Pessoa = new Pessoa();
  textoBotao: string = 'Salvar';
  formInvalido = false;
  cargos = [
    {
      "id": 1,
      "nome": "Analista de Sistemas Jr."
    },
    {
      "id": 2,
      "nome": "Analista de Sistemas Pl."
    },
    {
      "id": 3,
      "nome": "Analista de Sistemas Sr."
    }
  ]
  equipes: Array<Equipe> = []

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private cadastroService: CadastroService,
    private cadastroEquipeService: CadastroEquipeService
  ) { }

  ngOnInit(): void {
    
    this.activatedRoute.params.subscribe(parametros =>{
      if(parametros['id']){
        this.id = parametros['id']
        this.cadastroService.buscarItemID(this.id).subscribe(
          p => {
            this.pessoa = p
            this.atualizando = true
            this.textoBotao = 'Editar'
          }, 
          error => {
            this.mensagemError = error.error
            this.error = true
          }
        )
      }
    })

    this.cadastroEquipeService.listar().subscribe(e =>{
      this.equipes = e
    }) 
    
  }


  editar = () =>{
    this.cadastroService.editar(this.pessoa).subscribe(
      success => {
        this.navegar('listar')
      },
      error => {
        this.mensagemError = error.error
        this.error = true
      },
      () => console.log('Requisição completa'))
  }

  navegar = (rota: any) =>{
    this.router.navigate([rota])
  }

  onSubmit(form:any){

    if (!this.pessoa.equipe?.id) {
      delete this.pessoa.equipe
    }

    if (this.pessoa.cargo == 0) {
      delete this.pessoa.cargo
    }
    
    if(form.valid == false){
      this.formInvalido = true
    }else{
      if(this.textoBotao == 'Salvar'){
        this.cadastroService.adicionar(this.pessoa).subscribe(
          success => {
            this.navegar('listar')
          },
          error => {
            this.mensagemError = error.error
            this.error = true
          },
          () => console.log('Requisição completa'))
      }else{
        this.editar()
      }
    }
  }

}
