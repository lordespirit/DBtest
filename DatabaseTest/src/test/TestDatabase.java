package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import main.DBConnection;

public class TestDatabase {

	//@Test
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
	
	//@Test
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
	
	@SuppressWarnings("rawtypes")
	//@Test
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
	
	/**
	 * Este test prueba la conexión, el insert, select, delete y update
	 */
	@Test
	public void testUpdate(){
		
		DBConnection connector = new DBConnection("dbtest","edu","1234");
		@SuppressWarnings("rawtypes")
		ArrayList<Map> arrayResult = null;
		int id = -1;
		
		try {
			
			connector.connect();
			connector.deleteAll();
			id = connector.insert("Edu","Edu@edu.com","www.myweb.edu.es", "Summary de Edu", "Comentario de Edu");
			arrayResult = connector.select(id);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			connector.close();
			
		}
		
		Assert.assertEquals(true, id>-1);
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> hashMap1 =  (HashMap<String, String>) arrayResult.get(0); 
		
		
		Assert.assertEquals("Edu", hashMap1.get("myuser")); 
		Assert.assertEquals("Edu@edu.com", hashMap1.get("email")); 
		Assert.assertEquals("www.myweb.edu.es", hashMap1.get("webpage")); 
		Assert.assertEquals("Summary de Edu", hashMap1.get("summary")); 
		Assert.assertEquals("Comentario de Edu", hashMap1.get("comments"));
		
		try {
			connector.connect();
			connector.update(1, "Eduard", "edu@movistar.es", "mypage.edu.edu", "Nuevo sumario", "Nueva página de prueba");
			arrayResult = connector.select(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connector.close();
		}
		
		@SuppressWarnings("unchecked")
		HashMap<String, String> hashMap2 =  (HashMap<String, String>) arrayResult.get(0); 
		
		
		Assert.assertEquals("Eduard", hashMap2.get("myuser")); 
		Assert.assertEquals("edu@movistar.es", hashMap2.get("email")); 
		Assert.assertEquals("mypage.edu.edu", hashMap2.get("webpage")); 
		Assert.assertEquals("Nuevo sumario", hashMap2.get("summary")); 
		Assert.assertEquals("Nueva página de prueba", hashMap2.get("comments"));
		
		DBConnection.writeResultSet(hashMap2);
		
	}
	
}
