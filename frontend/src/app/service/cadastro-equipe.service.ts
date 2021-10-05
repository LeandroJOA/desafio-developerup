import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Equipe } from '../Objetos/equipe';

@Injectable({
  providedIn: 'root'
})
export class CadastroEquipeService {

  private readonly API = `${environment.API}equipe`

  constructor(private $http: HttpClient) { }

  listar(){
    return this.$http.get<Equipe[]>(`${this.API}`)
  }

  excluirItem(id: any){
    return this.$http.delete(`${this.API}/${id}`)
  }

  adicionar(e: Equipe){
    return this.$http.post(this.API, e)
  }

  editar(e: Equipe){
    return this.$http.put(`${this.API}/${e.id}`, e)
  }

  buscarItemID(id:any){
    return this.$http.get<Equipe>(`${this.API}/${id}`)
  }

}
