public class ItemEmprestimo {
    private Livro livro;
    private boolean devolvido;

    public ItemEmprestimo(Livro livro) {
        this.livro = livro;
        this.devolvido = false;
    }
    public void registrarDevolucao() {
        devolvido = true;
    }

    public Livro getLivro() {
        return livro;
    }

    public boolean isDevolvido() {
        return devolvido;
    }
}