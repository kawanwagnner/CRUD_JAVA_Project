package Sistema;

import java.util.Scanner;

import Models.Usuario;
import Models.Produto;

public class Sistema {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Usuario systemUser = new Usuario();
		Produto systemProduct = new Produto();
		
		int escolhaSistema;
		
		System.out.println("Bem-vindo(a) ao Systema!");
		System.out.println("");
		do {
			
			System.out.println("1 - Sistema de Cadastro User \n2 - Sistema de Cadastro Produto \n5 - Sair\n");
			escolhaSistema = input.nextInt();
			switch (escolhaSistema) {
			case 1: {
				systemUser.systemUser();
				continue;
			}
			case 2: {
				systemProduct.systemProduct();				
				continue;
			}
			case 5: {
				System.exit(0);
				break;
			}
			default:
				System.err.println("[ERRO!@]");
				break;
			}

		} while (escolhaSistema != 5);
		input.close();
		
	}

}
