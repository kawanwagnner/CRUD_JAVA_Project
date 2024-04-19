package Models;

import java.util.Scanner;

import Service.HandleMenu;
import Service.HandleMenuProduto;

public class Produto {
	private long id;
	private String nome;
	private double preco;
	private int qtd;

	public Produto(long id, String nome, double preco, int qtd) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.qtd = qtd;
	}
	
	public Produto() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	@Override
	public String toString() {
		return id + ";" + nome + ";" + preco + ";" + qtd;
	}
	
	public void systemProduct() {
		Scanner input = new Scanner(System.in);
		HandleMenuProduto hm = new HandleMenuProduto();
		int escolha = 0;

		do {
			System.out.println("1 - Criar Produto \n2 - Editar Produto \n3 - Deletar Produto \n4 - Listar Produto \n5 - Detalhes Produto \n6 - Calc. Preços \n7 - Calc. QTDs \n10 - Sair\n");
			escolha = input.nextInt();

			switch (escolha) {
			case 1: {
				hm.criar();
				break;
			}
			case 2: {
				hm.editar();
				break;
			}
			case 3: {
				hm.deletar();
				break;
			}
			case 4: {
				hm.listar();
				break;
			}
			case 5: {
				hm.detalhar();
				break;
			}
			case 6: {
				hm.calcular();
				break;
			}
			case 7: {
				hm.calcularQTD();
				break;
			}
			case 10: {
				System.exit(0);
			}
			default:
				System.err.println("Opção inválida!!");
				break;
			}
		} while (escolha != 9);

		input.close();

	}
}
