package View;

import Model.Tarefa;

import java.util.List;
import java.util.Scanner;

public class VisaoTarefa {
    private Scanner sc;

    public VisaoTarefa() {
        this.sc = new Scanner(System.in);
    }

    public void listar(List<Tarefa> tarefas) {
        System.out.println("--- Lista de tarefas ---");
        if (tarefas.isEmpty()) {
            System.out.println("A lista esta vaiza");
        } else {
            for (Tarefa tarefa : tarefas) {
                System.out.println(tarefa);
            }
        }
    }

    public void exibirMenu() {
        System.out.println("\n--- Gerenciamento de tarefas ---");
        System.out.println("1. Adicionar tarefas");
        System.out.println("2. Listar tarefas");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void addSucesso() {
        System.out.println("Adicionado com sucesso");

    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void exibirMensagemErro(String mensagem) {
        System.out.println("ERRO: " + mensagem);
    }

    public Tarefa exibirAddTarefa() {
        System.out.println("Qual a descricao da tarefa: ");
        String descricao = sc.nextLine();
        System.out.println("Quem e responsavel pela tarefa: ");
        String responsavel = sc.nextLine();
        System.out.println("Qual a status da tarefa: (1:Pronto,2:Pendente,3:Cancelado)");
        int statusInt = sc.nextInt();
        String statusString;
        switch (statusInt) {
            case 1:
                statusString = "Pronto";
                break;
            case 2:
                statusString = "Pendente";
                break;
            case 3:
                statusString = "Cancelado";
                break;
            default:
                statusString = "Pendente";
                System.out.println("Opção de status inválida. Definido como 'Pendente'.");
                break;
        }


        Tarefa t = new Tarefa(descricao, responsavel, statusString);

        return t;
    }


}





