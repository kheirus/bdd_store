import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


public class Interface_client {
    private Connection conn;
	private String login;
	private String id;
	private String nom;
	Statement st;
	
	
	
	public Interface_client(Connexion b){
		
		this.conn = b.getConnection();
		this.login = b.getLogin();
		this.id = b.getId();
		this.nom = b.getNom();
		}
	

	
	public void Menu() throws IOException, SQLException, InterruptedException{
		boolean flag1 = false;
		String pass;
		Scanner sc = new Scanner(System.in);
		System.out.println("=========================================");
		System.out.println("             Interface Client       ");
		System.out.println("==========================================");
		System.out.print("Hello ");
		Thread.sleep(500);
		System.out.println("\t \t Mr "+this.nom+"\n\n\n\n");
		System.out.println("1-  Ajouter un périphérique");
		System.out.println("2-  Enlever un périphérique");
		System.out.println("3-  Chercher une application"); // par mot-clé
		System.out.println("4-  Afficher une liste d'application recommandées");
		System.out.println("5-  Donner des avis sur les applications installées");
		System.out.println("6-  Accéder à vos informations de paiement");
		System.out.println("7-  Le nombre des points <Méla>");
		System.out.println("8-  Connaitre les applications installées avec leurs droits");// Les droits 0101
		System.out.println("9-  Modifier votre profile");
		System.out.println("10- Voir des commentaires sur des applications :");
		System.out.println("0-  Déconnexion  ");
		System.out.print("\t\t\t\t  Votre choix:");
		int b = sc.nextInt();
		    while(b != 0){
			switch(b){
			
			case 1:
				
					
				this.add_periph();
				System.out.println("Votre périphérique est bien ajouté");
				System.out.print("Appuyer Sur n'importe quelle touche pour revenir ");
				pass = sc.next();
				Interface.cls();
				this.Menu();
				
				break;
				
			case 2 : 
					this.delete_periph();
			        System.out.println("Votre périphérique est bien supprimé  !!");
				    System.out.print("Appuyer sur n'importe quelle touche pour revenir :");
				    pass = sc.next();
				    Interface.cls();
				    this.Menu();
				
				    break;
				    
			case 3 :
				    this.search_apps();
				    System.out.print("Appuyer sur n'importe quelle touche pour revenir :");
				    pass = sc.next();
				    Interface.cls();
				    this.Menu();
				    
			case 4 :
					this.app_recommande();
					System.out.print("Appuyer sur n'importe quelle touche pour revenir :");
				    pass = sc.next();
				    Interface.cls();
				    this.Menu();
				    
			case 5 :
					this.ajouter_avis();
					System.out.print("Appuyer sur n'importe quelle touche pour revenir :");
				    pass = sc.next();
				    Interface.cls();
				    this.Menu();
					
			case 6 : 
				this.information_paiement();
				System.out.print("Appuyer sur n'importe quelle touche pour revenir :");
				pass = sc.next();
				Interface.cls();
				this.Menu();
			
			case 7 :
					this.point_mela();
					System.out.print("Appuyer sur n'importe quelle touche pour revenir :");
					pass = sc.next();
					Interface.cls();
					this.Menu();
					
			case 8:
					this.lister_droit();
					System.out.print("\n \n \n Appuyer sur n'importe quelle touche pour revenir :");
					pass = sc.next();
					Interface.cls();
					this.Menu();
					
				    
			case 9 :
				    System.out.println("1 - Modifier votre mot de passe");
				    System.out.println("2 - Changer votre adresse mail");
				     b= sc.nextInt();
				     switch(b){
				     
				     case 1 :
				    	 	
				    	     while(flag1 == false){
				    	    	 Interface.cls();
				    	    	 System.out.print("Entrer votre ancien password :");
				    	    	  pass = sc.next();
				    	    	 if(verif_password(pass)){
				    	    		 System.out.print("Entrer votre nouveau mot de passe :");
				    	    		 pass = sc.next();
				    	    		 update_password(pass);
				    	    		 flag1 = true;
				    	    	 }
				    	    	 else
				    	    		 System.out.println("Erreur !!  Ce n'est pas votre password courant !!");
				    	     
				    	     }
				    	     System.out.println("Mot de passe modifié avec succés!!");
				    	     System.out.println("Appuyer sur n'importe quelle touche pour quitter");
				    	     String qlq = sc.nextLine();
				    	     Interface.cls();
				    	     Menu();
				    	     break;
				     }
				    break;
				
			}
		    }
		    Interface.cls();
		    char escCode = 0x1B;
			int row = 20; int column = 30;
			System.out.print(String.format("%c[%d;%df",escCode,row,column));
		    System.out.print("  GOLDEN ");
		    Thread.sleep(1000);
		    System.out.print("STORE ");
		    Thread.sleep(1000);
		    System.out.print("VOUS");
		    Thread.sleep(1000);
		    System.out.print(" SOUHAITE");
		    Thread.sleep(1000);
		    row = 22;column = 35;
			System.out.print(String.format("%c[%d;%df",escCode,row,column));
		    System.out.println("            UNE BONNE JOURNEE :) \n\n\n\n\n");
		    Thread.sleep(100);
		
	}
	
	public void app_recommande() throws SQLException{
		
		
		st = conn.createStatement();
		ResultSet rs = st.executeQuery(" SELECT DISTINCT id_apps from installe where id_users='"+this.id+"';");
		LinkedList<String> apps_installe = new LinkedList<String>();
		LinkedList<String> utilisateurs = new LinkedList<String>();
		
		
		while(rs.next()){
			apps_installe.add(rs.getString(1));
		}
		int i=0;
		while(apps_installe.size() > i){
			rs = st.executeQuery(" SELECT id_users from installe where id_apps='"+apps_installe.get(i)+"';");
			while(rs.next()){
				utilisateurs.add(rs.getString(1));
			}
			i++;
		}
			
		 for(i=0 ; i<utilisateurs.size(); i++){
			 
			 rs = st.executeQuery("SELECT nom_apps from apps,installe where apps.id_apps = installe.id_apps and "
					 					+"id_users='"+utilisateurs.get(i)+"' and installe.id_apps NOT IN (SELECT " +
					 							" id_apps from installe where id_users='"+this.id+"');");
			 while(rs.next()){
				 System.out.print(rs.getString(1)+" \t\t");
			 }
			 
			     System.out.println("");
		 }
		
		
		
		
		
		
	}
	public void point_mela(){
		
		
		
		try {
			
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select sum(point_mela) from " +
           " apps , installe where" +
           " id_users = '" +
           this.id+
           "' and apps.id_apps =installe.id_apps "+
         ";");
			
			while(rs.next()){
				System.out.println(" Vos points méla : "+rs.getString(1));
			}
			
			
			
		}catch(Exception e){
			
		}

		
	}
	
	public void add_periph(){
		int id_periph;
		String nom_periph;
		String type_periph;
		String nom_os;
		int id_fab;
		int id_os;
		Scanner sc = new Scanner(System.in);
		System.out.print("Entrer le nom de votre périphérique :");
		nom_periph = sc.next();
		System.out.print("Entrer le type de votre peripherique :");
		type_periph = sc.next();
		System.out.print("Choisir l'id  et le nom de votre OS :");
		this.lister_os();
		System.out.print("ID OS :");
		id_os = sc.nextInt();
		System.out.print("Nom OS :");
		nom_os = sc.next();
		System.out.print("Choisir le fabriquant de votre périphérique :");
		this.lister_fab();
		System.out.print("ID Fabriquant :");
		id_fab = sc.nextInt();
		
		
		try {
		    Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
		    int rs ;
		    rs = st.executeUpdate("INSERT INTO peripherique VALUES(DEFAULT,"+
		    		"'0','"+nom_os+"','0','0','"
		    		+nom_periph+"','"+type_periph+"','"
		    		+id_os+"','"+
		    		id_fab+"','"+
		    		this.id+"');");
		  
		  
		    st.close();
		} catch(SQLException ex) {
			
		}
		
		
		
		
	}
	
	public void delete_periph(){
		Scanner sc = new Scanner(System.in);
		int id ;
		
		System.out.println("Entrez l'id du périphérique que vous voulez supprimer :");
		id = sc.nextInt();
		

		try {
	    Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
int rs;
rs = st.executeUpdate("DELETE FROM peripherique where id_periph='"+id+"';"); 


st.close();
} catch(SQLException ex) {

}
	}
		
		public void search_apps() throws SQLException{
			Scanner sc = new Scanner(System.in);
			String mot ="";
			System.out.println("Entrer un mot cle pour cherche une application :");
			mot = sc.next();
			
			this.st = conn.createStatement(); 
			ResultSet rs = null ;
			
			rs = st.executeQuery("Select * from apps where mot_cle like '%"+mot+"%';");
			
			System.out.println(" id_apps | nom_apps ");
			
			while(rs.next()){
				System.out.printf(rs.getString(1)+"\t"+rs.getString(2)+"\n");	
			}
			rs.close();
			st.close();
		}
		
		public void ajouter_avis(){
			
			int elstar;
			String coms = "";
			String rep="";
			Scanner sc = new Scanner(System.in);
			int id_app;
			
			System.out.print(" Entrer l'Id de votre application ");
			id_app = sc.nextInt();
			System.out.print("Entrer votre elstar [ Votre entrée doit être entre {0 et 5 } ]");
			elstar = sc.nextInt();
			System.out.println("Vous voulez entrer un commentaire ?? ");
			while(   (rep.equals("O") == false)     &&      (rep.equals("o") == false)   &&    (rep.equals("N") == false)    && (rep.equals("n") == false ) ){
				System.out.print("(O/n) :");
				rep = sc.next();
			}
			if(rep.equals("O") || rep.equals("o")){
				
				System.out.print("Entrer votre commentaire :");
				
				coms = sc.next();
			}
			
			try
				{
				st = conn.createStatement();
				int rs = st.executeUpdate("INSERT INTO installe VALUES('"+id_app+"','"
				+this.id+"','"
				+coms+"','"
				+elstar+"');");
				
				st.close();
				}catch(Exception e){
					
				}
			    
			
			
			
			
			
			
		}
	
		
		public void afficher_coms(){
			
			
			
			
			System.out.println("Entrer l'id de l'application");
			
			
			
			
			
			
			
			
			
			
			
		}
	public boolean verif_password(String pass){
		String goodpass = "";
		boolean flag = false;
		
		try {
		    Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
		    ResultSet rs = null;
		    rs = st.executeQuery("SELECT mdp "+ 
					 "FROM users WHERE id_login='" + login + "';");
		    while (rs.next()) {
			goodpass = rs.getString(1);
			
			if (pass.equals(goodpass))
				flag = true;
		    }
		    rs.close();
		    st.close();
		} catch(SQLException ex) {
		    System.out.println("Erreur " + ex.getMessage());
		}
			
		return flag;
	}
	
	
	public void update_password(String new_pass){
		
		try {
		    Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
		    int rs;
		    rs = st.executeUpdate("UPDATE users set mdp='"+new_pass+
		    		"' where id_login='"+login+"';");
		   
		    st.close();
		} catch(SQLException ex) {
		    System.out.println("Erreur " + ex.getMessage());
		}
		
	}
	public void information_paiement(){
		try {
		    st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					      ResultSet.CONCUR_READ_ONLY);
		    String request = 
			"SELECT * "+ 
			"FROM info_paiement "+
			"WHERE id_users='"+this.id+"';";
		    ResultSet rs = st.executeQuery(request);
		    System.out.println("id_info | id_users |  type  |   numero_carte   | date_expiration ");
		    while(rs.next()){
		    	System.out.print(rs.getString(1));
		    	System.out.print("\t\t"+rs.getString(2));
		    	System.out.print("\t"+rs.getString(3));
		    	System.out.print("\t"+rs.getString(4));
		    	System.out.println("\t\t"+rs.getString(5));
		    }
		} catch(SQLException ex) {
		    System.out.println("Erreur " + ex.getMessage());
		}
			
	}
	
	
	public void lister_droit(){
		Scanner sc = new Scanner(System.in);
		int id;
		String result ="";
		System.out.println("Entrer l'id de votre application");
		id = sc.nextInt();
		try{
			
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT droits from apps,installe where"+ 
			" installe.id_apps='"+id+"' and installe.id_apps= apps.id_apps and id_users='"+this.id+"';" );
		
			
			while(rs.next()){
				result = Integer.toBinaryString(Integer.parseInt(rs.getString(1)));
			}
		     rs.close();
		     st.close();    
		}catch(Exception e){
			
		}
	
		if(result.length() == 2)
			result = "00" + result ;
		else if(result.length() == 3)
			result = "0" + result ;
		else if(result.length() == 1)
			result = "000" + result ;
		System.out.println("  DROITS   : "+result);
		
		}
	
	
	
	
	public int max_id_periph(){
		 int max = 0;
		try {
		    Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
		    ResultSet rs = null;
		    rs = st.executeQuery("SELECT max(id_periph) FROM peripherique;");
		    System.out.println(" ID_OS");
		    while (rs.next()) {
			max = rs.getInt(1); 
		    }
		    rs.close();
		    st.close();
		} catch(SQLException ex) {
		    System.out.println("Erreur " + ex.getMessage());
		}

	return max;
	}
	
public void lister_os(){
		
		
		
		try {
		    Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
		    ResultSet rs = null;
		    rs = st.executeQuery("SELECT DISTINCT id_os FROM os;");
		    System.out.println(" ID_OS");
		    while (rs.next()) {
			System.out.println(rs.getString(1)); 
		    }
		    rs.close();
		    st.close();
		} catch(SQLException ex) {
		    System.out.println("Erreur " + ex.getMessage());
		}

	}

public void lister_fab(){
	
	
	
	try {
	    Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
	    ResultSet rs = null;
	    rs = st.executeQuery("SELECT DISTINCT id_fabriquant FROM fabriquant;");
	    System.out.println(" ID_Fab");
	    while (rs.next()) {
		System.out.println(rs.getString(1));
	    }
	    rs.close();
	    st.close();
	} catch(SQLException ex) {
	    System.out.println("Erreur " + ex.getMessage());
	}

	
	
	
}

	public static void main(String[] args) {
		

	}

}
