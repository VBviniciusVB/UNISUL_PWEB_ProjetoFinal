import { Cliente } from './../../clientes/model';
import { Livro } from './../../livros/model';
import { Emprestimo} from './../model';
import { Component, OnInit, NgModule } from '@angular/core';
import { EmprestimosService } from '../emprestimos.service';
import { MessageService, ConfirmationService, SelectItem } from 'primeng/api';
import { FormControl } from '@angular/forms';
import { ClientesService } from '../../clientes/clientes.service';
import { LivrosService } from '../../livros/livros.service';

@Component({
  selector: 'app-emprestimos-opcoes',
  templateUrl: './emprestimos-opcoes.component.html',
  styleUrls: ['./emprestimos-opcoes.component.css']
})
export class EmprestimosOpcoesComponent implements OnInit {

  // Acessa o Model
  emprestimo = new Emprestimo();

  // Método Pesquisa
  emprestimos: Emprestimo[];

  // ComboBox
  clientes: Cliente[];
  livros: Livro[];

  //Qual aba ele esta (Procurar Cliente , Cadastrar Cliente)
  index: number = 0;

  //Se vai alterar um código ou não ( TRUE = NÃO , FALSE = SIM )
  Editar: boolean = true;

  //Procurar pelo nome dessa variável
  Procura:string;

  constructor(
    private service: EmprestimosService,
    private messageService: MessageService,
    private conf: ConfirmationService,
    private clienteService: ClientesService,
    private livroService: LivrosService
  ) {}

  ngOnInit() {

    this.pesquisar();

    this.clienteService.pesquisar('').then((dados) => {
    this.clientes = dados.map((emprestimo) => ({label: emprestimo.nome, value: emprestimo.id}));
    });

    this.livroService.pesquisar('').then((dados) => {
    this.livros = dados.map((emprestimo) => ({label: emprestimo.nome, value: emprestimo.id}));
    });

  };

  inserir(form: FormControl) {

    // Acessa o Cliente/Livro da COMBO BOX

    this.clienteService.buscarPorCodigo(this.emprestimo.cliente.id).then((data) => {
      this.emprestimo.cliente = data;

      this.livroService.buscarPorCodigo(this.emprestimo.livro.id).then((data) => {
        this.emprestimo.livro = data;

        // Verifica se passou do limite de estoque ou de livros pro cliente

        if(this.emprestimo.cliente.alugou >= 3){
          this.messageService.add({severity:'error', summary:'Erro de Inserção', detail:'O cliente já possui o máximo de livros'});
        }else{
          if(this.emprestimo.livro.alugados >= this.emprestimo.livro.estoque){
            this.messageService.add({severity:'error', summary:'Erro de Inserção', detail:'Todos os livros "'+this.emprestimo.livro.nome+'" já foram alugados'});
          }else{

            //Incrementa em +1 a quantia de livros do cliente e livros alugados

            this.emprestimo.cliente.alugou = this.emprestimo.cliente.alugou + 1;
            this.emprestimo.livro.alugados = this.emprestimo.livro.alugados + 1;

            this.clienteService.alterar(this.emprestimo.cliente)
            .then( ()=>{

              this.livroService.alterar(this.emprestimo.livro)
              .then( ()=>{

                //Finalmente adiciona o Empréstimo

                this.service.adicionar(this.emprestimo).then( ()=>{
                  this.messageService.add({severity:'success', summary:'Inserção Concluida', detail:'O Emprestimo foi cadastrado no sistema'});
                });
              });
            });
          }
        }
      });
    });
  }

  alterar(form: FormControl) {

        // Decrementa os valores anteriores

        this.emprestimo.cliente.alugou = this.emprestimo.cliente.alugou - 1;
        this.emprestimo.livro.alugados = this.emprestimo.livro.alugados - 1;

        this.clienteService.alterar(this.emprestimo.cliente)
        .then( ()=>{
          this.livroService.alterar(this.emprestimo.livro)
          .then( ()=>{

            this.service.alterar(this.emprestimo)
            .then( ()=>{
              this.messageService.add({severity:'success', summary:'Edição Concluida', detail:'O emprestimo '+this.emprestimo.id+' foi alterado'});

              this.carregarEmprestimo(this.emprestimo.id);


            });
          });
        });
  }

  salvar(form: FormControl) {
    if(this.Editar){
      this.inserir(form);
    }else{
      this.alterar(form);
    }
  }

  excluir(emprestimo: any){

    this.carregarEmprestimo(emprestimo.id);
    this.emprestimo.cliente.id = emprestimo.cliente.id;

    this.clienteService.buscarPorCodigo(this.emprestimo.cliente.id).then((data) => {
      this.emprestimo.cliente = data;

      this.livroService.buscarPorCodigo(this.emprestimo.livro.id).then((data) => {
        this.emprestimo.livro = data;

        this.emprestimo.cliente.alugou = this.emprestimo.cliente.alugou - 1;
        this.emprestimo.livro.alugados = this.emprestimo.livro.alugados - 1;

        this.clienteService.alterar(this.emprestimo.cliente)
        .then( ()=>{

          this.livroService.alterar(this.emprestimo.livro)
          .then( ()=>{

            this.service.excluir(emprestimo.id).then(()=>{
              this.pesquisar();
              this.messageService.add({severity:'success', summary:'Exclusão', detail:'Emprestimo '+emprestimo.id+' excluído'});

            });
          });
        });
      });
    });
  }

  pesquisar(){
    this.service.pesquisar(this.Procura)
    .then((dados)=>{
      this.emprestimos=dados;
    });
  }

  carregarEmprestimo(id:number){
    this.service.buscarPorCodigo(id)
      .then((data) => {
        this.emprestimo = data;
      }
    );
  }

  ConfirmarExclusao(emprestimo:any){
    this.conf.confirm({
      message: 'Tem certeza que deseja excluir '+emprestimo.nome+'?',
      accept: () => {
        this.excluir(emprestimo);
      }
    });
  }

  OpenPesquisa() {
    this.index = 0;
}

  OpenCadastro(ID) {
    this.index = 1;
    this.carregarEmprestimo(ID);
    this.Editar = false;
}

  onTabChange(event) {
   this.index = event.index;
   this.Editar = true;
   if (this.index == 0){
    this.pesquisar();
   }else{
     this.emprestimo = new Emprestimo();
   }
  }

  editando(){
    return Boolean(this.Editar);
  }
}
