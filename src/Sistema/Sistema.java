package Sistema;

import java.util.Scanner;

import Service.HandleMenu;

public class Sistema {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		HandleMenu hm = new HandleMenu();
		int escolha = 0;

		do {
			// \n
			System.out.println("1 - Criar \n2 - Editar \n3 - Deletar \n4 - Listar \n5 - Detalhes \n6 - Login \n9 - Sair\n");
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
				hm.login();
				break;
			}
			default:
				System.err.println("Opção inválida!!");
				break;
			}
		} while (escolha != 9);

		input.close();
	}

}
