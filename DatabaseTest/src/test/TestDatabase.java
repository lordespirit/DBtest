package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import main.DBConnection;

public class TestDatabase {

	@Test
	public void testConnection(){
		
		boolean result = false;
		DBConnection connector = new DBConnection("dbtest","edu","1234");
		
		try {
			result = connector.connect();
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}finally {
			connector.close();
		}
		
		Assert.assertEquals(true, result);
		
	}
	
	//@Test
	public void testInsert(){
		
		DBConnection connector = new DBConnection("dbtest","edu","1234");
		int id = -1;
		
		try {
			
			connector.connect();
			id = connector.insert("edu","edu@edu.com","www.edu.com", "00edu", "Prueba Edu DB");
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			connector.close();
			
		}
		
		Assert.assertEquals(true, id>-1);
		
	}
	
	@Test
	public void testDelete(){
		
		DBConnection connector = new DBConnection("dbtest","edu","1234");
		try {
			
			connector.connect();
			connector.deleteAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		
			connector.close();
		}
	}
	
	//@Test
	public void testSelectAll(){
		
		
	}
	
	@Test
	public void testSelectIntID(){
		
		DBConnection connector = new DBConnection("dbtest","edu","1234");
		ArrayList<Map> arrayResult = null;
		int id = -1;
		
		try {
			
			connector.connect();
			id = connector.insert("Edu","Edu@edu.com", "www.myweb.edu.es", "Summary de Edu", "Comentario de Edu");
			arrayResult = connector.select(id);
			
		} catch (Exception e){
			e.printStackTrace();
			
		}finally {
			connector.close();
		}
		
		
		Assert.assertEquals("Edu", arrayResult.get(0).get("myuser"));
		Assert.assertEquals(arrayResult.size(), 1);
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> hashMap =  (HashMap<String, String>) arrayResult.get(0); 
		
		
		Assert.assertEquals("Edu", hashMap.get("myuser")); 
		Assert.assertEquals("Edu@edu.com", hashMap.get("email")); 
		Assert.assertEquals("www.myweb.edu.es", hashMap.get("webpage")); 
		Assert.assertEquals("Summary de Edu", hashMap.get("summary")); 
		Assert.assertEquals("Comentario de Edu", hashMap.get("comments"));
		
		DBConnection.writeResultSet(hashMap);
		
	}
	
}
