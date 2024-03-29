import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ButtonModule } from 'primeng/button';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { ConfirmationService } from 'primeng/api';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {ContextMenuModule} from 'primeng/contextmenu';
import {ToolbarModule} from 'primeng/toolbar';
import {TabViewModule} from 'primeng/tabview';
import {PanelModule} from 'primeng/panel';
import {RadioButtonModule} from 'primeng/radiobutton';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {DropdownModule} from 'primeng/dropdown';
import {SpinnerModule} from 'primeng/spinner';
import {InputMaskModule} from 'primeng/inputmask';
import {PickListModule} from 'primeng/picklist';
import {CalendarModule} from 'primeng/calendar';

import { ClientesOpcoesComponent } from './clientes/clientes-opcoes/clientes-opcoes.component';
import { ClientesModule } from './clientes/clientes.module';

import { LivrosOpcoesComponent } from './livros/livros-opcoes/livros-opcoes.component';
import { LivrosModule } from './livros/livros.module';

import { EmprestimosOpcoesComponent } from './emprestimos/emprestimos-opcoes/emprestimos-opcoes.component';
import { EmprestimosModule } from './emprestimos/emprestimos.module';

const rotas: Routes = [
  {path: 'clientes', component: ClientesOpcoesComponent},
  {path: 'livros', component: LivrosOpcoesComponent},
  {path: 'emprestimos', component: EmprestimosOpcoesComponent},

];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    ButtonModule,
    RouterModule.forRoot(rotas),
    ContextMenuModule,
    ToolbarModule,
    TabViewModule,
    PanelModule,
    RadioButtonModule,
    ConfirmDialogModule,
    DropdownModule,
    SpinnerModule,
    InputMaskModule,
    PickListModule,
    ClientesModule,
    LivrosModule,
    EmprestimosModule,
    CalendarModule

  ],
  providers: [
    ConfirmationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
