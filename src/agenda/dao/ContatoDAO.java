package agenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import agenda.factory.ConnectionFactory;
import agenda.model.Contato;

public class ContatoDAO {

	/*
	 * Create - ok 
	 * Read - ok 
	 * Update - ok 
	 * Delete - ok
	 */

	public void save(Contato contato) {

		String sql = "INSERT INTO contatos(name, age,telephoneNumber, registerDate) VALUES(?, ?, ?, ?)";

		Connection con = null;

		PreparedStatement pstm = null;

		try {
			// Criar conexão

			con = ConnectionFactory.createConnectionToMySQL();

			// Criando preparedStatement para executar query

			pstm = con.prepareStatement(sql);

			// adicionar valores esperados pela query
			pstm.setString(1, contato.getName());
			pstm.setInt(2, contato.getAge());
			pstm.setString(3, contato.getTelephoneNumber());
			pstm.setTimestamp(4, new java.sql.Timestamp(contato.getRegisterDate().getTime()));

			// executar query

			pstm.execute();
			System.out.println("Salvo com sucesso");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fechar conexões
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void update(Contato contato) {

		String sql = "UPDATE contatos SET name = ?, age = ?, telephoneNumber = ?, registerDate = ? " + "WHERE id = ?";

		Connection con = null;
		PreparedStatement pstm = null;

		try {
			// criar conexão com banco
			con = ConnectionFactory.createConnectionToMySQL();

			// criar classe pra executar query
			pstm = con.prepareStatement(sql);

			// adicionar valores para atualizar
			pstm.setString(1, contato.getName());
			pstm.setInt(2, contato.getAge());
			pstm.setString(3, contato.getTelephoneNumber());
			pstm.setTimestamp(4, new java.sql.Timestamp(contato.getRegisterDate().getTime()));

			// vendo o id
			pstm.setInt(5, contato.getId());

			// executa a query
			pstm.execute();
			System.out.println("Atualização completa");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteById(int id) {

		String sql = "DELETE FROM contatos WHERE id= ?";

		Connection con = null;
		PreparedStatement pstm = null;

		try {
			// criar conexão com banco
			con = ConnectionFactory.createConnectionToMySQL();

			// criar classe pra executar query
			pstm = con.prepareStatement(sql);

			// vendo o id
			pstm.setInt(1, id);

			// executa a query
			pstm.execute();
			System.out.println("Contato deletado");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<Contato> listContatos() {

		String sql = "SELECT * FROM contatos";

		List<Contato> contatos = new ArrayList<Contato>();

		Connection con = null;
		PreparedStatement pstm = null;

		// Classe que vai recuperar os dados

		ResultSet rset = null;

		try {
			con = ConnectionFactory.createConnectionToMySQL();

			pstm = con.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Contato contato = new Contato();

				// recuperar id
				contato.setId(rset.getInt("id"));

				// recuperar nome
				contato.setName(rset.getString("name"));

				// recuperar idade
				contato.setAge(rset.getInt("age"));

				// recuperar numero
				contato.setTelephoneNumber(rset.getString("telephoneNumber"));

				// recuperar data de registro
				contato.setRegisterDate(rset.getDate("registerDate"));

				contatos.add(contato);

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatos;

	}

	public Contato findById(int id) {

		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		Contato contato = null;

		try {
			con = ConnectionFactory.createConnectionToMySQL();
			String sql = "SELECT * FROM contatos WHERE id = ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rset = pstm.executeQuery();

			if (rset.next()) {
				contato = new Contato();
				contato.setId(rset.getInt("id"));
				contato.setName(rset.getString("name"));
				contato.setAge(rset.getInt("age"));
				contato.setTelephoneNumber(rset.getString("telephoneNumber"));
				contato.setRegisterDate(rset.getDate("registerDate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return contato;
	}
}