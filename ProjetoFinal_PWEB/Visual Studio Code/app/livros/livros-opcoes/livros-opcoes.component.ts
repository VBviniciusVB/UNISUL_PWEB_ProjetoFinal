import { Livro } from '../model';
import { Component, OnInit } from '@angular/core';
import { LivrosService } from '../livros.service';
import { MessageService } from 'primeng/api';
import { FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-livros-opcoes',
  templateUrl: './livros-opcoes.component.html',
  styleUrls: ['./livros-opcoes.component.css']
})
export class LivrosOpcoesComponent implements OnInit {

  livro = new Livro();

  livros = [];



  nomeBusca:string;

  constructor(
    private service: LivrosService,
    private messageService: MessageService,
    private rota: ActivatedRoute,
    //private rotaP: Router
  ) { }

  inserir(form: FormControl) {
    this.service.adicionar(this.livro)
    .then( ()=>{
      this.messageService.add({severity:'success', summary:'Cadastro', detail:'Livro '+this.livro.nome+' cadastrado'});
      form.reset();
    });
  }

  ngOnInit() {
    this.pesquisar();
  }

  carregarLivro(id:number){
    this.service.buscarPorCodigo(id)
      .then((data) => {
        this.livro = data;
      }
    );
  }

  alterar(form: FormControl) {
    this.service.alterar(this.livro)
    .then( ()=>{
      this.messageService.add({severity:'success', summary:'Edição', detail:'Livro '+this.livro.nome+' alterada'});
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
      this.livros=dados;
    });
  }

  get editando(){
    return Boolean(this.livro.id);
  }



}
