package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBConnection {
	
	private static final String DB_TABLE = "comments";
	private final String DBNAME;
	private final String DBUSER;
	private final String DBPASS;
	private Connection connect;
	private Statement statement;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;
	
	public DBConnection(String dbname, String dbuser, String dbpass){
		this.DBNAME = dbname;
		this.DBUSER = dbuser;
		this.DBPASS = dbpass;
	}
	
	@SuppressWarnings("rawtypes")
	private ArrayList<Map> resultSetToCollection(ResultSet resultSet) throws SQLException { 
        // ResultSet is initially before the first data set
         ArrayList<Map> list = new ArrayList<Map>(); 
        
        while (resultSet.next()) {
            HashMap<String,String> hashMap = new HashMap<String,String>(); 
            String id = resultSet.getString("id");
            String myuser = resultSet.getString("myuser");
            String email = resultSet.getString("email");
            String webpage = resultSet.getString("webpage");
            String summary = resultSet.getString("summary");
            Date datum = resultSet.getDate("datum");
            String comments = resultSet.getString("comments");
       
            hashMap.put("id",id);
            hashMap.put("myuser",myuser);
            hashMap.put("email", email);
            hashMap.put("webpage",webpage);
            hashMap.put("summary",summary);
            hashMap.put("datum",datum.toString());
            hashMap.put("comments",comments);
            
            list.add(hashMap);  
        }
        
        return list; 
    }
 
	@SuppressWarnings("rawtypes")
	public ArrayList<Map> select(int id) throws SQLException {
		
		String selectSQL = "SELECT * FROM " + DB_TABLE + " WHERE id=?";
		ArrayList<Map> list = new ArrayList<Map>();
		
		try {
			
			preparedStatement = connect.prepareStatement(selectSQL);
			preparedStatement.setInt(1,id);
			resultSet = preparedStatement.executeQuery();
			list = resultSetToCollection(resultSet); 
			
		} catch (Exception e) {
			
			close(); 
			throw e;
		}
		
		return list;
	}
	
	/**
	 * Abre la conexión a la base de datos.
	 * Si no puede, cierr
	 * @return 
	 * @throws Exception
	 */
	public boolean connect() throws Exception {
		try {
			
			//CARGAR DROVER MYSQL
			Class.forName("com.mysql.jdbc.Driver");
			// jdbc:mysql://ip // database
			connect = DriverManager.getConnection("jdbc:mysql://localhost/" + DBNAME + "?user=" + DBUSER +"&password=" + DBPASS);
			// Statement allow to issue SQL queries to the database
			statement = connect.createStatement();
			
		} catch (Exception e) {
			
			close();
			throw e;
			
		}
		
		return statement !=null;
	}
	
	public void close() {
		try {
			
			if (resultSet !=null)
				resultSet.close(); resultSet = null;
			if (statement != null)
				statement.close(); statement = null;
			if (connect != null)
				connect.close(); connect = null;
			
		} catch (Exception e) {
			
		}
	}
	
	/**
	  *      INSERT INTO tabla values (default, ?, ?, ?, ? , ?, ? )
	  * 
	  * 
	  * @param user
	  * @param email
	  * @param sumary
	  * @param comment
	 * @return 
	 * @throws SQLException 
	  */
	public int insert(String user, String email,String webpage ,String summary, String comment) throws SQLException {
		
		int lastInsertderId = -1;
		String strSQL = "INSERT INTO " + DB_TABLE + " VALUES (default,?,?,?,?,?,?)";
		
		try {
		
			preparedStatement = connect.prepareStatement(strSQL,Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, webpage);
			preparedStatement.setDate(4, new java.sql.Date(System.currentTimeMillis()));
			preparedStatement.setString(5, summary);
			preparedStatement.setString(6, comment);
			
			// Ejecutamos y guardamos la key de resultado en rs (Objeto ResultSet)
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			
			if(rs.next())
				lastInsertderId = rs.getInt(1);
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
		return lastInsertderId;
		
	}
	
	/**
	  * Elimina todos los reigistro de la tabla y reinicia la cuenta de ids
	  * @throws SQLException
	*/
	public void deleteAll() throws SQLException{
		
		try {
			
			preparedStatement = connect.prepareStatement("TRUNCATE "+DB_TABLE);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			
			close();
			e.printStackTrace();
			
		}
	}
	
	/**
	  * Elimina un registro por su ID
	  * @param id registro a eliminar
	  * @throws SQLException
	  */
	 public void delete(int id) throws SQLException{		
			try {
				preparedStatement = connect
				        .prepareStatement("delete from "+DB_TABLE+"  where id= ? ; ");
				 preparedStatement.setInt(1, id);
		         preparedStatement.executeUpdate();
			
		    } catch (SQLException e) {
		    	close(); 
	            throw e;
			}
	 }
	
	 public static void writeResultSet(HashMap<String,String> hashMap ){
         System.out.print("\tid: " +        hashMap.get("id"));
         System.out.print("\tUser: " +     hashMap.get("myuser"));
         System.out.print("\tWebsite: " +  hashMap.get("webpage"));
         System.out.print("\tsummary: " +  hashMap.get("summary"));
         System.out.print("\tDate: " +     hashMap.get("datum"));
         System.out.print("\tComment: " +  hashMap.get("comments"));
         System.out.print("\t\n");
         
	 }


}
