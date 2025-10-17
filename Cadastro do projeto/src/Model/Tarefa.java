package Model;

public class Tarefa {
    private String descricao;
    private String responsavel;
    private String status;

    public Tarefa() {
    }

    public Tarefa(String descricao, String responsavel, String status) {
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return "Tarefa" +
                "descricao='" + descricao + '\'' +
                ", responsavel='" + responsavel + '\'' +
                ", status='" + status + '\''
                ;
    }
}
