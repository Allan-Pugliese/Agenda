package agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	// Nome do usuário
	private static final String USERNAME = "root";

	// Senha do banco
	private static final String PASSWORD = "";

	// Caminho/Porta banco de dados e nome do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

	// Conexão
	public static Connection createConnectionToMySQL() throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

		return connection;

	}

	public static void main(String[] args) {
		Connection con = null;
		try {
			con = createConnectionToMySQL();

			if (con != null) {
				System.out.println("Conexão obtida com sucesso");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
