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

  nomeBusca:string;

  constructor(
    private service: ClientesService,
    private messageService: MessageService,
    private rota: ActivatedRoute,
    private conf: ConfirmationService
    //private rotaP: Router
  ) { }


  inserir(form: FormControl) {
    this.service.adicionar(this.cliente)
    .then( ()=>{
      this.messageService.add({severity:'success', summary:'Cadastro Concluído', detail:'O cliente '+this.cliente.nome+' foi cadastrado'});
      form.reset();
    });
  }

  ngOnInit() {
    this.pesquisar();


  }

  carregarCliente(id:number){
    this.service.buscarPorCodigo(id)
      .then((data) => {
        this.cliente = data;
      }
    );
  }

  alterar(form: FormControl) {
    this.service.alterar(this.cliente)
    .then( ()=>{
      this.messageService.add({severity:'success', summary:'Edição', detail:'Cliente '+this.cliente.nome+' alterada'});
      form.reset();
    });
  }

  salvar(form: FormControl) {
    if(this.editando){
      this.alterar(form);
    }else{
      this.inserir(form);
    }
    //this.rotaP.navigate(['/clientes']);
  }

  pesquisar(){
    this.service.pesquisar({nome:this.nomeBusca})
    .then((dados)=>{
      this.clientes=dados;
    });
  }

  ConfirmarExclusao(cliente:any){
    this.conf.confirm({
      message: 'Tem certeza que deseja excluir '+cliente.nome+'?',
      accept: () => {
        this.excluir(cliente);
      }
    });
  }

  excluir(cliente: any){
    this.service.excluir(cliente.id)
    .then(()=>{
      this.pesquisar();
      this.messageService.add({severity:'success', summary:'Exclusão', detail:'Cliente '+cliente.nome+' excluída'});
    });
  }

  OpenPesquisa() {
    this.index = 0;
}

  OpenCadastro(ID) {
    this.index = 1;
    this.carregarCliente(ID);
}

  onTabChange(event) {
   this.index = event.index;
  }

  get editando(){
    return Boolean(this.cliente.id);
  }



}
