import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {TooltipModule} from 'primeng/tooltip';
import {TableModule} from 'primeng/table';
import { EmprestimosService } from './emprestimos.service';
import {ToastModule} from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { FormsModule } from '@angular/forms';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {TabViewModule} from 'primeng/tabview';
import {PanelModule} from 'primeng/panel';
import {RadioButtonModule} from 'primeng/radiobutton';
import {PickListModule} from 'primeng/picklist';
import {CalendarModule} from 'primeng/calendar';
import {DropdownModule} from 'primeng/dropdown';

import { EmprestimosOpcoesComponent } from './emprestimos-opcoes/emprestimos-opcoes.component';

@NgModule({
  declarations: [EmprestimosOpcoesComponent],
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
    RadioButtonModule,
    PickListModule,
    CalendarModule,
    DropdownModule
  ],
  exports:[
    EmprestimosOpcoesComponent
  ],
  providers: [
    EmprestimosService,
    MessageService
  ]
})
export class EmprestimosModule { }
