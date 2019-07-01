export class Emprestimo{
  id: number;
  cliente= new Cliente();
  livro= new Livro();
  dataemprestimo: String;
  datadevolucao: String;
}

  //livros: Livro[] = [];
  //clientes: Cliente[] = [];

export class Livro{
  id: number;
  nome: String;
  tipo: String;
  estoque: number;
  local: String;
}

export class Cliente{
  id: number;
  nome: String;
  tipo: String;
  sexo: String;
  idade: number;
}

