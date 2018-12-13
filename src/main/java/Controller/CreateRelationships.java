package Controller;

import Data.DataExample;

public class CreateRelationships {

	public static String LinkID(String link) {
		int index1 = link.indexOf("/");
		int index2 = link.indexOf(".",index1+2);
		return link.substring(index1+2, index2);
	}
	
	public CreateRelationships() {
	}
	
	public void CreateRelationship() {
		DataExample  data = new DataExample();
		try {		
			for(int i=0; i<data.link.length; i++) {	
				String linkid = LinkID(data.link[i]);
				ConnectionDB.cn.execute("CREATE ("+linkid+":Link { "
					+ "Link: '"+data.link[i]+"', "			
					+ "LinhID: '"+linkid+"'})");
				System.out.println("Da them "+linkid+"!");
			}	
			
			ConnectionDB.cn.execute("MATCH (per:Person),(lk:Link) WHERE per.LinkTrichRut = lk.Link\r\n" + 
					"CREATE (per)-[:TRICH_RUT_TU]->(lk)");
			ConnectionDB.cn.execute("MATCH (org:Organization),(lk:Link) WHERE org.LinkTrichRut = lk.Link\r\n" + 
					"CREATE (org)-[:TRICH_RUT_TU]->(lk)");
			ConnectionDB.cn.execute("MATCH (loca:Location),(lk:Link) WHERE loca.LinkTrichRut = lk.Link\r\n" + 
					"CREATE (loca)-[:TRICH_RUT_TU]->(lk)");
			ConnectionDB.cn.execute("MATCH (event:Event),(lk:Link) WHERE event.LinkTrichRut = lk.Link\r\n" + 
					"CREATE (event)-[:TRICH_RUT_TU]->(lk)");
			ConnectionDB.cn.execute("MATCH (time:Time),(lk:Link) WHERE time.LinkTrichRut = lk.Link\r\n" + 
					"CREATE (time)-[:TRICH_RUT_TU]->(lk)");
			ConnectionDB.cn.execute("MATCH (country:Country),(lk:Link) WHERE country.LinkTrichRut = lk.Link\r\n" + 
					"CREATE (country)-[:TRICH_RUT_TU]->(lk)");
			
			
			} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
}
