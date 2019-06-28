export class Emprestimo{
  id: number;
  livros: Livro[] = [];
  clientes: Cliente[] = [];
  dataemprestimo: Date;
  datadevolucao: Date;
}

export class Livro{
  id: number;
  nome: string;
  tipo: string;
  estoque: number;
  local: string;
}

export class Cliente{
  id: number;
  nome: string;
  tipo: string;
  sexo: string;
  idade: number;
}

