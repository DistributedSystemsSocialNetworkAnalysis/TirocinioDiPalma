import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class fromJsonToTxt {

    @SuppressWarnings("unchecked")
	public static void main(String args[]) throws FileNotFoundException, IOException, ParseException {
    	
        // File input path
        System.out.println("Starting...");
        
        List<JSONObject> prima_parte = new ArrayList<JSONObject>();
        List<JSONObject> seconda_parte = new ArrayList<JSONObject>();
        List<JSONObject> terza_parte = new ArrayList<JSONObject>();
        
        
		ArrayList<String> path = new ArrayList<String>();
		path.add("/Users/francescodipalma/Desktop/Dati_finali/gennaio_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/febbraio_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/marzo_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/aprile_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/maggio_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/giugno_finali.json");
		path.add("/Users/francescodipalma/Desktop/Dati_finali/luglio_finali.json");
		
		//read
		JSONParser parser = new JSONParser();
		//per ogni file prendo il jsonArray e aggiungo ogni suo oggetto in una lista
		for(String elem: path){
			JSONArray json = (JSONArray) parser.parse(new FileReader(elem));
			
			Iterator<JSONObject> iterator = json.iterator();
			while (iterator.hasNext()) {
				
				JSONObject j = iterator.next();
				StringTokenizer data;
				
				try {
					data = new StringTokenizer((String) j.get("data"));
				}catch(NullPointerException e){ 
					data = null;
				}
				
				if(data != null) {
					String giorno = data.nextToken();
					
					try {
						String mese = data.nextToken();
						int g = Integer.parseInt(giorno);
						
						if(mese.equals("gen") || mese.equals("feb") || (mese.equals("mar") && g < 10)) prima_parte.add(j);
						if(mese.equals("apr") || (mese.equals("mar") && g >= 10) || (mese.equals("mag") && g < 5)) seconda_parte.add(j);
						if(mese.equals("giu") || mese.equals("lug") || (mese.equals("mag") && g >= 5)) terza_parte.add(j);
					}
					catch(NoSuchElementException e) {
						terza_parte.add(j);
					}
				}
				else {
					terza_parte.add(j);
				}

			}
		}
		
		PrintWriter prima = new PrintWriter(new File("/Users/francescodipalma/Desktop/ultime_analisi/prima_parte.txt"));
		PrintWriter seconda = new PrintWriter(new File("/Users/francescodipalma/Desktop/ultime_analisi/seconda_parte.txt"));
		PrintWriter terza = new PrintWriter(new File("/Users/francescodipalma/Desktop/ultime_analisi/terza_parte.txt"));
				
		for(JSONObject elem: prima_parte) {
	
			StringBuilder dati = new StringBuilder();
			dati.append((String) elem.get("testo"));
			dati.append('\n');
			prima.write(dati.toString());
		}
		
		for(JSONObject elem: seconda_parte) {
			
			StringBuilder dati = new StringBuilder();
			dati.append((String) elem.get("testo"));
			dati.append('\n');
			seconda.write(dati.toString());
		}
		
		for(JSONObject elem: terza_parte) {
			
			StringBuilder dati = new StringBuilder();
			dati.append((String) elem.get("testo"));
			dati.append('\n');
			terza.write(dati.toString());
		}
		
		System.out.println("End");
    }
}