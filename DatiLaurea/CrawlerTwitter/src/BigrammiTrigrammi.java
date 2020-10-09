import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BigrammiTrigrammi {
    
    public static void main(String[] args) throws IOException, ParseException {

    	BufferedReader reader = new BufferedReader(new FileReader("/Users/francescodipalma/Desktop/ultime_analisi/prima_parte.txt"));
		String line = reader.readLine();
		
		String parola = "soldi";
		String word = null;
		String prec = null;
		String succ = null;
		HashMap<String, Integer> trigramma = new HashMap<String, Integer>();
		
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(new FileReader("/Users/francescodipalma/Desktop/Dati_finali/stopwords.json"));
		JSONArray j = (JSONArray) json.get("words");
		@SuppressWarnings("unchecked")
		ArrayList<String> stopwords = new ArrayList<String>(j);
		
		while (line != null) {
			line = reader.readLine();
			if(line != null) line = line.toLowerCase();
			
			if(line!=null && line.contains(parola)) {
				
				StringTokenizer st = new StringTokenizer(line, " ,:.;*!?/");
				
				while(st.hasMoreElements()) {
					prec = word;
					word = succ;
					succ = st.nextToken();
					
					if(word!=null && word!="") {
						if(word.equals(parola)) {
							
							if(!stopwords.contains(prec)) {
								if(trigramma.containsKey(prec)) {
									trigramma.put(prec, trigramma.get(prec) + 1);
								}else trigramma.put(prec, 1);
							}
							
							if(!stopwords.contains(succ) ) {
								if(trigramma.containsKey(succ)) {
									trigramma.put(succ, trigramma.get(succ) + 1);
								}else trigramma.put(succ, 1);
							}
					
						}
					}
				}
			}
		}
		reader.close();
		
		FileWriter file = new FileWriter("/Users/francescodipalma/Desktop/ultime_analisi/soldi.txt");
		for(Entry<String, Integer> entry : trigramma.entrySet()) file.append(entry.getKey() + " " + entry.getValue() + "\n");
		file.close();
			
    }


}