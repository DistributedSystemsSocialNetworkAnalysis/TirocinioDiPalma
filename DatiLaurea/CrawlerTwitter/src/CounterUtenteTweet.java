import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CounterUtenteTweet {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ParseException {
		
		ArrayList<String> path = new ArrayList<String>();
		path.add("/Users/francescodipalma/Desktop/Dati_finali/luglio_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/gennaio_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/febbraio_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/marzo_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/aprile_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/maggio_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/giugno_finali.json");
		
		//read
		JSONParser parser = new JSONParser();
		
		//hashmap per memorizzare il nome utente e il numero di tweet
		HashMap<String, Integer> UtentiTweet = new HashMap<String, Integer>();
		int numeroTweet = 0;
		int numeroUtentiTotale = 0;
		int numeroMaxTweet = 0;
		String ideMaxTweet = null;
		
		for(String elem: path){
			JSONArray json = (JSONArray) parser.parse(new FileReader(elem));
			Iterator<JSONObject> iterator = json.iterator();
			
			while (iterator.hasNext()) {
				JSONObject j = iterator.next();
				
				String ide = (String) j.get("identificatore");
				
				if(ide != null) {
					numeroTweet++;
					
				    if(!UtentiTweet.containsKey(ide)) {
				    	UtentiTweet.put(ide, 1);
				    	numeroUtentiTotale++;
				    }
				    else {
				    	int u = UtentiTweet.get(ide);
				    	UtentiTweet.put(ide, u+1);
				    	
				    	if(u+1 > numeroMaxTweet) {
				    		numeroMaxTweet = u+1;
				    		ideMaxTweet = ide;
				    	}
				    }
				    
				}
			}
			
		}
		int nTU = numeroTweet / numeroUtentiTotale;
		
		System.out.println("Tweet toali: " + numeroTweet);
		System.out.println("Utenti totali: " + numeroUtentiTotale);
		System.out.println("Numero Tweet su numero utenti: " + nTU);
		System.out.println("Numero massimo di Tweet: " + numeroMaxTweet);
		System.out.println("Dell'utente: " + ideMaxTweet);

		
	}

}
