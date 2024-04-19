package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Models.Usuario;

public class GerenciadorDeUsers {
	private static final String NOME_ARQUIVO = "usuarios.txt";

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

	public void adicionarUsuario(Usuario usuario) {
		// Writer => Escrever
		// BuffereWriter, FileWriter
		// BufferedWriter, proporciona uma eficiente escrita
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
			bw.write(usuario.toString()); // 1;arthur;12345
			bw.newLine(); // nova linha no arquivo txt
			System.out.println("Usuário adicionado com sucesso!");
		} catch (Exception e) {
			System.out.println("Ocorreu um ero ao escrever o usuário no arquivo " + e.getMessage());
		}
	}

	public List<Usuario> lerUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		// Buffed, File, Reaer
		try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
			String linha; // linha => 1;noe;senha

			while ((linha = br.readLine()) != null) {
				String[] partes = linha.split(";");
				usuarios.add(new Usuario(Integer.parseInt(partes[0]), partes[1], partes[2]));
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao ler o arquivo " + e.getMessage());
		}
		return usuarios;
	}

	public void deletarUsuario(int id) {
		List<Usuario> usuarios = lerUsuarios();
		// array function => o usuario vai passar de linha em linha
		if (usuarios.removeIf(usuario -> usuario.getId() == id)) {
			reescreverArquivo(usuarios);
			System.out.println("Usuário deletado com sucesso");
		} else {
			System.out.println("Usuário não encontrado");
		}
	}

	public void editarUsuario(int id, String novoNome, String novaSenha) {
		List<Usuario> usuarios = lerUsuarios();
		boolean find = false;

		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				usuario.setNome(novoNome);
				usuario.setSenha(novaSenha);
				find = true;
				break;
			}
		}

		if (find) {
			reescreverArquivo(usuarios);
			System.out.println("Usuário editado com sucesso!");
		} else {
			System.err.println("Usuário não encontrado! :(");
		}

	}
	
	public void editarSenha(int id, String novaSenha) {
		List<Usuario> usuarios = lerUsuarios();
		boolean find = false;
		
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				System.out.println("Usuário: " + usuario.getNome());
				
				usuario.setSenha(novaSenha);
				find = true;
				break;
			}
		}
		
		if (find) {
			reescreverArquivo(usuarios);
			System.out.println("Usuário editado com sucesso!");
		} else {
			System.err.println("Usuário não encontrado! :(");
		}
		
	}

	public void reescreverArquivo(List<Usuario> usuarios) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
			for (Usuario usuario : usuarios) {
				bw.write(usuario.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao reescrever o arquivo! " + e.getMessage());
		}
	}

	public void listarUsuarios() {
		List<Usuario> usuarios = lerUsuarios();
		// nenhum usuario
		if (usuarios.isEmpty()) {
			System.out.println("Nenhum usuário cadastrado...");
		} else {
			System.out.println("Lista de usuarios");
			for (Usuario usuario : usuarios) {
				System.out.println("ID: " + usuario.getId() + ", Nome: " + "" + usuario.getNome() + ", Senha: "
						+ usuario.getSenha());
			}
		}
	}

	public void detalharUsuario(int id) {
		List<Usuario> usuarios = lerUsuarios();
		boolean find = false;

		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				System.out.println(usuario.getNome());
				System.out.println(usuario.getSenha());
				find = true;
				break;
			}
		}

		if (!find) {
			System.err.println("Usuário não encontrado! :(");
		}

	}

	public void loginUsuario(String nomeValue, String senhaValue) {
		List<Usuario> usuarios = lerUsuarios();

		boolean validador = false;
		for (Usuario usuario : usuarios) {

			if (usuario.getNome().equalsIgnoreCase(nomeValue) && usuario.getSenha().equalsIgnoreCase(senhaValue)) {
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
