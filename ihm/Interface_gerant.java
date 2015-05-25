import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class Interface_gerant {
	Connection conn;
	String id;
	String nom;
	Statement st;
	public Interface_gerant(Connexion conn){
		this.conn =  conn.getConnection();
		this.id = conn.getId();
		this.nom = conn.getNom();
	}
	
	
	public void Print(String s){
		
		System.out.println(s);
	}
	public void Menu() throws IOException, SQLException, InterruptedException{
		Scanner sc = new Scanner(System.in);
		System.out.println("=========================================");
		System.out.println("             Interface Gérant             ");
		System.out.println("==========================================");
		System.out.print("Hello ");
		Thread.sleep(500);
		System.out.println("\t \t Mrs "+this.nom+"\n\n\n\n");
		System.out.println("1-  Voir toutes les adresses mail des clients");
		System.out.println("2-  Voir votre profit");
		System.out.println("3-  Voir les profits des développeurs");
		System.out.println("4-  Accorder un bonus exceptionnel au meilleur développeur");
		System.out.println("5-  Voir les statistiques sur les périphériques");
		System.out.println("6-  Voir les meilleures ventes");
		System.out.println("7-  Voir les informations des utilisateurs");
		System.out.println("8-  Voir les informations sur les applications");
		System.out.println("9-  Radier un utilisateur ");
		System.out.println("0-  Déconnexion  ");
		System.out.print("\t\t\t\t  Votre choix:");
		int b = sc.nextInt();
		    while(b != 0){
			switch(b){
			
			case 1:
				Interface.cls();
				try {
				    Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
									ResultSet.CONCUR_READ_ONLY);
				    ResultSet rs = null;
				    rs = st.executeQuery("SELECT nom_users,prenom_users,adresse "+ 
							 "FROM users;");
				    int i=0;
				    Interface.cls();
				    while (rs.next()) {
					Print("Client "+i+"---> Nom : "+rs.getString(1)+" Prenom : "+
		                         rs.getString(2)+" Adresse: "+rs.getString(3));
					i++;
				    }
				    rs.close();
				    st.close();
				} catch(SQLException ex) {
					
				}
				System.out.println("Appuyer sur '0' pour quitter !!");
				b=sc.nextInt();
				Interface.cls();
				Menu();
				break;
				
			case 2 :
				    Interface.cls();	
					this.profit();
					System.out.println("Appuyer sur '0' pour quitter !!");
					b=sc.nextInt();
					Interface.cls();
					Menu();
				    break;
				
			case 5 :
					Interface.cls();
					this.statistic_periph();
					System.out.println("Appuyer sur '0' pour quitter !!");
					b=sc.nextInt();
					Interface.cls();
					Menu();
				    break;
			case 7:
					Interface.cls();
					this.info_users();
					System.out.println("Appuyer sur '0' pour quitter !!");
					b=sc.nextInt();
					Interface.cls();
					Menu();
					break;
				
			case 8:
				
				   Interface.cls();
				   this.inf_app();
				   System.out.println("Appuyer sur '0' pour quitter !!");
				   b=sc.nextInt();
				   Interface.cls();
				   Menu();	
				   break;
				   
			case 9:
					Interface.cls();
					System.out.println(" Entrer l'id de l'utilisateur que vous voulez radier ");
					String ident = sc.next();
					Radier_users(ident);
					System.out.println("Appuyer sur '0' pour quitter !!");
					b=sc.nextInt();
					Interface.cls();
					Menu();	
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
			
	
	public void Radier_users(String id) throws SQLException{
		
		st = conn.createStatement();
		int rs = st.executeUpdate("DELETE FROM info_paiement where id_users = '"+id+"';");
		rs = st.executeUpdate("DELETE FROM installe where id_users = '"+id+"';");
		rs = st.executeUpdate("DELETE FROM users where id_users = '"+id+"';");
	}
	
	public void inf_app() throws SQLException, IOException {
		
		int rep = 0 ;
		Scanner sc = new Scanner (System.in);
		System.out.println("1 - Afficher les applications par catégorie:");
		System.out.println("2 - Par prix ascendant :");
		System.out.println("3 - Par prix descendant :");
		System.out.println("4 - Compatibilité avec les périphériques :");
		rep = sc.nextInt();
		
		switch(rep)
			{
		
		case 1:
				try 
					{
						st = conn.createStatement();
					
						ResultSet rs1 = st.executeQuery("SELECT nom_apps,categorie from apps order by categorie; ");
					
						while(rs1.next()){
							System.out.print(rs1.getString(1));
							System.out.println("  "+rs1.getString(2));
					}
					
					
					
					ResultSet rs = st.executeQuery("SELECT count(*) from apps ;");
			  
					while(rs.next()){
						System.out.println("Il y'a "+rs.getString(1)+" applications  \n \n \n");
					}
				}catch(Exception e){
			
				}
				System.out.println("Appuyer sur '0' pour quitter !!");
				   int b = sc.nextInt();
				   Interface.cls();
				   inf_app();
				break;
				
		case 2:
			try 
			{
				st = conn.createStatement();
		
				ResultSet rs1 = st.executeQuery("SELECT DISTINCT nom_apps, prix FROM apps ORDER BY prix ASC; ");
			
				while(rs1.next()){
					System.out.print(rs1.getString(1));
					System.out.println("  "+rs1.getString(2));
				}
		
		
			}catch(Exception e){

			}
			System.out.println("Appuyer sur '0' pour quitter !!");
			   b = sc.nextInt();
			   Interface.cls();
			   inf_app();
			break;
	
		case 3 :
			try 
			{
				st = conn.createStatement();
		
				ResultSet rs1 = st.executeQuery("SELECT DISTINCT nom_apps, prix FROM apps ORDER BY prix DESC; ");
			
				while(rs1.next()){
					System.out.print(rs1.getString(1));
					System.out.println("  "+rs1.getString(2));
				}
		
		
			}catch(Exception e){

			}
			System.out.println("Appuyer sur '0' pour quitter !!");
			b = sc.nextInt();
			Interface.cls();
			inf_app();
			break;
			
		case 4:
					int a;
					do
						{
							System.out.println("1- Predator ");
							System.out.println("2- Cyborg ");
							System.out.println("3- Bionic ");
				
							a = sc.nextInt();
						
						}while(a != 1 && a!= 2 && a!= 3);	
			    
					if(a == 1)
			    	app_par_os("Predator");
					else if(a == 2)
						app_par_os("Cyborg");
					else
						app_par_os("Bionic");
					System.out.println("Appuyer sur '0' pour quitter !!");
					b = sc.nextInt();
					Interface.cls();
					inf_app();
					break;	
			}
		
			
		
	}
	
	public void app_par_os(String os) throws SQLException{
		
		
		st = conn.createStatement();
		
		ResultSet rs1 = st.executeQuery("SELECT DISTINCT (nom_apps,nom_os,version_os) FROM apps WHERE nom_os ='"+os+"'; ");
	    
		while(rs1.next()){
			System.out.println(rs1.getString(1));
		}
		
		
		
	}
	
	
	  public void statistic_periph(){
		  
		  String type_periph = "" ;
		  Scanner sc = new Scanner(System.in);
		  System.out.print("Entrer le type du périphérique :");// Tboguissa 
		  while(   (type_periph.equals("telephone") == false)     &&      (type_periph.equals("montre") == false)   &&    (type_periph.equals("lunette") == false)    && (type_periph.equals("tablette") == false ) ){
				
				type_periph= sc.next();
			}
		  
		  
		  try{
			  
			  st = conn.createStatement();
			  ResultSet rs = st.executeQuery("SELECT count(*) from peripherique where type_periph='"
					  						+type_periph+"';");
			  while(rs.next()){
				  System.out.println("Il y'a "+rs.getString(1)+" "+type_periph+"s");
			  }
			  
			  
			  
		  
		  }catch(Exception e){
			  
		  }
		  
		  
	  }
		
		public void profit() throws IOException{
			
			try {
			    Statement st = conn.createStatement();
	    ResultSet rs = null;
	    rs = st.executeQuery("SELECT sum(prix)*0.7 "+ 
				 "FROM installe,apps where installe.id_apps = apps.id_apps;");
	    int i=0;
	    Interface.cls();
	    while (rs.next()) {
		Print("Profit ="+rs.getString(1));
		i++;
	    }
	    rs.close();
	    st.close();
	} catch(SQLException ex) {
		
	}	
		}
		
		
		public void info_users(){
			LinkedList<String> l = new LinkedList<String>();
			LinkedList<String> l1 = new LinkedList<String>();
			LinkedList<String> l2 = new LinkedList<String>();
			int rep ;
			Scanner sc = new Scanner(System.in);
			System.out.println("1 - Clients  ");
			System.out.println("2 - Developpeurs ");
			System.out.print("Votre choix :");
			rep = sc.nextInt();
			
		
			switch(rep){
			
				case 1 :
					    System.out.println("Liste des Clients :");
						try
							{
								System.out.println("toto");
								st = conn.createStatement();
								ResultSet rs = st.executeQuery("SELECT nom,prenom,adresse from users "
																+"where raison_sociale = 'C';" );
								
								System.out.println("NOM ----------PRENOM---------ADRESSE ");
								while(rs.next()){
									l.add(rs.getString(1));
									l1.add(rs.getString(2));
									l2.add(rs.getString(3));
									
								}
								for(int i=0;i<l.size();i++)
								{
									System.out.print(l.get(i));
									System.out.print("   "+l1.get(i));
									System.out.println("   "+l2.get(i));
								}
								ResultSet rs1 = st.executeQuery("SELECT count(*) from users where"
																+" raison_sociale='C';");
								System.out.print("Il y a ");
								while(rs1.next()){
									System.out.print(rs1.getString(1));
								}
								System.out.println(" Clients"); 
							}catch(Exception e){
								
							}
						break;
						
				case 2 :System.out.println("Liste des Developpeurs :");
				try
				{
					st = conn.createStatement();
					ResultSet rs = st.executeQuery("SELECT nom,prenom,adresse from users "
													+"where raison_sociale = 'D';" );
					
					System.out.println("NOM ----------PRENOM---------ADRESSE ");
					while(rs.next()){
						l.add(rs.getString(1));
						l1.add(rs.getString(2));
						l2.add(rs.getString(3));
						
					}
					for(int i=0;i<l.size();i++)
					{
						System.out.print(l.get(i));
						System.out.print("   "+l1.get(i));
						System.out.println("   "+l2.get(i));
					}
					ResultSet rs1 = st.executeQuery("SELECT count(*) from users where"
													+" raison_sociale='D';");
					System.out.print("Il y a ");
					while(rs1.next()){
						System.out.print(rs1.getString(1));
					}
					System.out.println(" Developpeurs"); 
				}catch(Exception e){
					
				}
					
						break;
			               
			
			
			
			
			
			
			
			
			}
			
			
			
			
			
			
			
			
			
		}
		
	
	
	public static void main(String[] args) {
		

	}

}
