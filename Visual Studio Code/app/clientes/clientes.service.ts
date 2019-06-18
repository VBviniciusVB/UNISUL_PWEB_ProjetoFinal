import { Cliente } from './model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  clientesURL = 'http://localhost:8080/clientes';
  urlFiltro;

  constructor(private http: HttpClient) { }



  pesquisar(filtro: any): Promise<any> {
    if(filtro.nome){
      this.urlFiltro = 'http://localhost:8080/clientes/busca?nome='+filtro.nome;
    }else{
      this.urlFiltro = 'http://localhost:8080/clientes';
    }

    return this.http.get<any>(this.urlFiltro).toPromise();
  }

  excluir(id:number):Promise<void>{
    return this.http.delete(this.clientesURL+'/'+id)
    .toPromise()
    .then(() => null);
  }

  adicionar(cliente: Cliente): Promise<any>{
    return this.http.post(this.clientesURL, cliente)
    .toPromise();
  }

  alterar(cliente: Cliente): Promise<any>{
    return this.http.put(this.clientesURL+'/'+cliente.id, cliente)
    .toPromise();
  }

  buscarPorCodigo(codigo: number): Promise<Cliente> {
    return this.http.get<Cliente>(this.clientesURL+'/'+codigo).toPromise();
  }

}
