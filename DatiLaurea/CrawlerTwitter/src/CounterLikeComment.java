import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CounterLikeComment {

	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ParseException {
		
		ArrayList<String> path = new ArrayList<String>();
		path.add("/Users/francescodipalma/Desktop/Dati_finali/luglio_finali.json");
		
		//read
		JSONParser parser = new JSONParser();
		
		//per ogni file prendo il jsonArray e conto
		for(String elem: path){
			JSONArray json = (JSONArray) parser.parse(new FileReader(elem));
			
			Iterator<JSONObject> iterator = json.iterator();
			int [] LikeGiornaliero = new int[32];
			for(int i = 0;i<32;i++) LikeGiornaliero[i] = 0;
			
			int [] CommentiGiornaliero = new int[32];
			for(int i = 0;i<32;i++) CommentiGiornaliero[i] = 0;
			
			int numeroLike = 0;
			int numeroCommenti = 0;
			int maxLike = 0;
			String testoLike = "";
			String nomeLike = "";
			int maxCommenti = 0;
			String testoCommenti = "";
			String nomeCommenti = "";
			
			while (iterator.hasNext()) {
				JSONObject j = iterator.next();
				
				int like, commenti;
				String date = (String) j.get("data");
				String testo = (String) j.get("testo");
				String nome = (String) j.get("nome");
				
				String l = (String) j.get("like");
				if(l != null) l = l.replace(".", "");
				if(l != null && !l.equals("")) like = Integer.parseInt(l);
				else like = 0;
				
				if(like > maxLike) {
					maxLike = like;
					testoLike = testo;
					nomeLike = nome;
				}
				
				String c = (String) j.get("comment");
				if(c != null) c = c.replace(".", "");
				if(c != null && !c.equals("")) commenti = Integer.parseInt(c);
				else commenti = 0;
				
				if(commenti > maxCommenti) {
					maxCommenti = commenti;
					testoCommenti = testo;
					nomeCommenti = nome;
				}
				
				if(date != null) {
				
					StringTokenizer st = new StringTokenizer(date);
				    
					int num = 0;
					try {
						num = Integer.parseInt(st.nextToken());
					}
					catch(Exception e) { 
						LikeGiornaliero[31] = LikeGiornaliero[31] + like; 
						CommentiGiornaliero[31] = CommentiGiornaliero[31] + commenti;
						num = -1; 
					}
					
					if(num != -1 && st.nextToken() != "mese in corso") {
						int n = num-1;
						LikeGiornaliero[n] = LikeGiornaliero[n] + like; 
						CommentiGiornaliero[n] = CommentiGiornaliero[n] + commenti;
					}
				    
				}
			}
			
			System.out.println("Like:");
			for(int i = 0;i<31;i++) {
				System.out.println(LikeGiornaliero[i]);
				numeroLike = numeroLike + LikeGiornaliero[i];
			}
			
			System.out.println("Commenti:");
			for(int i = 0;i<31;i++) {
				System.out.println(CommentiGiornaliero[i]);
				numeroCommenti = numeroCommenti + CommentiGiornaliero[i];
			}
			
			System.out.println("Numero totale Like: " + numeroLike);
			System.out.println("Numero totale Commenti: " + numeroCommenti);
			
			System.out.println("Miglior numero di like del mese: " + maxLike);
			System.out.println("Di: " + nomeLike);
			System.out.println("Con testo: " + testoLike);
			
			System.out.println("Miglior numero di commenti del mese: " + maxCommenti);
			System.out.println("Di: " + nomeCommenti);
			System.out.println("Con testo: " + testoCommenti);
		}
		
		
	}

}


