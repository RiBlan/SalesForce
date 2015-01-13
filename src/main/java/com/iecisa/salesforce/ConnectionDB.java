package com.iecisa.salesforce;
//ola k ase?
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
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String sql;
		
		public void contactData (String dataExternal,String idContact){
									
		try{
			
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
			statement = connection.createStatement();
			
//			String sql = "INSERT INTO Registration " +
//	                   "VALUES (100, 'Zara', 'Ali', 18)";

			sql = "INSERT INTO info " +
	                   "VALUES ("+ "'" + idContact + "'"  + "," + "'" + dataExternal + "'" + ")";
			
	        statement.executeUpdate(sql);
		      
	 
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
		  
			
		}//end contactData
		
    public void queryData (String data){
    	String con;
	    try{	
	    	Class.forName(JDBC_DRIVER);
	    	connection= DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
	    	statement = connection.createStatement();
	    	
	    	sql = "SELECT confidencial from info WHERE id = '" + data + "'" ;
	    
	    	resultSet = statement.executeQuery(sql);
	    	
	    	if(resultSet.next()){
	    		con = resultSet.getString("confidencial");
		    	System.out.println(con);
	    	}
	    	
//	    	if (resultSet!=null){
//	    	con = resultSet.getString("confidencial");
//	    	System.out.println(con);
//	    	}
	    	
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
		
	}//end queryData
    
    public void updateData(String dataExternal, String idContact){
    	try{
    	
    	Class.forName(JDBC_DRIVER);
    	connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
		statement = connection.createStatement();
		
		// UPDATE table_name SET field1=new-value1, field2=new-value2
		// [WHERE Clause]
		sql =  "UPDATE info SET confidencial = '" + dataExternal + "' " + "WHERE id = '" + idContact + "'"; 
		
        statement.executeUpdate(sql);
    	
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
    }//end updateData

    public void deleteData (String idContact){
//    	DELETE FROM table_name [WHERE Clause]
//    	If WHERE clause is not specified, then all the records will be deleted from the given MySQL table.
    	try{
    	Class.forName(JDBC_DRIVER);
    	connection = DriverManager.getConnection(DATABASE_URL,USER,PASSWORD);
    	statement = connection.createStatement();
    	
    	sql = "DELETE FROM info WHERE id = '" + idContact + "'";
    	statement.executeUpdate(sql);
    	    	
    	}catch(SQLException e){
    		//Handle errors for JDBC
    		e.printStackTrace();
    	}catch(Exception e){
    		//Hadle errors for Class.forName
    		e.printStackTrace();
    	}finally{
    		//finally block used to closed resources
    		try{
    			if(statement!=null)
    				connection.close();
    		}catch(SQLException e){
    		}//do nothing
    		try{
    			if(connection!=null)
    				connection.close();
    		}catch(SQLException e){
    			e.printStackTrace();
    		}
    	}
    }//end deleteData
    
    
	}//end ConnectionDB
	
	

