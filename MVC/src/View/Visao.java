package View;
import Model.Modelo;

import java.util.List;


public class Visao {

    public void exibirListaProdutos(List<Modelo> list) {
        System.out.println("--- Lista de Produtos ---");
        if (list.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Modelo m : list) {
                System.out.println(m);
            }
        }
        System.out.println("------------------------");
    }

    public void exibirMensagemSucesso(String mensagem) {
        System.out.println("SUCESSO: " + mensagem);
    }

    public void exibirMensagemErro(String mensagem) {
        System.out.println("ERRO: " + mensagem);
    }

    public void exibirMenu() {
        System.out.println("\n--- Gerenciamento de Produtos ---");
        System.out.println("1. Adicionar produto");
        System.out.println("2. Listar produtos");
        System.out.println("3. Atualizar quantidade");
        System.out.println("4. Remover produto");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }
}
