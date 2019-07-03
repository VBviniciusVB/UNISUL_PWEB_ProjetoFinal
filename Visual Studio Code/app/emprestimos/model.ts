export class Emprestimo{
  id: number;
  dataemprestimo: string;
  datadevolucao: string;
  livro = new Livro();
  cliente = new Cliente();
}

export class Livro{
  id: number;
  nome: string;
  area: string;
  estoque: number;
  local: string;
  alugados: number;
}

export class Cliente{
  id: number;
  nome: string;
  tipo: string;
  sexo: string;
  idade: number;
  alugou: number;
}
