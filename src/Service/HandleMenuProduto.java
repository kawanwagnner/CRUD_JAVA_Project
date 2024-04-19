package Service;

import java.io.*;
import java.util.*;

import Models.Produto;
import Utils.GerenciadorDeProdutos;

public class HandleMenuProduto {

	private static final String NOME_ARQUIVO = "produtos.txt";

	Scanner input = new Scanner(System.in);

	// Gerenciador
	GerenciadorDeProdutos gp = new GerenciadorDeProdutos();

	// Construtor Vazio
	public HandleMenuProduto() {
		// toda vez que a classe menu, for instanciada, o nosso arquivo sera verificado
		gp.verificarECria("produtos.txt");
	}

	public void criar() {
		System.out.println("Digite o nome do produto: ");
		String nome = input.next();
		System.out.println("Digite o preço do produto: ");
		double preco = input.nextDouble();
		System.out.println("Digite a quantidade do produto: ");
		int qtd = input.nextInt();

		long id = getNextId();

		Produto produto = new Produto(id, nome, preco, qtd);
		gp.adicionarProduto(produto);
	}

	public void editar() {
		System.out.println("Digite o ID do Produto a ser editado: ");
		int id = input.nextInt();

		System.out.println("Digite o novo nome do produto: ");
		String nome = input.next();
		System.out.println("Digite o novo preço do produto: ");
		double preco = input.nextDouble();
		System.out.println("Digite a nova quantidade do produto: ");
		int qtd = input.nextInt();

		gp.editarProduto(id, nome, preco, qtd);
	}

	public void deletar() {
		System.out.println("Digite o ID do Produto a ser deletado: ");
		int id = input.nextInt();
		gp.deletarProduto(id);
	}

	public void listar() {
		gp.listarProdutos();
	}

	public void detalhar() {
		System.out.println("Digite o ID do Produto a ser deletado: ");
		int id = input.nextInt();
		gp.detalharProduto(id);
	}
	
	public void calcular() {
		gp.calcularPrecos();
	}
	
	public void calcularQTD() {
		gp.calcularQTDs();
	}

	private long getNextId() {
		List<Produto> produtos = gp.lerProdutos();
		long maxId = 0;
		// for => forEacch
		// Único produto : Lista com todos
		for (Produto produto : produtos) {
			long id = produto.getId();
			// 1
			if (id > maxId) {
				// Lógica para descobrir o último id
				maxId = id;
				// 10
			}
		}
		return maxId + 1;
	}

	public void deletarProduto(int id) {
		List<Produto> produtos = gp.lerProdutos();

		if (produtos.removeIf(produto -> produto.getId() == id)) {
			System.out.println("Produto deletado com sucesso.");
		} else {
			System.out.println("Produto não encontrada.");
		}
	}

	public void reescreverArquivo(List<Produto> produtos) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
			for (Produto produto : produtos) {
				bw.write(produto.toString());
				bw.newLine();
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro ao reescrever o arquivo: " + e.getMessage());
		}

	}

}
