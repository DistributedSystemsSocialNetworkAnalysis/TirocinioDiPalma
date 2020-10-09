import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.json.simple.parser.ParseException;

public class frequencyWords {
    
	//TF
    public double tf(List<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        System.out.println("TF = " + result + " / " + doc.size());
        return result / doc.size();
    }

    //IDF
    public double idf(int occTotale, int numTotParole, String term) {
    	
        System.out.println("IDF = " + "Math.log(" + numTotParole + " / " + occTotale + ")");
        return Math.log(numTotParole / occTotale);
    }

    //TF * IDF
    public double tfIdf(List<String> doc, int OT, int NTP, String term) {
        return tf(doc, term) * idf(OT, NTP, term);

    }

    public static void main(String[] args) throws IOException, ParseException {

    	BufferedReader reader = new BufferedReader(new FileReader("/Users/francescodipalma/Desktop/ultime_analisi/terza_parte.txt"));
		String line = reader.readLine();
		 
		int linea = 2;
		int lineaOccMax = 2;
		List<String> doc = null;
		String parola = "soldi";
		int occorrenzaMax = 0;
		int occorrenzaTotale = 0;
		int nTotaleParole = 0;
		String word = null;
		
		while (line != null) {
			line = reader.readLine();
			
			if(line!=null) {
				StringTokenizer st = new StringTokenizer(line, " ,:.;*!/");
				List<String> l1 = new ArrayList<String>();
				int occorrenza = 0;
				
				while(st.hasMoreElements()) {
					word = st.nextToken();
					
					if(word!=null && word!="") {
						if(word.equalsIgnoreCase(parola)) {
							occorrenzaTotale++;
							occorrenza++;
						}
						nTotaleParole++;
						l1.add(word);
					}
				}
				
				if(occorrenza > occorrenzaMax) {
					doc = l1;
					lineaOccMax = linea;
					occorrenzaMax = occorrenza;
				}
			}
			linea++;
		}
		reader.close();

        frequencyWords calculator = new frequencyWords();
        double tfidf = calculator.tfIdf(doc, occorrenzaTotale ,nTotaleParole, parola);
        System.out.println("TF-IDF (" + parola + ") = " + tfidf);
        System.out.println("La parola " + parola + " occorre " + occorrenzaMax + " volta/e al massimo in un documento, alla linea " + lineaOccMax);


    }


}