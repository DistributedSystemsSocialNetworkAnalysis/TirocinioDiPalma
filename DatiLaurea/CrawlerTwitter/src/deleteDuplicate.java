import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class deleteDuplicate {

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ParseException {
		
		List<JSONObject> list = new ArrayList<JSONObject>();
		ArrayList<String> path = new ArrayList<String>();
		path.add("/Users/francescodipalma/Desktop/Dati_finali/luglio.json");
		//path.add("/Users/francescodipalma/Desktop/Dati_finali/giungo.json");
		//path.add("/Users/francescodipalma/Desktop/Dati_finali/maggio.json");
		//path.add("/Users/francescodipalma/Desktop/Dati_finali/aprile.json");
		//path.add("/Users/francescodipalma/Desktop/Dati_finali/marzo.json");
		//path.add("/Users/francescodipalma/Desktop/Dati_finali/febbraio.json");
		//path.add("/Users/francescodipalma/Desktop/Dati_finali/gennaio.json");
		
		
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
		
		//elimination of duplicates through a jsonObject HashSet
		Set<JSONObject> noDuplicates = new HashSet<JSONObject>(list);
		
		JSONArray newJson = new JSONArray();
		for(JSONObject elem: noDuplicates) {
			newJson.add(elem);
		}
		
		//scrivo in un unico file il risultato
		FileWriter newData = new FileWriter("/Users/francescodipalma/Desktop/Dati_finali/luglio_finali.json");
		//write
		newData.write(newJson.toJSONString());
		newData.flush();
		newData.close();
	}

}
