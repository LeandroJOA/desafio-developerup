import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { CadastroService } from './service/cadastro.service';
import { ListaProfissionalComponent } from './lista-profissional/lista-profissional.component';
import { ListaEquipeComponent } from './lista-equipe/lista-equipe.component';
import { CadastroEquipeComponent } from './cadastro-equipe/cadastro-equipe.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CadastroComponent,
    ListaProfissionalComponent,
    ListaEquipeComponent,
    CadastroEquipeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [CadastroService],
  bootstrap: [AppComponent]
})
export class AppModule { }
