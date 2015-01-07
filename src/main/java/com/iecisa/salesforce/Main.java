package com.iecisa.salesforce;

import java.util.Scanner;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class Main {
  
	    //database URL
		static final String DATABASE_URL = "jdbc:mysql://localhost/salesforce";
		//user
		static final String USER = "root";
		//password
		static final String PASS = "123456";
		
static final String USERNAME = "frblanco@iecisa.com.mx";
static final String PASSWORD = "26Logan$onSaUNxRg6bkt1tGXdSDpAp7houJ4";
  static EnterpriseConnection connection;


  public static void main(String[] args) {

    ConnectorConfig config = new ConnectorConfig();
    config.setUsername(USERNAME);
    config.setPassword(PASSWORD);
    //config.setTraceMessage(true);
    
    try {
      
      connection = Connector.newConnection(config);
      
 
      
      int opcion = 0;

      while(opcion != 7){
    	  
     System.out.println("1 Crear un contacto\n"
      					+ "2 Crear una cuenta\n"
      					+ "3 Ver todos los contactos\n"
      					+ "4 Ver todas las cuentas\n"
      					+ "5 Actualizar un contacto\n"
      					+ "6 Eliminar un contacto\n"
      					+ "7 Salir\n"
      					+ "Seleccione una opción: ");
      
      Scanner menu = new Scanner(System.in);
      opcion = menu.nextInt();
      
      switch (opcion) {
	case 1: createContacts();
	break;
	case 2:  createAccount();
	break;
	case 3:  queryContacts();
	break;
	case 4:  queryAccount();
	break;
	case 5: updateContact();
	break;
	case 6: deleteContact();
	break;
	default:
		break;
	}
      }
        
    } catch (ConnectionException e1) {
        e1.printStackTrace();
    }  

  }
  

  private static void queryAccount() {
	try {
		 QueryResult queryResults = connection.query("SELECT Name, AccountNumber, Website, Phone"
			 		+ " FROM Account ORDER BY CreatedDate DESC");
		     if (queryResults.getSize() > 0) {
		       for (int i=0;i<queryResults.getRecords().length;i++) {
		         // cast the SObject to a strongly-typed Contact
		        Account cu = (Account)queryResults.getRecords()[i];
		         System.out.println(" - Nombre: "+cu.getName()
		        		 +" "+ "- Número de Cuenta: " +  cu.getAccountNumber()
		        		 +" "+ "- Página web: " + cu.getWebsite()
		        		 +" "+ "Número Telefónico: " + cu.getPhone());
		       }
		     }
	} catch (Exception e) {
		
	}
	
}


private static void createContacts() {
    System.out.println("Capturar Nuevo Contacto");
    try {
    	Scanner entrada = new Scanner(System.in);
      	Contact contacto = new Contact();
      	System.out.print("Ingrese Nombre: ");
      	contacto.setFirstName(entrada.nextLine());
      	System.out.print("Ingrese Apellidos: ");
      	contacto.setLastName(entrada.nextLine());
      	System.out.print("Ingrese número telefónico: ");
      	contacto.setPhone(entrada.nextLine());
      	System.out.print("Ingrese Email: ");
      	contacto.setEmail(entrada.nextLine());
      	
      	connection.create(new SObject[] {contacto} );
    	
	} catch (Exception e) {
		
	}

}


private static void updateContact() {
    
    try {
      
      Scanner entrada = new Scanner(System.in);
//      Contact updateContact = new Contact();
      System.out.println("Dame un email de contacto para modificar");
//      QueryResult queryResults = connection.query("SELECT * FROM Contact  ");
      
      QueryResult queryResults = connection.query("SELECT FirstName, LastName, Phone,Email, Id"
        		+ " FROM Contact WHERE Email =  " + "'" + entrada.nextLine() + "'"  );
      if (queryResults.getSize() > 0) {
//        for (int i=0;i<queryResults.getRecords().length;i++) {
          // cast the SObject to a strongly-typed Contact
    	  Contact c = (Contact)queryResults.getRecords()[0];
    	  System.out.println("Se modificara este contacto: ");
    	 
    	  System.out.println(" - Nombre: "+c.getFirstName()+" "+ c.getLastName() 
     		  	 + " " + "- Número Telefonico: "+ c.getPhone()
     		  	 + " " + "- Email: " + " "+ c.getEmail()
     		  	 + " " + "- Id: " + " " + c.getId());
    	    	  
         
            System.out.print("Ingrese Nombre: ");
        	c.setFirstName(entrada.nextLine());
        	System.out.print("Ingrese Apellidos: ");
        	c.setLastName(entrada.nextLine());
        	System.out.print("Ingrese número telefónico: ");
        	c.setPhone(entrada.nextLine());
          
        	connection.update(new SObject[] {c} );
        	
          System.out.println("El contacto Modificado: ");
          System.out.println(" - Nombre: "+c.getFirstName()+" "+ c.getLastName() 
        		  	 + " " + "- Número Telefonico: "+ c.getPhone()
        		  	 + " " + "- Email: " + " "+ c.getEmail() );
      }else{System.out.println("Registro no encontrado");}
      
    
    } catch (Exception e) {
    	System.out.println("Se presento un error de tipo: ");
    	e.printStackTrace();
        
    }    
    
  }
  
private static void deleteContact(){
	try {
	
		Scanner entrada = new Scanner(System.in);
      System.out.println("Dame un email de contacto para eliminar");
      
      QueryResult queryResults = connection.query("SELECT FirstName, LastName, Phone,Email, Id"
        		+ " FROM Contact WHERE Email =  " + "'" + entrada.nextLine() + "'"  );
      if (queryResults.getSize() > 0) {
    	  Contact c = (Contact)queryResults.getRecords()[0];
    	  System.out.println("Se eliminara este contacto: ");
    	  System.out.println(" - Nombre: "+c.getFirstName()+" "+ c.getLastName() 
     		  	 + " " + "- Número Telefonico: "+ c.getPhone()
     		  	 + " " + "- Email: " + " "+ c.getEmail());
    	    	          
              String[] id = {c.getId()};
        	  connection.delete(id);
        	       	
          System.out.println("El contacto se elimino.");
         
      }else{System.out.println("Registro no encontrado");}
      
	} catch (Exception e) {
		e.printStackTrace();
	}
}


private static void createAccount() {
	
	  try{
		  
		  Scanner entrada = new Scanner(System.in);
		  Account cuenta = new Account();
		  System.out.print("Ingrese el nombre de la cuenta: ");
		  cuenta.setName(entrada.nextLine());
		  System.out.print("Ingrese número de la cuenta: ");
		  cuenta.setAccountNumber(entrada.nextLine());
		  System.out.print("Ingrese el nombre de la página web: ");
		  cuenta.setWebsite(entrada.nextLine());
		  System.out.print("Ingrese el número telefónico: ");
		  cuenta.setPhone(entrada.nextLine());
		  
		  connection.create(new SObject[] {cuenta} );
		
	  }catch (Exception e) {
	      e.printStackTrace();
	    } 
	
}


private static void queryContacts() {
    
    try {
       
    	
    			
      System.out.println("");
      System.out.println("Mostrar todos los contactos");
      QueryResult queryResults = connection.query("SELECT FirstName, LastName, Phone,Email, Id"
      		+ " FROM Contact ORDER BY CreatedDate DESC");
      if (queryResults.getSize() > 0) {
        for (int i=0;i<queryResults.getRecords().length;i++) {
          // cast the SObject to a strongly-typed Contact
          Contact c = (Contact)queryResults.getRecords()[i];
          System.out.println(" - Nombre: "+c.getFirstName()+" "+ c.getLastName() 
        		  	 + " " + "- Número Telefonico: "+ c.getPhone()
        		  	 + " " + "- Email: " + " "+ c.getEmail()
        		  	 + " " + "- ID: " + " " + c.getId() );
        }
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }    
    
  }
  

 
}
