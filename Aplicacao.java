import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacao {
    private Scanner scanner = new Scanner(System.in);
    private Biblioteca biblioteca = new Biblioteca();
    private int opcao = -1;
    public static void main(String[] args) {
        new Aplicacao().executar();
    }

    public void executar() {
        do {
            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1 - Cadastrar leitor");
            System.out.println("2 - Consultar leitor");
            System.out.println("3 - Listar leitores");
            System.out.println("4 - Atualizar leitor");
            System.out.println("5 - Remover leitor");
            System.out.println("6 - Cadastrar livro");
            System.out.println("7 - Consultar livro");
            System.out.println("8 - Listar livros");
            System.out.println("9 - Atualizar livro");
            System.out.println("10 - Remover livro");
            System.out.println("11 - Registrar empréstimo");
            System.out.println("12 - Listar empréstimos");
            System.out.println("13 - Registrar devolução");
            System.out.println("0 - Sair");
            System.out.println("Escolha uma opção:");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                int codigo;

                switch (opcao) {
                    case 1: {
                        System.out.println("Digite o código do leitor:");
                        codigo = Integer.parseInt(scanner.nextLine());

                        System.out.println("Digite o nome do leitor:");
                        String nome = scanner.nextLine();

                        System.out.println("Digite o email do leitor:");
                        String email = scanner.nextLine();

                        Leitor leitor = new Leitor(codigo, nome, email);

                        if (biblioteca.cadastrarLeitor(leitor)) {
                            System.out.println("Leitor cadastrado.");
                        } else {
                            System.out.println("Não foi possível cadastrar o leitor.");
                        }
                        break;
                    }

                    case 2: {
                        System.out.println("Digite o código do leitor desejado:");
                        codigo = Integer.parseInt(scanner.nextLine());

                        Leitor leitor = biblioteca.consultarLeitor(codigo);

                        if (leitor != null) {
                            System.out.println(leitor);
                        } else {
                            System.out.println("Registro não encontrado");
                        }
                        break;
                    }

                    case 3:
                        biblioteca.listarLeitores();
                        break;

                    case 4: {
                        System.out.println("Digite o código do leitor para atualizar:");
                        codigo = Integer.parseInt(scanner.nextLine());

                        Leitor encontrado = biblioteca.consultarLeitor(codigo);

                        if (encontrado == null) {
                            System.out.println("Registro não encontrado");
                        } else {
                            System.out.println("Digite o novo nome para atualizar:");
                            String nome = scanner.nextLine();

                            System.out.println("Digite o novo email para atualizar:");
                            String email = scanner.nextLine();

                            try {
                                encontrado.atualizarDados(nome, email);
                                System.out.println("Dados do leitor atualizados");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    }

                    case 5: {
                        System.out.println("Digite o código do leitor para remover:");
                        codigo = Integer.parseInt(scanner.nextLine());

                        Leitor remover = biblioteca.consultarLeitor(codigo);

                        if (remover != null) {
                            if (biblioteca.removerLeitor(remover)) {
                                System.out.println("Leitor removido.");
                            } else {
                                System.out.println("Leitor não removido o empréstimo está em aberto");
                            }
                        } else {
                            System.out.println("Registro não encontrado");
                        }
                        break;
                    }

                    case 6: {
                        System.out.println("Digite o código do livro:");
                        codigo = Integer.parseInt(scanner.nextLine());

                        System.out.println("Digite o título do livro:");
                        String titulo = scanner.nextLine();

                        System.out.println("Digite o autor do livro:");
                        String autor = scanner.nextLine();

                        Livro livro = new Livro(codigo, titulo, autor);

                        if (biblioteca.cadastrarLivro(livro)) {
                            System.out.println("Livro cadastrado com sucesso");
                        } else {
                            System.out.println("Não foi possível cadastrar o livro");
                        }
                        break;
                    }

                    case 7: {
                        System.out.println("Digite o código do livro desejado:");
                        codigo = Integer.parseInt(scanner.nextLine());

                        Livro livro = biblioteca.consultarLivro(codigo);

                        if (livro != null) {
                            System.out.println(livro);
                        } else {
                            System.out.println("Registro não encontrado");
                        }
                        break;
                    }

                    case 8:
                        biblioteca.listarLivros();
                        break;

                    case 9: {
                        System.out.println("Digite o código do livro para atualizar:");
                        codigo = Integer.parseInt(scanner.nextLine());

                        Livro achado = biblioteca.consultarLivro(codigo);

                        if (achado != null) {
                            System.out.println("Digite o novo título:");
                            String titulo = scanner.nextLine();

                            System.out.println("Digite o novo autor:");
                            String autor = scanner.nextLine();

                            try {
                                achado.atualizarDados(titulo, autor);
                                System.out.println("Livro atualizado com sucesso");
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("Registro não encontrado");
                        }
                        break;
                    }

                    case 10: {
                        System.out.println("Digite o código do livro para remover:");
                        codigo = Integer.parseInt(scanner.nextLine());

                        Livro tirar = biblioteca.consultarLivro(codigo);

                        if (tirar != null) {
                            if (biblioteca.removerLivro(tirar)) {
                                System.out.println("Livro removido com sucesso");
                            } else {
                                System.out.println("Livro não removido o empréstimo está aberto");
                            }
                        } else {
                            System.out.println("Registro não encontrado");
                        }
                        break;
                    }

                    case 11: {
                        System.out.println("Digite o código do empréstimo:");
                        int codigoEmprestimo = Integer.parseInt(scanner.nextLine());

                        System.out.println("Digite o código do leitor:");
                        codigo = Integer.parseInt(scanner.nextLine());

                        Leitor leitorEmprestimo = biblioteca.consultarLeitor(codigo);

                        if (leitorEmprestimo == null) {
                            System.out.println("Registro não encontrado");
                            break;
                        }

                        ArrayList<Livro> livrosSelecionados = new ArrayList<>();

                        do {
                            System.out.println("Digite o código do livro (0 para encerrar):");
                            int codigoLivro = Integer.parseInt(scanner.nextLine());
                            if (codigoLivro == 0) {
                                break;
                            }
                            Livro livro = biblioteca.consultarLivro(codigoLivro);

                            if (livro != null) {
                                livrosSelecionados.add(livro);
                            } else {
                                System.out.println("Registro não encontrado");
                            }
                        } while (true);

                        boolean registrado = biblioteca.registrarEmprestimo(
                                codigoEmprestimo,
                                leitorEmprestimo,
                                livrosSelecionados);

                        if (registrado) {
                            System.out.println("Empréstimo registrado com sucesso");
                        } else {
                            System.out.println("Não foi possível registrar o empréstimo.");
                            System.out.println("Verifique se o código já existe");
                            System.out.println("e se todos os livros estão disponíveis.");
                        }

                        break;
                    }

                    case 12:
                        biblioteca.listarEmprestimos();
                        break;

                    case 13: {
                        System.out.println("Digite o código do empréstimo para registrar a devolução:");
                        int codigoDevolucao = Integer.parseInt(scanner.nextLine());

                        boolean devolvido = biblioteca.registrarDevolucao(codigoDevolucao);

                        if (devolvido) {
                            System.out.println("Devolução registrada com sucesso");
                        } else {
                            System.out.println("Não foi possível registrar a devolução");
                        }
                        break;
                    }

                    case 0:
                        System.out.println("Aplicação encerrada");
                        break;

                    default:
                        System.out.println("Opção inválida");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro: ");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
