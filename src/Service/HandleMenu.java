package Service;

import java.io.*;
import java.util.*;

import Models.Usuario;
import Utils.GerenciadorDeUsers;

public class HandleMenu {

	private static final String NOME_ARQUIVO = "usuarios.txt";

	Scanner input = new Scanner(System.in);

	// Gerenciador
	GerenciadorDeUsers gs = new GerenciadorDeUsers();

	// Construtor Vazio
	public HandleMenu() {
		// toda vez que a classe menu, for instanciada, o nosso arquivo sera verificado
		gs.verificarECria("usuarios.txt");
	}

	public void criar() {
		System.out.println("Digite seu nome: ");
		String nome = input.next();
		System.out.println("Digite a senha: ");
		String senha = input.next();

		int id = getNextId();

		Usuario u = new Usuario(id, nome, senha);
		gs.adicionarUsuario(u);
	}

	public void editar() {
		System.out.println("Digite o ID do Usuário a ser editado: ");
		int id = input.nextInt();

		System.out.println("Digite o novo seu nome: ");
		String nome = input.next();
		System.out.println("Digite a nova senha: ");
		String senha = input.next();

		gs.editarUsuario(id, nome, senha);
	}

	public void deletar() {
		System.out.println("Digite o ID do Usuário a ser deletado: ");
		int id = input.nextInt();
		gs.deletarUsuario(id);
	}

	public void listar() {
		gs.listarUsuarios();
	}

	public void detalhar() {
		System.out.println("Digite o ID do Usuário a ser deletado: ");
		int id = input.nextInt();
		gs.detalharUsuario(id);
	}

	public void login() {
		System.out.println("Fazendo Login... ");
		System.out.println("");
		
		System.out.println("Digite seu nome: ");
		String nome = input.next();
		
		System.out.println("Digite sua senha: ");
		String senha = input.next();
		gs.loginUsuario(nome, senha);
	}

	private int getNextId() {
		List<Usuario> usuarios = gs.lerUsuarios();
		int maxId = 0;
		// for => forEacch
		// Único usuário : Lista com todos
		for (Usuario usuario : usuarios) {
			int id = usuario.getId();
			// 1
			if (id > maxId) {
				// Lógica para descobrir o último id
				maxId = id;
				// 10
			}
		}
		return maxId + 1;
	}

	public void deletarUsuario(int id) {
		List<Usuario> usuarios = gs.lerUsuarios();

		if (usuarios.removeIf(usuario -> usuario.getId() == id)) {
			System.out.println("Usuário deletado com sucesso.");
		} else {
			System.out.println("Usuario não encontrada.");
		}
	}

	public void reescreverArquivo(List<Usuario> usuarios) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
			for (Usuario usuario : usuarios) {
				bw.write(usuario.toString());
				bw.newLine();
			}
		} catch (Exception e) {
			System.err.println("Ocorreu um erro ao reescrever o arquivo: " + e.getMessage());
		}

	}

}
