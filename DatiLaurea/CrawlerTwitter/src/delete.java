import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class delete {
	//per cancellare i superfli di gennaio
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ParseException {
		
		List<JSONObject> list = new ArrayList<JSONObject>();
		ArrayList<String> path = new ArrayList<String>();
		path.add("/Users/francescodipalma/Desktop/Dati_Secondo_Grafico/tutti/gennaio.json");
		
		//read
		JSONParser parser = new JSONParser();
		
		//per ogni file prendo il jsonArray e aggiungo ogni suo oggetto in una lista
		for(String elem: path){
			JSONArray json = (JSONArray) parser.parse(new FileReader(elem));
			
			Iterator<JSONObject> iterator = json.iterator();
			while (iterator.hasNext()) {
				JSONObject j = iterator.next();
				String date = (String) j.get("data");
				if(date != null) {
				
					StringTokenizer st = new StringTokenizer(date);
				    System.out.print(st.nextToken());
				    System.out.print(st.nextToken());
				    try {
				    	System.out.println(st.nextToken());
				    }
				    catch(NoSuchElementException e) {
				    	System.out.println();
				    	list.add(j);
				    }
				}
			    
			}
		}
		
		//eliminazione dei duplicati tramite un HashSet di jsonObject
		Set<JSONObject> noDuplicates = new HashSet<JSONObject>(list);
		
		JSONArray newJson = new JSONArray();
		for(JSONObject elem: noDuplicates) {
			newJson.add(elem);
		}
		
		//scrivo in un unico file il risultato
		FileWriter newData = new FileWriter("/Users/francescodipalma/Desktop/Dati_Secondo_Grafico/tutti/gennaio_nodup.json");
		//write
		newData.write(newJson.toJSONString());
		newData.flush();
		newData.close();
	}

}

