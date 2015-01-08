package com.iecisa.salesforce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

		//database URL
		private static final String DATABASE_URL = "jdbc:mysql://localhost/salesforce";
		//driver name
		private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		
		//user
		private static final String USER = "root";
		//password
		private static final String PASSWORD = "123456";
		
		
		
		
		public void contactData (String dataExternal,String idContact){
			
			
			System.out.println(dataExternal);
					
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try{
			
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
			System.out.println("Conecto exitosamente...");
			statement = connection.createStatement();
			
//			String sql = "INSERT INTO Registration " +
//	                   "VALUES (100, 'Zara', 'Ali', 18)";

			String sql = "INSERT INTO info " +
	                   "VALUES ("+ "'" + idContact + "'"  + "," + "'" + dataExternal + "'" + ")";
			
	        statement.executeUpdate(sql);
		      
			resultSet = statement.executeQuery("SELECT * FROM info" );
			
			
			
			while(resultSet.next()){
				System.out.println(resultSet.getString("id") + "\t" 
								+  resultSet.getString("confidencial") );
			}
						
		 
		}catch(SQLException e){
			//Handle errors for JDBC
			e.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to closed resources
			try{
				if(statement!=null)
					connection.close();
			}catch(SQLException e){
			 }//nada (do nothing)
			try{
				if(connection!=null)
					connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}//end finally try
		
		  
			
		}//end main
	}//end ConnectionDB
	
	

