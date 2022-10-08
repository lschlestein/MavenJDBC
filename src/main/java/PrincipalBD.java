
import java.sql.SQLException;
import java.util.ArrayList;

public class PrincipalBD {

	public static void main(String[] args) throws SQLException {
		// Endereço para conex�o ao BD
		final String dbUrl = "jdbc:mysql://localhost:3306/reuniao";
		final String dbUser = "root";
		final String dbPassword = "";

		// Criação da Classe BancoDados
		BancoDados bd = new BancoDados(dbUrl, dbUser, dbPassword);
		
		//bd.inserirAlterarExcluir("INSERT INTO pessoa (nome, email, cargo) VALUES ('Lucas Alberto', 'lucas@outlook.com', 'Professor');");
		//bd.inserirAlterarExcluir("UPDATE pessoa SET nome='John O´Connor', email='oconnor@gmail.com', cargo='Aluno' WHERE nome='Lucas Alberto';");
		//bd.inserirAlterarExcluir("DELETE FROM pessoa WHERE nome='John O´Connor';");
		bd.consultar("select * from pessoa");
		
		//ArrayList<Pessoa> pessoas = bd.consultarArray("select * from pessoa");
		
		//System.out.println("Foram localizadas "+pessoas.size()+" pessoas");
		//for(int i=0; i<pessoas.size();i++) {
		//	System.out.println(pessoas.get(i));
			
		//}

	}

}
