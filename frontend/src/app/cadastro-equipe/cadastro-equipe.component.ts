import { CadastroEquipeService } from './../service/cadastro-equipe.service';
import { Equipe } from './../Objetos/equipe';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-cadastro-equipe',
  templateUrl: './cadastro-equipe.component.html',
  styleUrls: ['./cadastro-equipe.component.css']
})
export class CadastroEquipeComponent implements OnInit {

  mensagemError: string = ""
  error: boolean = false
  id: any
  equipe: Equipe = new Equipe();
  textoBotao: string = 'Salvar';
  formInvalido = false;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private cadastroEquipeService: CadastroEquipeService
  ) { }

  ngOnInit(): void {
    
    this.activatedRoute.params.subscribe(parametros =>{
      if(parametros['id']){
        this.id = parametros['id']
        this.cadastroEquipeService.buscarItemID(this.id).subscribe(
          e => {
            this.equipe = e
            this.textoBotao = 'Editar'
          },
          error => {
            this.mensagemError = error.error
            this.error = true
          }
        )
      }
    })
  }


  editar = () =>{
    this.cadastroEquipeService.editar(this.equipe).subscribe(
      success => {
        this.navegar('listarEquipe')
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

    if(form.valid == false){
      this.formInvalido = true
    }else{
      if(this.textoBotao == 'Salvar'){
        this.cadastroEquipeService.adicionar(this.equipe).subscribe(
          success => {
            this.navegar('listarEquipe')
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
