import { FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CidadesService } from './../cidades.service';
import { Cidade } from './../model';
import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-cidades-cadastro',
  templateUrl: './cidades-cadastro.component.html',
  styleUrls: ['./cidades-cadastro.component.css']
})
export class CidadesCadastroComponent implements OnInit {

  cidade = new Cidade();
  estados = [];

  constructor(
    private service: CidadesService,
    private messageService: MessageService,
    private rota: ActivatedRoute
  ) { }

  ngOnInit() {
    this.pesquisarEstados();
    const codigoCidade = this.rota.snapshot.params['id'];
    if (codigoCidade) {
      this.carregarCidade(codigoCidade);
    }
  }

  carregarCidade(id: number) {
    this.service.buscarPorCodigo(id)
      .then((data) => {
        this.cidade = data;
      }
      );
  }

  inserir(form: FormControl) {
    this.service.adicionar(this.cidade)
      .then(() => {
        this.messageService.add({ severity: 'success', summary: 'Cadastro', detail: 'Cidade ' + this.cidade.nome + ' cadastrada' });
        form.reset();
      });
  }

  alterar(form: FormControl) {
    this.service.alterar(this.cidade)
      .then(() => {
        this.messageService.add({ severity: 'success', summary: 'Edição', detail: 'Cidade ' + this.cidade.nome + ' alterada' });
        form.reset();
      });
  }

  salvar(form: FormControl) {
    if (this.editando) {
      this.alterar(form);
    } else {
      this.inserir(form);
    }
  }

  get editando() {
    return Boolean(this.cidade.id);
  }

  pesquisarEstados() {
    this.service.listarUf()
      .then((dados) => {
        this.estados = dados
          .map(e => ({ label: e.nome, value: e.id }));
      });
  }

}
