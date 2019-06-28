import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {TooltipModule} from 'primeng/tooltip';
import {TableModule} from 'primeng/table';
import { ClientesService } from './clientes.service';
import {ToastModule} from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { FormsModule } from '@angular/forms';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {TabViewModule} from 'primeng/tabview';
import {PanelModule} from 'primeng/panel';
import {RadioButtonModule} from 'primeng/radiobutton';



import { RouterModule } from '@angular/router';

import { ClientesOpcoesComponent } from './clientes-opcoes/clientes-opcoes.component';

@NgModule({
  declarations: [ClientesOpcoesComponent],
  imports: [
    CommonModule,
    InputTextModule,
    ButtonModule,
    TooltipModule,
    TableModule,
    ToastModule,
    FormsModule,
    ConfirmDialogModule,
    RouterModule,
    TabViewModule,
    PanelModule,
    RadioButtonModule

  ],
  exports:[
    ClientesOpcoesComponent
  ],
  providers: [
    ClientesService,
    MessageService
  ]
})
export class ClientesModule { }
