import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class ContaUtentiGiornalieri {

	public static void main(String args[]) throws IOException {
		
		String csvFile = "/Users/francescodipalma/Desktop/Dati_finali/dataset.csv";
	    BufferedReader br = null;
	    String line = "";
	    String cvsSplitBy = "\t";
	    HashMap<String, ParametriUtente> utenti = new HashMap<>();
	
	    try {
	
	        br = new BufferedReader(new FileReader(csvFile));
	        while ((line = br.readLine()) != null) {
	
	            // use comma as separator
	            String[] u = line.split(cvsSplitBy);
	
	            if(utenti.containsKey(u[0])) {
	            	ParametriUtente p = utenti.get(u[0]);
	            	
	            	p.aggiugiTweet();
	            	if(!p.getDate().contains(u[2])) p.aggiungiData(u[2]);
	            	if(!p.getTesti().contains(u[5])) p.aggiungiTesto(u[5]);
	            	
	            	utenti.put(u[0], p);
	            }
	            else {
	            	ArrayList<String> testi = new ArrayList<>();
	            	ArrayList<String> date = new ArrayList<>();
	            	date.add(u[2]); testi.add(u[5]); 
	            	
	            	ParametriUtente p = new ParametriUtente(u[0], 1, testi, date);
	            	utenti.put(u[0], p);
	            }
	
	        }
	
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    FileWriter file = new FileWriter("/Users/francescodipalma/Desktop/Dati_finali/dataCount.txt");
	    ArrayList<String> persone = new ArrayList<>();
		
	    for(int i = 1; i<=7; i++) {
	    	
	    	int j = 0;
	    	String data = "";
	    	if(i == 7) { j = 31; data = " lug"; }
	    	if(i == 6) { j = 30; data = " giu"; }
	    	if(i == 5) { j = 31; data = " mag"; } 
	    	if(i == 4) { j = 30; data = " apr"; }
	    	if(i == 3) { j = 31; data = " mar"; }
	    	if(i == 2) { j = 29; data = " feb"; }
	    	if(i == 1) { j = 31; data = " gen"; }
	    			
	    	for(int k = 1;k<=j; k++) {
	    		String dataFinale = k + data;
	    		file.append(dataFinale + ":");
	    		
	    		int count = 0;
	    		int countUnici = 0;
	    		
			    for(Entry<String, ParametriUtente> entry : utenti.entrySet()) {
			    	
			        ParametriUtente value = entry.getValue();
			        
			        String ide = value.getIde();
			        if(value.getIde() == "") ide = "@_A_mors";
			        
			        
		
			        if(value.getDate().contains(dataFinale)) {
			        	count++;
			        	if(!persone.contains(ide)) {
			        		countUnici++;
			        		persone.add(ide);
			        	}
			        }
			        
			    }
			    file.append(count + " " + countUnici + "\n");
	    	}
	    }
	    
	    file.close(); 
	}
}
