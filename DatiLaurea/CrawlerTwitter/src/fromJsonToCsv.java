import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class fromJsonToCsv {

    @SuppressWarnings("unchecked")
	public static void main(String args[]) throws FileNotFoundException, IOException, ParseException {
    	
        // File input path
        System.out.println("Starting...");
        
        List<JSONObject> list = new ArrayList<JSONObject>();
		ArrayList<String> path = new ArrayList<String>();
		path.add("/Users/francescodipalma/Desktop/Dati_finali/luglio_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/giugno_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/maggio_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/aprile_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/marzo_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/febbraio_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/gennaio_finali.json");
		
		//read
		JSONParser parser = new JSONParser();
		//per ogni file prendo il jsonArray e aggiungo ogni suo oggetto in una lista
		for(String elem: path){
			JSONArray json = (JSONArray) parser.parse(new FileReader(elem));
			
			Iterator<JSONObject> iterator = json.iterator();
			while (iterator.hasNext()) {
				list.add(iterator.next());
			}
		}
		
		try (PrintWriter writer = new PrintWriter(new File("/Users/francescodipalma/Desktop/Dati_finali/datiTab.csv"))) {
				
		      StringBuilder info = new StringBuilder();
		      info.append("identificatore");
		      info.append('\t');
		      info.append("nome");
		      info.append('\t');
		      info.append("data");
		      info.append('\t');
		      info.append("like");
		      info.append('\t');
		      info.append("comment");
		      info.append('\t');
		      info.append("testo");
		      info.append('\n');
		      
		      writer.write(info.toString());
		      
		      for(JSONObject elem: list) {
	
		    	  StringBuilder dati = new StringBuilder();
		    	  if(elem.get("identificatore") != null){
		    	  
			    	  dati.append((String) elem.get("identificatore"));
			    	  dati.append('\t');
			    	  dati.append((String) elem.get("nome"));
			    	  dati.append('\t');
			    	  dati.append((String) elem.get("data"));
			    	  dati.append('\t');
			    	  dati.append((String) elem.get("like"));
			    	  dati.append('\t');
			    	  dati.append((String) elem.get("comment"));
			    	  dati.append('\t');
			    	  dati.append((String) elem.get("testo"));
			    	  dati.append('\n');
		
				      writer.write(dati.toString());
		    	  }
		      }
		      
		}catch(FileNotFoundException e){}
		
		System.out.println("End");
    }
}