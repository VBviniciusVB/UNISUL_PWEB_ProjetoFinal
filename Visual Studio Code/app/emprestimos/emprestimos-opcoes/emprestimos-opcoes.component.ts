import { Cliente } from './../../clientes/model';
import { Livro } from './../../livros/model';
import { Emprestimo} from './../model';
import { Component, OnInit, NgModule } from '@angular/core';
import { EmprestimosService } from '../emprestimos.service';
import { MessageService, ConfirmationService, SelectItem } from 'primeng/api';
import { FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ClientesService } from '../../clientes/clientes.service';
import { LivrosService } from '../../livros/livros.service';

@Component({
  selector: 'app-emprestimos-opcoes',
  templateUrl: './emprestimos-opcoes.component.html',
  styleUrls: ['./emprestimos-opcoes.component.css']
})
export class EmprestimosOpcoesComponent implements OnInit {

  emprestimo = new Emprestimo();
  emprestimos: Emprestimo[];

  //pega valores pro combo box
  clientes: Cliente[];
  livros: Livro[];

  livros2 = new Livro();


  index: number = 0;
  Editar: boolean = true;

  nomeBusca:string;

  constructor(
    private service: EmprestimosService,
    private messageService: MessageService,
    private rota: ActivatedRoute,
    private conf: ConfirmationService,
    private clienteService: ClientesService,
    private livroService: LivrosService

    //private rotaP: Router
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
    this.service.adicionar(this.emprestimo).then( ()=>{
      this.messageService.add({severity:'success', summary:'Inserção Concluida', detail:'O Emprestimo '+this.emprestimo.id+' foi adicionado'});
    });
  }

  alterar(form: FormControl) {
    this.service.alterar(this.emprestimo)
    .then( ()=>{
      this.messageService.add({severity:'success', summary:'Edição Concluida', detail:'O emprestimo '+this.emprestimo.id+' foi alterado'});
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
    this.service.excluir(emprestimo.id)
    .then(()=>{
      this.pesquisar();
      this.messageService.add({severity:'success', summary:'Exclusão', detail:'Emprestimo '+emprestimo.id+' excluída'});
    });
  }

  pesquisar(){
    this.service.pesquisar({nome:this.nomeBusca})
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
   }

  }

  editando(){
    return Boolean(this.Editar);
  }

}
