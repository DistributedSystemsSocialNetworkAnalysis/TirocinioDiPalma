import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class counter {

	
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
			int [] d = new int[32];
			for(int i = 0;i<32;i++) d[i] = 0;
			
			while (iterator.hasNext()) {
				JSONObject j = iterator.next();
				
				String date = (String) j.get("data");
				
				if(date != null) {
				
					StringTokenizer st = new StringTokenizer(date);
				    
					int num = 0;
					try {
						num = Integer.parseInt(st.nextToken());
					}
					catch(Exception e) { d[31]++; num = -1; }
					
					if(num != -1 && st.nextToken() != "mese in corso") d[num-1]++;
				    
				}
			}
			
			for(int i = 0;i<31;i++) System.out.println(d[i]);
		}
		
		
	}

}

