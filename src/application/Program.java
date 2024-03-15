package application;

import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import agenda.dao.ContatoDAO;
import agenda.model.Contato;

public class Program {

	public static void main(String[] args) throws Exception {

		Locale.setDefault(Locale.US);
		char optionExit;
		char optionMenu;
		char optionDelete = ' ';

		try (Scanner teclado = new Scanner(System.in)) {
			do {
				String name;
				int age;
				String telephoneNumber;
				int id;

				System.out.println("Agenda de contatos");
				System.out.println();

				System.out.println("Qual função deseja executar: ");
				System.out.println("Criar contato: C");
				System.out.println("Atualizar contato: A");
				System.out.println("Deletar contato: D");

				System.out.print("Digite aqui: ");
				optionMenu = teclado.next().charAt(0);

				if (optionMenu == 'c' || optionMenu == 'C') {

					// Criar novo contato
					System.out.println("Adicionar contato");
					Contato contato = new Contato();
					ContatoDAO contatoDao = new ContatoDAO();

					System.out.print("Nome: ");
					name = teclado.next();
					contato.setName(name);
					System.out.println();

					System.out.print("Idade: ");
					age = teclado.nextInt();
					contato.setAge(age);
					System.out.println();

					System.out.print("Telefone: ");
					telephoneNumber = teclado.next();
					contato.setTelephoneNumber(telephoneNumber);
					System.out.println();

					contato.setRegisterDate(new Date());

					contatoDao.save(contato);

				}
				if (optionMenu == 'a' || optionMenu == 'A') {

					// Atualizar contato
					System.out.println("Atualizar contato");

					// numero localizado no banco de dados
					System.out.println("Id do contato para atualizar: ");
					id = teclado.nextInt();

					ContatoDAO contatoDao = new ContatoDAO();
					Contato contatoUpdate = contatoDao.findById(id);

					if (contatoUpdate != null) {

						System.out.print("Novo nome: ");
						name = teclado.next();
						contatoUpdate.setName(name);
						System.out.println();

						System.out.print("Nova Idade: ");
						age = teclado.nextInt();
						contatoUpdate.setAge(age);
						System.out.println();

						System.out.print("Novo telefone: ");
						telephoneNumber = teclado.next();
						System.out.println();

						contatoUpdate.setRegisterDate(new Date());

						contatoDao.update(contatoUpdate);

						System.out.println("Contato atualizado com sucesso");

					} else {
						System.out.println("Id não encontrado");
					}

				}

				ContatoDAO contatoDao = new ContatoDAO();

				do {
					if (optionMenu == 'D' || optionMenu == 'd') {

						// deletar contato
						System.out.println("Excluir contato");

						System.out.print("Digite o Id do contato para excluir: ");
						id = teclado.nextInt();

						System.out.println("Confirme o id: ");
						int verificationId = teclado.nextInt();

						if (verificationId == id) {

							contatoDao.deleteById(id);

						} else {
							System.out.println("Erro ao confirmar Id");
							System.out.println("Deseja tentar excluir novamente ?S/N");
							optionDelete = teclado.next().charAt(0);

						}

					}
				} while (optionDelete == 's' || optionDelete == 'S');

				System.out.println("Deseja fazer outra operação? S/N");
				optionExit = teclado.next().charAt(0);
			} while (optionExit == 's' || optionExit == 'S');

		}

		// visualização dos registros de todos os dados
		ContatoDAO contatoDao = new ContatoDAO();

		for (Contato c : contatoDao.listContatos()) {
			System.out.println("Contato: " + c.getName());

		}

	}
}
