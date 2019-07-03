import { Emprestimo, Cliente } from './model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmprestimosService {

  livrosURL = 'http://localhost:8080/livros';
  clientesURL = 'http://localhost:8080/clientes';
  emprestimosURL = 'http://localhost:8080/emprestimos';
  urlFiltro;

  constructor(private http: HttpClient) { }

  pesquisar(filtro: String): Promise<any> {
    let urlRed = 'http://localhost:8080/emprestimos/busca'.concat(filtro ? "?nome=" + filtro : '');
    return this.http.get<any>(urlRed).toPromise();
  }

  excluir(id:number):Promise<void>{
    return this.http.delete(this.emprestimosURL+'/'+id)
    .toPromise()
    .then(() => null);
  }

  adicionar(emprestimo: Emprestimo): Promise<any>{
    return this.http.post(this.emprestimosURL, emprestimo)
    .toPromise();
  }

  alterar(emprestimo: Emprestimo): Promise<any>{
    return this.http.put(this.emprestimosURL+'/'+emprestimo.id, emprestimo)
    .toPromise();
  }

  buscarPorCodigo(codigo: number): Promise<Emprestimo> {
    return this.http.get<Emprestimo>(this.emprestimosURL+'/'+codigo).toPromise();
  }
}
