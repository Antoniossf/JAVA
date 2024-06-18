import java.sql.Connection;
import java.sql.DriverManager;

public class conectaDB {
    //atributo
    private Connection conexao;
    // contrutor
    public conectaDB () { 
        // sao  as credenciais da sua conexao com bd
        String url="jdbc:mariadb://localhost:3306/biblioteca";
        String user= "root";
        String pwd=  "root";
        try{
            conexao = DriverManager.getConnection(url, user, pwd);
            System.out.println("conexão realizada");
        } catch (Exception e) {
            System.out.println("Falha na conexão");
            System.out.println(e.getMessage());
        }
    }
    
  public Connection getConexaoDB(){
        return conexao;
  }    
}
