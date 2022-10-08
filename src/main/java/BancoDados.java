import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BancoDados {
	private String db_url;
	private String db_user;
	private String db_password;

	public BancoDados(String db_url, String db_user, String db_password) {
		this.db_url = db_url;
		this.db_user = db_user;
		this.db_password = db_password;
	}

	public void consultar(String db_query) {
		try (Connection c = DriverManager.getConnection(db_url, db_user, db_password);
				Statement statement = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet resultSet = statement.executeQuery(db_query)) {
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			System.out.println("Conectado ao MySql");

			System.out.printf("Resultado da pesquisa:%n%n");

			// exibe os nomes de coluna no ResultSet
			for (int i = 1; i <= numberOfColumns; i++)
				System.out.printf("%-25s\t", metaData.getColumnName(i));
			System.out.println();

			while (resultSet.next()) {
				for (int i = 1; i <= numberOfColumns; i++)
					System.out.printf("%-25s\t", resultSet.getObject(i));
				System.out.println();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Falha ao conectar ao MySql");
		}
	}

	public ArrayList<Pessoa> consultarArray(String db_query) {
		ArrayList<Pessoa> p = new ArrayList<Pessoa>();
		try (Connection c = DriverManager.getConnection(db_url, db_user, db_password);
				Statement statement = c.createStatement();
				ResultSet resultSet = statement.executeQuery(db_query)) {
			System.out.println("Conectado ao MySql");

			var pAux = new Pessoa();

			while (resultSet.next()) {
				pAux = new Pessoa(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"),
						resultSet.getString("cargo"));
				p.add(pAux);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Falha ao conectar ao MySql");
		}
		return p;
	}

	public int inserirAlterarExcluir(String db_query) {
		int linhas = 0;
		try (Connection c = DriverManager.getConnection(db_url, db_user, db_password);) {
			System.out.println("Conectado ao MySql");
			Statement statement = c.createStatement();
			linhas = statement.executeUpdate(db_query);
			System.out.println("Operacao Efetudada com Sucesso");
			return linhas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Opera��o Nao Efetuada");
		}
		return linhas;
	}
}
