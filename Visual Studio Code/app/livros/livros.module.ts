import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import {InputTextModule} from 'primeng/inputtext';
import {ButtonModule} from 'primeng/button';
import {TooltipModule} from 'primeng/tooltip';
import {TableModule} from 'primeng/table';
import { LivrosService } from './livros.service';
import {ToastModule} from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { FormsModule } from '@angular/forms';
import {ConfirmDialogModule} from 'primeng/confirmdialog';
import {TabViewModule} from 'primeng/tabview';
import {PanelModule} from 'primeng/panel';
import {DropdownModule} from 'primeng/dropdown';
import {SpinnerModule} from 'primeng/spinner';
import {InputMaskModule} from 'primeng/inputmask';

import { LivrosOpcoesComponent } from './livros-opcoes/livros-opcoes.component';

@NgModule({
  declarations: [LivrosOpcoesComponent],
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
    DropdownModule,
    SpinnerModule,
    InputMaskModule
  ],
  exports:[
    LivrosOpcoesComponent
  ],
  providers: [
    LivrosService,
    MessageService
  ]
})
export class LivrosModule { }
