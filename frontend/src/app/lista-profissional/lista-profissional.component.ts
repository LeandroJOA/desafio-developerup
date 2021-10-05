import { Pessoa } from './../Objetos/pessoa';
import { Component, OnInit } from '@angular/core';
import { CadastroService } from '../service/cadastro.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lista-profissional',
  templateUrl: './lista-profissional.component.html',
  styleUrls: ['./lista-profissional.component.css']
})
export class ListaProfissionalComponent implements OnInit {

  mensagemError: string = ""
  error: boolean = false
  prod: any
  pessoas: Array<Pessoa> = []
  carregarLoading: boolean = false
  constructor(private cadastroService: CadastroService, private router: Router) { }

  ngOnInit(): void {

    this.cadastroService.listar().subscribe(
      p => {
        setTimeout(() => {
          this.carregarLoading  = true
          this.pessoas = p
        }, 2000)
      },
      error => {
        this.mensagemError = error.error
        this.error = true
      }
    ) 
  }

  associarCargo(cargo: any){
    if(cargo == 1){
        return "Analista de Sistemas Jr.";
    }else if(cargo == 2){
        return "Analista de Sistemas Pl.";
    }else if(cargo == 3){
        return "Analista de Sistemas Sr.";
    }
    return ''
}

  excluirItem = (id: any) =>{
    if(window.confirm('Deseja excluir este profissional?')){
      this.cadastroService.excluirItem(id).subscribe(
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
    this.router.navigate(['cadastro', id])
  }

  navegar = () =>{
    this.router.navigate(['cadastro'])
  }


}