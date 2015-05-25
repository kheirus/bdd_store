import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Connexion {
	static LinkedList<String> l = new LinkedList<String>();
	static Connection conn = null;
	String login;
	String nom;
	String id;
	public Connexion(){
		
		login="";
			try {
				
				Class.forName("org.postgresql.Driver");
				String URL = "jdbc:postgresql://localhost:5432/prjbd6";
				conn = DriverManager.getConnection(URL, "laagad", "prjbd6");
			
			// Test de connection
			
				if(conn != null)
					System.out.println("Connexion établie !! ");
				else
					System.out.println("Connexion refusée !! ");
			}catch(Exception e)
			{
				e.printStackTrace();
				System.err.println("Message:"+e.getMessage() );
				System.exit(1);
			
			}
		
	}

	
	public Connection getConnection(){
		return this.conn;
	}
	public String getLogin(){
		return this.login;
	}
	public String getId(){
		return this.id ;
	}
	public String getNom(){
		return this.nom;
	}
	
	
	
	

	
	
	
	
	
	
	
    public String connexionValide(String id_login, String password){
	String typePersonne = "";
	String goodpassword ="";
	try {
	    Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
	    ResultSet rs = null;
	    rs = st.executeQuery("SELECT mdp,raison_sociale,id_users,nom "+ 
				 "FROM users WHERE id_login='"+ id_login+"';");
	    while (rs.next()) {
	    	goodpassword = rs.getString(1);
	    
	    if (password.equals(goodpassword) == true){
			this.login = id_login;
			typePersonne = rs.getString(2);
			this.id = rs.getString(3);
			this.nom = rs.getString(4);
	    }
	    
	    
		rs.close();
	    st.close();
	    }
	} catch(SQLException ex) {
	    System.out.println("Erreur " + ex.getMessage());
	}
	
	
	
	return typePersonne;
	
	
	
    }
	
    
    
    
	public static void main(String[] args) throws SQLException {
		
	
	

	}

	

}
