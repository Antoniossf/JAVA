import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class LivroDAO {
    private conectaDB conexao;

    public LivroDAO(){
        conexao = new conectaDB();
    }
    public void inserir (Livro livro) {
        //conectaDB conexao = new conectaDB();
        String sql = "INSERT INTO livro(titulo,autor,ano) values(?,?,?)";
        try {
            PreparedStatement pst = conexao.getConexaoDB().prepareStatement(sql);
            pst.setString(1, livro.getTitulo());
            pst.setString(2, livro.getAutor());
            pst.setInt(3, livro.getAno());
            pst.execute();
            System.out.println("insercao ok: "+livro);
        } catch (Exception e) {
            System.out.println("Falha na insercão:"+ e.getMessage());
            
        }
    }
    public LinkedList<Livro>consultarTodos(){
        //conectaDB conexao = new conectaDB();
        String sql = "SELECT * FROM livro";
        LinkedList<Livro> lista = new LinkedList<Livro>();
        try{
            PreparedStatement pst = conexao.getConexaoDB().prepareStatement(sql);
            //executar consultar
            ResultSet resultados = pst.executeQuery();
            while(resultados.next()){
                String titulo = resultados.getString("titulo");
                String autor = resultados.getString("autor");
                int ano = resultados.getInt("ano");
                //cria objeto java
                Livro obj = new Livro(titulo);
                obj.setAno(ano);
                obj.setAutor(autor);
                //adicionar na lista
                lista.add(obj);
            }
            return lista;
        }catch(Exception e){
        System.out.println("Falha na consulta livro: " + e.getMessage());
        }
        return null;
    }
    //IMPLEMENTAR OS METODOS ABAIXO
    public Livro consultar(int id){
        String sql = "SELECT * FROM livro where idLivro= ?";
        // somente vamos criar o objeto se tiver no BC
        Livro livro =null;
        try {
            PreparedStatement pst = conexao.getConexaoDB().prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet resultado = pst.executeQuery();
            if (resultado.next()){ // se achar registro no BD
                // recuperando dados do banco
                String titulo = resultado.getString("titulo"); 
                String autor = resultado.getString("autor");
                int ano = resultado.getInt("ano");
                // int id_bd = resultado.getInt("idLivro");
                // criar o objeto livro e popular
                livro = new Livro(titulo);
                livro.setAno(ano);
                livro.setAutor(autor);
                livro.setId(id);
                return livro;
            }
            else{
                System.out.println("Nao tem registro com id = " + id);
            }
        } catch (Exception e) {
            System.out.println("Falha na consulta: " + e.getMessage());
        } 
        return null;
    }
    
    public void excluir(int id) {
        String sql = "DELETE FROM livro WHERE idLivro = ?";
        try {
            PreparedStatement pst = conexao.getConexaoDB().prepareStatement(sql);
            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Exclusão realizada com sucesso. ID: " + id);
            } else {
                System.out.println("Nenhum registro encontrado com ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Falha na exclusão: " + e.getMessage());
        }
    }

    public void alterar(Livro livro) {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, ano = ? WHERE idLivro = ?";
        try {
            PreparedStatement pst = conexao.getConexaoDB().prepareStatement(sql);
            pst.setString(1, livro.getTitulo());
            pst.setString(2, livro.getAutor());
            pst.setInt(3, livro.getAno());
            pst.setInt(4, livro.getId());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Alteração realizada com sucesso: " + livro);
            } else {
                System.out.println("Nenhum registro encontrado com ID: " + livro.getId());
            }
        } catch (Exception e) {
            System.out.println("Falha na alteração: " + e.getMessage());
        }
    }
}
}

