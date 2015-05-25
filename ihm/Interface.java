import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Interface {

Connexion conn;
Scanner sc = new Scanner(System.in);
Scanner sb = new Scanner(System.in);
String login ;
String mdp;
String type_personne;

	public Interface(){
		conn = new Connexion();
	}
		
	public String Authentification(){
		
		System.out.print("################    GOLDEN STORE   ################"
				+"\n\n Entrer votre login :");
		login=sc.nextLine();
		System.out.print("\n Entrer votre mot de passe :");
		mdp=sc.nextLine();
		System.out.println(mdp);
		type_personne=conn.connexionValide(login, mdp);
		
		return type_personne;
	}
	
	static void cls() throws IOException{
  	
		for(int i=0; i<50; i++)
    	    System.out.println();
		/*char escCode = 0x1B;
		int row = 10; int column = 0;
		System.out.print(String.format("%c[%d;%df",escCode,row,column));
		*/
  	}
	
	
	public static void positionnement(int a, int b){
		
		char escCode = 0x1B;
		System.out.print(String.format("%c[%d;%df",escCode,a,b));
		
	}
	
	public static void main(String[] args) throws IOException, SQLException, InterruptedException {
		 
         Interface c = new Interface();
         
         
         
         cls();
         String type;
         do{
         type = c.Authentification();
         cls();
         System.out.println(" \t \t E R R E U R   D' A U T H E N T I F I C A T I O N          ! ! !  \n\n\n");
         }while(type.equals(""));
         cls();
         if(type.equals("G") == true  ){
        	 Interface_gerant gerant = new Interface_gerant(c.conn);
        	 gerant.Menu();
         }
         	
         else if(type.equals("D") == true){
        	 Interface_develop develop = new Interface_develop(c.conn);
        	 develop.Menu();
         }
         else if(type.equals("C")){
        	 Interface_client client = new Interface_client(c.conn);
        	 client.Menu();
         }
        	 
         else
        	 System.out.println("Erreur");
	
	
	}

}
