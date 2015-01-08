package com.iecisa.salesforce;

import java.util.Scanner;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Account;
import com.sforce.soap.enterprise.sobject.Contact;
import com.sforce.soap.enterprise.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class Main {
  
	    	
static final String USERNAME = "frblanco@iecisa.com.mx";
static final String PASSWORD = "26Logan$onSaUNxRg6bkt1tGXdSDpAp7houJ4";
static EnterpriseConnection connection;


  public static void main(String[] args) {

    ConnectorConfig config = new ConnectorConfig();
    config.setUsername(USERNAME);
    config.setPassword(PASSWORD);
   
    
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
//    menu.close();
      }
        
    } catch (ConnectionException e1) {
        e1.printStackTrace();
    }  

  }
  

/*
 * Contactos  
 */

  

private static void createContacts() {
    System.out.println("Capturar Nuevo Contacto");
    try {
    	ConnectionDB dataExternal = new ConnectionDB();
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
      	
    	SaveResult[] saveResults = connection.create(new SObject[] {contacto} );
    	
      	//for ConnectionDB, External Data and ID Contact
      	System.out.println("Ingrese Dato externo");
      	String dato = entrada.nextLine();
      	if(!(dato.equals('\n'))){
      		System.out.println(dato);
      		dataExternal.contactData(entrada.nextLine(), saveResults[0].getId());
      	}      	
      	
      	System.out.println(saveResults[0].getId());
      	     	
//      	entrada.close();
      	
	} catch (Exception e) {
		
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


private static void updateContact() {
    
    try {
      
      Scanner entrada = new Scanner(System.in);

      System.out.println("Dame un email de contacto para modificar");

      
      QueryResult queryResults = connection.query("SELECT FirstName, LastName, Phone,Email, Id"
        		+ " FROM Contact WHERE Email =  " + "'" + entrada.nextLine() + "'"  );
      if (queryResults.getSize() > 0) {

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
      
//    entrada.close();
    } catch (Exception e) {
    	System.out.println("Se presento un error de tipo: ");
    	e.printStackTrace();
        
    }    
    
  }
  
private static void deleteContact(){
	try {
	
		Scanner entrada = new Scanner(System.in);
      System.out.println("Dame un ID de contacto para eliminar");
      
      QueryResult queryResults = connection.query("SELECT FirstName, LastName, Phone,Email, Id"
        		+ " FROM Contact WHERE Id =  " + "'" + entrada.nextLine() + "'"  );
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
//   entrada.close();  
	} catch (Exception e) {
		e.printStackTrace();
	}
}


/*
 * Cuentas
 */

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
//	  entrada.close();
	  }catch (Exception e) {
	      e.printStackTrace();
	    } 
	
}



 
}
