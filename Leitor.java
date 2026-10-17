public class Leitor {
    private int codigo;
    private String nome;
    private String email;
    public Leitor(int codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }
    public void atualizarDados(String nome, String email) {
        if (nome == null || nome.isBlank() || email == null || email.isBlank()) {
            throw new IllegalArgumentException("Nome e e-mail são obrigatórios.");
        }
        this.nome = nome;
        this.email = email;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return codigo + " - " + nome + " (" + email + ")";
    }
}
