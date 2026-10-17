import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Emprestimo {
    private int codigo;
    private LocalDate dataEmprestimo;
    private boolean aberto;
    private Leitor leitor;
    private ArrayList<ItemEmprestimo> itens = new ArrayList<>();

    public Emprestimo(int codigo, Leitor leitor) {
        this.codigo = codigo;
        this.leitor = leitor;
        this.dataEmprestimo = LocalDate.now();
        this.aberto = true;
    }
    public boolean adicionarLivro(Livro livro) {
        if (livro == null) {
            return false;
        }
        if (contemLivro(livro)) {
            return false;
        }
        itens.add(new ItemEmprestimo(livro));
        return true;
    }

    public boolean contemLivro(Livro livro) {
        for (ItemEmprestimo item : itens) {
            if (item.getLivro().getCodigo() == livro.getCodigo()) {
                return true;
            }
        }
        return false;
    }
    public void finalizar() {
        if(!aberto){
            return;
        }
        for (int i = 0; i < itens.size(); i++) {
            ItemEmprestimo itemEmprestimo = itens.get(i);
            itemEmprestimo.registrarDevolucao();
       itemEmprestimo.getLivro().devolver();
        }
        aberto =false;
    }

    public String toString() {
        String titulos = "";
        for (ItemEmprestimo item : itens) {
            if (!titulos.isEmpty()) {
                titulos += ", ";
            }
            titulos += item.getLivro().getTitulo();
        }
        return codigo + " - " + leitor.getNome() + " - "
                + dataEmprestimo.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " - " + (aberto ? "aberto" : "finalizado")
                + " - livros: " + titulos;
    }

    public int getCodigo() {
        return codigo;
    }

    public boolean isAberto() {
        return aberto;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
}