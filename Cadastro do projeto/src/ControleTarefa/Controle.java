package ControleTarefa;

import Model.Tarefa;
import View.VisaoTarefa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Controle {
    private List<Tarefa> tarefas;
    private VisaoTarefa visao;
    private Scanner sc;

    public Controle() {
        this.visao = new VisaoTarefa();
        this.tarefas = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }


    public void iniciar() {
        int opcao = -1;
        while (opcao != 3) {
            try {
                visao.exibirMenu();
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {
                    case 1:
                        addTarefa();
                        break;
                    case 2:
                        listar();
                        break;

                    case 3:
                        visao.exibirMensagem("Saindo do menu...");
                        break;
                    default:
                        visao.exibirMensagemErro("Opção inválida. Escolha um número entre 1 e 5.");
                }
            } catch (Exception e) {
                visao.exibirMensagemErro("Entrada inválida. Digite um número.");
                sc.nextLine();
            }
        }
    }

    public void addTarefa() {
        Tarefa tarefa = visao.exibirAddTarefa();
        tarefas.add(tarefa);
        visao.addSucesso();

    }

    public void listar() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            visao.listar(tarefas);

        }

    }


}

