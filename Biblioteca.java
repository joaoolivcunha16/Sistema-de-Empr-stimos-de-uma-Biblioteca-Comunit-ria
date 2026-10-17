import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Leitor> leitores = new ArrayList<>();
    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();


    public boolean cadastrarLeitor(Leitor leitor) {
        if (leitor == null) {
            return false;
        }
        if (leitor.getCodigo() <= 0) {
            return false;
        }
        if (leitor.getNome() == null || leitor.getNome().isBlank()
                || leitor.getEmail() == null || leitor.getEmail().isBlank()) {
            return false;
        }
        for (Leitor l : leitores) {
            if (l.getCodigo() == leitor.getCodigo()) {
                return false;
            }
        }

        leitores.add(leitor);
        return true;
    }

    public Leitor consultarLeitor(int codigo) {
        for (Leitor leitor : leitores) {
            if (leitor.getCodigo() == codigo) {
                return leitor;
            }
        }
        return null;
    }

    public void listarLeitores() {
        for (Leitor leitor : leitores) {
            System.out.println(leitor);
        }
    }

    public boolean removerLeitor(Leitor leitor) {
if(leitor == null){
    return false;
}
for(Emprestimo e : emprestimos ){
    if(e.isAberto() && e.getLeitor().getCodigo() == leitor.getCodigo()){
        return false;

    }
    }
        return leitores.remove(leitor);
    }

    public boolean cadastrarLivro(Livro livro) {
        if (livro == null) {
            return false;
        }
        if (livro.getCodigo() <= 0) {
            return false;
        }
        if (livro.getTitulo() == null || livro.getTitulo().isBlank()
                || livro.getAutor() == null || livro.getAutor().isBlank()) {
            return false;
        }
        for (Livro l : livros) {
            if (l.getCodigo() == livro.getCodigo()) {
                return false;
            }
        }

        livros.add(livro);
        return true;
    }

    public Livro consultarLivro(int codigo) {
        for (Livro livro : livros) {
            if (livro.getCodigo() == codigo) {
                return livro;
            }
        }
        return null;
    }

    public void listarLivros() {
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    public boolean removerLivro(Livro livro) {
        if (livro == null) {
            return false;
        }
        for (Emprestimo e : emprestimos) {
            if (e.isAberto() && e.contemLivro(livro)) {
                return false;
            }
        }
        return livros.remove(livro);
    }

    public boolean registrarEmprestimo(int codigo, Leitor leitor, ArrayList<Livro> livrosSelecionados) {
        if (codigo <= 0) {
            return false;
        }
        for (Emprestimo e : emprestimos) {
            if (e.getCodigo() == codigo) {
                return false;
            }
        }
        if (leitor == null || consultarLeitor(leitor.getCodigo()) == null) {
            return false;
        }
        if (livrosSelecionados == null || livrosSelecionados.isEmpty()) {
            return false;
        }
        for (Livro livro : livrosSelecionados) {
            if (livro == null || consultarLivro(livro.getCodigo()) == null || !livro.isDisponivel()) {
                return false;
            }
        }
        for (int i = 0; i < livrosSelecionados.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (livrosSelecionados.get(j).getCodigo() == livrosSelecionados.get(i).getCodigo()) {
                    return false;
                }
            }
        }
        Emprestimo emprestimo = new Emprestimo(codigo, leitor);
        for (Livro livro : livrosSelecionados) {
            emprestimo.adicionarLivro(livro);
            livro.emprestar();
        }
        emprestimos.add(emprestimo);

        return true;
    }

    public void listarEmprestimos() {
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo);
        }
    }

    public boolean registrarDevolucao(int codigo) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getCodigo() == codigo) {
                if (!emprestimo.isAberto()) {
                    return false;
                }
                emprestimo.finalizar();
                return true;
            }
        }
        return false;
    }


}