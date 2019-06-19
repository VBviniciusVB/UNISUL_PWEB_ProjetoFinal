import { Emprestimo } from './model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EmprestimosService {

  emprestimosURL = 'http://localhost:8080/emprestimos';
  urlFiltro;

  constructor(private http: HttpClient) { }



  pesquisar(filtro: any): Promise<any> {
    if(filtro.nome){
      this.urlFiltro = 'http://localhost:8080/emprestimos/busca?nome='+filtro.nome;
    }else{
      this.urlFiltro = 'http://localhost:8080/emprestimos';
    }

    return this.http.get<any>(this.urlFiltro).toPromise();
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
