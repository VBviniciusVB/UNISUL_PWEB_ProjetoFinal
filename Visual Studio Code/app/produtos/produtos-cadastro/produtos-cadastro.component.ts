import { FormControl } from '@angular/forms';
import { Categoria } from './../../categorias/model';
import { CategoriasService } from './../../categorias/categorias.service';
import { ActivatedRoute } from '@angular/router';
import { ProdutosService } from './../produtos.service';
import { Produto } from './../model';
import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-produtos-cadastro',
  templateUrl: './produtos-cadastro.component.html',
  styleUrls: ['./produtos-cadastro.component.css']
})
export class ProdutosCadastroComponent implements OnInit {

  produto = new Produto();
  categorias: Categoria[];

  constructor(
    private service: ProdutosService,
    private categoriaService: CategoriasService,
    private messageService: MessageService,
    private rota: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.pesquisarCategorias();
  }

  pesquisarCategorias(){
    this.categoriaService.pesquisar("")
    .then((dados)=>{
      this.categorias=dados;
    });
  }

  salvar(form: FormControl) {
    this.service.adicionar(this.produto)
    .then( ()=>{
      this.messageService.add({severity:'success', summary:'Cadastro', detail:'Produto '+this.produto.nome+' cadastrado'});
      form.reset();
    });
  }

}
