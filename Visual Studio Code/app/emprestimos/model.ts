export class Emprestimo{
  id: number;
  dataemprestimo: String;
  datadevolucao: String;
  livro: Livro[] = [];
  cliente: Cliente[] = [];
  //livro = new Livro();
  //cliente = new Cliente();
}

export class Livro{
  id: number;
  nome: String;
  area: String;
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
