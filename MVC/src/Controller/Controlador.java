package Controller;

import Model.Modelo;
import SQL.DatabaseConnection;
import View.Visao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Controlador {

    private Visao view;
    private Scanner sc;

    public Controlador() {
        this.view = new Visao();
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao = -1;
        while (opcao != 5) {
            view.exibirMenu();

            try {
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (java.util.InputMismatchException e) {
                sc.nextLine();
                opcao = 0;
            }

            switch (opcao) {
                case 1:
                    executarAdicionarProduto();
                    break;
                case 2:
                    executarListarProdutos();
                    break;
                case 3:
                    executarAtualizarQuantidade();
                    break;
                case 4:
                    executarRemoverProduto();
                    break;
                case 5:
                    view.exibirMensagemSucesso("Saindo do programa. Até mais!");
                    break;
                default:
                    view.exibirMensagemErro("Opção inválida. Tente novamente.");
            }
        }
    }



    private void executarAdicionarProduto() {
        view.exibirMensagemSucesso("--- Adicionar Novo Produto ---");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = 0;
        if (sc.hasNextInt()) {
            quantidade = sc.nextInt();
            sc.nextLine();
        } else {
            sc.nextLine();
            view.exibirMensagemErro("Quantidade inválida. Voltando ao menu.");
            return;
        }

        System.out.print("Preço: ");
        double preco = 0.0;
        if (sc.hasNextDouble()) {
            preco = sc.nextDouble();
            sc.nextLine();
        } else {
            sc.nextLine();
            view.exibirMensagemErro("Preço inválido. Voltando ao menu.");
            return;
        }

        Modelo novoProduto = new Modelo();
        novoProduto.setNome(nome);
        novoProduto.setQuantidade(quantidade);
        novoProduto.setPreco(preco);

        if (adicionarProdutoAoBanco(novoProduto)) {
            view.exibirMensagemSucesso("Produto '" + nome + "' adicionado com sucesso!");
        } else {
            view.exibirMensagemErro("Falha ao adicionar produto.");
        }
    }

    private void executarListarProdutos() {
        List<Modelo> lista = listarProdutosDoBanco();
        view.exibirListaProdutos(lista);
    }

    private void executarRemoverProduto() {
        view.exibirMensagemSucesso("--- Remover Produto ---");

        System.out.print("Digite o ID do produto a remover: ");
        int id = -1;
        if (sc.hasNextInt()) {
            id = sc.nextInt();
            sc.nextLine();
        } else {
            sc.nextLine();
            view.exibirMensagemErro("ID inválido. Voltando ao menu.");
            return;
        }

        if (removerProdutoDoBanco(id)) {
            view.exibirMensagemSucesso("Produto com ID " + id + " removido com sucesso.");
        } else {
            view.exibirMensagemErro("Falha ao remover produto ou ID não encontrado.");
        }
    }

    private void executarAtualizarQuantidade() {
        view.exibirMensagemSucesso("--- Atualizar Quantidade ---");

        System.out.print("Digite o ID do produto a atualizar: ");
        int id = -1;
        if (sc.hasNextInt()) {
            id = sc.nextInt();
            sc.nextLine();
        } else {
            sc.nextLine();
            view.exibirMensagemErro("ID inválido. Voltando ao menu.");
            return;
        }

        System.out.print("Digite a nova quantidade: ");
        int novaQuantidade = -1;
        if (sc.hasNextInt()) {
            novaQuantidade = sc.nextInt();
            sc.nextLine();
        } else {
            sc.nextLine();
            view.exibirMensagemErro("Quantidade inválida. Voltando ao menu.");
            return;
        }

        if (atualizarQuantidadeDoBanco(id, novaQuantidade)) {
            view.exibirMensagemSucesso("Quantidade do produto ID " + id + " atualizada para " + novaQuantidade);
        } else {
            view.exibirMensagemErro("Falha ao atualizar quantidade ou ID não encontrado.");
        }
    }



    public boolean adicionarProdutoAoBanco(Modelo produto) {
        String sql = "INSERT INTO produtos (nome, quantidade, preco) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade()); 
            stmt.setDouble(3, produto.getPreco());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("ERRO SQL (Adicionar): " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private List<Modelo> listarProdutosDoBanco() {
        List<Modelo> produtos = new ArrayList<>();

        String sql = "SELECT id, nome, quantidade, preco FROM produtos ORDER BY nome";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Modelo m = new Modelo();
                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setQuantidade(rs.getInt("quantidade")); 
                m.setPreco(rs.getDouble("preco"));

                produtos.add(m);
            }
        } catch (SQLException e) {
            System.err.println("ERRO SQL (Listar): " + e.getMessage());
            e.printStackTrace();
        }
        return produtos;
    }

    public boolean removerProdutoDoBanco(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("ERRO SQL (Remover): " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean atualizarQuantidadeDoBanco(int id, int novaQuantidade) {
        String sql = "UPDATE produtos SET quantidade = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("ERRO SQL (Atualizar): " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
