import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Interface_develop {
   Connection conn;
   String id;
   Statement st;
   String nom ;
	public Interface_develop(Connexion conn){
		this.conn = conn.getConnection();
		this.id = conn.getId();
		this.nom = conn.getNom();
	}
	
	public void Menu() throws IOException, SQLException, InterruptedException{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("=========================================");
		System.out.println("             Interface Développeur       ");
		System.out.println("==========================================");
		System.out.print("Hello ");
		Thread.sleep(500);
		System.out.println("\t \t Mr "+this.nom+"\n\n\n\n");
		System.out.println("1-  Ajouter une nouvelle application");
		System.out.println("2-  Mettre à jour une de vos applications");
		System.out.println("3-  Connaitre les avis sur vos applications");
		System.out.println("4-  Voir les statistiques sur vos applications");
		System.out.println("5-  Le nombre des applications installées par type de périphériques");
		System.out.println("6-  Les applications qui se vendent le mieux");
		System.out.println("7-  Les notes moyennes");
		System.out.println("8-  Votre profit");
		System.out.println("0-  Déconnexion  ");
		System.out.print("\t\t\t\t  Votre choix:");
		int b = sc.nextInt();
		    while(b != 0){
			switch(b){
			
			case 1:
					Interface.cls();
					this.add_app();
					System.out.println("Appuyer sur '0' pour quitter !!");
					b=sc.nextInt();
					Interface.cls();
					Menu();
					break;
				
			case 2 :	
							Interface.cls();
							this.maj();
							System.out.println("Appuyer sur '0' pour quitter !!");
							b=sc.nextInt();
							break;
							
			case 5:
							Interface.cls();
							this.apps_par_periph();
							System.out.println("Appuyer sur '0' pour quitter !!");
							b=sc.nextInt();
							break;
							
			case 8 :
							Interface.cls();
							this.profit();
							System.out.println("Appuyer sur '0' pour quitter !!");
							b=sc.nextInt();
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
			
		

	public void profit() throws SQLException{
		
		
	 
		
		 
		 ResultSet rs4 = null ;
		 st = conn.createStatement();
		 rs4 = st.executeQuery("SELECT sum(prix)*0.7 FROM apps,installe where apps.id_apps = installe.id_apps AND installe.id_users ='"
											+this.id+"';");
		
		 while(rs4.next()){
			System.out.print("Votre profit est : "+rs4.getString(1)+" $");
		}
			
		
	}
	
		public void maj(){
		 
		 int id=1;
		 Scanner sc = new Scanner(System.in);
		 System.out.print("Entrer l'ID de votre application :");
		 id = sc.nextInt();
		 
		 try {
				Statement st = conn.createStatement();
				int rs = st.executeUpdate("INSERT INTO maj VALUES(DEFAULT,current_date,'"+id+"','"+this.id+"');");
				
				st.close();
			} catch(SQLException ex) {
				
			}
			
			
		 
		 
		
		
		
	}
	
		
	public void apps_par_periph() throws SQLException{
		
		String periph ="";
		Scanner sc = new Scanner(System.in);
		System.out.println(" Entre un type de périphérique :");
	     while(periph.equals("tablette") != true && periph.equals("montre") != true && periph.equals("telephone") != true && periph.equals("lunette") != true){
		    	System.out.print("[tablette]  OR [montre] OR [telephone] OR [lunette] :");
		    	periph = sc.next();
		    }
		  
	     ResultSet rs1 = st.executeQuery("SELECT count(*) from apps where"
	    		 			+" type_periph='"+periph+"';");
	     System.out.print("Il y a ");
	     while(rs1.next()){
	    	 System.out.print(rs1.getString(1));
	     }
	     st.cancel();
	     rs1.close();
	     System.out.println(periph+"s");
		 
		
		
	}
	public void add_app() throws IOException{
		String nom_app,mot_cle,categorie,nom_os="";
		int version_app, release_app,droits,payante=2,mensuel=2,version_os,release_os,points_mela;
		float prix;
		Scanner sc = new Scanner(System.in);
		System.out.print("Entrer le nom de votre application :");
		nom_app = sc.nextLine();
		System.out.print("Entrer le prix de votre application :");
		prix = sc.nextFloat();
		System.out.print("Entrer la version de l'application:");
		version_app = sc.nextInt();
		System.out.print("Entrer le ' Release_app ' :");
		release_app = sc.nextInt();
		System.out.print("Entrer sa catégorie: ");
	    categorie = sc.next();
	    System.out.print("Entrer ses droits :");
	    droits = sc.nextInt();
	    System.out.println("Votre application est payante  ?!");
	    while(payante != 1 && payante !=0){
	    	System.out.print("[1/0]:");
	    	payante = sc.nextInt();
	    }
	     if( payante == 1){
	    	 System.out.println("Paiement mensuel ??");
	    	 while(mensuel != 1 && mensuel !=0){
	    		 System.out.print("[1/0]:");
	    		 mensuel = sc.nextInt();
	    	 }
	    	 
	     }
	     else
    		 mensuel = 0;
	     
	     System.out.println(" Entre le nom de votre OS :");
	     while(nom_os.equals("Cyborg") != true && nom_os.equals("Predator") != true && nom_os.equals("Bionic") != true){
		    	System.out.print("[Cyborg]  OR [Predator] OR [Bionic] :");
		    	nom_os = sc.next();
		    }
	     System.out.print("Entrer la version de l'OS :");
	     version_os = sc.nextInt();
	     System.out.print("Entre le ' Release OS ' : ");
	     release_os = sc.nextInt();
	     System.out.print(" Entrer les points mela de votre application :");
	     points_mela = sc.nextInt();
	     System.out.print("Entrer les mots clé de votre application :");
	     mot_cle = sc.next();
	     
	    try {
			Statement st = conn.createStatement();
			int rs = st.executeUpdate("INSERT INTO apps VALUES(DEFAULT,'"+nom_app+"','"+prix+"','"+payante+"','"+mensuel+"','"
										+droits+"','"+release_app+"','"+version_app+"','"+nom_os+"','"+release_os+"','"+version_os+
										"','"+this.id+"','"+categorie+"','"+mot_cle+"','"+points_mela+"');");
			
			st.close();
		} catch(SQLException ex) {
		
		}
	    
	}
	
	public static void main(String[] args) {
		

	}

}
