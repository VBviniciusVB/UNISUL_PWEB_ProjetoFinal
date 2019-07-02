import { Livro } from '../model';
import { Component, OnInit } from '@angular/core';
import { LivrosService } from '../livros.service';
import { MessageService, ConfirmationService, SelectItem } from 'primeng/api';
import { FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

interface Area {
  name: string;
  code: string;
}

@Component({
  selector: 'app-livros-opcoes',
  templateUrl: './livros-opcoes.component.html',
  styleUrls: ['./livros-opcoes.component.css']
})

export class LivrosOpcoesComponent implements OnInit {

  livro = new Livro();
  livros = [];

  index: number = 0;
  Editar: boolean = true;
  nomeBusca:string;

  LivroGroupArea: SelectItem[];
  LivroSelecionadoArea: Area;

  constructor(
    private service: LivrosService,
    private messageService: MessageService,
    private conf: ConfirmationService
  ) {
    this.LivroGroupArea = [
          {label:'', value:''},
          {label:'Épico', value:'Épico'},
          {label:'Fábula', value:'Fábula'},
          {label:'Epopeia', value:'Epopeia'},
          {label:'Novela', value:'Novela'},
          {label:'Conto', value:'Conto'},
          {label:'Crônica', value:'Crônica'},
          {label:'Ensaio', value:'Ensaio'},
          {label:'Romance', value:'Romance'},
          {label:'Elegia', value:'Elegia'},
          {label:'Ode', value:'Ode'},
          {label:'Écloga', value:'Écloga'},
          {label:'Soneto', value:'Soneto'},
          {label:'Auto', value:'Auto'},
          {label:'Comédia', value:'Comédia'},
          {label:'Tragédia', value:'Tragédia'},
          {label:'Tragicomédia', value:'Tragicomédia'},
          {label:'Farsa', value:'Farsa'}
    ];}

  ngOnInit() {
    this.pesquisar();
  }

  inserir(form: FormControl) {
    this.service.adicionar(this.livro)
    .then( ()=>{
      this.messageService.add({severity:'success', summary:'Inserção Concluida', detail:'O livro '+this.livro.nome+' foi adicionado'});
    });
  }

  alterar(form: FormControl) {
    this.service.alterar(this.livro)
    .then( ()=>{
      this.messageService.add({severity:'success', summary:'Edição Concluida', detail:'O livro '+this.livro.nome+' foi alterado'});
    });
  }

  salvar(form: FormControl) {
    if(this.Editar){
      this.inserir(form);
    }else{
      this.alterar(form);
    }
  }

  excluir(livro: any){
    this.service.excluir(livro.id)
    .then(()=>{
      this.pesquisar();
      this.messageService.add({severity:'success', summary:'Exclusão', detail:'Livro '+livro.nome+' excluída'});
    });
  }

  pesquisar(){
    this.service.pesquisar({nome:this.nomeBusca})
    .then((dados)=>{
      this.livros=dados;
    });
  }

  carregarLivro(id:number){
    this.service.buscarPorCodigo(id)
      .then((data) => {
        this.livro = data;
      }
    );
  }

  ConfirmarExclusao(livro:any){
    this.conf.confirm({
      message: 'Tem certeza que deseja excluir '+livro.nome+'?',
      accept: () => {
        this.excluir(livro);
      }
    });
  }

  OpenPesquisa() {
    this.index = 0;
}

  OpenCadastro(ID) {
    this.index = 1;
    this.carregarLivro(ID);
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
