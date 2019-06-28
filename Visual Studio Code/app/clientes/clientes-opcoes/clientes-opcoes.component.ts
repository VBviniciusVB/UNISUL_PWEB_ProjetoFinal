import { Cliente } from '../model';
import { Component, OnInit } from '@angular/core';
import { ClientesService } from '../clientes.service';
import { MessageService, ConfirmationService } from 'primeng/api';
import { FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-clientes-opcoes',
  templateUrl: './clientes-opcoes.component.html',
  styleUrls: ['./clientes-opcoes.component.css']
})
export class ClientesOpcoesComponent implements OnInit {

  cliente = new Cliente();
  clientes = [];

  index: number = 0;
  Editar: boolean = true;

  nomeBusca:string;

  constructor(
    private service: ClientesService,
    private messageService: MessageService,
    private rota: ActivatedRoute,
    private conf: ConfirmationService
    //private rotaP: Router
  ) { }

  ngOnInit() {
    this.pesquisar();
  }

  inserir(form: FormControl) {
    this.service.adicionar(this.cliente)
    .then( ()=>{
      this.messageService.add({severity:'success', summary:'Inserção Concluida', detail:'O cliente '+this.cliente.nome+' foi adicionado'});
    });
  }

  alterar(form: FormControl) {
    this.service.alterar(this.cliente)
    .then( ()=>{
      this.messageService.add({severity:'success', summary:'Edição Concluida', detail:'O cliente '+this.cliente.nome+' foi alterado'});
    });
  }

  salvar(form: FormControl) {
    if(this.Editar){
      this.inserir(form);
    }else{
      this.alterar(form);
    }
  }

  excluir(cliente: any){
    this.service.excluir(cliente.id)
    .then(()=>{
      this.pesquisar();
      this.messageService.add({severity:'success', summary:'Exclusão', detail:'Cliente '+cliente.nome+' excluída'});
    });
  }

  pesquisar(){
    this.service.pesquisar({nome:this.nomeBusca})
    .then((dados)=>{
      this.clientes=dados;
    });
  }

  carregarCliente(id:number){
    this.service.buscarPorCodigo(id)
      .then((data) => {
        this.cliente = data;
      }
    );
  }

  ConfirmarExclusao(cliente:any){
    this.conf.confirm({
      message: 'Tem certeza que deseja excluir '+cliente.nome+'?',
      accept: () => {
        this.excluir(cliente);
      }
    });
  }

  OpenPesquisa() {
    this.index = 0;
}

  OpenCadastro(ID) {
    this.index = 1;
    this.carregarCliente(ID);
    this.Editar = false;
}

  onTabChange(event) {
   this.index = event.index;
   this.Editar = true;
   if (this.index == 0){
    this.pesquisar();
   }

  }

  editando(){
    return Boolean(this.Editar);
  }

}
