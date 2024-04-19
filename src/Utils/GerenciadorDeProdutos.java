package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Models.Produto;

public class GerenciadorDeProdutos {
	private static final String NOME_ARQUIVO = "produtos.txt";

	// Verificar a existencia do nosso banco caso não exista.
	public void verificarECria(String nomeArquivo) {
		// file => arquivo
		File arquivo = new File(nomeArquivo);
		// verificar se o arquivo existe
		if (arquivo.exists()) {
			System.out.println("Banco funcionando!");
		} else {
			// tente criar, em caso de erro exibe o erro.
			try {
				// Criar o novo arquivo
				arquivo.createNewFile();
				System.out.println("Arquivo criado com sucesso.");
			} catch (Exception e) {
				System.out.println("Ocorreu um erro ao criar o arquivo " + e.getMessage());
			}
		}
	}

	public void adicionarProduto(Produto produto) {
		// Writer => Escrever
		// BuffereWriter, FileWriter
		// BufferedWriter, proporciona uma eficiente escrita
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
			bw.write(produto.toString()); // 1;arthur;12345
			bw.newLine(); // nova linha no arquivo txt
			System.out.println("Produto adicionado com sucesso!");
		} catch (Exception e) {
			System.out.println("Ocorreu um ero ao escrever o produto no arquivo " + e.getMessage());
		}
	}

	public List<Produto> lerProdutos() {
		List<Produto> produtos = new ArrayList<Produto>();
		// Buffed, File, Reaer
		try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
			String linha; // linha => 1;noe;senha

			while ((linha = br.readLine()) != null) {
				String[] partes = linha.split(";");
				produtos.add(new Produto(Long.parseLong(partes[0]), partes[1], Double.parseDouble(partes[2]), Integer.parseInt(partes[3])));
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao ler o arquivo " + e.getMessage());
		}
		return produtos;
	}

	public void deletarProduto(int id) {
		List<Produto> produtos = lerProdutos();
		// array function => o produto vai passar de linha em linha
		if (produtos.removeIf(produto -> produto.getId() == id)) {
			reescreverArquivo(produtos);
			System.out.println("Produto deletado com sucesso");
		} else {
			System.out.println("Produto não encontrado");
		}
	}

	public void editarProduto(int id, String novoNome, double novoPreco, int qtd) {
		List<Produto> produtos = lerProdutos();
		boolean find = false;

		for (Produto produto : produtos) {
			if (produto.getId() == id) {
				produto.setNome(novoNome);
				produto.setPreco(novoPreco);
				produto.setQtd(qtd);
				find = true;
				break;
			}
		}

		if (find) {
			reescreverArquivo(produtos);
			System.out.println("Produto editado com sucesso!");
		} else {
			System.err.println("Produto não encontrado! :(");
		}

	}

	public void reescreverArquivo(List<Produto> produtos) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
			for (Produto produto : produtos) {
				bw.write(produto.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao reescrever o arquivo! " + e.getMessage());
		}
	}

	public void listarProdutos() {
		List<Produto> produtos = lerProdutos();
		// nenhum produto
		if (produtos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado...");
		} else {
			System.out.println("Lista de produtos");
			for (Produto produto : produtos) {
				System.out.println("ID: " + produto.getId() + ", Nome: " + "" + produto.getNome() + ", preço: "
						+ produto.getPreco() + ", Quantidade: " + produto.getQtd());
			}
		}
	}
	
	public void calcularPrecos() {
		List<Produto> produtos = lerProdutos();
		// nenhum produto
		if (produtos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado...");
		} else {
			System.out.println("O valor total dos preços somados é: " + somarPrecosProdutos());
		}
		
		
	}
	
	public void calcularQTDs() {
		List<Produto> produtos = lerProdutos();
		// nenhum produto
		if (produtos.isEmpty()) {
			System.out.println("Nenhum produto cadastrado...");
		} else {
			System.out.println("O total de Produtos disponívies atualmente em estoque é: " + somarQTDS());
		}
	}
	
	public double somarPrecosProdutos() {
        double soma = 0.0;
        List<Produto> produtos = lerProdutos();
        for (Produto produto : produtos) {
            soma += produto.getPreco() * produto.getQtd();
        }
        return soma;
    }
	
	public double somarQTDS() {
		double soma = 0.0;
		List<Produto> produtos = lerProdutos();
		for (Produto produto : produtos) {
			soma += produto.getQtd();
		}
		return soma;
	}

	public void detalharProduto(int id) {
		List<Produto> produtos = lerProdutos();
		boolean find = false;

		for (Produto produto : produtos) {
			if (produto.getId() == id) {
				System.out.println(produto.getNome());
				System.out.println(produto.getPreco());
				System.out.println(produto.getQtd());
				find = true;
				break;
			}
		}

		if (!find) {
			System.err.println("Produto não encontrado! :(");
		}

	}

	public void loginProduto(String nomeValue, double senhaValue) {
		List<Produto> produtos = lerProdutos();

		boolean validador = false;
		for (Produto produto : produtos) {

			if (produto.getNome().equalsIgnoreCase(nomeValue) && produto.getPreco() == senhaValue) {
				System.out.println("Acesso Permitido, você foi logado.");
				validador = true;
				break;
			}
		}
		if (validador == false) {
			System.err.println("Acesso negado! Nome ou senha incorretos.");
		}


	}
}
